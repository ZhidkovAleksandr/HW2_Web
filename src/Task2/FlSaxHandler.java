package Task2;

import Task2.Enums.Multiplying;
import Task2.Enums.Soil;
import Task2.entity.Flower;
import Task2.entity.GrowTips;
import Task2.entity.VisualParam;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FlSaxHandler extends DefaultHandler {
    private Flower flower;
    private VisualParam visualParam;
    private GrowTips growTips;
    private String currentQName;
    private int countEl;
    public FlSaxHandler(){
        this.flower = new Flower();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //System.out.println("Start element: " + qName);
        currentQName = qName;


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //System.out.println("End element: " + qName);
        currentQName = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length);
        //System.out.println("Characters: " + value);
        switch (currentQName) {
            case "name" :
                flower.setName(value);
                break;
            case "soil" :
                Soil soilType = Soil.valueOf(value.toUpperCase());
                flower.setSoil(soilType);
                break;
            case "origin" :
                flower.setOrigin(value);
                break;
            case "visual_param" :
                visualParam = new VisualParam();
                flower.setVisualParam(visualParam);
                break;
            case "stem_color" :
                visualParam.setStemColor(value);
                break;
            case "leaf_color" :
                visualParam.setLeafColor(value);
                break;
            case "avg_size" :
                visualParam.setAvgSize(value);
                break;
            case "grow_tips" :
                growTips = new GrowTips();
                flower.setGrowTips(growTips);
                break;
            case "temp" :
                growTips.setTemp(Float.parseFloat(value));
                break;
            case "lighting" :
                growTips.setLighting(Boolean.parseBoolean(value));
                break;
            case "watering" :
                growTips.setWatering(Float.parseFloat(value));
                break;
            case "multiplying" :
                Multiplying multiplying = Multiplying.valueOf(value.toUpperCase());
                flower.setMultiplying(multiplying);
                break;
        }
    }

    public Flower getFlower(){
        return flower;
    }
}
