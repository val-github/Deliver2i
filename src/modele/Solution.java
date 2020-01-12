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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;

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
    
    @Column(name = "Nombre_Livreur", nullable = false)
    private Integer nombreLivreur;
    
    /*@OneToOne
    //@JoinColumn(name="id_Instance")
    private Instance instance;
    
    @OneToMany(mappedBy="solution")
    private List<Shift> shift;*/

    public Solution() {
        this.coutTotal = 0f;
        this.nombreLivreur = 0;
        //this.shift=new LinkedList<>();
    }
    
    public void addShift(Shift s) {
        if(s != null)
        {
            //shift.add(s);
        }
        coutTotal();
        nombreLivreur();
    }

    private void coutTotal() {
        float cout = 0;
        
        this.coutTotal = cout;
    }
    
    private void nombreLivreur() {
        int livreur = 0;
        
        this.nombreLivreur = livreur;
    }
    @OneToMany
    private Collection <Shift> shift;
    
    
}
