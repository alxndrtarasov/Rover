/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rover;

import java.util.StringTokenizer;

/**
 *
 * @author Alexander
 */
public class TurnCommand implements RoverCommand {

    private Turnable rover;
    private Direction direction;

    public TurnCommand(Turnable rover, StringTokenizer token) {
        if (token.countTokens() != 2) {
            throw new AssertionError();
        }
        token.nextToken();
        direction = Direction.valueOf(token.nextToken());
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.turnTo(direction);
    }

}
