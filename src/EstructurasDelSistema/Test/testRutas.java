package EstructurasDelSistema.Test;

import EstructurasDelSistema.Ciudades;
import EstructurasDelSistema.SistemaRutas.NodoCiudad;
import EstructurasDelSistema.SistemaRutas.Rutas;
import clases.Ciudad;
import Estructuras.lineales.dinamicas.Lista;

public class testRutas {
    public static void main(String[] args) {
        Ciudades info = new Ciudades();
        cargarCiudades(info);
        //testToString();
        //testInsertarYEliminar();
        //testExisteCiudad();
        //testExisteRuta();
        //testExisteCamino();
        //testListarEnProfundidad();
        //testListarEnAnchura();
        //testEsVacio();
        //testClone();
        //testCaminoCortoCiudades();
        testCaminoCortoDistancias();
    }

    public static void cargarCiudades(Ciudades info){
        info.insertarCiudad(1234, "UNO", "UNIDAD");
        info.insertarCiudad(5678, "DOS", "UNIDAD");
        info.insertarCiudad(9012, "TRES", "UNIDAD");
        info.insertarCiudad(3456, "CUATRO", "UNIDAD");
        info.insertarCiudad(7890, "CINCO", "UNIDAD");
        info.insertarCiudad(2345, "SEIS", "UNIDAD");
        info.insertarCiudad(6789, "SIETE", "UNIDAD");
    }

    public static void testToString(Ciudades info) {
        System.out.println("Prueba de toString");
        Rutas r1 = new Rutas();
        System.out.println("Rutas r1 vacio: \n" + r1.toString());
        System.out.println("Ingreso una ciudad");
        r1.insertarCiudad(info.getCiudad(1234));
        System.out.println("Rutas r1: \n" + r1.toString());
        System.out.println("Ingreso otra ciudad");
        r1.insertarCiudad(info.getCiudad(1234));
        System.out.println("Rutas r1: \n" + r1.toString());
        System.out.println("Ingreso una ruta de UNO a DOS");
        r1.insertarRuta( info.getCiudad(1234),info.getCiudad(5678), 24.5);
        System.out.println("Rutas r1: \n" + r1.toString());
    }

