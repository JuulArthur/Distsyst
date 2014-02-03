import javax.swing.event.ListSelectionEvent;

/**
 * Created by tib on 2/3/14.
 */
public interface TTTServer {

    public void connect(String name, char mark ,TTTServerImpl opponent);

    public void valueChanged(ListSelectionEvent e);


}
