import org.json.*;

public class JsonParser implements IParser {

    @Override
    public String parse(String content) throws Exception {
        StringBuilder sb = new StringBuilder();
        JSONTokener tokener = new JSONTokener(content);
        Object value = tokener.nextValue(); 

        if (value instanceof JSONObject) {
            JSONObject obj = (JSONObject) value;
            sb.append("Detectado Objeto JSON Principal:\n");
            for (String key : obj.keySet()) {
                sb.append(String.format("  - Clave: %-15s | Valor: %s\n", key, obj.get(key).toString()));
            }
        } else if (value instanceof JSONArray) {
            JSONArray arr = (JSONArray) value;
            sb.append("Detectado Array JSON con " + arr.length() + " elementos:\n\n");
            for (int i = 0; i < arr.length(); i++) {
                sb.append("--- Elemento " + i + " ---\n");
                if (arr.get(i) instanceof JSONObject) {
                    JSONObject objInArray = arr.getJSONObject(i);
                     for (String key : objInArray.keySet()) {
                        sb.append(String.format("  - Clave: %-15s | Valor: %s\n", key, objInArray.get(key).toString()));
                    }
                } else {
                     sb.append("  - Valor: " + arr.get(i).toString() + "\n");
                }
            }
        } else {
            sb.append("JSON con formato no reconocido (ni objeto ni array principal).");
        }
        return sb.toString();
    }
}