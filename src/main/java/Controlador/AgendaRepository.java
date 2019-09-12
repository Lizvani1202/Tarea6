package Controlador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


interface AgendaRepository extends JpaRepository<Agenda,Long>{

}
