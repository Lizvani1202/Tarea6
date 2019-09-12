package Modelo;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Agenda {
    private @Id @GeneratedValue Long id;
    private String fecha;
    private String hora;
    private String especialidad;
    private String odontologo;

    public Agenda() {
    }

    public Agenda(String fecha, String hora, String especialidad, String odontologo) {
        this.fecha = fecha;
        this.hora = hora;
        this.especialidad = especialidad;
        this.odontologo = odontologo;
    }
}