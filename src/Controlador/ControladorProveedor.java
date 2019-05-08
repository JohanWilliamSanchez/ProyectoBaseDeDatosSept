/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexion.ProcesarBD;
import Modelo.Proveedor;
import Vista.Formulario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.events.MouseEvent;

/**
 *
 * @author Johan Sánchez
 */
public class ControladorProveedor implements ActionListener, FocusListener, MouseListener {
    
    private Formulario vista;
    private  Proveedor proveedor;
    private DefaultTableModel tablaProv = new DefaultTableModel();
    private int seleccion;

    public ControladorProveedor(Formulario vista) {
        this.vista = vista;
        //this.proveedor = proveedor;
        
        vista.jButtonActualizarP.addActionListener(this);
        vista.jButtonAgregarP.addActionListener(this);
        vista.jButtonEliminarP.addActionListener(this);
        vista.jButtonBuscarP.addActionListener(this);
        
        vista.jTextNitP.addActionListener(this);
        vista.jTextNitP.addFocusListener(this);
        
        //vista.jTableProveedores.addFocusListener(this);
        vista.jTableProveedores.addMouseListener(this);
        
        vista.setTitle("Formulario");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        
         vista.jButtonActualizarP.setEnabled(false);
        //vista.jButtonAgregarP.setEnabled(false);
        vista.jButtonEliminarP.setEnabled(false);
        vista.jButtonBuscarP.setEnabled(false);
        
       
       llenarTabla();
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField campoTexto=vista.jTextNitP;
        
            int nit;
            String nombre;
            String direccion;
            int telefono;
            String ciudad;
            String tipo;
            ResultSet rs;
            ProcesarBD pBD = new ProcesarBD();
        
        
        if(campoTexto.getText().length()==0){
         System.out.println("No hay texto");
        }
        else{
         System.out.println("Si hay texto");
        }
        
        if(e.getSource()==vista.jButtonAgregarP){
            
            System.out.println("Boton te dice hola");
            nit=Integer.parseInt(vista.jTextNitP.getText());
            nombre=vista.jTextFieldNombreP.getText();
            direccion=vista.jTextFieldDireccionP.getText();
            telefono=Integer.parseInt(vista.jTextFieldTelefonoP.getText());
            ciudad=vista.jTextFieldCiudadP.getText();
            tipo=vista.jTextFieldTipoP.getText();
            System.out.println("EL nombre es "+nombre);
            if(vista.jTextNitP.getText().length()!=0 &&
                    vista.jTextFieldTelefonoP.getText().length()!=0 &&
                     vista.jTextFieldNombreP.getText().length()!=0 &&
                        vista.jTextFieldDireccionP.getText().length()!=0 &&
                         vista.jTextFieldCiudadP.getText().length()!=0 &&
                             vista.jTextFieldTipoP.getText().length()!=0 ){
                
                proveedor = new Proveedor(nit, telefono, nombre, direccion, ciudad, tipo);
                String [] datos ={nit+"",  nombre, direccion,telefono+"", ciudad, tipo};
                tablaProv.addRow(datos);
                
                System.out.println(proveedor.toString());
                volverNulo();
                inhabilitar();
            }else{
               JOptionPane.showMessageDialog(null, "Hay campos vacios, deben llenarse todos los campos", 
                       "Tenemos un 3312", JOptionPane.WARNING_MESSAGE);
            }
            
        if(e.getSource()==vista.jButtonEliminarP){
            int respueta = JOptionPane.showConfirmDialog(null, "¿Desea Elimanrar este Proveedor?");
            if(respueta==JOptionPane.YES_OPTION){
                tablaProv.removeRow(seleccion);
                JOptionPane.showInputDialog(null, "Se elimino Exitosamente");
                seleccion=-1;
                vista.jButtonEliminarP.setEnabled(false);
                volverNulo();
            }
        }
                
        if(e.getSource()==vista.jButtonActualizarP){

        nit=Integer.parseInt(vista.jTextNitP.getText());
        nombre=vista.jTextFieldNombreP.getText();
        direccion=vista.jTextFieldDireccionP.getText();
        telefono=Integer.parseInt(vista.jTextFieldTelefonoP.getText());
        ciudad=vista.jTextFieldCiudadP.getText();
        tipo=vista.jTextFieldTipoP.getText();
        Object [] l = {nit,nombre,direccion,telefono,ciudad,tipo};
            for (int i = 0; i < 6; i++) {
                tablaProv.setValueAt(l[i], seleccion, i);
            }
           vista.jButtonActualizarP.setEnabled(false);
                volverNulo();
            }
        }
        
        if(e.getSource()==vista.jButtonBuscarP){
            nit=Integer.parseInt(vista.jTextNitP.getText());
            pBD.buscarMaestroProveedores(nit, vista.jTextNitP, vista.jTextFieldNombreP, vista.jTextFieldDireccionP, vista.jTextFieldTelefonoP, vista.jTextFieldCiudadP, vista.jTextFieldTipoP);
        }
        
    }

