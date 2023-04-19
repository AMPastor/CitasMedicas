package com.metaenlace.citasmedicas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Medico extends Usuario{
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;*/

    @Column(name = "numColegiado")
    private String numColegiado;

    //Relacion mucho a muchos entre Medico y Paciente. @JoinTable es la tabla intermedia
    //La anotación inverseJoinColumns indica que la clave externa de la tabla se llamará "paciente_id" y se asociará con la clave primaria de la tabla Paciente.
    //La propiedad cascade de la anotación @ManyToMany especifica que las operaciones PERSIST y MERGE en un objeto Medico deben propagarse a los objetos relacionados
    // en la lista pacientes. Esto significa que si se agrega o elimina un objeto Paciente de la lista pacientes de un objeto Medico, se persistirán los cambios en la base de datos.
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "medicos_paciente",
            joinColumns = { @JoinColumn(name = "medico_id")},
            inverseJoinColumns = { @JoinColumn(name = "paciente_id")}
    )
    private List<Paciente> pacientes;

    //Lista objetos tipo Cita. Relacion uno a muchos entre médicos y citas.
    @OneToMany(mappedBy="medico")
    private List<Cita> citas;

    public Medico(){
        super();
    }

    public Medico(String numColegiado) {
        super();
        this.numColegiado = numColegiado;
    }

}
