import java.util.*;
class Primes extends Thread {
    int n;
    int thread;
    int low;
    int high;
    static int count = 0;
    public Primes(int threadID, int low, int high) {
        this.thread = threadID;
        this.low = low;
        this.high = high;
    }
    static boolean isPrime(long n)
    {
        if (n <= 1) return false;
        double limit = Math.sqrt(n);
        for (long i = 2; i <= limit; i++)
        {
            if (n % i == 0) return false;
        }
        // System.out.println("a");
        return true;
    }
    public void run() {

        for(int i = low; i < high; i++) {
            if(Primes.isPrime(i) == true) {
                System.out.println(i);
                count ++;
            }
        }
    }
    public static void main(String[] arg)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input N: ");
        int n = sc.nextInt();
        System.out.println("Input the number of processes: ");
        int p = sc.nextInt();
        Thread threads[] = new Thread[p];
        for(int i = 0; i < p; i++) {
            if(i == (p-1)) {
                threads[i] = new Primes(i,i*n/p, n);
            }
            threads[i] = new Primes(i, i*n/p, (i+1)*n/p);
            threads[i].start();
        }
        for(int i = 0; i < p; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        System.out.println("The number of primes < " + n + " is " + count);
    }
}