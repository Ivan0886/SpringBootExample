package com.dam.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dam.modelo.Ejemplar;
import com.dam.modelo.EstadoEjemplar;

@Repository
public interface EjemplarRepositorio extends CrudRepository<Ejemplar, String> 
{
	@Query (value = "SELECT * FROM ejemplar WHERE isbn=?1" ,nativeQuery=true)
	List<Ejemplar> buscarPorIsbn(String isbn);
	List<Ejemplar> findByEstado(EstadoEjemplar estado);
}
