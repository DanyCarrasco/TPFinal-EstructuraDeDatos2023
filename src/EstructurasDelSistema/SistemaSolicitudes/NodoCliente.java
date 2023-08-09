package EstructurasDelSistema.SistemaSolicitudes;

import Estructuras.lineales.dinamicas.Lista;
import clases.Cliente;
import clases.Descripcion;
import clases.Documento;

public class NodoCliente {
    private Cliente persona;
    private Lista descripciones;

    public NodoCliente(Cliente persona, Descripcion des) {
        this.persona = persona;
        descripciones = new Lista();
        descripciones.insertar(des, descripciones.longitud() + 1);
    }

    public Cliente getCliente() {
        return this.persona;
    }

    public boolean agregarDescripcion(Descripcion des) {
        boolean exito = false;
        if (descripciones.localizar(des) < 1 && this.persona != null) {
            exito = descripciones.insertar(des, descripciones.longitud() + 1);
        }
        return exito;
    }

    public boolean eliminarDescripcion(String fecha, String domRetiro) {
        boolean exito = false;
        if (this.persona != null && !descripciones.esVacia()) {
            exito = this.descripciones.eliminar(this.descripciones.localizar(new Descripcion(fecha,domRetiro)));
        }
        return exito;
    }

    public String mostrarDescripcion(String fecha, String domRetiro) {
        String salida = "";
        if (persona != null) {
            int pos = descripciones.localizar(new Descripcion(fecha, domRetiro));
            if (0 < pos){
                Descripcion des = (Descripcion) this.descripciones.recuperar(pos);
                salida = des.toString();
            }
        }
        return salida;
    }

    public boolean existeDescripcion(String fecha, String domRetiro) {
        boolean salida = false;
        if (persona != null) {
            salida = 0 < descripciones.localizar(new Descripcion(fecha, domRetiro));
        }
        return salida;
    }

    public int getCantMetrosDescripcion(String fecha, String domRetiro) {
        int salida = 0;
        if (persona != null) {
            if (0 < descripciones.localizar(new Descripcion(fecha, domRetiro))) {
                Descripcion des = (Descripcion) this.descripciones.recuperar(descripciones.localizar(new Descripcion(fecha, domRetiro)));
                salida = des.getCantMetrosCuadrados();
            }
        }
        return salida;
    }

    public boolean setCantMetrosDescripcion(String fecha, String domRetiro, int cantMetros) {
        boolean exito = false;
        if (persona != null) {
            int pos = descripciones.localizar(new Descripcion(fecha, domRetiro));
            if (0 < pos) {
                Descripcion des = (Descripcion) this.descripciones.recuperar(pos);
                this.descripciones.eliminar(pos);
                des.setCantMetrosCuadrados(cantMetros);
                exito = this.descripciones.insertar(des, this.descripciones.longitud() + 1);
            }
        }
        return exito;
    }

    public int getCantBultosDescripcion(String fecha, String domRetiro) {
        int salida = 0;
        if (persona != null) {
            if (0 < descripciones.localizar(new Descripcion(fecha, domRetiro))) {
                Descripcion des = (Descripcion) this.descripciones.recuperar(descripciones.localizar(new Descripcion(fecha, domRetiro)));
                salida = des.getCantBulto();
            }
        }
        return salida;
    }

    public boolean setCantBultosDescripcion(String fecha, String domRetiro, int cantBultos) {
        boolean exito = false;
        if (persona != null) {
            int pos = descripciones.localizar(new Descripcion(fecha, domRetiro));
            if (0 < pos) {
                Descripcion des = (Descripcion) this.descripciones.recuperar(pos);
                this.descripciones.eliminar(pos);
                des.setCantBulto(cantBultos);
                exito = this.descripciones.insertar(des, this.descripciones.longitud() + 1);
            }
        }
        return exito;
    }

    public String getDomicilioEntregaDescripcion(String fecha, String domRetiro) {
        String domicilio = "";
        if (persona != null) {
            int pos = descripciones.localizar(new Descripcion(fecha, domRetiro));
            if (0 < pos) {
                Descripcion des = (Descripcion) this.descripciones.recuperar(pos);
                domicilio = des.getDomicilioEntrega();
            }
        }
        return domicilio;
    }

    public boolean setDomicilioEntregaDescripcion(String fecha, String domRetiro, String domicilio) {
        boolean exito = false;
        if (persona != null) {
            int pos = descripciones.localizar(new Descripcion(fecha, domRetiro));
            if (0 < pos) {
                Descripcion des = (Descripcion) this.descripciones.recuperar(pos);
                this.descripciones.eliminar(pos);
                des.setDomicilioEntrega(domicilio);
                exito = this.descripciones.insertar(des, this.descripciones.longitud() + 1);
            }
        }
        return exito;
    }

    public boolean getPagoEnvioDescripcion(String fecha, String domRetiro) {
        boolean salida=false;
        if (persona != null) {
            int pos = descripciones.localizar(new Descripcion(fecha, domRetiro));
            if (0 < pos) {
                Descripcion des = (Descripcion) this.descripciones.recuperar(pos);
                salida = des.getEnvioPagado();
            }
        }
        return salida;
    }

    public boolean setPagoEnvioDescripcion(String fecha, String domRetiro, boolean pagado) {
        boolean exito = false;
        if (persona != null) {
            int pos = descripciones.localizar(new Descripcion(fecha, domRetiro));
            if (0 < pos) {
                Descripcion des = (Descripcion) this.descripciones.recuperar(pos);
                this.descripciones.eliminar(pos);
                des.setEnvioPagado(pagado);
                exito = this.descripciones.insertar(des, this.descripciones.longitud() + 1);
            }
        }
        return exito;
    }

    public Lista getListaDescripcion() {
        return this.descripciones;
    }

    public double cantMetrosCuadradosDescripcion() {
        double cantTotal = 0;
        if (!this.descripciones.esVacia()) {
            for (int j = 1; j <= this.descripciones.longitud(); j++) {
                Descripcion des = (Descripcion) descripciones.recuperar(j);
                cantTotal = cantTotal + des.getCantMetrosCuadrados();
            }
        }
        return cantTotal;
    }

    public boolean esVacio() {
        return this.descripciones.esVacia();
    }

    public void vaciar() {
        this.descripciones.vaciar();
    }

    public boolean equals(Object obj) {
        NodoCliente nodo = (NodoCliente) obj;
        return this.persona.equals(nodo.getCliente());
    }

    public String toString() {
        String cad = "Cliente: " + this.persona.toString() + "\n Lista de sus pedidos:\n";
        cad = cad + this.descripciones.toString();
        return cad;
    }
}
