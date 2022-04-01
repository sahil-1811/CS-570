package Maze;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;


public class MazeTest extends JFrame implements GridColors {

    
    private TwoDimGrid theGrid;

    
    public static void main(String[] args) {
        try {
            if (args.length < 1) {

                String text =JOptionPane.showInputDialog("Enter number of rows:");
                int nRows = Integer.parseInt(text);
                text =JOptionPane.showInputDialog("Enter number of columns:");
                int nCols = Integer.parseInt(text);
                TwoDimGrid aGrid = new TwoDimGrid(nRows, nCols);
                new MazeTest(aGrid);
            } else {
                BufferedReader br =new BufferedReader(new FileReader(args[0]));
                ArrayList<char[]> gridArrayList = new ArrayList<char[]>();
                String line;
                while ((line = br.readLine()) != null) {
                    char[] row = line.toCharArray();
                    gridArrayList.add(row);
                }
                char[][] bitMap =gridArrayList.toArray(new char[gridArrayList.size()][]);
                int nRows = bitMap.length;
                int nCols = bitMap[0].length;
                TwoDimGrid aGrid = new TwoDimGrid(nRows, nCols);
                aGrid.recolor(bitMap, NON_BACKGROUND);
                new MazeTest(aGrid);
            }
        } catch (Exception ex) {
            System.err.println("Exception " + ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private MazeTest(TwoDimGrid aGrid) {
        theGrid = aGrid;
        getContentPane().add(aGrid, BorderLayout.CENTER);
        JTextArea instruct = new JTextArea(2, 20);
        instruct.setText("Toggle a button to change its color"+ "\nPress SOLVE when ready");
        getContentPane().add(instruct, BorderLayout.NORTH);
        JButton solveButton = new JButton("SOLVE");
        solveButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                solve();
            }
        });
        JButton resetButton = new JButton("RESET");
        resetButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                (new Maze(theGrid)).restore();
            }
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(solveButton);
        bottomPanel.add(resetButton);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

    }

    public void solve() {
    	Maze m = new Maze(theGrid);
        boolean found = m.findMazePath();
		if (found) {
		    JOptionPane.showMessageDialog(null, "Success - Reset Maze and Try Again");
		} else {
			JOptionPane.showMessageDialog(null, "No path - Reset Maze and Try Again");
		}
    }
}