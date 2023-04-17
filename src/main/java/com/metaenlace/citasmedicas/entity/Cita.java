package com.metaenlace.citasmedicas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Se utilizará un identificador generado automáticamente.
    private long id;
    @Column(name = "fechaHora")
    private Date fechaHora;
    @Column(name = "motivoCita")
    private String motivoCita;

    //Relaciones

    //@JoinColumn se utiliza para especificar la columna que se utilizará como clave externa para mantener la relación.
    @ManyToOne
    @JoinColumn(name="paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name="medico_id")
    private Medico medico;

    //Todas las operaciones en un objeto Cita también deben aplicarse al objeto relacionado Diagnostico
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "diagnostico_id", referencedColumnName = "id")
    private Diagnostico diagnostico;

    //Hay dos constructroes para facilitar la creacion de instancias de la clase Cita, dependiendo de si se tienen o no los valores iniciales para los atributos
    public Cita(){}

    public Cita(Date fechaHora, String motivoCita) {
        super();
        this.fechaHora = fechaHora;
        this.motivoCita = motivoCita;
    }

}
