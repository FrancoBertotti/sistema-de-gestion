package com.tup.programacion3.repository;

import com.tup.programacion3.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class BaseRepository<T> {

    protected final Class<T> miClass;

    public BaseRepository(Class<T> miClass) {
        this.miClass = miClass;
    }

    public T guardar(T entity) {
        // EntityManager desde JPAUtil
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            T entityMerged = em.merge(entity);
            tx.commit();
            return entityMerged;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al guardar la entidad: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    // buscarPorId
    public Optional<T> buscarPorId(Long id) {
    return ejecutarConsulta(em -> Optional.ofNullable(em.find(miClass, id)));
}

    // listarActivos()
    public List<T> listarActivos() {
        String jpql = "FROM " + miClass.getSimpleName() + " e WHERE e.eliminado = false";
        return ejecutarConsulta(em -> em.createQuery(jpql, miClass).getResultList());
    }

    // eliminarLogico
    public boolean eliminarLogico(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T entity = em.find(miClass, id);

            if (entity != null) {

                try {
                    entity.getClass().getMethod("setEliminado", boolean.class).invoke(entity, true);
                } catch (Exception e) {
                    throw new RuntimeException("La entidad no tiene el método setEliminado(boolean)", e);
                }

                em.merge(entity);
                tx.commit();
                return true;
            }

            tx.commit();
            return false;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error en el borrado lógico, no se encontro el registro: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    protected <R> R ejecutarConsulta(Function<EntityManager, R> consulta) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return consulta.apply(em);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
