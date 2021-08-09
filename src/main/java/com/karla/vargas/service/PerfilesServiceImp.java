package com.karla.vargas.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karla.vargas.model.Perfil;

@Service
public class PerfilesServiceImp implements IntPerfilesService{
	
	private List<Perfil> lista;
	
	public PerfilesServiceImp() {
		lista = new LinkedList<Perfil>();
		
		Perfil per1 = new Perfil();
		per1.setId(1);
		per1.setPerfil("Administrador");
		
		Perfil per2 = new Perfil();
		per2.setId(2);
		per2.setPerfil("Profesor");
		
		Perfil per3 = new Perfil();
		per3.setId(3);
		per3.setPerfil("Alumno");
		
		lista.add(per1);
		lista.add(per2);
		lista.add(per3);
	}

	@Override
	public List<Perfil> obtenerTodas() {
		return lista;
	}

	@Override
	public void guardar(Perfil perfil) {
		lista.add(perfil);
	}

	@Override
	public void eliminar(Integer idPerfil) {
		lista.remove(buscarPorId(idPerfil));
	}

	@Override
	public Perfil buscarPorId(Integer idPerfil) {
		for(Perfil p: lista) {
			if(p.getId() == idPerfil) {
				return p;
			}
		}
		return null;
	}

	@Override
	public Page<Perfil> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
