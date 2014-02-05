import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * A Tic Tac Toe application.
 * Currently this is a stand-alone application where
 * players take alternating turns using the same computer.
 * <p/>
 * The task is to transform it to a networking application using RMI.
 */
public class TicTacToe extends JFrame implements ListSelectionListener
{
    private static final int BOARD_SIZE = 15;
    private final BoardModel boardModel;
    private final JTable board;
    private final JLabel statusLabel = new JLabel();
    private final char playerMarks[] = {'X', 'O'};
    private int currentPlayer = 0; // Player to set the next mark.
    private final static int PORT = 3101;



    private TTTServerImpl localPlayer;
    private TTTServer remotePlayer;


    public static void main(String args[])
    {
        new TicTacToe();
    }

    public void initRMI(String address){
        // Set sercurity manager
        System.setSecurityManager(new InsecureSecurityManager());

        //Set local server
        try {
            this.localPlayer = new TTTServerImpl(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        //Set remote server
        String url = "rmi://"+ address + "/TTTServer";
        try {
            this.remotePlayer = (TTTServer) Naming.lookup(url);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        //Remote player not yet found, I am server
        if(remotePlayer == null){
            try {
                Naming.rebind(url, localPlayer);
                System.out.println("Waiting for opponent...");

            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        //Remote player found
        else{
            try {
                remotePlayer.connect(url,'X',localPlayer);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

    }

    public void setOpponent(TTTServer opponent){
        this.remotePlayer = opponent;
    }
    public TicTacToe()
    {
        super("TDT4190: Tic Tac Toe");

        boardModel = new BoardModel(BOARD_SIZE);
        board = new JTable(boardModel);
        board.setFont(board.getFont().deriveFont(25.0f));
        board.setRowHeight(30);
        board.setCellSelectionEnabled(true);
        for (int i = 0; i < board.getColumnCount(); i++)
            board.getColumnModel().getColumn(i).setPreferredWidth(30);
        board.setGridColor(Color.BLACK);
        board.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer dtcl = new DefaultTableCellRenderer();
        dtcl.setHorizontalAlignment(SwingConstants.CENTER);
        board.setDefaultRenderer(Object.class, dtcl);
        board.getSelectionModel().addListSelectionListener(this);
        board.getColumnModel().getSelectionModel().addListSelectionListener(this);

        statusLabel.setPreferredSize(new Dimension(statusLabel.getPreferredSize().width, 40));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(board, BorderLayout.CENTER);
        contentPane.add(statusLabel, BorderLayout.SOUTH);
        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int centerX = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getSize().width) / 2;
        int centerY = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getSize().height) / 2;
        setLocation(centerX, centerY);
        setVisible(true);

        initRMI("127.0.0.1:" + PORT);
    }

    void setStatusMessage(String status)
    {
        statusLabel.setText(status);
    }

    /**
     * This has to be modified. Currently the application is stand-alone so
     * both players have to use the same computer.
     * <p/>
     * When completed, marks from the first player originates from a ListSelectionEvent
     * and is then sent to the second player. And marks from the second player is received
     * and added to the board of the first player.
     */
    public void valueChanged(ListSelectionEvent e)
    {
        System.out.println("Changing my value");
        System.out.println(e.toString());
        if (e.getValueIsAdjusting())
            return;
        int x = board.getSelectedColumn();
        int y = board.getSelectedRow();
        System.out.println(x);
        System.out.println(y);
        if (x == -1 || y == -1 || !boardModel.isEmpty(x, y))
            return;
        if (boardModel.setCell(x, y, playerMarks[currentPlayer]))
            setStatusMessage("Player " + playerMarks[currentPlayer] + " won!");
        currentPlayer = 1 - currentPlayer; // The next turn is by the other player.

        try {
            remotePlayer.valueChanged(x,y);
            System.out.println("Changing the other value");
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }


    }

    public void LocalValueChanged(int x, int y)
    {
        if (x == -1 || y == -1 || !boardModel.isEmpty(x, y))
            return;
        if (boardModel.setCell(x, y, playerMarks[currentPlayer]))
            setStatusMessage("Player " + playerMarks[currentPlayer] + " won!");
        currentPlayer = 1 - currentPlayer; // The next turn is by the other player.
    }
}
