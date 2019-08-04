import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class w11_grayScaleFork {
    static class Image{
        private int width, height;
        private double color;

        public Image(int width, int height, double color) {
            this.width = width;
            this.height = height;
            this.color = color;
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return height;
        }

        public double getColor() {
            return color;
        }
    }
    static class GrayScale extends RecursiveAction{

        private BufferedImage img;
        private int fromWidth, toWidth,fromHeight, toHeight;
        private Vector<Image> results;

        public GrayScale(BufferedImage img, int fromWidth, int toWidth, int fromHeight, int toHeight) {
            this.img = img;
            this.fromWidth = fromWidth;
            this.toWidth = toWidth;
            this.fromHeight = fromHeight;
            this.toHeight = toHeight;
            this.results = new Vector<Image>();
        }

        public Vector<Image> Results(){
            return results;
        }

        @Override
        protected void compute() {
            if(fromWidth - toWidth < 1)
                solveSmall();
            else
                solveBig();
        }

        private void solveSmall() {
            WritableRaster raster = img.getRaster();
            for(int i = fromWidth; i < toWidth; i++){
                for(int j = fromHeight; j < toHeight; j++) {
                    double R = raster.getSample(i, j, 0);
                    double G = raster.getSample(i, j, 1);
                    double B = raster.getSample(i, j, 2);
                    double level = 0.3 * R + 0.59 * G + 0.11 * B;
                    Image temp = new Image(i,j,level);
                    results.add(temp);
                }
            }
        }
        private void solveBig() {
            int mid = toWidth / 2;
            GrayScale gs1 = new GrayScale(img, fromWidth, mid, fromHeight, toHeight);
            GrayScale gs2 = new GrayScale(img, mid + 1, toWidth, fromHeight, toHeight);

            invokeAll(gs1, gs2);
            results.addAll(gs1.Results());
            results.addAll(gs2.Results());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedImage img = ImageIO.read(new File(args[0]));
        int x = img.getWidth();
        int y = img.getHeight();
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        WritableRaster newRas = newImg.getRaster();
        GrayScale g = new GrayScale(img, 0, x, 0, y);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(g);

        for (Object o : g.Results()){
            Image tmp = (Image)o;
            newRas.setSample(tmp.getWidth(), tmp.getHeight(),0, tmp.getColor());
            newRas.setSample(tmp.getWidth(), tmp.getHeight(),1, tmp.getColor());
            newRas.setSample(tmp.getWidth(), tmp.getHeight(),2, tmp.getColor());

        }

        ImageIO.write(newImg,"png", new File(args[1]));
    }
}
