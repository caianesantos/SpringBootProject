package com.atividade.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.atividade.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