    @Override
    public void focusGained(FocusEvent e) {
        JTextField campoTexto=vista.jTextNitP;
        if(e.getSource()==vista.jTextNitP){
            System.out.println("tengo el foco :* :D 8D");
            
        }
        
        if(e.getSource()==vista.jTabProveedores){
            System.out.println("La tabla gano el foco");
        }
        
        System.out.println("Cualquier evento jajaaja");
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField campoTexto=vista.jTextNitP;
        if(e.getSource()==vista.jTextNitP){
            System.out.println("Ya no tengo el foco :c ");
            if(campoTexto.getText().length()!=0){
                vista.jTextFieldNombreP.setEditable(true);
                 vista.jTextFieldCiudadP.setEditable(true);
                  vista.jTextFieldTelefonoP.setEditable(true);
                   vista.jTextFieldDireccionP.setEditable(true);
                    vista.jTextFieldTipoP.setEditable(true);
                    
                    
            }else{

               inhabilitar();
                    
            }
        }
        
        
                    }
 
/**
 * Este metodo permite que a todos los campos de texto se les de el valor de null
 */   
    private void volverNulo(){
        vista.jTextNitP.setText(null);
                        vista.jTextFieldTelefonoP.setText(null);
                         vista.jTextFieldNombreP.setText(null);
                            vista.jTextFieldDireccionP.setText(null);
                             vista.jTextFieldCiudadP.setText(null);
                                 vista.jTextFieldTipoP.setText(null);
    }

    private void inhabilitar(){
        vista.jTextFieldNombreP.setEditable(false);
                     vista.jTextFieldCiudadP.setEditable(false);
                      vista.jTextFieldTelefonoP.setEditable(false);
                       vista.jTextFieldDireccionP.setEditable(false);
                        vista.jTextFieldTipoP.setEditable(false);
    }

    private void llenarTabla(){
        
        tablaProv.addColumn("Nit");
        tablaProv.addColumn("Nombre");
        tablaProv.addColumn("Dirección");
        tablaProv.addColumn("Telefono");
        tablaProv.addColumn("Ciudad");
        tablaProv.addColumn("Tipo");
       /* String [] nulos={"","","","","",""};
        for (int i = 0; i < 12; i++) {
        tablaProv.addRow(nulos);
        }*/
            
         vista.jTableProveedores.setModel(tablaProv);
         vista.jTableProveedores.setEnabled(false);
         
    }
    /*
    private void tablaClick(java.awt.event.MouseEvent mE){
       // int seleccion = tablaProv.
       
       seleccion=vista.jTableProveedores.getSelectedRow();
       
       if(seleccion!=-1){
           vista.jTextNitP.setText(vista.jTableProveedores.getValueAt(seleccion, 0).toString());
           vista.jTextFieldNombreP.setText(vista.jTableProveedores.getValueAt(seleccion, 1).toString());
           vista.jTextFieldDireccionP.setText(vista.jTableProveedores.getValueAt(seleccion, 2).toString());
           vista.jTextFieldTelefonoP.setText(vista.jTableProveedores.getValueAt(seleccion, 3).toString());
           vista.jTextFieldCiudadP.setText(vista.jTableProveedores.getValueAt(seleccion, 4).toString());
           vista.jTextFieldTipoP.setText(vista.jTableProveedores.getValueAt(seleccion, 5).toString());
           
           vista.jButtonActualizarP.setEnabled(true);
           vista.jButtonEliminarP.setEnabled(true);
       }
    }*/

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*if(e.getSource()==vista.jTabProveedores){
            System.out.println("CLiquie la tabla");
        }*/
        seleccion=vista.jTableProveedores.getSelectedRow();
        System.out.println(seleccion);
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
