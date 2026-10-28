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
            new Usuario(null, "Caiane", "Silva", "11111111111"),
            new Usuario(null, "Pedro", "Santos", "22222222222"),
            new Usuario(null, "Dessa", "Oliveira", "33333333333"),
            new Usuario(null, "Guilherme", "Souza", "44444444444"),
            new Usuario(null, "Flávio", "Costa", "55555555555")
        ));
    }
}
