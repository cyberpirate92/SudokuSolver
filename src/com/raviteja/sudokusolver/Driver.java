package com.raviteja.sudokusolver;
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
				SolverWindow window = new SolverWindow(nGrids);
				window.setVisible(true);
			}
		});
	}
}
