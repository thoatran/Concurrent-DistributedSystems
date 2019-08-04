import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class HeadTail {

    static Semaphore S = new Semaphore(0);

    static int numThreads = 5;
    static boolean[] flips = new boolean[numThreads];
    static int count = numThreads;


    private static int remaining() {
        int n = 0;
        for (int i = 0; i < numThreads; i++) {
            if (flips[i]) {
                n++;
            }
        }
        return n;
    }

    static public class FlippingCoin implements Runnable {
        private int id;
        private boolean check;

        public FlippingCoin(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void run() {
            Random rand = new Random();
            check = rand.nextBoolean();
            flips[id] = check;
            if (check)
                System.out.print("\t" + (this.getId()+1) + "\t");

            S.release();
        }
    }


    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        //System.out.println("Input the number of processes: ");
        //P = sc.nextInt();
        System.out.println("\n______________HEAD TAIL GAME___________");
        int current = 1;
        for (int i = 0; i < numThreads; i++) {
            flips[i] = true;
        }

        while (count > 1) {
            System.out.print("Round " + current + " stops. The players have HEAD side: ");
            for (int i = 0; i < numThreads; i++) {
                //System.out.println(i);
                if (flips[i]) {
                    new Thread(new FlippingCoin(i)).start();
                }
            }

            //System.out.println("1");

            for (int j = 0; j < count; j++) {
                try {
                    S.acquire();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            current++;
            count = remaining();
            //System.out.println(count);
            System.out.println("\n_____________________________________");
        }
    }
}