import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import mpi.MPI;
public class w06_countWords {
	public static void main(String args[]) 
                              throws Exception {
		MPI.Init(args);
		int ID = MPI.COMM_WORLD.Rank(); // proc ID
		
		Path txt_file = Paths.get("D:\\Documents\\Distributed computing\\Ex6\\file_"+Integer.toString(ID)+".txt");
		List<String> line;
		line = Files.readAllLines(txt_file);
		line = Arrays.asList(line.get(0).split("\\s* \\s*"));
		
		// Get number of word
		int wordCount = line.size();
		
		// Get maximum word length
		int maxLength = 0;
		for(int i = 0; i < line.size(); i++) {
			if(line.get(i).length() > maxLength) {
				maxLength = line.get(i).length();
			}
		}
		
		int sumWordCount[] = new int[1];
		int maxWordLength[] = new int[1];
		MPI.COMM_WORLD.Reduce(new int[] {wordCount}, 0, sumWordCount, 0, 1, MPI.INT, MPI.SUM, 0);
		MPI.COMM_WORLD.Reduce(new int[] {maxLength}, 0, maxWordLength, 0, 1, MPI.INT, MPI.MAX, 0);
		
		if(ID == 0) {
			System.out.println(sumWordCount[0]);
			System.out.println(maxWordLength[0]);
		}		
		
		MPI.Finalize();
	}
}
