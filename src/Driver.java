/**
 * Driver class used for testing SolverWindow
 * @author cyberpirate92
 *
 */
import javax.swing.SwingUtilities;

public class Driver {
	public static void main(String[] args) {
		
		int nGrids = 3;
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SolverWindow(nGrids);
			}
		});
	}
}