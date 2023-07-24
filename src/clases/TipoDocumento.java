package clases;

import Estructuras.lineales.dinamicas.Lista;

public class TipoDocumento {
    //Usa una lista para guardar los tipos de documento
    private Lista tipos;

    public TipoDocumento() {
        tipos = new Lista();
        tipos.insertar("DNI", 1); //documento nacional de identidad
        tipos.insertar("PAS", 2); //pasaporte nacional
        tipos.insertar("LC", 3); //libreta civica
        tipos.insertar("LE", 4); //libreta de enrolamiento
        tipos.insertar("CNF", 5); //certificado de nacionalidad con foto
    }

    public String getTipoDocumento(int num) {
        String cad = "-";
        if (num <= tipos.longitud()) {
            cad = (String) tipos.recuperar(num);
        }
        return cad;
    }

    public boolean agregarTipoDocumento(String tipo) {
        return tipos.insertar(tipo, tipos.longitud() + 1);
    }

    public boolean borrarTipoDocumento(String tipo) {
        return tipos.eliminar(tipos.localizar(tipo));
    }

    public boolean existeTipoDocumento (String tipo){
        return tipos.localizar(tipo) != 0;
    }
    public int localizarTipoDocumento(String tipo){
        return tipos.localizar(tipo);
    }
}
