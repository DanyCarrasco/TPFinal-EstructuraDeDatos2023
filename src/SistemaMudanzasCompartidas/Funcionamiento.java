package SistemaMudanzasCompartidas;

import clases.Provincia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Funcionamiento {
    private Provincia listaProvincias;

    public Funcionamiento(){
        this.listaProvincias = new Provincia();
    }

    public String getProvinciaArgentina(int pos){
        return listaProvincias.getProvincia(pos);
    }

    public boolean esCodigoPostal(int num){
        String numCadena = Integer.toString(num);
        return numCadena.length() == 4;
    }
}
