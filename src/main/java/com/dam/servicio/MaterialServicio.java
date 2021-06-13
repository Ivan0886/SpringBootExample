package com.dam.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dam.modelo.Ejemplar;
import com.dam.modelo.Material;
import com.dam.modelo.Prestamo;
import com.dam.repositorio.MaterialRepositorio;
import com.dam.repositorio.PrestamoRepositorio;

@Service
public class MaterialServicio implements IMaterialServicio 
{
	@Autowired MaterialRepositorio materialRepositorio;
	@Autowired PrestamoRepositorio prestamoRepositorio;
	
	@Override
	public Optional<Material> buscarPorISBN(String isbn) 
	{
		return materialRepositorio.findById(isbn);
	}

	
	@Override
	public List<Material> buscarPorAutor(String autor) 
	{
		return materialRepositorio.findByAutor(autor);
	}

	
	@Override
	public void insertarMaterial(Material material) 
	{
		materialRepositorio.save(material);
	}

	
	@Override
	public boolean actualizarMaterial(Material material) 
	{
		boolean exito = false;
		if (materialRepositorio.existsById(material.getIsbn()))
		{
			materialRepositorio.save(material);
			exito = true;
		}
		
		return exito;
	}

	
	@Override
	public boolean borrarMaterial(String isbn) 
	{
		boolean exito = false;
		
		Optional<Material> material = materialRepositorio.findById(isbn); 
		
		// Ver si existe
		if (material.isPresent())
		{
			// Comprobar que no tienes ejemplares pendientes
			boolean puedoBorrar = true;
			Material m = material.get();
			List<Ejemplar> ejemplares = m.getEjemplares();
			
			for(Ejemplar e: ejemplares)
				if(prestamoRepositorio.findByDevueltoAndEjemplar(false, e).size()>0)
					puedoBorrar = false;
			
			// Borrar
			if(puedoBorrar)
			{
				materialRepositorio.deleteById(isbn);
				exito = true;
			}
		}

		return exito;
	}


	@Override
	public List<Ejemplar> buscarEjemplaresDisponibles(String isbn) 
	{
		List<Ejemplar> ejemplaresDisponibles = new ArrayList<>();
		List<Ejemplar> ejemplaresPrestados;
		
		Optional<Material> material = materialRepositorio.findById(isbn); 
		
		if(material.isPresent())
		{
			// Obtener lista de ejemplares no devueltos
			ejemplaresPrestados =  prestamoRepositorio.findByDevueltoAndEjemplarIn(false, material.get().getEjemplares()).
					stream().map(Prestamo::getEjemplar). // convertir prestamos a ejemplares
					collect(Collectors.toList()); // obtener lista de ejemplares
			
			// Obtener lista de ejemplares disponibles
			ejemplaresDisponibles = material.get().getEjemplares(); // todos los ejemplares estan disponibles
			ejemplaresDisponibles.removeIf(e -> ejemplaresPrestados.contains(e)); // quitar los prestados
		}
		
		System.out.println(ejemplaresDisponibles);
		
		return ejemplaresDisponibles;
	}
}