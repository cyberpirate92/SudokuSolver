import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SolverWindow extends JFrame {
	
	// constants
	private static final int WINDOW_HEIGHT = 500;
	private static final int WINDOW_WIDTH = 500;
	private static final int HORIZONTAL_GAP = 0;
	private static final int VERTICAL_GAP = 0;
	private static final String DELIM_CHARACTER = ",";
	
	private int gridSize;
	private SubGrid[][] subGrids;
	private JPanel mainPanel, buttonPanel;
	private JMenuBar menuBar;
	private File puzzleDataFile;
	
	public SolverWindow(String windowTitle, int gridSize) {
		
		super(windowTitle);
		
		this.gridSize = gridSize;
		this.mainPanel = new JPanel();
		this.buttonPanel = new JPanel();
		this.subGrids = new SubGrid[gridSize][gridSize];
		this.menuBar = new JMenuBar();
		this.puzzleDataFile = null;
		
		initializeGUI();
	}
	
	public SolverWindow(int gridSize) {
		this("Sudoku Solver", gridSize);
	}
	
	private void initializeGUI() {
		
		initMenuBar();
		initSudokuGrid();
		initButtonPanel();
		initWindowLayout();
		
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void initMenuBar() {
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem loadPuzzleItem = new JMenuItem("Load");
		loadPuzzleItem.setMnemonic(KeyEvent.VK_L);
		loadPuzzleItem.setFont(Util.getFont());
		loadPuzzleItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				final JFileChooser fileChooser = new JFileChooser();
				int status = fileChooser.showOpenDialog(SolverWindow.this);
				
				if(status == JFileChooser.APPROVE_OPTION) {
					try {
						SolverWindow.this.setPuzzleDataFile(fileChooser.getSelectedFile());
					}
					catch(Exception e) {
						displayErrorDialog(e.getMessage());
						e.printStackTrace();
					}
				}
				else {
					System.out.println("Load cancelled");
				}
			}
		});
		fileMenu.add(loadPuzzleItem);
		
		JMenuItem savePuzzleItem = new JMenuItem("Save");
		savePuzzleItem.setMnemonic(KeyEvent.VK_S);
		savePuzzleItem.setFont(Util.getFont());
		savePuzzleItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				final JFileChooser fileChooser = new JFileChooser();
				int status = fileChooser.showSaveDialog(SolverWindow.this);
				
				if(status == JFileChooser.APPROVE_OPTION) {
					try {
						if(writeMatrixToFile(fileChooser.getSelectedFile())) {
							displayInfoDialog("File saved.");
						} else {
							displayErrorDialog("File could not be saved.");
						}
					}
					catch(Exception e) {
						displayErrorDialog(e.getMessage());
						e.printStackTrace();
					}
				}
				else {
					System.out.println("Save cancelled");
				}
			}
		});
		fileMenu.add(savePuzzleItem);
		
		fileMenu.setFont(Util.getFont());
		this.menuBar.add(fileMenu);
	}
	
	private void initSudokuGrid() {
		GridLayout gridLayout = new GridLayout(this.gridSize, this.gridSize);
		gridLayout.setHgap(HORIZONTAL_GAP);
		gridLayout.setVgap(VERTICAL_GAP);
		
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(gridLayout);
		
		for(int i=0; i<this.gridSize; i++) {
			for(int j=0; j<this.gridSize; j++) {
				this.subGrids[i][j] = new SubGrid(i, j, this.gridSize);
				tempPanel.add(this.subGrids[i][j]);
			}
		}
		
		this.mainPanel.setLayout(new BorderLayout());
		this.mainPanel.add(tempPanel, BorderLayout.CENTER);
	}
	
	private void initButtonPanel() {
		
		JButton solveButton, clearButton, evaluateButton;
		solveButton = new JButton("Solve");
		solveButton.setFont(Util.getFont());
		solveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		clearButton = new JButton("Clear");
		clearButton.setFont(Util.getFont());
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		evaluateButton = new JButton("Evaluate");
		evaluateButton.setFont(Util.getFont());
		evaluateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		this.buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.buttonPanel.add(solveButton);
		this.buttonPanel.add(clearButton);
		this.buttonPanel.add(evaluateButton);
	}
	
	private void initWindowLayout() {
		this.setJMenuBar(this.menuBar);
		this.setLayout(new BorderLayout());
		this.getContentPane().add(this.mainPanel, BorderLayout.CENTER);
		this.getContentPane().add(this.buttonPanel, BorderLayout.SOUTH);
	}
	
	private void setPuzzleDataFile(File file) {
		this.puzzleDataFile = file;
		loadPuzzle();
	}
	
	private int[][] readMatrixFromFile() {
		
		int[][] matrix = new int[this.gridSize*this.gridSize][this.gridSize*this.gridSize];
		try {
			
			int count = this.gridSize*this.gridSize;
			int lineCount = 0;
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(this.puzzleDataFile));
			
			while((line = reader.readLine()) != null) {
				String[] tokens = line.split(DELIM_CHARACTER);
				if(tokens.length != count) {
					displayErrorDialog("Puzzle file corrupted, lesser/more number of elements found than " + count + "");
					return null;
				}
				else {
					if(lineCount < this.gridSize*this.gridSize) {
						for(int i=0; i<tokens.length; i++) {
							matrix[lineCount][i] = Integer.parseInt(tokens[i]);
						}
					}
					else {
						displayErrorDialog("Puzzle file contains excess rows, truncating");
						break;
					}
				}
				lineCount++;
			}
			
			if(lineCount != this.gridSize*this.gridSize) {
				displayErrorDialog("Puzzle file incomplete, missing " + (this.gridSize*this.gridSize - lineCount) + "rows");
				return null;
			}
		}
		catch(NumberFormatException nfe) {
			displayErrorDialog("Puzzle file corrupt, contains non-numeric character where numeric expected");
			nfe.printStackTrace();
		}
		catch(FileNotFoundException fnfe) {
			displayErrorDialog("The file '" + this.puzzleDataFile.getName() + "' could not be opened.");
			fnfe.printStackTrace();
		}
		catch(IOException ioe) {
			displayErrorDialog("I/O Error occured, please try opening the file again.");
			ioe.printStackTrace();
		}
		catch(Exception e) {
			displayErrorDialog("An unhandled exception has occured, please try again");
			e.printStackTrace();
		}
		return matrix;
	}
	
	private void loadPuzzle() {
		int[][] puzzleData = readMatrixFromFile();
		for(int i=0; i<puzzleData.length; i++) {
			for(int j=0; j<puzzleData[i].length; j++) {
				
				// sub-grid row and column
				int subGridRow = i/gridSize;
				int subGridCol = j/gridSize;
				
				// row and column within the sub-grid
				int gridRow = i%gridSize;
				int gridCol = j%gridSize;
				
				this.subGrids[subGridRow][subGridCol].setValueAtPosition(gridRow, gridCol, puzzleData[i][j]);
			}
		}
	}
	
	/*
	 * Combine all sub-grids to form 
	 * a single large matrix, basically
	 * do the reverse of loadPuzzle
	 */
	private int[][] getPuzzleMatrix() {
		int[][] matrix = new int[this.gridSize*this.gridSize][this.gridSize*this.gridSize];
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[i].length; j++) {
				
				// sub-grid row and column
				int subGridRow = i/gridSize;
				int subGridCol = j/gridSize;
				
				// row and column within the sub-grid
				int gridRow = i%gridSize;
				int gridCol = j%gridSize;
				
				matrix[i][j] = this.subGrids[subGridRow][subGridCol].getValueAtPosition(gridRow, gridCol);
			}
		}
		
		return matrix;
	}
	
	private static void displayErrorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private static void displayInfoDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private boolean writeMatrixToFile(File file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			int[][] matrix = getPuzzleMatrix();
			for(int i=0; i<matrix.length; i++) {
				for(int j=0; j<matrix[i].length; j++) {
					writer.write(matrix[i][j]+"");
					if(j != matrix[i].length-1)
						writer.write(",");
				}
				writer.write("\r\n");
			}
			writer.flush();
			writer.close();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
