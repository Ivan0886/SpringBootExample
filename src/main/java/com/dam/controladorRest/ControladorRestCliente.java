package com.dam.controladorRest;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dam.modelo.Cliente;
import com.dam.servicio.IClienteServicio;

@RestController
@RequestMapping("biblioteca/clientes")
public class ControladorRestCliente 
{
	@Autowired IClienteServicio servicioCliente;
	
	@PostMapping("/insertar")
	public ResponseEntity<Cliente> insertarCliente(@RequestBody Cliente cliente)
	{
		HttpStatus status = HttpStatus.CREATED;
		
		if(!servicioCliente.buscarPorDNI(cliente.getDni()).isPresent())
			servicioCliente.insertarCliente(cliente);
		else
			status = HttpStatus.BAD_REQUEST;
		
		return new ResponseEntity<Cliente>(cliente,status);
	}
	
	
	@GetMapping("/consultar/{dni}")
	public ResponseEntity<Cliente> consultarCliente(@PathVariable String dni)
	{
		ResponseEntity <Cliente> response;
		
		Optional<Cliente> cliente = servicioCliente.buscarPorDNI(dni); 
		
		if(!cliente.isPresent())
			response = new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		else
			response = new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
		
		return response;
	}
	
	
	@GetMapping("/listar")
	public ResponseEntity<Cliente[]> consultarClientes()
	{
		ResponseEntity <Cliente[]> response;
		
		List<Cliente> clientes = servicioCliente.buscarClientes(); 
		
		Cliente[] array = new Cliente[clientes.size()];
		
		array = clientes.toArray(array);
		
		response =  new ResponseEntity<Cliente[]>(array, HttpStatus.OK);
		
		return response;
	}
	
	
	@PutMapping("/modificar")
	public ResponseEntity<Cliente> modificarCliente(@RequestBody Cliente cliente)
	{
		HttpStatus status = HttpStatus.ACCEPTED;
		
		if(!servicioCliente.actualizarCliente(cliente))
			status = HttpStatus.BAD_REQUEST;
		
		return new ResponseEntity<Cliente>(cliente,status);
	}
	
	
	@DeleteMapping("/eliminar/{dni}")
	public ResponseEntity<Cliente> eliminarCliente(@PathVariable String dni)
	{
		ResponseEntity<Cliente> response;
		
		if(servicioCliente.borrarCliente(dni))
			response = new ResponseEntity<>(HttpStatus.OK);
		else
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return response;
	}
}