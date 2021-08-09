package com.karla.vargas.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karla.vargas.model.Categoria;
import com.karla.vargas.model.Vacante;

@Service
public class VacantesServiceImp implements IntVacantesService {
	
	private List<Vacante> lista;
	
	public VacantesServiceImp() {
		lista = new LinkedList<Vacante>();
		//Formato para la fecha
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			Vacante v1 = new Vacante();
			v1.setId(1);
			v1.setNombre("Ingeniero de Sistemas");
			v1.setDescripcion("Desarrollo de Software");
			v1.setSalario(12000.0);
			v1.setFecha(LocalDate.parse("31-05-2021", formato));
			v1.setDestacado(1);
			v1.setImagen("logo15.png");
			v1.setEstatus("Creada");
			v1.setDetalles("<h3>Requisitos: </h3><ul><li>Amplia experiencia</li></ul>");
			Categoria c1 = new Categoria();
			c1.setId(3);
			c1.setNombre("Desarrollo de Software");
			c1.setDescripcion("Relacionado con el desarrollo");
			v1.setCategoria(c1);
			/****************************************************/
			Vacante v2 = new Vacante();
			v2.setId(2);
			v2.setNombre("Diseñador web");
			v2.setDescripcion("Desarrollo de Frond end");
			v2.setSalario(8000.0);
			v2.setFecha(LocalDate.parse("01-06-2021", formato));
			v2.setDestacado(0);
			v2.setImagen("logo17.png");
			v2.setEstatus("Aprobada");
			v2.setDetalles("<h3>Requisitos: </h3><ul><li>Amplia experiencia</li></ul>");
			Categoria c2 = new Categoria();
			c2.setId(3);
			c2.setNombre("Diseño de páginas web");
			c2.setDescripcion("Relacionado con el deiseño");
			v2.setCategoria(c2);
			/****************************************************/
			Vacante v3 = new Vacante();
			v3.setId(3);
			v3.setNombre("Contador");
			v3.setDescripcion("Contabilidad y finanzas");
			v3.setSalario(15000.0);
			v3.setFecha(LocalDate.parse("01-06-2021", formato));
			v3.setDestacado(1);
			v3.setImagen("logo10.png");
			v3.setEstatus("Eliminada");
			v3.setDetalles("<h3>Requisitos: </h3><ul><li>Amplia experiencia</li></ul>");
			Categoria c3 = new Categoria();
			c3.setId(1);
			c3.setNombre("Contabilidad");
			c3.setDescripcion("Relacionado con la contabilidad");
			v3.setCategoria(c3);
			
			lista.add(v1);
			lista.add(v2);
			lista.add(v3);
		}catch(DateTimeParseException ex) {
			System.out.println("Error: "+ex.getMessage());
		}
	}

	@Override
	public List<Vacante> obtenerTodas() {
		return lista;
	}

	@Override
	public void guardar(Vacante vacante) {
		lista.add(vacante);
	}

	@Override
	public void eliminar(Integer idVacante) {
		lista.remove(buscarPorId(idVacante));
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		for(Vacante v: lista) {
			if(v.getId()==idVacante) {
				return v;
			}
		} 
		return null;
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vacante> obtenerDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

}
