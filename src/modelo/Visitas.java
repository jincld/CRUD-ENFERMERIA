/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.UUID;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import vista.frmVisitas;

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
    
      public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_paciente", "Nombre", "Edad", "Especialidad"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM TBVISITAS");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_PACIENTE"), 
                    rs.getString("NOMBRE"), 
                    rs.getInt("EDAD"), 
                    rs.getString("ESPECIALIDAD")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
      
        public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from TBVISITAS where UUID_PACIENTE = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("Este es el error metodo de eliminar" + e);
        }
    }
          
        public void cargarDatosTabla(frmVisitas vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbPacientes.getSelectedRow();
 
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbPacientes.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbPacientes.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbPacientes.getValueAt(filaSeleccionada, 2).toString();
            String EspecialidadDeTB = vista.jtbPacientes.getValueAt(filaSeleccionada, 3).toString();
 
            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtEspecialidad.setText(EspecialidadDeTB);
        }
    }
        
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update TBVISITAS set NOMBRE = ?, EDAD  = ?, ESPECIALIDAD = ? where UUID_PACIENTE = ?");
 
                updateUser.setString(1, getNOMBRE());
                updateUser.setInt(2, getEDAD());
                updateUser.setString(3, getESPECIALIDAD());
                updateUser.setString(4, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
    
    public void Limpiar(frmVisitas vista){
        
            int filaSeleccionada = vista.jtbPacientes.getSelectedRow();
    
            vista.txtNombre.setText("");
            vista.txtEdad.setText("");
            vista.txtEspecialidad.setText("");
    }
    
    public void Buscar(JTable tabla, JTextField txtBuscar) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Paciente", "Nombre", "Edad", "Especialidad"});
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("SELECT * FROM TBVISITAS WHERE NOMBRE LIKE ? || '%'");
            deleteEstudiante.setString(1, txtBuscar.getText());
            ResultSet rs = deleteEstudiante.executeQuery();
 
             while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_Paciente"), 
                    rs.getString("Nombre"), 
                    rs.getInt("Edad"), 
                    rs.getString("Especialidad")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
 
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }
          
}