    public static void testInsertarYEliminar(Ciudades info) {
        System.out.println("Prueba de insertar y eliminar ciudades");
        Rutas r2 = new Rutas();
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto UNO en Rutas: " + r2.insertarCiudad(info.getCiudad(1234)));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto UNO en Rutas: " + r2.insertarCiudad(info.getCiudad(1234)));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto DOS en Rutas: " + r2.insertarCiudad(info.getCiudad(5678)));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto TRES en Rutas: " + r2.insertarCiudad(info.getCiudad(9012)));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto CUATRO en Rutas: " + r2.insertarCiudad(info.getCiudad(3456)));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto CINCO en Rutas: " + r2.insertarCiudad(info.getCiudad(7890)));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto SEIS en Rutas: " + r2.insertarCiudad(info.getCiudad(2345)));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Elimino SEIS en Rutas: " + r2.eliminarCiudad(info.getCiudad(2345)));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Elimino CINCO en Rutas: " + r2.eliminarCiudad(info.getCiudad(7890)));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto SIETE en Rutas: " + r2.insertarCiudad(info.getCiudad(6789)));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Elimino CINCO en Rutas (no se encuentra en Rutas): " + r2.eliminarCiudad(7890));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("-----------------------------------------------------");

        System.out.println("Insertar y eliminar rutas");
        System.out.println("Inserto ruta de UNO a DOS con 10.5: " + r2.insertarRuta(info.getCiudad(1234), info.getCiudad(5678), 10.5));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto ruta de UNO a CUATRO con 11.6: " + r2.insertarRuta(info.getCiudad(1234), info.getCiudad(3456), 11.6));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto ruta de CUATRO a DOS con 12.7: " + r2.insertarRuta( info.getCiudad(3456),  info.getCiudad(5678), 12.7));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto ruta de DOS a TRES con 13.8: " + r2.insertarRuta(info.getCiudad(5678), info.getCiudad(9012), 13.8));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto ruta de TRES a SIETE con 14.9: " + r2.insertarRuta(info.getCiudad(9012), info.getCiudad(6789), 14.9));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Elimino ruta de UNO a CUATRO: " + r2.eliminarRuta(info.getCiudad(1234), info.getCiudad(3456)));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Elimino vertice SIETE: " + r2.eliminarCiudad(info.getCiudad(6789)));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto ruta de TRES a SIETE con 14.9: " + r2.insertarRuta(info.getCiudad(9012), info.getCiudad(6789), 14.9));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Elimino ruta de TRES a SIETE: " + r2.eliminarRuta(info.getCiudad(9012), info.getCiudad(6789)));
        System.out.println("Rutas r2: \n" + r2.toString());
        System.out.println("Inserto ruta de UNO a TRES con 15: " + r2.insertarRuta(info.getCiudad(1234), info.getCiudad(9012), 15));
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

    public static void testExisteRuta(Ciudades info) {
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

    public static void testCaminoCortoCiudades() {
        Ciudades sistema = new Ciudades();
        Ciudad uno = new Ciudad(1234, "UNO", "UNIDAD");
        Ciudad dos = new Ciudad(5678, "DOS", "UNIDAD");
        Ciudad tres = new Ciudad(9012, "TRES", "UNIDAD");
        Ciudad cuatro = new Ciudad(3456, "CUATRO", "UNIDAD");
        Ciudad siete = new Ciudad(6789, "SIETE", "UNIDAD");
        System.out.println("Prueba de camino corto ciudades:");
        Rutas g5 = new Rutas();
        System.out.println("Pruebo en Rutas vacio camino corto de UNO a DOS: " + g5.caminoCortoCiudades(1234, 5678).toString());
        System.out.println("Inserto ciudades en Rutas");
        g5.insertarCiudad(uno);
        g5.insertarCiudad(dos);
        g5.insertarCiudad(tres);
        g5.insertarCiudad(cuatro);
        System.out.println("Rutas r13: \n" + g5.toString());
        System.out.println("Pruebo en Rutas con ciudades camino corto de UNO a DOS: " + g5.caminoCortoCiudades(uno, dos).toString());
        System.out.println("Inserto rutas de las ciudades en Rutas");
        g5.insertarRuta(uno,dos,13.2);
        g5.insertarRuta(uno,cuatro,15.4);
        g5.insertarRuta(uno,tres,17.6);
        g5.insertarRuta(dos,cuatro,19.8);
        g5.insertarRuta(cuatro,tres, 21);
        g5.insertarRuta(tres,dos,11.2);
        System.out.println("Rutas r13: \n" + g5.toString());
        System.out.println("Pruebo en Rutas con ciudades camino corto de UNO a TRES: " + g5.caminoCortoCiudades(uno, tres).toString());
        System.out.println("Pruebo en Rutas con ciudades camino corto de UNO a SIETE: " + g5.caminoCortoCiudades(uno, siete).toString());
    }

    public static void testCaminoCortoDistancias() {
        Ciudad uno = new Ciudad(1234, "UNO", "UNIDAD");
        Ciudad dos = new Ciudad(5678, "DOS", "UNIDAD");
        Ciudad tres = new Ciudad(9012, "TRES", "UNIDAD");
        Ciudad cuatro = new Ciudad(3456, "CUATRO", "UNIDAD");
        Ciudad siete = new Ciudad(6789, "SIETE", "UNIDAD");
        System.out.println("Prueba de camino corto ciudades:");
        Rutas g5 = new Rutas();
        System.out.println("Pruebo en Rutas vacio camino corto de UNO a DOS: " + g5.caminoCortoDistancias(uno, dos).toString());
        System.out.println("Inserto ciudades en Rutas");
        g5.insertarCiudad(uno);
        g5.insertarCiudad(dos);
        g5.insertarCiudad(tres);
        g5.insertarCiudad(cuatro);
        System.out.println("Rutas r13: \n" + g5.toString());
        System.out.println("Pruebo en Rutas con ciudades camino corto de UNO a DOS: " + g5.caminoCortoDistancias(uno, dos).toString());
        System.out.println("Inserto rutas de las ciudades en Rutas");
        g5.insertarRuta(uno,dos,10);
        g5.insertarRuta(uno,cuatro,4);
        g5.insertarRuta(uno,tres,6);
        g5.insertarRuta(dos,cuatro,2);
        g5.insertarRuta(cuatro,tres, 4);
        g5.insertarRuta(tres,dos,5);
        System.out.println("Rutas r13: \n" + g5.toString());
        System.out.println("Pruebo en Rutas con ciudades camino corto de UNO a DOS: " + g5.caminoCortoDistancias(uno, dos).toString());
        System.out.println("Pruebo en Rutas con ciudades camino corto de UNO a SIETE: " + g5.caminoCortoDistancias(uno, siete).toString());
    }
}
