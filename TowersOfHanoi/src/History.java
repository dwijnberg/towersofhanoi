import java.util.*;
import java.io.*;
import objectdraw.*;


public class History {
	private Stack<String[]> history;
	private Pole leftPile;
	private Pole midPile;
	private Pole rightPile;
	private Pole source;
	private Pole destination;
	private Text numMoves;
	private int numDisks;
	private Autoplayer autoRun;
	
	public History(Pole lp, Pole mp, Pole rp, Text n, int nd) {
		leftPile = lp;
		midPile = mp;
		rightPile = rp;
		numMoves = n;
		numDisks = nd;
		history = new Stack<String[]>();
	}
	
	public void setAutoRun(Autoplayer i) {
		autoRun = i;
	}
	
	public void addMove(String[] a){
		String[] temp = {"x", "x"};
		temp[0] = a[0];
		temp[1] = a[1];
		history.push(temp);
		numMoves.setText("Number of Moves: " + history.size());
	}

	
	public Stack<String[]> getHistory() {
		return history;
	}
	
}