import java.util.Random;

import mpi.MPI;

public class Main {
    private static final int ROCK = 0;
    private static final int SCISSORS = 2;
    private static final int PAPER = 1;

    private static int[] totalScore = new int[] {0,0};

    public static void main(String args[]) throws Exception {
        MPI.Init(args);
        int ID = MPI.COMM_WORLD.Rank();

        Random ran = new Random();

        while(totalScore[0] < 3 && totalScore[1] < 3) {
            int random = ran.nextInt(3);

            if (ID == 1) {
                MPI.COMM_WORLD.Send(new int[] {random}, 0, 1, MPI.INT, 0, 0);
            } else {
                int tmp[] = new int[1];
                MPI.COMM_WORLD.Recv(tmp, 0, 1, MPI.INT, MPI.ANY_SOURCE, MPI.ANY_TAG);

                System.out.println("P1: " + stringEquivalent(random));
                System.out.println("P2: " + stringEquivalent(tmp[0]));

                if(random == tmp[0]) {
                    System.out.println("Draw");
                }

                else if(random == ROCK && tmp[0] == SCISSORS) {
                    System.out.println("P1 win");
                    totalScore[0]++;
                }

                else if(random == SCISSORS && tmp[0] == PAPER) {
                    System.out.println("P1 wins");
                    totalScore[0]++;
                }

                else if(random == PAPER && tmp[0] == ROCK) {
                    System.out.println("P1 wins");
                    totalScore[0]++;
                }

                else {
                    System.out.println("P2 wins");
                    totalScore[1]++;
                }

                System.out.println(totalScore[0] + " - " + totalScore[1]);
            }
        }

        if(ID == 0) {
            if(totalScore[0] == 3) System.out.println("Winner: P1");
            else System.out.println("Winner: P2");
        }

        MPI.Finalize();
    }

    private static String stringEquivalent(int num) {
        if (num == ROCK) {
            return "Rock";
        } else if(num == SCISSORS) {
            return "Scissors";
        } else {
            return "Paper";
        }
    }
}
