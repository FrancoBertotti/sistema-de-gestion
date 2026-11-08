package com.tup.programacion3.repository;

import com.tup.programacion3.model.entities.Usuario;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository extends BaseRepository<Usuario> {

    public UsuarioRepository() {
        super(Usuario.class);
    }

    // Buscar usuario activo por mail — retorna optional vacío si no existe o esta
    // dado de baja
    public Optional<Usuario> buscarPorMail(String mail) {
        String jpql = "SELECT u FROM Usuario u WHERE u.mail = :mail AND u.eliminado = false";
        List<Usuario> resultados = ejecutarConsulta(em -> em.createQuery(jpql, miClass)
                .setParameter("mail", mail)
                .getResultList());
        return resultados.isEmpty() ? Optional.empty() : Optional.of(resultados.get(0));
    }
}
