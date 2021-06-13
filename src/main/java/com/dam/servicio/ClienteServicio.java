package com.dam.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.RepositoryInvocationInformation;
import org.springframework.stereotype.Service;

import com.dam.modelo.Cliente;
import com.dam.repositorio.ClienteRepositorio;
import com.dam.repositorio.PrestamoRepositorio;

@Service
public class ClienteServicio implements IClienteServicio 
{
	@Autowired ClienteRepositorio clienteRepositorio;
	@Autowired PrestamoRepositorio prestamoRepositorio;
	
	@Override
	public void insertarCliente(Cliente cliente) 
	{
		clienteRepositorio.save(cliente);
	}

	
	@Override
	public boolean borrarCliente(String dni) 
	{
		boolean exito = false;
		Optional<Cliente> cliente = clienteRepositorio.findById(dni);	
		
		if (cliente.isPresent())
		{
			if (prestamoRepositorio.findByClienteAndDevuelto(cliente.get(), false).isEmpty())
			{
				clienteRepositorio.deleteById(dni);
				exito = true;
			}
		}
		
		return exito;
	}

	
	@Override
	public boolean actualizarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		boolean exito = false;
		if (clienteRepositorio.existsById(cliente.getDni()))
		{
			clienteRepositorio.save(cliente);
			exito = true;
		}
		
		return exito;
	}

	
	@Override
	public Optional<Cliente> buscarPorDNI(String dni) 
	{
		return clienteRepositorio.findById(dni);
	}

	
	@Override
	public List<Cliente> buscarClientesSancionados() 
	{	
		return clienteRepositorio.findByFechaFinSancionAfter(LocalDate.now());
	}

	
	@Override
	public List<Cliente> buscarClientes() 
	{
		return (List<Cliente>) clienteRepositorio.findAll();
	}

	
	@Override
	public boolean clienteDisponible(String dni) 
	{
		boolean disponible = false;
		
		Optional<Cliente> cliente = clienteRepositorio.findById(dni);
		
		if (cliente.isPresent())
			if (cliente.get().getFechaFinSancion() == null || cliente.get().getFechaFinSancion().isBefore(LocalDate.now()))
				if (prestamoRepositorio.findByClienteAndDevuelto(cliente.get(), false).isEmpty())
					disponible = true;		
		
		return disponible;
	}
}