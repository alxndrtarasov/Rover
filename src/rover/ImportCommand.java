/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rover;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Alexander
 */
abstract class ImportCommand implements RoverCommand{
    protected RoverCommandParser parser;
    protected Rover rover;
    protected Queue commands = new LinkedList<RoverCommand>();

    public ImportCommand(Rover rover, String fileName, RoverCommandParser parser) throws ParseException, RecursiveRunException {
        parser.addFileToRun(fileName);
        this.rover = rover;
        this.parser=parser;
        fulfilTheQueue(fileName);
        parser.popTheFile();
    }

    abstract void fulfilTheQueue (String fileName)throws ParseException,RecursiveRunException;
    //To change body of generated methods, choose Tools | Templates.

    public void add(RoverCommand command) {
        commands.add(command);
    }

    public void remove(RoverCommand command) {
        commands.remove(command);
    }

    @Override
    public void execute() {
        while (!commands.isEmpty()) {
            LoggingCommand logCom = new LoggingCommand((RoverCommand) commands.poll());
            logCom.execute();
        }

    }
}
