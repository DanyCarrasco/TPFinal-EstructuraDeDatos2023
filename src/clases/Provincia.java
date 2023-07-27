package clases;

public class Provincia {
    private String[] provincias;

    public Provincia() {
        provincias = new String[24];
        provincias[1] = "Buenos Aires";
        provincias[2] = "Catamarca";
        provincias[3] = "Chaco";
        provincias[4] = "Chubut";
        provincias[5] = "Cordoba";
        provincias[6] = "Corrientes";
        provincias[7] = "Entre Rios";
        provincias[8] = "Formosa";
        provincias[9] = "Jujuy";
        provincias[10] = "La Pampa";
        provincias[11] = "La Rioja";
        provincias[12] = "Mendoza";
        provincias[13] = "Misiones";
        provincias[14] = "Neuquen";
        provincias[15] = "Rio Negro";
        provincias[16] = "Salta";
        provincias[17] = "San Juan";
        provincias[18] = "San Luis";
        provincias[19] = "Santa Cruz";
        provincias[20] = "Santa Fe";
        provincias[21] = "Santiago del Estero";
        provincias[22] = "Tierra del Fuego";
        provincias[23] = "Tucuman";
    }

    public int cantidadProvincias(){
        return this.provincias.length;
    }

    public String getProvincia(int num){
        String cad = "Numero equivocado";
        if (0 < num && num < this.provincias.length) {
            cad = this.provincias[num];
        }
        return cad;
    }
}
