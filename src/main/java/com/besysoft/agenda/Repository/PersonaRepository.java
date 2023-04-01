package com.besysoft.agenda.Repository;

import com.besysoft.agenda.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    boolean existsByEmail(String email);

    List<Persona> findAllByOrderByApellidoAsc();

    List<Persona> findByCiudadContaining(String ciudad);

    List<Persona> findByNombre(String nombre);

    List<Persona> findByApellido(String apellido);

    List<Persona> findByNombreAndApellido(String nombre, String apellido);
}
