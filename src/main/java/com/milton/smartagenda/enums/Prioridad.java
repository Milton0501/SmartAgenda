package com.milton.smartagenda.enums;

public enum Prioridad {

    colorFuerte("#EF4444"),
    colorMedio("#F59E0B"),
    colorBajo("#3B82F6");

    private final String hexColor;

    Prioridad(String hexColor) {
        this.hexColor = hexColor;
    }

    public String getHexColor() {
        return hexColor;
    }
}
