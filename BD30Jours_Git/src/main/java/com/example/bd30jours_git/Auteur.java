package com.example.bd30jours_git;

import java.util.ArrayList;

/**
 * Created by Dimitri on 05/03/14.
 */
public class Auteur {

    private String pseudo;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public ArrayList<String> getBd() {
        return bd;
    }

    public void setBd(ArrayList<String> bd) {
        this.bd = bd;
    }

    public ArrayList<String> getPlanches() {
        return planches;
    }

    public void setPlanches(ArrayList<String> planches) {
        this.planches = planches;
    }

    private String nom;
    private String prenom;
    private ArrayList<String> planches;
    private ArrayList<String> bd;

    public Auteur(String pseud, String nom, String prenom)
    {
        pseudo=pseud;
        this.nom=nom;
        this.prenom=prenom;
    }
}
