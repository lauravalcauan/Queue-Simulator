import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SimulationTime implements Runnable {

    private Thread simulationThread;
    private int simulationTime;
    private Simulator simulator;
    private int duration;
    private float averageWaitingTime = 0f;
    private boolean running;
    private ArrayList<Client> waitingClients = new ArrayList<>();
    FileWriter outFile;

    public SimulationTime(Simulator simulator, FileWriter outFile) {
        simulationThread = new Thread(this);
        this.outFile = outFile;
        simulationTime = 1;
        running = false;
        this.simulator = simulator;
        this.duration = this.simulator.getDurationOfSimulation();
    }

    public void startSimulation() {
        simulationThread.start();
        running = true;
    }

    public void stopSimulation() {
        this.running = false;
    }

    public void setWaitingClients(ArrayList<Client> waitingClients) {
        this.waitingClients = waitingClients;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public void addWaitingTime(int amount) {
        this.averageWaitingTime += (float) amount;
    }

    public void setAverageWaitingTime(int nr) {
        this.averageWaitingTime = this.averageWaitingTime/(float) nr;
    }

    public float getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public void printWaitingClients() throws IOException {
        ArrayList<Client> printing =  new ArrayList<>(this.waitingClients);
        outFile.write("Waiting: ");
        for(Client c: printing)
            outFile.write("(" + c.getID() + ", " + c.getArrivalTime() + ", " + c.getServiceTime() + "), ");
        outFile.write("\n");
    }

    public void run() {

        while (running) {
            try {
                while(duration >= simulationTime) {
                    if(simulationTime != 0) {
                        outFile.write("\n" + "Time: " + simulationTime + "\n");
                        printWaitingClients();
                    }
                    this.simulator.setSimulationTime(simulationTime);
                    simulationTime = simulationTime + 1;
                    Thread.sleep(1000);
                }
                running = false;
            } catch (InterruptedException | IOException e1) {
                System.out.print(" ");
            }
        }
    }
}