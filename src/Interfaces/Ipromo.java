/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.promo;
import java.util.List; 

/**
 *
 * @author LENOVO
 */
public interface Ipromo {
    
    
    public void ajouterpromo(promo p ) ;
    public List<promo> afficherpromo();
    public boolean modifierpromo(promo p);
    public void supprimerpromo(int id_promo);
    

  
}
