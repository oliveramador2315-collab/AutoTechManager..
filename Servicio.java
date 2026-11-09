package com.autotech.mode1;

public class MantenimientoBasico extends Servicio {
    public MantenimientoBasico(String nombre, double costoBase) {
        super(nombre, costoBase);
    }

    @Override
    public double calcularCostoTotal() {
        return costoBase; // Sin recargos
    }
}