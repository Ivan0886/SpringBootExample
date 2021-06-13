package com.dam.clienteRest;

import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.dam.modelo.Cliente;


public class AppCliente 
{
	public static void main(String[] args) 
	{
		insertarCliente();
		consultarCliente();
		modificarCliente();
		consultarCliente();
		borrarCliente();
		consultarCliente();	
	}
	
	
	public static void insertarCliente()
	{
		final String URL = "http://localhost:9090/biblioteca/clientes/insertar";

		Cliente respuesta;		
		Cliente cliente = new Cliente ();
		
		cliente.setDni("001");
		cliente.setNombre("miguel");
		cliente.setEmail("miguel@yahoo.es");
		cliente.setFechaNacimiento(LocalDate.now().minusYears(34));
		
		RestTemplate restTemplate = new RestTemplate();
		
		try 
		{
			respuesta = restTemplate.postForObject(URL, cliente, Cliente.class);
	
			System.out.println("cliente insertado correctamente");
			System.out.println(respuesta);
		} catch(HttpClientErrorException e) {
			System.out.println("error, el cliente ya existe");
		}
	}
	
	
	public static void consultarCliente()
	{
		final String URL = "http://localhost:9090/biblioteca/clientes/consultar/{dni}";
		RestTemplate restTemplate = new RestTemplate();
		
		String dni = "001";
		Cliente respuesta;
		
		try 
		{
			ResponseEntity<Cliente> response  = restTemplate.getForEntity(URL, Cliente.class,dni);
			
			respuesta = response.getBody();
			System.out.println(respuesta);
		} catch(HttpClientErrorException e) {
			System.out.println ("dni no encontrado");
		}
	}
	
	
	public static void modificarCliente()
	{
		final String URL1 = "http://localhost:9090/biblioteca/clientes/consultar/{dni}";
		final String URL2 = "http://localhost:9090/biblioteca/clientes/modificar";
		RestTemplate restTemplate = new RestTemplate();
		
		String dni = "001";
		Cliente respuesta;
		
		try 
		{
			ResponseEntity<Cliente> response  = restTemplate.getForEntity(URL1, Cliente.class,dni);
		
			respuesta = response.getBody();
			System.out.println("valor actual");
			System.out.println(respuesta);
			
			respuesta.setFechaFinSancion(LocalDate.now());
			
			restTemplate.put(URL2, respuesta);
			
			System.out.println("registro actualizado correctamente");
			
		} catch(HttpClientErrorException e) {
			System.out.println("dni no encontrado");
		}
	}
	
	
	public static void borrarCliente()
	{
		final String URL = "http://localhost:9090/biblioteca/clientes/eliminar/{dni}";
		RestTemplate restTemplate = new RestTemplate();
		
		String dni = "001";	
		
		try 
		{
			restTemplate.delete(URL,dni);
		
			System.out.println("borrado correctamente");
		} catch(HttpClientErrorException e) {
			System.out.println ("dni no encontrado");
		}
	}
	
	
	public static void listarClientes()
	{
		final String URL = "http://localhost:9090/biblioteca/clientes/listar";
		RestTemplate restTemplate = new RestTemplate();
		
		Cliente[] respuesta;
		
		try 
		{
			ResponseEntity<Cliente[]> response  = restTemplate.getForEntity(URL, Cliente[].class);
			
			respuesta = response.getBody();
			
			for (Cliente c : respuesta)
				System.out.println(c);
		} catch(HttpClientErrorException e) {
			System.out.println ("error");
		}
	}
}