/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
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
 * @author Val
 */
@Entity
public class Instance implements Serializable {

    private Connection connection;
    private static final long serialVersionUID = 1L;
    private static Instance instance;
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
    
    /* OneToMany : tournée */
    @OneToMany(mappedBy="Id_Instance")
    private Collection <Tournee> tournees;
    
    public static Instance getInstance() throws ClassNotFoundException, SQLException{
        if(instance == null)
            instance = new Instance();
        return instance;
    }

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
    
    public Integer getIdInstance() {
        return idInstance;
    }    
    
    public boolean listeTournee(Date debut, Date fin, EntityManager em)
    {
        Tournee t = new Tournee(debut,fin,this);
        em.persist(t);
        if(tournees.add(t)==true)
        {
            return true;
        }
        return false;
    }
    
    private void connect() throws ClassNotFoundException, SQLException {
        String driverClass = "org.apache.derby.jdbc.ClientDriver";
        String urlDatabase = "jdbc:derby://localhost:1527/Projet";
        String user = "val";
        String pwd = "100898";
        Class.forName(driverClass);
        connection = DriverManager.getConnection(urlDatabase, user, pwd);
    }
    
    public List<Instance> ensInstance () throws SQLException {
        List<Instance> instances = new ArrayList<>();
        String requete = "SELECT * from instance";
        try (Statement stmt = connection.createStatement(); ResultSet res = stmt.executeQuery(requete)) {
            while (res.next()) {
                Instance inst = new Instance(res.getString("nom"), res.getInt("dureeMin"), res.getInt("dureeMax"), res.getDate("date"));
                instances.add(inst);
            }
        }
        return instances;
    }

    @Override
    public String toString() {
        return "Instance{" + "idInstance=" + idInstance + ", nom=" + nom + ", dureeMin=" + dureeMin + ", dureeMax=" + dureeMax + ", date=" + date + ", tournees=" + tournees + '}';
    }

}
