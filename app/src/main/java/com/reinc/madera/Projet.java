package com.reinc.madera;

public class Projet{

    private String idProjet;
    private String labelPlan;
    private String datePlan;
    private String client;
    private String utilisateur;

    public Projet(String idProjet, String labelPlan, String datePlan, String client, String utilisateur) {
        this.idProjet = idProjet;
        this.labelPlan = labelPlan;
        this.datePlan = datePlan;
        this.client = client;
        this.utilisateur = utilisateur;
    }

    public String getId() {
        return idProjet;
    }

    public void setId(String idProjet) {
        this.idProjet = idProjet;
    }

    public String getLabelPlan() {
        return labelPlan;
    }

    public void setLabelPlan(String labelPlan) {
        this.labelPlan = labelPlan;
    }

    public String getDatePlan() {
        return datePlan;
    }

    public void setDatePlan(String datePlan) {
        this.datePlan = datePlan;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }
}