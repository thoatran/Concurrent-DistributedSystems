import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import jomp.runtime.OMP;


public class w12_grayScale {

    static BufferedImage img;
    static WritableRaster raster;
    static BufferedImage newImg;
    static WritableRaster newRas;

    static class GrayScale {

        private int fromWidth, toWidth, fromHeight, toHeight;
        private BufferedImage img;
        WritableRaster raster;
        public GrayScale(BufferedImage img, int fromWidth, int toWidth, int fromHeight,int toHeight) {
            this.fromWidth = fromWidth;
            this.toWidth = toWidth;
            this.fromHeight = fromHeight;
            this.toHeight = toHeight;
            this.img = img;
            this.raster = img.getRaster();
        }

        public void solveSmall(int width){

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.width = width;
  __omp_Object0.toHeight = toHeight;
  __omp_Object0.fromHeight = fromHeight;
  __omp_Object0.toWidth = toWidth;
  __omp_Object0.fromWidth = fromWidth;
  __omp_Object0.img = img;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object0);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  // shared variables
  width = __omp_Object0.width;
  toHeight = __omp_Object0.toHeight;
  fromHeight = __omp_Object0.fromHeight;
  toWidth = __omp_Object0.toWidth;
  fromWidth = __omp_Object0.fromWidth;
  img = __omp_Object0.img;
}
// OMP PARALLEL BLOCK ENDS

        }

        public void solveBig(int from, int to) {
            int mid = (to + from) / 2;
            if(to - from == 1){
                solveSmall(from);
                return;
            }
            {
                solveBig(from, mid);
                solveBig(mid , to);
            }

        }

        public void compute(){
            solveBig(fromWidth, toWidth);
        }

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private class __omp_Class0 extends jomp.runtime.BusyTask {
  // shared variables
  int width;
  int toHeight;
  int fromHeight;
  int toWidth;
  int fromWidth;
  BufferedImage img;
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
                        __omp_WholeData2.start = (long)( fromHeight);
                        __omp_WholeData2.stop = (long)( toHeight);
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
                double R = raster.getSample(width, i, 0);
                double G = raster.getSample(width, i, 1);
                double B = raster.getSample(width, i, 2);

                double level = 0.3 * R + 0.59 * G + 0.11 * B;
                newRas.setSample(width, i , 0, level);
                newRas.setSample(width, i, 1, level);
                newRas.setSample(width, i, 2, level);
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



    public static void main(String [] args) throws IOException {
        img = ImageIO.read(new File(args[0]));
        int x = img.getWidth();
        int y = img.getHeight();
        newImg = new BufferedImage(x, y, img.getType());
        newRas = newImg.getRaster();
        GrayScale gs = new GrayScale(img, 0, x, 0, y);
        gs.compute();
        ImageIO.write(newImg, "png", new File(args[1]));
    }}

