package com.dam.servicio;

import java.util.List;
import java.util.Optional;
import com.dam.modelo.Ejemplar;
import com.dam.modelo.Prestamo;

public interface IPrestamoServicio 
{	
	public void insertarPrestamo (Prestamo prestamo);
	
	public boolean actualizarPrestamo (Prestamo prestamo);
	
	public boolean borrarPrestamo (long id);
	
	public List<Prestamo> buscarPrestamosPendientes ();
	
	public Optional<Ejemplar> insertarPrestamo (String dni, String isbn);
}