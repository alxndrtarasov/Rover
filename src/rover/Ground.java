package rover;

public class Ground {
	private GroundCell[][] landscape;
	private int length;
	private int width;
	
	public Ground(int length,int width){
		this.length=length;
		this.width=width;
		landscape = new GroundCell[this.length][this.width];
		for(int i =0;i<this.length;i++)
			for(int j=0;j<this.width;j++)
				landscape[i][j]=new GroundCell(i,j);
	}
	
	Ground(){
		this(100,100);
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
	public GroundCell[][] getLandscape() {
		return landscape;
	}
	public void setLandscape(GroundCell[][] landscape) {
		this.landscape = landscape;
	}
}
