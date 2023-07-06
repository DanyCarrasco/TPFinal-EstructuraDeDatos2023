package clases;

import Estructuras.lineales.dinamicas.Lista;

public class tipoDocumento {
    //Usa una lista para guardar los tipos de documento
    private Lista tipos;

    public tipoDocumento() {
        tipos = new Lista();
        tipos.insertar("DNI", 1); //documento nacional de identidad
        tipos.insertar("LC", 2); //libreta civica
        tipos.insertar("LE", 3); //libreta de enrolamiento
        tipos.insertar("CNF", 4); //certificado de nacionalidad con foto
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

}
