package com.raviteja.sudokusolver.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.raviteja.sudokusolver.*;

public class TestSolverWindow {

	static final int[][] size2PuzzleData = {
		{0, 0, 4, 2}, 
		{0, 0, 0, 3}, 
		{2, 0, 0, 0}, 
		{3, 1, 0, 0}
	};
	
	static final int[][] size2SolvedPuzzleData = {
		{3, 2, 4, 1},
		{1, 4, 2, 3},
		{2, 1, 3, 4},
		{4, 3, 1, 2}
	};
	
	static final int[][] size3PuzzleData = {
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
	
	static final int[][] size3SolvedPuzzleData = {
		{4, 3, 5, 2, 6, 9, 7, 8, 1},
		{6, 8, 2, 5, 7, 1, 4, 9, 3},
		{1, 9, 7, 8, 3, 4, 5, 6, 2},
		{8, 2, 6, 1, 9, 5, 3, 4, 7},
		{3, 7, 4, 6, 8, 2, 9, 1, 5},
		{9, 5, 1, 7, 4, 3, 6, 2, 8},
		{5, 1, 9, 3, 2, 6, 8, 7, 4},
		{2, 4, 8, 9, 5, 7, 1, 3, 6},
		{7, 6, 3, 4, 1, 8, 2, 5, 9}
	};
	
	@Test
	public void testSolverWindowInt_anyValidInt() {
		assertNotNull(new SolverWindow(3));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSolverWindowInt_zero() {
		new SolverWindow(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSolverWindowInt_negativeValues() {
		new SolverWindow(-23);
	}

	@Test
	public void testGetRow_size2_someRow() {
		SolverWindow solver = new SolverWindow(2);
		solver.loadPuzzle(size2PuzzleData);
		assertArrayEquals(size2PuzzleData[1] ,solver.getRow(1));
	}
	
	@Test
	public void testGetRow_size2_firstRow() {
		SolverWindow solver = new SolverWindow(2);
		solver.loadPuzzle(size2PuzzleData);
		assertArrayEquals(size2PuzzleData[0] ,solver.getRow(0));
	}
	
	@Test
	public void testGetRow_size2_lastRow() {
		SolverWindow solver = new SolverWindow(2);
		solver.loadPuzzle(size2PuzzleData);
		assertArrayEquals(size2PuzzleData[3] ,solver.getRow(3));
	}
	
	@Test
	public void testGetRow_size3_someRow() {
		SolverWindow window = new SolverWindow(3);
		window.loadPuzzle(size3PuzzleData);
		assertArrayEquals(size3PuzzleData[6], window.getRow(6));
	}
	
	@Test
	public void testGetRow_size3_firstRow() {
		SolverWindow window = new SolverWindow(3);
		window.loadPuzzle(size3PuzzleData);
		assertArrayEquals(size3PuzzleData[0], window.getRow(0));
	}
	
	@Test
	public void testGetRow_size3_lastRow() {
		SolverWindow window = new SolverWindow(3);
		window.loadPuzzle(size3PuzzleData);
		assertArrayEquals(size3PuzzleData[8], window.getRow(8));
	}
	
	@Test
	public void testGetColumn_size2_someColumn() {
		
		int testColumn = 1;
		int gridSize = 2;
		SolverWindow solver = new SolverWindow(gridSize);
		int[] expectedResult = new int[gridSize * gridSize];
		
		for(int i=0; i<gridSize*gridSize; i++) {
			expectedResult[i] = size2PuzzleData[i][testColumn];
		}
		
		solver.loadPuzzle(size2PuzzleData);
		assertArrayEquals(expectedResult ,solver.getColumn(testColumn));
	}
	
	@Test
	public void testGetColumn_size2_firstColumn() {
		
		int testColumn = 0;
		int gridSize = 2;
		SolverWindow solver = new SolverWindow(gridSize);
		int[] expectedResult = new int[gridSize * gridSize];
		
		for(int i=0; i<gridSize*gridSize; i++) {
			expectedResult[i] = size2PuzzleData[i][testColumn];
		}
		
		solver.loadPuzzle(size2PuzzleData);
		assertArrayEquals(expectedResult ,solver.getColumn(testColumn));
	}
	
	@Test
	public void testGetColumn_size2_lastColumn() {
		
		int gridSize = 2;
		int testColumn = (gridSize*gridSize)-1;
		SolverWindow solver = new SolverWindow(gridSize);
		int[] expectedResult = new int[gridSize * gridSize];
		
		for(int i=0; i<gridSize*gridSize; i++) {
			expectedResult[i] = size2PuzzleData[i][testColumn];
		}
		
		solver.loadPuzzle(size2PuzzleData);
		assertArrayEquals(expectedResult ,solver.getColumn(testColumn));
	}
	
	@Test
	public void testGetColumn_size3_someColumn() {
		
		int gridSize = 3;
		int testColumn = 1;
		SolverWindow solver = new SolverWindow(gridSize);
		int[] expectedResult = new int[gridSize * gridSize];
		
		for(int i=0; i<gridSize*gridSize; i++) {
			expectedResult[i] = size3PuzzleData[i][testColumn];
		}
		
		solver.loadPuzzle(size3PuzzleData);
		assertArrayEquals(expectedResult ,solver.getColumn(testColumn));
	}
	
	@Test
	public void testGetColumn_size3_firstColumn() {
		
		int gridSize = 3;
		int testColumn = 0;
		SolverWindow solver = new SolverWindow(gridSize);
		int[] expectedResult = new int[gridSize * gridSize];
		
		for(int i=0; i<gridSize*gridSize; i++) {
			expectedResult[i] = size3PuzzleData[i][testColumn];
		}
		
		solver.loadPuzzle(size3PuzzleData);
		assertArrayEquals(expectedResult ,solver.getColumn(testColumn));
	}
	
	@Test
	public void testGetColumn_size3_lastColumn() {
		
		int gridSize = 3;
		int testColumn = (gridSize*gridSize)-1;
		SolverWindow solver = new SolverWindow(gridSize);
		int[] expectedResult = new int[gridSize * gridSize];
		
		for(int i=0; i<gridSize*gridSize; i++) {
			expectedResult[i] = size3PuzzleData[i][testColumn];
		}
		
		solver.loadPuzzle(size3PuzzleData);
		assertArrayEquals(expectedResult ,solver.getColumn(testColumn));
	}
	
	@Test(expected = NullPointerException.class)
	public void testContainsNonZeroUniqueValues_null() {
		SolverWindow.containsNonZeroUniqueValues(null);
	}
	
	@Test
	public void testContainsNonZeroUniqueValues_emptyArray() {
		boolean result = SolverWindow.containsNonZeroUniqueValues(new int[0]);
		assertEquals(false, result);
	}
	
	@Test
	public void testContainsNonZeroUniqueValues_validUniqueValues() {
		int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		boolean result = SolverWindow.containsNonZeroUniqueValues(values);
		assertEquals(true, result);
	}
	
	@Test
	public void testContainsNonZeroUniqueValues_inValidUniqueValue() {
		int[] values = {1, 2, 3, 4, 51, 6, 7, 8, 9};
		boolean result = SolverWindow.containsNonZeroUniqueValues(values);
		assertEquals(false, result);
	}
	
	@Test
	public void testContainsNonZeroUniqueValues_validNonUniqueValues() {
		int[] values = {1, 2, 3, 2, 5, 6, 1, 8, 9};
		boolean result = SolverWindow.containsNonZeroUniqueValues(values);
		assertEquals(false, result);
	}
	
	@Test
	public void testContainsNonZeroUniqueValues_inValidNonUniqueValues() {
		int[] values = {1, 21, 3, 21, 5, 6, 1, 8, 9};
		boolean result = SolverWindow.containsNonZeroUniqueValues(values);
		assertEquals(false, result);
	}
	
	@Test
	public void testIsCompletePuzzle_size2Complete() {
		SolverWindow window = new SolverWindow(2);
		window.loadPuzzle(size2SolvedPuzzleData);
		boolean result = window.isCompletePuzzle();
		assertEquals(true, result);
	}
	
	@Test
	public void testIsCompletePuzzle_size2InComplete() {
		SolverWindow window = new SolverWindow(2);
		window.loadPuzzle(size2PuzzleData);
		boolean result = window.isCompletePuzzle();
		assertEquals(false, result);
	}
	
	@Test
	public void testIsCompletePuzzle_size3Complete() {
		SolverWindow window = new SolverWindow(3);
		window.loadPuzzle(size3SolvedPuzzleData);
		boolean result = window.isCompletePuzzle();
		assertEquals(true, result);
	}
	
	@Test
	public void testIsCompletePuzzle_size3InComplete() {
		SolverWindow window = new SolverWindow(3);
		window.loadPuzzle(size3PuzzleData);
		boolean result = window.isCompletePuzzle();
		assertEquals(false, result);
	}
}
