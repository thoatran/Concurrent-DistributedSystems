import jomp.runtime.OMP;
import java.util.Arrays;
import java.util.Random;

public class w12_sorting {

    static int N = 20;
    static int A[] = new int[N];

    static void Sort(int A[], int from, int to){
        Arrays.sort(A, from, to);
    }

    static void Merge(int A[], int from1, int to1, int from2, int to2) {
        int B[] = new int[to1 - from1];
        int C[] = new int[to2 - from2];
        for (int i = from1; i < to1; i++) {
            B[i - from1] = A[i];
        }
        for (int i = from2; i < to2; i++) {
            C[i - from2] = A[i];
        }

        int i = 0, j = 0;
        int index = from1;
        while (i < B.length && j < C.length) {
            if (B[i] < C[j]) {
                A[index] = B[i];
                index ++;
                i++;
            } else if(B[i] > C[j]) {
                A[index] = C[j];
                index ++;
                j++;
            } else {
                A[index] = B[i];
                index++; i++;
                A[index] = C[j];
                index ++; j++;
            }
        }
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
    }

    public static void main(String [] args) {
        Random ran = new Random();
        System.out.println("The array before sorting:");
        for(int i = 0; i < N; i++) {
            A[i] = ran.nextInt(100);
            System.out.print(A[i]);
	    System.out.print(" ; ");
        }
        System.out.println();

        OMP.setNumThreads(4);

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.ran = ran;
  __omp_Object0.args = args;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object0);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  // shared variables
  ran = __omp_Object0.ran;
  args = __omp_Object0.args;
}
// OMP PARALLEL BLOCK ENDS



        OMP.setNumThreads(2);

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class2 __omp_Object2 = new __omp_Class2();
  // shared variables
  __omp_Object2.ran = ran;
  __omp_Object2.args = args;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object2);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  // shared variables
  ran = __omp_Object2.ran;
  args = __omp_Object2.args;
}
// OMP PARALLEL BLOCK ENDS

        Merge(A, 0, N / 2, N / 2, N);
        System.out.println("The array after sorting:");
        for(int i = 0; i < N; i++) {
            System.out.print(A[i]);
	    System.out.print(" ; ");
        }
	System.out.println();
    }

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private static class __omp_Class2 extends jomp.runtime.BusyTask {
  // shared variables
  Random ran;
  String [ ] args;
  // firstprivate variables
  // variables to hold results of reduction

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  // reduction variables, init to default
    // OMP USER CODE BEGINS

                  { // OMP SECTIONS BLOCK BEGINS
                  // copy of firstprivate variables, initialized
                  // copy of lastprivate variables
                  // variables to hold result of reduction
                  boolean amLast=false;
                  {
                    // firstprivate variables + init
                    // [last]private variables
                    // reduction variables + init to default
                    // -------------------------------------
                    __ompName_3: while(true) {
                    switch((int)jomp.runtime.OMP.getTicket(__omp_me)) {
                    // OMP SECTION BLOCK 0 BEGINS
                      case 0: {
                    // OMP USER CODE BEGINS

            {
                Merge(A, 0, N / 4, N / 4, N / 2);
            }
                    // OMP USER CODE ENDS
                      };  break;
                    // OMP SECTION BLOCK 0 ENDS
                    // OMP SECTION BLOCK 1 BEGINS
                      case 1: {
                    // OMP USER CODE BEGINS

            {
                Merge(A, N / 2 , 3 * N / 4, 3 * N / 4, N);
            }
                    // OMP USER CODE ENDS
                    amLast = true;
                      };  break;
                    // OMP SECTION BLOCK 1 ENDS

                      default: break __ompName_3;
                    } // of switch
                    } // of while
                    // call reducer
                    jomp.runtime.OMP.resetTicket(__omp_me);
                    jomp.runtime.OMP.doBarrier(__omp_me);
                    // copy lastprivate variables out
                    if (amLast) {
                    }
                  }
                  // update lastprivate variables
                  if (amLast) {
                  }
                  // update reduction variables
                  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                  }
                  } // OMP SECTIONS BLOCK ENDS

    // OMP USER CODE ENDS
  // call reducer
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS



// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private static class __omp_Class0 extends jomp.runtime.BusyTask {
  // shared variables
  Random ran;
  String [ ] args;
  // firstprivate variables
  // variables to hold results of reduction

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  // reduction variables, init to default
    // OMP USER CODE BEGINS

                  { // OMP SECTIONS BLOCK BEGINS
                  // copy of firstprivate variables, initialized
                  // copy of lastprivate variables
                  // variables to hold result of reduction
                  boolean amLast=false;
                  {
                    // firstprivate variables + init
                    // [last]private variables
                    // reduction variables + init to default
                    // -------------------------------------
                    __ompName_1: while(true) {
                    switch((int)jomp.runtime.OMP.getTicket(__omp_me)) {
                    // OMP SECTION BLOCK 0 BEGINS
                      case 0: {
                    // OMP USER CODE BEGINS

            {
                Sort(A, 0, N/4);
            }
                    // OMP USER CODE ENDS
                      };  break;
                    // OMP SECTION BLOCK 0 ENDS
                    // OMP SECTION BLOCK 1 BEGINS
                      case 1: {
                    // OMP USER CODE BEGINS

            {
                Sort(A, N/4, N/2);
            }
                    // OMP USER CODE ENDS
                      };  break;
                    // OMP SECTION BLOCK 1 ENDS
                    // OMP SECTION BLOCK 2 BEGINS
                      case 2: {
                    // OMP USER CODE BEGINS

            {
                Sort(A, N/2, 3 * N / 4);
            }
                    // OMP USER CODE ENDS
                      };  break;
                    // OMP SECTION BLOCK 2 ENDS
                    // OMP SECTION BLOCK 3 BEGINS
                      case 3: {
                    // OMP USER CODE BEGINS

            {
                Sort(A, 3 * N / 4, N);
            }
                    // OMP USER CODE ENDS
                    amLast = true;
                      };  break;
                    // OMP SECTION BLOCK 3 ENDS

                      default: break __ompName_1;
                    } // of switch
                    } // of while
                    // call reducer
                    jomp.runtime.OMP.resetTicket(__omp_me);
                    jomp.runtime.OMP.doBarrier(__omp_me);
                    // copy lastprivate variables out
                    if (amLast) {
                    }
                  }
                  // update lastprivate variables
                  if (amLast) {
                  }
                  // update reduction variables
                  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                  }
                  } // OMP SECTIONS BLOCK ENDS

    // OMP USER CODE ENDS
  // call reducer
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS

}


