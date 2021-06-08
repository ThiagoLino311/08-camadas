package org.serratec.backend.repository;

import org.serratec.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	// assinatura para buscar o email
	Usuario findByEmail(String email);
}
