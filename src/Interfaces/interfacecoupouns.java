/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import Models.coupouns;

/**
 *
 * @author LENOVO
 */
public interface interfacecoupouns {
    public void ajoutercoupouns(coupouns c );
    public List<coupouns> affichercoupouns();
  // public void modifiercoupouns();
   public void supprimercoupouns(int id_coupouns);
   public boolean modifiercoupouns(coupouns c);
}
