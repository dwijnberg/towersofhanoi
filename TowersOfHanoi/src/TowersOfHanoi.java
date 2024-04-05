import objectdraw.*;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Clock;
import java.util.Iterator;
import java.util.Stack;

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
	private Disk selected;
	private Location here;
	private Stack<Move> moves;
	private Text victory;

	
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
		
		p1 = new Pole(120, 250, 10, 350, 1, canvas);
		numOfDisks = p1.createDisks(7);
		p2 = new Pole(360., 250., 10., 350., 2, canvas);
		p3 = new Pole(600, 250, 10, 350, 3, canvas);
		
		numOfMoves = 0;
		moveLabel = new Text("Number of moves: " + numOfMoves, 550, 150, canvas);
		moveLabel.setFontSize(15);
		moveLabel.setColor(Color.RED);
		
		requestFocus();
		addKeyListener(this);
		canvas.addKeyListener(this);
		
		moves = new Stack<Move>();


		
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
	
	public void onMouseClick(Location point) {
		
		String button = inButton(point);
		if (button == null) {
			return;
		}
		switch (button) {
		
		case "save":
			save();
			break;
		case "reset":
			p1.reset();
			p2.reset();
			p3.reset();
			p1.createDisks();
			break;
		case "undo":
			if (moves.size() > 0) {
				undo();
				break;
			}
			
		
		}
		
	}
	
	public void onMousePress(Location point) {
		here = point;
		if(p1.diskContains(point)) {
			selected = p1.getDisk();
		}
		if(p2.diskContains(point)) {
			selected = p2.getDisk();
		}
		if (p3.diskContains(point)) {
			selected = p3.getDisk();
		}
	}
	
	public void onMouseDrag(Location point) {
		if(selected != null) {
			double dx = point.getX() - here.getX();
			double dy = point.getY() - here.getY();
			selected.move(dx, dy);
			here = point;
		}
	}
	
	public void onMouseRelease(Location point) {
		if(selected != null) {
			if(p1.contains(point) && p1.addable(selected)) {
				p1.addDisk(selected);
			} else if(p2.contains(point) && p2.addable(selected)) {
				p2.addDisk(selected);
			} else if(p3.contains(point) && p3.addable(selected)) {
				p3.addDisk(selected);
			}
			else {
				selected.getPole().addDisk(selected);
			}
		}
		selected = null;
		if(winCondition()) {
			victoryScreen();
		}
	}
		
	public void undo() {
		
		Move lastMove = moves.peek();
		lastMove.getDisk().getPole().moveDisk(lastMove.getDisk(), lastMove.getPole());

		
	}
	
	public void save() {
		System.out.print("save");
		File saveFile = new File("towersofhanoi" + System.currentTimeMillis()+".txt");
		try {
			if (saveFile.createNewFile()) {
				
				FileWriter saveWriter = new FileWriter(saveFile);
				Stack<Move> movesReverse = new Stack<Move>();
				while(moves.size() > 0) {
					
					movesReverse.push(moves.pop());
					
				}
				while(movesReverse.size() > 0) {
					
					saveWriter.append(movesReverse.peek().getDisk().getSize() + "-" + movesReverse.peek().getPole().toString() + "\n");
					moves.push(movesReverse.pop());
					
				}
				saveWriter.append("\n");
				Stack<Disk> pole1 = new Stack<Disk>();
				String savePole1 = "";
				while (p1.getDisks().size() > 0) {
					
					Disk d = p1.getDisks().pop();
					pole1.push(d);
					savePole1 = d.getSize() + "\n" + savePole1;
					
				}
				while (pole1.size() > 0) {
					
					p1.getDisks().push(pole1.pop());
					
				}
				System.out.print(pole1.size());
				Stack<Disk> pole2 = new Stack<Disk>();
				String savePole2 = "Pole 2:\n";
				while (p2.getDisks().size() > 0) {
					
					pole2.push(p2.getDisks().pop());
					savePole2 = pole2.pop().getSize() + savePole2;
					
				}
				while (pole2.size() > 0) {
					
					p2.getDisks().push(pole2.pop());
					
				}
				System.out.print(pole2.size());
				Stack<Disk> pole3 = new Stack<Disk>();
				String savePole3 = "Pole 3:\n";
				while (p3.getDisks().size() > 0) {
					
					pole3.push(p3.getDisks().pop());
					savePole3 = pole3.pop().getSize() + savePole3;
					
				}
				while (pole3.size() > 0) {
					
					p3.getDisks().push(pole3.pop());
					
				}
				System.out.print(pole3.size());
				saveWriter.close();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String inButton(Location point) {
		
		if (saveButton.contains(point)) {
			
			return "save";
			
		} else if (undoButton.contains(point)) {
			
			return "undo";
			
		} else if (resetButton.contains(point)) {
			
			return "reset";
			
		} else if (autoplayButton.contains(point)) {
			
			return "autoplay";
			
		}
		
		return null;
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
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
	
	private boolean winCondition() {
		if(p3.numDisks() == numOfDisks) {
			return true;
		} else {
			return false;
		}
	}
	
	private void victoryScreen() {
		victory = new Text("You won!",400,100, canvas);
		victory.setFontSize(20);
		
		
	}
	
}
