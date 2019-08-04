import java.util.*;
import java.util.concurrent.Semaphore;
public class FindMaximum {
    static final int N = 10;
    static int A[] = new int[N];
    static Semaphore S = new Semaphore(0);

    static class Maximum implements Runnable
    {
        int Left, Right;
        int max = A[Left];

        public Maximum(int left, int right)
        {
            Left = left; Right = right;
        }

        public void run()
        {
            Arrays.sort(A, Left, Right);
            max = A[Right - 1];
            S.release();
            System.out.println(max);
        }
    }

    static public void main(String args[])
    {
        Random rand = new Random();
        for(int i = 0; i < N; ++i) {
            A[i] = rand.nextInt(1000);
            // System.out.println(A[i]);
        }
        Thread t1 = new Thread( new Maximum(0,N/4));
        t1.start();
        Thread t2 = new Thread( new Maximum(N/4,N/2));
        t2.start();
        Thread t3 = new Thread( new Maximum(N/2,3 * N/4));
        t3.start();
        Thread t4 = new Thread( new Maximum(3*N/4, N));
        t4.start();

        try
        {
            S.acquire();
            S.acquire();
            S.acquire();
            S.acquire();
        }
        catch(Exception e){}

        int B[] = new int[4];
        B[0] = A[N/4 - 1];
        B[1] = A[N/2 - 1];
        B[2] = A[3*N/4 - 1];
        B[3] = A[N - 1];
        int maximum = B[0];
        for(int i = 0; i < 4; i++) {
            if(maximum < B[i]){
                maximum = B[i];
            }
        }
        System.out.println("The largest number in the array is: " + maximum );

    }
}
