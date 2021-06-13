package com.dam.clienteRest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class AppCargarDatos 
{
	public static void main(String[] args) 
	{		
		cargarDatos ();
		AppCliente.listarClientes ();
	}
	
	
	public static void cargarDatos()
	{
		final String URL = "http://localhost:9090/biblioteca/pruebas/cargardatos";
		RestTemplate restTemplate = new RestTemplate();
		
		try 
		{
			ResponseEntity<String> response  = restTemplate.getForEntity(URL, String.class);	
	
			System.out.println(response.getBody());		
		} catch(HttpClientErrorException e) {
			System.out.println ("error");
		}
	}
}