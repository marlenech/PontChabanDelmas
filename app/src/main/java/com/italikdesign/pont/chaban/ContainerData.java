package com.italikdesign.pont.chaban;

import android.content.Context;


import android.support.v7.app.AppCompatActivity;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by italikdesign on 29/05/2016.
 */
public class ContainerData extends AppCompatActivity {

    static public Context context;


    public ContainerData() {


    }


    public static ArrayList<Feed> getFeeds() {


        // Class factory to get instance of sax
        SAXParserFactory fabrique = SAXParserFactory.newInstance();
        SAXParser parseur = null;
        ArrayList<Feed> feeds = null;
        try {
            // Factory an instance of SAXParser
            parseur = fabrique.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        // We define the URL of the XML file
        URL url = null;

        try {
            url = new URL(App.getContext().getString(R.string.address_xml));
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }


		/*
         * The handler will manage the XML file that is that it is he who will be responsible
         * Parsing of operations. this class is vera in detail below after .
		*/

        DefaultHandler handler = new ParserXMLHandler();


        try {
            //We parse the XML file
            parseur.parse(url.openConnection().getInputStream(), handler);


            //directly recovering the list of feeds
            feeds = ((ParserXMLHandler) handler).getData();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //It returns the array list
        return feeds;
    }


}