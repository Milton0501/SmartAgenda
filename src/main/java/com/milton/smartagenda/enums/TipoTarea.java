package com.milton.smartagenda.enums;

public enum TipoTarea {

    MECANICO("Trabajo de mecanica"),
    ESPOSICION("Preparacion de Exposicion"),
    PERSONAL("Asunto Personal"),
    OTROS("Otros");

    private final String nombre;

    TipoTarea(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
