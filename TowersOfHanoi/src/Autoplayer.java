import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Autoplayer {
	private Queue<String[]> key;
	private Pole left;
	private Pole middle;
	private Pole right;
	private History memory;
	private AutoplayerMoves graphics;
	private boolean best = true;
	private int flag = 0;
	
	public Autoplayer(Pole lp, Pole mp, Pole rp, History mem) {
		key = new LinkedList<String[]>();
		left = lp;
		middle = mp;
		right = rp;
		memory = mem;

		figureItOut(lp.numDisks(), "l", "r");
		while (rp.numDisks()>0) {
			mp.addDisk(rp.popTop());
		}
		while (mp.numDisks()>0) {
			lp.addDisk(mp.popTop());
		}
	}
	
	private String returnMid(String source, String dest) {
		if (source.equals("l")&&dest.equals("m")) {
			return "r";
		}
		else if (source.equals("l")&&dest.equals("r")) {
			return "m";
		}
		else if (source.equals("m")&&dest.equals("r")) {
			return "l";
		}
		else if (source.equals("m")&&dest.equals("l")) {
			return "r";
		}
		else if (source.equals("r")&&dest.equals("l")) {
			return "m";
		}
		else return "l";
	}
	
	private void figureItOut(int num, String source, String dest) {
		if (num == 1) {
			move(source, dest);
		}
		else {
			String mid = returnMid(source, dest);
			figureItOut(num-1, source, mid);
			move(source, dest);
			figureItOut(num-1, mid, dest);
		}
	}
	
	private void move(String s, String d) {
		toPile(d).addDisk(toPile(s).popTop());
		String[] temp = {s, d};
		key.add(temp);
	}
	
	private Pole toPile(String s) {
		switch (s) {
		case "l":
			return left;
		case "m":
			return middle;
		case "r":
			return right;
		}
		return null;
	}
	
	public void setFlag() {
		flag = memory.getHistory().size();
	}
	
	public void remove() {
		key.remove();
	}
	
	public void startPlaying() {
		if (!best) {
			int temp = key.size();
			Stack<String[]> tempStack = new Stack<String[]>();
			while (memory.getHistory().size()>flag) {
				String[] command = memory.getHistory().pop();
				String[] reverse = {command[1], command[0]};
				key.add(reverse);
				tempStack.push(command);
			}
			
			while (tempStack.size()>0) {
				memory.getHistory().push(tempStack.pop());
			}
			
			for (int i = 0; i < temp; i++) {
				key.add(key.remove());
			}
		}
		graphics = new AutoplayerMoves(memory, key, 1000, false, this);
	}
	
	public void pause() {
		if (graphics != null) {
			graphics.pause();
		}
	}
	
	public Queue<String[]> getKey() {
		return key;
	}

}