package com.raviteja.sudokusolver;
/** 
 * Data Model for each sub grid
 * @author cyberpirate92 *
 */

public class GridData {
	
	private int gridSize;
	private int[][] data;
	private boolean[][] isConstant;
	private boolean[] flag;
	
	public GridData(int gridSize) {
		this.gridSize = gridSize;
		data = new int[this.gridSize][this.gridSize];
		flag = new boolean[gridSize*gridSize];
		isConstant = new boolean[this.gridSize][this.gridSize];
	}
	
	public int getValueAtPosition(int row, int col) {
		return this.data[row][col];
	}
	
	public int[] getRow(int row) {
		return this.data[row];
	}
	
	public int[] getColumn(int col) {
		int[] columnData = new int[this.gridSize];
		for(int i=0; i<this.gridSize; i++) {
			columnData[i] = this.data[i][col];
		}
		return columnData;
	}
	
	public boolean setValueAtPosition(int row, int col, int value) {
		return setValueAtPosition(row, col, value, false);
	}
	
	public boolean setValueAtPosition(int row, int col, int value, boolean isConstant) {
		if(value == 0) { 
			if(this.data[row][col] != 0) {
				this.flag[this.data[row][col]-1] = false;
			}
			this.data[row][col] = value;
		}
		else if(value >= 1 && value <=this.gridSize*this.gridSize) {
			if(flag[value-1]) {
				System.out.println("Value " + value + " already exists in grid");
				return false;
			}
			else {
				this.data[row][col] = value;
				this.flag[value-1] = true;
				this.isConstant[row][col] = isConstant;
			}
		}
		else {
			System.out.println("Value '" + value + "' not in range [1-"+(gridSize*gridSize)+"]");
			return false;
		}
		return true;
	}
	
	public int[][] getMatrix() {
		return this.data;
	}
	
	public boolean isConstant(int row, int col) {
		return isConstant[row][col];
	}
}
