import java.io.*;
import java.util.*;

public class Simulator {
    private int nrClients;
    private int nrQueues;
    private int simulationTime;
    private int durationOfSimulation;
    private int arrivalMinTime;
    private int arrivalMaxTime;
    private int serviceMinTime;
    private int serviceMaxTime;

    private ArrayList<Client> clients;
    private Scanner scan;

    File readFile;
    public Simulator(String readFileName, String writeFileName){
        try {
            readFile = new File(readFileName);
            scan = new Scanner(readFile);

            nrClients = Integer.parseInt(scan.nextLine());
            nrQueues = Integer.parseInt(scan.nextLine());
            durationOfSimulation = Integer.parseInt(scan.nextLine());

            String[] values = scan.nextLine().split(",");
            arrivalMinTime = Integer.parseInt(values[0]);
            arrivalMaxTime = Integer.parseInt(values[1]);


            values = scan.nextLine().split(",");
            serviceMinTime = Integer.parseInt(values[0]);
            serviceMaxTime = Integer.parseInt(values[1]);

            clients = new ArrayList<>();
            new RandomClientGenerator(clients, nrClients, arrivalMinTime, arrivalMaxTime, serviceMinTime, serviceMaxTime);

        } catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
        }
    }

    public void setSimulationTime(int simulationTime) {
        this.simulationTime = simulationTime;
    }

    public int getDurationOfSimulation() {
        return this.durationOfSimulation;
    }

    public int getNrQueues() {
        return nrQueues;
    }

    public ArrayList<Client> getClients() {
        return new ArrayList<>(clients);
    }

}
