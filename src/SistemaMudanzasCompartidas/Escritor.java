package SistemaMudanzasCompartidas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Escritor {
    private static String cvcFile;

    public Escritor(String rutaArchivo) throws IOException {
        cvcFile = rutaArchivo;
    }

    public static void registrarInfo(Object aviso) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(cvcFile, true);
            pw = new PrintWriter(fichero);
            pw.println("Info: \n"+aviso+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }


    public static void registrarError(Object aviso) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(cvcFile, true);
            pw = new PrintWriter(fichero);
            pw.println("Error: \n"+aviso+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    public static void vaciarTxt(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(cvcFile, false);
            pw = new PrintWriter(fichero);
            pw.println("");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

}
