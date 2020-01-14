/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.Collection;
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
    
    @Column(name = "Horaire_fin", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaireFin; //f
    
    @Column(name = "Duree", nullable = false)
    private Date duree;
    
    @Column(name = "Temps_mort", nullable = false)
    public Date tempsMort;
    
    @OneToMany
    private List<Tournee> tournees;
    
    /*@ManyToOne
    @JoinColumn(name="id_Solution")
    private Solution solution;*/
    
    ///ATTENTION CONDITION !!!!
    public Shift() {
        this.horaireDebut = new Date(0,0,0,9,0);
        this.horaireFin = new Date(0,0,0,10,0);
        Duree(horaireDebut,horaireFin);
        TempsMort();
        //tournees = new LinkedList<>();
        //this.solution = new Solution();
    }
    
    public Shift(Date horaireDebut, Date horaireFin) {
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        Duree(horaireDebut,horaireFin);
        TempsMort();
        //tournees = new LinkedList<>();
        //this.solution = new Solution();
    }
    
    public boolean addTournee(Tournee t)
    {
        if(t!=null)
        {
            if(tournees.add(t)==true)
            {
                horaireDebut=tournees.get(0).getHoraireDebut();
                horaireFin=tournees.get(tournees.size()-1).getHoraireFin();
                duree=Duree(horaireDebut,horaireFin);
                tempsMort=TempsMort();
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
     * tm(s) = max {Tmin; dur(s)} − Somme(t∈T)(fin du shift − debut du shift)
     */
    private void TempsMort()
    {
        Date tm,dureeT = null;
        List<Tournee> tourn;
        tourn= new LinkedList<>();
        tourn=this.tournees;
        for (Tournee t : tourn)
        {
            Date d = new Date(0,0,0,t.horaireFin.getHours()-t.horaireDebut.getHours(),t.horaireFin.getMinutes()-t.horaireDebut.getMinutes());
            int H,M;
            H=dureeT.getHours()+d.getHours();
            M=dureeT.getMinutes()+d.getMinutes();
            if(M>60)
            {
                int H1=M/60;
                int M1=M%60;
                dureeT = new Date(0,0,0,H+H1,M1);
            }else{
                dureeT= new Date(0,0,0,H,M);
            }
        }
        tm=new Date(0,0,0,this.duree.getHours()-dureeT.getHours(),this.duree.getMinutes()-dureeT.getMinutes());
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
        Date nouv = new Date(0,0,0,fin.getHours()-debut.getHours(),fin.getMinutes()-debut.getMinutes());
        this.duree=nouv;
    }
   
    @Override
    public String toString() {
        return "Shift{" + "idShift=" + idShift + ", horaireDebut=" + horaireDebut.getHours() + ":" + horaireDebut.getMinutes() + ", horaireFin=" + horaireFin.getHours() + ":" + horaireFin.getMinutes() + ", duree=" + duree + ", tempsMort=" + tempsMort/* + ", tournees=" + tournees*/ + '}';
    }
}
