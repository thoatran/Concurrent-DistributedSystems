import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIGuessGame extends UnicastRemoteObject implements RMIGuessGameInterface {
    private String word;
    private char[] wordArr;
    private char[] guess;
    private int remain;

    protected RMIGuessGame(String word) throws RemoteException {
        this.word = word;
        this.wordArr = word.toCharArray();
        this.guess = new char[wordArr.length];
        java.util.Arrays.fill(guess, '*');
        this.remain = wordArr.length;
    }

    public char[] theFirstReturnStr() throws Exception{
        return guess;
    }

    public char[] createReturnString(char charguess) throws Exception {
        //System.out.println(guess);
        //System.out.println(remain);
        for(int i = 0; i < wordArr.length; i++) {
            if(charguess == wordArr[i] && guess[i] == '*') {
                guess[i] = charguess;
                remain --;
            }
        }

        if(remain == 0){
            System.out.println("END GAME!!!");
        }
        //System.out.println(guess);
        return guess;
    }

}
