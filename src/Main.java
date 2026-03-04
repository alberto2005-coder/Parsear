import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;

public class Main extends JFrame {

    private JComboBox<String> parserSelector;
    private JButton openButton;
    private JTextArea outputArea;
    private JFileChooser fileChooser;

    public Main() {
        setTitle("Herramienta de Parseo de Archivos (Modular)");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        topPanel.add(new JLabel("Selecciona el tipo de parser:"));

        String[] parserTypes = {"JSON", "XML", "CSV"};
        parserSelector = new JComboBox<>(parserTypes);
        topPanel.add(parserSelector);

        openButton = new JButton("Seleccionar Archivo y Parsear");
        topPanel.add(openButton);

        add(topPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(new EmptyBorder(0, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        fileChooser = new JFileChooser();
        openButton.addActionListener(e -> onParseButtonClick());
    }

    private void onParseButtonClick() {
        String selectedParserType = (String) parserSelector.getSelectedItem();
        if (selectedParserType == null) return;

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            outputArea.setText("Parseando archivo: " + selectedFile.getName() + " como " + selectedParserType + "...\n\n");

            try {
                // 1. Leer el contenido del archivo
                String content = Files.readString(selectedFile.toPath());
                
                // 2. Llamar al método que decide qué parser usar
                String parsedData = parseData(content, selectedParserType);
                
                // 3. Mostrar el resultado
                outputArea.append(parsedData);

            } catch (Exception ex) {
                outputArea.append("--- ERROR ---\n");
                outputArea.append("No se pudo leer o parsear el archivo.\n");
                outputArea.append("Detalle: " + ex.getMessage());
            }
        }
    }

    private String parseData(String content, String type) throws Exception {
        IParser parser; 
        switch (type) {
            case "JSON":
                parser = new JsonParser();
                break;
            case "XML":
                parser = new XmlParser();
                break;
            case "CSV":
                parser = new CsvParser();
                break;
            default:
                throw new Exception("Tipo de parser no reconocido.");
        }
        
        return parser.parse(content);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}