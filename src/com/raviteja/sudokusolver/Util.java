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
}
