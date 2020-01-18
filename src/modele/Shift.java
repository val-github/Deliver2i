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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gwendoline
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

    public Date getHoraireDebut() {
        return horaireDebut;
    }

    @Column(name = "Horaire_fin", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaireFin; //f

    public Date getHoraireFin() {
        return horaireFin;
    }
    
    @Column(name = "Duree", nullable = false)
    private int duree;

    public int getDuree() {
        return duree;
    }
    
    @Column(name = "DureeMin", nullable = false)
    private int dureeMin;

    public int getDureeMin() {
        return dureeMin;
    }
    
    
    @Column(name = "DureeMax", nullable = false)
    private int dureeMax;

    public int getDureeMax() {
        return dureeMax;
    }
    
    
    @Column(name = "Temps_mort", nullable = false)
    private int tempsMort;

    public int getTempsMort() {
        return tempsMort;
    }
    
    
    @OneToMany
    public List<Tournee> tournees;
    
    
    ///ATTENTION CONDITION !!!!
    public Shift() {
        this.horaireDebut = new Date(0,0,0,9,0);
        this.horaireFin = new Date(0,0,0,10,0);
        Duree(horaireDebut,horaireFin);
        TempsMort();
        tournees = new LinkedList<>();
    }
    
    public Shift(Date horaireDebut) {
        this.horaireDebut = horaireDebut;
        this.horaireFin = null;
        Duree(horaireDebut,horaireFin);
        TempsMort();
        tournees = new LinkedList<>();
    }
    
    public Shift(Integer idShift, Date horaireDebut, Date horaireFin, Date duree, Date tempsMort) {
        this.idShift = idShift;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.duree = duree;
        this.tempsMort = tempsMort;
        this.tournees = new LinkedList<>();
    }
    
    public Integer getIdShift() {
        return idShift;
    }

    public Date getHoraireDebut() {
        return horaireDebut;
    }

    public Date getHoraireFin() {
        return horaireFin;
    }

    public Date getDuree() {
        return duree;
    }
    
    public Date getTempsMort() {
        return tempsMort;
    }

    public boolean addTournee(Tournee t)
    {
        if(t!=null)
        {
            if(tournees.add(t)==true)
            {
                horaireDebut=tournees.get(0).getHoraireDebut();
                horaireFin=tournees.get(tournees.size()-1).getHoraireFin();
                Duree(horaireDebut,horaireFin);
                TempsMort();
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @return float
     * 
     * Cette fonction calcul le temps mort du Shift
     * tm(s) = max {Tmin; dur(s)} âˆ’ Somme(tâˆˆT)(fin du shift âˆ’ debut du shift)
     */
    private void TempsMort()
    {
        int tm=0;
        List<Tournee> tourn;
        tourn= new LinkedList<>();
        tourn=this.tournees;
        for (Tournee t : tourn)
        {
            int tm1=(t.getHoraireFin().getHours()-t.getHoraireDebut().getHours())*60+(t.getHoraireFin().getMinutes()-t.getHoraireDebut().getMinutes());
            tm=tm+tm1;
        }
        this.tempsMort=tm;
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
    private void Duree(Date debut,Date fin)
    {
        int nouv=(fin.getHours()-debut.getHours())*60+(fin.getMinutes()-debut.getMinutes());
        this.duree=nouv;
    }
   
    @Override
    public String toString() {
        return "Shift{" + "idShift=" + idShift + ", horaireDebut=" + horaireDebut.getHours() + ":" + horaireDebut.getMinutes() + ", horaireFin=" + horaireFin.getHours() + ":" + horaireFin.getMinutes() + ", duree=" + duree + ", tempsMort=" + tempsMort/* + ", tournees=" + tournees*/ + '}';
    }
}
