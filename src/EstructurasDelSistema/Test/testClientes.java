package EstructurasDelSistema.Test;

import EstructurasDelSistema.Clientes;

import java.io.*;

public class testClientes {
    public static void main(String[] args) {
        //testToString();
        //testInsertarYEliminar();
        //testExisteCliente();
        //testGetCliente();
        //testEsVacio();
        //testVaciar();
        //testObtenerConjuntoDominio();
        //testObtenerConjuntoRango();
        //testMostrarCliente();
        testClone();
    }

    public static void testToString() {
        Clientes c1 = new Clientes();
        System.out.println("Clientes c1 vacio: " + c1.toString());
        System.out.println("Se inserta un cliente: " + c1.insertarCliente(1, 43552859, "DANIEL", "CARRASCO", 155462482, "danycarrasco077@gmail.com"));
        System.out.println("Clientes c1: " + c1.toString());
    }

    public static void testInsertarYEliminar() {
        Clientes c2 = new Clientes();
        System.out.println("Clientes c2: \n" + c2.toString());
        System.out.println("Se inserta un cliente: " + c2.insertarCliente(1, 43562859, "PERSONA", "UNO", 155467482, "persona1@gmail.com"));
        System.out.println("Clientes c2: \n" + c2.toString());
        System.out.println("Se inserta otro cliente: " + c2.insertarCliente(1, 87623786, "PERSONA", "DOS", 299528282, "persona2@gmail.com"));
        System.out.println("Clientes c2: \n" + c2.toString());
        c2.insertarCliente(1, 45367372, "PERSONA", "TRES", 299848773, "persona3@gmail.com");
        c2.insertarCliente(2, 43526261, "PERSONA", "CUATRO", 546376722, "persona4@gmail.com");
        c2.insertarCliente(1, 46372729, "PERSONA", "CINCO", 299383872, "persona4@gmail.com");
        System.out.println("Se insertaron los clientes");
        System.out.println("Clientes c2: \n" + c2.toString());
        System.out.println("Se quita PERSONA CUATRO: " + c2.eliminarCliente(1, 45367372));
        System.out.println("Clientes c2: \n" + c2.toString());
        System.out.println("Se quita PERSONA UNO: " + c2.eliminarCliente(1, 43562859));
        System.out.println("Clientes c2: \n" + c2.toString());
    }

    public static void testExisteCliente() {
        System.out.println("Prueba de existe cliente:");
        Clientes c3 = new Clientes();
        System.out.println("Clientes c3: \n" + c3.toString());
        System.out.println("Existe cliente en HashMap de clientes vacio: " + c3.existeCliente(1, 43502849));
        c3.insertarCliente(1, 43502849, "PERSONA", "UNO", 299237482, "persona1@gmail.com");
        c3.insertarCliente(1, 87623786, "PERSONA", "DOS", 299528282, "persona2@gmail.com");
        c3.insertarCliente(1, 45367372, "PERSONA", "TRES", 299848773, "persona3@gmail.com");
        c3.insertarCliente(2, 43526261, "PERSONA", "CUATRO", 546376722, "persona4@gmail.com");
        c3.insertarCliente(1, 46372729, "PERSONA", "CINCO", 299383872, "persona5@gmail.com");
        System.out.println("Clientes c2: \n" + c3.toString());
        System.out.println("Existe cliente PERSONA UNO: " + c3.existeCliente(1, 43502849));
        System.out.println("Existe cliente PERSONA SEIS: " + c3.existeCliente(2, 43512849));
        c3.insertarCliente(2, 43512849, "PERSONA", "SEIS", 299237182, "persona6@gmail.com");
        System.out.println("Clientes c3: \n" + c3.toString());
        System.out.println("Existe cliente PERSONA SEIS: " + c3.existeCliente(2, 43512849));
    }

