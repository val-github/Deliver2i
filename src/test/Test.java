/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import io.InstanceReader;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Gwendoline
 */
public class Test {
    public static void main(String[] args) {
        final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("PROJETPU");  
        // Ici on met pas le nom de la base de donne mais le nom 
        //qu'il y a dans persistance.xml
        final EntityManager em = emf.createEntityManager();
        try
        {
            final EntityTransaction et = em.getTransaction();
            try
            {
                et.begin();
                // creation d’une entite persistante
                InstanceReader reader = new InstanceReader("instances/instance_test.csv");
                reader.readInstance(em);

                System.out.println("=> Reader : " + reader);
                et.commit();
            } 
            catch (Exception ex) 
            {
                et.rollback();
            }
        } 
        finally
        {
            if(em != null && em.isOpen())
            {
                em.close();
            }
            if(emf != null && emf.isOpen())
            {
                emf.close();
            }
        }
    }
}
