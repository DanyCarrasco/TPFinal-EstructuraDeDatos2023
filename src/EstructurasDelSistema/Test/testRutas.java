package EstructurasDelSistema.Test;

import EstructurasDelSistema.SistemaRutas.Rutas;
import clases.Ciudad;

public class testRutas {
    public static void main(String[] args) {
        //testToString();
        testInsertarYEliminar();

    }

    public static void testToString(){
        System.out.println("Prueba de toString");
        Ciudad uno = new Ciudad(1234, "UNO", "UNIDAD");
        Ciudad dos = new Ciudad(5678, "DOS", "UNIDAD");
        Rutas r1 = new Rutas();
        System.out.println("Rutas r1 vacio: \n"+ r1.toString());
        System.out.println("Ingreso una ciudad");
        r1.insertarCiudad(uno);
        System.out.println("Rutas r1: \n"+ r1.toString());
        System.out.println("Ingreso otra ciudad");
        r1.insertarCiudad(dos);
        System.out.println("Rutas r1: \n"+ r1.toString());
        System.out.println("Ingreso un arco de UNO a DOS");
        r1.insertarArco(uno, dos, 24.5);
        System.out.println("Rutas r1: \n"+ r1.toString());
    }

    public static void testInsertarYEliminar(){
        System.out.println("Prueba de insertar y eliminar ciudades");
        Ciudad uno = new Ciudad(1234,"UNO", "UNIDAD");
        Ciudad dos = new Ciudad(5678,"DOS", "UNIDAD");
        Ciudad tres = new Ciudad(9012,"TRES", "UNIDAD");
        Ciudad cuatro = new Ciudad(3456,"CUATRO", "UNIDAD");
        Ciudad cinco = new Ciudad(7890,"CINCO", "UNIDAD");
        Ciudad seis = new Ciudad(2345,"SEIS", "UNIDAD");
        Ciudad siete = new Ciudad(6789,"SIETE", "UNIDAD");
        Rutas r2 = new Rutas();
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Inserto UNO en Rutas: "+ r2.insertarCiudad(uno));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Inserto UNO en Rutas: "+ r2.insertarCiudad(uno));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Inserto DOS en Rutas: "+ r2.insertarCiudad(dos));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Inserto TRES en Rutas: "+ r2.insertarCiudad(tres));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Inserto CUATRO en Rutas: "+ r2.insertarCiudad(cuatro));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Inserto CINCO en Rutas: "+ r2.insertarCiudad(cinco));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Inserto SEIS en Rutas: "+ r2.insertarCiudad(seis));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Elimino SEIS en Rutas: "+ r2.eliminarCiudad(seis));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Elimino CINCO en Rutas: "+ r2.eliminarCiudad(cinco));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Inserto SIETE en Rutas: "+ r2.insertarCiudad(siete));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Elimino CINCO en Rutas (no se encuentra en Rutas): "+ r2.eliminarCiudad(cinco));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("-----------------------------------------------------");

        System.out.println("Insertar y eliminar arcos");
        System.out.println("Inserto arco de UNO a DOS con 10.5: " + r2.insertarArco(uno, dos,10.5));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Inserto arco de UNO a CUATRO con 11.6: " + r2.insertarArco(uno,cuatro, 11.6));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Inserto arco de CUATRO a DOS con 12.7: " + r2.insertarArco(cuatro,dos,12.7));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Inserto arco de DOS a TRES con 13.8: " + r2.insertarArco(dos, tres, 13.8));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Inserto arco de TRES a SIETE con 14.9: " + r2.insertarArco(tres, siete, 14.9));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Elimino arco de UNO a CUATRO: " + r2.eliminarArco(uno, cuatro));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Elimino vertice SIETE: " + r2.eliminarCiudad(siete));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Inserto arco de TRES a SIETE con 14.9: " + r2.insertarArco(tres, siete, 14.9));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Elimino arco de TRES a SIETE: " + r2.eliminarArco(tres,siete));
        System.out.println("Rutas r2: \n"+ r2.toString());
        System.out.println("Inserto arco de UNO a TRES con 15: " + r2.insertarArco(uno,tres, 15));
        System.out.println("Rutas r2: \n"+ r2.toString());
    }

}
