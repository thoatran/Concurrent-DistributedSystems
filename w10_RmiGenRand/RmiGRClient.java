import java.rmi.*;

class RmiGRClient
{
	public static void main (String[] args) 
	{
		try 
		{
			RmiGenRandInterface rnd = (RmiGenRandInterface)Naming.lookup("//127.0.0.1/RndService");
			System.out.println(rnd.GenRand());
			System.out.println(rnd.GenRand());
			System.out.println(rnd.GenRand());
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
}