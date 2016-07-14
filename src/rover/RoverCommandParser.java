/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rover;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Alexander
 */
public abstract class RoverCommandParser {

    protected Rover rover;
    protected Queue<RoverCommand> commands = new LinkedList<RoverCommand>();
    private Stack<String> filesToBeRun = new Stack<String>();

    public void addFileToRun(String fileName) throws RecursiveRunException{
        if(filesToBeRun.contains(fileName)){
            throw new RecursiveRunException();
        }
        filesToBeRun.add(fileName);
    }
    
    public String popTheFile(){
        return filesToBeRun.pop();
    }
    
    public RoverCommandParser(Rover rover, String fileName) throws ParseException, RecursiveRunException {
        try {
            if (!isFileCorrect(fileName)) {
                throw new ParseException("Wrong symbol", 0);
            }
            this.rover = rover;
            if(filesToBeRun.contains(fileName))
                throw new RecursiveRunException();
            filesToBeRun.add(fileName);
            fulfilTheQueue(fileName);
            filesToBeRun.pop();
        } catch (StackOverflowError er) {
            System.out.println("ERROR. Recursive run of files");
        }
    }

    abstract void fulfilTheQueue(String fileName) throws ParseException,RecursiveRunException;

    abstract boolean isFileCorrect(String fileName);

    public RoverCommand readNextCommand() {
        return commands.poll();
    }
}
