import jomp.runtime.OMP;
import java.util.Arrays;

public class w12_findPrime {

    static int A;
    static int B;
    static int[] arr;

    static boolean isPrime(int n) {
        if(n <= 1) return false;
        double limit = Math.sqrt(n);
        for(int i = 2; i <= limit; i++) {
            if(n % i == 0)
                return false;
        }
        return true;
    }
    static void findPrime(int from, int to){

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.to = to;
  __omp_Object0.from = from;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object0);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  // shared variables
  to = __omp_Object0.to;
  from = __omp_Object0.from;
}
// OMP PARALLEL BLOCK ENDS

    }

    public static void main(String [] args) {
        A = Integer.parseInt(args[0]);
        B = Integer.parseInt(args[1]);
        arr = new int[B - A + 1];
        for(int i = 0; i < arr.length; i++){
            arr[i] = A + i;
        }
        int mid = arr.length / 2;

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class4 __omp_Object4 = new __omp_Class4();
  // shared variables
  __omp_Object4.mid = mid;
  __omp_Object4.args = args;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object4);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  // shared variables
  mid = __omp_Object4.mid;
  args = __omp_Object4.args;
}
// OMP PARALLEL BLOCK ENDS

        Arrays.sort(arr, 0, arr.length);
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != 0){
                System.out.println(arr[i]);
            }
        }
    }

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private static class __omp_Class4 extends jomp.runtime.BusyTask {
  // shared variables
  int mid;
  String [ ] args;
  // firstprivate variables
  // variables to hold results of reduction

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  // reduction variables, init to default
    // OMP USER CODE BEGINS

        {
            findPrime(0, mid);
            findPrime(mid, arr.length);
        }
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
  int to;
  int from;
  // firstprivate variables
  // variables to hold results of reduction

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  // reduction variables, init to default
    // OMP USER CODE BEGINS

                  { // OMP FOR BLOCK BEGINS
                  // copy of firstprivate variables, initialized
                  // copy of lastprivate variables
                  // variables to hold result of reduction
                  boolean amLast=false;
                  {
                    // firstprivate variables + init
                    // [last]private variables
                    // reduction variables + init to default
                    // -------------------------------------
                    jomp.runtime.LoopData __omp_WholeData2 = new jomp.runtime.LoopData();
                    jomp.runtime.LoopData __omp_ChunkData1 = new jomp.runtime.LoopData();
                    __omp_WholeData2.start = (long)( from);
                    __omp_WholeData2.stop = (long)( to);
                    __omp_WholeData2.step = (long)(1);
                    jomp.runtime.OMP.setChunkStatic(__omp_WholeData2);
                    while(!__omp_ChunkData1.isLast && jomp.runtime.OMP.getLoopStatic(__omp_me, __omp_WholeData2, __omp_ChunkData1)) {
                    for(;;) {
                      if(__omp_WholeData2.step > 0) {
                         if(__omp_ChunkData1.stop > __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                         if(__omp_ChunkData1.start >= __omp_WholeData2.stop) break;
                      } else {
                         if(__omp_ChunkData1.stop < __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                         if(__omp_ChunkData1.start > __omp_WholeData2.stop) break;
                      }
                      for(int i = (int)__omp_ChunkData1.start; i < __omp_ChunkData1.stop; i += __omp_ChunkData1.step) {
                        // OMP USER CODE BEGINS
 {
            if(!isPrime(arr[i]))
                arr[i] = 0;
        }
                        // OMP USER CODE ENDS
                        if (i == (__omp_WholeData2.stop-1)) amLast = true;
                      } // of for 
                      if(__omp_ChunkData1.startStep == 0)
                        break;
                      __omp_ChunkData1.start += __omp_ChunkData1.startStep;
                      __omp_ChunkData1.stop += __omp_ChunkData1.startStep;
                    } // of for(;;)
                    } // of while
                    // call reducer
                    jomp.runtime.OMP.doBarrier(__omp_me);
                    // copy lastprivate variables out
                    if (amLast) {
                    }
                  }
                  // set global from lastprivate variables
                  if (amLast) {
                  }
                  // set global from reduction variables
                  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                  }
                  } // OMP FOR BLOCK ENDS

    // OMP USER CODE ENDS
  // call reducer
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS

}


