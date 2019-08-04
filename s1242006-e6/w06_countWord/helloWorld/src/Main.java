
import mpi.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        MPI.Init(args);
        int ID = MPI.COMM_WORLD.Rank();

        List<String> line;
        Path txt_file = Paths.get("/home/thoa/IdeaProjects/helloWorld/src/file_"+Integer.toString(ID)+".txt");

        line = Files.readAllLines(txt_file);
        line = Arrays.asList(line.get(0).split("\\s* \\s*"));

        int wordCount = line.size();

        // Get maximum word length
        int maxLength = 0;
        for(int i = 0; i < line.size(); i++) {
            if(line.get(i).length() > maxLength) {
                maxLength = line.get(i).length();
            }
        }

        int countWord[] = new int[1];
        int lengthMax[] = new int[1];


        MPI.COMM_WORLD.Reduce(new int[] {wordCount}, 0, countWord, 0, 1, MPI.INT, MPI.SUM, 0);
        MPI.COMM_WORLD.Reduce(new int[] {maxLength}, 0, lengthMax, 0, 1, MPI.INT, MPI.MAX, 0);

        if(ID == 0) {
            System.out.println("The number of words: " + countWord[0]);
            System.out.println("The maximum length: " + lengthMax[0]);
        }

        MPI.Finalize();
    }
}