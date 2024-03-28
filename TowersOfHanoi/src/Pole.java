import java.awt.Color;

import objectdraw.*;

public class Pole {
	
	FilledRect pole;
	//
	public Pole(double x, double y, double wif, double lenf, DrawingCanvas canvas) {
		
		pole = new FilledRect(x,y,wif,lenf,canvas);
		pole.setColor(Color.black);
		
	}

}
