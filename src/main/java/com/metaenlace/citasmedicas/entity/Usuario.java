package com.metaenlace.citasmedicas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
@Inheritance(strategy=InheritanceType.JOINED)
//Abstract xq es una clase base que proporciona atributos comunes a las dos clases q extiende (medico y paciente)
public abstract class Usuario {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_seq_gen")
    @SequenceGenerator(name = "usuarios_seq_gen", sequenceName = "usuarios_seq", allocationSize = 1)
    private long id;

    //private long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "clave")
    private String clave;

}


