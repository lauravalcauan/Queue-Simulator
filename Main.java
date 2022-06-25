public class Main {
    public static void main(String[] args){
        System.out.println(args[0]);
        System.out.println(args[1]);

        QueuesHandler q = new QueuesHandler(args[0], args[1]);
        q.startProgram();
    }
}