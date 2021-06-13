package com.dam.servicio;

import java.util.List;
import java.util.Optional;
import com.dam.modelo.Ejemplar;
import com.dam.modelo.Material;

public interface IMaterialServicio 
{
	public Optional<Material> buscarPorISBN (String isbn);
	public List<Material> buscarPorAutor (String autor);
	public void insertarMaterial (Material material);
	public boolean actualizarMaterial (Material material);
	public boolean borrarMaterial (String isbn);
	public List<Ejemplar> buscarEjemplaresDisponibles (String isbn);
}
