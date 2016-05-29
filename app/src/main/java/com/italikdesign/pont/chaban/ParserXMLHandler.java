package com.italikdesign.pont.chaban;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by italikdesign on 29/05/2016.
 */
public class ParserXMLHandler extends DefaultHandler {

    // name of tags XML
    private final String ITEM = "item";
    private final String IMAGE = "image-url";
    private final String TITLE = "title";
    private final String DESCRIPTION = "description";
    private final String HEURE = "heure";


    // Array list of feeds
    private ArrayList<Feed> feeds;

    // Boolean on whether we are inside of an item
    private boolean inItem;

    // Feed current
    private Feed currentFeed;

    // Buffer on whether contain the data of tag XML
    private StringBuffer buffer;

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        super.processingInstruction(target, data);
    }

    public ParserXMLHandler() {
        super();
    }


    // * Method is called once
    // * when starting of analyse of flux xml.


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        feeds = new ArrayList<Feed>();

    }

    /*
     * Function starting when parser find a tag XML
     * New instance of feed
     */
    @Override
    public void startElement(String uri, String localName, String name,	Attributes attributes) throws SAXException {
       // We reset the buffer every time it encounters an item
        buffer = new StringBuffer();

        // localName contain the name of tag encounter

        // Encounters a tag ITEM, must instance a new feed
        if (localName.equalsIgnoreCase(ITEM)){
            this.currentFeed = new Feed();
            inItem = true;
        }
        // action
        if (localName.equalsIgnoreCase(IMAGE)){
            // Nothing to do
        }

        if (localName.equalsIgnoreCase(TITLE)){
            // Nothing to do
        }

        if(localName.equalsIgnoreCase(DESCRIPTION)){
            // Nothing to do
        }

        if(localName.equalsIgnoreCase(HEURE)){
            // Nothing to do
        }

    }



    @Override
    public void endElement(String uri, String localName, String name) throws SAXException {

        if (localName.equalsIgnoreCase(IMAGE)){
            if(inItem){
               // The characters are in the buffer object
                this.currentFeed.setImageURL(buffer.toString());
                buffer = null;
            }

        }

        if (localName.equalsIgnoreCase(TITLE)){
            if(inItem){
                // The characters are in the buffer object
                this.currentFeed.setTitle(buffer.toString());
                buffer = null;
            }
        }


        if(localName.equalsIgnoreCase(DESCRIPTION)){
            if(inItem){
                this.currentFeed.setDescription(buffer.toString());
                buffer = null;
            }
        }

        if(localName.equalsIgnoreCase(HEURE)){
            if(inItem){
                this.currentFeed.setHeure(buffer.toString());
                buffer = null;
            }
        }

        if (localName.equalsIgnoreCase(ITEM)){
            feeds.add(currentFeed);
            inItem = false;
        }

    }



    public void characters(char[] ch,int start, int length)	throws SAXException{
        String lecture = new String(ch,start,length);
        if(buffer != null) buffer.append(lecture);
    }


    // method for get data
    public ArrayList<Feed> getData(){
        return feeds;
    }
}

