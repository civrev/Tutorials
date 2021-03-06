import java.awt.Color;
import java.awt.Polygon;

public class Paddle extends Polygon {
	
	private int x;
	private int y;
	private int length;
	private int width;
	private int player; //1 is left side, 2 is right side
	private int board_size;
	private Color player_color;
	private boolean isComputer;
	private int difficulty;

	public Paddle(int player, int board_size, Color color) {
		
		this.player = player;
		this.player_color = color;
		this.isComputer = false;
		this.board_size = board_size;
		
		if(player==1) {
			x = board_size/16;
			y = board_size/2;
		}else {
			x = board_size - board_size/16;
			y = board_size/2;
		}
		
		length = board_size/10;
		width = board_size/100;
		
		this.addPoint(x, y);
		this.addPoint(x, y+length);
		this.addPoint(x-width, y+length);
		this.addPoint(x-width, y);
	}
	
	public Ball collision(Ball ball){
		
		int direction = ball.getDirection()%360;
		
		if(player==1) {
			
			if(direction <=180) {
				//in quadrant three
				ball.setDirection(90-(direction-90));
			}else {
				//in quadrant two
				ball.setDirection(270+(270-direction));
			}
			
			
		}else { //must be player 2
			
			if(direction >=270) {
				//in quadrant one
				ball.setDirection(270-(direction-270));
			}else {
				//in quadrant four
				ball.setDirection(90+(90-direction));
			}
			
		}
		
		return ball;
	}
	
public void computerMove(Ball ball) {
		
		int bottom = ball.y + ball.getSize();
		int randomNum = (int) (Math.random()*100);
		int increment = board_size/60;
		
		//computer can play the game too
		if(isComputer) {
			if(50+difficulty*5>=randomNum) {
				if(bottom > y+length/2) { //move up towards ball
					setY(y + increment);
				}else { // move down towards ball
					setY(y - increment);
				}
			}else { //if fail random number check, do the opposite of intended move
				if(bottom > y+length/2) { //move up towards ball
					setY(y - increment);
				}else {
					setY(y + increment);
				}
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		this.ypoints = new int[]{y,y+length, y+length, y}; //using setY() will now move the paddle
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public Color getPlayer_color() {
		return player_color;
	}

	public void setPlayer_color(Color player_color) {
		this.player_color = player_color;
	}

	public boolean isComputer() {
		return isComputer;
	}

	public void setComputer(boolean isComputer) {
		this.isComputer = isComputer;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	

}
