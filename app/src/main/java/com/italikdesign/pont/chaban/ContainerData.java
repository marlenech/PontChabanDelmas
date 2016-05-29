package com.italikdesign.pont.chaban;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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






    public static ArrayList<Feed> getFeeds(){

        // Class factory to get instance of sax
        SAXParserFactory fabrique = SAXParserFactory.newInstance();
        SAXParser parseur = null;
        ArrayList<Feed> feeds = null;
        try {
            // On "fabrique" une instance de SAXParser
            parseur = fabrique.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        // On d�fini l'url du fichier XML
        URL url = null;

        try {
            url = new URL (App.getContext().getString(R.string.address_xml));
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

		/*
		 * Le handler sera gestionnaire du fichier XML c'est � dire que c'est lui qui sera charg�
		 * des op�rations de parsing. On vera cette classe en d�tails ci apr�s.
		*/
        DefaultHandler handler = new ParserXMLHandler();
        try {
            // On parse le fichier XML
            parseur.parse(url.openConnection().getInputStream(), handler);

            // On r�cup�re directement la liste des feeds
            feeds = ((ParserXMLHandler) handler).getData();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Feed feed : feeds) {
            String imageURL = feed.getImageURL();
            Bitmap bitmap = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                bitmap = BitmapFactory
                        .decodeStream(new
                                        URL(imageURL).openStream(),
                                null, bmOptions);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            feed.setImageBitmap(bitmap);
        }

        // On la retourne l'array list
        return feeds;
    }




}




