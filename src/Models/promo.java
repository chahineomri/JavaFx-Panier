/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author LENOVO
 */
public class promo {
    
   private int id_promo;
   private String nom_promo;
   private int type_promo;
   private String date_d;
   private String date_f;
   private int id_resto;
   private String text_promo;

    public promo() {
    }

    public promo(int id_promo, String nom_promo, int type_promo, String date_d, String date_f, int id_resto, String text_promo) {
        this.id_promo = id_promo;
        this.nom_promo = nom_promo;
        this.type_promo = type_promo;
        this.date_d = date_d;
        this.date_f = date_f;
        this.id_resto = id_resto;
        this.text_promo = text_promo;
    }

    public int getId_promo() {
        return id_promo;
    }

    public void setId_promo(int id_promo) {
        this.id_promo = id_promo;
    }

    public String getNom_promo() {
        return nom_promo;
    }

    public void setNom_promo(String nom_promo) {
        this.nom_promo = nom_promo;
    }

    public int getType_promo() {
        return type_promo;
    }

    public void setType_promo(int type_promo) {
        this.type_promo = type_promo;
    }

    public String getDate_d() {
        return date_d;
    }

    public void setDate_d(String date_d) {
        this.date_d = date_d;
    }

    public String getDate_f() {
        return date_f;
    }

    public void setDate_f(String date_f) {
        this.date_f = date_f;
    }

    public int getId_resto() {
        return id_resto;
    }

    public void setId_resto(int id_resto) {
        this.id_resto = id_resto;
    }

    public String getText_promo() {
        return text_promo;
    }

    public void setText_promo(String text_promo) {
        this.text_promo = text_promo;
    }

    @Override
    public String toString() {
        return "promo{" + "id_promo=" + id_promo + ", nom_promo=" + nom_promo + ", type_promo=" + type_promo + ", date_d=" + date_d + ", date_f=" + date_f + ", id_resto=" + id_resto + ", text_promo=" + text_promo + '}';
    }
   
}
  

