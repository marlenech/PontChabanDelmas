package com.italikdesign.pont.chaban;

import android.content.pm.ResolveInfo;


/**
 * Created by italikdesign on 29/05/2016.
 */
public class Feed {
    private long id;

    private String title;

    private String description;
    private String heure;


    public Feed() {
        super();
        this.id = id;

        this.title = title;
        this.description = description;
        this.heure = heure;

    }



    public Feed(MainFragment mainFragment, ResolveInfo ri) {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
