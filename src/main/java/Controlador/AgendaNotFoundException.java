package Controlador;

public class AgendaNotFoundException extends RuntimeException {

    AgendaNotFoundException(Long id){
        super("No se pudo encontrar la cita"+id);

    }
}
