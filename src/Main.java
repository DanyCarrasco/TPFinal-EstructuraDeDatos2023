import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        SimpleDateFormat c = new SimpleDateFormat("d/MM/y",Locale.ENGLISH);
        System.out.println("Hello world!");
        System.out.println(c.format(new Date()));
    }

    public static String obtenerFecha() {
        DateFormat formato = new SimpleDateFormat(
                "d/MM/yyyy", Locale.ENGLISH);
        return formato.format(new Date());
    }
}