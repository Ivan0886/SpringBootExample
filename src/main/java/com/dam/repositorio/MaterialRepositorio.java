package com.dam.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dam.modelo.Material;

@Repository
public interface MaterialRepositorio extends CrudRepository<Material, String> 
{
	@Query ("select m from Material m join m.ejemplares e where count(e) > ?1")
	List<Material> buscarPorVariosEjemplares(int varios);
	List<Material> findByAutor(String autor);
}