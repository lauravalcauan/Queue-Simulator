
public class Client {
    private int ID;
    private int arrivalTime;
    private int serviceTime;

    public Client(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getID() {
        return ID;
    }


    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int compareTo(Client c) {
        if (this.arrivalTime < c.arrivalTime) {
            return 1;
        } else if(this.arrivalTime>c.arrivalTime) {
            return -1;
        }
        return 0;
    }

    public String toString() {
        System.out.println("Client: " + ID + ", " + arrivalTime + ", " + serviceTime);
        return "Client: " + ID + ", " + arrivalTime + ", " + serviceTime;
    }
}