    public static void testGetCliente() {
        System.out.println("Prueba de getCliente:");
        Clientes c3 = new Clientes();
        System.out.println("Clientes c3: \n" + c3.toString());
        System.out.println("Busca cliente en HashMap de clientes vacio (null): " + (c3.getCliente(1, 43502849) == null));
        c3.insertarCliente(1, 43502849, "PERSONA", "UNO", 299237482, "persona1@gmail.com");
        c3.insertarCliente(1, 87623786, "PERSONA", "DOS", 299528282, "persona2@gmail.com");
        c3.insertarCliente(1, 45367372, "PERSONA", "TRES", 299848773, "persona3@gmail.com");
        c3.insertarCliente(2, 43526261, "PERSONA", "CUATRO", 546376722, "persona4@gmail.com");
        c3.insertarCliente(1, 46372729, "PERSONA", "CINCO", 299383872, "persona5@gmail.com");
        System.out.println("Clientes c3: \n" + c3.toString());
        System.out.println("Busca cliente PERSONA UNO: " + c3.getCliente(1, 43502849).toString());
        System.out.println("Busca cliente PERSONA SEIS (null): " + (c3.getCliente(2, 43512849) == null));
        c3.insertarCliente(2, 43512849, "PERSONA", "SEIS", 299237182, "persona6@gmail.com");
        System.out.println("Clientes c3: \n" + c3.toString());
        System.out.println("Busca cliente PERSONA SEIS: " + c3.getCliente(2, 43512849).toString());
    }

    public static void testEsVacio() {
        System.out.println("Prueba de esVacio:");
        Clientes c4 = new Clientes();
        System.out.println("Clientes c4: \n" + c4.toString());
        System.out.println("El hashMap clientes es vacio: " + c4.esVacio());
        c4.insertarCliente(1, 43502849, "PERSONA", "UNO", 299237482, "persona1@gmail.com");
        System.out.println("Clientes c4: \n" + c4.toString());
        System.out.println("El hashMap clientes es vacio: " + c4.esVacio());
    }

    public static void testVaciar() {
        System.out.println("Prueba de vaciar:");
        Clientes c5 = new Clientes();
        c5.insertarCliente(1, 43502849, "PERSONA", "UNO", 299237482, "persona1@gmail.com");
        System.out.println("El hashMap clientes es vacio: " + c5.esVacio());
        System.out.println("Se vacia hashMap clientes");
        c5.vaciar();
        System.out.println("El hashMap clientes es vacio: " + c5.esVacio());
    }

