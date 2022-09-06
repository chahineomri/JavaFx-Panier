/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.Personne;
import java.util.List; 

/**
 *
 * @author Fayechi
 */
public interface Ipersonne {
    //Add
    public void ajouterPersonne(Personne p);
    public void ajouterPersonne2(Personne p);
    
    //List
    public List<Personne> afficherPersonne();
}
