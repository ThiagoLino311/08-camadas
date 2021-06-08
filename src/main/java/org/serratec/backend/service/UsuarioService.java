package org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.UsuarioDTO;
import org.serratec.backend.exception.EmailException;
import org.serratec.backend.model.Usuario;
import org.serratec.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public List<UsuarioDTO> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		for (Usuario usuario : usuarios) {
			UsuarioDTO dto = new UsuarioDTO(usuario);
			usuariosDTO.add(dto);
		}
		return usuariosDTO;
	}
	
	public Usuario inserir(Usuario user) throws EmailException {
		Usuario usuario = usuarioRepository.findByEmail(user.getEmail());
		if (usuario != null) {
			throw new EmailException("Email já existente");
		}
		user.setSenha(passwordEncoder.encode(user.getSenha()));
		return usuarioRepository.save(user);
	}
	
}
