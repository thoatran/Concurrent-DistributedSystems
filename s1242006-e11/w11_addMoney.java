import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class w11_addMoney {
    static AtomicInteger account = new AtomicInteger(0);
    static Semaphore S = new Semaphore(0);
    public static class Spouse implements Runnable{
        private int sum;

        public Spouse(int sum) {
            this.sum = sum;
        }

        public void run(){
            for(int i = 0; i < sum; i++){
                System.out.println(account.addAndGet(1));
            }
            S.release();
        }
    }
    public static void main(String [] args) {
        Spouse husband = new Spouse(50000);
        Spouse wife = new Spouse(50000);

        new Thread(husband).start();
        new Thread(wife).start();

        try {
            S.acquire();
            S.acquire();
        } catch (Exception e){
            System.out.println(e);
        }

        System.out.println("Total: " + account.get());

    }
}
