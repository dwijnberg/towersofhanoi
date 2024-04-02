import objectdraw.*;

public class TowersOfHanoi extends WindowController {
	
	private FramedRect saveButton;
	private FramedRect undoButton;
	private FramedRect autoplayButton;
	private FramedRect resetButton;
	private Text saveText;
	private Text undoText;
	private Text autoplayText;
	private Text resetText;
	

	public void begin() {
		
		Pole p1 = new Pole(120, 250, 10, 350, canvas);
		p1.createDisks();
		Pole p2 = new Pole(360, 250, 10, 350, canvas);
		Pole p3 = new Pole(600, 250, 10, 350, canvas);
		int numOfMoves = 0;
		
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
		
		


		
	}
	
	
	public static void main(String[] args) {
		
		new TowersOfHanoi().startController(720, 1080);
		
	}
	
}
