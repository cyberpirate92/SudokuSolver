package com.raviteja.sudokusolver;
import java.awt.Color;
import java.awt.Font;

public class Util {
	public static Font getFont() {
		Font font = new Font("Arial", Font.PLAIN, 15);
		return font;
	}
	
	public static Color getBackgroundColor(int row, int col) {
		if((row + col) % 2 == 0)
			return Color.LIGHT_GRAY;
		else
			return Color.WHITE;
	}
	
	public static Color getHighlightColor() {
		return Color.ORANGE;
	}
	
	public static void printMatrix(int[][] matrix) {
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[i].length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public static void printMatrix(int[] matrix) {
		System.out.print("[");
		for(int i=0; i<matrix.length; i++)
			System.out.print(matrix[i] + ",");
		System.out.print("]\n");
	}
	
	// TODO: Inefficient implementation, try to make it efficient
	public static boolean contains(int[] values, int value) {
		for(int x : values) 
			if(x == value)
				return true;
		return false;
	}
}
