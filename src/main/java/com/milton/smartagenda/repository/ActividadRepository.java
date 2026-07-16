package com.milton.smartagenda.repository;

import com.milton.smartagenda.domain.Actividad;
import com.milton.smartagenda.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    List<Actividad> findByUsuarioAndFechaProgramadaOrderByTiempoInicioAsc(Usuario usuario, LocalDate fecha);
    boolean existsByUsuarioAndFechaProgramadaAndTiempoInicio(Usuario usuario, LocalDate fecha, java.time.LocalTime tiempoInicio);
}
