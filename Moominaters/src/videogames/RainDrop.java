package videogames;


public class RainDrop{
	private int x,y, size, ang;
	
	public RainDrop(int size, int x, int ang, int y){
	super();
	this.x = x;
	this.y = y;
	this.size = size;
	this.ang = ang;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setX(int x){
		this.x = x;
	}
	public int getY(){
		return y;
	}
	public int getX(){
		return x;
	}
	public int getSize(){
		return size;
	}
	public int getAng(){
		return ang;
	}
}
