package com.milton.smartagenda.service;

import com.milton.smartagenda.domain.Actividad;
import com.milton.smartagenda.domain.Usuario;
import com.milton.smartagenda.enums.TipoTarea;
import com.milton.smartagenda.enums.Prioridad;
import com.milton.smartagenda.repository.ActividadRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    public List<Actividad> obtenerActividadesDelDia(Usuario usuario, LocalDate fecha){
        return actividadRepository.findByUsuarioAndFechaProgramadaOrderByTiempoInicioAsc(usuario, fecha);
    }

    @Transactional
    public Actividad guardarActividad(Actividad actividad) {
        Actividad actividadGuardada = actividadRepository.save(actividad);
        generarSugerenciaCopiloto(actividadGuardada);
        return actividadGuardada;
    }

    @Transactional
    public void alternarCompletado(Long id) {
        actividadRepository.findById(id).ifPresent(actividad -> {
            actividad.setCompletado(!actividad.isCompletado());
            actividadRepository.save(actividad);
        });
    }

    @Transactional
    public void eliminarActividad(Long id) {
        actividadRepository.deleteById(id);
    }

    private void generarSugerenciaCopiloto(Actividad actividadPrincipal) {
        TipoTarea tipo = actividadPrincipal.getTipoTarea();
        Usuario usuario = actividadPrincipal.getUsuario();
        LocalDate fecha = actividadPrincipal.getFechaProgramada();
        LocalTime horaPrincipal = actividadPrincipal.getTiempoInicio();

        if (tipo == TipoTarea.MECANICO) {
            LocalTime horaSugerida = horaPrincipal.minusHours(1);

            if (!actividadRepository.existsByUsuarioAndFechaProgramadaAndTiempoInicio(usuario, fecha, horaSugerida)) {
                Actividad sugerencia = new Actividad(
                        null,
                        "🤖 Copiloto: Alistamiento de herramientas y stock",
                        "Prepara la tarjeta de propiedad del vehículo, haz una lista con los fallos o ruidos extraños que has detectado recientemente y asegúrate de dejar la llave de repuesto a la mano. " + actividadPrincipal.getTitulo(),
                        fecha,
                        horaSugerida,
                        Prioridad.colorBajo, //
                        TipoTarea.OTROS,
                        false,
                        true, //
                        usuario
                );
                actividadRepository.save(sugerencia);
            }
        }

        else if (tipo == TipoTarea.ESPOSICION) {
            LocalTime horaSugerida = horaPrincipal.minusHours(2);

            if (!actividadRepository.existsByUsuarioAndFechaProgramadaAndTiempoInicio(usuario, fecha, horaSugerida)) {
                Actividad sugerencia = new Actividad(
                        null,
                        "🤖 Copiloto: Bloque de enfoque y repaso mental",
                        "Silencia notificaciones. Haz un repaso rápido de tus diapositivas, estructura tu speech de inicio y respira hondo antes de: " + actividadPrincipal.getTitulo(),
                        fecha,
                        horaSugerida,
                        Prioridad.colorMedio,
                        TipoTarea.OTROS,
                        false,
                        true,
                        usuario
                );
                actividadRepository.save(sugerencia);
            }
        }
    }
}
