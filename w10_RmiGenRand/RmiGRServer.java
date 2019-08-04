import java.rmi.*;

class RmiGRServer
{
	public static void main (String[] args) 
	{
		try 
		{
			Naming.rebind("RndService", new RmiGenRand());
			System.out.println("Server is ready");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
}