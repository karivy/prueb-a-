package com.karla.vargas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karla.vargas.model.Vacante;


public interface VacantesRepository extends JpaRepository<Vacante, Integer> {
	
	//select * from Vacantes where estatus=?
	List<Vacante> findByEstatus(String estatus);
	
	//Dos campos y ordenados (Estatus y Destacado)
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
}
