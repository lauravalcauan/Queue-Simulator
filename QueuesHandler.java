import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;

public class QueuesHandler implements Runnable {
    private String readFile;
    private String writeFile;
    private ArrayList<Client> clients;
    private ArrayList<Client> remainingClients;
    private Simulator simulation;
    private SimulationTime time;
    private ExecutorService executor;
    FileWriter outFile;

    public QueuesHandler(String readFile, String writeFile) {
        this.readFile = readFile;
        this.writeFile = writeFile;
    }

    public void setAverageWaitingTime() {
        ArrayList<Client> avg =  new ArrayList<>(this.clients);
        for(Client c: avg) {
            time.addWaitingTime(c.getServiceTime());
        }
    }

    public void setRemainingClients(ArrayList<Client> waitingClients) {
        this.remainingClients = waitingClients;
    }

    public void startProgram(){
        simulation = new Simulator(readFile, writeFile); //reads the data
        simulation.toString(); //simulation contains all the data read from files
        this.clients = simulation.getClients();
        setRemainingClients(simulation.getClients());
        try {
            outFile = new FileWriter(writeFile);
            for(Client c : clients) {
                outFile.write(c.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("");
        }
        this.time = new SimulationTime(simulation, outFile);
        time.setWaitingClients(this.remainingClients);
        time.startSimulation();
        setAverageWaitingTime();
        this.executor = Executors.newFixedThreadPool(simulation.getNrQueues());
        this.run();

        time.stopSimulation();
        time.setAverageWaitingTime(clients.size());
        try {
            outFile.write("Average Waiting Time: " + time.getAverageWaitingTime());
            this.outFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        for(Client c: clients)
        {
            if(c.getArrivalTime() >= time.getSimulationTime())
                processCommandBefore(c.getArrivalTime() - time.getSimulationTime()+1);
            this.remainingClients.remove(0);
            Runnable worker = new ClientThread(c, remainingClients, time, outFile);
            executor.execute(worker);
        }
        processCommandBefore(this.clients.get(clients.size()-1).getServiceTime());
        executor.shutdown();
    }

    private void processCommandBefore(int seconds) {
        try {
            for(int i = 0; i< seconds; i++) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
