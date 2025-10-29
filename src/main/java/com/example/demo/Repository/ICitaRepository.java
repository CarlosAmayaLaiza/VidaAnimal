package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.classes.Cita;

public interface ICitaRepository extends JpaRepository<Cita, Integer> {

	
	//CONSULTA PARA EL USUARIO 
	
	   List<Cita> findByUsuarioId(int idUsuario);
	
	
	
	
}
