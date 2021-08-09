package com.karla.vargas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.karla.vargas.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
	
	Usuario findByUsername(String username);
	
	@Query(value="select coint(*) from usuarios", nativeQuery=true)
	Integer totalEntidades();
}
