/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Gwendoline
 */
@Entity
public class Instance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="Id_Instance")
    private Integer idInstance;

    @Column(name = "Nom", nullable = false)
    private String nom;
    
    @Column(name = "Duree_minimale", nullable = false)
    private Integer dureeMin;
    
    @Column(name = "Duree_maximale", nullable = false)
    private Integer dureeMax;
    
    @Column(name = "Date_du_jour", nullable = false)
    private Date date;
    
    
    /* On a une liste de Tournee en ManyToOne*/
    private List<Tournee> tournees;

    public Instance() {
        this.nom = "";
        this.dureeMin = 0;
        this.dureeMax = 0;
        this.date = new Date(0,0,0,0,0);
        tournees = new LinkedList<>();// accepte les doublons et on se sais combien on a de tournees
    }

        public Instance(String nom, Integer dureeMin, Integer dureeMax, Date date) {
        this.nom = nom;
        this.dureeMin = dureeMin;
        this.dureeMax = dureeMax;
        this.date = date;
        tournees = new LinkedList<>(); // accepte les doublons et on se sais combien on a de tournees
    }
    
    private void Lecture()
    {

    }
    
    private boolean ListeTournee(Date debut, Date fin)
    {
        Tournee t = new Tournee(debut,fin);
        if(tournees.add(t)==true)
        {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        Instance i = new Instance();
        i.Lecture();
    }
}
