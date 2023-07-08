package clases;

public class Ciudad {
    private int codigoPostal;
    private String nombre;
    private String provincia;

    public Ciudad(int codigoPostal, String nombre, String provincia) {
        this.codigoPostal = codigoPostal;
        this.nombre = nombre;
        this.provincia = provincia;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
