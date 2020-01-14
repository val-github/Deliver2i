/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gwendoline
 */
@Entity
public class Solution implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="Id_Solution")
    private Integer idSolution;

    @Column(name = "Temps_Mort", nullable = false)
    private Date tempsMort;
    
    @Column(name = "Nombre_Livreur", nullable = false)
    private Integer nombreLivreur;

    @OneToOne
    private Instance instance;

    @OneToMany
    private List<Shift> shifts;
    
    public Solution() {
        this.coutTotal = 0f;
        this.nombreLivreur = 0;
        this.shifts = new LinkedList<>();       
    }
    
     public Solution(Instance i) {
        this.coutTotal = 0f;
        this.nombreLivreur = 0;
        instance = i;
        this.shifts = new LinkedList<>();        
    }
     
    public void addShift(Shift s) {
        
        if(s != null)
        {
            if(shifts.add(s)==true)
            {
                coutTotal();
                nombreLivreur();
            }
        } 
    }
    /*
    private void coutTotal() {
        float cout = 0;
        
        this.coutTotal = cout;
    }
    
    private void nombreLivreur() {
        int livreur = 0;
        
        this.nombreLivreur = livreur;
    }*/
    //fonction permettant de calculer le temps mort total de la solution
    
    private float nombreLivreurs()
    {
        return this.shift.size();
    }
    
    private void tempsMort()
    {
        Date tm=null;
        List<Shift> shif;
        shif= new LinkedList<>();
        shif=this.shift;
        for (Shift s : shif)
        {
            Date tm1=new Date(0,0,0,s.tempsMort.getHours(),s.tempsMort.getMinutes());
            int H,M;
            H=tm.getHours()+tm1.getHours();
            M=tm.getMinutes()+tm1.getMinutes();
            if(M>60)
            {
                int H1=M/60;
                int M1=M%60;
                tm= new Date(0,0,0,H+H1,M1);
            }else{
                tm= new Date(0,0,0,H,M);
            }
        }
        this.tempsMort=tm;
    }
    
}
