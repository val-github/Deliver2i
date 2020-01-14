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
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToMany;

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @OneToMany
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
    
    public boolean listeTournee(Date debut, Date fin, EntityManager em)
    {
        Tournee t = new Tournee(debut,fin);
        em.persist(t);
        if(tournees.add(t)==true)
        {
            return true;
        }
        return false;
    }
    
    public void creationShift(EntityManager em) //Solution triviale
    {
        Solution sol = new Solution(this);
        for(Tournee t : tournees)
        {
            Shift s = new Shift();
            s.addTournee(t);
            creationSolution(sol,s);
            em.persist(s);
        }
        em.persist(sol);
    }
    
    private void creationSolution(Solution sol,Shift s)
    {
        if(sol!=null)
        {
            if(s!=null)
            {
                sol.addShift(s);
            }
        }
    }
    
    

    @Override
    public String toString() {
        return "Instance{" + "idInstance=" + idInstance + ", nom=" + nom + ", dureeMin=" + dureeMin + ", dureeMax=" + dureeMax + ", date=" + date + /*", tournees=" + tournees + */'}';
    }

}
