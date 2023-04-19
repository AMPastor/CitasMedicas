package com.metaenlace.citasmedicas.entitydto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CitaDTO {
    private long id;
    private Date fechaHora;
    private String motivoCita;
    private long pacienteId; //Duda
    private long medicoId; //Duda
    //private MedicoDTO medico;
    //private PacienteDTO paciente;
    private long diagnosticoId;
}




