import javax.swing.event.ListSelectionEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by tib on 2/3/14.
 */
public class TTTServerImpl extends UnicastRemoteObject implements TTTServer {

    private TicTacToe tttgame;


    public TTTServerImpl() throws RemoteException{
        this.tttgame = tttgame;
    }

    @Override
    public void connect(String address, char mark, TTTServerImpl opponent) throws RemoteException{
        try {
            Naming.rebind(address, opponent);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void valueChanged(ListSelectionEvent e){
        tttgame.valueChanged(e);

    }



}
