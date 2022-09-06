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
public class coupouns {
    private int id_coupouns;
   private String text_coupouns;
   private String code_coupouns;

    public coupouns() {
    }

    public coupouns(int id_coupouns, String text_coupouns, String code_coupouns) {
        this.id_coupouns = id_coupouns;
        this.text_coupouns = text_coupouns;
        this.code_coupouns = code_coupouns;
    }

    public int getId_coupouns() {
        return id_coupouns;
    }

    public void setId_coupouns(int id_coupouns) {
        this.id_coupouns = id_coupouns;
    }

    public String getText_coupouns() {
        return text_coupouns;
    }

    public void setText_coupouns(String text_coupouns) {
        this.text_coupouns = text_coupouns;
    }

    public String getCode_coupouns() {
        return code_coupouns;
    }

    public void setCode_coupouns(String code_coupouns) {
        this.code_coupouns = code_coupouns;
    }

    @Override
    public String toString() {
        return "coupouns{" + "id_coupouns=" + id_coupouns + ", text_coupouns=" + text_coupouns + ", code_coupouns=" + code_coupouns + '}';
    }
   
}
