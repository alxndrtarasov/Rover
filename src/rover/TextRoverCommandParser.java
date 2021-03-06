/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rover;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parsable file should contain only rows like: move
 * <number from 000000000 to 999999999> <number from 000000000 to 999999999>
 * OR turn < EAST or WEST or NORTH or SOUTH >
 * OR
 * < file path >
 *
 * @author Alexander
 */
public class TextRoverCommandParser extends RoverCommandParser {

    public TextRoverCommandParser(Rover rover, String fileName) throws ParseException, RecursiveRunException {
        super(rover, fileName);
    }

    @Override
    protected void fulfilTheQueue(String fileName) throws ParseException,RecursiveRunException {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            
            String currentString = "";
            
            while ((currentString = fileReader.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(currentString, " ");
                switch (token.countTokens()) {
                    case 1:
                        ImportTextCommand importCommand = new ImportTextCommand(rover, token.nextToken(),this);
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

    protected boolean isFileCorrect(String fileName) {
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(fileName));
            
            Pattern p = Pattern.compile("(move [0-9]{1,6} [0-9]{1,6})|(turn ((EAST)|(WEST)|(SOUTH)|(NORTH)))");
            
            String currentString = "";
            while ((currentString = fileReader.readLine()) != null) {
                Matcher m = p.matcher(currentString);
                if (!m.matches()) {
                    if (!isFileCorrect(currentString)) {
                        return false;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File " + fileName + " does not exist");
            return false;
        } catch (Exception ex) {
            Logger.getLogger(TextRoverCommandParser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                fileReader.close();
            } catch (IOException ex) {
                Logger.getLogger(TextRoverCommandParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
}
