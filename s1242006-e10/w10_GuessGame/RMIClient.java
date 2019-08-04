
import java.rmi.Naming;
import java.util.Scanner;

public class RMIClient {
    public static void main(String[] args) {
        boolean check = true;
        char[] toprint;
        try {
            RMIGuessGameInterface temp = (RMIGuessGameInterface) Naming.lookup("//127.0.0.1/Game");
            toprint = temp.theFirstReturnStr();
            System.out.print("Server> ");
            System.out.println(toprint);

            do{
                check = false;
                System.out.print("Client> ");
                Scanner sc = new Scanner(System.in);
                char c = sc.next().charAt(0);
                toprint = temp.createReturnString(c);
                System.out.print("Server> ");
                System.out.println(toprint);
                for (int i = 0; i < toprint.length; i++){
                    if(toprint[i] == '*')
                        check = true;
                }
            } while (check == true);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
