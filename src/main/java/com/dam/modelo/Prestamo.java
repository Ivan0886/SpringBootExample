package com.dam.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Prestamo 
{
	@Id
	@GeneratedValue (strategy = GenerationType.TABLE)
	private long id;
	private LocalDate fechaPrestamo;
	private boolean devuelto;
	@ManyToOne
	@JoinColumn(name="dni")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name="registro")
	private Ejemplar ejemplar;
}