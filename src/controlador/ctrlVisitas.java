
package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Visitas;
import vista.frmVisitas;

public class ctrlVisitas implements MouseListener{
    
    //LLAMAR CAPAS MODELO Y VISTA
    private Visitas modelo;
    private frmVisitas vista;
    
    //CREAR EL CONSTRUCTOR
    public ctrlVisitas(Visitas modelo, frmVisitas vista){
    this.modelo = modelo;
    this.vista = vista;
    
    vista.btnAgregar.addMouseListener(this);
    } 

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource()==vista.btnAgregar){
       
            modelo.setNOMBRE(vista.txtNombre.getText());
            modelo.setEDAD(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setESPECIALIDAD(vista.txtEspecialidad.getText());
            
            modelo.Guardar();
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
    
}
