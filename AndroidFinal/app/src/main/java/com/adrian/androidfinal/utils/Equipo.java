package com.adrian.androidfinal.utils;

import java.io.Serializable;

public class Equipo implements Serializable {
    String nombreEs, descripcion, banner, escudo, twitter, facebook, instagram, web, id;


    public Equipo(String nombreEs, String descripcion, String banner, String escudo, String twitter, String facebook, String instagram, String web, String id) {
        this.nombreEs = nombreEs;
        this.descripcion = descripcion;
        this.banner = banner;
        this.escudo = escudo;
        this.twitter = twitter;
        this.facebook = facebook;
        this.instagram = instagram;
        this.web = web;
        this.id = id;
    }

    public String getNombreEs() {
        return nombreEs;
    }

    public void setNombreEs(String nombreEs) {
        this.nombreEs = nombreEs;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}