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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Val
 */
@Entity
public class Shift implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="Id_Shift")
    private Integer idShift;

    @Column(name = "Horaire_debut", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaireDebut; //d
    
    @Column(name = "Horaire_fin", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaireFin; //f
    
    @Column(name = "Duree", nullable = false)
    private Float duree;
    
    @Column(name = "Temps_mort", nullable = false)
    private Float tempsMort;

    /* ManyToOne : Tounees */
    private List<Tournee> tournees;
    
    
    /*ManyToOne : tournée    */
    //ManyToOne
    //@JoinColumn(name="DEPT_ID")
    //private Departement departement;
    
    ///ATTENTION CONDITION !!!!
    public Shift() {
        this.horaireDebut = new Date(0,0,0,9,0);
        this.horaireFin = new Date(0,0,0,10,0);
        this.duree = Duree(horaireDebut,horaireFin);
        this.tempsMort = TempsMort();
        tournees = new LinkedList<>();
    }
    
    public Shift(Date horaireDebut, Date horaireFin) {
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.duree = Duree(horaireDebut,horaireFin);
        this.tempsMort = TempsMort();
        tournees = new LinkedList<>();
    }
    
    /**
     * 
     * @param debut
     * @param fin
     * @return float
     * 
     * Cette fonction calcule la duree du Shift
     * duree = fin du shift - debut du shift
     */
    private Float Duree(Date debut,Date fin)
    {
        float dureeFinale=0;   
        
        Date nouv = new Date(0,0,0,fin.getHours()-debut.getHours(),fin.getMinutes()-debut.getMinutes());
        
        int heure = nouv.getHours();        
        float minute = nouv.getMinutes();
        minute = minute/60;
        
        dureeFinale = heure + minute;
        return dureeFinale;
    }
   
    /**
     * 
     * @return float
     * 
     * Cette fonction calcul le temps mort du Shift
     * tm(s) = max {Tmin; dur(s)} − Somme(t∈T)(fin du shift − debut du shift)
     */
    private Float TempsMort()
    {
        return duree;
    }
    
    public static void main(String[] args) {
        /*Date debut = new Date(0,0,0,10,0);
        Date fin = new Date(0,0,0,11,10);
        
        Shift s = new Shift();
        s.Duree(debut,fin);*/
    }
}
