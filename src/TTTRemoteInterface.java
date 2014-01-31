/**
 * Created by tib on 1/29/14.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TTTRemoteInterface extends Remote {

    public String connect(String playerName, char piece, TicTacToeServer opponent) throws RemoteException;
    public void disconnect() throws RemoteException;
<<<<<<< HEAD
    public void doMove(int x, int y);
=======
>>>>>>> leik dis?

}
