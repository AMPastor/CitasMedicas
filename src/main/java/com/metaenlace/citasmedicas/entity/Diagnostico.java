package com.metaenlace.citasmedicas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "diagnosticos")
public class Diagnostico {

    @Id //Clave primaria tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Se utilizará un identificador generado automáticamente.
    private long id;
    @Column(name = "valoracionEspecialista")
    private String valoracionEspecialista;
    @Column(name = "enfermedad")
    private String enfermedad;

    //Todas las operaciones en un objeto Diagnostico también deben aplicarse al objeto relacionado Cita.
    //Insertar, actualizar o eliminar un objeto Diagnostico hará lo mismo en el objeto relacionado Cita.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cita_id", referencedColumnName = "id")
    private Cita cita;

    public Diagnostico(){}

}

