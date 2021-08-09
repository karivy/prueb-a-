package com.karla.vargas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.karla.vargas.model.Perfil;

public interface IntPerfilesService {
	public List<Perfil> obtenerTodas();
	public void guardar(Perfil perfil);
	public void eliminar(Integer idPerfil);
	public Perfil buscarPorId(Integer idPerfil);
	public Page<Perfil> buscarTodas(Pageable page);
}
