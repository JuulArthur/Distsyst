import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by tib on 2/3/14.
 */
public class TTTServerImpl extends UnicastRemoteObject implements TTTServer {

    private TicTacToe tttgame;


    public TTTServerImpl(TicTacToe tttgame) throws RemoteException{
        this.tttgame = tttgame;
    }

    @Override
    public void connect(String address, char mark, TTTServer opponent) throws RemoteException{
        tttgame.setOpponent(opponent);
        tttgame.setStatusMessage("Opponent connected. My turn");
    }

    public void valueChanged(int x, int y){
        tttgame.localValueChanged(x,y);
    }



}
