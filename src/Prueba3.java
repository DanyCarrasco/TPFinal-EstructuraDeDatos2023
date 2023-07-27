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
        int resp;
        System.out.println("1: seguir con siguiente provincia");
        System.out.println("0: terminar");
        System.out.println("Ingrese su respuesta:");
        resp = sc.nextInt();
        System.out.println("\n");
        metodoProvincia(resp);
    }

    public static void metodoProvincia(int resp){
        Scanner sc = new Scanner(System.in);
        int n = 19;
        while (resp == 1){
            Provincia prov = new Provincia();
            String p = prov.getProvincia(n);
            String csvFile = "C:\\Users\\danyc\\OneDrive\\Documentos\\gitProjects\\TPFinal-EstructuraDeDatos2023.v1\\Ciudades de "+p+".txt";
            BufferedReader br = null;
            String line = "";
            //Se define separador ";"
            String cvsSplitBy = ",";
            try {
                System.out.println("\n");
                System.out.println("De la lista de las ciudades de "+ p +":");
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
                System.out.println("Forma una lista con las mismas ciudades y el codigo postal de cada uno");
                System.out.println("\n");
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
            System.out.println("1: seguir con siguiente provincia");
            System.out.println("0: terminar");
            System.out.println("Ingrese su respuesta:");
            resp = sc.nextInt();

            if (resp == 1){
                n++;
                if (n == prov.cantidadProvincias() - 1){
                    System.out.println("Ultima provincia:\n");
                }
            }
        }
    }
}
