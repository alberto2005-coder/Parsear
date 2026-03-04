import java.io.BufferedReader;
import java.io.StringReader;

public class LectorCSV {

    public static void main(String[] args) throws Exception {
        String textoCsv = "id,nombre,email\n" +
                          "1,Carlos,carlos@email.com\n" +
                          "2,Maria,maria@email.com\n" +
                          "3,Luis,luis@email.com";
        
        System.out.println("--- Lector CSV ---");

        BufferedReader br = new BufferedReader(new StringReader(textoCsv));
        
        String linea;
        boolean esCabecera = true;

        while ((linea = br.readLine()) != null) {
            
            if (esCabecera) {
                esCabecera = false;
                continue;
            }

            String[] columnas = linea.split(",");
            
            String id = columnas[0];
            String nombre = columnas[1];
            String email = columnas[2];
            
            System.out.println("Usuario ID: " + id + ", Nombre: " + nombre + ", Email: " + email);
        }
        
        br.close();
    }
}