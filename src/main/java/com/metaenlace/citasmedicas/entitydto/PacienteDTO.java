package com.metaenlace.citasmedicas.entitydto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PacienteDTO {
    private long id;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String clave;
    private List<Long> medicosId;
    private List<Long> citasId;
    private String nss;
    private String numTarjeta;
    private String telefono;
    private String direccion;
}

//medicasId y citasId son listas identificadores de objetos de clase Medico y Cita.
//Son mejores que List<Medico> y List<Cita> xq mejoran la eficiencia en las consultas de la base de datos.

