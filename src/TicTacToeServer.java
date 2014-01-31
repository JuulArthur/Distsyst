<<<<<<< HEAD
<<<<<<< HEAD
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

/**
 * Created by tib on 1/29/14.
 */
public class TicTacToeServer implements TTTRemoteInterface {
    String url;
    BoardModel boardModel;
    String playerName;
    char opponentMark;

    @Override
    public String connect(String playerName, char mark, TicTacToeServer opponent) throws RemoteException {
        try {
            Naming.unbind(this.url);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return playerName;
    }

    TicTacToeServer(String playerName, String url, BoardModel boardModel) {
        System.setSecurityManager(new RMISecurityManager());
        this.url = url;
        this.playerName = playerName;
        this.boardModel = boardModel;
    }

    public void setUrl(String url){
        this.url = url;

    }

    public void setOpponentMark(char mark) throws RemoteException{
        this.opponentMark = mark;
    }

    public void doMove(int x, int y) throws RemoteException {
        boardModel.setCell(x, y, this.opponentMark);
    }

    @Override
    public void disconnect() throws RemoteException {

    }
=======
=======
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

>>>>>>> leik dis?
/**
 * Created by tib on 1/29/14.
 */
public class TicTacToeServer implements TTTRemoteInterface {
    String url;
    TicTacToe game;
    String playerName;

    @Override
    public String connect(String playerName, char mark, TicTacToeServer opponent) throws RemoteException {
        try {
            Naming.unbind(this.url);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return playerName;
    }

    TicTacToeServer(String playerName, String url) {
        this.url = url;
        this.playerName = playerName;
    }

    public void setUrl(String url){
        this.url = url;

    }

    @Override
    public void disconnect() throws RemoteException {

<<<<<<< HEAD
>>>>>>> Klasser her og der
=======
    }
>>>>>>> leik dis?
}
