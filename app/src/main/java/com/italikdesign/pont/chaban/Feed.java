package com.italikdesign.pont.chaban;

import android.content.pm.ResolveInfo;


/**
 * Created by italikdesign on 29/05/2016.
 */
public class Feed {
    private long id;

    private String jour;

    private String date;
    private String annee;
    private String sens;
    private String bateaux;
    private String heure1;
    private String heure2;
    private String motif;
    private String heurepassage;


    public Feed() {
        super();
        this.id = id;

        this.jour = jour;
        this.date = date;
        this.annee = annee;
        this.sens = sens;
        this.bateaux = bateaux;
        this.heure1 = heure1;
        this.sens = sens;
        this.bateaux = bateaux;
        this.heure1 = heure1;
        this.heure2 = heure2;
        this.motif = motif;
        this.heurepassage = heurepassage;

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


    public String getJour() {
        return jour;
    }
    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getAnnee() {
        return annee;
    }
    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getSens() {
        return sens;
    }
    public void setSens(String sens) {
        this.sens = sens;
    }

    public String getBateaux() {
        return bateaux;
    }
    public void setBateaux(String bateaux) {
        this.bateaux = bateaux;
    }

    public String getHeure1() {
        return heure1;
    }
    public void setHeure1(String heure1) {
        this.heure1 = heure1;
    }

    public String getHeure2() {
        return heure2;
    }
    public void setHeure2(String heure2) {
        this.heure2 = heure2;
    }

    public String getMotif() {
        return motif;
    }
    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getHeurepassage() {
        return heurepassage;
    }
    public void setHeurepassage(String heurepassage) {
        this.heurepassage = heurepassage;
    }



}
