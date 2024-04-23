import objectdraw.*;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Clock;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JOptionPane;

/*
 * 
 * 2. If we were to have made a CLI option, many of the classes already made could have been reused but modified slightly. The pole class for example could be reused for the most part except for changing the methods which deal explicitly with objectdraw objects and convert them to graphics shown in text on the command line. 
 * The method most effected by this would be moveDisk, as it calls methods from objectdraw, but almost all the rest of the methods are reusable. The same goes for disk, where I would just need to change the constructor to produce a command line graphic and not a FilledRect. 
 * In the main class, much less can be salvaged as new methods need to be made to convert all of the mouse actions to typed inputs and the same would go for the buttons as well. THe save/read methods would be largely unchanged and so would the getDisk/getPole methods.
 * 3. The ABACABA pattern is a pattern that offers the best solution to the Towers of Hanoi game. If you were to convert the letters into the corresponding poles, you would find that if you take the pole from the top of the first pole (A) and place it on the pole 2 (B) and continue in the order of the pattern, you would solve the puzzle in the most efficient way.
 * 
 */

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
	private static String saveFile;
	private boolean autoplaying;

	
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
		p2 = new Pole(360, 250, 10, 350, 2, canvas);
		p3 = new Pole(600, 250, 10, 350, 3, canvas);
		
		if (saveFile == null) {
			numOfDisks = p1.createDisks(7);
			moves = new Stack<Move>();

		} else {
		
			read();
			
		}
		
		moveLabel = new Text("Number of moves: " + numOfMoves, 550, 150, canvas);
		moveLabel.setFontSize(15);
		moveLabel.setColor(Color.RED);
		
		requestFocus();
		addKeyListener(this);
		canvas.addKeyListener(this);
		
		numOfMoves = moves.size();


		
	}
	
