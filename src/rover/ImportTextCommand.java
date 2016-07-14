/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander
 */
public class ImportTextCommand extends ImportCommand {

    public ImportTextCommand(Rover rover, String fileName, RoverCommandParser parser) throws ParseException, RecursiveRunException {
        super(rover, fileName,parser);
    }
  
    @Override
    protected void fulfilTheQueue(String fileName) throws ParseException,RecursiveRunException{
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            String currentString = "";
            while ((currentString = fileReader.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(currentString, " ");
                switch (token.countTokens()) {
                    case 1:
                        ImportTextCommand importCommand = new ImportTextCommand(rover, token.nextToken(),parser);
                        commands.add(importCommand);
                        break;
                    case 2:
                        TurnCommand turnCommand = new TurnCommand(rover, token);
                        commands.add(turnCommand);
                        break;
                    case 3:
                        MoveCommand moveCommand = new MoveCommand(rover, token);
                        commands.add(moveCommand);
                        break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TextRoverCommandParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
