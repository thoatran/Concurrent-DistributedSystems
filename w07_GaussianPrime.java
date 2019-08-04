import java.util.ArrayList;

import mpi.MPI;

public class w07_GaussianPrime {
	public static int P; // number of client
	public static int M; // ending point of searching space
	public static int N; // length of a subinterval
	
	private static boolean isGausianPrime(int n) {
		if (n <= 1) {
			return false;
		}
		
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if(n%i == 0) return false;
		}
		
		return((n-3)%4 == 0);
	}
	
	private static ArrayList<Integer> findGaussianPrime(int L, int R){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = L; i < R; i++) {
			if (isGausianPrime(i)) {
				list.add(i);
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		
		MPI.Init(args);
		P = MPI.COMM_WORLD.Size()-1;
		M = Integer.parseInt(args[3]);
		N = Integer.parseInt(args[4]);
		
		int ID = MPI.COMM_WORLD.Rank(); // proc ID
		
		if(ID == 0) { // Server				
			
			int sP = 0;
			int Left = 2;
			
			while(sP < P) {
				// Receive client ID
				int clientID[] = new int[1];
				MPI.COMM_WORLD.Recv(clientID, 0, 1, MPI.INT, MPI.ANY_SOURCE, 1);
				
				// Receive number of prime numbers
				int numPrime[] = new int[1];
				MPI.COMM_WORLD.Recv(numPrime, 0, 1, MPI.INT, clientID[0], 2);
				
				// Receive prime numbers				
				if (numPrime[0] > 0) {
					int primeList[] = new int[numPrime[0]];
					MPI.COMM_WORLD.Recv(primeList, 0, numPrime[0], MPI.INT, clientID[0], 3);
					
					// Print prime numbers
					for (int i = 0; i < numPrime[0]; i++) {
						if(primeList[i] != 0)
							System.out.print(primeList[i]+"   ");
					}
					System.out.println();
				}
								
				if (Left == M) {
					MPI.COMM_WORLD.Send(new int[] {0}, 0, 1, MPI.INT, clientID[0], 4);
					sP++;
				} else {
					MPI.COMM_WORLD.Send(new int[] {Left}, 0, 1, MPI.INT, clientID[0], 4);
				}
				
				Left = Math.min(M, Left+N);
				
			}
			
		} else { // Worker processes
			int clientId[] = new int[] {ID};
			int primeList[] = new int[1];			
			int Left[] = new int[] {-1};
			
			while(Left[0] != 0) {				
				// Send client id to server
				MPI.COMM_WORLD.Send(clientId, 0, 1, MPI.INT, 0, 1);
				
				// Send number of prime numbers
				MPI.COMM_WORLD.Send(new int[] {primeList.length}, 0, 1, MPI.INT, 0, 2);
				
				// Send prime numbers
				if (primeList.length>0) {
					MPI.COMM_WORLD.Send(primeList, 0, primeList.length, MPI.INT, 0, 3);
				}
				
				// Receive Left from server
				MPI.COMM_WORLD.Recv(Left, 0, 1, MPI.INT, 0, 4);
				
				int Right = Math.min(M, Left[0]+N);
				ArrayList<Integer> list = new ArrayList<>();
				list = findGaussianPrime(Left[0], Right);
				
				primeList = new int[list.size()];
				for(int i = 0; i < list.size(); i++) {
					primeList[i] = list.get(i);
				}
			}
		}
		
		MPI.Finalize();
	}
}