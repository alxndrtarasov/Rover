package rover;

public class GroundVisor {
	private Ground ground;
	
        public GroundVisor(){
		ground = new Ground();
	}
	
	public boolean hasObstacles(int x,int y){
		try{
		if(ground.getLandscape()[x][y].getState()==CellState.FREE){
			System.out.printf("Ground x=%d y=%d has no obstacles\n",x,y);
			return false;
		}
		System.out.printf("Ground x=%d y=%d has obstacles\n",x,y);
		return true;
		}catch(Exception ae){
			throw new GroundVisorException(ae);
		}
	}

	public Ground getGround() {
		return ground;
	}

	public void setGround(Ground ground) {
		this.ground = ground;
	}
}
