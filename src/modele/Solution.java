/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

    @Column(name = "Cout_Total", nullable = false)
    private Float coutTotal;
    
    @Column(name = "Date_fin", nullable = false)
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

    private void coutTotal() {
        float cout = 0;
        
        this.coutTotal = cout;
    }
    
    private void nombreLivreur() {
        int livreur = 0;
        
        this.nombreLivreur = livreur;
    }
}
