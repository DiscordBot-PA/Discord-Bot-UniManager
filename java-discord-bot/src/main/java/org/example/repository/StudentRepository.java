package org.example.repository;

import javax.persistence.*;


import org.example.EntityManagerFactoryUtil;
import org.example.model.Student;

import java.util.List;

public class StudentRepository {
    /**
     * Metoda salveaza un obiect de tip Student in baza de date
     * @param student
     */
    public void create(Student student) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }
}
