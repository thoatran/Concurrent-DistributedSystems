import java.rmi.Naming;

public class Server{
    public static void main(String[] args) {
        try{
            Naming.rebind("Board",new RMIBulletinBoard());
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
