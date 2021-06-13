package com.dam.servicio;

import java.util.List;
import java.util.Optional;
import com.dam.modelo.Cliente;

public interface IClienteServicio 
{
	public void insertarCliente (Cliente  cliente);
	public boolean borrarCliente (String dni);
	public boolean actualizarCliente (Cliente cliente);
	public Optional<Cliente> buscarPorDNI (String dni);
	public List<Cliente> buscarClientesSancionados();
	public List<Cliente> buscarClientes ();
	public boolean clienteDisponible (String dni);
}