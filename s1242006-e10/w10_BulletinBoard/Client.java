
import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            RMIInterface temp = (RMIInterface) Naming.lookup("//127.0.0.1/Board");
            if(args[0].equals("add")){
                Scanner sc = new Scanner(System.in);
                String mes = sc.nextLine();
                temp.AddMessage(mes);
            }
            else if(args[0].equals("read")){
                String strMes = new String();
                strMes = temp.ShowMessages();
                System.out.println(strMes);
            }
            else {
                System.out.println("Error!!!");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
