package rover;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rover implements Moveable, Turnable {

    private Direction direction;
    private int x;
    private int y;
    private GroundVisor visor;

    public Rover() {
        visor = new GroundVisor();
    }

    @Override
    public void turnTo(Direction d) {
        this.direction = d;
        System.out.println("Current direction: " + direction);
    }

    private boolean isXPossible(int x) {
        if (x < 0) {
            return false;
        }
        if (x > visor.getGround().getLength() - 1) {
            return false;
        }
        return true;
    }

    private boolean isYPossible(int y) {
        if (y < 0) {
            return false;
        }
        if (y > visor.getGround().getWidth() - 1) {
            return false;
        }
        return true;
    }

    @Override
    public void move(int x, int y) {
        if (!(isXPossible(x) && isYPossible(y))) {
            throw new IndexOutOfBoundsException();
        }
        this.x = x;
        this.y = y;
        System.out.println("Current position: " + this.x + " " + this.y);
    }

    public static void main(String[] args) {
        Rover r = new Rover();
        r.getVisor().setGround(new Ground(100, 100));
        r.move(9, 9);
        r.getVisor().hasObstacles(9, 9);
        TextRoverCommandParser parser;
        try {
            parser = new TextRoverCommandParser(r, "commands.txt");
            RoverCommand command = null;
            while ((command = parser.readNextCommand()) != null) {
                LoggingCommand logCom = new LoggingCommand(command);
                logCom.execute();
            }
        } catch (ParseException | RecursiveRunException ex) {
            Logger.getLogger(Rover.class.getName()).log(Level.SEVERE, null, ex);
        }
        XMLRoverCommandParser parserXML;
        try {
            parserXML = new XMLRoverCommandParser(r, "commands.xml");
            RoverCommand commandXML = null;
            while ((commandXML = parserXML.readNextCommand()) != null) {
                LoggingCommand logCom = new LoggingCommand(commandXML);
                logCom.execute();
            }
        } catch (ParseException | RecursiveRunException ex) {
            Logger.getLogger(Rover.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public GroundVisor getVisor() {
        return visor;
    }

    public void setVisor(GroundVisor visor) {
        this.visor = visor;
    }
}
