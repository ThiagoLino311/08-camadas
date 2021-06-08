package org.serratec.backend.controller;

import java.net.URI;
import java.util.List;

import org.serratec.backend.dto.UsuarioDTO;
import org.serratec.backend.exception.EmailException;
import org.serratec.backend.model.Usuario;
import org.serratec.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar(){
		List<UsuarioDTO> usuarios = usuarioService.findAll();
		return ResponseEntity.ok(usuarios);		
	}
	
	/*
		No padrão Rest devemos inserir a informação no headers do registro que acabou de ser 
		inserido na tabela do banco de dados.
		fromCurrentRequest.path - a partir da uri atual, vamos adicionar o valor do id no final da 
		uri sendo assim quando um recurso for criado saberemos como localizar o novo recurso.
		O método insert recebe o objeto no formato json. Esse objeto é persistido na base de 
		dados e é retornada na requisição a URL do registro que foi criado.
		Acessando a URL podemos consultar o objeto que foi inserido
		
		O codigo de resposta 422 Unprocessable Entity indica que o servidor entende o tipo de 
		conteúdo da entidade da requisição, e a sintaxe da requisição esta correta, mas não foi 
		possível processar as instruções presentes.
	*/
	
	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody Usuario usuario) {
		try {
			usuario = usuarioService.inserir(usuario);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
			return ResponseEntity.created(uri).body(usuario);
		} catch (EmailException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
}
