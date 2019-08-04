import java.util.*;
import java.util.concurrent.Semaphore;


class w03_sort2Proc
{
    static final int N = 100000;
    static int A[] = new int[N];
    static Semaphore S = new Semaphore(0);

    static class SortThread implements Runnable
    {
        int Left, Right;

        public SortThread(int left, int right)
        {
            Left = left; Right = right;
        }

        public void run()
        {
            Arrays.sort(A, Left, Right);
            S.release();
        }
    }

    static public void main(String args[])
    {
        Random rand = new Random();
        for(int i = 0; i < N; ++i)
            A[i] = rand.nextInt(1000);

        new Thread(new SortThread(0, N / 2)).start();
        new Thread(new SortThread(N / 2, N)).start();

        try
        {
            S.acquire();
            S.acquire();
        }
        catch(Exception e){}

        // here add merging code
        int B[] = new int[N/2];
        int C[] = new int [N-N/2];
        for(int i = 0; i < N/2; i++){
            B[i] = A[i];
            C[i] = A[i + N/2];
        }
        int i=0, j = 0;
        int index = 0;
        // System.out.println(N);
        while(i < B.length && j < C.length){
            if(B[i] < C[j]){
                A[index] = B[i];
                index ++;
                i++;
            } else if(B[i] > C[j]){
                A[index] = C[j];
                index ++;
                j++;
            } else {
                A[index] = B[i];
                index ++; i ++;
                A[index] = C[j];
                index ++; j++;
            }
        }
        //System.out.println(i);
        //System.out.println(j);
        if(i < B.length){
            while(i < B.length){
                A[index] = B[i];
                index++; i++;
            }
        } else if( j < C.length){
            while(j < C.length) {
                A[index] = C[j];
                index++; j++;
            }
        } else {
            System.out.println();
        }
        for (int k = 0; k < N; k++)
            System.out.println(A[k]);
    }
}
