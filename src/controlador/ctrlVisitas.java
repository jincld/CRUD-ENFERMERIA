
package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Visitas;
import vista.frmVisitas;

public class ctrlVisitas implements MouseListener, KeyListener{
    
    //LLAMAR CAPAS MODELO Y VISTA
    private Visitas modelo;
    private frmVisitas vista;
    
    //CREAR EL CONSTRUCTOR
    public ctrlVisitas(Visitas modelo, frmVisitas vista){
    this.modelo = modelo;
    this.vista = vista;
    
    vista.btnAgregar.addMouseListener(this);
    modelo.Mostrar(vista.jtbPacientes);
    
    vista.btnEliminar.addMouseListener(this);
    vista.jtbPacientes.addMouseListener(this);
    vista.btnEditar.addMouseListener(this);
    vista.btnLimpiar.addMouseListener(this);
    vista.txtBuscar.addKeyListener(this);
    vista.btnBuscar.addMouseListener(this);
    
    } 

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource()==vista.btnAgregar){
       
            modelo.setNOMBRE(vista.txtNombre.getText());
            modelo.setEDAD(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setESPECIALIDAD(vista.txtEspecialidad.getText());
            
            modelo.Guardar();
            modelo.Mostrar(vista.jtbPacientes);
        }
        
        if (e.getSource() == vista.btnEliminar) {
            
            modelo.Eliminar(vista.jtbPacientes);
            modelo.Mostrar(vista.jtbPacientes);
        }
        
        if (e.getSource() == vista.jtbPacientes) {
            
            modelo.cargarDatosTabla(vista);
        }
        
        if (e.getSource() == vista.btnEditar) {
            
            modelo.setNOMBRE(vista.txtNombre.getText());
            modelo.setEDAD(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setESPECIALIDAD(vista.txtEspecialidad.getText());
            
            modelo.Actualizar(vista.jtbPacientes);
            modelo.Mostrar(vista.jtbPacientes);
        }
        
        if (e.getSource() == vista.btnLimpiar) {
            modelo.Limpiar(vista);
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        if (e.getSource() == vista.txtBuscar) {
            modelo.Buscar(vista.jtbPacientes, vista.txtBuscar);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    
}
