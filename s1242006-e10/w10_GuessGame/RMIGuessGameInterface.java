import java.rmi.*;

public interface RMIGuessGameInterface extends Remote{
    public char[] theFirstReturnStr() throws Exception;

    public char[] createReturnString(char charguess) throws Exception;

}
