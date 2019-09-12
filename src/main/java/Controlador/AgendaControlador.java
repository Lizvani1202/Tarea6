package Controlador;


import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
class AgendaControlador {
    private final AgendaRepository repository;
    private final rescursosAgenda rescursosAgenda;

    public AgendaControlador(AgendaRepository repository, Controlador.rescursosAgenda rescursosAgenda) {
        this.repository = repository;
        this.rescursosAgenda = rescursosAgenda;
    }

    public AgendaRepository getRepository() {
        return repository;
    }

    public Controlador.rescursosAgenda getRescursosAgenda() {
        return rescursosAgenda;
    }

    @GetMapping("/Agenda")
    Resource<Resource<Agenda>> all(){

        List<Resource<Agenda>> resources = repository.findAll().stream()
                .map(rescursosAgenda::toResource)
                .collect(Collectors.toList());
        return new org.springframework.hateoas.Resource<>(resources,
                linkTo(methodOn(AgendaControlador.class).all()).withSelfRel());

    }


    @PostMapping("/Agenda")
    Agenda newAgenda(@RequestBody Agenda newAgenda){
        return repository.save(newAgenda);
    }

    @GetMapping("/Agenda{id}")
    org.springframework.hateoas.Resource<Agenda> one(@PathVariable Long id){
        Agenda agenda = repository.findById(id)
                .orElseThrow(()->new AgendaNotFoundException((id)));

       return rescursosAgenda.toResource(agenda);

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
