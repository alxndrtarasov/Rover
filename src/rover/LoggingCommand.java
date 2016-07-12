/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rover;

import java.util.logging.Logger;

/**
 *
 * @author Alexander
 */
public class LoggingCommand implements RoverCommand {
    private static Logger log = Logger.getLogger(LoggingCommand.class.getName());
    RoverCommand command;

    public LoggingCommand(RoverCommand command) {
        this.command = command;
    }

    public void log(){
        log.info(command.getClass().getSimpleName()+" to be executed");
    }
    
    @Override
    public void execute() {
        log();
        command.execute();
    }

}
