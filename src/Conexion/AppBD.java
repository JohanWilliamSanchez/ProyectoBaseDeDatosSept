package Conexion;


import java.util.Scanner;


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
    }   
    }
    

