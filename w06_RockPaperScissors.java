import java.util.Random;

import mpi.MPI;
public class w06_RockPaperScissors {
	private static final int ROCK = 0;
	private static final int PAPER = 1;
	private static final int SCISSORS = 2;
	
	private static String getResultString(int num) {
		if (num == ROCK) {
			return "Rock";
		} else if(num == SCISSORS) {
			return "Scissors";
		} else {
			return "Paper";
		}
	}
	
	private static int[] scores = new int[] {0,0};
	
	public static void main(String args[]) 
                              throws Exception {
		MPI.Init(args);
		int ID = MPI.COMM_WORLD.Rank(); // proc ID
		
		Random random = new Random();
		
		while(scores[0] < 3 && scores[1] < 3) {
			int randomNum = random.nextInt(3);
			
			if (ID == 1) {
				MPI.COMM_WORLD.Send(new int[] {randomNum}, 0, 1, MPI.INT, 0, 0);
			} else {
				int opponent[] = new int[1];
				MPI.COMM_WORLD.Recv(opponent, 0, 1, MPI.INT, MPI.ANY_SOURCE, MPI.ANY_TAG);
				
				System.out.println("P1: " + getResultString(randomNum));
				System.out.println("P2: " + getResultString(opponent[0]));
				
				if(randomNum == opponent[0]) { 
					System.out.println("Draw!"); 
				}
				else if(randomNum == PAPER && opponent[0] == ROCK) {
					System.out.println("P1 wins!");
					scores[0]++;
				}
				else if(randomNum == SCISSORS && opponent[0] == PAPER) {
					System.out.println("P1 wins!");
					scores[0]++;
				}
				else if(randomNum == ROCK && opponent[0] == SCISSORS) {
					System.out.println("P1 wins!");
					scores[0]++;
				}
				else {
					System.out.println("P2 wins!");
					scores[1]++;
				}
				
				System.out.println(scores[0] + "  " + scores[1]);
				System.out.println("-----");
			}	
		}
		
		if(ID == 0) {
			if(scores[0] == 3) System.out.println("Winner: P1!");
			else System.out.println("Winner: P2");
		}
		
		MPI.Finalize();
	}
}
