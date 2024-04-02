import objectdraw.*;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class TowersOfHanoi extends WindowController implements KeyListener {
	
	private FramedRect saveButton;
	private FramedRect undoButton;
	private FramedRect autoplayButton;
	private FramedRect resetButton;
	private Text saveText;
	private Text undoText;
	private Text autoplayText;
	private Text resetText;
	private Text moveLabel;
	private int numOfMoves;
	private int numOfDisks;
	private Pole p1;
	private Pole p2;
	private Pole p3;

	
	public void begin() {
		
		saveButton = new FramedRect(630,40,50,15,canvas);
		saveText = new Text("save",640,40,canvas);
		saveText.setFontSize(12);
		
		undoButton = new FramedRect(630,60,50,15,canvas);
		undoText = new Text("undo",640,60,canvas);
		undoText.setFontSize(12);
		
		autoplayButton = new FramedRect(630,80,50,15,canvas);
		autoplayText = new Text("auto",640,80,canvas);
		autoplayText.setFontSize(12);
		
		resetButton = new FramedRect(630,100,50,15,canvas);
		resetText = new Text("reset",640,100,canvas);
		resetText.setFontSize(12);
		
		p1 = new Pole(120, 250, 10, 350, canvas);
		numOfDisks = p1.createDisks(7);
		p2 = new Pole(360, 250, 10, 350, canvas);
		p3 = new Pole(600, 250, 10, 350, canvas);
		numOfMoves = 0;

		moveLabel = new Text("Number of moves: " + numOfMoves, 550, 150, canvas);
		moveLabel.setFontSize(15);
		moveLabel.setColor(Color.RED);
		requestFocus();
		addKeyListener(this);
		canvas.addKeyListener(this);


		
	}
	
	public void newMove() {
		
		numOfMoves++;
		moveLabel.setText("Number of moves: " + numOfMoves);
		
	}
	
	public void movesReset() {
		
		numOfMoves = 0;
		moveLabel.setText("Number of moves: " + numOfMoves);
		
	}
	
	public static void main(String[] args) {
		
		new TowersOfHanoi().startController(720, 1080);
		JOptionPane.showMessageDialog(null, "Towers of Hanoi, built by EA™");
		while(true) {
			//JOptionPane.showMessageDialog(null, "Please send 5 dollars to @Duncan-Wijnberg on Venmo");

		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		JOptionPane.showMessageDialog(null, e.getKeyChar());
		switch (e.getKeyChar()) {
	
		case '3':
			p1.reset();
			p2.reset();
			p3.reset();
			p1.createDisks(3);
			break;
		case '4':
			p1.reset();
			p2.reset();
			p3.reset();
			p1.createDisks(4);
			break;
		case '5':
			p1.reset();
			p2.reset();
			p3.reset();
			p1.createDisks(5);
			break;
		case '6':
			p1.reset();
			p2.reset();
			p3.reset();
			p1.createDisks(6);
			break;
		case '7':
			p1.reset();
			p2.reset();
			p3.reset();
			p1.createDisks(7);
			break;
			
		
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
