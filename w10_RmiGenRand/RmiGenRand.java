import java.rmi.*;
import java.rmi.server.*;

public class RmiGenRand extends UnicastRemoteObject implements RmiGenRandInterface
{
	private java.util.Random r = new java.util.Random();
	
	public RmiGenRand() throws RemoteException
	{
	}
	
	public int GenRand() throws RemoteException
	{
		return r.nextInt();
	}
}