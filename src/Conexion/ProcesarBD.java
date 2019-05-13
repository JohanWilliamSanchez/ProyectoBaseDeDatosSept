package Conexion;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ProcesarBD {

    private PreparedStatement ps;

    private Conexion con;

    public ProcesarBD() {
        System.out.println("entro");
        this.con = new Conexion();
    }

    public void  ingresarMaestroProveedores(int id, String nombre, String direccion, int telefono, String ciudad, String tipo) {
        String datos[] = {Integer.toString(id), nombre, direccion, Integer.toString(telefono), ciudad, tipo};
        insertar(datos, "INSERT INTO MaestroProveedores(NIT, Nombre,Direccion,Telefono,Cuidad,Tipo) VALUES(?,?,?,?,?,?)");

    }
    public void  ingresarComprobantes(String  id, String descripcion,  String tipo, int cuenta) {
        String datos[] = {id, descripcion, tipo, Integer.toString(cuenta)};
        insertar(datos, "INSERT INTO Comprobantes(Comprobante, Descripcion, Tipo, Cuenta) VALUES(?,?,?,?)");

    }
    public void  ingresarCentroCosto(int  id, String descripcion,   int cuenta) {
        String datos[] = {Integer.toString(id), descripcion,  Integer.toString(cuenta)};
        insertar(datos, "INSERT INTO CentroCosto(ID, Descripcion, Cuenta) VALUES(?,?,?)");
     }
    private boolean insertar(String datos[], String insert) {
        boolean estado = false;

        try {	
            PreparedStatement ps = con.conectado().prepareStatement(insert);

            for (int i = 0; i <= datos.length - 1; i++) {
                ps.setString(i + 1, datos[i]);
            }
            ps.execute();
            ps.close();
            estado = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return estado;
    }

    public void listar() {
        String strConsulta = "SELECT * FROM MaestroProveedores";
        try {
            PreparedStatement ps = con.conectado().prepareStatement(strConsulta);
            ResultSet res = ps.executeQuery();

            int NIT = 0;
            String Nombre = "";
            String Direccion = "";
            int Telefono = 0;
            String Cuidad = "";
            String Tipo = "";
            while (res.next()) {
                NIT = res.getInt("NIT");
                Nombre = res.getString("Nombre");
                Direccion = res.getString("Direccion");
                Telefono = res.getInt("Telefono");
                Cuidad = res.getString("Cuidad");
                Tipo = res.getString("Tipo");

                System.out.println(NIT + "\t" + Nombre + "\t" + Direccion + "\t" + Telefono + "\t" + Cuidad + "\t" + Tipo);
            }
            res.close();

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void  buscarMaestroProveedores(int id,JTextField nit,JTextField nombre, JTextField direccion, JTextField telefono, JTextField ciudad, JTextField tipo) {
            System.out.println("HolaBusqueda");
        try {
         ResultSet rs1 = buscar( "SELECT * FROM MaestroProveedores WHERE NIT = "+id);          
            System.out.println("HolaBusqueda2");
            //System.out.println("rs1.next(): "+rs1.next());
            System.out.println("Salto");
            if(rs1.next()){
                nit.setText(rs1.getString("NIT")); //Son los nombres del atributo de la base de datos
                nombre.setText(rs1.getString("Nombre"));
                direccion.setText(rs1.getString("Direccion"));
                telefono.setText(rs1.getString("Telefono"));
                ciudad.setText(rs1.getString("Cuidad"));
                tipo.setText(rs1.getString("Tipo"));                
            } else {
                JOptionPane.showMessageDialog(null, "No existe una persona con la clave");
            }
        } catch (SQLException ex) {
            System.out.println("Error en el Buscar pBD");
            System.err.println(ex);
        }
    }
    public void  buscarComprobantes(int id) {
        buscar( "SELECT * FROM Comprobantes WHERE Comprobante = "+id);

    } 
    public void  buscarCentroCosto(int id) {
        buscar( "SELECT * FROM MaestroProveedores WHERE ID = "+id);

    }
    
     public ResultSet buscar(String find) {

        ResultSet rs = null;
        try {
         
            PreparedStatement ps = con.conectado().prepareStatement(find);

            rs =ps.executeQuery(find);
            
            /*while(rs.next()){
                System.out.println("Se encontro a la persona "+rs.getString(2));
                
            }*/
            
            
            
            /*ps.execute();
            ps.close();*/

        } catch (Exception e) {
            System.out.println("Error en el buscarMaestroProveedores");
            System.err.println(e);
        }
        return rs;

    }
    public void eliminarMaestroProveedores(int id) {
        eliminar(id, "DELETE FROM MaestroProveedores WHERE NIT=? ");
    }
    public void eliminarComprobantes(int id) {
        eliminar(id, "DELETE FROM Comprobantes WHERE Comprobante=? ");

    }
    public void eliminarCentroCosto(int id) {
        eliminar(id, "DELETE FROM CentroCosto WHERE ID=? ");

    }
    private boolean eliminar(int id, String delete) {
        boolean estado = false;

        try {
            PreparedStatement ps = con.conectado().prepareStatement(delete);

            ps.setInt(1, id);

            ps.execute();
            ps.close();
            estado = true;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return estado;

    }
    
    public void busquedaTablaMProvee(int id,JTextField nit,JTextField nombre, JTextField direccion, JTextField telefono, JTextField ciudad, JTextField tipo){
        ResultSet rs = null;
        try {
         
            String find = "SELECT * FROM MaestroProveedores WHERE NIT = "+id;
            PreparedStatement ps = con.conectado().prepareStatement(find);

            rs =ps.executeQuery(find);
            
            while(rs.next()){
                System.out.println("Se encontro a la persona "+rs.getString(2));
                
            }
            
            if(rs.next()){
                nit.setText(rs.getString("NIT")); //Son los nombres del atributo de la base de datos
                nombre.setText(rs.getString("Nombre"));
                direccion.setText(rs.getString("Direccion"));
                telefono.setText(rs.getString("Telefono"));
                ciudad.setText(rs.getString("Cuidad"));
                tipo.setText(rs.getString("Tipo"));                
            } else {
                JOptionPane.showMessageDialog(null, "No existe una persona con la clave");
            }
            
            /*ps.execute();
            ps.close();*/

        } catch (Exception e) {
            System.out.println("Error en el buscarMaestroProveedores");
            System.err.println(e);
        }
    }
}
