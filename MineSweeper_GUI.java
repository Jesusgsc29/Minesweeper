package Project2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MineSweeper_GUI extends JFrame {

	private JPanel jpMain;
	private MineSweeperBoard board;
	private int count;
	
	public MineSweeper_GUI() {
		
		jpMain = new JPanel();
		jpMain.setLayout(new BorderLayout());
		board = new MineSweeperBoard();
		jpMain.add(board, BorderLayout.CENTER);
		
		add(jpMain);
		setSize(500,500);
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private class MineSweeperBoard extends JPanel implements ActionListener{

		private JButton[][] board;
		private Grid g;
		private int numRows;
		private int numCols;
		private int numBombs;
		
		public MineSweeperBoard() {
			
			g = new Grid();
			numRows = g.getNumRows();
			numCols = g.getNumColumns();
			numBombs = g.getNumBombs();
			setLayout(new GridLayout(numRows,numCols));
			populateBoard();
			int i,j;
			boolean[][] a = g.getBombGrid();
			for( i=0; i < a.length; i++) {
				for(j=0; j<a[i].length; j++ ) {
					System.out.print(a[i][j]+" ");
				}
				System.out.println("");
			}
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btnClicked = (JButton) e.getSource();
			switch (btnClicked.getText()) {
			case "*":
			btnClicked.setForeground(Color.RED);
			JOptionPane.showMessageDialog(null,"Oh no! you have lost");	
			playAgain();
			break;
			case 0+"":
			btnClicked.setForeground(Color.PINK);
			break;
			case 1+"":
			btnClicked.setForeground(Color.BLUE);
			break;
			case 2+"":
			btnClicked.setForeground(Color.GREEN);
				break;
			case 3+"":
			btnClicked.setForeground(Color.RED);
				break;
			case 4+"":
			btnClicked.setForeground(Color.CYAN);
				break;
			case 5+"":
			btnClicked.setForeground(Color.ORANGE);
				break;
			case 6+"":
			btnClicked.setForeground(Color.ORANGE);
				break;
			case 7+"":
			btnClicked.setForeground(Color.ORANGE);
				break;
			}
			count++;
			if(isWinner()) {
				JOptionPane.showMessageDialog(null,"Congratulations!You have won.");
				playAgain();
			}
		}
	
		
		public void populateBoard() {
			board = new JButton[numRows][numCols];
			for(int row=0; row<board.length; row++){
				for(int col=0; col < board[row].length; col++){
					if(g.isBombAtLocation(row, col)) {
						board[row][col] = new JButton("*");
					}
					else {
						switch (g.getCountAtLocation(row, col)) {
						case 0:
							board[row][col] = new JButton("0");
							break;
						case 1:
							board[row][col] = new JButton(1 +"");
							break;
						case 2:
							board[row][col] = new JButton(2 +"");
							break;
						case 3:
							board[row][col] = new JButton(3 +"");
							break;
						case 4:
							board[row][col] = new JButton(4 +"");
							break;
						case 5:
							board[row][col] = new JButton(5 +"");
							break;
						case 6:
							board[row][col] = new JButton(6 +"");
							break;
						case 7:
							board[row][col] = new JButton(7 +"");		
							break;
						}
				}
					
					Font bigFont = new Font(Font.SANS_SERIF, Font.BOLD, 36);
					board[row][col].setBackground(Color.WHITE);
					board[row][col].setForeground(Color.WHITE);
					board[row][col].setFont(bigFont);
					board[row][col].addActionListener(this);
					board[row][col].setEnabled(true);	
					this.add(board[row][col]);
			}
			}
			
		}
		
		public void playAgain() {
			int yesNo = JOptionPane.showConfirmDialog(null,"Do you want to play again?","Yes or No",JOptionPane.YES_NO_OPTION);
			if(yesNo == JOptionPane.YES_OPTION) {
				count = 0;
				clearBoard();
			}
			else {
				System.exit(EXIT_ON_CLOSE);
			}
		}
		
		private void clearBoard() {
			Grid g2 = new Grid();
			for(int row=0; row<board.length; row++){
				for(int col=0; col < board[row].length; col++){
					if(g2.isBombAtLocation(row, col)) {
						board[row][col].setText("*");;
					}
					else {
						switch (g2.getCountAtLocation(row, col)) {
						case 0:
							board[row][col].setText("0");
							break;
						case 1:
							board[row][col].setText(1 +"");
							break;
						case 2:
							board[row][col].setText(2 +"");
							break;
						case 3:
							board[row][col].setText(3 +"");
							break;
						case 4:
							board[row][col].setText(4 +"");
							break;
						case 5:
							board[row][col].setText(5 +"");
							break;
						case 6:
							board[row][col].setText(6 +"");
							break;
						case 7:
							board[row][col].setText(7 +"");		
							break;
						}
					}
					board[row][col].setBackground(Color.WHITE);
					board[row][col].setForeground(Color.WHITE);
					board[row][col].setEnabled(true);
				}
			}
		}

		public boolean isWinner() {
			if(count == (numRows*numCols)-numBombs) {
				return true;
			}
			return false;
		}
	}//Board class
	
	
	
	

}//GUI class
