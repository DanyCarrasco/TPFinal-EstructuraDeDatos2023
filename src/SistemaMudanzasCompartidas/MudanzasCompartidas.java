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
    private static Escritor aviso;

    static {
        try {
            aviso = new Escritor("C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\escribeme.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        int opcion;
        aviso.vaciarTxt();
        menu();
        opcion = lectura.nextInt();
        while (opcion != 7) {
            ejecutarOpcion(opcion);
            menu();
            opcion = lectura.nextInt();
        }
        System.out.println("Se termina de ejecutar el sistema");
        aviso.registrarInfo("Se termina de ejecutar el sistema\n\nEstado final del sistema:\n\n" + funcion.mostrarSistema());
        lectura.close();
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

    public static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                menuDeCarga();
                break;
            case 2:
                ABMCiudades();
                break;
            case 3:
                ABMRedDeRutas();
                break;
            case 4:
                ABMClientes();
                break;
            case 5:
                ABMPedidos();
                break;
            case 6:
                mostrarSistema();
                break;
            default:
                System.out.println("Numero ingresado incorrecto, intentalo de nuevo");
                aviso.registrarInfo("Ingresa un numero erroneo en la eleccion de opciones de menu principal");
                break;
        }
    }

    public static void menuDeCarga() {
        metodo();
        System.out.println("Estado del sistema: \n");
        System.out.println(funcion.mostrarSistema());
        aviso.registrarInfo("Se muestra el sistema cargado al usuario");
        aviso.registrarInfo("Estado del sistema al terminar la carga inicial:\n\n" + funcion.mostrarSistema());
    }

    public static void metodo() {
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
                    metodoCargar(datos[i]);
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
    }

    public static void metodoCargar(String linea) {
        StringTokenizer st = new StringTokenizer(linea, ";");
        switch (st.nextToken()) {
            case "C":
                cargarCiudad(st);
                break;
            case "S":
                cargarSolicitud(st);
                break;
            case "R":
                cargarRuta(st);
                break;
            case "P":
                cargarCliente(st);
                break;
        }
    }

    public static void cargarCiudad(StringTokenizer st) {
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
            aviso.registrarInfo("Se creo una ciudad " + nombre + " exitosamente");
        } else {
            System.out.println("No se inserto la ciudad " + nombre + " exitosamente");
            aviso.registrarError("No se creo una ciudad " + nombre + " exitosamente");
        }
    }

    public static void cargarSolicitud(StringTokenizer st) {
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
            aviso.registrarInfo("Se creo una solicitud de " + funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino) + " de cliente " + funcion.getNombreCliente(tipoDoc, numeroDoc) + " ingresado exitosamente");
        } else {
            System.out.println("No se inserto la solicitud de viaje de un cliente ingresado exitosamente");
            aviso.registrarError("No se creo una solicitud de " + funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino) + "de cliente " + funcion.getNombreCliente(tipoDoc, numeroDoc) + " ingresado exitosamente");
        }
    }

    public static void cargarRuta(StringTokenizer st) {
        int codOrigen = Integer.parseInt(st.nextToken());
        int codDestino = Integer.parseInt(st.nextToken());
        double etiqueta = Double.parseDouble(st.nextToken());
        if (funcion.insertarRuta(codOrigen, codDestino, etiqueta)) {
            System.out.println("Se inserto la ruta entre dos ciudades ingresadas exitosamente");
            aviso.registrarInfo("Se creo una ruta entre las ciudades " + funcion.getNombreCiudad(codOrigen) + " y " + funcion.getNombreCiudad(codDestino) + " exitosamente");
        } else {
            System.out.println("No se inserto la solicitud de viaje de un cliente ingresado exitosamente");
            aviso.registrarError("No se creo una ruta entre las ciudades " + funcion.getNombreCiudad(codOrigen) + " y " + funcion.getNombreCiudad(codDestino) + " exitosamente");
        }
    }

    public static void cargarCliente(StringTokenizer st) {
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
            aviso.registrarInfo("Se creo un cliente " + funcion.getNombreCliente(tipoDoc, numDoc) + " exitosamente");
        } else {
            System.out.println("No se inserto un cliente exitosamente");
            aviso.registrarError("No se creo un cliente " + funcion.getNombreCliente(tipoDoc, numDoc) + " exitosamente");
        }
    }

    public static void ABMCiudades() {
        int opcion;
        menuABMCiudades();
        opcion = lectura.nextInt();
        while (opcion != 14) {
            opcionesABMCiudades(opcion);
            menuABMCiudades();
            lectura.hasNextLine();
            opcion = lectura.nextInt();
        }
        System.out.println("Se termina de ejecutar ABMCiudades");
        aviso.registrarInfo("Se termina de ejecutar ABMCiudades");
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

    public static void opcionesABMCiudades(int opcion) {
        switch (opcion) {
            case 1:
                insertarCiudadSistema();
                break;
            case 2:
                eliminarCiudadSistema();
                break;
            case 3:
                getNombreCiudadSistema();
                break;
            case 4:
                setNombreCiudadSistema();
                break;
            case 5:
                getProvinciaCiudadSistema();
                break;
            case 6:
                setProvinciaCiudadSistema();
                break;
            case 7:
                listarCodigosPostales();
                break;
            case 8:
                listarCiudades();
                break;
            case 9:
                existeCiudad();
                break;
            case 10:
                esVacioCiudades();
                break;
            case 11:
                mostrarCiudad();
                break;
            case 12:
                listarRangoCiudades();
                break;
            case 13:
                vaciarCiudades();
                break;
            default:
                System.out.println("Numero ingresado incorrecto, intentalo de nuevo");
                aviso.registrarInfo("Ingresa un numero erroneo en la eleccion de opciones de ABM Ciudades");
                break;
        }
    }

    public static void insertarCiudadSistema() {
        int codigo, numProvincia;
        String ciudad;
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
            } else {
                if (funcion.existeCiudad(codigo)) {
                    System.out.println("No se inserto ciudad " + codigo + " porque ya existe en el sistema");
                    aviso.registrarError("No se inserta la ciudad " + codigo + " porque ya existe en el sistema");
                } else {
                    if (funcion.insertarCiudad(codigo, ciudad, numProvincia)) {
                        System.out.println("Se inserta la ciudad " + codigo + " con exito");
                        aviso.registrarInfo("Se inserta la ciudad " + codigo + " con exito");
                    }
                }
            }
        } else {
            System.out.println("Se inserta una ciudad sin exito porque no se ingreso un codigo postal correcto");
            aviso.registrarError("Se inserta una ciudad sin exito porque no se ingreso un codigo postal correcto");
        }
    }

    public static void eliminarCiudadSistema() {
        int codigo;
        System.out.println("Ingrese el codigo de la ciudad:");
        codigo = lectura.nextInt();
        if (esCodigoPostal(codigo)) {
            if (funcion.existeCiudad(codigo)) {
                if (funcion.eliminarCiudad(codigo)) {
                    System.out.println("Se elimino la ciudad " + codigo + " con exito");
                    aviso.registrarInfo("Se elimino la ciudad " + codigo + " con exito");
                } else {
                    System.out.println("Se elimino una ciudad " + codigo + " sin exito");
                    aviso.registrarError("Se elimino una ciudad " + codigo + " sin exito");
                }
            } else {
                System.out.println("No se elimino la ciudad " + codigo + " porque ya no existe en el sistema");
                aviso.registrarInfo("No se elimino la ciudad " + codigo + " porque ya no existe en el sistema");
            }
        } else {
            System.out.println("Eliminacion de una ciudad " + codigo + " sin exito");
            aviso.registrarInfo("Eliminacion de una ciudad " + codigo + " sin exito");
        }
    }

    public static void getNombreCiudadSistema() {
        String nombre;
        System.out.println("Ingrese el codigo de la ciudad:");
        int codigo = lectura.nextInt();
        if (esCodigoPostal(codigo)) {
            if (funcion.existeCiudad(codigo)) {
                nombre = funcion.getNombreCiudad(codigo);
                System.out.println("Nombre de la ciudad buscada: " + nombre);
                aviso.registrarInfo("Se encuentra y se muestra el nombre de la ciudad buscada " + codigo);
            } else {
                System.out.println("No se encuentra la ciudad " + codigo);
                aviso.registrarError("No se encuentra la ciudad " + codigo);
            }
        } else {
            System.out.println("Se busco el nombre de ciudad " + codigo + " sin exito");
            aviso.registrarError("Se busco el nombre ciudad " + codigo + " sin exito");
        }
    }

    public static void setNombreCiudadSistema() {
        String nombreNuevo;
        System.out.println("Ingrese el codigo de la ciudad:");
        int codigo = lectura.nextInt();
        if (esCodigoPostal(codigo)) {
            if (funcion.existeCiudad(codigo)) {
                System.out.println("Ingrese el nuevo nombre de la ciudad: ");
                nombreNuevo = lectura.next().toUpperCase();
                if (funcion.setNombreCiudad(codigo, nombreNuevo)) {
                    System.out.println("Se modifico el nombre de la ciudad " + codigo);
                    aviso.registrarInfo("Se modifico el nombre de la ciudad " + codigo);
                } else {
                    System.out.println("Se busco ciudad " + codigo + "para modificar su nombre sin exito");
                    aviso.registrarError("Se busco ciudad " + codigo + "para modificar su nombre sin exito");
                }
            } else {
                System.out.println("No se encuentra la ciudad " + codigo + " en el sistema");
                aviso.registrarError("No se encuentra la ciudad " + codigo + "en el sistema");
            }
        } else {
            System.out.println("Se busco ciudad" + codigo + "para modificar su nombre sin exito");
            aviso.registrarError("Se busco ciudad" + codigo + "para modificar su nombre sin exito");
        }
    }

    public static void getProvinciaCiudadSistema() {
        System.out.println("Ingrese el codigo de la ciudad:");
        int codigo = lectura.nextInt();
        if (esCodigoPostal(codigo)) {
            if (funcion.existeCiudad(codigo)) {
                System.out.println("Provincia de la ciudad buscada: " + funcion.getProvinciaCiudad(codigo));
                aviso.registrarInfo("Se encuentra y se muestra la provincia de la ciudad buscada: " + codigo);
            } else {
                System.out.println("No se encuentra la ciudad " + codigo + "en el sistema");
                aviso.registrarError("No se encuentra la ciudad " + codigo + " en el sistema");
            }
        } else {
            System.out.println("Se busco la provincia de ciudad " + codigo + " sin exito");
            aviso.registrarError("Se busco la provincia de ciudad " + codigo + " sin exito");
        }
    }

    public static void setProvinciaCiudadSistema() {
        int numProvincia;
        String nombreNuevo;
        System.out.println("Ingrese el codigo de la ciudad:");
        int codigo = lectura.nextInt();
        if (esCodigoPostal(codigo)) {
            if (funcion.existeCiudad(codigo)) {
                mostrarProvincias();
                numProvincia = lectura.nextInt();
                nombreNuevo = funcion.getProvinciaArgentina(numProvincia);
                if (funcion.setProvinciaCiudad(codigo, nombreNuevo)) {
                    System.out.println("Se modifico la provincia de la ciudad " + codigo);
                    aviso.registrarInfo("Se encuentra y se modifica el nombre de una ciudad buscada: " + codigo);
                } else {
                    System.out.println("Se busca ciudad " + codigo + " para modificar la provincia sin exito");
                    aviso.registrarError("Se busca ciudad " + codigo + " para modificar la provincia sin exito");
                }
            } else {
                System.out.println("No se encuentra ciudad " + codigo + " en el sistema");
                aviso.registrarError("No se encuentra ciudad " + codigo + " en el sistema");
            }
        } else {
            System.out.println("Se busca ciudad " + codigo + " para modificar la provincia sin exito");
            aviso.registrarError("Se busca una ciudad " + codigo + " para modificar la provincia sin exito");
        }
    }

    public static void listarCodigosPostales() {
        Lista lis;
        if (!funcion.esVacioCiudades()) {
            lis = funcion.listarCodigosPostales();
            System.out.println("Lista de los codigos postales del sistema:\n");
            System.out.println(lis.toString());
            aviso.registrarInfo("Se enlista y se muestran codigos postales del sistema con exito");
        } else {
            System.out.println("No se encuentran ciudades ingresadas en el sistema");
            aviso.registrarError("No se encuentran ciudades ingresadas en el sistema");
        }
    }

    public static void listarCiudades() {
        Lista lis;
        if (!funcion.esVacioCiudades()) {
            lis = funcion.listarCiudades();
            System.out.println("Lista de las ciudades del sistema:\n");
            System.out.println(lis.toString());
            aviso.registrarInfo("Se enlista y se muestran las ciudades del sistema con exito");
        } else {
            System.out.println("No se encuentran ciudades en el sistema");
            aviso.registrarError("No se encuentran ciudades en el sistema");
        }
    }

    public static void existeCiudad() {
        int codigo;
        if (!funcion.esVacioCiudades()) {
            System.out.println("Ingrese el codigo postal de la ciudad");
            codigo = lectura.nextInt();
            if (funcion.existeCiudad(codigo)) {
                System.out.println("Existe la ciudad buscada: " + funcion.getNombreCiudad(codigo));
                aviso.registrarInfo("Se informo que se encontro la ciudad buscada " + funcion.getNombreCiudad(codigo) + " en el sistema");
            } else {
                System.out.println("No existe la ciudad " + codigo + " buscada");
                aviso.registrarInfo("Se informo que no se encontro la ciudad buscada " + codigo + " en el sistema");
            }
        } else {
            System.out.println("No se encuentran ciudades ingresadas en el sistema");
            aviso.registrarError("No se encuentran ciudades ingresadas en el sistema");
        }
    }

    public static void esVacioCiudades() {
        if (funcion.esVacioCiudades()) {
            System.out.println("No hay ciudades en el sistema");
            aviso.registrarInfo("Se informa que no hay ciudades en el sistema");
        } else {
            System.out.println("Si hay ciudades en el sistema");
            aviso.registrarInfo("Se informa que hay ciudades en el sistema");
        }
    }

    public static void mostrarCiudad() {
        int codigo;
        System.out.println("Ingrese el codigo postal de la ciudad: ");
        codigo = lectura.nextInt();
        if (funcion.existeCiudad(codigo)) {
            System.out.println(funcion.mostrarCiudad(codigo));
            aviso.registrarInfo("Se muestra la informacion de la ciudad " + funcion.getNombreCiudad(codigo) + " en el sistema con exito");
        } else {
            System.out.println("No se encuentra la ciudad " + codigo + " en el sistema");
            aviso.registrarError("No se encuentra la ciudad " + codigo + " en el sistema");
        }
    }

    public static void listarRangoCiudades() {
        int prefijo;
        System.out.println("Se ingresa un prefijo de dos digitos, para devolver una lista de ciudades cuyo codigo postal" +
                " comienza con dicho prefijo");
        System.out.println("Ingrese el numero prefijo");
        prefijo = lectura.nextInt();
        if (sonDosDigitos(prefijo)) {
            if (!funcion.esVacioCiudades()) {
                Lista lis = funcion.listarRangoCiudades(prefijo);
                System.out.println("Lista de las ciudades en base al rango del prefijo ingresado: \n" + lis.toString());
                aviso.registrarInfo("Se enlista ciudades que comienzan con el perfijo " + prefijo + " en el codigo postal del sistema con exito");
            } else {
                System.out.println("No se encuentra nunguna ciudad en el sistema");
                aviso.registrarError("No se encuentra ninguna ciudad en el sistema");
            }
        } else {
            System.out.println("Se enlista ciudades que comienzan con el codigo postal del sistema sin exito");
            aviso.registrarError("Se enlista ciudades que comienzan con el codigo postal del sistema sin exito");
        }
    }

    public static void vaciarCiudades() {
        if (funcion.vaciarCiudades()) {
            System.out.println("Se borraron las ciudades del sistema");
            aviso.registrarInfo("Se informa que se borran las ciudades del sistema con exito");
        } else {
            System.out.println("Se borran las ciudades del sistema sin exito");
            aviso.registrarError("Se borran las ciudades del sistema sin exito");
        }
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


    public static void ABMRedDeRutas() {
        int opcion;
        menuABMRedDeRutas();
        opcion = lectura.nextInt();
        while (opcion != 8) {
            opcionesABMRedDeRutas(opcion);
            menuABMRedDeRutas();
            opcion = lectura.nextInt();
        }
        System.out.println("Se termina de ejecutar ABMRedDeRutas");
        aviso.registrarInfo("Se termina de ejecutar ABMRedDeRutas");
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

    public static void opcionesABMRedDeRutas(int opcion) {
        switch (opcion) {
            case 1:
                insertarRutaSistema();
                break;
            case 2:
                eliminarRutaSistema();
                break;
            case 3:
                existeRuta();
                break;
            case 4:
                existeCamino();
                break;
            case 5:
                esVacioRutas();
                break;
            case 6:
                listarCaminoCortoCiudades();
                break;
            case 7:
                listarCaminoCortoDistancias();
                break;
            default:
                System.out.println("Numero ingresado incorrecto, intentalo de nuevo");
                aviso.registrarInfo("Ingresa un numero erroneo en la eleccion de opciones de ABM Red de Rutas");
                break;
        }
    }

    public static void insertarRutaSistema() {
        int codOrigen, codDestino;
        double etiqueta;
        System.out.println("Ingrese el codigo de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
            if (funcion.existeRuta(codOrigen, codDestino)) {
                System.out.println("Existe una ruta entre " + codOrigen + " y " + codDestino);
                aviso.registrarError("Existe una ruta entre " + codOrigen + " y " + codDestino);
            } else {
                System.out.println("Ingrese la distancia de entre las ciudades: ");
                etiqueta = lectura.nextDouble();
                if (funcion.insertarRuta(codOrigen, codDestino, etiqueta)) {
                    System.out.println("Se ingreso una ruta con exito entre " + codOrigen + " y " + codDestino);
                    aviso.registrarInfo("Se inserta una ruta entre las ciudades de " + codOrigen + " y " + codDestino + " con exito");
                }
            }
        } else {
            System.out.println("Se inserta una ruta sin exito");
            aviso.registrarError("Se inserta una ruta sin exito");
        }
    }

    public static void eliminarRutaSistema() {
        System.out.println("Ingrese el codigo de la ciudad origen:");
        int codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo de la ciudad origen:");
        int codDestino = lectura.nextInt();
        boolean exito = false;
        if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
            if (funcion.existeRuta(codOrigen, codDestino)) {
                exito = funcion.eliminarRuta(codOrigen, codDestino);
            } else {
                System.out.println("No existe la ruta entre " + codOrigen + " y " + codDestino);
                aviso.registrarError("No existe la ruta entre " + codOrigen + " y " + codDestino);
            }
        }
        if (exito) {
            System.out.println("Se elimino la ruta entre " + codOrigen + " y " + codDestino + ": " + exito);
            aviso.registrarInfo("Se elimino una ruta entre las ciudades de " + codOrigen + " y " + codDestino + " con exito");
        } else {
            System.out.println("Se elimino una ruta sin exito entre " + codOrigen + " y " + codDestino);
            aviso.registrarError("Se elimino una ruta sin exito entre " + codOrigen + " y " + codDestino);
        }
    }

    public static void existeRuta() {
        int codOrigen, codDestino;
        if (!funcion.esVacioCiudades()) {
            System.out.println("Ingrese el codigo postal de la ciudad");
            codOrigen = lectura.nextInt();
            System.out.println("Ingrese el codigo postal de la ciudad");
            codDestino = lectura.nextInt();
            if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
                if (funcion.existeRuta(codOrigen, codDestino)) {
                    System.out.println("Existe una ruta entre la ciudad " + codOrigen + " y " + codDestino);
                    aviso.registrarInfo("Se informo que se encontro una ruta entre las ciudades " + codOrigen + " y " + codDestino + "en el sistema ");
                } else {
                    System.out.println("No existe la ruta entre " + codOrigen + " y " + codDestino);
                    aviso.registrarError("No existe la ruta entre " + codOrigen + " y " + codDestino);
                }
            } else {
                System.out.println("Verifica la existencia de una ruta entre dos ciudades del sistema sin exito");
                aviso.registrarError("Verifica la existencia de una ruta entre dos ciudades del sistema sin exito");
            }
        } else {
            System.out.println("No se encuentran ciudades ingresadas en el sistema");
            aviso.registrarError("No se encuentran ciudades ingresadas en el sistema");
        }
    }

    public static void existeCamino() {
        int codOrigen, codDestino;
        System.out.println("Ingrese el numero codigo postal de la ciudad de origen");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el numero codigo postal de la ciudad de origen");
        codDestino = lectura.nextInt();
        if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
            if (funcion.existeCamino(codOrigen, codDestino)) {
                System.out.println("Existe un camino de la ciudad " + codOrigen + " y " + codDestino);
                aviso.registrarInfo("Se informo que se encontro una camino de la ciudad " + codOrigen + " y " + codDestino + " en el sistema");
            } else {
                System.out.println("No se encuentra la ruta entre " + codOrigen + " y " + codDestino);
                aviso.registrarError("No se encuentra la ruta entre " + codOrigen + " y " + codDestino);
            }
        } else {
            System.out.println("Verifica si existe un camino de ciudad " + codOrigen + " y " + codDestino + " en el sistema sin exito");
            aviso.registrarError("Verifica si existe un camino de ciudad " + codOrigen + " y " + codDestino + " en el sistema sin exito");
        }
    }

    public static void esVacioRutas() {
        if (funcion.esVacioCiudades()) {
            aviso.registrarInfo("Se informa que no hay ciudades en el sistema");
            System.out.println("No hay ciudades en el sistema");
        } else {
            System.out.println("Si hay ciudades en el sistema");
            aviso.registrarInfo("Se informa que hay ciudades en el sistema");
        }
    }

    public static void listarCaminoCortoCiudades() {
        Lista lis;
        if (!funcion.esVacioCiudades()) {
            System.out.println("Ingrese el codigo postal de la ciudad de origen: ");
            int codOrigen = lectura.nextInt();
            System.out.println("Ingrese el codigo postal de la ciudad de destino: ");
            int codDestino = lectura.nextInt();
            if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
                if (funcion.existeCiudad(codOrigen) && funcion.existeCiudad(codDestino)) {
                    lis = funcion.caminoCortoCiudades(codOrigen, codDestino);
                    if (lis.esVacia()) {
                        System.out.println("No se encuentra un camino entre " + codOrigen + " y " + codDestino);
                        aviso.registrarError("No se encuentra un camino entre " + codOrigen + " y " + codDestino);
                    } else {
                        System.out.println("Lista de los ciudaes que forma el camino corto entre dos ciudades buscadas del sistema: \n" + lis.toString());
                        aviso.registrarInfo("Se enlista y se muestran las ciudades que forman el camino corto desde " + codOrigen + " a " + codDestino + " del sistema con exito");
                    }
                } else {
                    System.out.println("No se encuentra ciudades ingresdas en el sistema");
                    aviso.registrarError("No se encuentra ciudades ingresadas en el sistema");
                }
            } else {
                System.out.println("Se enlista las ciudades que forman el camino corto entre " + codOrigen + " a " + codDestino + " del sistema sin exito");
                aviso.registrarError("Se enlista las ciudades que forman el camino corto entre dos ciudades del sistema sin exito");
            }
        } else {
            System.out.println("No hay ciudades ingresdas en el sistema");
            aviso.registrarError("No hay ciudades ingresadas en el sistema");
        }
    }

    public static void listarCaminoCortoDistancias() {
        Lista lis;
        if (!funcion.esVacioCiudades()) {
            System.out.println("Ingrese el codigo postal de la ciudad de origen: ");
            int codOrigen = lectura.nextInt();
            System.out.println("Ingrese el codigo postal de la ciudad de destino: ");
            int codDestino = lectura.nextInt();
            if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
                if (funcion.existeCiudad(codOrigen) && funcion.existeCiudad(codDestino)) {
                    lis = funcion.caminoCortoDistancia(codOrigen, codDestino);
                    if (lis.esVacia()) {
                        System.out.println("Se enlista las ciudades que forman el camino corto entre dos ciudades del sistema sin exito");
                        aviso.registrarError("Se enlista las ciudades que forman el camino corto entre dos ciudades del sistema sin exito");
                    } else {
                        System.out.println("Lista de los ciudaes que forma el camino corto entre dos ciudades buscadas del sistema: \n" + lis.toString());
                        aviso.registrarInfo("Se enlista y se muestran las ciudades que forman el camino corto desde "
                                + codOrigen + " a " + codDestino + " del sistema con exito");
                    }
                } else {
                    System.out.println("No se encuentran las ciudades " + codOrigen + " a " + codDestino);
                    aviso.registrarError("No se encuentran las ciudades " + codOrigen + " a " + codDestino);
                }
            } else {
                System.out.println("Se enlista las ciudades que forman el camino corto entre dos ciudades del sistema sin exito");
                aviso.registrarError("Se enlista las ciudades que forman el camino corto entre dos ciudades del sistema sin exito");
            }
        } else {
            System.out.println("No hay ciudades en el sistema");
            aviso.registrarError("No hay ciudades en el sistema");
        }
    }


    public static void ABMClientes() {
        int opcion;
        menuABMClientes();
        opcion = lectura.nextInt();
        while (opcion != 14) {
            opcionesABMClientes(opcion);
            menuABMClientes();
            opcion = lectura.nextInt();
        }
        System.out.println("Se termina de ejecutar ABMClientes");
        aviso.registrarInfo("Se termina de ejecutar ABMClientes");
    }

    public static void menuABMClientes() {
        System.out.println("1 - Insertar un Cliente en el sistema");
        System.out.println("2 - Eliminar un Cliente en el sistema");
        System.out.println("3 - Obtener el nombre de un cliente en el sistema");
        System.out.println("4 - Modificar el nombre de un cliente en el sistema");
        System.out.println("5 - Obtener el apellido de un cliente en el sistema");
        System.out.println("6 - Modificar el apellido de un cliente en el sistema");
        System.out.println("7 - Obtener el telefono de un cliente en el sistema");
        System.out.println("8 - Modificar el telefono de un cliente en el sistema");
        System.out.println("9 - Obtener el email de un cliente en el sistema");
        System.out.println("10 - Modificar el email de un cliente en el sistema");
        System.out.println("11 - Verificar si existe un cliente en el sistema");
        System.out.println("12 - Muestra la informacion de un cliente en el sistema");
        System.out.println("13 - Verificar si esta vacio de clientes en el sistema");
        System.out.println("14 - Volver al menu principal");
    }

    public static void opcionesABMClientes(int opcion) {
        switch (opcion) {
            case 1:
                insertarCliente();
                break;
            case 2:
                eliminarCliente();
                break;
            case 3:
                getNombreCliente();
                break;
            case 4:
                setNombreCliente();
                break;
            case 5:
                getApellidoCliente();
                break;
            case 6:
                setApellidoCliente();
                break;
            case 7:
                getTelefonoCliente();
                break;
            case 8:
                setTelefonoCliente();
                break;
            case 9:
                getEmailCliente();
                break;
            case 10:
                setEmailCliente();
                break;
            case 11:
                existeCliente();
                break;
            case 12:
                mostrarCliente();
                break;
            case 13:
                esVacioClientes();
                break;
            default:
                System.out.println("Numero ingresado incorrecto, intentalo de nuevo");
                aviso.registrarInfo("Ingresa un numero erroneo en la eleccion de opciones de ABM Clientes");
                break;
        }
    }

    public static void insertarCliente() {
        int tipoDoc, numDoc, telefono;
        String nombre, apellido, email;
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
                System.out.println("Se ingreso el cliente " + nombre + " con exito");
                aviso.registrarInfo("Se inserta el cliente " + nombre + " con exito");
            }
        } else {
            System.out.println("Existe cliente de tipo de documento " + tipoDoc + " y numero " + numDoc + " en el sistema");
            aviso.registrarError("Existe cliente de tipo de documento " + tipoDoc + " y numero " + numDoc + " en el sistema");
        }
    }

    private static void listaDeDocumentos() {
        System.out.println("Ingrese el tipo de documento:");
        System.out.println("1 - Documento nacinal de identidad (DNI)");
        System.out.println("2 - Pasaporte nacional (PAS)");
        System.out.println("3 - Libreta civica (LC)");
        System.out.println("4 - Libreta de enrolamiento (LE)");
        System.out.println("5 - Certificado de nacionalidad con foto (CNF)");
    }

    public static void eliminarCliente() {
        int tipoDoc, numDoc;
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingrese el numero del documento:");
        numDoc = lectura.nextInt();
        if (funcion.existeCliente(tipoDoc, numDoc)) {
            if (funcion.eliminarCliente(tipoDoc, numDoc)) {
                System.out.println("Se elimina el cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " con exito");
                aviso.registrarInfo("Se elimina el cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " con exito");
            } else {
                System.out.println("Se elimina un cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " sin exito");
                aviso.registrarError("Se elimina un cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " sin exito");
            }
        } else {
            System.out.println("No existe cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
            aviso.registrarError("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
        }
    }

    public static void getNombreCliente() {
        int tipoDoc, numDoc;
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingresa el numero del documento:");
        numDoc = lectura.nextInt();
        if (funcion.existeCliente(tipoDoc, numDoc)) {
            System.out.println("Nombre del cliente buscado: " + funcion.getNombreCliente(tipoDoc, numDoc));
            aviso.registrarInfo("Se encuentra y se muestra el nombre de cliente buscado de tipo documento " + tipoDoc + " y numero " + numDoc + " en el sistema");
        } else {
            System.out.println("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
            aviso.registrarError("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
        }
    }

    public static void setNombreCliente() {
        String nombre;
        int tipoDoc, numDoc;
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingresa el numero del documento:");
        numDoc = lectura.nextInt();
        String t = lectura.nextLine();
        if (funcion.existeCliente(tipoDoc, numDoc)) {
            System.out.println("Ingrese el nuevo nombre del cliente:");
            lectura.hasNextLine();
            nombre = lectura.nextLine();
            nombre = nombre.toUpperCase();
            if (funcion.setNombreCliente(tipoDoc, numDoc, nombre)) {
                System.out.println("Se cambio el nombre del cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
                aviso.registrarInfo("Se cambia el nombre de cliente buscado de tipo documento " + tipoDoc + " y numero " + numDoc + " en el sistema");
            } else {
                System.out.println("Cambio del nombre del cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " sin exito");
                aviso.registrarError("Cambio del nombre del cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " sin exito");
            }
        } else {
            System.out.println("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
            aviso.registrarError("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
        }
    }

    public static void getApellidoCliente() {
        int tipoDoc, numDoc;
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingresa el numero del documento:");
        numDoc = lectura.nextInt();
        if (funcion.existeCliente(tipoDoc, numDoc)) {
            System.out.println("Nombre del cliente buscado: " + funcion.getApellidoCliente(tipoDoc, numDoc));
            aviso.registrarInfo("Se encuentra y se muestra el apellido de cliente buscado de tipo documento " + tipoDoc + " y numero " + numDoc + " en el sistema");
        } else {
            System.out.println("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
            aviso.registrarError("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
        }
    }

    public static void setApellidoCliente() {
        String apellido;
        int tipoDoc, numDoc;
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingresa el numero del documento:");
        numDoc = lectura.nextInt();
        String t = lectura.nextLine();
        if (funcion.existeCliente(tipoDoc, numDoc)) {
            System.out.println("Ingrese el nuevo apellido del cliente:");
            apellido = lectura.nextLine().toUpperCase();
            if (funcion.setApellidoCliente(tipoDoc, numDoc, apellido)) {
                System.out.println("Se cambio el apellido del cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
                aviso.registrarInfo("Se cambia el apellido de cliente buscado de tipo documento " + tipoDoc + " y numero " + numDoc + " en el sistema");
            } else {
                System.out.println("Cambio del apellido del cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " sin exito");
                aviso.registrarError("Cambio del apellido del cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " sin exito");
            }
        } else {
            System.out.println("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
            aviso.registrarError("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
        }
    }

    public static void getTelefonoCliente() {
        int tipoDoc, numDoc;
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingresa el numero del documento:");
        numDoc = lectura.nextInt();
        if (funcion.existeCliente(tipoDoc, numDoc)) {
            System.out.println("Telefono del cliente buscado: " + funcion.getTelefonoCliente(tipoDoc, numDoc));
            aviso.registrarInfo("Se encuentra y se muestra el telefono de cliente buscado de tipo documento " + tipoDoc + " y numero " + numDoc + " en el sistema");
        } else {
            System.out.println("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
            aviso.registrarError("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
        }
    }

    public static void setTelefonoCliente() {
        int telefono;
        int tipoDoc, numDoc;
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingresa el numero del documento:");
        numDoc = lectura.nextInt();
        if (funcion.existeCliente(tipoDoc, numDoc)) {
            System.out.println("Ingrese el nuevo telefono del cliente:");
            telefono = lectura.nextInt();
            if (funcion.setTelefonoCliente(tipoDoc, numDoc, telefono)) {
                System.out.println("Se cambio el telefono del cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
                aviso.registrarInfo("Se cambia el telefono de cliente buscado de tipo documento " + tipoDoc + " y numero " + numDoc + " en el sistema");
            } else {
                System.out.println("Cambio del telefono del cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " sin exito");
                aviso.registrarError("Cambio del telefono del cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " sin exito");
            }
        } else {
            System.out.println("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
            aviso.registrarError("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
        }
    }

    public static void getEmailCliente() {
        int tipoDoc, numDoc;
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingresa el numero del documento:");
        numDoc = lectura.nextInt();
        if (funcion.existeCliente(tipoDoc, numDoc)) {
            System.out.println("Email del cliente buscado: " + funcion.getEmailCliente(tipoDoc, numDoc));
            aviso.registrarInfo("Se encuentra y se muestra el email de cliente buscado de tipo documento " + tipoDoc + " y numero " + numDoc + " en el sistema");
        } else {
            System.out.println("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
            aviso.registrarError("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
        }
    }

    public static void setEmailCliente() {
        String email;
        int tipoDoc, numDoc;
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingresa el numero del documento:");
        numDoc = lectura.nextInt();
        String t = lectura.nextLine();
        if (funcion.existeCliente(tipoDoc, numDoc)) {
            System.out.println("Ingrese el nuevo email del cliente:");
            email = lectura.nextLine().toUpperCase();
            if (funcion.setEmailCliente(tipoDoc, numDoc, email)) {
                System.out.println("Se cambio el email del cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
                aviso.registrarInfo("Se cambia el email de cliente buscado de tipo documento " + tipoDoc + " y numero " + numDoc + " en el sistema");
            } else {
                System.out.println("Cambio del email del cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " sin exito");
                aviso.registrarError("Cambio del email del cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " sin exito");
            }
        } else {
            System.out.println("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
            aviso.registrarError("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
        }
    }

    public static void existeCliente() {
        int tipoDoc, numDoc;
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingresa el numero del documento:");
        numDoc = lectura.nextInt();
        if (funcion.existeCliente(tipoDoc, numDoc)) {
            System.out.println("Existe el cliente buscado de tipo documento " + tipoDoc + " y numero " + numDoc);
            aviso.registrarInfo("Se informo que se encontro al cliente buscado de tipo documento " + tipoDoc + " y numero " + numDoc + " en el sistema");
        } else {
            System.out.println("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
            aviso.registrarError("No existe un cliente de tipo documento " + tipoDoc + " y numero " + numDoc);
        }
    }

    public static void mostrarCliente() {
        int tipoDoc, numDoc;
        listaDeDocumentos();
        tipoDoc = lectura.nextInt();
        System.out.println("Ingresa el numero del documento:");
        numDoc = lectura.nextInt();
        if (funcion.existeCliente(tipoDoc, numDoc)) {
            System.out.println("Cliente buscado: \n" + funcion.mostrarCliente(tipoDoc, numDoc));
            aviso.registrarInfo("Se mostro informacion de cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " en el sistema");
        } else {
            System.out.println("No se encuentra el cliente con tipo documento " + tipoDoc + " y numero " + numDoc);
            aviso.registrarError("No se encuentra el cliente con tipo documento " + tipoDoc + " y numero " + numDoc);
        }
    }

    public static void esVacioClientes() {
        if (funcion.esVacioClientes()) {
            System.out.println("No hay clientes en el sistema");
            aviso.registrarInfo("Se informa que no hay clientes en el sistema");
        } else {
            System.out.println("Si hay clientes en el sistema");
            aviso.registrarInfo("Se informa que hay clientes en el sistema");
        }
    }

    public static void ABMPedidos() {
        int opcion;
        menuABMPedidos();
        opcion = lectura.nextInt();
        while (opcion != 11) {
            opcionesABMPedidos(opcion);
            menuABMPedidos();
            opcion = lectura.nextInt();
        }
        System.out.println("Se termina de ejecutar ABMPedidos");
        aviso.registrarInfo("Se termina de ejecutar ABMPedidos");
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

    public static void opcionesABMPedidos(int opcion) {
        switch (opcion) {
            case 1:
                insertarSolicitud();
                break;
            case 2:
                eliminarSolicitud();
                break;
            case 3:
                eliminarSolicitudesCliente();
                break;
            case 4:
                eliminarSolicitudes();
                break;
            case 5:
                existeCiudadesDeViaje();
                break;
            case 6:
                existeClienteSolicitud();
                break;
            case 7:
                existeSolicitud();
                break;
            case 8:
                esVacioSolicitudes();
                break;
            case 9:
                mostrarSolicitudes();
                break;
            case 10:
                esCaminoPerfecto();
                break;
            default:
                System.out.println("Numero ingresado incorrecto, intentalo de nuevo");
                aviso.registrarInfo("Ingresa un numero erroneo en la eleccion de opciones de ABM Pedidos");
                break;
        }
    }

    public static void insertarSolicitud() {
        int codOrigen, codDestino, cantMetros, cantBultos, tipoDoc, numDoc;
        String fecha, domEntrega, domRetiro;
        boolean pago;
        System.out.println("Ingrese el codigo de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
            if (funcion.existeCiudad(codOrigen) && funcion.existeCiudad(codDestino)) {
                fecha = ingresarFecha();
                while (fecha.isEmpty()) {
                    fecha = ingresarFecha();
                }
                listaDeDocumentos();
                tipoDoc = lectura.nextInt();
                System.out.println("Ingrese el numero del documento:");
                numDoc = lectura.nextInt();
                if (funcion.existeCliente(tipoDoc, numDoc)) {
                    System.out.println("Ingrese la cantidad de metros cuadrados del pedido:");
                    cantMetros = lectura.nextInt();
                    System.out.println("Ingrese la cantidad de bultos del pedido:");
                    cantBultos = lectura.nextInt();
                    System.out.println("Ingrese el domicilio de retiro del pedido:");
                    domRetiro = lectura.next().toUpperCase();
                    System.out.println("Ingrese el domicilio de entrega del pedido:");
                    domEntrega = lectura.next().toUpperCase();
                    System.out.println("Ingrese si el envio esta pagado (true/false):");
                    pago = lectura.nextBoolean();
                    if (funcion.insertarSolicitud(codOrigen, codDestino, tipoDoc, numDoc, fecha, cantMetros, cantBultos, domRetiro, domEntrega, pago)) {
                        System.out.println("Se ingreso una solicitud de " + codOrigen + " a " + codDestino + " de cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " con exito");
                        aviso.registrarInfo("Se ingreso una solicitud de " + codOrigen + " a " + codDestino + " de cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " con exito");
                    } else {
                        System.out.println("Se inserta una solicitud " + codOrigen + " a " + codDestino + " de cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " sin exito");
                        aviso.registrarError("Se inserta una solicitud " + codOrigen + " a " + codDestino + " de cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " sin exito");
                    }
                } else {
                    System.out.println("No se encuentra el cliente con tipo documento " + tipoDoc + " y numero " + numDoc);
                    aviso.registrarError("No se encuentra el cliente con tipo documento " + tipoDoc + " y numero " + numDoc);
                }
            } else {
                System.out.println("No se encuentran las ciudades " + codOrigen + " y " + codDestino);
                aviso.registrarError("No se encuentra las ciudades " + codOrigen + " y " + codDestino);
            }
        } else {
            System.out.println("Se inserta una solicitud de un cliente sin exito");
            aviso.registrarError("Se inserta una solicitud de un cliente sin exito");
        }
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

    public static void eliminarSolicitud() {
        int codOrigen, codDestino, tipoDoc, numDoc;
        String fecha, domRetiro;
        System.out.println("Ingrese el codigo de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
            if (funcion.existeCiudad(codOrigen) && funcion.existeCiudad(codDestino)) {
                listaDeDocumentos();
                tipoDoc = lectura.nextInt();
                System.out.println("Ingrese el numero del documento:");
                numDoc = lectura.nextInt();
                if (funcion.existeCliente(tipoDoc, numDoc)) {
                    fecha = ingresarFecha();
                    while (fecha.isEmpty()) {
                        fecha = ingresarFecha();
                    }
                    System.out.println("Ingrese el domicilio de retiro del pedido:");
                    domRetiro = lectura.next();
                    if (funcion.eliminarSolicitud(codOrigen, codDestino, tipoDoc, numDoc, fecha, domRetiro)) {
                        System.out.println("Se elimino una solicitud de " + codOrigen + " a " + codDestino + " de cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " con exito");
                        aviso.registrarInfo("Se elimino una solicitud de " + codOrigen + " a " + codDestino + " de cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " con exito");
                    } else {
                        System.out.println("Se elimina una solicitud de " + codOrigen + " a " + codDestino + " de cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " sin exito");
                        aviso.registrarError("Se elimina una solicitud de " + codOrigen + " a " + codDestino + " de cliente de tipo documento " + tipoDoc + " y numero " + numDoc + " sin exito");
                    }
                } else {
                    System.out.println("No se encuentra el cliente con tipo documento " + tipoDoc + " y numero " + numDoc);
                    aviso.registrarError("No se encuentra el cliente con tipo documento " + tipoDoc + " y numero " + numDoc);
                }
            } else {
                System.out.println("No se encuentran las ciudades " + codOrigen + " y " + codDestino);
                aviso.registrarError("No se encuentra las ciudades " + codOrigen + " y " + codDestino);
            }
        } else {
            System.out.println("Se elimina una solicitud de un cliente sin exito");
            aviso.registrarError("Se elimina una solicitud de un cliente sin exito");
        }
    }

    public static void eliminarSolicitudesCliente() {
        int codOrigen, codDestino, tipoDoc, numDoc;
        System.out.println("Ingrese el codigo de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
            if (funcion.existeCiudad(codOrigen) && funcion.existeCiudad(codDestino)) {
                listaDeDocumentos();
                tipoDoc = lectura.nextInt();
                System.out.println("Ingrese el numero del documento:");
                numDoc = lectura.nextInt();
                if (funcion.existeCliente(tipoDoc, numDoc)) {
                    if (funcion.eliminarSolicitudesCliente(codOrigen, codDestino, tipoDoc, numDoc)) {
                        System.out.println("Se elimino todas las solicitud " + funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino)
                                + " del cliente " + funcion.getNombreCliente(tipoDoc, numDoc) + " con exito");
                        aviso.registrarInfo("Se elimino todas las solicitudes de " + funcion.getNombreCiudad(codOrigen) + " a " + funcion.getNombreCiudad(codDestino)
                                + " del cliente " + funcion.getNombreCliente(tipoDoc, numDoc) + " con exito");
                    } else {
                        System.out.println("Se elimina una solicitud de un cliente sin exito");
                        aviso.registrarError("Se elimina una solicitud de un cliente sin exito");
                    }
                } else {
                    System.out.println("No se encuentra el cliente con tipo documento " + tipoDoc + " y numero " + numDoc);
                    aviso.registrarError("No se encuentra el cliente con tipo documento " + tipoDoc + " y numero " + numDoc);
                }
            } else {

            }
        } else {
            System.out.println("Se elimina una solicitud de un cliente sin exito");
            aviso.registrarError("Se elimina una solicitud de un cliente sin exito");
        }
    }

    public static void eliminarSolicitudes() {
        int codOrigen, codDestino;
        System.out.println("Ingrese el codigo de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
            if (funcion.existeCiudad(codOrigen) && funcion.existeCiudad(codDestino)) {
                if (funcion.eliminarSolicitudes(codOrigen, codDestino)) {
                    System.out.println("Se eliminaron todas las solicitud del viaje de " + codOrigen + " a " + codDestino + " con exito");
                    aviso.registrarInfo("Se eliminaron todas las solicitudes del viaje de " + codOrigen + " a " + codDestino + " con exito");
                } else {
                    System.out.println("No se encuentra la solicitud");
                    aviso.registrarError("No se encuentra la solicitud");
                }
            } else {
                System.out.println("No se encuentran las ciudades " + codOrigen + " y " + codDestino);
                aviso.registrarError("No se encuentra las ciudades " + codOrigen + " y " + codDestino);
            }
        } else {
            System.out.println("Se elimina una solicitud de un cliente sin exito");
            aviso.registrarError("Se elimina una solicitud de un cliente sin exito");
        }
    }

    public static void existeCiudadesDeViaje() {
        int codOrigen, codDestino;
        System.out.println("Ingrese el codigo postal de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingresa el codigo postal de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (funcion.existeCiudadesDeViaje(codOrigen, codDestino)) {
            System.out.println("Existe una solicitud del viaje de " + codOrigen + " y " + codDestino);
            aviso.registrarInfo("Se informo que se encontro una solicitud de un viaje de " + codOrigen + " y " + codDestino + " en el sistema");
        } else {
            System.out.println("No existe una solicitud de viaje entre " + codOrigen + " y " + codDestino);
            aviso.registrarError("No existe una solicitud de viaje entre " + codOrigen + " y " + codDestino);
        }
    }

    public static void existeClienteSolicitud() {
        int codOrigen, codDestino, tipoDoc, numDoc;
        System.out.println("Ingrese el codigo postal de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo postal de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
            if (funcion.existeCiudad(codOrigen) && funcion.existeCiudad(codDestino)) {
                listaDeDocumentos();
                tipoDoc = lectura.nextInt();
                System.out.println("Ingrese el numero del documento:");
                numDoc = lectura.nextInt();
                if (funcion.existeCliente(tipoDoc, numDoc)) {
                    if (funcion.existeClienteSolicitud(codOrigen, codDestino, tipoDoc, numDoc)) {
                        System.out.println("Existen solicitudes del cliente de tipo documento " + tipoDoc + " y numero " + numDoc +
                                " de un viaje de " + codOrigen + " a " + codDestino);
                        aviso.registrarInfo("Se informo que se encontraron solicitudes del cliente de tipo documento " + tipoDoc + " y numero " + numDoc +
                                " de un viaje de " + codOrigen + " a " + codDestino + " en el sistema");
                    } else {
                        System.out.println("Verifica la existencia de alguna solicitud de un cliente de un viaje del sistema sin exito");
                        aviso.registrarError("Verifica la existencia de alguna solicitud de un cliente de un viaje del sistema sin exito");
                    }
                } else {
                    System.out.println("No se encuentra el cliente con tipo documento " + tipoDoc + " y numero " + numDoc);
                    aviso.registrarError("No se encuentra el cliente con tipo documento " + tipoDoc + " y numero " + numDoc);
                }
            } else {
                System.out.println("No se encuentran las ciudades " + codOrigen + " y " + codDestino);
                aviso.registrarError("No se encuentra las ciudades " + codOrigen + " y " + codDestino);
            }
        } else {
            System.out.println("Verifica la existencia de alguna solicitud de un cliente de un viaje del sistema sin exito");
            aviso.registrarError("Verifica la existencia de alguna solicitud de un cliente de un viaje del sistema sin exito");
        }
    }

    public static void existeSolicitud() {
        int codOrigen, codDestino, tipoDoc, numDoc;
        String domRetiro, fecha;
        System.out.println("Ingrese el codigo postal de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo postal de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (esCodigoPostal(codOrigen) && esCodigoPostal(codDestino)) {
            if (funcion.existeCiudad(codOrigen) && funcion.existeCiudad(codDestino)) {
                listaDeDocumentos();
                tipoDoc = lectura.nextInt();
                System.out.println("Ingrese el numero del documento:");
                numDoc = lectura.nextInt();
                if (funcion.existeCliente(tipoDoc, numDoc)) {
                    fecha = ingresarFecha();
                    while (fecha.isEmpty()) {
                        fecha = ingresarFecha();
                    }
                    System.out.println("Ingrese el domicilio de retiro:");
                    domRetiro = lectura.next().toUpperCase();
                    if (funcion.existeSolicitud(codOrigen, codDestino, tipoDoc, numDoc, fecha, domRetiro)) {
                        System.out.println("Existe solicitud del cliente tipo documento " + tipoDoc + " y numero " + numDoc +
                                " de un viaje de " + codOrigen + " a " + codDestino);
                        aviso.registrarInfo("Se informo que se encontraron solicitudes del cliente tipo documento " + tipoDoc + " y numero " + numDoc + " de un viaje de " + codOrigen + " a " + codDestino + " en el sistema");
                    } else {
                        System.out.println("No se encuentra la solicitud del cliente tipo documento " + tipoDoc + " y numero " + numDoc +
                                " de un viaje de " + codOrigen + " a " + codDestino);
                        aviso.registrarError("No se encuentra la solicitud del cliente tipo documento " + tipoDoc + " y numero " + numDoc +
                                " de un viaje de " + codOrigen + " a " + codDestino);
                    }
                } else {
                    System.out.println("No se encuentra el cliente con tipo documento " + tipoDoc + " y numero " + numDoc);
                    aviso.registrarError("No se encuentra el cliente con tipo documento " + tipoDoc + " y numero " + numDoc);
                }
            } else {
                System.out.println("No se encuentran las ciudades " + codOrigen + " y " + codDestino);
                aviso.registrarError("No se encuentra las ciudades " + codOrigen + " y " + codDestino);
            }
        } else {
            System.out.println("Verifica la existencia de una solicitud de un cliente de un viaje del sistema sin exito");
            aviso.registrarError("Verifica la existencia de una solicitud de un cliente de un viaje del sistema sin exito");
        }
    }

    public static void esVacioSolicitudes() {
        if (funcion.esVacioSolicitudes()) {
            System.out.println("No hay clientes en el sistema");
            aviso.registrarInfo("Se informa que no hay clientes en el sistema");
        } else {
            System.out.println("Si hay clientes en el sistema");
            aviso.registrarInfo("Se informa que hay clientes en el sistema");
        }
    }

    public static void mostrarSolicitudes() {
        int codOrigen, codDestino;
        System.out.println("Ingrese el codigo postal de la ciudad origen:");
        codOrigen = lectura.nextInt();
        System.out.println("Ingrese el codigo postal de la ciudad destino:");
        codDestino = lectura.nextInt();
        if (funcion.existeCiudadesDeViaje(codOrigen, codDestino)) {
            System.out.println("Se muestran las solicitudes de un viaje del sistema: \n" + funcion.mostrarSolicitudes(codOrigen, codDestino));
            aviso.registrarInfo("Se mostraron las solicitudes del viaje de " + codOrigen + " a " + codDestino + " en el sistema");
        } else {
            System.out.println("No se encuentran las ciudades " + codOrigen + " y " + codDestino);
            aviso.registrarError("No se encuentra las ciudades " + codOrigen + " y " + codDestino);
        }
    }

    public static void esCaminoPerfecto() {
        int ingresar = 0;
        int cantCamion;
        Lista lis = new Lista();
        while (ingresar == 0) {
            ingresar = ingresarCodigosPostales(lis);
        }
        System.out.println("Ingrese la capacidad total en metros cuadrados del camion: ");
        cantCamion = lectura.nextInt();
        if (funcion.esCaminoPerfecto(lis, cantCamion)) {
            System.out.println("Existe camino perfecto en la lista de ciudades");
            aviso.registrarInfo("El camino perfecto en la lista de un viaje del sistema con exito");
        } else {
            System.out.println("El camino perfecto en la lista de un viaje del sistema sin exito");
            aviso.registrarError("El camino perfecto en la lista de un viaje del sistema sin exito");
        }
    }

    private static int ingresarCodigosPostales(Lista lis) {
        int ingresar;
        int codPostal;
        String t = lectura.nextLine();
        System.out.println("Ingrese un codigo postal de una ciudad del sistema");
        codPostal = lectura.nextInt();
        t = lectura.nextLine();
        while (!funcion.existeCiudad(codPostal)) {
            System.out.println("El codigo postal ingresado no se encuentra registrado, intentelo de nuevo:");
            codPostal = lectura.nextInt();
        }
        lis.insertar(codPostal, lis.longitud() + 1);
        System.out.println("Desea ingresar otro codigo postal en la lista? (0 = true/1 = false)");
        ingresar = lectura.nextInt();
        return ingresar;
    }


    public static void mostrarSistema() {
        System.out.println(funcion.mostrarSistema());
        aviso.registrarInfo("Muestra el sistema actual al usuario");
        System.out.println("Se termina de ejecutar mostrarSistema");
        aviso.registrarInfo("Se termina de ejecutar mostrarSistema");
    }
}