    public static void testObtenerConjuntoDominio() {
        System.out.println("Prueba de obtener conjunto dominio:");
        Clientes c5 = new Clientes();
        System.out.println("Clientes c5: \n" + c5.toString());
        System.out.println("En HashMap de clientes vacio: ");
        mostrarLista(c5.obtenerConjuntoDominio().toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
        c5.insertarCliente(1, 43502849, "PERSONA", "UNO", 299237482, "persona1@gmail.com");
        c5.insertarCliente(1, 87623786, "PERSONA", "DOS", 299528282, "persona2@gmail.com");
        c5.insertarCliente(1, 45367372, "PERSONA", "TRES", 299848773, "persona3@gmail.com");
        c5.insertarCliente(2, 43526261, "PERSONA", "CUATRO", 546376722, "persona4@gmail.com");
        c5.insertarCliente(1, 46372729, "PERSONA", "CINCO", 299383872, "persona5@gmail.com");
        System.out.println("Clientes c5: \n" + c5.toString());
        System.out.println("En HashMap de clientes: ");
        mostrarLista(c5.obtenerConjuntoDominio().toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
        System.out.println("Se agrega PERSONA SEIS");
        c5.insertarCliente(2, 43512849, "PERSONA", "SEIS", 299237182, "persona6@gmail.com");
        System.out.println("Clientes c5: \n" + c5.toString());
        System.out.println("En HashMap de clientes: ");
        mostrarLista(c5.obtenerConjuntoDominio().toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
    }

    public static void testObtenerConjuntoRango() {
        System.out.println("Prueba de obtener conjunto dominio:");
        Clientes c6 = new Clientes();
        System.out.println("Clientes c6: \n" + c6.toString());
        System.out.println("En HashMap de clientes: ");
        mostrarLista(c6.obtenerConjuntoRango().toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
        c6.insertarCliente(1, 43502849, "PERSONA", "UNO", 299237482, "persona1@gmail.com");
        c6.insertarCliente(1, 87623786, "PERSONA", "DOS", 299528282, "persona2@gmail.com");
        c6.insertarCliente(1, 45367372, "PERSONA", "TRES", 299848773, "persona3@gmail.com");
        c6.insertarCliente(2, 43526261, "PERSONA", "CUATRO", 546376722, "persona4@gmail.com");
        c6.insertarCliente(1, 46372729, "PERSONA", "CINCO", 299383872, "persona5@gmail.com");
        System.out.println("Clientes c6: \n" + c6.toString());
        System.out.println("En HashMap de clientes: ");
        mostrarLista(c6.obtenerConjuntoRango().toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
        System.out.println("Se agrega PERSONA SEIS");
        c6.insertarCliente(2, 43512849, "PERSONA", "SEIS", 299237182, "persona6@gmail.com");
        System.out.println("Clientes c6: \n" + c6.toString());
        System.out.println("En HashMap de clientes: ");
        mostrarLista(c6.obtenerConjuntoRango().toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
    }

    public static void testMostrarCliente(){
        System.out.println("Prueba de obtener conjunto dominio:");
        Clientes c7 = new Clientes();
        System.out.println("Clientes c7: \n" + c7.toString());
        System.out.println("Busca cliente en hashMap vacio: "+ c7.mostrarCliente(1, 45367372));
        c7.insertarCliente(1, 43502849, "PERSONA", "UNO", 299237482, "persona1@gmail.com");
        c7.insertarCliente(1, 87623786, "PERSONA", "DOS", 299528282, "persona2@gmail.com");
        c7.insertarCliente(1, 45367372, "PERSONA", "TRES", 299848773, "persona3@gmail.com");
        c7.insertarCliente(2, 43526261, "PERSONA", "CUATRO", 546376722, "persona4@gmail.com");
        c7.insertarCliente(1, 46372729, "PERSONA", "CINCO", 299383872, "persona5@gmail.com");
        System.out.println("Clientes c7: \n" + c7.toString());
        System.out.println("Busca PERSONA TRES en hashMap cargado: "+ c7.mostrarCliente(1, 45367372));
        System.out.println("Busca PERSONA SEIS en hashMap cargado: "+ c7.mostrarCliente(2, 43512849));
        System.out.println("Se agrega PERSONA SEIS");
        c7.insertarCliente(2, 43512849, "PERSONA", "SEIS", 299237182, "persona6@gmail.com");
        System.out.println("Clientes c7: \n" + c7.toString());
        System.out.println("Busca PERSONA SEIS en hashMap cargado: "+ c7.mostrarCliente(2, 43512849));
    }

    private static void mostrarLista(String s, String csvFile) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        String line = "";
        //Se define separador ";"
        String cvsSplitBy = ";";
        try {
            bw = new BufferedWriter(new FileWriter(csvFile));
            br = new BufferedReader(new FileReader(csvFile));
            bw.write(s);
            bw.flush();
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(cvsSplitBy);
                int num = datos.length;
                int i = 0;
                while (i < num) {
                    //Imprime datos.
                    System.out.println(datos[i]);
                    i++;
                }
            }
            br.close();
            bw.close();
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(csvFile));
            bw2.write("");
            bw2.flush();
            bw2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void testClone(){
        System.out.println("Prueba clone:");
        Clientes c8 = new Clientes();
        System.out.println("Clientes c8: \n"+c8.toString());
        System.out.println("Se hace un clone de Clientes vacio");
        Clientes c9 = c8.clone();
        System.out.println("Se ingresa elemento PERSONA UNO en Clientes original");
        c8.insertarCliente(1, 43502849, "PERSONA", "UNO", 299237482, "persona1@gmail.com");
        System.out.println("Clientes c8: \n"+c8.toString());
        System.out.println("Clientes clone vacio: \n"+c9.toString());
        c8.insertarCliente(1, 87623786, "PERSONA", "DOS", 299528282, "persona2@gmail.com");
        c8.insertarCliente(1, 45367372, "PERSONA", "TRES", 299848773, "persona3@gmail.com");
        c8.insertarCliente(2, 43526261, "PERSONA", "CUATRO", 546376722, "persona4@gmail.com");
        System.out.println("Se insertaron clientes");
        System.out.println("Se crea otro Clientes clone con clientes agregados");
        Clientes c10 = c8.clone();
        System.out.println("Se inserta otro cliente en Clientes original");
        c8.insertarCliente(1, 46372729, "PERSONA", "CINCO", 299383872, "persona5@gmail.com");
        System.out.println("Clientes c8: \n"+c8.toString());
        System.out.println("Clientes clone con clientes: \n"+c10.toString());
        System.out.println("Se inserta otro cliente en el ultimo clone creado");
        c10.insertarCliente(2, 43512849, "PERSONA", "SEIS", 299237182, "persona6@gmail.com");
        System.out.println("Clientes c8: \n"+c8.toString());
        System.out.println("Ultimo Clientes clone: \n"+c10.toString());
    }

}
