package com.dam.repositorio;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dam.modelo.Cliente;

@Repository
public interface ClienteRepositorio extends CrudRepository<Cliente, String> 
{	
	List<Cliente> findByFechaFinSancionAfter(LocalDate fechaActual);
	List<Cliente> findByFechaFinSancionBefore(LocalDate fechaActual);
}