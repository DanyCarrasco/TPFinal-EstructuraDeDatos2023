package EstructurasDelSistema.Test;

import EstructurasDelSistema.SistemaRutas.NodoCiudad;
import EstructurasDelSistema.SistemaRutas.Rutas;
import clases.Ciudad;
import Estructuras.lineales.dinamicas.Lista;

public class testRutas {
    public static void main(String[] args) {
        //testToString();
        //testInsertarYEliminar();
        //testExisteCiudad();
        //testExisteRuta();
        //testExisteCamino();
        //testListarEnProfundidad();
        //testListarEnAnchura();
        //testEsVacio();
        testClone();
    }

    public static void testToString() {
        System.out.println("Prueba de toString");
        Ciudad uno = new Ciudad(1234, "UNO", "UNIDAD");
        Ciudad dos = new Ciudad(5678, "DOS", "UNIDAD");
        Rutas r1 = new Rutas();
        System.out.println("Rutas r1 vacio: \n" + r1.toString());
        System.out.println("Ingreso una ciudad");
        r1.insertarCiudad(uno);
        System.out.println("Rutas r1: \n" + r1.toString());
        System.out.println("Ingreso otra ciudad");
        r1.insertarCiudad(dos);
        System.out.println("Rutas r1: \n" + r1.toString());
        System.out.println("Ingreso una ruta de UNO a DOS");
        r1.insertarRuta(uno, dos, 24.5);
        System.out.println("Rutas r1: \n" + r1.toString());
    }

