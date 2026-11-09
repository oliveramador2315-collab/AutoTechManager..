package com.mycompany.autotechmanager;

import com.autotech.service.TallerService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutoTechManager {
    public static void main(String[] args) {
        TallerService service = new TallerService();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        
        // Colección requerida
        List<String> historialSimulado = new ArrayList<>();
        historialSimulado.add("Cambio de aceite realizado");

        while (opcion != 3) {
            System.out.println("\n--- AUTO TECH MANAGER ---");
            System.out.println("1. Simular Caso Toyota Hilux (Cambio Motor)");
            System.out.println("2. Mostrar Reporte (Simulado)");
            System.out.println("3. Salir");
            System.out.print("Elija opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("--- INGRESO DE DATOS ---");
                    System.out.print("ID de la Orden (Pon 1): ");
                    int idOrden = sc.nextInt();
                    System.out.print("ID del Vehiculo (Pon 1): ");
                    int idVehiculo = sc.nextInt();
                    System.out.println("Motores disponibles: ID 1 (Toyota), ID 2 (Honda)");
                    System.out.print("ID del Motor a instalar: ");
                    int idMotor = sc.nextInt();

                    service.procesarCambioMotor(idOrden, idVehiculo, idMotor);
                    break;
                case 2:
                    System.out.println("Consulta la Vista 'Vista_Historial' en SQL Server.");
                    System.out.println("Historial en memoria: " + historialSimulado);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }
}