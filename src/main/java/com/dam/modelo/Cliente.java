package com.dam.modelo;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente 
{
	@Id
	@Column(length=9)
	@EqualsAndHashCode.Include
	@NonNull
	private String dni;
	@Column(length=50) private String nombre;
	@Column(length=50) private String email;
	private LocalDate fechaNacimiento;
	private LocalDate fechaFinSancion; // fecha de fin de sanción
}
