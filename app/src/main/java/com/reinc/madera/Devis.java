package com.reinc.madera;

public class Devis {
    private String idDevis;
    private String nom;
    private String etatDevis;
    private String totalHT;
    private String totalTTC;
    private String tauxRemise;
    private String creationDate;
    private String projet;

    public Devis(String idDevis, String nom, String etatDevis, String totalHT, String totalTTC, String tauxRemise, String creationDate, String projet) {
        this.idDevis = idDevis;
        this.nom = nom;
        this.etatDevis = etatDevis;
        this.totalHT = totalHT;
        this.totalTTC = totalTTC;
        this.tauxRemise = tauxRemise;
        this.creationDate = creationDate;
        this.projet = projet;
    }

    public String getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(String idDevis) {
        this.idDevis = idDevis;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEtatDevis() {
        return etatDevis;
    }

    public void setEtatDevis(String etatDevis) {
        this.etatDevis = etatDevis;
    }

    public String getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(String totalHT) {
        this.totalHT = totalHT;
    }

    public String getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(String totalTTC) {
        this.totalTTC = totalTTC;
    }

    public String getTauxRemise() {
        return tauxRemise;
    }

    public void setTauxRemise(String tauxRemise) {
        this.tauxRemise = tauxRemise;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getProjet() {
        return projet;
    }

    public void setProjet(String projet) {
        this.projet = projet;
    }
}

