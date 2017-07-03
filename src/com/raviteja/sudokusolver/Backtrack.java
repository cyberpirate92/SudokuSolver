package com.raviteja.sudokusolver;

public class Backtrack implements SudokuSolver {

	@Override
	public int[][] solve(int[][] puzzleMatrix) {
		
		int gridSize = (int)Math.sqrt(puzzleMatrix.length);
		SolverWindow solver = new SolverWindow(gridSize);
		solver.loadPuzzle(puzzleMatrix);
		boolean filled = false;
		int unfilledCount = 0, previousUnfilledCount = 0;
		
		while(!solver.isCompletePuzzle()) {
			for(int i=0; i<gridSize*gridSize; i++) {
				for(int j=0; j<gridSize*gridSize; j++) {
					
					int gridRow = i/gridSize, gridCol = j/gridSize;
					int subGridRow = i%gridSize, subGridCol = j%gridSize;
					
					if(solver.getValueAtPosition(gridRow, gridCol, subGridRow, subGridCol) == 0) {
						filled = false;
						for(int val=1; val<=gridSize*gridSize; val++) {
							System.out.println("Trying value " + val + " at position ("+i+","+j+") ...");
							if(solver.setValueAtPosition(gridRow, gridCol, subGridRow, subGridCol, val)) {
								filled = true;
								break;
							}
						}
						if(!filled) 
							unfilledCount++;
					}
				}
			}
			
			System.out.println("Previous unfilled cells: " + previousUnfilledCount);
			System.out.println("Current unfilled cells: " + unfilledCount);
			if(previousUnfilledCount != unfilledCount) { 
				previousUnfilledCount = unfilledCount;
				unfilledCount = 0;
			}
			else {
				System.out.println("Unable to solve");
				break;
			}
		}
			
		int[][] result = solver.getPuzzleMatrix();
		System.out.println("After Backtracking");
		Util.printMatrix(result);
		return result;
	}

}
