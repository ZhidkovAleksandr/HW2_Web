package Task2;

import Links.FileLinks;
import Task2.Enums.Multiplying;
import Task2.Enums.Soil;
import Task2.entity.Flower;
import Task2.entity.GrowTips;
import Task2.entity.VisualParam;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ParserStax {

    //private Flower flower;

    XMLInputFactory factory;
    XMLEventReader eventReader;

    public ParserStax() {

        this.factory = factory = XMLInputFactory.newFactory();

    }

    public Flower createSTAXFlower() throws FileNotFoundException, XMLStreamException {
        Flower flower = new Flower();

        XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(FileLinks.chamomilePath));

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (startElement.getName().getLocalPart().equals("name")) {
                    event = eventReader.nextEvent();
                    flower.setName(event.asCharacters().getData());
                } else if (startElement.getName().getLocalPart().equals("soil")) {
                    event = eventReader.nextEvent();
                    flower.setSoil(Soil.valueOf(event.asCharacters().getData().toUpperCase()));
                } else if ((startElement.getName().getLocalPart().equals("origin"))) {
                    event = eventReader.nextEvent();
                    flower.setOrigin(event.asCharacters().getData());
                } else if (startElement.getName().getLocalPart().equals("visual_param")) {
                    VisualParam visualParam = new VisualParam();
                    while (true) {
                        event = eventReader.nextEvent();
                        if (event.isStartElement()) {
                            startElement = event.asStartElement();
                            if (startElement.getName().getLocalPart().equals("stem_color")) {
                                event = eventReader.nextEvent();
                                visualParam.setStemColor(event.asCharacters().getData());
                            } else if (startElement.getName().getLocalPart().equals("leaf_color")) {
                                event = eventReader.nextEvent();
                                visualParam.setLeafColor(event.asCharacters().getData());
                            } else if (startElement.getName().getLocalPart().equals("avg_size")) {
                                event = eventReader.nextEvent();
                                visualParam.setAvgSize(event.asCharacters().getData());
                                flower.setVisualParam(visualParam);
                                break;
                            }
                        }
                    }
                } else if (startElement.getName().getLocalPart().equals("grow_tips")) {
                    GrowTips growTips = new GrowTips();
                    while (true) {
                        event = eventReader.nextEvent();
                        if (event.isStartElement()) {
                            startElement = event.asStartElement();
                            if (startElement.getName().getLocalPart().equals("temp")) {
                                event = eventReader.nextEvent();
                                growTips.setTemp(Float.parseFloat(event.asCharacters().getData()));
                            } else if (startElement.getName().getLocalPart().equals("lighting")) {
                                event = eventReader.nextEvent();
                                growTips.setLighting(Boolean.parseBoolean(event.asCharacters().getData()));
                            } else if (startElement.getName().getLocalPart().equals("watering")) {
                                event = eventReader.nextEvent();
                                growTips.setWatering(Float.parseFloat(event.asCharacters().getData()));
                                flower.setGrowTips(growTips);
                                break;
                            }
                        }
                    }
                } else if (startElement.getName().getLocalPart().equals("multiplying")) {
                    event = eventReader.nextEvent();
                    flower.setMultiplying(Multiplying.valueOf(event.asCharacters().getData().toUpperCase()));
                }

            }




        }

        return flower;

    }
}
