import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Naming;
import java.util.Random;
import java.util.Vector;

public class RMIServer {

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("w10_words.txt"));
        String line = reader.readLine();
        Vector<String> allWords = new Vector<String>();
        while(line != null) {
            allWords.add(line);
            line = reader.readLine();
        }
        Random rand = new Random();
        int index = rand.nextInt(allWords.size());
        String word = allWords.get(index);
        //System.out.println(word);

        System.out.println("START GAME");

        Naming.rebind("Game", new RMIGuessGame(word));
    }
}
