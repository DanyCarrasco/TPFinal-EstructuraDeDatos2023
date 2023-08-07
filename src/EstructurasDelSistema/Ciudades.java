package EstructurasDelSistema;

import Estructuras.lineales.dinamicas.Lista;
import clases.Ciudad;
import Estructuras.DiccionarioAVL.ArbolAVLDicc;

public class Ciudades {
    private ArbolAVLDicc arbol;

    public Ciudades() {
        arbol = new ArbolAVLDicc();
    }

    public boolean existeCiudad(int codigoPostal) {
        return arbol.existeClave(codigoPostal);
    }

    public boolean insertarCiudad(int codigoPostal, String nombre, String provincia) {
        boolean exito = false;
        if (!arbol.existeClave(codigoPostal)) {
            exito = arbol.insertar(codigoPostal, (new Ciudad(codigoPostal, nombre, provincia)));
        }
        return exito;
    }

    public boolean eliminarCiudad(int codigoPostal) {
        boolean exito = false;
        if (arbol.existeClave(codigoPostal)) {
            exito = arbol.eliminar(codigoPostal);
        }
        return exito;
    }

    public Ciudad getCiudad(int codigoPostal) {
        Ciudad salida = null;
        if (arbol.existeClave(codigoPostal)) {
            salida = (Ciudad) arbol.obtenerInformacion(codigoPostal);
        }
        return salida;
    }

    public String getNombreCiudad(int codigoPostal) {
        Ciudad buscado = null;
        String nombre = "No se encontro ciudad buscada";
        if (arbol.existeClave(codigoPostal)) {
            buscado = (Ciudad) arbol.obtenerInformacion(codigoPostal);
            nombre = buscado.getNombre();
        }
        return nombre;
    }

    public boolean setNombreCiudad(int codigoPostal, String nombre) {
        boolean exito = false;
        if (arbol.existeClave(codigoPostal)) {
            Ciudad original = (Ciudad) arbol.obtenerInformacion(codigoPostal);
            original.setNombre(nombre);
            arbol.eliminar(codigoPostal);
            exito = arbol.insertar(original.getCodigoPostal(), original);
        }
        return exito;
    }

    public String getProvinciaCiudad(int codigoPostal) {
        Ciudad buscado = null;
        String provincia = "No se encuentra ciudad buscada";
        if (arbol.existeClave(codigoPostal)) {
            buscado = (Ciudad) arbol.obtenerInformacion(codigoPostal);
            provincia = buscado.getProvincia();
        }
        return provincia;
    }

    public boolean setProvinciaCiudad(int codigoPostal, String provincia) {
        boolean exito = false;
        if (arbol.existeClave(codigoPostal)) {
            Ciudad original = (Ciudad) arbol.obtenerInformacion(codigoPostal);
            original.setProvincia(provincia);
            arbol.eliminar(codigoPostal);
            exito = arbol.insertar(original.getCodigoPostal(), original);
        }
        return exito;
    }

    public Lista listarCodigosPostales(){
        return arbol.listarClaves();
    }

    public Lista listarCiudades(){
        return arbol.listarDatos();
    }

    public boolean esVacio(){
        return arbol.esVacio();
    }

    public Ciudades clone(){
        Ciudades clon = new Ciudades();
        clon.arbol = arbol.clone();
        return clon;
    }

    public void vaciar(){
        arbol.vaciar();
    }

    public String mostrarCiudad(int codigo){
        String cad = "No se encuentra la ciudad buscada";
        if (this.arbol.existeClave(codigo)){
            Ciudad c1 = (Ciudad) this.arbol.obtenerInformacion(codigo);
            cad = c1.toString();
        }
        return cad;
    }

    public Lista listarCiudadesRango(int prefijo){
        int codigoMin = prefijo*100;
        int codigoMax = ((prefijo+1)*100)-1;
        return arbol.listarRango(codigoMin,codigoMax);
    }

    public String toString(){
        String cad = "No hay ciudades";
        if (!arbol.esVacio()){
            cad = arbol.toString();
        }
        return cad;
    }
}
