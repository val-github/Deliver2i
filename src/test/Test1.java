/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import io.InstanceReader;
import modele.Instance;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author guigeek
 */
public class Test1 {

    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROJETPU");
        final EntityManager em = emf.createEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                // creation d’une entite persistante

                InstanceReader instread1 = new InstanceReader("instance_0.csv");
                InstanceReader instread2 = new InstanceReader("instance_1.csv");
                InstanceReader instread3 = new InstanceReader("instance_2.csv");
                InstanceReader instread4 = new InstanceReader("instance_3.csv");
                InstanceReader instread5 = new InstanceReader("instance_4.csv");
                InstanceReader instread6 = new InstanceReader("instance_5.csv");
                InstanceReader instread7 = new InstanceReader("instance_6.csv");
                InstanceReader instread8 = new InstanceReader("instance_7.csv");
                InstanceReader instread9 = new InstanceReader("instance_8.csv");
                InstanceReader instread10 = new InstanceReader("instance_9.csv");
                InstanceReader instread11 = new InstanceReader("instance_10.csv");
                instread1.readInstance(em);
                instread2.readInstance(em);
                instread3.readInstance(em);
                instread4.readInstance(em);
                instread5.readInstance(em);
                instread6.readInstance(em);
                instread7.readInstance(em);
                instread8.readInstance(em);
                instread9.readInstance(em);
                instread10.readInstance(em);
                instread11.readInstance(em);
                et.commit();

            } catch (Exception ex) {
                et.rollback();
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}
