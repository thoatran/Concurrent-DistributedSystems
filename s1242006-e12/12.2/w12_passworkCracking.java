import jomp.runtime.OMP;

public class w12_passworkCracking {

    static String pwd;
    static String set = "0123456789abcdefjhjklmnopqrsrtuvwxyz";
    static boolean crackingPassword (String pwd) {
        boolean check = false;
        check = w02_fileCrypto.Decrypt("w02_mobydick.encrypted", "password.txt", pwd);
        return check;
    }

    public static void main(String [] args) {
        int lengthOfSet = set.length();
        for(int i = 0; i < lengthOfSet; i++) {
            for(int j = 0; j < lengthOfSet; j++) {
                for(int m = 0; m < lengthOfSet; m++) {

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.m = m;
  __omp_Object0.j = j;
  __omp_Object0.i = i;
  __omp_Object0.lengthOfSet = lengthOfSet;
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
  m = __omp_Object0.m;
  j = __omp_Object0.j;
  i = __omp_Object0.i;
  lengthOfSet = __omp_Object0.lengthOfSet;
  args = __omp_Object0.args;
}
// OMP PARALLEL BLOCK ENDS

                }
            }
        }
    }

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private static class __omp_Class0 extends jomp.runtime.BusyTask {
  // shared variables
  int m;
  int j;
  int i;
  int lengthOfSet;
  String [ ] args;
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
                                __omp_WholeData2.start = (long)( 0);
                                __omp_WholeData2.stop = (long)( lengthOfSet);
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
                                  for(int n = (int)__omp_ChunkData1.start; n < __omp_ChunkData1.stop; n += __omp_ChunkData1.step) {
                                    // OMP USER CODE BEGINS
 {
                        pwd = Character.toString(set.charAt(i)) + Character.toString(set.charAt(j)) + Character.toString(set.charAt(m)) + Character.toString(set.charAt(n));
                        //System.out.println(pwd);
                        if(crackingPassword(pwd) == true) {
                            System.out.println("Needing password: " + pwd);
                            System.exit(0);
                        }
                    }
                                    // OMP USER CODE ENDS
                                    if (n == (__omp_WholeData2.stop-1)) amLast = true;
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

