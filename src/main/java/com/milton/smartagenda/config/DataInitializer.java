package com.milton.smartagenda.config;

import com.milton.smartagenda.domain.Usuario;
import com.milton.smartagenda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.findByNombre("Milton").isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setNombre("Milton");
            usuarioRepository.save(usuario);
            System.out.println("=================================================");
            System.out.println(">> BASE DE DATOS INICIALIZADA: Milton fue creado con éxito. <<");
            System.out.println("=================================================");
        } else {
            System.out.println(">> BASE DE DATOS OK: El usuario Milton ya existía.");
        }
    }
}
