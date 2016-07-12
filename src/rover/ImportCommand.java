/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rover;

import java.text.ParseException;

/**
 *
 * @author Alexander
 */
public class ImportCommand extends RoverCommandParser implements RoverCommand {

    public ImportCommand(Rover rover, String fileName) throws ParseException {
        super(rover, fileName);
    }

    //Adds the graphic to the composition.
    public void add(RoverCommand command) {
        commands.add(command);
    }

    //Removes the graphic from the composition.
    public void remove(RoverCommand command) {
        commands.remove(command);
    }

    @Override
    public void execute() {
        while (!commands.isEmpty()) {
            LoggingCommand logCom = new LoggingCommand(commands.poll());
            logCom.execute();
        }

    }
}
