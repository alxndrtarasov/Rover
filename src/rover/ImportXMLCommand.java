/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rover;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Alexander
 */
public class ImportXMLCommand extends ImportCommand {

    ImportXMLCommand(Rover rover, String fileName, RoverCommandParser parser) throws ParseException, RecursiveRunException {
        super(rover, fileName, parser);
    }

    @Override
    protected void fulfilTheQueue(String fileName) throws ParseException, RecursiveRunException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fileName);
            Element rootElem = document.getDocumentElement();
            for (int i = 0; i < rootElem.getChildNodes().getLength(); i++) {
                Node currentNode = rootElem.getChildNodes().item(i);
                if (currentNode instanceof Element) {
                    switch (((Element) currentNode).getNodeName()) {
                        case "move":
                            MoveCommand moveCommand = new MoveCommand(rover, Integer.parseInt(((Element) currentNode).getAttribute("x")), Integer.parseInt(((Element) currentNode).getAttribute("y")));
                            commands.add(moveCommand);
                            break;
                        case "turn":
                            TurnCommand turnCommand = new TurnCommand(rover, Direction.valueOf(((Element) currentNode).getAttribute("direction")));
                            commands.add(turnCommand);
                            break;
                        case "import":
                            ImportXMLCommand importCommand = new ImportXMLCommand(rover, ((Element) currentNode).getAttribute("src"), parser);
                            commands.add(importCommand);
                            break;
                    }
                }
            }
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(XMLRoverCommandParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
