package com.autotech.service;
import com.autotech.db.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TallerService {

    // REQUISITO: Validar compatibilidad (Caso Hilux)
    public boolean validarCompatibilidad(int idVehiculo, int idMotor) {
        String sql = "SELECT m.marca_compatible, v.marca FROM Motores m, Vehiculos v WHERE m.id_motor = ? AND v.id_vehiculo = ?";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMotor);
            ps.setInt(2, idVehiculo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("marca_compatible").equalsIgnoreCase(rs.getString("marca"));
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return false;
    }

    // REQUISITO: Transacción Atómica (Descontar stock)
    public void procesarCambioMotor(int idOrden, int idVehiculo, int idMotor) {
        if (!validarCompatibilidad(idVehiculo, idMotor)) {
            System.out.println(" ERROR: El motor NO es compatible con el vehiculo.");
            return;
        }

        String sqlStock = "UPDATE Motores SET stock = stock - 1 WHERE id_motor = ? AND stock > 0";
        String sqlDetalle = "INSERT INTO DetalleOrden (id_orden, id_motor, cantidad) VALUES (?, ?, 1)";

        try (Connection conn = ConexionDB.getConexion()) {
            conn.setAutoCommit(false); // INICIA TRANSACCION
            try (PreparedStatement ps1 = conn.prepareStatement(sqlStock);
                 PreparedStatement ps2 = conn.prepareStatement(sqlDetalle)) {
                
                ps1.setInt(1, idMotor);
                if (ps1.executeUpdate() == 0) {
                    throw new SQLException("No hay stock de este motor.");
                }

                ps2.setInt(1, idOrden);
                ps2.setInt(2, idMotor);
                ps2.executeUpdate();

                conn.commit(); // GUARDA LOS CAMBIOS
                System.out.println(" EXITO: Motor asignado y stock descontado.");
            } catch (SQLException ex) {
                conn.rollback(); // REVIERTE SI HAY ERROR
                System.out.println("ERROR EN TRANSACCION: " + ex.getMessage());
            }
        } catch (SQLException e) { System.out.println("Error BD: " + e.getMessage()); }
    }
}