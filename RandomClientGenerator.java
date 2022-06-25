import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class RandomClientGenerator{
    private int IDCounter = 0;
    private Random r = new Random();

    public RandomClientGenerator(ArrayList<Client> clients, int N, int minArrival, int maxArrival, int minService, int maxService) {
        for(int i = 0; i<N; i++) {
            clients.add(new Client(generateId(), generateArrivalTime(minArrival, maxArrival), generateServiceTime(minService, maxService)));
        }
        sortAscending(clients);
    }

    public RandomClientGenerator(Client client, int N, int minArrival, int maxArrival, int minService, int maxService) {
        client = new Client(generateId(), generateArrivalTime(minArrival, maxArrival), generateServiceTime(minService, maxService));
    } 

    private void sortAscending(ArrayList<Client> clients) {
        clients.sort(new Comparator<Client>(){
            public int compare(Client o1, Client o2) {
                return o2.compareTo(o1);
            }
        });
    }

    private int generateId() {
        IDCounter++;
        return IDCounter;
    }

    private int generateArrivalTime(int min, int max) {
        return r.nextInt((max - min) + 1) + min;
    }

    private int generateServiceTime(int min, int max) {
        return r.nextInt((max - min) + 1) + min;
    }
}
