package EstructurasDelSistema.Test;

import EstructurasDelSistema.Ciudades;
import EstructurasDelSistema.Clientes;
import EstructurasDelSistema.SistemaRutas.NodoCiudad;
import clases.Ciudad;

import java.io.*;

public class testCiudades {
    public static void main(String[] args) {
        testToString();
        //testInsertarYEliminar();
        //testExisteCiudad();
        //testGetCliente();
        //testListarCodigosPostales();
        //testListarCiudades();
        //testListarCiudadesRango();
        //testEsVacio();
        //testVaciar();
        //testClone();
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
        System.out.println("Se elimina ciudad UNO en Ciudades vacio: " + c2.eliminarCiudad(1234));
        System.out.println("Se ingresa ciudad UNO: " + c2.insertarCiudad(1234, "UNO", "UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad UNO: " + c2.insertarCiudad(1234, "UNO", "UNIDAD"));
        System.out.println("Se ingresa ciudad DOS: " + c2.insertarCiudad(5678, "DOS", "UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad TRES: " + c2.insertarCiudad(9012, "TRES", "UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad CUATRO: " + c2.insertarCiudad(1123, "CUATRO", "UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad CINCO: " + c2.insertarCiudad(1015, "CINCO", "UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad SEIS: " + c2.insertarCiudad(1120, "SEIS", "UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad SIETE: " + c2.insertarCiudad(1017, "SIETE", "UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad OCHO: " + c2.insertarCiudad(6789, "OCHO", "UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se ingresa ciudad NUEVE: " + c2.insertarCiudad(7890, "NUEVE", "UNIDAD"));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se elimina ciudad UNO: " + c2.eliminarCiudad(1234));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se elimina ciudad DOS: " + c2.eliminarCiudad(5678));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se elimina ciudad CUATRO: " + c2.eliminarCiudad(1123));
        System.out.println("Ciudades c2: \n" + c2.toString());
        System.out.println("Se elimina ciudad NUEVE: " + c2.eliminarCiudad(7890));
        System.out.println("Ciudades c2: \n" + c2.toString());
    }

    public static void testExisteCiudad() {
        Ciudades c3 = new Ciudades();
        System.out.println("Se crea Ciudades vacio");
        System.out.println("Ciudades c3: \n" + c3.toString());
        System.out.println("Existe ciudad UNO: " + c3.existeCiudad(1234));
        c3.insertarCiudad(1234, "UNO", "UNIDAD");
        System.out.println("Ciudades c3: \n" + c3.toString());
        System.out.println("Existe ciudad UNO: " + c3.existeCiudad(1234));
        c3.insertarCiudad(5678, "DOS", "UNIDAD");
        c3.insertarCiudad(9012, "TRES", "UNIDAD");
        c3.insertarCiudad(1123, "CUATRO", "UNIDAD");
        c3.insertarCiudad(1015, "CINCO", "UNIDAD");
        c3.insertarCiudad(1120, "SEIS", "UNIDAD");
        c3.insertarCiudad(1017, "SIETE", "UNIDAD");
        System.out.println("Se insertaron las ciudades");
        System.out.println("Ciudades c3: \n" + c3.toString());
        System.out.println("Existe ciudad OCHO: " + c3.existeCiudad(6789));
        System.out.println("Se inserta ciudad OCHO");
        c3.insertarCiudad(6789, "OCHO", "UNIDAD");
        System.out.println("Ciudades c3: \n" + c3.toString());
        System.out.println("Existe ciudad OCHO: " + c3.existeCiudad(6789));
    }

    public static void testGetCliente() {
        Ciudades c4 = new Ciudades();
        System.out.println("Se crea Ciudades vacio");
        System.out.println("Ciudades c4: \n" + c4.toString());
        System.out.println("Busca ciudad UNO (null): " + (c4.getCiudad(1234) == null));
        c4.insertarCiudad(1234, "UNO", "UNIDAD");
        System.out.println("Ciudades c3: \n" + c4.toString());
        System.out.println("Existe ciudad UNO: " + c4.getCiudad(1234).toString());
        c4.insertarCiudad(5678, "DOS", "UNIDAD");
        c4.insertarCiudad(9012, "TRES", "UNIDAD");
        c4.insertarCiudad(1123, "CUATRO", "UNIDAD");
        c4.insertarCiudad(1015, "CINCO", "UNIDAD");
        c4.insertarCiudad(1120, "SEIS", "UNIDAD");
        c4.insertarCiudad(1017, "SIETE", "UNIDAD");
        System.out.println("Se insertaron las ciudades");
        System.out.println("Ciudades c3: \n" + c4.toString());
        System.out.println("Busca ciudad OCHO (null): " + (c4.getCiudad(6789) == null));
        System.out.println("Se inserta ciudad OCHO");
        c4.insertarCiudad(6789, "OCHO", "UNIDAD");
        System.out.println("Ciudades c3: \n" + c4.toString());
        System.out.println("Busca ciudad OCHO: " + c4.getCiudad(6789).toString());
    }

    public static void testListarCodigosPostales() {
        Ciudades c5 = new Ciudades();
        System.out.println("Se crea Ciudades vacio");
        System.out.println("Ciudades c5: \n" + c5.toString());
        System.out.println("Enlista en ciudades vacia: \n");
        mostrarLista(c5.listarCodigosPostales().toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
        c5.insertarCiudad(1234, "UNO", "UNIDAD");
        c5.insertarCiudad(5678, "DOS", "UNIDAD");
        c5.insertarCiudad(9012, "TRES", "UNIDAD");
        c5.insertarCiudad(1123, "CUATRO", "UNIDAD");
        c5.insertarCiudad(1015, "CINCO", "UNIDAD");
        c5.insertarCiudad(1120, "SEIS", "UNIDAD");
        c5.insertarCiudad(1017, "SIETE", "UNIDAD");
        System.out.println("Se insertaron las ciudades");
        System.out.println("Ciudades c5: \n" + c5.toString());
        System.out.println("Enlista codigos postales de ciudades: \n");
        mostrarLista(c5.listarCodigosPostales().toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
        System.out.println("Se agrega ciudad OCHO");
        c5.insertarCiudad(6789, "OCHO", "UNIDAD");
        System.out.println("Ciudades c5: \n" + c5.toString());
        System.out.println("Enlista codigos postales de ciudades: \n");
        mostrarLista(c5.listarCodigosPostales().toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
    }

    public static void testListarCiudades() {
        Ciudades c6 = new Ciudades();
        System.out.println("Se crea Ciudades vacio");
        System.out.println("Ciudades c6: \n" + c6.toString());
        System.out.println("Enlista en Ciudades vacio: \n");
        mostrarLista(c6.listarCiudades().toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
        c6.insertarCiudad(1234, "UNO", "UNIDAD");
        c6.insertarCiudad(5678, "DOS", "UNIDAD");
        c6.insertarCiudad(9012, "TRES", "UNIDAD");
        c6.insertarCiudad(1123, "CUATRO", "UNIDAD");
        c6.insertarCiudad(1015, "CINCO", "UNIDAD");
        c6.insertarCiudad(1120, "SEIS", "UNIDAD");
        c6.insertarCiudad(1017, "SIETE", "UNIDAD");
        System.out.println("Se insertaron las ciudades");
        System.out.println("Ciudades c6: \n" + c6.toString());
        System.out.println("Enlista ciudades de Ciudades: \n");
        mostrarLista(c6.listarCiudades().toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
        System.out.println("Se agrega ciudad OCHO");
        c6.insertarCiudad(6789, "OCHO", "UNIDAD");
        System.out.println("Ciudades c6: \n" + c6.toString());
        System.out.println("Enlista ciudades de Ciudades: \n");
        mostrarLista(c6.listarCiudades().toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
    }

    public static void testListarCiudadesRango() {
        Ciudades c6 = new Ciudades();
        System.out.println("Se crea Ciudades vacio");
        System.out.println("Ciudades c6: \n" + c6.toString());
        System.out.println("Enlista en Ciudades vacio: \n");
        mostrarLista(c6.listarCiudadesRango(10).toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
        c6.insertarCiudad(1234, "UNO", "UNIDAD");
        c6.insertarCiudad(5678, "DOS", "UNIDAD");
        c6.insertarCiudad(9012, "TRES", "UNIDAD");
        c6.insertarCiudad(1123, "CUATRO", "UNIDAD");
        c6.insertarCiudad(1015, "CINCO", "UNIDAD");
        c6.insertarCiudad(1120, "SEIS", "UNIDAD");
        c6.insertarCiudad(1017, "SIETE", "UNIDAD");
        System.out.println("Se insertaron las ciudades");
        System.out.println("Ciudades c6: \n" + c6.toString());
        System.out.println("Enlista ciudades entre 1000 y 1099 de codigo postal de Ciudades: \n");
        mostrarLista(c6.listarCiudadesRango(10).toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
        System.out.println("Enlista ciudades entre 1100 y 1199 de codigo postal de Ciudades: \n");
        mostrarLista(c6.listarCiudadesRango(11).toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
        System.out.println("Enlista ciudades entre 1200 y 1299 de codigo postal de Ciudades: \n");
        mostrarLista(c6.listarCiudadesRango(12).toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
        System.out.println("Enlista ciudades entre 6700 y 6799 de codigo postal de Ciudades: \n");
        mostrarLista(c6.listarCiudadesRango(67).toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
        System.out.println("Se agrega ciudad OCHO");
        c6.insertarCiudad(6789, "OCHO", "UNIDAD");
        System.out.println("Ciudades c6: \n" + c6.toString());
        System.out.println("Enlista ciudades entre 6700 y 6799 de codigo postal de Ciudades: \n");
        mostrarLista(c6.listarCiudadesRango(67).toString(), "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\lista.txt");
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

    public static void testEsVacio() {
        Ciudades c7 = new Ciudades();
        System.out.println("Se crea Ciudades vacio");
        System.out.println("Ciudades c7: \n" + c7.toString());
        System.out.println("Ciudades esta vacio: " + c7.esVacio());
        System.out.println("Se agrega una Ciudad");
        c7.insertarCiudad(1234, "UNO", "UNIDAD");
        System.out.println("Ciudades c7: \n" + c7.toString());
        System.out.println("Ciudades esta vacio: " + c7.esVacio());
    }

    public static void testVaciar() {
        Ciudades c8 = new Ciudades();
        System.out.println("Se crea Ciudades vacio");
        System.out.println("Se agrega una Ciudad");
        c8.insertarCiudad(1234, "UNO", "UNIDAD");
        System.out.println("Ciudades esta vacio: " + c8.esVacio());
        System.out.println("Se vacia Ciudades");
        c8.vaciar();
        System.out.println("Ciudades esta vacio: " + c8.esVacio());
    }

    public static void testClone() {
        Ciudades c9 = new Ciudades();
        System.out.println("Se crea Ciudades vacio");
        System.out.println("Ciudades c9: \n" + c9.toString());
        System.out.println("Se hace un clone de Ciudades vacio");
        Ciudades c10 = c9.clone();
        System.out.println("Se ingresa elemento PERSONA UNO en Clientes original");
        c9.insertarCiudad(1234, "UNO", "UNIDAD");
        System.out.println("Ciudades c9: \n" + c9.toString());
        System.out.println("Ciudades clone vacio: \n" + c10.toString());
        c9.insertarCiudad(5678, "DOS", "UNIDAD");
        c9.insertarCiudad(9012, "TRES", "UNIDAD");
        c9.insertarCiudad(1123, "CUATRO", "UNIDAD");
        System.out.println("Se insertaron ciudades");
        System.out.println("Se crea otro Ciudades clone con ciudades agregados");
        Ciudades c11 = c9.clone();
        System.out.println("Se inserta otra ciudad en Ciudades original");
        c9.insertarCiudad(1015, "CINCO", "UNIDAD");
        System.out.println("Ciudades c9: \n" + c9.toString());
        System.out.println("Ciudades clone con ciudades: \n" + c11.toString());
        System.out.println("Se inserta otra ciudad en el ultimo clone creado");
        c11.insertarCiudad(1120, "SEIS", "UNIDAD");
        System.out.println("Ciudades c9: \n" + c9.toString());
        System.out.println("Ultimo Ciudades clone: \n" + c11.toString());
    }
}
