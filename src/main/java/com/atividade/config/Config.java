package com.atividade.config;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import com.atividade.model.Usuario;
import com.atividade.model.repository.UsuarioRepository;

@Configuration
public class Config implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    public Config(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {
        usuarioRepository.saveAll(List.of(
            new Usuario(null, "Caiane", "Silva", "33044888211"),
            new Usuario(null, "Pedro", "Ricardo", "11874747555"),
            new Usuario(null, "Guilherme", "Reis", "07936320991"),
            new Usuario(null, "Marcos", "Dessa", "90869900122"),
            new Usuario(null, "Flávio", "Correia", "38160086156")
        ));
    }
}
