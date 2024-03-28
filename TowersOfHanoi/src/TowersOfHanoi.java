import objectdraw.FilledRect;
import objectdraw.WindowController;

public class TowersOfHanoi extends WindowController {

	public void begin() {
		
		Pole p1 = new Pole(120, 150, 10, 400, canvas);
		
	}
	
	
	public static void main(String[] args) {
		
		new TowersOfHanoi().startController(720, 1080);
		
	}
	
}
