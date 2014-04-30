package com.example.bd30jours_git;

/**
 * Created by Dimitri on 05/03/14.
 */
public class PreviewALaCon {

    private double rating;

    public String getTitre() {
        return titre;
    }
    public String getUrlPreview()
    {
        return this.urlPreview;
    }

    public String getUrlLecture()
    {
        return this.urlLecture;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public Type getType() {
        return type;
    }
    public int getId(){return id;};

    public void setType(Type type) {
        this.type = type;
    }

    private String titre;
    private String description;
    private Auteur auteur;
    private Type type;
    private int id;
    private String urlPreview;
    private String urlLecture;
    public PreviewALaCon(double rate, String title, String descri, Auteur aut, Type typ, int idd, String urlPreview, String urlLecture)
    {
        rating=rate;
        titre=title;
        description=descri;
        auteur=aut;
        type=typ ;
        id =idd;
        this.urlLecture=urlLecture;
        this.urlPreview=urlPreview;
    }


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
