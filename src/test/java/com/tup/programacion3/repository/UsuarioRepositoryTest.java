package com.tup.programacion3.repository;

import com.tup.programacion3.model.entities.Usuario;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioRepositoryTest {

    private final UsuarioRepository usuarioRepo = new UsuarioRepository();

    @Test
    void buscarPorMail_devuelveElUsuarioConEseMail() {
        // PREPARAR: creo y guardo un usuario
        Usuario usuario = new Usuario();
        usuario.setNombre("Prueba");
        usuario.setMail("prueba1@test.com");
        usuario.setEliminado(false);
        Usuario guardado = usuarioRepo.guardar(usuario);

        // EJECUTAR: busco por ese mail
        Optional<Usuario> resultado = usuarioRepo.buscarPorMail("prueba1@test.com");

        // VERIFICAR: encontró algo y es el mismo usuario
        assertTrue(resultado.isPresent());
        assertEquals(guardado.getId(), resultado.get().getId());
    }

    @Test
    void buscarPorMail_conMailInexistente_devuelveVacio() {
        Optional<Usuario> resultado = usuarioRepo.buscarPorMail("noexiste@test.com");

        assertTrue(resultado.isEmpty());
    }
}