    public static void testInsertarYEliminar() {
        System.out.println("Prueba de insertar y eliminar ciudades");
        Ciudad uno = new Ciudad(1234, "UNO", "UNIDAD");
        Ciudad dos = new Ciudad(5678, "DOS", "UNIDAD");
        Ciudad tres = new Ciudad(9012, "TRES", "UNIDAD");
        Ciudad cuatro = new Ciudad(3456, "CUATRO", "UNIDAD");
        Ciudad cinco = new Ciudad(7890, "CINCO", "UNIDAD");
        Ciudad seis = new Ciudad(2345, "SEIS", "UNIDAD");
        Ciudad siete = new Ciudad(6789, "SIETE", "UNIDAD");
        Rutas r2 = new Rutas();
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto UNO en Rutas: " + r2.insertarCiudad(uno));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto UNO en Rutas: " + r2.insertarCiudad(uno));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto DOS en Rutas: " + r2.insertarCiudad(dos));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto TRES en Rutas: " + r2.insertarCiudad(tres));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto CUATRO en Rutas: " + r2.insertarCiudad(cuatro));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto CINCO en Rutas: " + r2.insertarCiudad(cinco));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto SEIS en Rutas: " + r2.insertarCiudad(seis));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Elimino SEIS en Rutas: " + r2.eliminarCiudad(seis));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Elimino CINCO en Rutas: " + r2.eliminarCiudad(cinco));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto SIETE en Rutas: " + r2.insertarCiudad(siete));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Elimino CINCO en Rutas (no se encuentra en Rutas): " + r2.eliminarCiudad(cinco));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("-----------------------------------------------------");

        System.out.println("Insertar y eliminar rutas");
        System.out.println("Inserto ruta de UNO a DOS con 10.5: " + r2.insertarRuta(uno, dos, 10.5));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto ruta de UNO a CUATRO con 11.6: " + r2.insertarRuta(uno, cuatro, 11.6));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto ruta de CUATRO a DOS con 12.7: " + r2.insertarRuta(cuatro, dos, 12.7));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto ruta de DOS a TRES con 13.8: " + r2.insertarRuta(dos, tres, 13.8));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto ruta de TRES a SIETE con 14.9: " + r2.insertarRuta(tres, siete, 14.9));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Elimino ruta de UNO a CUATRO: " + r2.eliminarRuta(uno, cuatro));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Elimino vertice SIETE: " + r2.eliminarCiudad(siete));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto ruta de TRES a SIETE con 14.9: " + r2.insertarRuta(tres, siete, 14.9));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Elimino ruta de TRES a SIETE: " + r2.eliminarRuta(tres, siete));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto ruta de UNO a TRES con 15: " + r2.insertarRuta(uno, tres, 15));
        System.out.println("Rutas r2: \n" + r2.toString());
    }

    public static void testExisteCiudad() {
        System.out.println("Prueba de existe ciudad:");
        Ciudad uno = new Ciudad(1234, "UNO", "UNIDAD");
        Ciudad dos = new Ciudad(5678, "DOS", "UNIDAD");
        Ciudad tres = new Ciudad(9012, "TRES", "UNIDAD");
        Ciudad cuatro = new Ciudad(3456, "CUATRO", "UNIDAD");
        Ciudad siete = new Ciudad(6789, "SIETE", "UNIDAD");
        Rutas r3 = new Rutas();
        System.out.println("Pruebo en Rutas vacio buscar ciudad UNO: " + r3.existeCiudad(uno));
        System.out.println("Inserto ciudades en Rutas");
        r3.insertarCiudad(uno);
        r3.insertarCiudad(dos);
        r3.insertarCiudad(tres);
        r3.insertarCiudad(cuatro);
        System.out.println("Rutas r3: \n" + r3.toString());
        System.out.println("Pruebo en grafo con vertices buscar vertice 'A': " + r3.existeCiudad(uno));
        System.out.println("Inserto arcos en grafo");
        r3.insertarRuta(uno, dos, 10.1);
        r3.insertarRuta(uno, cuatro, 11.2);
        r3.insertarRuta(uno, tres, 12.3);
        r3.insertarRuta(dos, cuatro, 13.4);
        r3.insertarRuta(cuatro, tres, 14.5);
        r3.insertarRuta(tres, dos, 15.6);
        System.out.println("Rutas r3: \n" + r3.toString());
        System.out.println("Pruebo en Rutas con rutas ingresadas buscar ciudad UNO: " + r3.existeCiudad(uno));
        System.out.println("Pruebo en Rutas con rutas ingresadas buscar ciudad SIETE: " + r3.existeCiudad(siete));
    }

    public static void testExisteRuta() {
        System.out.println("Prueba de existe arco:");
        Ciudad uno = new Ciudad(1234, "UNO", "UNIDAD");
        Ciudad dos = new Ciudad(5678, "DOS", "UNIDAD");
        Ciudad tres = new Ciudad(9012, "TRES", "UNIDAD");
        Ciudad cuatro = new Ciudad(3456, "CUATRO", "UNIDAD");
        Ciudad siete = new Ciudad(6789, "SIETE", "UNIDAD");
        Rutas r4 = new Rutas();
        System.out.println("Pruebo en Rutas vacio buscar ruta 'A' a 'B': " + r4.existeRuta(uno, dos));
        System.out.println("Inserto ciudades en Rutas");
        r4.insertarCiudad(uno);
        r4.insertarCiudad(dos);
        r4.insertarCiudad(tres);
        r4.insertarCiudad(cuatro);
        System.out.println("Rutas r4: \n" + r4.toString());
        System.out.println("Pruebo en Rutas con ciudades buscar ruta 'A' a 'B': " + r4.existeRuta(uno, dos));
        System.out.println("Inserto rutas en Rutas");
        r4.insertarRuta(uno, dos, 10.1);
        r4.insertarRuta(uno, cuatro, 11.2);
        r4.insertarRuta(uno, tres, 12.3);
        r4.insertarRuta(dos, cuatro, 13.4);
        r4.insertarRuta(cuatro, tres, 14.5);
        r4.insertarRuta(tres, dos, 15.6);
        System.out.println("Rutas r4: \n" + r4.toString());
        System.out.println("Pruebo en Rutas con vertices buscar ruta UNO a DOS: " + r4.existeRuta(uno, dos));
        System.out.println("Pruebo en Rutas con vertices buscar ruta UNO a SIETE: " + r4.existeRuta(uno, siete));
    }

    public static void testExisteCamino() {
        System.out.println("Prueba de existe camino:");
        Ciudad uno = new Ciudad(1234, "UNO", "UNIDAD");
        Ciudad dos = new Ciudad(5678, "DOS", "UNIDAD");
        Ciudad tres = new Ciudad(9012, "TRES", "UNIDAD");
        Ciudad cuatro = new Ciudad(3456, "CUATRO", "UNIDAD");
        Ciudad siete = new Ciudad(6789, "SIETE", "UNIDAD");
        Rutas g4 = new Rutas();
        System.out.println("Pruebo en Rutas vacio si existe camino UNO a DOS: " + g4.existeCamino(uno, dos));
        System.out.println("Inserto ciudades en Rutas");
        g4.insertarCiudad(uno);
        g4.insertarCiudad(dos);
        g4.insertarCiudad(tres);
        g4.insertarCiudad(cuatro);
        System.out.println("Rutas r5: \n" + g4.toString());
        System.out.println("Pruebo en Rutas con ciudades si existe camino UNO a DOS: " + g4.existeCamino(uno, dos));
        System.out.println("Inserto rutas de ciudades en Rutas");
        g4.insertarRuta(uno, dos, 10.3);
        g4.insertarRuta(uno, cuatro, 11.6);
        g4.insertarRuta(uno, tres, 12.9);
        g4.insertarRuta(dos, cuatro, 13.2);
        g4.insertarRuta(cuatro, tres, 14.5);
        g4.insertarRuta(tres, dos, 15.8);
        System.out.println("Rutas r5: \n" + g4.toString());
        System.out.println("Pruebo en Rutas con ciudades existe camino UNO a DOS: " + g4.existeCamino(uno, dos));
        System.out.println("Pruebo en Rutas con ciudades existe camino UNO a SIETE: " + g4.existeCamino(uno, siete));
    }

    public static void testListarEnProfundidad() {
        System.out.println("Prueba de listar en profundidad:");
        Ciudad uno = new Ciudad(1234, "UNO", "UNIDAD");
        Ciudad dos = new Ciudad(5678, "DOS", "UNIDAD");
        Ciudad tres = new Ciudad(9012, "TRES", "UNIDAD");
        Ciudad cuatro = new Ciudad(3456, "CUATRO", "UNIDAD");
        Rutas r6 = new Rutas();
        System.out.println("Rutas r6: \n" + r6.toString());
        System.out.println("Se enlista en un Rutas vacio (retorna lista vacia): " + r6.listarEnProfundidad().esVacia());
        System.out.println("Se ingresa ciudad UNO en Rutas");
        r6.insertarCiudad(uno);
        System.out.println("Rutas r6: \n" + r6.toString());
        System.out.println("Se enlista Rutas r5: " + r6.listarEnProfundidad().toString());
        r6.insertarCiudad(dos);
        r6.insertarCiudad(tres);
        r6.insertarCiudad(cuatro);
        System.out.println("Se insertaron las ciudades");
        System.out.println("Se enlista Rutas r5: " + r6.listarEnProfundidad().toString());
        r6.insertarRuta(uno, dos, 10.2);
        r6.insertarRuta(uno, cuatro, 11.4);
        r6.insertarRuta(dos, tres, 12.6);
        r6.insertarRuta(dos, cuatro, 13.8);
        System.out.println("Se insertaron las rutas de las ciudades");
        System.out.println("Rutas r6: \n" + r6.toString());
        System.out.println("Se enlista Rutas r5: " + r6.listarEnProfundidad().toString());
    }

    public static void testListarEnAnchura() {
        System.out.println("Prueba de listar en anchura:");
        Ciudad uno = new Ciudad(1234, "UNO", "UNIDAD");
        Ciudad dos = new Ciudad(5678, "DOS", "UNIDAD");
        Ciudad tres = new Ciudad(9012, "TRES", "UNIDAD");
        Ciudad cuatro = new Ciudad(3456, "CUATRO", "UNIDAD");
        Rutas r7 = new Rutas();
        System.out.println("Rutas r7: \n" + r7.toString());
        System.out.println("Se enlista en un Rutas vacio (retorna lista vacia): " + r7.listarEnAnchura().esVacia());
        System.out.println("Se ingresa ciudad UNO en Rutas");
        r7.insertarCiudad(uno);
        System.out.println("Rutas r7: \n" + r7.toString());
        System.out.println("Se enlista Rutas r6: ");
        mostrarLista(r7.listarEnAnchura());
        r7.insertarCiudad(dos);
        r7.insertarCiudad(tres);
        r7.insertarCiudad(cuatro);
        System.out.println("Se insertaron las ciudades");
        System.out.println("Se enlista Rutas r6: ");
        mostrarLista(r7.listarEnAnchura());
        r7.insertarRuta(uno, dos, 10.2);
        r7.insertarRuta(uno, cuatro, 11.4);
        r7.insertarRuta(dos, tres, 12.6);
        r7.insertarRuta(dos, cuatro, 13.8);
        System.out.println("Se insertaron las rutas de las ciudades");
        System.out.println("Rutas r7: \n" + r7.toString());
        System.out.println("Se enlista Rutas r6: ");
        mostrarLista(r7.listarEnAnchura());
    }

    private static void mostrarLista(Lista lis) {
        int largo = lis.longitud();
        for (int i = 1; i <= largo; i++) {
            NodoCiudad nodo = (NodoCiudad) lis.recuperar(i);
            System.out.println(nodo.getElem().toString());
        }
    }

    public static void testEsVacio() {
        System.out.println("Prueba de 'es vacio':");
        Ciudad uno = new Ciudad(1234, "UNO", "UNIDAD");
        Rutas r8 = new Rutas();
        System.out.println("Rutas r8: \n" + r8.toString());
        System.out.println("Rutas vacio: " + r8.esVacio());
        System.out.println("Se ingresa ciudad UNO en Rutas");
        r8.insertarCiudad(uno);
        System.out.println("Rutas r8: \n" + r8.toString());
        System.out.println("Rutas vacio: " + r8.esVacio());
    }

    public static void testClone() {
        Ciudad uno = new Ciudad(1234, "UNO", "UNIDAD");
        Ciudad dos = new Ciudad(5678, "DOS", "UNIDAD");
        Ciudad tres = new Ciudad(9012, "TRES", "UNIDAD");
        Ciudad cuatro = new Ciudad(3456, "CUATRO", "UNIDAD");
        Ciudad siete = new Ciudad(6789, "SIETE", "UNIDAD");
        System.out.println("Prueba de clone:");
        Rutas r9 = new Rutas();
        System.out.println("Rutas r9: \n" + r9.toString());
        System.out.println("Se hace un clone de Rutas vacio");
        Rutas r10 = r9.clone();
        System.out.println("Se ingresa ciudad UNO en Rutas original");
        r9.insertarCiudad(uno);
        System.out.println("Rutas r9: \n" + r9.toString());
        System.out.println("Rutas clone vacio: \n" + r10.toString());
        r9.insertarCiudad(dos);
        r9.insertarCiudad(tres);
        r9.insertarCiudad(cuatro);
        System.out.println("Se insertaron las ciudades");
        System.out.println("Se crea otro Rutas clone con ciudades");
        Rutas r11 = r9.clone();
        System.out.println("Rutas r9: \n" + r9.toString());
        r9.insertarRuta(uno, dos, 10.1);
        r9.insertarRuta(uno, cuatro, 11.3);
        r9.insertarRuta(dos, tres, 12.5);
        r9.insertarRuta(dos, cuatro, 13.7);
        System.out.println("Se insertaron las rutas de las ciudades");
        System.out.println("Rutas r9: \n" + r9.toString());
        System.out.println("Rutas clone con ciudades: \n" + r11.toString());
        System.out.println("Se crea un Rutas clone con las rutas de las ciudades");
        Rutas r12 = r9.clone();
        System.out.println("Rutas r9: \n" + r9.toString());
        System.out.println("Rutas clone con rutas: \n" + r12.toString());
        System.out.println("Se agrega un vertice en ultimo grafo clonado: " + r12.insertarCiudad(siete));
        System.out.println("Se agrega un arco en el ultimo grafo clonado: " + r12.insertarRuta(tres, siete, 14.9));
        System.out.println("Rutas r9: \n" + r9.toString());
        System.out.println("Ultimo Rutas clonado: \n" + r12.toString());
    }
}
