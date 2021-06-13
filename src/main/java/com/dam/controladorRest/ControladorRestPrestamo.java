package com.dam.controladorRest;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dam.modelo.Ejemplar;
import com.dam.modelo.Prestamo;
import com.dam.servicio.IPrestamoServicio;

@RestController
@RequestMapping("biblioteca/prestamo")
public class ControladorRestPrestamo 
{	
	@Autowired IPrestamoServicio prestamoServicio;
	
	@PostMapping("/insertar")
	public ResponseEntity<Ejemplar> insertarPrestamo (@RequestParam("dni") String dni,
														@RequestParam("isbn") String isbn)
	{		
		HttpStatus status = HttpStatus.CREATED;
		Optional<Ejemplar> ejemplar = Optional.empty();
		
		System.out.println("peticion " + dni + " " + isbn);	
		
		ejemplar = prestamoServicio.insertarPrestamo(dni, isbn);
		
		if(!ejemplar.isPresent())
			status = HttpStatus.BAD_REQUEST;
		
		return new ResponseEntity<Ejemplar>(ejemplar.orElse(new Ejemplar()),status);
	}
	
	
	@GetMapping("/listarpendientes")
	public List<Prestamo> listarPendientes()
	{
		return prestamoServicio.buscarPrestamosPendientes();
	}
}