public void read() {
		
		File save = new File(saveFile);
		if (save.exists()) {
			
			Scanner reader = null;
			reader = new Scanner(saveFile);
			reader.nextLine();
				
			String next = "";
			Stack<Integer> disks = new Stack<Integer>();
			Pole p = p1;
			while (!(next = reader.nextLine()).contains("Pole")) {
				disks.push(Integer.parseInt(next));

					
			}
			while (disks.size() > 0) {
				
				p.createDisk(Integer.parseInt(next));
				
			}
			p = p2;
			while (!(next = reader.nextLine()).contains("Pole")) {
				
				disks.push(Integer.parseInt(next));

					
			}
			while (disks.size() > 0) {
				
				p.createDisk(Integer.parseInt(next));
				
			}
			p = p3;
			while (!(next = reader.nextLine()).contains("Pole")) {
				disks.push(Integer.parseInt(next));

					
			}
			while (disks.size() > 0) {
				
				p.createDisk(Integer.parseInt(next));
				
			}
			Stack<Move> reverse = new Stack<Move>();
			while (reader.hasNextLine()) {
					
				next = reader.nextLine();
				int diskSize = next.charAt(0);
				int poleNum = next.charAt(2);
				Move m = new Move(getDisk(diskSize),getPole(poleNum));
				reverse.push(m);

					
			}
			while (reverse.size() > 0) {
					
				moves.push(reverse.pop());
					
			}
				
		} else {
			System.out.print("File doesn't exist");
			return;
		}
		
	}
	
	public Pole getPole(int i) {
		
		switch (i) {
		
		case 1:
			return p1;
		case 2:
			return p2;
		case 3:
			return p3;
		
		}
		return null;
		
	}
	
	
	public Disk getDisk(int size) {
		
		Stack<Disk> pole = new Stack<Disk>();
		Disk disk = null;
		while (p1.getDisks().size() > 0) {
			
			Disk d = p1.getDisks().pop();
			if (d.getSize() == size) {
				
				disk = d;
				
			}
			pole.push(d);
			
		}
		
		while (pole.size() > 0) {
			
			p1.getDisks().push(pole.pop());
			
		}
		if (disk != null) {
			
			return disk;
			
		}
		pole = new Stack<Disk>();
		while (p2.getDisks().size() > 0) {
			
			Disk d = p2.getDisks().pop();
			if (d.getSize() == size) {
				
				disk = d;
				
			}
			pole.push(d);
			
		}
		while (pole.size() > 0) {
			
			p2.getDisks().push(pole.pop());
			
		}
		if (disk != null) {
			
			return disk;
			
		}
		pole = new Stack<Disk>();
		while (p3.getDisks().size() > 0) {
			
			Disk d = p3.getDisks().pop();
			if (d.getSize() == size) {
				
				disk = d;
				
			}
			pole.push(d);
			
		}
		while (pole.size() > 0) {
			
			p3.getDisks().push(pole.pop());
			
		}
		return disk;
		
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
		

		if (args.length > 0) {
		
			saveFile = args[0];
			
		}	
			
			
		new TowersOfHanoi().startController(720, 1080);
		JOptionPane.showMessageDialog(null, "Towers of Hanoi, built by EA™");
			
		
		/*while() {
			//JOptionPane.showMessageDialog(null, "Please send 5 dollars to @Duncan-Wijnberg on Venmo");

		}*/
		
	}
	
	public void onMouseClick(Location point) {
		
		String button = inButton(point);
		if (button == null) {
			return;
		}
		switch (button) {
		
		case "auto":
			if (!autoPlaying) {
				autoPlaying = true;
				iterator.startPlaying();
			} else {
				autoPlaying = false;
				iterator.pause();
			}
		
		case "save":
			save();
			break;
		case "reset":
			p1.reset();
			p2.reset();
			p3.reset();
			p1.createDisks();
			numOfMoves = 0;
			moveLabel.setText("Number of moves: " + numOfMoves);
			if(victory != null) {
				victory.hide();
			}
			numOfMoves = 0;
			break;
		case "undo":
			System.out.print(moves.size());

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
		} else if(p2.diskContains(point)) {
			selected = p2.getDisk();
		} else if (p3.diskContains(point)) {
			selected = p3.getDisk();
		}
		else {
			selected = null;
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
			//System.out.println("Release:" + selected.getSize());
			double p1distance = point.distanceTo(new Location(p1.getPole().getX() + p1.getPole().getWidth()/2, p1.getPole().getY()));
			double p2distance = point.distanceTo(new Location(p2.getPole().getX() + p2.getPole().getWidth()/2, p2.getPole().getY()));
			double p3distance = point.distanceTo(new Location(p3.getPole().getX() + p3.getPole().getWidth()/2, p3.getPole().getY()));


			
			if(p1.contains(point) && p1.addable(selected)) {
				if (selected.getPole() != p1) {
					
					moves.add(new Move(selected, selected.getPole()));
					newMove();
					
				}
				selected.getPole().moveDisk(selected,p1);
				
			} else if (p2.contains(point) && p2.addable(selected)) {
				if (selected.getPole() != p2) {
					
					moves.add(new Move(selected, selected.getPole()));
					newMove();
					
				}
				selected.getPole().moveDisk(selected,p2);
				
			} else if (p3.contains(point) && p3.addable(selected)) {
				if (selected.getPole() != p3) {
					
					moves.add(new Move(selected, selected.getPole()));
					newMove();
					
				}
				selected.getPole().moveDisk(selected,p3);
				
			}
			else {
				selected.getPole().moveDisk(selected, selected.getPole());
			}
			
			
		}
		selected = null;
		if(winCondition()) {
			victoryScreen();
		}
	}
		
	public void undo() {
		
		Move lastMove = moves.pop();
		lastMove.getDisk().getPole().moveDisk(lastMove.getDisk(), lastMove.getPole());
		numOfMoves--;
		moveLabel.setText("Number of moves: " + numOfMoves);
		

		
	}
	
	public void save() {
		File saveFile = new File("towersofhanoi" + System.currentTimeMillis()+".txt");
		try {
			if (saveFile.createNewFile()) {
				
				FileWriter saveWriter = new FileWriter(saveFile);
				Stack<Move> movesReverse = new Stack<Move>();
				
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
				savePole1 = "Pole 1:\n" + savePole1;
				saveWriter.append(savePole1);
				Stack<Disk> pole2 = new Stack<Disk>();
				String savePole2 = "";
				while (p2.getDisks().size() > 0) {
					
					Disk d = p2.getDisks().pop();
					pole2.push(d);
					savePole2 = d.getSize() + "\n" + savePole2;
					
				}
				while (pole2.size() > 0) {
					
					p2.getDisks().push(pole2.pop());
					
				}
				savePole2 = "Pole 2:\n" + savePole2;
				saveWriter.append(savePole2);
				Stack<Disk> pole3 = new Stack<Disk>();
				String savePole3 = "";
				while (p3.getDisks().size() > 0) {
					
					Disk d = p3.getDisks().pop();
					pole3.push(d);
					savePole3 = d.getSize() + "\n" + savePole3;
					
				}
				while (pole3.size() > 0) {
					
					p3.getDisks().push(pole3.pop());
					
				}
				savePole3 = "Pole 3:\n" + savePole3;
				saveWriter.append(savePole3);
				saveWriter.append("\n");
				while(moves.size() > 0) {
					
					movesReverse.push(moves.pop());
					
				}
				while(movesReverse.size() > 0) {
					
					saveWriter.append(movesReverse.peek().getDisk().getSize() + "-" + movesReverse.peek().getPole().toString() + "\n");
					moves.push(movesReverse.pop());
					
				}
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
