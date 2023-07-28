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
        System.out.println("Se crea Ciudades vacio");
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se elimina ciudad UNO en Ciudades vacio: "+ c2.eliminarCiudad(1234));
        System.out.println("Se ingresa ciudad UNO: "+ c2.insertarCiudad(1234,"UNO","UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad UNO: "+ c2.insertarCiudad(1234,"UNO","UNIDAD"));
        System.out.println("Se ingresa ciudad DOS: "+ c2.insertarCiudad(5678,"DOS","UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad TRES: "+ c2.insertarCiudad(9012,"TRES","UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad CUATRO: "+ c2.insertarCiudad(1123,"CUATRO","UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad CINCO: "+ c2.insertarCiudad(1015,"CINCO","UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad SEIS: "+ c2.insertarCiudad(1120,"SEIS","UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad SIETE: "+ c2.insertarCiudad(1017,"SIETE","UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad OCHO: "+ c2.insertarCiudad(6789,"OCHO","UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad NUEVE: "+ c2.insertarCiudad(7890,"NUEVE","UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se elimina ciudad UNO: "+ c2.eliminarCiudad(1234));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se elimina ciudad DOS: "+ c2.eliminarCiudad(5678));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se elimina ciudad CUATRO: "+ c2.eliminarCiudad(1123));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se elimina ciudad NUEVE: "+ c2.eliminarCiudad(7890));
        System.out.println("Ciudades c2: \n" + c2.toString());
    }


}
