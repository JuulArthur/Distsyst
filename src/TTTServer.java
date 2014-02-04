import javax.swing.event.ListSelectionEvent;
import java.rmi.*;

/**
 * Created by tib on 2/3/14.
 */
public interface TTTServer extends Remote{

    public void connect(String name, char mark ,TTTServerImpl opponent) throws RemoteException;

    public void valueChanged(ListSelectionEvent e) throws RemoteException;


}
