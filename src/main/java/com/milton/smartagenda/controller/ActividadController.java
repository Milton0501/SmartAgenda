package com.milton.smartagenda.controller;

import com.milton.smartagenda.domain.Actividad;
import com.milton.smartagenda.domain.Usuario;
import com.milton.smartagenda.repository.UsuarioRepository;
import com.milton.smartagenda.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
@Controller
@RequestMapping("/agenda")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String index(Model model) {
        Usuario usuarioDefault = usuarioRepository.findByNombre("Milton")
                .orElseThrow(() -> new RuntimeException("Usuario de prueba no encontrado."));

        LocalDate hoy = LocalDate.now();
        List<Actividad> actividades = actividadService.obtenerActividadesDelDia(usuarioDefault, hoy);

        model.addAttribute("tasks", actividades);
        model.addAttribute("usuario", usuarioDefault);
        return "index";
    }

    // Guardar una nueva actividad
    @PostMapping("/tasks")
    public String guardarActividad(@ModelAttribute Actividad nuevaActividad, RedirectAttributes redirectAttributes) {
        Usuario usuarioDefault = usuarioRepository.findByNombre("Milton").orElse(null);

        nuevaActividad.setUsuario(usuarioDefault);
        nuevaActividad.setFechaProgramada(LocalDate.now());
        nuevaActividad.setCompletado(false);
        nuevaActividad.setComparacionTarea(false);

        actividadService.guardarActividad(nuevaActividad);

        redirectAttributes.addFlashAttribute("mensajeExito", "¡Se ha agendado una nueva actividad correctamente!");

        if (nuevaActividad.getTipoTarea().name().equals("MECANICO") ||
                nuevaActividad.getTipoTarea().name().equals("ESPOSICION")) {
            redirectAttributes.addFlashAttribute("companionAlert", "Tu copiloto ha analizado tu actividad de " + nuevaActividad.getTipoTarea().getNombre() + " y ha agendado un bloque de preparación previo.");
        }

        return "redirect:/agenda";
    }

    @PostMapping("/tasks/{id}/toggle")
    public String toggleActividad(@PathVariable Long id) {
        actividadService.alternarCompletado(id);
        // Redirige correctamente a la agenda
        return "redirect:/agenda";
    }

    @PostMapping("/tasks/{id}/delete")
    public String eliminarActividad(@PathVariable Long id) {
        actividadService.eliminarActividad(id);
        // Redirige correctamente a la agenda
        return "redirect:/agenda";
    }
}
