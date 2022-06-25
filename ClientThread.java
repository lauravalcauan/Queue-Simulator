import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ClientThread implements Runnable {
    private Client client;
    private int threadID = 0;
    private SimulationTime time;
    FileWriter outFile;

    public ClientThread(Client client, ArrayList<Client> waitingClients, SimulationTime time, FileWriter outFile){
        this.client = client;
        this.time = time;
        this.outFile = outFile;
        time.setWaitingClients(waitingClients);
    }

    public void run() {
        threadID = splitThreadName(Thread.currentThread().getName());
        time.addWaitingTime(time.getSimulationTime() - client.getArrivalTime() - 1);
        processCommandDuring(client.getServiceTime());
    }

    private void processCommandDuring(int seconds) {
        try {
            for(int i = 0; i< seconds; i++) {
                printConfig();
                client.setServiceTime(client.getServiceTime() - 1);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int splitThreadName(String fullName) {
        String[] values = fullName.split("-");
        return Integer.parseInt(values[3]);
    }

    public void printConfig() {
        try {
            outFile.write("Queue " + threadID + ": " + "(" + client.getID() + ", " + client.getArrivalTime() + ", " + client.getServiceTime() + ")" + "\n");
        } catch (IOException e) {
            System.out.println("");
        }
    }

}