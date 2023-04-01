package com.besysoft.agenda.Repository;

import com.besysoft.agenda.Entity.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {

    boolean existsById(Long id);

    boolean existsByEmpresaId(Long idEmpresa);

    boolean existsByPersonaId(Long idPersona);

    List<Contacto> findByEmpresa_Id(Long idEmpresa);

    List<Contacto> findAllByOrderByPersona_Apellido();

}
