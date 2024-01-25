import Links.FileLinks;
import Task4.entity.Candy;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try{
            readCandy();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void readCandy() throws FileNotFoundException, XMLStreamException {

        List<Candy> candies = new ArrayList<>();
        Candy candy;
        candy = null;

        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(FileLinks.candyPath));

        while (eventReader.hasNext()){
            XMLEvent event = eventReader.nextEvent();
            if(event.isStartElement()){
                StartElement startElement = event.asStartElement();
                String elName = startElement.getName().getLocalPart();
                if(startElement.getName().getLocalPart().equals("candy")){
                  candy = new Candy();

                }

                switch (elName){
                    case "name" :
                        event = eventReader.nextEvent();
                        candy.setName(event.asCharacters().getData());
                        break;
                    case "taste" :
                        event = eventReader.nextEvent();
                        candy.setTaste(event.asCharacters().getData());
                        break;
                    case "color" :
                        event = eventReader.nextEvent();
                        candy.setColor(event.asCharacters().getData());
                }
            }
            if(event.isEndElement()){
                EndElement endElement = event.asEndElement();
                if(endElement.getName().getLocalPart().equals("candy")){
                   candies.add(candy);
                }
            }
        }

        for (Candy c:candies) {
            System.out.println(c);
        }

    }

}