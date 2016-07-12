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
public class MoveCommand implements RoverCommand {

    private Moveable rover;
    private int x;
    private int y;

    public MoveCommand(Moveable rover, StringTokenizer token) {
        this.rover = rover;
        if (token.countTokens() != 3) {
            throw new AssertionError();
        }
        token.nextToken();
        x = Integer.parseInt(token.nextToken());
        y = Integer.parseInt(token.nextToken());
    }

    @Override
    public void execute() {
        rover.move(x, y);
    }

}
