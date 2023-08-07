package SistemaMudanzasCompartidas;

import Estructuras.lineales.dinamicas.Lista;
import EstructurasDelSistema.Ciudades;
import EstructurasDelSistema.Clientes;
import EstructurasDelSistema.SistemaRutas.Rutas;
import EstructurasDelSistema.SistemaSolicitudes.Solicitudes;
import clases.Ciudad;
import clases.Cliente;
import clases.Descripcion;
import clases.Provincia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Funcionamiento {
    private Provincia listaProvincias;
    private Ciudades c1;
    private Rutas r1;
    private Clientes p1;
    private Solicitudes s1;

    public Funcionamiento() {
        this.listaProvincias = new Provincia();
        this.c1 = new Ciudades();
        this.r1 = new Rutas();
        this.p1 = new Clientes();
        this.s1 = new Solicitudes();
    }

    public String getProvinciaArgentina(int pos) {
        return listaProvincias.getProvincia(pos);
    }

    public boolean existeCiudad(int codigo) {
        return this.c1.existeCiudad(codigo);
    }

    public boolean insertarCiudad(int codigo, String nombre, int numProvincia) {
        boolean exito;
        exito = this.c1.insertarCiudad(codigo, nombre, this.listaProvincias.getProvincia(numProvincia));
        if (exito) {
            exito = this.r1.insertarCiudad(this.c1.getCiudad(codigo));
        }
        return exito;
    }

    public boolean insertarCiudad(int codigo, String nombre, String provincia) {
        boolean exito;
        exito = this.c1.insertarCiudad(codigo, nombre, provincia);
        if (exito) {
            exito = this.r1.insertarCiudad(this.c1.getCiudad(codigo));
        }
        return exito;
    }

    public boolean eliminarCiudad(int codigo) {
        boolean exito;
        Ciudad c = this.c1.getCiudad(codigo);
        exito = this.c1.eliminarCiudad(codigo);
        if (exito) {
            exito = this.r1.eliminarCiudad(c);
        }
        return exito;
    }

    public String getNombreCiudad(int codigo) {
        return this.c1.getNombreCiudad(codigo);
    }

    public boolean setNombreCiudad(int codigo, String nombre) {
        return this.c1.setNombreCiudad(codigo, nombre);
    }

    public String getProvinciaCiudad(int codigo) {
        return this.c1.getProvinciaCiudad(codigo);
    }

    public boolean setProvinciaCiudad(int codigo, String nombre) {
        return this.c1.setProvinciaCiudad(codigo, nombre);
    }

    public Lista listarCodigosPostales() {
        return this.c1.listarCodigosPostales();
    }

    public Lista listarCiudades() {
        return this.c1.listarCiudades();
    }

    public boolean esVacioCiudades() {
        return this.c1.esVacio();
    }

    public boolean vaciarCiudades() {
        this.c1.vaciar();
        this.r1.vaciar();
        return this.c1.esVacio();
    }

    public Lista listarRangoCiudades(int prefijo) {
        return this.c1.listarCiudadesRango(prefijo);
    }

    public String mostrarCiudad(int codigo) {
        return this.c1.mostrarCiudad(codigo);
    }

    public boolean insertarRuta(int codOrigen, int codDestino, double etiqueta) {
        boolean exito = this.c1.existeCiudad(codOrigen) && this.c1.existeCiudad(codDestino);
        if (exito) {
            exito = this.r1.insertarRuta(this.c1.getCiudad(codOrigen), this.c1.getCiudad(codDestino), etiqueta);
        }
        return exito;
    }

    public boolean eliminarRuta(int codOrigen, int codDestino) {
        boolean exito = this.c1.existeCiudad(codOrigen) && this.c1.existeCiudad(codDestino);
        if (exito) {
            exito = this.r1.eliminarRuta(this.c1.getCiudad(codOrigen), this.c1.getCiudad(codDestino));
        }
        return exito;
    }

    public boolean existeRuta(int codOrigen, int codDestino) {
        boolean exito = this.c1.existeCiudad(codOrigen) && this.c1.existeCiudad(codDestino);
        if (exito) {
            exito = this.r1.existeRuta(this.c1.getCiudad(codOrigen), this.c1.getCiudad(codDestino));
        }
        return exito;
    }

    public boolean existeCamino(int codOrigen, int codDestino) {
        boolean exito = this.c1.existeCiudad(codOrigen) && this.c1.existeCiudad(codDestino);
        if (exito) {
            exito = this.r1.existeCamino(this.c1.getCiudad(codOrigen), this.c1.getCiudad(codDestino));
        }
        return exito;
    }

    public Lista caminoCortoCiudades(int codOrigen, int codDestino) {
        Lista lis = new Lista();
        boolean exito = this.c1.existeCiudad(codOrigen) && this.c1.existeCiudad(codDestino);
        if (exito) {
            lis = this.r1.caminoCortoCiudades(this.c1.getCiudad(codOrigen), this.c1.getCiudad(codDestino));
        }
        return lis;
    }

    public Lista caminoCortoDistancia(int codOrigen, int codDestino) {
        Lista lis = new Lista();
        boolean exito = this.c1.existeCiudad(codOrigen) && this.c1.existeCiudad(codDestino);
        if (exito) {
            lis = this.r1.caminoCortoDistancias(this.c1.getCiudad(codOrigen), this.c1.getCiudad(codDestino));
        }
        return lis;
    }

    public boolean insertarCliente(int tipoDoc, int numDoc, String nombre, String apellido, int telefono, String email) {
        return this.p1.insertarCliente(tipoDoc, numDoc, nombre, apellido, telefono, email);
    }

    public boolean eliminarCliente(int tipoDoc, int numDoc) {
        return this.p1.eliminarCliente(tipoDoc, numDoc);
    }

    public String getNombreCliente(int tipoDoc, int numDoc) {
        return this.p1.getNombreCliente(tipoDoc, numDoc);
    }

    public boolean setNombreCliente(int tipoDoc, int numDoc, String nombre) {
        return this.p1.setNombreCliente(tipoDoc, numDoc, nombre);
    }

    public String getApellidoCliente(int tipoDoc, int numDoc) {
        return this.p1.getApellidoCliente(tipoDoc, numDoc);
    }

    public boolean setApellidoCliente(int tipoDoc, int numDoc, String apellido) {
        return this.p1.setNombreCliente(tipoDoc, numDoc, apellido);
    }

    public int getTelefonoCliente(int tipoDoc, int numDoc) {
        return this.p1.getTelefonoCliente(tipoDoc, numDoc);
    }

    public boolean setTelefonoCliente(int tipoDoc, int numDoc, int telefono) {
        return this.p1.setTelefonoCliente(tipoDoc, numDoc, telefono);
    }

    public String getEmailCliente(int tipoDoc, int numDoc) {
        return this.p1.getEmailCliente(tipoDoc, numDoc);
    }

    public boolean setEmailCliente(int tipoDoc, int numDoc, String email) {
        return this.p1.setEmailCliente(tipoDoc, numDoc, email);
    }

    public boolean existeCliente(int tipoDoc, int numDoc) {
        return this.p1.existeCliente(tipoDoc, numDoc);
    }

    public boolean esVacioClientes() {
        return this.p1.esVacio();
    }

    public String mostrarCliente(int tipoDoc, int numDoc) {
        return this.p1.mostrarCliente(tipoDoc, numDoc);
    }

    public boolean insertarSolicitud(int codOrigen, int codDestino, int tipoDoc, int numDoc, String fecha, int cantMetros, int cantBultos, String domRetiro, String domEntrega, boolean pagado) {
        boolean exito = false;
        if (this.c1.existeCiudad(codOrigen) && this.c1.existeCiudad(codDestino)) {
            String origen = this.c1.getNombreCiudad(codOrigen);
            String destino = this.c1.getNombreCiudad(codDestino);
            if (this.p1.existeCliente(tipoDoc, numDoc)) {
                Cliente persona = this.p1.getCliente(tipoDoc, numDoc);
                Descripcion des = new Descripcion(fecha, cantMetros, cantBultos, domRetiro, domEntrega, pagado);
                if (!this.s1.existeCiudadesDeViaje(origen, destino)) {
                    exito = this.s1.insertarSolicitud(origen, destino, persona, des);
                } else {
                    if (!this.s1.existeCliente(origen, destino, persona)) {
                        exito = this.s1.agregarCliente(origen, destino, persona, des);
                    } else {
                        if (!this.s1.existeDescripcion(origen, destino, persona, des)) {
                            exito = this.s1.agregarDescripcion(origen, destino, persona, des);
                        }
                    }
                }
            }
        }
        return exito;
    }

    public boolean eliminarSolicitud(int codOrigen, int codDestino, int tipoDoc, int numDoc, String fecha, String domRetiro) {
        boolean exito = false;
        if (this.c1.existeCiudad(codOrigen) && this.c1.existeCiudad(codDestino)) {
            String origen = this.c1.getNombreCiudad(codOrigen);
            String destino = this.c1.getNombreCiudad(codDestino);
            if (this.p1.existeCliente(tipoDoc, numDoc)) {
                Cliente persona = this.p1.getCliente(tipoDoc, numDoc);
                Descripcion des = new Descripcion(fecha, domRetiro);
                if (this.s1.existeDescripcion(origen, destino, persona, des)) {
                    exito = this.s1.eliminarSolicitud(origen, destino, persona, des);
                }
            }
        }
        return exito;
    }

    public boolean eliminarSolicitudesCliente(int codOrigen, int codDestino, int tipoDoc, int numDoc) {
        boolean exito = false;
        if (this.c1.existeCiudad(codOrigen) && this.c1.existeCiudad(codDestino)) {
            String origen = this.c1.getNombreCiudad(codOrigen);
            String destino = this.c1.getNombreCiudad(codDestino);
            if (this.p1.existeCliente(tipoDoc, numDoc)) {
                Cliente persona = this.p1.getCliente(tipoDoc, numDoc);
                exito = this.s1.eliminarSolicitudesCliente(origen, destino, persona);
            }
        }
        return exito;
    }

    public boolean eliminarSolicitudes(int codOrigen, int codDestino) {
        boolean exito = false;
        if (this.c1.existeCiudad(codOrigen) && this.c1.existeCiudad(codDestino)) {
            String origen = this.c1.getNombreCiudad(codOrigen);
            String destino = this.c1.getNombreCiudad(codDestino);
            exito = this.s1.eliminarSolicitudes(origen, destino);
        }
        return exito;
    }

    public boolean existeCiudadesDeViaje(int codOrigen, int codDestino) {
        boolean exito = false;
        if (this.c1.existeCiudad(codOrigen) && this.c1.existeCiudad(codDestino)) {
            String origen = this.c1.getNombreCiudad(codOrigen);
            String destino = this.c1.getNombreCiudad(codDestino);
            exito = this.s1.existeCiudadesDeViaje(origen, destino);
        }
        return exito;
    }

    public boolean existeClienteSolicitud(int codOrigen, int codDestino, int tipoDoc, int numDoc) {
        boolean exito = false;
        if (this.c1.existeCiudad(codOrigen) && this.c1.existeCiudad(codDestino)) {
            String origen = this.c1.getNombreCiudad(codOrigen);
            String destino = this.c1.getNombreCiudad(codDestino);
            if (this.p1.existeCliente(tipoDoc, numDoc)) {
                Cliente persona = this.p1.getCliente(tipoDoc, numDoc);
                exito = this.s1.existeCliente(origen, destino, persona);
            }
        }
        return exito;
    }

    public boolean existeSolicitud(int codOrigen, int codDestino, int tipoDoc, int numDoc, String fecha, String domRetiro) {
        boolean exito = false;
        if (this.c1.existeCiudad(codOrigen) && this.c1.existeCiudad(codDestino)) {
            String origen = this.c1.getNombreCiudad(codOrigen);
            String destino = this.c1.getNombreCiudad(codDestino);
            if (this.p1.existeCliente(tipoDoc, numDoc)) {
                Cliente persona = this.p1.getCliente(tipoDoc, numDoc);
                Descripcion des = new Descripcion(fecha, domRetiro);
                exito = this.s1.existeDescripcion(origen, destino, persona, des);
            }
        }
        return exito;
    }

    public boolean esVacioSolicitudes(){
        return this.s1.esVacio();
    }

    public String mostrarSolicitudes(int codOrigen, int codDestino) {
        String cad = "No se encuentran las ciudades en el sistema";
        if (this.c1.existeCiudad(codOrigen) && this.c1.existeCiudad(codDestino)) {
            String origen = this.c1.getNombreCiudad(codOrigen);
            String destino = this.c1.getNombreCiudad(codDestino);
            cad = this.s1.mostrarSolicitudes(origen, destino);
        }
        return cad;
    }

    public boolean esCaminoPerfecto(Lista lis, double cantCamion){
        int largo = lis.longitud();
        for (int i = 1; i <= largo; i++) {
            int codigo = (int) lis.recuperar(i);
            lis.eliminar(i);
            String ciudad = this.c1.getNombreCiudad(codigo);
            lis.insertar(ciudad,i);
        }
        return this.s1.esCaminoPerfecto(lis,cantCamion);
    }

    public String mostrarSistema() {
        String cad = "Sistema de Ciudades: \n\n";
        cad = cad + this.c1.toString() + "\n\n";
        cad = cad + "Red de rutas: \n\n" + this.r1.toString() + "\n\n";
        cad = cad + "Sistema de Clientes: \n\n" + this.p1.toString() + "\n\n";
        cad = cad + "Sistema de Solicitudes: \n\n" + this.s1.toString() + "\n\n";
        return cad;
    }
}
