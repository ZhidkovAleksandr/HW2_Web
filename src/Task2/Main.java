package Task2;

import Links.FileLinks;
import Task2.entity.Flower;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XMLStreamException {
        List<Flower> flowers = new ArrayList<>();
        Flower flowerD = createDOMFlower();
        Flower flowerSax = createSAX_Flower();
        Flower flowerSt = createStaxFlower();
        flowers.add(flowerD);
        flowers.add(flowerSax);
        flowers.add(flowerSt);
        for (Flower fl:flowers) {
            System.out.println(fl);
        }


    }

    public static Flower createStaxFlower() throws XMLStreamException, FileNotFoundException {
        ParserStax parserStax = new ParserStax();
        Flower flower = parserStax.createSTAXFlower();
        return flower;

    }

    public static Flower createDOMFlower() throws ParserConfigurationException, IOException, SAXException {
        FlDomHandler domHandler = new FlDomHandler();
        Flower flowerD = domHandler.readCreateFlower();
        return flowerD;
    }

    public static Flower createSAX_Flower() throws ParserConfigurationException, SAXException, IOException{

        Flower flower;

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        FlSaxHandler fh = new FlSaxHandler();

        File file = new File(FileLinks.tulipPath);

        parser.parse(file, fh);

        flower = fh.getFlower();
        return flower;
    }

}
