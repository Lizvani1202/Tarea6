package Controlador;


import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
class AgendaControlador {
    private final AgendaRepository repository;

    public AgendaControlador(AgendaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/Agenda")
    Resource<Resource<Agenda>> all(){
        List<Resource<Agenda>> resources = repository.findAll().stream()
                .map(agenda -> new Resource<>(agenda,
                        linkTo(methodOn(AgendaControlador.class).one(agenda.getId())).withSelfRel(),
                        linkTo(methodOn(AgendaControlador.class).all()).withRel("employees")))
                .collect(Collectors.toList());
        return new Resource<>(resources,
                linkTo(methodOn(AgendaControlador.class).all()).withSelfRel());

    }


    @PostMapping("/Agenda")
    Agenda newAgenda(@RequestBody Agenda newAgenda){
        return repository.save(newAgenda);
    }

    @GetMapping("/Agenda{id}")
    Resource one(@PathVariable Long id){
        Agenda agenda = repository.findById(id)
                .orElseThrow(()->new AgendaNotFoundException((id)));

        return new Resource(agenda,
                linkTo(methodON(AgendaControlador.class).one(id)).withSelfRel(),
                linkTo(methodON(AgendaControlador.class).all()).withRel("agenda"));

    }
    @PutMapping("/Agenda/{id}")
    Agenda reemplazarAgenda(@RequestBody Agenda newAgenda, @PathVariable Long id){

        return repository.findById(id)
                .map(agenda -> {
                    agenda.setFecha(newAgenda.getFecha());
                    agenda.setHora(newAgenda.getHora());
                    agenda.setTratamiento(newAgenda.getTratamiento());
                    agenda.setOdontologo(newAgenda.getOdontologo());
                    return repository.save(agenda);
                }).orElseGet(()->{
                  newAgenda.setId(id);
                  return repository.save(newAgenda);
        });
    }
    @DeleteMapping("/Agenda/{id}")
    void deleteAgenda(@PathVariable Long id){
        repository.deleteById(id);
    }
}
