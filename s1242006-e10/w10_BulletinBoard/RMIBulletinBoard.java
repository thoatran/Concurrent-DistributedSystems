import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class RMIBulletinBoard extends UnicastRemoteObject implements RMIInterface {
    Vector<String> allMes = new Vector<String>();
    Vector<String> allDates = new Vector<String>();

    protected RMIBulletinBoard() throws RemoteException {
    }

    public void AddMessage(String mes) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


        Date date = new Date();
        allMes.add(mes);
        allDates.add(formatter.format(date));

    }

    public String ShowMessages() {
        String all = new String();
        for(int i = 0; i < allMes.size(); i++) {
            System.out.println(allDates.get(i));
            System.out.println(allMes.get(i));
            all = all.concat(allMes.get(i)).concat("\n");
            System.out.println();
        }
        return all;
    }

}
