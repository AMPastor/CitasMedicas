package com.metaenlace.citasmedicas.entitydto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MedicoDTO {
    private long id;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String clave;
    private String numColegiado;
    private List<Long> pacientesId;
    private List<Long> citasId;
}

//pacientesId y citasId son listas identificadores de objetos de clase Paciente y Cita.
//Son mejores que List<Paciente> y List<Cita> xq mejoran la eficiencia en las consultas de la base de datos.




