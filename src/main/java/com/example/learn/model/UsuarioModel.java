package com.example.learn.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Data

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @NotEmpty(message = "Nombre es requerido")
    private String nombre;
    @NotEmpty(message = "Apellido es requerido")
    private String apellido;
    @NotEmpty(message = "Correo es requerido")
    private String correo;
    @Size(min = 0, max = 10)
    @NotEmpty(message = "Telefono es requerido")
    private String telefono;
    @NotEmpty(message = "Contraseña es requerido")
    @Column(name = "password")
    private String pass;
    
}
