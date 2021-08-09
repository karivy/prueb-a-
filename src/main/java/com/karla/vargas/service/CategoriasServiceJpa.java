package com.karla.vargas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karla.vargas.model.Categoria;
import com.karla.vargas.repository.CtaegoriasRepository;

@Service
@Primary
public class CategoriasServiceJpa implements IntCategoriasService{

	 @Autowired
	 private CtaegoriasRepository repoCategorias;
	 
	@Override
	public List<Categoria> obtenerTodos() {
		return repoCategorias.findAll();
	}

	@Override
	public void guardar(Categoria categoria) {
		repoCategorias.save(categoria);
	}

	@Override
	public void eliminar(Integer idCategoria) {
		repoCategorias.deleteById(idCategoria);
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> optional = repoCategorias.findById(idCategoria);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		return repoCategorias.findAll(page);
	}

}