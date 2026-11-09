package com.autotech.mode1;

public class CambioMotor extends Servicio {
    private double costoRepuesto;

    public CambioMotor(String nombre, double costoBase, double costoRepuesto) {
        super(nombre, costoBase);
        this.costoRepuesto = costoRepuesto;
    }

    @Override
    public double calcularCostoTotal() {
        return costoBase + costoRepuesto; // Mano de obra + Repuesto
    }
}