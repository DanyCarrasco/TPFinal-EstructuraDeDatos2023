package clases;

public class Ciudad implements Comparable{
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

    public boolean equals(Object obj){
        Ciudad ciudad = (Ciudad) obj;
        return this.codigoPostal == ciudad.getCodigoPostal();
    }

    public Ciudad clone(){
        return (new Ciudad(this.getCodigoPostal(),this.getNombre(),this.getProvincia()));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 77 + hash * this.codigoPostal;
        return hash;
    }

    @Override
    public int compareTo(Object o) {
        int salida;
        Ciudad ciudad = (Ciudad) o;
        if (this.codigoPostal < ciudad.getCodigoPostal()){
            salida = -1;
        } else {
            if (this.codigoPostal > ciudad.getCodigoPostal()){
                salida = 1;
            } else {
                salida = 0;
            }
        }
        return salida;
    }


}
