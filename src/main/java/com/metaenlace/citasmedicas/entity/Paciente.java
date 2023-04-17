package com.metaenlace.citasmedicas.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Paciente extends Usuario {

    @Column(name = "numSeguridadSocial")
    private String nss;
    @Column(name = "numTarjeta")
    private String numTarjeta;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "direccion")
    private String direccion;

    /*La anotación @ManyToMany indica que la relación es de muchos a muchos.
    La anotación @JoinTable se utiliza para especificar la tabla intermedia que se utilizará para almacenar la relación.

    La propiedad cascade de la anotación @ManyToMany especifica que las operaciones PERSIST y MERGE en un objeto Paciente deben propagarse
    a los objetos relacionados en la lista medicos. Esto significa que si se agrega o elimina un objeto Medico de la lista medicos de un
    objeto Paciente, se persistirán los cambios en la base de datos.
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "medicos_paciente",
            joinColumns = { @JoinColumn(name = "paciente_id")},
            inverseJoinColumns = { @JoinColumn(name = "medico_id")}
    )
    private List<Medico> medicos;

    @OneToMany(mappedBy="paciente")
    private List<Cita> citas;


    public Paciente(){
        super();
    }

    public Paciente(String nss, String numTarjeta, String telefono, String direccion, List<Medico> medicos) {
        super();
        this.nss = nss;
        this.numTarjeta = numTarjeta;
        this.telefono = telefono;
        this.direccion = direccion;
        this.medicos = medicos;
    }
}

