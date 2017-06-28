import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SubGrid extends JPanel {
	
	// constants
	private static final int HORIZONTAL_GAP = 0;
	private static final int VERTICAL_GAP = 0;
	
	private int gridSize;
	private GridLayout gridLayout;
	private JTextField[][] cells;
	private GridData data;
	
	public SubGrid(int gridSize) {
		super();
		this.gridSize = gridSize;
		this.cells = new JTextField[this.gridSize][this.gridSize];
		
		initializeLayout();
	}
	
	private void initializeLayout() {
		this.gridLayout = new GridLayout(this.gridSize, this.gridSize, HORIZONTAL_GAP, VERTICAL_GAP);
		this.setLayout(this.gridLayout);
		this.setBorder(BorderFactory.createLineBorder(Color.black)); 
		
		// adding elements to sub grid
		for(int i=0; i<cells.length; i++) {
			for(int j=0; j<cells[i].length; j++) {
				this.cells[i][j] = new JTextField(1);
				this.cells[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				this.cells[i][j].setHorizontalAlignment(JTextField.CENTER);
				this.cells[i][j].setFont(Util.getFont());
				this.add(cells[i][j]);
			}
		}
		this.setVisible(true);
	}
}
