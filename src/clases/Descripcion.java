package clases;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Descripcion {
    //Descripcion de la solicitud
    //otro comentario
    private final String fechaSolicitud;
    private Cliente persona;
    private int cantMetrosCuadrados;
    private int cantBulto;
    private String domicilioRetiro;
    private String domicilioEntrega;
    private boolean envioPagado;


    public Descripcion(){
        fechaSolicitud = (new SimpleDateFormat("d/MM/y", Locale.ENGLISH)).format(new Date());
        envioPagado = false;
    }

    public Descripcion(Cliente persona){
        fechaSolicitud = (new SimpleDateFormat("d/MM/y", Locale.ENGLISH)).format(new Date());
        this.persona = persona;
        this.envioPagado = false;
    }

    public Descripcion(Cliente persona, int cantMetrosCuadrados, int cantBulto, String domicilioRetiro, String domicilioEntrega){
        fechaSolicitud = (new SimpleDateFormat("d/MM/y", Locale.ENGLISH)).format(new Date());
        this.persona = persona;
        this.cantMetrosCuadrados = cantMetrosCuadrados;
        this.cantBulto = cantBulto;
        this.domicilioRetiro = domicilioRetiro;
        this.domicilioEntrega = domicilioEntrega;
        this.envioPagado = false;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public Cliente getPersona() {
        return persona;
    }

    public void setPersona(Cliente persona) {
        this.persona = persona;
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

    public void pagoEnvio(){
        this.envioPagado = true;
    }
}
