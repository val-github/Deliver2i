/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gwendoline
 */
@Entity
public class Tournee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="Id_Tournee")
    private Integer idTournee;

    @Column(name = "Horaire_debut", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date horaireDebut;
    
    @Column(name = "Horaire_fin", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date horaireFin;
    
    /* ManyToOne : Instance */
    @ManyToOne
    private Instance instance;

    
    /* ManyToOne :  Shift */
    @ManyToOne
    private Shift shift;
    
    public Tournee() {
        this.horaireDebut = new Date(0,0,0,9,0);
        this.horaireFin = new Date(0,0,0,10,0);
        this.instance = new Instance();
        //this.shift = new Shift();
    }   
    
    public Tournee(Date dateDebut, Date dateFin, Instance i) {
        if(dateDebut.compareTo(dateFin) > 0)
        {
            this.horaireDebut = dateFin;
            this.horaireFin = dateDebut;
        }
        else
        {
            this.horaireDebut = dateDebut;
            this.horaireFin = dateFin;
        }
        if(i!=null)
        {
            instance=i;
        }
    }

    public Date getHoraireDebut() {
        return horaireDebut;
    }

    public Date getHoraireFin() {
        return horaireFin;
    }
    
    public void ajoutTourneeShift(Shift s) {
        if (s!=null)
        {
            this.shift = s;
        }
    }
    
    @Override
    public String toString() {
        return "\n\tTournee{" + "idTournee=" + idTournee + ", horaireDebut=" + horaireDebut.getHours() + ":" + horaireDebut.getMinutes() + ", horaireFin=" + horaireFin.getHours() + ":" + horaireFin.getMinutes() + '}';
    }

       
}
