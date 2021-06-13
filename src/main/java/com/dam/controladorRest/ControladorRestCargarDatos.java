package com.dam.controladorRest;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dam.modelo.Cliente;
import com.dam.modelo.Disco;
import com.dam.modelo.Ejemplar;
import com.dam.modelo.EstadoEjemplar;
import com.dam.modelo.Libro;
import com.dam.modelo.Material;
import com.dam.modelo.Prestamo;
import com.dam.servicio.IClienteServicio;
import com.dam.servicio.IMaterialServicio;
import com.dam.servicio.IPrestamoServicio;

@RestController
@RequestMapping("biblioteca/pruebas")
public class ControladorRestCargarDatos 
{	
	@Autowired IClienteServicio servicioCliente;
	@Autowired IMaterialServicio servicioMaterial;
	@Autowired IPrestamoServicio servicioPrestamo;
	
	@GetMapping("/cargardatos")
	public ResponseEntity<String> consultarCliente()
	{
		ResponseEntity <String> response;
		
		Cliente cliente1, cliente2;
		Material m1,m2;
		Ejemplar e1,e2,e3,e4;
		Prestamo p;
		
		cliente1 = new Cliente();
		cliente1.setDni("001");
		cliente1.setNombre("miguel");
		cliente1.setEmail("miguel@yahoo.es");
		cliente1.setFechaNacimiento(LocalDate.now().minusYears(34));
		
		cliente2 = new Cliente();
		cliente2.setDni("002");
		cliente2.setNombre("juanjo");
		cliente2.setEmail("juanjo@yahoo.es");
		cliente2.setFechaNacimiento(LocalDate.now().minusYears(24));
		
		servicioCliente.insertarCliente(cliente1);
		servicioCliente.insertarCliente(cliente2);
		
		e1 = new Ejemplar ("001001",EstadoEjemplar.ACEPTABLE);
		e2 = new Ejemplar ("001002",EstadoEjemplar.BUENO);
		e3 = new Ejemplar ("002001",EstadoEjemplar.ACEPTABLE);
		e4 = new Ejemplar ("002002",EstadoEjemplar.MALO);
		
		
		m1 = Libro.builder().isbn("001").
				autor("Cervantes").
				titulo("Quijote").
				ejemplare(e1).
				ejemplare(e2).
				nPaginas(600).build();
		
		
		m2 = Disco.builder().isbn("002").
				autor("Vetusta").
				titulo("Distinto lugar").
				ejemplare(e3).
				ejemplare(e4).
				duracion(LocalTime.of(1, 12, 20)).build();
		
		servicioMaterial.insertarMaterial(m1);
		servicioMaterial.insertarMaterial(m2);
				
		p = new Prestamo();
		p.setCliente(cliente1);
		p.setEjemplar(e1);
		p.setFechaPrestamo(LocalDate.now().minusDays(3));
		p.setDevuelto(false);
		
		servicioPrestamo.insertarPrestamo(p);
		
		response =  new ResponseEntity<>("carga realizada correctamente", HttpStatus.OK);
		
		return response;
	}
}