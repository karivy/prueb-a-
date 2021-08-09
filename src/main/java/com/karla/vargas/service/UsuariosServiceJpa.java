package com.karla.vargas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karla.vargas.model.Usuario;
import com.karla.vargas.repository.UsuariosRepository;
import com.karla.vargas.service.IntUsuariosService;

@Service
public class UsuariosServiceJpa implements IntUsuariosService{
	
	@Autowired
	private UsuariosRepository repoUsuarios;
	
	@Override
	public List<Usuario> obtenerTodas() {
		return repoUsuarios.findAll();
	}

	@Override
	public void guardar(Usuario usuario) {
		repoUsuarios.save(usuario);
	}

	@Override
	public void eliminar(Integer idUsuario) {
		repoUsuarios.deleteById(idUsuario);
	}

	@Override
	public Page<Usuario> buscarTodas(Pageable page) {
		return repoUsuarios.findAll(page);
	}

	@Override
	public Integer numeroEntidades() {
		return repoUsuarios.findAll().size();
	}
	
	@Override
	public Integer calcularTotalEntidades() {
		return repoUsuarios.totalEntidades();
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		return repoUsuarios.findByUsername(username);
	}
}
