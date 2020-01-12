/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modele.Instance;
import modele.Tournee;

/**
 *
 * @author Gwendoline
 */
public class PROJET {
    
    private Instance In;
    
    //fonction de connection à l'instance
    private Connection connection;
    private void connect() throws ClassNotFoundException, SQLException {
        String driverClass = "org.apache.derby.jdbc.ClientDriver";
        String urlDatabase = "jdbc:derby://localhost:1527/Projet";
        String user = "val";
        String pwd = "100898";
        Class.forName(driverClass);
        connection = DriverManager.getConnection(urlDatabase, user, pwd);
    }
    //fonction de récupération des tournées stockées
    private List<Tournee> ensTournee () throws SQLException {
        List<Tournee> tournees = new ArrayList<>();
        String requete = "SELECT * from instance";
        try (Statement stmt = connection.createStatement(); ResultSet res = stmt.executeQuery(requete)) {
            while (res.next()) {
                Tournee t = new Tournee(res.getInt("Id_Tournee"), res.getInt("Horaire_debut"), res.getInt("Horaire_fin"));
                tournees.add(t);
            }
        }
        return tournees;
    }
    
    //création d'une liste avec les tournees
    List<Tournee> tournees=ensTournee()
    /**
     * @param args the command line arguments
     */
    //public static void main(String[] args) 
    //{
        
        
        // TODO code application logic here
    //}
    

