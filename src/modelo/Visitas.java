/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.UUID;
import java.sql.*;

/**
 *
 * @author Estudiante
 */
public class Visitas {
    //PARAMETROS DE LA TABLA DE LA BASE
    private String UUID_PACIENTE;
    private String NOMBRE;
    private int EDAD;
    private String ESPECIALIDAD;
    
    //GETTERS Y SETTERS

    public String getUUID_PACIENTE() {
        return UUID_PACIENTE;
    }

    public void setUUID_PACIENTE(String UUID_PACIENTE) {
        this.UUID_PACIENTE = UUID_PACIENTE;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public int getEDAD() {
        return EDAD;
    }

    public void setEDAD(int EDAD) {
        this.EDAD = EDAD;
    }

    public String getESPECIALIDAD() {
        return ESPECIALIDAD;
    }

    public void setESPECIALIDAD(String ESPECIALIDAD) {
        this.ESPECIALIDAD = ESPECIALIDAD;
    }
    
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addProducto = conexion.prepareStatement("INSERT INTO TBVISITAS(UUID_PACIENTE, NOMBRE, EDAD, ESPECIALIDAD) VALUES (?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addProducto.setString(1, UUID.randomUUID().toString());
            addProducto.setString(2, getNOMBRE());
            addProducto.setInt(3, getEDAD());
            addProducto.setString(4, getESPECIALIDAD());
            //Ejecutar la consulta
            addProducto.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
}
