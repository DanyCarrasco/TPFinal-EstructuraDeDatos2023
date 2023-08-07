import clases.Provincia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Prueba3 {
    //Automatice las preguntas para buscar
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        metodo();
    }

    public static void metodo() {
        Scanner sc = new Scanner(System.in);
        String csvFile = "C:\\Users\\danyc\\OneDrive\\Escritorio\\Hola.txt";
        BufferedReader br = null;
        String line = ";";
        //Se define separador ";"
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(cvsSplitBy);
                int num = datos.length;
                int i = 0;
                while (i < num) {
                    //Imprime datos.
                    System.out.println(datos[i]);
                    i++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
