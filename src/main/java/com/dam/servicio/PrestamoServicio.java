package com.dam.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.dam.modelo.Ejemplar;
import com.dam.modelo.Prestamo;
import com.dam.repositorio.PrestamoRepositorio;

@Service
public class PrestamoServicio implements IPrestamoServicio 
{	
	@Autowired PrestamoRepositorio prestamoRepositorio;
	@Autowired IClienteServicio servicioCliente;
	@Autowired IMaterialServicio servicioMaterial;	

	@Override
	public void insertarPrestamo(Prestamo prestamo) 
	{
		prestamoRepositorio.save(prestamo);	
	}

	
	@Override
	public boolean actualizarPrestamo(Prestamo prestamo) 
	{
		boolean exito = false;
		if (prestamoRepositorio.existsById(prestamo.getId()))
		{
			prestamoRepositorio.save(prestamo);
			exito = true;
		}
		
		return exito;
	}

	
	@Override
	public boolean borrarPrestamo(long id) 
	{		
		boolean exito = false;
		if (prestamoRepositorio.existsById(id))
		{
			prestamoRepositorio.deleteById(id);
			exito = true;
		}
			
		return exito;
	}

	
	@Override
	public List<Prestamo> buscarPrestamosPendientes() 
	{
		Sort sort = Sort.by("fechaPrestamo").descending();
		return prestamoRepositorio.findByDevuelto(false,sort);
	}

	
	@Override
	public Optional<Ejemplar> insertarPrestamo(String dni, String isbn) 
	{
		Optional<Ejemplar> ejemplarPrestado = Optional.empty();
		List<Ejemplar> ejemplaresDisponibles;
		Prestamo prestamo;
		
		if(servicioCliente.clienteDisponible(dni))
		{
			ejemplaresDisponibles = servicioMaterial.buscarEjemplaresDisponibles(isbn);
			// Si quedan ejemplares del material disponibles
			if(!ejemplaresDisponibles.isEmpty())
			{
				prestamo = new Prestamo();
				prestamo.setCliente(servicioCliente.buscarPorDNI(dni).get());
				prestamo.setEjemplar(ejemplaresDisponibles.get(0));
				prestamo.setFechaPrestamo(LocalDate.now());
				prestamo.setDevuelto(false);
				
				ejemplarPrestado = Optional.of(ejemplaresDisponibles.get(0));
				
				// anotar prestamo
				prestamoRepositorio.save(prestamo);
			}			
		}
	
		return ejemplarPrestado;
	}
}