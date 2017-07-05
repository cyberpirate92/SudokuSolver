package com.raviteja.sudokusolver;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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
	private SolverWindow window;
	
	public SubGrid(int row, int col, int gridSize, SolverWindow window) {
		super();
		this.gridRow = row;
		this.gridCol = col;
		this.gridSize = gridSize;
		this.cells = new JTextField[this.gridSize][this.gridSize];
		this.data = new GridData(gridSize);
		this.window = window;
		
		initializeLayout();
	}
	
	private void initializeLayout() {
		this.gridLayout = new GridLayout(this.gridSize, this.gridSize, HORIZONTAL_GAP, VERTICAL_GAP);
		this.setLayout(this.gridLayout);
		this.setBorder(BorderFactory.createLineBorder(Color.black)); 
		
		// adding elements to sub grid
		for(int i=0; i<cells.length; i++) {
			for(int j=0; j<cells[i].length; j++) {
				final int subGridRow = i, subGridCol = j;
				this.cells[i][j] = new JTextField(1);
				this.cells[i][j].setBorder(BorderFactory.createLineBorder(Util.getBackgroundColor(gridRow+1, gridCol)));
				this.cells[i][j].setHorizontalAlignment(JTextField.CENTER);
				this.cells[i][j].setFont(Util.getFont());
				this.cells[i][j].addFocusListener(new FocusListener(){
 
					@Override
					public void focusGained(FocusEvent e) {
						//SubGrid.this.setBackgroundToAll(Util.getHighlightColor());
						SubGrid.this.window.highlightRow(gridRow*gridSize+subGridRow);
						SubGrid.this.window.highlightColumn(gridCol*gridSize+subGridCol);
					}

					@Override
					public void focusLost(FocusEvent e) {
						//SubGrid.this.setBackgroundToAll(Util.getBackgroundColor(gridRow, gridCol));
						SubGrid.this.window.deHighlightRow(gridRow*gridSize+subGridRow);
						SubGrid.this.window.deHighlightCol(gridCol*gridSize+subGridCol);
					}
					
				});
				this.add(cells[i][j]);
			}
		}
		setBackgroundToAll(Util.getBackgroundColor(gridRow, gridCol));
		this.setVisible(true);
	}
	
	public void setBackgroundToAll(Color backgroundColor) {
		for(int i=0; i<this.gridSize; i++) {
			for(int j=0; j<this.gridSize; j++) {
				cells[i][j].setBackground(backgroundColor);
			}
		}
	}
	
	public boolean setValueAtPosition(int row, int col, int value) {
		boolean result = this.data.setValueAtPosition(row, col, value);
		if(data.getValueAtPosition(row, col) != 0)
			this.cells[row][col].setText(value + "");
		return result;
	}
	
	public int getValueAtPosition(int row, int col) {
		return this.data.getValueAtPosition(row, col);
	}
	
	GridData getData() {
		return this.data;
	}
	
	public void setRowColor(int rowNumber, Color color) {
		for(int i=0; i<gridSize; i++)
			this.cells[rowNumber][i].setBackground(color);
	}
	
	public void setColumnColor(int colNumber, Color color) {
		for(int i=0; i<gridSize; i++)
			this.cells[i][colNumber].setBackground(color);
	}
	
	public void highlightRow(int row) {
		setRowColor(row, Util.getHighlightColor());
	}
	
	public void deHighlightRow(int row) {
		setRowColor(row, Util.getBackgroundColor(gridRow, gridCol));
	}
	
	public void highlightColumn(int col) {
		setColumnColor(col, Util.getHighlightColor());
	}
	
	public void deHighlightCol(int col) {
		setColumnColor(col, Util.getBackgroundColor(gridRow, gridCol));
	}
}
