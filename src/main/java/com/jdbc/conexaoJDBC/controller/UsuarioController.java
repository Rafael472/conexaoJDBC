package com.jdbc.conexaoJDBC.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdbc.conexaoJDBC.dao.UsuarioDAO;
import com.jdbc.conexaoJDBC.model.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired UsuarioDAO usuarioDAO;
	
	@RequestMapping({"", "/"})
	public List<Usuario> listar(){
		for (Usuario u : usuarioDAO.getUsuarios()) {
			System.out.println(u.getNome());
		}
		return usuarioDAO.getUsuarios();
	}
	
	@RequestMapping("/salvar")
	public void salvar() {
		Usuario usuario = new Usuario();
		usuario.setNome("Joao Gabriel");
		usuario.setIdade(20);
		usuario.setDataCadastro(new Date());
		usuarioDAO.save(usuario);
	}
	
	@RequestMapping("/atualizar")
	public void atualizar() {
		Usuario usuario = new Usuario();
		usuario.setId(2);
		usuario.setNome("Fernando Souza");
		usuario.setIdade(25);
		usuario.setDataCadastro(new Date());
		usuarioDAO.update(usuario);
	}
	
	@RequestMapping("/deletar")
	public void deletar() {
		usuarioDAO.deleteById(2);
	}
}
