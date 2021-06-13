package com.dam.clienteRest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.dam.modelo.Ejemplar;
import com.dam.modelo.Prestamo;

public class AppPrestamo 
{
	public static void main(String[] args) 
	{
		solicitarPrestamo();
		listarPrestamos();
	}
	
	
	public static void solicitarPrestamo()
	{
		final String URL = "http://localhost:9090/biblioteca/prestamo/insertar?dni={dni}&isbn={isbn}";
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Dni: ");
		String dni = teclado.nextLine();
		
		System.out.println("ISBN: ");
		String isbn = teclado.nextLine();
		
		//String dni = "002";
		//String isbn = "001";
		
		// Cargar parámetros
		Map<String,String> params = new HashMap<>();
		
	    params.put("dni", dni);
	    params.put("isbn", isbn);
		
	    Ejemplar respuesta;

		RestTemplate restTemplate = new RestTemplate();
		
		try 
		{
			respuesta = restTemplate.postForObject( URL, null,Ejemplar.class,params);
	
			System.out.println("prestamo realizado correctamente");
			System.out.println("ejemplar asignado");
			System.out.println(respuesta);
		
		} catch(HttpClientErrorException e) {
			System.out.println("error, no se ha podido realizar el prestamo");
		}
	}

	
	public static void listarPrestamos()
	{
		final String URL = "http://localhost:9090/biblioteca/prestamo/listarpendientes";
		Prestamo prestamos[];
		
		RestTemplate restTemplate = new RestTemplate();
		
		prestamos = restTemplate.getForObject(URL, Prestamo[].class);
		
		for (Prestamo prestamo : prestamos)
			System.out.println (prestamo);
	}
}