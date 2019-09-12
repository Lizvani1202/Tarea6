package Controlador;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class AgendaControlador {
    private final AgendaRepository repository;

    public AgendaControlador(AgendaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/Agenda")
    List<Agenda>all(){
        return repository.findAll();
    }

    @PostMapping("/Agenda")
    Agenda newAgenda(@RequestBody Agenda newAgenda){
        return repository.save(newAgenda);
    }

    @GetMapping("/Agenda{id}")
    Agenda one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(()-> new AgendaNotFoundException(id) );
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
