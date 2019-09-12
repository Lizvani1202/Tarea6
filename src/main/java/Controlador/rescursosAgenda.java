package Controlador;

import Controlador.Agenda;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
class rescursosAgenda implements ResourceAssembler<Agenda, Resource<Agenda>> {

    @Override
    public Resource<Agenda> toResource(Agenda agenda) {
        return new Resource<>(agenda,
                linkTo(methodOn(AgendaControlador.class).one(agenda.getId())).withSelfRel(),
                linkTo(methodOn(AgendaControlador.class).all()).withRel("employees"));
    }
}
