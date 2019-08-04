import java.io.*;
import java.net.*;
import java.util.Vector;
import java.util.Random;

public class wordGuessGame {
    static final int port = 1912;
    public static void Server() throws Exception{
        ServerSocket s = new ServerSocket(port);
        for(;;) {
            Socket c = s.accept();
            System.out.println("Start game!");
            BufferedReader sreader = new BufferedReader(new InputStreamReader(c.getInputStream()));
            BufferedWriter swriter = new BufferedWriter(new OutputStreamWriter(c.getOutputStream()));
            Vector<String> allWords = new Vector<String>();
            BufferedReader freader = new BufferedReader(new FileReader("w09_words.txt")); //path to the text source file
            String line = freader.readLine();
            while (line != null) {
                allWords.add(line);
                line = freader.readLine();
            }
            Random rand = new Random();
            int index = rand.nextInt(allWords.size());
            char[] word = allWords.get(index).toCharArray();
            char[] guess = new char[word.length];
            java.util.Arrays.fill(guess, '*');
            int remain = word.length;
            char charguess;
            for (; ; ) {
                swriter.write(guess, 0, guess.length);
                swriter.newLine();
                swriter.flush();
                charguess = sreader.readLine().charAt(0);
                for (int i = 0; i < word.length; i++) {
                    if (charguess == word[i] && guess[i] == '*') {
                        guess[i] = charguess;
                        remain--;
                    }
                }
                if (remain == 0) {
                    System.out.println("End game!");
                    swriter.write(new String("0"), 0, 1);
                    swriter.newLine();
                    swriter.flush();
                    swriter.write(guess, 0, guess.length);
                    swriter.newLine();
                    swriter.flush();
                    break;
                }
            }
        }
    }

    public static void Client(String server) throws Exception{
        Socket c = new Socket (server, port);
        BufferedReader creader = new BufferedReader(new InputStreamReader(c.getInputStream()));
        BufferedWriter cwriter = new BufferedWriter(new OutputStreamWriter(c.getOutputStream()));
        String guessword = new String();
        BufferedReader ipuser = new BufferedReader(new InputStreamReader(System.in));
        String recWord = new String();
        for(;;){
            recWord = creader.readLine();
            if(recWord.equals("0")){
                recWord = creader.readLine();
                System.out.println("server>"+recWord);
                break;
            }
            System.out.println("server>"+recWord);
            System.out.print("client>");
            guessword=ipuser.readLine();
            cwriter.write(guessword, 0, guessword.length());
            cwriter.newLine();
            cwriter.flush();
        }
    }
    public static void main(String args[]) throws Exception{

        try{
            if (args[0].equals("client")) {
                Client(args[1]);
            }
            else{
                Server();
            }
        }
        catch(Exception e)
        {
            System.out.println("Connection error!");
        }
    }
}