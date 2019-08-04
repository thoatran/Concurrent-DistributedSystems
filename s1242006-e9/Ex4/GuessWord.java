import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Vector;

public class GuessWord {
    static final int port = 10000;


    public static void Client(String server) throws Exception{
        Socket c = new Socket (server, port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(c.getOutputStream()));
        String guessword = new String();
        BufferedReader ipuser = new BufferedReader(new InputStreamReader(System.in));
        String rec = new String();
        for(;;){
            rec = reader.readLine();
            if(rec.equals("0")){
                rec = reader.readLine();
                System.out.println("server>"+rec);
                break;
            }
            System.out.println("Server>"+rec);
            System.out.print("Client>");
            guessword=ipuser.readLine();
            writer.write(guessword, 0, guessword.length());
            writer.newLine();
            writer.flush();
        }
    }


    public static void Server() throws Exception{
        ServerSocket s = new ServerSocket(port);
        for(;;) {
            Socket c = s.accept();
            System.out.println("START");

            Vector<String> allWords = new Vector<String>();

            BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(c.getOutputStream()));
            BufferedReader freader = new BufferedReader(new FileReader("w09_textFile.txt")); //path to the text source file
            String line = freader.readLine();
            while (line != null) {
                allWords.add(line);
                line = freader.readLine();
            }


            Random rand = new Random();
            int index = rand.nextInt(allWords.size());
            //System.out.println(index);
            char[] word = allWords.get(index).toCharArray();
            char[] guess = new char[word.length];
            java.util.Arrays.fill(guess, '*');
            int remaining = word.length;
            char cguess;
            for (;;) {
                writer.write(guess, 0, guess.length);
                writer.newLine();
                writer.flush();
                cguess = reader.readLine().charAt(0);
                for (int i = 0; i < word.length; i++) {
                    if (cguess == word[i] && guess[i] == '*') {
                        guess[i] = cguess;
                        remaining--;
                    }
                }
                //////////////////////////////
                if (remaining == 0) {
                    System.out.println("END!");
                    writer.write(new String("0"), 0, 1);
                    writer.newLine();
                    writer.flush();
                    writer.write(guess, 0, guess.length);
                    writer.newLine();
                    writer.flush();
                    break;
                }
            }
        }
    }

    public static void main(String args []) throws Exception {

        try
        {
            if(args[0].equals("client"))
                Client(args[1]);
            else
                Server();
        }
        catch(Exception e)
        {
            System.out.println("Connection error!");
        }
    }
}
