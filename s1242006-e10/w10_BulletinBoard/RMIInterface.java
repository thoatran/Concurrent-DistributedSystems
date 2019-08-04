import java.rmi.*;

public interface RMIInterface extends Remote {

    public void AddMessage(String mes) throws Exception;

    public String ShowMessages() throws Exception;

}
