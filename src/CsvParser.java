
public class CsvParser implements IParser {

    @Override
    public String parse(String content) throws Exception {
        StringBuilder sb = new StringBuilder();
        String[] lineas = content.split("\n"); 
        
        if (lineas.length == 0) {
            return "El archivo CSV está vacío.";
        }
        
        String[] cabeceras = lineas[0].trim().split(",");
        sb.append("Detectado CSV con " + cabeceras.length + " columnas.\n");
        sb.append("Cabeceras: " + String.join(" | ", cabeceras) + "\n");
        sb.append("-------------------------------------------------\n");
        
        for (int i = 1; i < lineas.length; i++) {
            if (lineas[i].trim().isEmpty()) continue; 
            
            String[] columnas = lineas[i].trim().split(",");
            sb.append("Fila " + i + ":\n");
            
            for (int j = 0; j < columnas.length; j++) {
                String header = (j < cabeceras.length) ? cabeceras[j] : "Columna " + j;
                sb.append(String.format("  - %-15s: %s\n", header.trim(), columnas[j].trim()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}