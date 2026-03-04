import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;


public class XmlParser implements IParser {
    
    @Override
    public String parse(String content) throws Exception {
        StringBuilder sb = new StringBuilder();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = new ByteArrayInputStream(content.getBytes());
        Document doc = builder.parse(is);
        doc.getDocumentElement().normalize();
        
        sb.append("Elemento Raíz: <" + doc.getDocumentElement().getNodeName() + ">\n\n");
        
        NodeList nodes = doc.getDocumentElement().getChildNodes();
        sb.append("Nodos Hijos Directos:\n");
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                sb.append(String.format("  - <%s>: %s\n", node.getNodeName(), node.getTextContent().trim()));
            }
        }
        return sb.toString();
    }
}