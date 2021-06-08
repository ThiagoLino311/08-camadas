package org.serratec.backend.dto;

import org.serratec.backend.model.Usuario;

/*
	Data Transfer Object é um padrão de projetos que serve para o transporte de dados entre diferentes componentes de um 
	sistema, diferentes instâncias ou processos de um sistema distribuído ou diferentes sistemas via serialização. A ideia 
	consiste basicamente em agrupar um conjunto de atributos numa classe simples de forma a otimizar a comunicação.
	Um DTO Não tem acesso a banco de dados e faz o isolamento dos dados. Podemos projetar os dados que queremos exibir 
	na resposta de uma API.
*/
public class UsuarioDTO {

	private Long id;
	private String nome; 
	private String email;
	
	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
