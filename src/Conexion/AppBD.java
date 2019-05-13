package Conexion;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AppBD {    
    public static void main(String []args) {
       /* Scanner sc = new Scanner(System.in);
        System.out.println("\nInsertar un Dato a la Base de Datos, Tabla MaestroProveedores");
        System.out.println("NIT: ");
        int id = sc.nextInt();
        System.out.println("Nombre: ");
        String nombre = sc.next();
        System.out.println("Direccion: ");
        String direccion = sc.next();
        System.out.println("Telefono: ");
        int telefono = sc.nextInt();
        System.out.println("Ciudad: ");
        String ciudad = sc.next();
        System.out.println("Tipo: ");
        String tipo = sc.next();
        ProcesarBD bd = new ProcesarBD();
        System.out.println("\nAnexar Dato a la Base de Dato");
        bd.ingresarMaestroProveedores(id, nombre, direccion,telefono,ciudad,tipo);
        System.out.println("\nListar informacion");
        bd.listar();      */
        ProcesarBD bd = new ProcesarBD();
        bd.listar();     
        /*
        ResultSet rs = bd.buscar(2, "SELECT * FROM MaestroProveedores WHERE NIT = ?");
        try {
            //System.out.println("Se encontro a "+rs.getString(1));
            while (rs.next()) 
{ 
    System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " ); 
}
        } catch (SQLException ex) {
            System.err.println("Tenemos problemas");
        }
        */
        Conexion con = new Conexion();
            int Nit=2;    
            String consulta = "SELECT * FROM MaestroProveedores WHERE NIT = "+Nit;
            //consulta ="SELECT * FROM MaestroProveedores WHERE NIT = 2 and NIT = 3"; // Esto da null ya que lo que se busca son llaves primarias en la base de datos
            Statement pstmt;
        try {
            pstmt = con.conectado().createStatement();
            ResultSet resultado = pstmt.executeQuery(consulta); 
            while(resultado.next()){
                System.out.println("Se encontro a la persona "+resultado.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println("Error para buscar");
            System.err.println(ex.toString());
        }
        /*        
        PreparedStatement ps = con.conectado().prepareStatement(find);
            ps.setString(1, NIT + "");

            rs =ps.executeQuery(find);
            ps.execute();
            ps.close();*/
        
        System.out.println("Probar con PreparedStatement");
            String find = "SELECT * FROM MaestroProveedores WHERE NIT = "+3;
            PreparedStatement ps;
        try {
            ps = con.conectado().prepareStatement(find);
            //ps.setString(1, NIT + "");
            ResultSet rs =ps.executeQuery(find);
            System.out.println("rs: "+rs.toString());
            System.out.println("rs.next(): "+rs.next());
            System.out.println("El rs tiene un tamaño de "+rs.getRow());
            while(rs.next()){
                System.out.println("Se encontro a la persona "+rs.getString(1));
            }
            
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AppBD.class.getName()).log(Level.SEVERE, null, ex);
        }

            System.out.println("Por el metodo buscar");
        try {
            System.out.println(bd.buscar( find).next());
        } catch (SQLException ex) {
            System.out.println("Errpr");
        }
            
    }   
    }
    

