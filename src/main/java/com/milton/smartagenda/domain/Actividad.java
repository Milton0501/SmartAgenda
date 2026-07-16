package com.milton.smartagenda.domain;

import com.milton.smartagenda.enums.Prioridad;
import com.milton.smartagenda.enums.TipoTarea;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="actividades")
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    @Column(nullable = false)
    private LocalDate fechaProgramada;
    @Column(nullable = false)
    private LocalTime tiempoInicio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Prioridad prioridad;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTarea tipoTarea;
    @Column(nullable = false)
    private boolean completado = false;
    @Column(nullable = false)
    private boolean comparacionTarea = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Actividad() {
    }

    public Actividad(Long id, String titulo, String descripcion, LocalDate fechaProgramada, LocalTime tiempoInicio, Prioridad prioridad, TipoTarea tipoTarea, boolean completado, boolean comparacionTarea, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaProgramada = fechaProgramada;
        this.tiempoInicio = tiempoInicio;
        this.prioridad = prioridad;
        this.tipoTarea = tipoTarea;
        this.completado = completado;
        this.comparacionTarea = comparacionTarea;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDate fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public LocalTime getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(LocalTime tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public TipoTarea getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(TipoTarea tipoTarea) {
        this.tipoTarea = tipoTarea;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public boolean isComparacionTarea() {
        return comparacionTarea;
    }

    public void setComparacionTarea(boolean comparacionTarea) {
        this.comparacionTarea = comparacionTarea;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
