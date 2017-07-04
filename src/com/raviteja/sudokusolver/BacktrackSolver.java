package com.raviteja.sudokusolver;

public class BacktrackSolver implements SudokuSolver {
	
	SolverWindow solver;
	
	@Override
	public int[][] solve(int[][] puzzleMatrix) {
		solver = new SolverWindow((int)Math.sqrt(puzzleMatrix.length));
		backtrack();
		return solver.getPuzzleMatrix();
	}
	
	private void backtrack() {
		
	}
	
	private boolean backtrack(int n) {
		return false;
	}

}
