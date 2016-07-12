package rover;

public class GroundCell {

    private CellState state = CellState.FREE;
    private int x;
    private int y;

    public GroundCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }
}
