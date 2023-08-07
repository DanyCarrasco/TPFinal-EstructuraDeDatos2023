package clases;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Descripcion {
    //Descripcion de la solicitud
    private int codigoUnico;
    private final String fechaSolicitud;
    private int cantMetrosCuadrados;
    private int cantBulto;
    private String domicilioRetiro;
    private String domicilioEntrega;
    private boolean envioPagado;


    public Descripcion(String domicilioRetiro) {
        fechaSolicitud = (new SimpleDateFormat("d/MM/y", Locale.ENGLISH)).format(new Date());
        envioPagado = false;
        generarCodigo();
    }

    public Descripcion(String fecha, String domicilioRetiro) {
        this.fechaSolicitud = fecha;
        this.domicilioRetiro = domicilioRetiro;
        this.domicilioEntrega = domicilioEntrega;
        envioPagado = false;
        generarCodigo();
    }


    public Descripcion(int cantMetrosCuadrados, int cantBulto, String domicilioRetiro, String domicilioEntrega) {
        fechaSolicitud = (new SimpleDateFormat("d/MM/y", Locale.ENGLISH)).format(new Date());
        this.cantMetrosCuadrados = cantMetrosCuadrados;
        this.cantBulto = cantBulto;
        this.domicilioRetiro = domicilioRetiro;
        this.domicilioEntrega = domicilioEntrega;
        this.envioPagado = false;
        generarCodigo();
    }

    public Descripcion(String fecha, int cantMetrosCuadrados, int cantBulto, String domicilioRetiro, String domicilioEntrega) {
        fechaSolicitud = fecha;
        this.cantMetrosCuadrados = cantMetrosCuadrados;
        this.cantBulto = cantBulto;
        this.domicilioRetiro = domicilioRetiro;
        this.domicilioEntrega = domicilioEntrega;
        this.envioPagado = false;
        generarCodigo();
    }

    public Descripcion(String fecha, int cantMetrosCuadrados, int cantBulto, String domicilioRetiro, String domicilioEntrega, boolean pago) {
        fechaSolicitud = fecha;
        this.cantMetrosCuadrados = cantMetrosCuadrados;
        this.cantBulto = cantBulto;
        this.domicilioRetiro = domicilioRetiro;
        this.domicilioEntrega = domicilioEntrega;
        this.envioPagado = pago;
        generarCodigo();
    }

    private void generarCodigo() {
        this.codigoUnico = this.fechaSolicitud.hashCode() + this.domicilioRetiro.hashCode();
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public int getCantMetrosCuadrados() {
        return cantMetrosCuadrados;
    }

    public void setCantMetrosCuadrados(int cantMetrosCuadrados) {
        this.cantMetrosCuadrados = cantMetrosCuadrados;
    }

    public int getCantBulto() {
        return cantBulto;
    }

    public void setCantBulto(int cantBulto) {
        this.cantBulto = cantBulto;
    }

    public String getDomicilioRetiro() {
        return domicilioRetiro;
    }

    public void setDomicilioRetiro(String domicilioRetiro) {
        this.domicilioRetiro = domicilioRetiro;
    }

    public String getDomicilioEntrega() {
        return domicilioEntrega;
    }

    public void setDomicilioEntrega(String domicilioEntrega) {
        this.domicilioEntrega = domicilioEntrega;
    }

    public boolean getEnvioPagado() {
        return envioPagado;
    }

    public void setEnvioPagado(boolean envioPagado) {
        this.envioPagado = envioPagado;
    }

    public void pagoEnvio() {
        this.envioPagado = true;
    }

    public String toString() {
        return "Fecha: " + fechaSolicitud + ", cantidad de metros cuadrados: " + cantMetrosCuadrados + ", cantidad de bultos: " + cantBulto +
                ", domicilio de retiro: " + domicilioRetiro + ", domicilio de entrega: " + domicilioEntrega + ", envio pagado: " + envioPagado;
    }

    public boolean equals(Object obj) {
        Descripcion des = (Descripcion) obj;
        return this.codigoUnico == des.codigoUnico;
    }
}
