package EstructurasDelSistema.SistemaSolicitudes;

import Estructuras.lineales.dinamicas.Lista;
import clases.Cliente;
import clases.Descripcion;
import clases.Documento;

public class NodoCliente {
    private Cliente persona;
    private Lista descripciones;

    public NodoCliente(Cliente persona, Descripcion des){
        this.persona = persona;
        descripciones = new Lista();
        descripciones.insertar(des,descripciones.longitud()+1);
    }

    public Cliente getCliente(){
        return this.persona;
    }

    public boolean agregarDescripcion(Descripcion des){
        boolean exito = false;
        if (descripciones.localizar(des) < 1 && this.persona != null) {
            exito = descripciones.insertar(des, descripciones.longitud()+1);
        }
        return exito;
    }

    public boolean eliminarDescripcion(Descripcion des){
        boolean exito = false;
        if (this.persona != null && !descripciones.esVacia()) {
            exito = this.descripciones.eliminar(this.descripciones.localizar(des));
        }
        return exito;
    }

    public Descripcion getDescripcion(String fecha, String domRetiro){
        Descripcion salida = null;
        if (persona != null && !this.descripciones.esVacia()) {
            salida = (Descripcion) this.descripciones.recuperar(descripciones.localizar(new Descripcion(fecha,domRetiro)));
        }
        return salida;
    }

    public Lista getListaDescripcion(){
        return this.descripciones;
    }

    public double cantMetrosCuadradosDescripcion(){
        double cantTotal = 0;
        if(!this.descripciones.esVacia()){
            for (int j = 1; j <=this.descripciones.longitud() ; j++) {
                Descripcion des = (Descripcion) descripciones.recuperar(j);
                cantTotal = cantTotal + des.getCantMetrosCuadrados();
            }
        }
        return cantTotal;
    }

    public boolean existeDescripcion(Descripcion des){
        boolean exito = false;
        if (persona != null){
            exito = this.descripciones.localizar(des) > 0;
        }
        return exito;
    }

    public boolean esVacio(){
        return this.descripciones.esVacia();
    }

    public void vaciar(){
        this.descripciones.vaciar();
    }

    public boolean equals(Object obj){
        NodoCliente nodo = (NodoCliente) obj;
        return this.persona.equals(nodo.getCliente());
    }

    public String toString(){
        String cad = "Cliente: " + this.persona.toString()+"\n Lista de sus pedidos:\n";
        cad = cad + this.descripciones.toString();
        return cad;
    }
}
