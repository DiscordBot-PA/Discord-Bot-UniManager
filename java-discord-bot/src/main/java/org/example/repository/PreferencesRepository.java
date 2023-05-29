package org.example.repository;

import org.example.EntityManagerFactoryUtil;
import org.example.model.Preference;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class PreferencesRepository {

    private EntityManagerFactory entityManagerFactory;

    public PreferencesRepository() {
        entityManagerFactory = EntityManagerFactoryUtil.getEntityManagerFactory();
    }

    public List<Preference> getAllPreferences() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Preference> query = entityManager.createQuery("SELECT p FROM Preference p", Preference.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public void save(Preference preference) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(preference);
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