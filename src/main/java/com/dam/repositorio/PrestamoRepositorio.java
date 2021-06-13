package com.dam.repositorio;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dam.modelo.Cliente;
import com.dam.modelo.Ejemplar;
import com.dam.modelo.Prestamo;

@Repository
public interface PrestamoRepositorio extends CrudRepository<Prestamo, Long> 
{
	List<Prestamo> findByDevuelto(boolean devuelto);
	List<Prestamo> findByDevuelto(boolean devuelto, Sort sort);
 	List<Prestamo> findByDevueltoAndEjemplar(boolean devuelto, Ejemplar ejemplar);
	List<Prestamo> findByCliente(Cliente cliente);
	List<Prestamo> findByEjemplar(Ejemplar ejemplar);
	List<Prestamo> findByFechaPrestamo(LocalDate fechaPrestamo);
	List<Prestamo> findByFechaPrestamoAfter(LocalDate fechaPrestamo);
	List<Prestamo> findByFechaPrestamoBetween(LocalDate fechaInicio, LocalDate fechaFin);
	List<Prestamo> findByClienteAndDevuelto(Cliente cliente, boolean devuelto);
	List<Prestamo> findByDevueltoAndEjemplarIn(boolean devuelto, List<Ejemplar> ejemplares);
}