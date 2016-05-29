package com.italikdesign.pont.chaban;

import android.graphics.Bitmap;

/**
 * Created by italikdesign on 29/05/2016.
 */
public class Feed {
    private long id;
    private String imageURL;
    private Bitmap imageBitmap;
    private String title;

    private String description;
    private String heure;


    public Feed(long id, String imageURL, String title,  String description, String heure) {
        super();
        this.id = id;
        this.imageURL = imageURL;
        this.title = title;
        this.description = description;
        this.heure = heure;

    }



    public Feed() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public Bitmap getImageBitmap() {
        return imageBitmap;
    }
    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeure() {
        return heure;
    }
    public void setHeure(String heure) {
        this.heure = heure;
    }



}
