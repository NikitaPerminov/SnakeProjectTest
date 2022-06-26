import java.awt.Color;
import java.awt.Graphics;

public class GameBody {
	
	private int aCord, bCord, width, heigth;
	
	public GameBody(int aCord, int bCord, int tileSize) {
		this.aCord = aCord;
		this.bCord = bCord;
		width = tileSize;
		heigth = tileSize;
	}
	
	public void tick() {
		
	}
	public void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(aCord * width , bCord * heigth , width, heigth);
	}

	public int getaCord() {
		return aCord;
	}

	public void setaCord(int aCord) {
		this.aCord = aCord;
	}

	public int getbCord() {
		return bCord;
	}

	public void setbCord(int bCord) {
		this.bCord = bCord;
	}
	
}
