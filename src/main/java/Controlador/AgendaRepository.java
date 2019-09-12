package Controlador;

import Modelo.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

interface AgendaRepository extends JpaRepository<Agenda,Long>{

}
