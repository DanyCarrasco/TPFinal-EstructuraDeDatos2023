package SistemaMudanzasCompartidas;

import Estructuras.lineales.dinamicas.Lista;
import com.sun.security.jgss.GSSUtil;

import javax.swing.plaf.PanelUI;
import java.io.*;
import java.time.Year;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MudanzasCompartidas {
    private static Funcionamiento funcion = new Funcionamiento();
    private static Scanner lectura = new Scanner(System.in);

    public static void main(String[] args) {
        String cvcFile = "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\escribeme.txt";

        try {
            FileWriter file = new FileWriter(cvcFile);
            BufferedWriter escribeme = new BufferedWriter(file);
            escribeme.write("");
            escribeme.flush();
            escribeme.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

        int opcion;
        String cad = "";
        menu();
        opcion = lectura.nextInt();
        while (opcion != 7) {
            cad = cad + ejecutarOpcion(opcion);
            menu();
            opcion = lectura.nextInt();
        }
        lectura.close();

        cad = cad + "El usuario termina la ejecucion del menu principal de Mudanzas Compartidas\n Estado del sistema: \n\n" + funcion.mostrarSistema();

        try {
            FileWriter file = new FileWriter(cvcFile);
            BufferedWriter escribeme = new BufferedWriter(file);
            escribeme.write(cad);
            escribeme.flush();
            escribeme.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void menu() {
        System.out.println("Menu");
        System.out.println("1 - Carga Inicial del sistema: ingresar al sistema un lote fijo de ciudades, rutas, clientes y pedidos");
        System.out.println("2 - ABM de ciudades");
        System.out.println("3 - ABM de la red de rutas");
        System.out.println("4 - ABM de clientes");
        System.out.println("5 - ABM de pedidos");
        System.out.println("6 - Mostrar sistema");
        System.out.println("7 - Terminar la ejecucion");
        System.out.println("Ingrese que opcion desea ejecutar: ");
    }

    public static String ejecutarOpcion(int opcion) {
        String cad = "";
        switch (opcion) {
            case 1:
                cad = menuDeCarga();
                break;
            case 2:
                cad = ABMCiudades();
                break;
            case 3:
                cad = ABMRedDeRutas();
                break;
            case 4:
                cad = ABMClientes();
                break;
            case 5:
                cad = ABMPedidos();
                break;
            case 6:
                cad = mostrarSistema();
                break;
            default:
                System.out.println("Numero ingresado incorrecto, intentalo de nuevo");
                cad = "Ingresa un numero erroneo en la eleccion de opciones de menu principal\n";
                break;
        }
        return cad;
    }

    public static String menuDeCarga() {
        String cad = metodo();
        System.out.println(funcion.mostrarSistema());
        cad = cad + "Se muestra el sistema cargado al usuario\n";
        return cad;
    }

    public static String metodo() {
        String cad = "";
        String csvFile = "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\leeme.txt";
        BufferedReader br = null;
        String line = ";";
        //Se define separador ";"
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(cvsSplitBy);
                int num = datos.length;
                int i = 0;
                while (i < num) {
                    cad = cad + metodoCargar(datos[i]);
                    i++;

                }
            }
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
        return cad;
    }

    public static String metodoCargar(String linea) {
        String cad = "";
        StringTokenizer st = new StringTokenizer(linea, ";");
        switch (st.nextToken()) {
            case "C":
                cad = cargarCiudad(st);
                break;
            case "S":
                cad = cargarSolicitud(st);
                break;
            case "R":
                cad = cargarRuta(st);
                break;
            case "P":
                cad = cargarCliente(st);
                break;
        }
        return cad;
    }

    public static String cargarCiudad(StringTokenizer st) {
        String[] arreglo = new String[4];
        int i = 1;
        while (st.hasMoreTokens()) {
            arreglo[i] = st.nextToken();
            i++;
        }
        String cad = "";
        int codigo = Integer.parseInt(arreglo[1]);
        String nombre = arreglo[2].toUpperCase();
        String provincia = arreglo[3];
        if (funcion.insertarCiudad(codigo, nombre, provincia)) {
            System.out.println("Se inserto la ciudad " + nombre + " exitosamente");
            cad = "Se creo una ciudad " + nombre + " exitosamente\n";
        } else {
            System.out.println("No se inserto la ciudad " + nombre + " exitosamente");
            cad = "No se creo una ciudad " + nombre + " exitosamente\n";
        }
        return cad;
    }

    public static String cargarSolicitud(StringTokenizer st) {
        String cad = "";
        int codOrigen = Integer.parseInt(st.nextToken());
        int codDestino = Integer.parseInt(st.nextToken());
        int tipoDoc = Integer.parseInt(st.nextToken());
        int numeroDoc = Integer.parseInt(st.nextToken());
        String fecha = st.nextToken();
        int cantMetros = Integer.parseInt(st.nextToken());
        int cantBultos = Integer.parseInt(st.nextToken());
        String domRetiro = st.nextToken().toUpperCase();
        String domEntrega = st.nextToken().toUpperCase();
        boolean pago = false;
        if (st.nextToken().equals("T")) {
            pago = true;
        }
        if (funcion.insertarSolicitud(codOrigen, codDestino, tipoDoc, numeroDoc, fecha, cantMetros, cantBultos, domRetiro, domEntrega, pago)) {
            System.out.println("Se inserto la solicitud de viaje de un cliente ingresado exitosamente");
            cad = "Se creo una solicitud de " + funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino) + " de cliente " + funcion.getNombreCliente(tipoDoc, numeroDoc) + " ingresado exitosamente\n";
        } else {
            System.out.println("No se inserto la solicitud de viaje de un cliente ingresado exitosamente");
            cad = "No se creo una solicitud de " + funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino) + "de cliente " + funcion.getNombreCliente(tipoDoc, numeroDoc) + " ingresado exitosamente\n";
        }
        return cad;
    }

    public static String cargarRuta(StringTokenizer st) {
        String cad = "";
        int codOrigen = Integer.parseInt(st.nextToken());
        int codDestino = Integer.parseInt(st.nextToken());
        double etiqueta = Double.parseDouble(st.nextToken());
        if (funcion.insertarRuta(codOrigen, codDestino, etiqueta)) {
            System.out.println("Se inserto la ruta entre dos ciudades ingresadas exitosamente");
            cad = "Se creo una ruta entre las ciudades " + funcion.getNombreCiudad(codOrigen) + " y " + funcion.getNombreCiudad(codDestino) + " exitosamente\n";
        } else {
            System.out.println("No se inserto la solicitud de viaje de un cliente ingresado exitosamente");
            cad = "No se creo una ruta entre las ciudades " + funcion.getNombreCiudad(codOrigen) + " y " + funcion.getNombreCiudad(codDestino) + " exitosamente\n";
        }
        return cad;
    }

    public static String cargarCliente(StringTokenizer st) {
        String[] arreglo = new String[7];
        int i = 1;
        while (st.hasMoreTokens()) {
            arreglo[i] = st.nextToken();
            i++;
        }
        String cad = "";
        int tipoDoc = Integer.parseInt(arreglo[1]);
        int numDoc = Integer.parseInt(arreglo[2]);
        String apellido = arreglo[3].toUpperCase();
        String nombre = arreglo[4].toUpperCase();
        int telefono = Integer.parseInt(arreglo[5]);
        String email = arreglo[6].toUpperCase();
        if (funcion.insertarCliente(tipoDoc, numDoc, nombre, apellido, telefono, email)) {
            System.out.println("Se inserto un cliente exitosamente");
            cad = "Se creo un cliente " + funcion.getNombreCliente(tipoDoc, numDoc) + " exitosamente\n";
        } else {
            System.out.println("No se inserto un cliente exitosamente");
            cad = "Se creo un cliente " + funcion.getNombreCliente(tipoDoc, numDoc) + " exitosamente\n";
        }
        return cad;
    }

    public static String ABMCiudades() {
        int opcion;
        String cad = "";
        menuABMCiudades();
        opcion = lectura.nextInt();
        while (opcion != 14) {
            cad = cad + opcionesABMCiudades(opcion);
            menuABMCiudades();
            lectura.hasNextLine();
            opcion = lectura.nextInt();
        }
        return cad;
    }

    public static void menuABMCiudades() {
        System.out.println("1 - Insertar una ciudad en el sistema");
        System.out.println("2 - Eliminar una ciudad en el sistema");
        System.out.println("3 - Obtener el nombre de una ciudad guardado en el sistema");
        System.out.println("4 - Cambiar el nombre de una ciudad guardado en el sistema");
        System.out.println("5 - Obtener la provincia de una ciudad guardado en el sistema");
        System.out.println("6 - Cambiar la provincia de una ciudad guardado en el sistema");
        System.out.println("7 - Listar los codigos postales guardados en el sistema");
        System.out.println("8 - Listar las ciudades guardadas en el sistema");
        System.out.println("9 - Verificar si existe una ciudad guardada en el sistema");
        System.out.println("10 - Verificar si esta vacio de ciudades en el sistema");
        System.out.println("11 - Mostrar la informacion de una ciudad guardado en el sistema");
        System.out.println("12 - Listar ciudades que comienzan con un prefijo");
        System.out.println("13 - Borrar las ciudades guardadas en el sistema");
        System.out.println("14 - Volver al menu principal");
    }

    public static String opcionesABMCiudades(int opcion) {
        String cad = "";
        switch (opcion) {
            case 1:
                cad = insertarCiudadSistema();
                break;
            case 2:
                cad = eliminarCiudadSistema();
                break;
            case 3:
                cad = getNombreCiudadSistema();
                break;
            case 4:
                cad = setNombreCiudadSistema();
                break;
            case 5:
                cad = getProvinciaCiudadSistema();
                break;
            case 6:
                cad = setProvinciaCiudadSistema();
                break;
            case 7:
                cad = listarCodigosPostales();
                break;
            case 8:
                cad = listarCiudades();
                break;
            case 9:
                cad = existeCiudad();
                break;
            case 10:
                cad = esVacioCiudades();
                break;
            case 11:
                cad = mostrarCiudad();
                break;
            case 12:
                cad = listarRangoCiudades();
                break;
            case 13:
                cad = vaciarCiudades();
                break;
            default:
                System.out.println("Numero ingresado incorrecto, intentalo de nuevo");
                cad = "Ingresa un numero erroneo en la eleccion de opciones de ABM Ciudades\n";
                break;
        }
        return cad;
    }

    public static String insertarCiudadSistema() {
        int codigo, numProvincia;
        String ciudad = "", salida = "Se inserta una ciudad sin exito\n";
        System.out.println("Ingrese el codigo de la ciudad:");
        codigo = lectura.nextInt();
        if (esCodigoPostal(codigo)) {
            System.out.println("Ingrese el nombre de la ciudad");
            ciudad = lectura.next().toUpperCase();
            mostrarProvincias();
            numProvincia = lectura.nextInt();
            lectura.nextLine();
            if (numProvincia < 0 || 24 < numProvincia) {
                System.out.println("Numero incorrecto, intentelo de nuevo");
                while (numProvincia < 0 || 24 < numProvincia) {
                    mostrarProvincias();
                    numProvincia = lectura.nextInt();
                    if (!(0 < numProvincia && numProvincia < 24)) {
                        System.out.println("Numero incorrecto, intentelo de nuevo");
                    }
                }
                if (funcion.insertarCiudad(codigo, ciudad, numProvincia)) {
                    System.out.println("Se inserta la ciudad " + ciudad + " con exito");
                    salida = "Se inserta la ciudad " + ciudad + " con exito\n";
                }
            }
        }
        return salida;
    }

    public static String eliminarCiudadSistema() {
        String salida = "Se elimino una ciudad sin exito\n";
        String ciudad = "";
        int codigo = 0;
        System.out.println("Ingrese el codigo de la ciudad:");
        if (lectura.hasNextLine()) {
            codigo = lectura.nextInt();
        }
        boolean exito = false;
        if (esCodigoPostal(codigo)) {
            ciudad = funcion.getNombreCiudad(codigo);
            exito = funcion.eliminarCiudad(codigo);
        }
        if (exito) {
            System.out.println("Se elimino la ciudad " + ciudad + " con exito");
            salida = "Se elimino la ciudad " + ciudad + " con exito\n";
        }
        return salida;
    }

    public static String getNombreCiudadSistema() {
        String salida = "Se busco el nombre de una ciudad sin exito\n";
        String nombre;
        System.out.println("Ingrese el codigo de la ciudad:");
        int codigo = lectura.nextInt();
        if (esCodigoPostal(codigo)) {
            if (funcion.existeCiudad(codigo)) {
                nombre = funcion.getNombreCiudad(codigo);
                System.out.println("Nombre de la ciudad buscada: " + nombre);
                salida = "Se encuentra y se muestra el nombre de la ciudad buscada: " + nombre + "\n";
            }
        }
        return salida;
    }

    public static String setNombreCiudadSistema() {
        String salida = "Se busco una ciudad para modificar su nombre sin exito\n";
        String nombreViejo, nombreNuevo;
        boolean exito;
        System.out.println("Ingrese el codigo de la ciudad:");
        int codigo = lectura.nextInt();
        if (esCodigoPostal(codigo)) {
            if (funcion.existeCiudad(codigo)) {
                nombreViejo = funcion.getNombreCiudad(codigo);
                System.out.println("Ingrese el nuevo nombre de la ciudad: ");
                nombreNuevo = lectura.next().toUpperCase();
                exito = funcion.setNombreCiudad(codigo, nombreNuevo);
                System.out.println("Se modifico el nombre de la ciudad: " + nombreViejo + "--->" + nombreNuevo + ":"+ exito);
                if (exito) {
                    salida = "Se encuentra y se modifica el nombre de una ciudad buscada: " + nombreViejo + "--->" + nombreNuevo + "\n";
                }
            }
        }
        return salida;
    }

    public static String getProvinciaCiudadSistema() {
        String salida = "Se busco la provincia de una ciudad sin exito\n";
        String nombre;
        System.out.println("Ingrese el codigo de la ciudad:");
        int codigo = lectura.nextInt();
        if (esCodigoPostal(codigo)) {
            if (funcion.existeCiudad(codigo)) {
                nombre = funcion.getProvinciaCiudad(codigo);
                System.out.println("Provincia de la ciudad buscada: " + nombre);
                salida = "Se encuentra y se muestra la provincia de la ciudad buscada: " + nombre + "\n";
            }
        }
        return salida;
    }

    public static String setProvinciaCiudadSistema() {
        String salida = "Se busca una ciudad para modificar la provincia sin exito\n";
        int numProvincia;
        boolean exito;
        String nombreViejo, nombreNuevo;
        System.out.println("Ingrese el codigo de la ciudad:");
        int codigo = lectura.nextInt();
        if (esCodigoPostal(codigo)) {
            if (funcion.existeCiudad(codigo)) {
                nombreViejo = funcion.getProvinciaCiudad(codigo);
                mostrarProvincias();
                numProvincia = lectura.nextInt();
                nombreNuevo = funcion.getProvinciaArgentina(numProvincia);
                exito = funcion.setProvinciaCiudad(codigo, nombreNuevo);
                System.out.println("Se modifico la provincia de la ciudad " + nombreViejo + "--->" + nombreNuevo +": " + exito);
                if (exito) {
                    salida = "Se encuentra y se modifica el nombre de una ciudad buscada: " + nombreViejo + "--->" + nombreNuevo + "\n";
                }
            }
        }
        return salida;
    }

    public static String listarCodigosPostales() {
        String salida = "Se enlista codigos postales del sistema sin exito\n";
        Lista lis;
        if (!funcion.esVacioCiudades()) {
            lis = funcion.listarCodigosPostales();
            System.out.println("Lista de los codigos postales del sistema:\n");
            System.out.println(lis.toString());
            salida = "Se enlista y se muestran codigos postales del sistema con exito: \n" + lis.toString() + "\n";
        }
        return salida;
    }

    public static String listarCiudades() {
        String salida = "Se enlista ciudades del sistema sin exito\n";
        Lista lis;
        if (!funcion.esVacioCiudades()) {
            lis = funcion.listarCiudades();
            System.out.println("Lista de las ciudades del sistema:\n");
            System.out.println(lis.toString());
            salida = "Se enlista y se muestran las ciudades del sistema con exito: \n" + lis.toString() + "\n";
        }
        return salida;
    }

    public static String existeCiudad() {
        int codigo;
        String salida = "Verifica la existencia de una ciudad del sistema sin exito\n";
        if (!funcion.esVacioCiudades()) {
            System.out.println("Ingrese el codigo postal de la ciudad");
            codigo = lectura.nextInt();
            salida = "Se informo que no se encontro la ciudad buscada en el sistema \n";
            if (funcion.existeCiudad(codigo)) {
                System.out.println("Existe la ciudad buscada: "+ funcion.getNombreCiudad(codigo));
                salida = "Se informo que se encontro la ciudad buscada " + funcion.getNombreCiudad(codigo) + " en el sistema \n";
            } else {
                System.out.println("No existe la ciudad buscada");
            }
        }
        return salida;
    }

    public static String esVacioCiudades() {
        String salida = "Se informa que no hay ciudades en el sistema\n";
        if (funcion.esVacioCiudades()) {
            System.out.println("No hay ciudades en el sistema");
        } else {
            System.out.println("Si hay ciudades en el sistema");
            salida = "Se informa que hay ciudades en el sistema\n";
        }
        return salida;
    }

    public static String mostrarCiudad() {
        int codigo;
        String salida = "Se muestra la informacion de una ciudad en el sistema sin exito\n";
        System.out.println("Ingrese el codigo postal de la ciudad: ");
        codigo = lectura.nextInt();
        if (funcion.existeCiudad(codigo)) {
            System.out.println(funcion.mostrarCiudad(codigo));
            salida = "Se muestra la informacion de la ciudad " + funcion.getNombreCiudad(codigo) + " en el sistema con exito\n";
        }
        return salida;
    }

    public static String listarRangoCiudades() {
        int prefijo;
        String salida = "Se enlista ciudades que comienzan con el codigo postal del sistema sin exito\n";
        System.out.println("Se ingresa un prefijo de dos digitos, para devolver una lista de ciudades cuyo codigo postal" +
                " comienza con dicho prefijo");
        System.out.println("Ingrese el numero prefijo");
        prefijo = lectura.nextInt();
        if (sonDosDigitos(prefijo)) {
            Lista lis = funcion.listarRangoCiudades(prefijo);
            System.out.println("Lista de las ciudades en base al rangp del prefijo ingresado: \n" + lis.toString());
            salida = "Se enlista ciudades que comienzan con el perfijo " + prefijo + " en el codigo postal del sistema con exito: \n" + lis.toString() + "\n";
        }
        return salida;
    }

    public static String vaciarCiudades() {
        String salida = "Se borran las ciudades del sistema sin exito\n";
        if (funcion.vaciarCiudades()) {
            System.out.println("Se borraron las ciudades del sistema");
            salida = "Se informa que se borran las ciudades del sistema con exito\n";
        }
        return salida;
    }

    private static boolean sonDosDigitos(int num) {
        String numCadena = Integer.toString(num);
        return numCadena.length() == 2;
    }

    private static boolean esCodigoPostal(int num) {
        String numCadena = Integer.toString(num);
        return numCadena.length() == 4;
    }

    public static void mostrarProvincias() {
        System.out.println("Ingrese el numero de la provincia de la ciudad:");
        System.out.println("1 -  BUENOS AIRES");
        System.out.println("2 - CATAMARCA");
        System.out.println("3 - CHACO");
        System.out.println("4 - CHUBUT");
        System.out.println("5 - CORDOBA");
        System.out.println("6 - CORRIENTES");
        System.out.println("7 - ENTRE RIOS");
        System.out.println("8 - FORMOSA");
        System.out.println("9 - JUJUY");
        System.out.println("10 - LA PAMPA");
        System.out.println("11 - LA RIOJA");
        System.out.println("12 - MENDOZA");
        System.out.println("13 - MISIONES");
        System.out.println("14 - NEUQUEN");
        System.out.println("15 - RIO NEGRO");
        System.out.println("16 - SALTA");
        System.out.println("17 - SAN JUAN");
        System.out.println("18 - SAN LUIS");
        System.out.println("19 - SANTA CRUZ");
        System.out.println("20 - SANTA FE");
        System.out.println("21 - SANTIAGO DEL ESTERO");
        System.out.println("22 - TIERRA DEL FUEGO");
        System.out.println("23 - TUCUMAN");
    }


    public static String ABMRedDeRutas() {
        int opcion;
        String cad = "";
        int opcionAnterior;
        System.out.println("Antes de seguir se debe haber cargado el sistema Ciudades con la opcion 2 o hacer la carga inicial con la opcion 1");
        System.out.println("Quiere seguir adelante?");
        System.out.println("1 - Si");
        System.out.println("0 - No");
        opcionAnterior = lectura.nextInt();
        if (opcionAnterior == 1) {
            menuABMRedDeRutas();
            opcion = lectura.nextInt();
            while (opcion != 8) {
                cad = cad + opcionesABMRedDeRutas(opcion);
                menuABMCiudades();
                opcion = lectura.nextInt();
            }
        } else {
            cad = "Regresa de nuevo al menu principal ya que no ha ingresado ninguna ciudad en el sistema\n";
        }
        return cad;
    }

    public static void menuABMRedDeRutas() {
        System.out.println("1 - Insertar una Ruta en el sistema");
        System.out.println("2 - Eliminar una Ruta en el sistema");
        System.out.println("3 - Verificar si existe una ruta en el sistema");
        System.out.println("4 - Verifica si existe un camino por las rutas guardadas en el sistema");
        System.out.println("5 - Verificar si esta vacio de rutas en el sistema");
        System.out.println("6 - Listar el camino mas corto de ciudades en el sistema");
        System.out.println("7 - Listar el camino mas corto de distancias en el sistema");
        System.out.println("8 - Volver al menu principal");
    }

    public static String opcionesABMRedDeRutas(int opcion) {
        String cad = "";
        switch (opcion) {
            case 1:
                cad = insertarRutaSistema();
                break;
            case 2:
                cad = eliminarRutaSistema();
                break;
            case 3:
                cad = existeRuta();
                break;
            case 4:
                cad = existeCamino();
                break;
            case 5:
                cad = esVacioRutas();
                break;
            case 6:
                cad = listarCaminoCortoCiudades();
                break;
            case 7:
                cad = listarCaminoCortoDistancias();
                break;
            default:
                System.out.println("Numero ingresado incorrecto, intentalo de nuevo");
                cad = "Ingresa un numero erroneo en la eleccion de opciones de ABM Red de Rutas\n";
                break;
        }
        return cad;
    }

    public static String insertarRutaSistema() {
        int codOrigen, codDestino;
        double etiqueta;
        String salida = "Se inserta una ruta sin exito\n";
        System.out.println("Ingrese el codigo de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
            System.out.println("Ingrese la distancia de entre las ciudades: ");
            etiqueta = lectura.nextDouble();
            if (funcion.insertarRuta(codOrigen, codDestino, etiqueta)) {
                System.out.println("Se ingreso una ruta con exito entre "+ funcion.getNombreCiudad(codOrigen) + " y " + funcion.getNombreCiudad(codDestino));
                salida = "Se inserta una ruta entre las ciudades de " + funcion.getNombreCiudad(codOrigen) + " y " + funcion.getNombreCiudad(codDestino) + " con exito\n";
            }
        }
        return salida;
    }

    public static String eliminarRutaSistema() {
        String salida = "Se elimino una ruta sin exito\n";
        System.out.println("Ingrese el codigo de la ciudad origen:");
        int codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo de la ciudad origen:");
        int codDestino = lectura.nextInt();
        boolean exito = false;
        if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
            exito = funcion.eliminarRuta(codOrigen, codDestino);
        }
        if (exito) {
            System.out.println("Se elimino la ruta entre "+ funcion.getNombreCiudad(codOrigen) + " y " + funcion.getNombreCiudad(codDestino) +": " + exito);
            salida = "Se elimino una ruta entre las ciudades de " + funcion.getNombreCiudad(codOrigen) + " y " + funcion.getNombreCiudad(codDestino) + " con exito\n";
        }
        return salida;
    }

    public static String existeRuta() {
        int codOrigen, codDestino;
        boolean exito;
        String salida = "Verifica la existencia de una ruta entre dos ciudades del sistema sin exito\n";
        if (!funcion.esVacioCiudades()) {
            System.out.println("Ingrese el codigo postal de la ciudad");
            codOrigen = lectura.nextInt();
            System.out.println("Ingrese el codigo postal de la ciudad");
            codDestino = lectura.nextInt();
            if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
                exito = funcion.existeRuta(codOrigen, codDestino);
                if (exito) {
                    System.out.println("Existe una ruta entre la ciudad "+ funcion.getNombreCiudad(codOrigen)
                            + " y " + funcion.getNombreCiudad(codDestino));
                    salida = "Se informo que se encontro una ruta entre las ciudades " + funcion.getNombreCiudad(codOrigen)
                            + " y " + funcion.getNombreCiudad(codDestino) + "en el sistema \n";
                }
            }
        }
        return salida;
    }

    public static String existeCamino() {
        int codOrigen, codDestino;
        String salida = "Verifica si existe un camino de una ciudad a otra en el sistema sin exito\n";
        System.out.println("Ingrese el numero codigo postal de la ciudad de origen");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el numero codigo postal de la ciudad de origen");
        codDestino = lectura.nextInt();
        if (sonDosDigitos(codOrigen) && esCodigoPostal(codDestino)) {
            if (funcion.existeCamino(codOrigen, codDestino)) {
                System.out.println("Existe un camino de la ciudad " + funcion.getNombreCiudad(codOrigen)
                        + " a la ciudad " + funcion.getNombreCiudad(codDestino));
                salida = "Se informo que se encontro una camino de la ciudad " + funcion.getNombreCiudad(codOrigen)
                        + " a la ciudad " + funcion.getNombreCiudad(codDestino) + "en el sistema \n";
            }
        }
        return salida;
    }

    public static String esVacioRutas() {
        String salida = "Se informa que no hay ciudades en el sistema\n";
        if (funcion.esVacioCiudades()) {
            System.out.println("No hay ciudades en el sistema");
        } else {
            System.out.println("Si hay ciudades en el sistema");
            salida = "Se informa que hay ciudades en el sistema\n";
        }
        return salida;
    }

    public static String listarCaminoCortoCiudades() {
        String salida = "Se enlista las ciudades que forman el camino corto entre dos ciudades del sistema sin exito\n";
        Lista lis;
        if (!funcion.esVacioCiudades()) {
            System.out.println("Ingrese el codigo postal de la ciudad de origen: ");
            int codOrigen = lectura.nextInt();
            if (esCodigoPostal(codOrigen)) {
                System.out.println("Ingrese el codigo postal de la ciudad de destino: ");
                int codDestino = lectura.nextInt();
                lis = funcion.caminoCortoCiudades(codOrigen, codDestino);
                System.out.println("Lista de los ciudaes que forma el camino corto entre dos ciudades buscadas del sistema: \n" + lis.toString());
                salida = "Se enlista y se muestran las ciudades que forman el camino corto desde "
                        + funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino)
                        + " del sistema con exito: \n" + lis.toString() + "\n";
            }
        }
        return salida;
    }

    public static String listarCaminoCortoDistancias() {
        String salida = "Se enlista las ciudades que forman el camino corto entre dos ciudades del sistema sin exito\n";
        Lista lis;
        if (!funcion.esVacioCiudades()) {
            System.out.println("Ingrese el codigo postal de la ciudad de origen: ");
            int codOrigen = lectura.nextInt();
            if (esCodigoPostal(codOrigen)) {
                System.out.println("Ingrese el codigo postal de la ciudad de destino: ");
                int codDestino = lectura.nextInt();
                lis = funcion.caminoCortoDistancia(codOrigen, codDestino);
                System.out.println("Lista de los ciudaes que forma el camino corto entre dos ciudades buscadas del sistema: \n" + lis.toString());
                salida = "Se enlista y se muestran las ciudades que forman el camino corto desde "
                        + funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino)
                        + " del sistema con exito: \n" + lis.toString() + "\n";
            }
        }
        return salida;
    }


    public static String ABMClientes() {
        int opcion;
        String cad = "";
        menuABMClientes();
        opcion = lectura.nextInt();
        while (opcion != 6) {
            cad = cad + opcionesABMClientes(opcion);
            menuABMClientes();
            opcion = lectura.nextInt();
        }
        return cad;
    }

    public static void menuABMClientes() {
        System.out.println("1 - Insertar un Cliente en el sistema");
        System.out.println("2 - Eliminar un Cliente en el sistema");
        System.out.println("3 - Verificar si existe un cliente en el sistema");
        System.out.println("4 - Muestra la informacion de un cliente en el sistema");
        System.out.println("5 - Verificar si esta vacio de clientes en el sistema");
        System.out.println("6 - Volver al menu principal");
    }

    public static String opcionesABMClientes(int opcion) {
        String cad = "";
        switch (opcion) {
            case 1:
                cad = insertarCliente();
                break;
            case 2:
                cad = eliminarCliente();
                break;
            case 3:
                cad = existeCliente();
                break;
            case 4:
                cad = mostrarCliente();
                break;
            case 5:
                cad = esVacioClientes();
                break;
            default:
                System.out.println("Numero ingresado incorrecto, intentalo de nuevo");
                cad = "Ingresa un numero erroneo en la eleccion de opciones de ABM Clientes\n";
                break;
        }
        return cad;
    }

    public static String insertarCliente() {
        int tipoDoc, numDoc, telefono;
        String nombre, apellido, email;
        String salida = "Se inserta un cliente sin exito\n";
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingrese el numero del documento:");
        numDoc = lectura.nextInt();
        if (!funcion.existeCliente(tipoDoc, numDoc)) {
            System.out.println("Ingrese el nombre del cliente:");
            nombre = lectura.next().toUpperCase();
            System.out.println("Ingrese el apellido del cliente:");
            apellido = lectura.next().toUpperCase();
            System.out.println("Ingrese el numero de telefono del cliente:");
            telefono = lectura.nextInt();
            System.out.println("Ingrese el email del cliente:");
            email = lectura.next().toUpperCase();
            if (funcion.insertarCliente(tipoDoc, numDoc, nombre, apellido, telefono, email)) {
                System.out.println("Se ingreso el cliente "+ nombre + " con exito");
                salida = "Se inserta el cliente " + nombre + " con exito\n";
            }
        }
        return salida;
    }

    private static void listaDeDocumentos() {
        System.out.println("Ingrese el tipo de documento:");
        System.out.println("1 - Documento nacinal de identidad (DNI)");
        System.out.println("2 - Pasaporte nacional (PAS)");
        System.out.println("3 - Libreta civica (LC)");
        System.out.println("4 - Libreta de enrolamiento (LE)");
        System.out.println("5 - Certificado de nacionalidad con foto (CNF)");
    }

    public static String eliminarCliente() {
        int tipoDoc, numDoc;
        String nombre;
        String salida = "Se elimina un cliente sin exito\n";
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingrese el numero del documento:");
        numDoc = lectura.nextInt();
        if (funcion.existeCliente(tipoDoc, numDoc)) {
            nombre = funcion.getNombreCliente(tipoDoc, numDoc);
            if (funcion.eliminarCliente(tipoDoc, numDoc)) {
                System.out.println("Se elimina el cliente "+ nombre + " con exito");
                salida = "Se elimina el cliente " + nombre + " con exito\n";
            }
        }
        return salida;
    }

    public static String existeCliente() {
        int tipoDoc, numDoc;
        String salida = "Verifica la existencia de un cliente del sistema sin exito\n";
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingresa el numero del documento:");
        numDoc = lectura.nextInt();
        if (funcion.existeCliente(tipoDoc, numDoc)) {
            System.out.println("Existe el cliente buscado: " + funcion.getNombreCliente(tipoDoc, numDoc));
            salida = "Se informo que se encontro al cliente buscado " + funcion.getNombreCliente(tipoDoc, numDoc) + " en el sistema \n";
        }
        return salida;
    }

    public static String mostrarCliente() {
        int tipoDoc, numDoc;
        String salida = "Muestra de la informacion de un cliente del sistema sin exito\n";
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingresa el numero del documento:");
        numDoc = lectura.nextInt();
        if (funcion.existeCliente(tipoDoc, numDoc)) {
            System.out.println("Cliente buscado: \n" + funcion.mostrarCliente(tipoDoc, numDoc));
            salida = "Se mostro informacion de cliente " + funcion.getNombreCliente(tipoDoc, numDoc) + " en el sistema \n";
        }
        return salida;
    }

    public static String esVacioClientes() {
        String salida = "Se informa que no hay clientes en el sistema\n";
        if (funcion.esVacioClientes()) {
            System.out.println("No hay clientes en el sistema");
        } else {
            System.out.println("Si hay clientes en el sistema");
            salida = "Se informa que hay clientes en el sistema\n";
        }
        return salida;
    }

    public static String ABMPedidos() {
        int opcion;
        String cad = "";
        int opcionAnterior;
        System.out.println("Antes de seguir se debe haber cargado manualmente el sistema Ciudades con la opcion 2 y el sistema Clientes de " +
                "la opcion 4 o hacer la carga inicial con el lote fijo con la opcion 1");
        System.out.println("Quiere seguir adelante?");
        System.out.println("1 - Si");
        System.out.println("0 - No");
        opcionAnterior = lectura.nextInt();
        if (opcionAnterior == 1) {
            menuABMRedDeRutas();
            opcion = lectura.nextInt();
            while (opcion != 8) {
                cad = cad + opcionesABMPedidos(opcion);
                menuABMCiudades();
                opcion = lectura.nextInt();
            }
        } else {
            cad = "Regresa de nuevo al menu principal ya que no ha ingresado ninguna ciudad en el sistema\n";
        }
        return cad;
    }

    public static void menuABMPedidos() {
        System.out.println("1 - Insertar una Solicitud en el sistema");
        System.out.println("2 - Eliminar una Solicitud en el sistema");
        System.out.println("3 - Eliminar solicitudes de un cliente de un viaje en el sistema");
        System.out.println("4 - Eliminar solicitudes de un viaje en el sistema");
        System.out.println("5 - Verificar si existen solicitudes de un viaje en el sistema");
        System.out.println("6 - Verificar si existen solicitudes de un cliente de un viaje en el sistema");
        System.out.println("7 - Verificar si existe una solicitud de un cliente de un viaje en el sistema");
        System.out.println("8 - Verificar si esta vacio de solicitudes en el sistema");
        System.out.println("9 - Mostrar las solicitudes de un viaje especifico en el sistema");
        System.out.println("10 - Verificar si existe un camino perfecto en las solicitudes del sistema");
        System.out.println("11 - Volver al menu principal");
    }

    public static String opcionesABMPedidos(int opcion) {
        String cad;
        switch (opcion) {
            case 1:
                cad = insertarSolicitud();
                break;
            case 2:
                cad = eliminarSolicitud();
                break;
            case 3:
                cad = eliminarSolicitudesCliente();
                break;
            case 4:
                cad = eliminarSolicitudes();
                break;
            case 5:
                cad = existeCiudadesDeViaje();
                break;
            case 6:
                cad = existeClienteSolicitud();
                break;
            case 7:
                cad = existeSolicitud();
                break;
            case 8:
                cad = esVacioSolicitudes();
                break;
            case 9:
                cad = mostrarSolicitudes();
                break;
            case 10:
                cad = esCaminoPerfecto();
                break;
            default:
                System.out.println("Numero ingresado incorrecto, intentalo de nuevo");
                cad = "Ingresa un numero erroneo en la eleccion de opciones de ABM Pedidos\n";
                break;
        }
        return cad;
    }

    public static String insertarSolicitud() {
        int codOrigen, codDestino, cantMetros, cantBultos, tipoDoc, numDoc;
        String fecha, domEntrega, domRetiro;
        boolean pago;
        String salida = "Se inserta una solicitud de un cliente sin exito\n";
        System.out.println("Ingrese el codigo de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
            fecha = ingresarFecha();
            while (fecha.isEmpty()) {
                fecha = ingresarFecha();
            }
            listaDeDocumentos();
            tipoDoc = lectura.nextInt();
            System.out.println("Ingrese el numero del documento:");
            numDoc = lectura.nextInt();
            System.out.println("Ingrese la cantidad de metros cuadrados del pedido:");
            cantMetros = lectura.nextInt();
            System.out.println("Ingrese la cantidad de bultos del pedido:");
            cantBultos = lectura.nextInt();
            System.out.println("Ingrese el domicilio de retiro del pedido:");
            domRetiro = lectura.next().toUpperCase();
            System.out.println("Ingrese el domicilio de entrega del pedido:");
            domEntrega = lectura.next().toUpperCase();
            System.out.println("Ingrese si el envio esta pagado (true/false):");
            pago = lectura.hasNext();
            if (funcion.insertarSolicitud(codOrigen, codDestino, tipoDoc, numDoc, fecha, cantMetros, cantBultos, domRetiro, domEntrega, pago)) {
                System.out.println("Se ingreso una solicitud de "+ funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino)
                        + " de cliente " + funcion.getNombreCliente(tipoDoc, numDoc) +" con exito");
                salida = "Se inserta una solicitud de " + funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino)
                        + " de cliente " + funcion.getNombreCliente(tipoDoc, numDoc) + " con exito\n";
            }
        }
        return salida;
    }

    private static String ingresarFecha() {
        int anio, mes, dia;
        String fecha = "";
        System.out.println("Ingrese el año de esta forma AAAA: (ejemplo 2021, 2022)");
        anio = lectura.nextInt();
        if (esCuatroDigitos(anio)) {
            mostrarMeses();
            mes = lectura.nextInt();
            System.out.println("Ingresar el dia:");
            dia = lectura.nextInt();
            if (verificarFecha(dia, mes, anio)) {
                if (mes / 10 == 0) {
                    fecha = dia + "/0" + mes + "/" + anio;
                } else {
                    fecha = dia + "/" + mes + "/" + anio;
                }
            }
        }
        return fecha;
    }

    private static boolean esCuatroDigitos(int num) {
        String s = Integer.toString(num);
        return s.length() == 4;
    }

    private static void mostrarMeses() {
        System.out.println("Ingrese el numero del mes correspondiente:");
        System.out.println("1 Enero");
        System.out.println("2 Febrero");
        System.out.println("3 Marzo");
        System.out.println("4 Abril");
        System.out.println("5 Mayo");
        System.out.println("6 Junio");
        System.out.println("7 Julio");
        System.out.println("8 Agosto");
        System.out.println("9 Septiembre");
        System.out.println("10 Octubre");
        System.out.println("11 Nomviembre");
        System.out.println("12 Diciembre");
    }

    private static boolean verificarFecha(int dia, int mes, int anio) {
        boolean exito = false;
        switch (mes) {
            case 4, 6, 9, 11:
                exito = 0 < dia && dia <= 30;
                break;
            case 1, 3, 5, 7, 8, 10, 12:
                exito = (0 < dia && dia <= 31);
                break;
            case 2:
                if (Year.isLeap(anio)) {
                    exito = 0 < dia && dia <= 29;
                } else {
                    exito = 0 < dia && dia <= 29;
                }
                break;
        }
        return exito;
    }

    public static String eliminarSolicitud() {
        int codOrigen, codDestino, tipoDoc, numDoc;
        String fecha, domRetiro;
        String salida = "Se elimina una solicitud de un cliente sin exito\n";
        System.out.println("Ingrese el codigo de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
            fecha = ingresarFecha();
            while (fecha.isEmpty()) {
                fecha = ingresarFecha();
            }
            listaDeDocumentos();
            tipoDoc = lectura.nextInt();
            System.out.println("Ingrese el numero del documento:");
            numDoc = lectura.nextInt();
            System.out.println("Ingrese el domicilio de retiro del pedido:");
            domRetiro = lectura.next();
            if (funcion.eliminarSolicitud(codOrigen, codDestino, tipoDoc, numDoc, fecha, domRetiro)) {
                System.out.println("Se elimino una solicitud de "+ funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino)
                        + " de cliente " + funcion.getNombreCliente(tipoDoc, numDoc) +" con exito");
                salida = "Se elimino una solicitud de " + funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino)
                        + " de cliente " + funcion.getNombreCliente(tipoDoc, numDoc) + " con exito\n";
            }
        }
        return salida;
    }

    public static String eliminarSolicitudesCliente() {
        int codOrigen, codDestino, tipoDoc, numDoc;
        String salida = "Se elimina una solicitud de un cliente sin exito\n";
        System.out.println("Ingrese el codigo de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
            listaDeDocumentos();
            tipoDoc = lectura.nextInt();
            System.out.println("Ingrese el numero del documento:");
            numDoc = lectura.nextInt();
            if (funcion.eliminarSolicitudesCliente(codOrigen, codDestino, tipoDoc, numDoc)) {
                System.out.println("Se elimino todas las solicitud "+ funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino)
                        + " del cliente " + funcion.getNombreCliente(tipoDoc, numDoc) +" con exito");
                salida = "Se elimino todas las solicitudes de " + funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino)
                        + " del cliente " + funcion.getNombreCliente(tipoDoc, numDoc) + " con exito\n";
            }
        }
        return salida;
    }

    public static String eliminarSolicitudes() {
        int codOrigen, codDestino;
        String salida = "Se elimina una solicitud de un cliente sin exito\n";
        System.out.println("Ingrese el codigo de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
            if (funcion.eliminarSolicitudes(codOrigen, codDestino)) {
                System.out.println("Se eliminaron todas las solicitud del viaje de "+
                        funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino) +" con exito");
                salida = "Se eliminaron todas las solicitudes del viaje de " + funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino)
                        + " con exito\n";
            }
        }
        return salida;
    }

    public static String existeCiudadesDeViaje() {
        int codOrigen, codDestino;
        String salida = "Verifica la existencia de la solicitud un viaje del sistema sin exito\n";
        System.out.println("Ingrese el codigo postal de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingresa el codigo postal de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (funcion.existeCiudadesDeViaje(codOrigen, codDestino)) {
            System.out.println("Existe una solicitud del viaje de " + funcion.getNombreCiudad(codOrigen) +
                    " a " + funcion.getNombreCiudad(codDestino));
            salida = "Se informo que se encontro una solicitud de un viaje de " + funcion.getNombreCiudad(codOrigen) +
                    " a " + funcion.getNombreCiudad(codDestino) + " en el sistema \n";
        }
        return salida;
    }

    public static String existeClienteSolicitud() {
        int codOrigen, codDestino, tipoDoc, numDoc;
        String salida = "Verifica la existencia de alguna solicitud de un cliente de un viaje del sistema sin exito\n";
        System.out.println("Ingrese el codigo postal de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo postal de la ciudad destino:");
        codDestino = lectura.nextInt();
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingrese el numero del documento:");
        numDoc = lectura.nextInt();
        if (funcion.existeClienteSolicitud(codOrigen, codDestino, tipoDoc, numDoc)) {
            System.out.println("Existen solicitudes del cliente "+funcion.getNombreCliente(tipoDoc,numDoc) +
                    " de un viaje de " + funcion.getNombreCiudad(codOrigen) +" a " + funcion.getNombreCiudad(codDestino));
            salida = "Se informo que se encontraron solicitudes del cliente " + funcion.getNombreCliente(tipoDoc,numDoc) + " de un viaje de " + funcion.getNombreCiudad(codOrigen) +
                    " a " + funcion.getNombreCiudad(codDestino) + " en el sistema \n";
        }
        return salida;
    }

    public static String existeSolicitud() {
        int codOrigen, codDestino, tipoDoc, numDoc;
        String domRetiro, fecha;
        String salida = "Verifica la existencia de una solicitud de un cliente de un viaje del sistema sin exito\n";
        System.out.println("Ingrese el codigo postal de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo postal de la ciudad destino:");
        codDestino = lectura.nextInt();
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingrese el numero del documento:");
        numDoc = lectura.nextInt();
        fecha = ingresarFecha();
        while (fecha.isEmpty()) {
            fecha = ingresarFecha();
        }
        System.out.println("Ingrese el domicilio de retiro:");
        domRetiro = lectura.next().toUpperCase();
        if (funcion.existeSolicitud(codOrigen, codDestino, tipoDoc, numDoc, fecha, domRetiro)) {
            System.out.println("Existe solicitud del cliente "+ funcion.getNombreCliente(tipoDoc, numDoc) +
                    " de un viaje de " + funcion.getNombreCiudad(codOrigen) +" a " + funcion.getNombreCiudad(codDestino));
            salida = "Se informo que se encontraron solicitudes del cliente " + funcion.getNombreCliente(tipoDoc, numDoc) + " de un viaje de " + funcion.getNombreCiudad(codOrigen) +
                    " a " + funcion.getNombreCiudad(codDestino) + " en el sistema \n";
        }
        return salida;
    }

    public static String esVacioSolicitudes() {
        String salida = "Se informa que no hay clientes en el sistema\n";
        if (funcion.esVacioSolicitudes()) {
            System.out.println("No hay clientes en el sistema");
        } else {
            System.out.println("Si hay clientes en el sistema");
            salida = "Se informa que hay clientes en el sistema\n";
        }
        return salida;
    }

    public static String mostrarSolicitudes() {
        int codOrigen, codDestino;
        String salida = "Muestra de solicitudes de un viaje del sistema sin exito\n";
        System.out.println("Ingrese el codigo postal de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo postal de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (funcion.existeCiudadesDeViaje(codOrigen, codDestino)) {
            System.out.println("Se muestran las solicitudes de un viaje del sistema: \n" + funcion.mostrarSolicitudes(codOrigen, codDestino));
            salida = "Se mostraron las solicitudes del viaje de " + funcion.getNombreCiudad(codOrigen) +
                    " a " + funcion.getNombreCiudad(codDestino) + " en el sistema: \n" + funcion.mostrarSolicitudes(codOrigen, codDestino) + "\n";
        }
        return salida;
    }

    public static String esCaminoPerfecto() {
        String salida = "El camino perfecto en la lista de un viaje del sistema sin exito\n";
        boolean ingresar = true;
        int cantCamion;
        Lista lis = new Lista();
        while (ingresar) {
            ingresar = ingresarCodigosPostales(lis);
        }
        System.out.println("Ingrese la capacidad total en metros cuadrados del camion: ");
        cantCamion = lectura.nextInt();
        if (funcion.esCaminoPerfecto(lis, cantCamion)) {
            System.out.println("Existe camino perfecto en la lista de ciudades");
            salida = "El camino perfecto en la lista de un viaje del sistema con exito\n";
        }
        return salida;
    }

    private static boolean ingresarCodigosPostales(Lista lis) {
        boolean ingresar;
        int codPostal;
        System.out.println("Ingrese un codigo postal de una ciudad del sistema");
        codPostal = lectura.nextInt();
        while (!funcion.existeCiudad(codPostal)) {
            System.out.println("El codigo postal ingresado no se encuentra registrado, intentelo de nuevo:");
            codPostal = lectura.nextInt();
        }
        lis.insertar(codPostal, lis.longitud() + 1);
        System.out.println("Desea ingresar otro codigo postal en la lista? (true/false)");
        ingresar = lectura.hasNext();
        return ingresar;
    }


    public static String mostrarSistema() {
        System.out.println(funcion.mostrarSistema());
        return "Muestra el sistema actual al usuario\n";
    }
}
