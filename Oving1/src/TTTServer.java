import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by tib on 2/3/14.
 */
public interface TTTServer extends Remote{

    public void connect(String name, char mark ,TTTServer opponent) throws RemoteException;

    public void valueChanged(int x, int y) throws RemoteException;


}
