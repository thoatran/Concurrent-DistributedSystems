import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class w11_grayScale{
    static class Image{
        private int width;
        private int height;
        private double color;

        public Image(int width, int height, double color) {
            this.width = width;
            this.height = height;
            this.color = color;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public double getColor() {
            return color;
        }

    }
    static class GrayScale implements Callable<Vector>{
        private int fromWidth, toWidth;
        private int fromHeight, toHeight;
        private BufferedImage img;

        public GrayScale(BufferedImage img, int fromWidth, int toWidth, int fromHeight,int toHeight) {
            this.fromWidth = fromWidth;
            this.toWidth = toWidth;
            this.fromHeight = fromHeight;
            this.toHeight = toHeight;
            this.img = img;
        }

        public Vector call() {
            Vector<Image> v = new Vector<>();
            WritableRaster raster = img.getRaster();
            for (int i = fromWidth; i < toWidth; i++) {
                for (int j = fromHeight; j < toHeight; j++) {
                    double R = raster.getSample(i, j, 0);
                    double G = raster.getSample(i, j, 1);
                    double B = raster.getSample(i, j, 2);

                    double level = 0.3 * R + 0.59 * G + 0.11 * B;
                    Image tmp = new Image(i, j, level);
                    v.add(tmp);
                }
            }
            return  v;
        }
    }
    public static void main(String [] args) throws Exception {
        System.out.println(Runtime.getRuntime().availableProcessors());
        BufferedImage img = ImageIO.read(new File(args[0]));
        int x = img.getWidth();
        int y = img.getHeight();
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        WritableRaster newRas = newImg.getRaster();
        GrayScale[] tasks = new GrayScale[]{new GrayScale(img, 0, x / 2, 0, y / 2), new GrayScale(img, x / 2 + 1, x, 0, y / 2), new GrayScale(img, 0, x / 2, y / 2 + 1, y), new GrayScale(img, x / 2 + 1, x, y / 2 + 1, y)};
        ExecutorService executor = Executors.newFixedThreadPool(4);

        List<Future<Vector>> results = executor.invokeAll(Arrays.asList(tasks));
        executor.shutdown();
        for (Future<Vector> r : results) {
            Vector v = r.get();
            for (Object o : v) {
                Image tmp = (Image)o;
                newRas.setSample(tmp.getWidth(), tmp.getHeight(),0, tmp.getColor());
                newRas.setSample(tmp.getWidth(), tmp.getHeight(),1, tmp.getColor());
                newRas.setSample(tmp.getWidth(), tmp.getHeight(),2, tmp.getColor());
            }
        }
        ImageIO.write(newImg,"png", new File(args[1]));
    }
}
