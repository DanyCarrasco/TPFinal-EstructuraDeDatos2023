// Importing required classes

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Prueba2 {
    // Main driver method
    public static void main(String args[]) throws FileNotFoundException {

        try {
            FileReader fr = new FileReader("C:\\Users\\danyc\\OneDrive\\Escritorio\\Hola.txt");
            BufferedReader br = new BufferedReader(fr);
            String texto = "", linea = "";
            while ((linea = br.readLine()) != null) {
                texto += linea;
            }
            br.close();
            // Constructor 2
            System.out.println("Using Constructor 2 - ");

            // Again creating object of class inside main()
            // method
            StringTokenizer st2 = new StringTokenizer(
                    texto, ";");

            // If tokens are present
            while (st2.hasMoreTokens())

                // Print all tokens
                System.out.println(st2.nextToken());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
/*
        // Creating object of class inside main() method
        StringTokenizer st1 = new StringTokenizer(
                "Hello Geeks How are you", " ");

        // Condition holds true till there is single token
        // remaining using hasMoreTokens() method
        while (st1.hasMoreTokens())

            // Getting next tokens
            System.out.println(st1.nextToken());

        // Constructor 2
        System.out.println("Using Constructor 2 - ");

        // Again creating object of class inside main()
        // method
        StringTokenizer st2 = new StringTokenizer(
                "JAVA : Code : String", " :");

        // If tokens are present
        while (st2.hasMoreTokens())

            // Print all tokens
            System.out.println(st2.nextToken());

        // Constructor 3
        System.out.println("Using Constructor 3 - ");

        // Again creating object of class inside main()
        // method
        StringTokenizer st3 = new StringTokenizer(
                "JAVA : Code : String", " :", true);

        while (st3.hasMoreTokens())
            System.out.println(st3.nextToken());*/
    }
}
