package com.tup.programacion3.repository;

import com.tup.programacion3.model.entities.Usuario;
import com.tup.programacion3.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository extends BaseRepository<Usuario> {

    public UsuarioRepository() {
        super(Usuario.class);
    }

    // Buscar usuario activo por mail — retorna optional vacío si no existe o esta dado de baja
    public Optional<Usuario> buscarPorMail(String mail) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<Usuario> resultados = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.mail = :mail AND u.eliminado = false",
                    Usuario.class)
                    .setParameter("mail", mail)
                    .getResultList();
            return resultados.isEmpty() ? Optional.empty() : Optional.of(resultados.get(0));
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
