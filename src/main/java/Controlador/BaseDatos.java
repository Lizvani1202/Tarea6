package Controlador;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class BaseDatos {

@Bean
    CommandLineRunner introducirBD(AgendaRepository agenda){
    return args -> {
        log.info("Cargando "+ agenda.save(new Agenda("12-02-2019","15:00","Implantologia","Mario Fernandez")));
        log.info("Cargando "+ agenda.save(new Agenda("17-02-2019","12:00","Caries","Oscar Orzolini")));
        };
    }
}
