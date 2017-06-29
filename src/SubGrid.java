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
	private int gridRow, gridCol; // denotes the row and column position of this sub-grid
	
	public SubGrid(int row, int col, int gridSize) {
		super();
		this.gridRow = row;
		this.gridCol = col;
		this.gridSize = gridSize;
		this.cells = new JTextField[this.gridSize][this.gridSize];
		this.data = new GridData(gridSize);
		
		initializeLayout();
	}
	
	private void initializeLayout() {
		this.gridLayout = new GridLayout(this.gridSize, this.gridSize, HORIZONTAL_GAP, VERTICAL_GAP);
		this.setLayout(this.gridLayout);
		this.setBorder(BorderFactory.createLineBorder(Color.black)); 
		
		Color backgroundColor = Util.getBackgroundColor(gridRow, gridCol);
		
		// adding elements to sub grid
		for(int i=0; i<cells.length; i++) {
			for(int j=0; j<cells[i].length; j++) {
				this.cells[i][j] = new JTextField(1);
				this.cells[i][j].setBorder(BorderFactory.createLineBorder(Util.getBackgroundColor(gridRow+1, gridCol)));
				this.cells[i][j].setHorizontalAlignment(JTextField.CENTER);
				this.cells[i][j].setFont(Util.getFont());
				this.cells[i][j].setBackground(backgroundColor);
				this.add(cells[i][j]);
			}
		}
		this.setVisible(true);
	}
	
	public void setValueAtPosition(int row, int col, int value) throws Exception {
		this.data.setValueAtPosition(row, col, value);
		if(data.getValueAtPosition(row, col) != 0)
			this.cells[row][col].setText(value + "");
	}
	
	public int getValueAtPosition(int row, int col) {
		return this.data.getValueAtPosition(row, col);
	}
	
	GridData getData() {
		return this.data;
	}
}
