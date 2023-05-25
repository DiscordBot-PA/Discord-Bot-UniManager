package org.example;

import javax.persistence.*;


import org.example.EntityManagerFactoryUtil;
import org.example.model.Student;

import java.util.List;

public class StudentRepository {
    /**
     * Metoda salveaza un obiect de tip Artist in baza de date
     * @param artist
     */
    public void create(Student artist) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(artist);
        em.getTransaction().commit();
        em.close();
    }
}
