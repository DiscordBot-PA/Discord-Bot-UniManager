package org.example.repository;

import org.example.EntityManagerFactoryUtil;
import org.example.model.Grade;
import org.example.model.Preference;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class GradeRepository {

    private EntityManagerFactory entityManagerFactory;

    public GradeRepository() {
        entityManagerFactory = EntityManagerFactoryUtil.getEntityManagerFactory();
    }

    public List<Grade> getGrades(String registrationNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Grade> query = entityManager.createQuery("SELECT p FROM Grade p WHERE p.student.registrationNumber=:registrationNumber", Grade.class);
            query.setParameter("registrationNumber", registrationNumber);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public void save(Grade grade) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(grade);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}