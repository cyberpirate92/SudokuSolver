import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SolverWindow extends JFrame {
	
	// constants
	private static final int WINDOW_HEIGHT = 500;
	private static final int WINDOW_WIDTH = 500;
	private static final int HORIZONTAL_GAP = 0;
	private static final int VERTICAL_GAP = 0;
	
	private int gridSize;
	private SubGrid[][] subGrids;
	private JPanel mainPanel, buttonPanel;
	
	public SolverWindow(String windowTitle, int gridSize) {
		
		super(windowTitle);
		
		this.gridSize = gridSize;
		this.mainPanel = new JPanel();
		this.buttonPanel = new JPanel();
		this.subGrids = new SubGrid[gridSize][gridSize];
		
		initializeGUI();
	}
	
	public SolverWindow(int gridSize) {
		this("Sudoku Solver", gridSize);
	}
	
	private void initializeGUI() {
		
		initSudokuGrid();
		initButtonPanel();
		initWindowLayout();
		
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
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
		this.setLayout(new BorderLayout());
		this.getContentPane().add(this.mainPanel, BorderLayout.CENTER);
		this.getContentPane().add(this.buttonPanel, BorderLayout.SOUTH);
	}
}
