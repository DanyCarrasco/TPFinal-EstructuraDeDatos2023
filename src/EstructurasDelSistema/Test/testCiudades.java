package EstructurasDelSistema.Test;

import EstructurasDelSistema.Ciudades;
import EstructurasDelSistema.SistemaRutas.NodoCiudad;

public class testCiudades {
    public static void main(String[] args) {
        //testToString();
        testInsertarYEliminar();
    }

    public static void testToString() {
        Ciudades c1 = new Ciudades();
        System.out.println("Se crea arbol vacio");
        System.out.println("Ciudad c1: \n" + c1.toString());
        System.out.println("Se ingresa una ciudad");
        c1.insertarCiudad(1234, "UNO", "UNIDAD");
        System.out.println("Ciudad c1: \n" + c1.toString());
    }

    public static void testInsertarYEliminar() {
        Ciudades c2 = new Ciudades();
        System.out.println("Se crea Clientes vacio");
        System.out.println("Ciudad c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad UNO: "+ c2.insertarCiudad(1234,"UNO","UNIDAD"));
        System.out.println("Ciudad c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad UNO: "+ c2.insertarCiudad(1234,"UNO","UNIDAD"));
        System.out.println("Se ingresa ciudad DOS: "+ c2.insertarCiudad(5678,"DOS","UNIDAD"));
        System.out.println("Ciudad c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad TRES: "+ c2.insertarCiudad(9012,"TRES","UNIDAD"));
        System.out.println("Ciudad c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad CUATRO: "+ c2.insertarCiudad(1123,"CUATRO","UNIDAD"));
        System.out.println("Ciudad c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad CINCO: "+ c2.insertarCiudad(1015,"CINCO","UNIDAD"));
        System.out.println("Ciudad c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad SEIS: "+ c2.insertarCiudad(1120,"SEIS","UNIDAD"));
        System.out.println("Ciudad c2: \n" + c2.toString());
    }
}
