package com.raviteja.sudokusolver.test;

import static org.junit.Assert.*;

import com.raviteja.sudokusolver.Backtrack;

import org.junit.Test;

public class TestBacktrack {
	
	static final int[][] size2PuzzleDataInitial = {
		{0, 0, 4, 2}, 
		{0, 0, 0, 3}, 
		{2, 0, 0, 0}, 
		{3, 1, 0, 0}
	};
	
	static final int[][] size2PuzzleDataFinal = {
		{1, 3, 4, 2},
		{4, 2, 1, 3},
		{2, 4, 3, 1},
		{3, 1, 2, 4}
	};
	
	static final int[][] size3PuzzleDataInitial = {
		{0, 0, 9, 0, 0, 0, 4, 0, 0},
		{8, 0, 0, 0, 2, 0, 0, 6, 0},
		{0, 6, 2, 0, 0, 0, 1, 0, 0},
		{2, 0, 1, 5, 0, 9, 0, 8, 3},
		{0, 0, 8, 1, 0, 2, 7, 0, 0},
		{4, 9, 0, 7, 0, 6, 2, 0, 1},
		{0, 0, 7, 0, 0, 0, 8, 1, 0},
		{0, 1, 0, 0, 7, 0, 0, 0, 4},
		{0, 0, 4, 0, 0, 0, 3, 0, 0}
	};
	
	
	static final int[][] size3PuzzleDataFinal = {
		{1, 3, 9, 8, 6, 7, 4, 2, 5},
		{8, 4, 5, 3, 2, 1, 9, 6, 7},
		{7, 6, 2, 9, 5, 4, 1, 3, 8},
		{2, 7, 1, 5, 4, 9, 6, 8, 3},
		{6, 5, 8, 1, 3, 2, 7, 4, 9},
		{4, 9, 3, 7, 8, 6, 2, 5, 1},
		{5, 2, 7, 4, 9, 3, 8, 1, 6},
		{3, 1, 6, 2, 7, 8, 5, 9, 4},
		{9, 8, 4, 6, 1, 5, 3, 7, 2}
	};
	
	@Test
	public void testSolveSize2() {
		Backtrack algorithm = new Backtrack();
		int[][] result = algorithm.solve(size2PuzzleDataInitial);
		assertArrayEquals(size2PuzzleDataFinal, result);
	}
	
	@Test
	public void testSolveSize3() {
		Backtrack algorithm = new Backtrack();
		int[][] result = algorithm.solve(size3PuzzleDataInitial);
		assertArrayEquals(size3PuzzleDataFinal, result);
	}

}
