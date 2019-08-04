import java.rmi.*;

public interface RmiGenRandInterface extends Remote
{
	public int GenRand() throws RemoteException;
}
