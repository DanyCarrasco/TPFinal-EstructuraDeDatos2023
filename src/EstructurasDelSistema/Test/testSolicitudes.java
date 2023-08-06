package EstructurasDelSistema.Test;

import Estructuras.lineales.dinamicas.Lista;
import EstructurasDelSistema.SistemaSolicitudes.Solicitudes;
import clases.Cliente;
import clases.Descripcion;
import clases.Documento;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class testSolicitudes {
    public static void main(String[] args) {
        //testToString();
        //testInsertarYEliminar();
        //testExiste();
        //testGetDescripcion();
        //testListar();
        //testEsVacio();
        //testVaciar();
        //testClone();
        //testMostrarSolicitudes();
        testEsCaminoPerfecto();
    }

    public static void testToString() {
        Solicitudes s1 = new Solicitudes();
        System.out.println("Creo un sistema de Solicitudes vacio");
        System.out.println("Solicitudes vacio: \n" + s1.toString());
        System.out.println("Inserto una solicitud de Buenos Aires a Neuquen de un cliente con la descripcion");
        s1.agregarDescripcion("Buenos Aires", "Neuquen", (new Cliente(new Documento(1, 43552859))), (new Descripcion(20, 5, "domicilio 1", "domicilio 2")));
        System.out.println("Solicitudes: \n" + s1.toString());
    }

    public static void testInsertarYEliminar() {
        Solicitudes s2 = new Solicitudes();
        Descripcion des1 = new Descripcion(25, 5, "domicilio A1", "domicilio A2");
        Descripcion des2 = new Descripcion(4, 2, "domicilio B1", "domicilio B2");
        Documento doc1 = new Documento(1, 12345678);
        Documento doc2 = new Documento(2, 91011121);
        Cliente c1 = new Cliente(doc1);
        Cliente c2 = new Cliente(doc2);
        System.out.println("Prueba de insertarSolicitud, agregarCliente y agregarDescripcion");
        System.out.println("Se crea Solicitudes vacio");
        System.out.println("Solicitudes s2: \n" + s2.toString());
        System.out.println("Elimino una solicitudes de 'Buenos Aires' a 'Neuquen' en Solicitudes vacio: " + s2.eliminarSolicitudes("Buenos Aires", "Neuquen"));
        System.out.println("Inserto una solicitud de 'Neuquen' a 'La Rioja' de un cliente con la descripcion: " + s2.insertarSolicitud("Neuquen", "La Rioja", c2, des1));
        System.out.println("Solicitudes s2: \n" + s2.toString());
        System.out.println("Inserto una solicitud de 'Catamarca' a 'Tucuman' de un cliente con la descripcion: " + s2.insertarSolicitud("Catamarca", "Tucuman", c1, des2));
        System.out.println("Agrego un cliente con descripcion de una solicitud inexistente: " + s2.agregarCliente("Buenos Aires", "Neuquen", c1, des1));
        System.out.println("Agrego un cliente con descripcion de una solicitud de 'Neuquen' a 'La Rioja': " + s2.agregarCliente("Neuquen", "La Rioja", c1, des1));
        System.out.println("Agrego una descripcion de un cliente no cargado en un solicitud de 'Catamarca' a 'Tucuman': " + s2.agregarDescripcion("Catamarca", "Tucuman", c2, des2));
        System.out.println("Agrego una descripcion de un cliente cargado en un solicitud de 'Neuquen' a 'La Rioja': " + s2.agregarDescripcion("Neuquen", "La Rioja", c1, des2));
        System.out.println("Solicitudes s2: \n" + s2.toString());
        System.out.println("--------------------------------------------------------------------------------------------\n\n");
        System.out.println("Prueba de eliminarSolicitud, eliminarCliente y eliminarDescripcion");
        System.out.println("Inserto una solicitud de 'Catamarca' a 'Bariloche' de un cliente c1 con descripcion des2: " + s2.insertarSolicitud("Catamarca", "Bariloche", c1, des2));
        System.out.println("Solicitudes s2: \n" + s2.toString());
        System.out.println("Elimino una solicitud de 'Buenos Aires' a 'Neuquen': " + s2.eliminarSolicitudes("Buenos Aires", "Neuquen"));
        System.out.println("Elimino de nuevo una solicitud de 'Buenos Aires' a 'Neuquen': " + s2.eliminarSolicitudes("Buenos Aires", "Neuquen"));
        System.out.println("Solicitudes s2: \n" + s2.toString());
        System.out.println("Elimino en solicitudes de un cliente c2 de 'Catamarca' a 'Tucuman': " + s2.eliminarSolicitudesCliente("Catamarca", "Tucuman", c2));
        System.out.println("Solicitudes s2: \n" + s2.toString());
        System.out.println("Elimino solicitudes de un cliente c1 agregado de 'Neuquen' a 'La Rioja': " + s2.eliminarSolicitudesCliente("Neuquen", "La Rioja", c1));
        System.out.println("Solicitudes s2: \n" + s2.toString());
        System.out.println("Elimino en solicitud de 'Neuquen' a 'La Rioja' un cliente c2 agregado: " + s2.eliminarSolicitudesCliente("Neuquen", "La Rioja", c2));
        System.out.println("Elimino de nuevo solicitud de 'Neuquen' a 'La Rioja' un cliente c2 agregado (false): " + s2.eliminarSolicitudesCliente("Neuquen", "La Rioja", c2));
        System.out.println("Solicitudes s2: \n" + s2.toString());
        System.out.println("Elimino en solicitud de 'Catamarca' a 'Tucuman' una descripcion des2 de un cliente c1: " + s2.eliminarSolicitud("Catamarca", "Tucuman", c1, des2));
        System.out.println("Solicitudes s2: \n" + s2.toString());
        System.out.println("Elimino en solicitud de 'Neuquen' a 'La Rioja' un cliente c2 inexistente: " + s2.eliminarSolicitud("Neuquen", "La Rioja", c2, des2));
        System.out.println("Solicitudes s2: \n" + s2.toString());
        System.out.println("Elimino en solicitud de 'Catamarca' a 'Bariloche' de cliente c1 existente una descripcion des1 no agregada: " + s2.eliminarSolicitud("Catamarca", "Bariloche", c1, des1));
        System.out.println("Solicitudes s2: \n" + s2.toString());
        System.out.println("Elimino en solicitud de 'Catamarca' a 'Bariloche' de cliente c1 existente una descripcion des2 agregada: " + s2.eliminarSolicitud("Catamarca", "Bariloche", c1, des2));
        System.out.println("Solicitudes s2: \n" + s2.toString());
    }

    public static void testExiste() {
        Descripcion des1 = new Descripcion(25, 5, "domicilio A1", "domicilio A2");
        Descripcion des2 = new Descripcion(4, 2, "domicilio B1", "domicilio B2");
        Documento doc1 = new Documento(1, 12345678);
        Documento doc2 = new Documento(2, 91011121);
        Cliente c1 = new Cliente(doc1);
        Cliente c2 = new Cliente(doc2);
        System.out.println("Prueba de existeCiudadesDeViaje, existeCliente y existeDescripcion");
        System.out.println("Se crea Solicitudes vacio");
        Solicitudes s3 = new Solicitudes();
        System.out.println("Existe CiudadesDeViaje en Solicitudes vacio: " + s3.existeCiudadesDeViaje("NEUQUEN", "BARILOCHE"));
        System.out.println("Existe Cliente en Solicitudes vacio: " + s3.existeCliente("NEUQUEN", "BARILOCHE", c1));
        System.out.println("Existe Descripcion en Solicitudes vacio: " + s3.existeDescripcion("NEUQUEN", "BARILOCHE", c1, des1));
        System.out.println("Ingreso una solicitud de 'NEUQUEN' a 'BARILOCHE' de un cliente con la descripcion en Solicitudes s3");
        s3.insertarSolicitud("NEUQUEN", "BARILOCHE", c1, des1);
        System.out.println("Existe CiudadesDeViaje ingresado en Solicitudes: " + s3.existeCiudadesDeViaje("NEUQUEN", "BARILOCHE"));
        System.out.println("Existe CiudadesDeViaje no ingresado en Solicitudes: " + s3.existeCiudadesDeViaje("NEUQUEN", "CUTRAL-CO"));
        System.out.println("Existe Cliente ingresado en Solicitud ingresada en Solicitudes: " + s3.existeCliente("NEUQUEN", "BARILOCHE", c1));
        System.out.println("Existe Cliente no ingresado en Solicitud ingresada en Solicitudes: " + s3.existeCliente("NEUQUEN", "BARILOCHE", c2));
        System.out.println("Existe Descripcion ingresado en Solicitud ingresada en Solicitudes: " + s3.existeDescripcion("NEUQUEN", "BARILOCHE", c1, des1));
        System.out.println("Existe Descripcion no ingresado en Solicitud ingresada en Solicitudes: " + s3.existeDescripcion("NEUQUEN", "BARILOCHE", c1, des2));
        System.out.println("Ingreso un cliente en solicitud ingresada en Solicitudes s3");
        s3.agregarCliente("NEUQUEN", "BARILOCHE", c2, des2);
        System.out.println("Existe Cliente c1 ingresado en Solicitud ingresada en Solicitudes: " + s3.existeCliente("NEUQUEN", "BARILOCHE", c1));
        System.out.println("Existe Cliente c2 ingresado en Solicitud ingresada en Solicitudes: " + s3.existeCliente("NEUQUEN", "BARILOCHE", c2));
        System.out.println("Existe Descripcion no ingresado en cliente ingresado de Solicitud ingresada en Solicitudes: " + s3.existeDescripcion("NEUQUEN", "BARILOCHE", c1, des2));
        System.out.println("Ingreso una descripcion en cliente ingresado en solicitud ingresada en Solicitudes s3");
        s3.agregarDescripcion("NEUQUEN", "BARILOCHE", c1, des2);
        System.out.println("Existe Descripcion des1 ingresado en cliente ingresado de Solicitud ingresada en Solicitudes: " + s3.existeDescripcion("NEUQUEN", "BARILOCHE", c1, des1));
        System.out.println("Existe Descripcion des2 ingresado en cliente ingresado de Solicitud ingresada en Solicitudes: " + s3.existeDescripcion("NEUQUEN", "BARILOCHE", c1, des2));
    }

    public static void testGetDescripcion() {
        Descripcion des1 = new Descripcion(25, 5, "domicilio A1", "domicilio A2");
        Descripcion des2 = new Descripcion(4, 2, "domicilio B1", "domicilio B2");
        Documento doc1 = new Documento(1, 12345678);
        Documento doc2 = new Documento(2, 91011121);
        Cliente c1 = new Cliente(doc1);
        Cliente c2 = new Cliente(doc2);
        System.out.println("Prueba de getDescripcion");
        Solicitudes s4 = new Solicitudes();
        System.out.println("Retorna Descripcion en Solicitud vacio (null): " + (s4.getDescripcion("BUENOS AIRES", "NEUQUEN", c1, des1.getFechaSolicitud(), des1.getDomicilioRetiro()) == null));
        System.out.println("Ingreso una solicitud de BUENOS AIRES a NEUQUEN de un cliente con la descripcion");
        s4.insertarSolicitud("BUENOS AIRES", "NEUQUEN", c1, des1);
        System.out.println("Solicitudes s4: \n" + s4.toString());
        System.out.println("Retorna Descripcion en Solicitud BUENOS AIRES a NEUQUEN de cliente no ingresado (null): " + (s4.getDescripcion("BUENOS AIRES", "NEUQUEN", c2, des1.getFechaSolicitud(), des1.getDomicilioRetiro()) == null));
        System.out.println("Retorna Descripcion en Solicitud BUENOS AIRES a NEUQUEN de cliente ingresado: \n" + s4.getDescripcion("BUENOS AIRES", "NEUQUEN", c1, des1.getFechaSolicitud(), des1.getDomicilioRetiro()).toString());
        System.out.println("Ingreso un cliente con la descripcion de una solicitud de BUENOS AIRES a NEUQUEN");
        s4.agregarCliente("BUENOS AIRES", "NEUQUEN", c2, des2);
        System.out.println("Solicitudes s4: \n" + s4.toString());
        System.out.println("Retorna Descripcion en Solicitud BUENOS AIRES a NEUQUEN de cliente ingresado: \n" + s4.getDescripcion("BUENOS AIRES", "NEUQUEN", c2, des2.getFechaSolicitud(), des2.getDomicilioRetiro()).toString());
        System.out.println("Retorna Descripcion en Solicitud BUENOS AIRES a NEUQUEN de cliente no ingresado (null): " + (s4.getDescripcion("BUENOS AIRES", "NEUQUEN", c1, des2.getFechaSolicitud(), des2.getDomicilioRetiro()) == null));
        System.out.println("Ingreso una Descripcion en cliente ingresado en solicitud de BUENOS AIRES a NEUQUEN");
        s4.agregarDescripcion("BUENOS AIRES", "NEUQUEN", c1, des2);
        System.out.println("Solicitudes s4: \n" + s4.toString());
        System.out.println("Retorna Descripcion ingresado en Solicitud BUENOS AIRES a NEUQUEN de cliente ingresado: \n" + s4.getDescripcion("BUENOS AIRES", "NEUQUEN", c1, des2.getFechaSolicitud(), des2.getDomicilioRetiro()).toString());
        System.out.println("Retorna Descripcion no ingresado en Solicitud BUENOS AIRES a NEUQUEN de cliente ingresado (null): " + (s4.getDescripcion("BUENOS AIRES", "NEUQUEN", c2, des1.getFechaSolicitud(), des1.getDomicilioRetiro()) == null));
        System.out.println("Retorna Descripcion en Solicitud no ingresado (null): " + (s4.getDescripcion("BUENOS AIRES", "TUCUMAN", c1, des1.getFechaSolicitud(), des1.getDomicilioRetiro()) == null));
    }

    public static void testListar() {
        Descripcion des1 = new Descripcion(25, 5, "domicilio A1", "domicilio A2");
        Descripcion des2 = new Descripcion(4, 2, "domicilio B1", "domicilio B2");
        Documento doc1 = new Documento(1, 12345678);
        Documento doc2 = new Documento(2, 91011121);
        Cliente c1 = new Cliente(doc1);
        Cliente c2 = new Cliente(doc2);
        System.out.println("Prueba de listarCiudadesDeViaje, listarNodoSolicitud, listarClientes, listarDescripcionesCliente");
        Solicitudes s5 = new Solicitudes();
        System.out.println("Solicitudes s5: \n" + s5.toString());
        System.out.println("Listar ciudadesDeViaje en Solicitudes vacio:\n" + s5.listarCiudadesDeViaje() + "\n");
        System.out.println("Listar NodoSolicitud en Solicitudes vacio:\n" + s5.listarNodoSolicitud() + "\n");
        System.out.println("Listar Clientes en Solicitudes vacio:\n" + s5.listarClientes("Buenos Aires", "Neuquen") + "\n");
        System.out.println("Listar Descripciones en Solicitudes vacio:\n" + s5.listarDescripcionesCliente("Buenos Aires", "Neuquen", c1) + "\n");
        System.out.println("Inserto una solicitud de 'Buenos Aires' a 'Neuquen' de un cliente con la descripcion: " + s5.insertarSolicitud("Buenos Aires", "Neuquen", c1, des1));
        System.out.println("Inserto una solicitud de 'Catamarca' a 'Bariloche' de un cliente con la descripcion: " + s5.insertarSolicitud("Catamarca", "Bariloche", c2, des2));
        System.out.println("Inserto una solicitud de 'Neuquen' a 'La Rioja' de un cliente con la descripcion: " + s5.insertarSolicitud("Neuquen", "La Rioja", c1, des2));
        System.out.println("Agrego un cliente con la descripcion de una solicitud de 'Buenos Aires' a 'Neuquen': " + s5.agregarCliente("Buenos Aires", "Neuquen", c2, des2));
        System.out.println("Agrego un cliente con la descripcion de una solicitud de 'Neuquen' a 'La Rioja': " + s5.agregarCliente("Neuquen", "La Rioja", c2, des1));
        System.out.println("Agrego una descripcion de un cliente en un solicitud de 'Neuquen' a 'La Rioja': " + s5.agregarDescripcion("Neuquen", "La Rioja", c2, des2));
        System.out.println("Agrego una descripcion de un cliente no cargado en un solicitud de 'Catamarca' a 'Bariloche': " + s5.agregarDescripcion("Catamarca", "Bariloche", c1, des2));
        System.out.println("Agrego un cliente en solicitud de 'Catamarca' a 'Bariloche': " + s5.agregarCliente("Catamarca", "Bariloche", c1, des2));
        System.out.println("Agrego una descripcion cargada de un cliente en un solicitud de 'Catamarca' a 'Bariloche': " + s5.agregarDescripcion("Catamarca", "Bariloche", c1, des2));
        System.out.println("Solicitudes s5: \n" + s5.toString());
        System.out.println("Listar ciudadesDeViaje en Solicitudes:\n" + s5.listarCiudadesDeViaje() + "\n");
        System.out.println("Listar NodoSolicitud en Solicitudes:\n" + s5.listarNodoSolicitud() + "\n");
        System.out.println("Listar Clientes de Buenos Aires a Neuquen en Solicitudes:\n" + s5.listarClientes("Buenos Aires", "Neuquen") + "\n");
        System.out.println("Listar Clientes de Neuquen a La Rioja en Solicitudes:\n" + s5.listarClientes("Neuquen", "La Rioja") + "\n");
        System.out.println("Listar Descripciones de Cliente c1 de Buenos Aires a Neuquen en Solicitudes:\n" + s5.listarDescripcionesCliente("Buenos Aires", "Neuquen", c1) + "\n");
        System.out.println("Listar Descripciones de Cliente c2 de Neuquen a La Rioja en Solicitudes:\n" + s5.listarDescripcionesCliente("Neuquen", "La Rioja", c2) + "\n");
        System.out.println("Listar Descripciones de Cliente c1 de Neuquen a La Rioja en Solicitudes:\n" + s5.listarDescripcionesCliente("Neuquen", "La Rioja", c1) + "\n");
    }

    public static void testEsVacio() {
        Descripcion des1 = new Descripcion(25, 5, "domicilio A1", "domicilio A2");
        Descripcion des2 = new Descripcion(4, 2, "domicilio B1", "domicilio B2");
        Documento doc1 = new Documento(1, 12345678);
        Documento doc2 = new Documento(2, 91011121);
        Cliente c1 = new Cliente(doc1);
        Cliente c2 = new Cliente(doc2);
        System.out.println("Prueba de esVacio, esVacioSolicitudes y esVacioSolicitudesCliente");
        System.out.println("Se crea Solicitudes vacio");
        Solicitudes s6 = new Solicitudes();
        System.out.println("Solicitudes s6: \n" + s6.toString());
        System.out.println("Solicitudes esta vacio: " + s6.esVacio());
        System.out.println("Inserto una solicitud de Buenos Aires a Neuquen de un cliente c1 con la descripcion des1");
        s6.insertarSolicitud("Buenos Aires", "Neuquen", c1, des1);
        System.out.println("Solicitudes s6: \n" + s6.toString());
        System.out.println("Solicitudes esta vacio: " + s6.esVacio());
    }

    public static void testVaciar() {
        Descripcion des1 = new Descripcion(25, 5, "domicilio A1", "domicilio A2");
        Descripcion des2 = new Descripcion(4, 2, "domicilio B1", "domicilio B2");
        Documento doc1 = new Documento(1, 12345678);
        Documento doc2 = new Documento(2, 91011121);
        Cliente c1 = new Cliente(doc1);
        Cliente c2 = new Cliente(doc2);
        System.out.println("Prueba de vaciar, vaciarSolicitudes y vaciarSolicitudesCliente");
        System.out.println("Se crea Solicitudes vacio");
        Solicitudes s7 = new Solicitudes();
        System.out.println("Solicitudes s7: \n" + s7.toString());
        System.out.println("Inserto una solicitud de Buenos Aires a Neuquen de un cliente c1 con la descripcion des1");
        s7.insertarSolicitud("Buenos Aires", "Neuquen", c1, des1);
        System.out.println("Inserto un cliente c2 con descripcion des2 en una solicitud de Buenos Aires a Neuquen");
        s7.agregarCliente("Buenos Aires", "Neuquen", c2, des2);
        System.out.println("Inserto una Descripcion des2 en cliente c1 en solicitud de Buenos Aires a Neuquen");
        s7.agregarDescripcion("Buenos Aires", "Neuquen", c1, des2);
        System.out.println("Solicitudes s7: \n" + s7.toString());
        System.out.println("Solicitudes esta vacio: " + s7.esVacio());
        System.out.println("Se vacia las solicitudes del cliente c1 de Buenos Aires a Neuquen");
        s7.vaciarSolicitudesCliente("Buenos Aires", "Neuquen", c1);
        System.out.println("Solicitudes esta vacio: " + s7.esVacio());
        System.out.println("Solicitudes s7: \n" + s7.toString());
        System.out.println("Se vacia las solicitudes de Buenos Aires a Neuquen");
        s7.vaciarSolicitudes("Buenos Aires", "Neuquen");
        System.out.println("Solicitudes esta vacio: " + s7.esVacio());
        System.out.println("Solicitudes s7: \n" + s7.toString());
    }

    public static void testClone() {
        Descripcion des1 = new Descripcion(25, 5, "domicilio A1", "domicilio A2");
        Descripcion des2 = new Descripcion(4, 2, "domicilio B1", "domicilio B2");
        Descripcion des3 = new Descripcion(16, 4, "domicilio C1", "domicilio C2");
        Documento doc1 = new Documento(1, 12345678);
        Documento doc2 = new Documento(2, 91011121);
        Documento doc3 = new Documento(1, 21222324);
        Cliente c1 = new Cliente(doc1);
        Cliente c2 = new Cliente(doc2);
        Cliente c3 = new Cliente(doc3);
        System.out.println("Se crea Solicitudes vacio");
        Solicitudes s8 = new Solicitudes();
        System.out.println("Se clona Solicitudes vacio");
        Solicitudes s9 = s8.clone();
        System.out.println("Se inserta una solicitud de Cordoba a Formosa de un cliente c1 con la descripcion des1: " + s8.insertarSolicitud("Cordoba", "Formosa", c1, des1));
        System.out.println("Solicitudes s8: \n" + s8.toString());
        System.out.println("Solicitudes clone s9 vacio: \n" + s9.toString());
        System.out.println("Se inserta otra solicitud en clone s9 de Buenos Aires a Cordoba de un cliente c1 con la descripcion des2: " + s9.insertarSolicitud("Buenos Aires", "Cordoba", c1, des2));
        System.out.println("Solicitudes s8: \n" + s8.toString());
        System.out.println("Solicitudes clone s9: \n" + s9.toString());
        System.out.println("Se elimina la descripcion des1 de cliente c1 de la solicitud de Cordoba a Formosa: " + s8.eliminarSolicitud("Cordoba", "Formosa", c1, des1));
        System.out.println("Solicitudes s8: \n" + s8.toString());
        System.out.println("Solicitudes clone s9: \n" + s9.toString());
        System.out.println("Se elimina la descripcion des2 de cliente c1 de solicitud desde Buenos Aires a Cordoba: " + s9.eliminarSolicitud("Buenos Aires", "Cordoba", c1, des2));
        System.out.println("Solicitudes s8: \n" + s8.toString());
        System.out.println("Solicitudes clone s9: \n" + s9.toString());
    }

    public static void testMostrarSolicitudes() {
        Descripcion des1 = new Descripcion(25, 5, "domicilio A1", "domicilio A2");
        Descripcion des2 = new Descripcion(4, 2, "domicilio B1", "domicilio B2");
        Documento doc1 = new Documento(1, 12345678);
        Documento doc2 = new Documento(2, 91011121);
        Cliente c1 = new Cliente(doc1);
        Cliente c2 = new Cliente(doc2);
        Solicitudes s10 = new Solicitudes();
        System.out.println("Se crea Solicitudes vacio");
        System.out.println("Solicitudes s10: \n" + s10.toString());
        System.out.println("Muestra lista de solicitudes de Solicitudes vacio: "+ s10.mostrarSolicitudes("Neuquen", "La Rioja"));
        System.out.println("Inserto una solicitud de 'Neuquen' a 'La Rioja' de un cliente con la descripcion: " + s10.insertarSolicitud("Neuquen", "La Rioja", c2, des1));
        System.out.println("Muestra lista de solicitudes 'Neuquen' a 'La Rioja': \n"+ s10.mostrarSolicitudes("Neuquen", "La Rioja"));
        System.out.println("Inserto una solicitud de 'Catamarca' a 'Tucuman' de un cliente con la descripcion: " + s10.insertarSolicitud("Catamarca", "Tucuman", c1, des2));
        System.out.println("Agrego un cliente con descripcion de una solicitud inexistente: " + s10.agregarCliente("Buenos Aires", "Neuquen", c1, des1));
        System.out.println("Agrego un cliente con descripcion de una solicitud de 'Neuquen' a 'La Rioja': " + s10.agregarCliente("Neuquen", "La Rioja", c1, des1));
        System.out.println("Agrego una descripcion de un cliente no cargado en un solicitud de 'Catamarca' a 'Tucuman': " + s10.agregarDescripcion("Catamarca", "Tucuman", c2, des2));
        System.out.println("Agrego una descripcion de un cliente cargado en un solicitud de 'Neuquen' a 'La Rioja': " + s10.agregarDescripcion("Neuquen", "La Rioja", c1, des2));
        System.out.println("Solicitudes s10: \n" + s10.toString());
        System.out.println("Muestra lista de solicitudes 'Neuquen' a 'La Rioja': \n"+ s10.mostrarSolicitudes("Neuquen", "La Rioja"));
        System.out.println("Inserto una solicitud de 'Catamarca' a 'Bariloche' de un cliente c1 con descripcion des2: " + s10.insertarSolicitud("Catamarca", "Bariloche", c1, des2));
        System.out.println("Solicitudes s2: \n" + s10.toString());
        System.out.println("Muestra lista de solicitudes 'Catamarca' a 'Bariloche': \n"+ s10.mostrarSolicitudes("Catamarca", "Bariloche"));
    }

    public static void testEsCaminoPerfecto() {
        Descripcion des1 = new Descripcion(25, 5, "domicilio A1", "domicilio A2");
        Descripcion des2 = new Descripcion(4, 2, "domicilio B1", "domicilio B2");
        Descripcion des3 = new Descripcion(16, 4, "domicilio C1", "domicilio C2");
        Descripcion des4 = new Descripcion(9, 3, "domicilio D1", "domicilio D2");
        Documento doc1 = new Documento(1, 12345678);
        Documento doc2 = new Documento(2, 91011121);
        Documento doc3 = new Documento(1, 23456789);
        Documento doc4 = new Documento(2, 10111213);
        Cliente c1 = new Cliente(doc1);
        Cliente c2 = new Cliente(doc2);
        Cliente c3 = new Cliente(doc3);
        Cliente c4 = new Cliente(doc4);
        Lista lis = new Lista();
        llenarLista(lis);
        Solicitudes s11 = new Solicitudes();
        System.out.println("Se crea Solicitudes vacio");
        System.out.println("Solicitudes s11: \n" + s11.toString());
        System.out.println("Es camino perfecto de Solicitudes vacio: "+ s11.esCaminoPerfecto(lis,100));
        System.out.println("Inserto una solicitud de 'San Rafael' a 'Chimbas' de un cliente con la descripcion: " + s11.insertarSolicitud("San Rafael", "Chimbas", c2, des1));
        System.out.println("Inserto una solicitud de 'Concordia' a 'San Rafael' de un cliente con la descripcion: " + s11.insertarSolicitud("Concordia", "San Rafael", c1, des2));
        System.out.println("Agrego un cliente con descripcion de una solicitud de 'San Rafael' a 'Chimbas': " + s11.agregarCliente("San Rafael", "Chimbas", c1, des1));
        System.out.println("Agrego una descripcion de un cliente cargado en un solicitud de 'San Rafael' a 'Chimbas': " + s11.agregarDescripcion("San Rafael", "Chimbas", c1, des2));
        System.out.println("Inserto una solicitud de 'Resistencia' a 'Concordia' de un cliente c1 con descripcion des2: " + s11.insertarSolicitud("Resistencia", "Concordia", c1, des2));
        System.out.println("Solicitudes s11: \n" + s11.toString());
        System.out.println("Es camino perfecto de Solicitudes: "+ s11.esCaminoPerfecto(lis,100));
    }

    private static void llenarLista(Lista lis) {
        lis.insertar("Resistencia", lis.longitud() + 1);
        lis.insertar("Concordia", lis.longitud() + 1);
        lis.insertar("San Rafael", lis.longitud() + 1);
        lis.insertar("Chimbas", lis.longitud() + 1);
    }
}
