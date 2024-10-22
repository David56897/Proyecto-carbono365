import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class carbono365 extends JFrame {
   final private JTextField nombreField;
   final private JComboBox<String> ciudadComboBox;
   final private JTextField consumoAguaField;
   final private JTextField consumoElectricidadField;
   final private JTextField consumoGasField;
   final private JTextField kmRecorridosField;
   final private JTextField reciclajeField;
   final private JTextArea resultadoArea;

    public carbono365() {
        setTitle("Monitoreo de Huella de Carbono");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelEntrada = new JPanel(new GridLayout(8, 2, 10, 10));
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelEntrada.add(new JLabel("Ingrese su nombre:"));
        nombreField = new JTextField();
        panelEntrada.add(nombreField);

        panelEntrada.add(new JLabel("Seleccione su ciudad:"));
        ciudadComboBox = new JComboBox<>(new String[]{"Medellín", "Bucaramanga", "Bogotá"});
        panelEntrada.add(ciudadComboBox);

        panelEntrada.add(new JLabel("Consumo de agua mensual (m3):"));
        consumoAguaField = new JTextField();
        panelEntrada.add(consumoAguaField);

        panelEntrada.add(new JLabel("Consumo de electricidad mensual (kWh):"));
        consumoElectricidadField = new JTextField();
        panelEntrada.add(consumoElectricidadField);

        panelEntrada.add(new JLabel("Consumo de gas natural mensual (kWh):"));
        consumoGasField = new JTextField();
        panelEntrada.add(consumoGasField);

        panelEntrada.add(new JLabel("Km recorridos mensualmente (vehículo):"));
        kmRecorridosField = new JTextField();
        panelEntrada.add(kmRecorridosField);

        panelEntrada.add(new JLabel("Cantidad de papel reciclada (kg):"));
        reciclajeField = new JTextField();
        panelEntrada.add(reciclajeField);

        JButton calcularButton = new JButton("Calcular Huella de Carbono");
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularHuellaDeCarbono();
            }
        });
        panelEntrada.add(calcularButton);

        //resultado
        resultadoArea = new JTextArea(10, 50);
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        add(panelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void calcularHuellaDeCarbono() {
        try {
            String nombre = nombreField.getText();
            String ciudad = (String) ciudadComboBox.getSelectedItem();
            float consumoAgua = Float.parseFloat(consumoAguaField.getText());
            float consumoElectricidad = Float.parseFloat(consumoElectricidadField.getText());
            float consumoGas = Float.parseFloat(consumoGasField.getText());
            float kmRecorridos = Float.parseFloat(kmRecorridosField.getText());
            float reciclaje = Float.parseFloat(reciclajeField.getText());

            // Calcular la huella de carbono
            float huellaDeCarbono = (consumoAgua * 0.002f) + (consumoElectricidad * 0.5f) +
                    (consumoGas * 0.3f) + (kmRecorridos * 0.21f) - (reciclaje * 0.01f);

            // Definir el promedio de la ciudad seleccionada
            float promedioCiudad = getPromedioCiudad(ciudad);

            // Mostrar resultados y recomendaciones
            mostrarResultados(nombre, ciudad, huellaDeCarbono, promedioCiudad);

        } catch (NumberFormatException e) {
            resultadoArea.setText("Por favor ingrese todos los valores de manera correcta.");
        }
    }

    private float getPromedioCiudad(String ciudad) {
        switch (ciudad) {
            case "Medellín":
                return 10.5f;
            case "Bucaramanga":
                return 8.2f;
            case "Bogotá":
                return 9.7f;
            default:
                return 0;
        }
    }

    private void mostrarResultados(String nombre, String ciudad, float huellaDeCarbono, float promedioCiudad) {
        StringBuilder resultado = new StringBuilder();
        resultado.append("Resultado de la huella de carbono para ").append(nombre).append(" en ").append(ciudad).append(":\n");
        resultado.append("Huella de carbono calculada: ").append(huellaDeCarbono).append(" CO2e.\n");
        resultado.append("Promedio de la ciudad: ").append(promedioCiudad).append(" CO2e.\n");

        if (huellaDeCarbono > promedioCiudad) {
            resultado.append("\nSu huella de carbono está por encima del promedio de la ciudad.\n");
            resultado.append("Recomendaciones para reducir la huella de carbono:\n");
            resultado.append("- Reduzca el consumo de agua utilizando dispositivos de bajo flujo.\n");
            resultado.append("- Utilice fuentes de energía renovables y desconecte aparatos cuando no los esté usando.\n");
            resultado.append("- Caminar, andar en bicicleta o utilizar transporte público reduce significativamente las emisiones de CO2.\n");
            resultado.append("- Identifica áreas de mejora en el consumo energético, transporte y residuos dentro de la empresa.\n");
            resultado.append("- Usa papel reciclado y minimiza el uso de papel innecesario, promoviendo procesos digitales.\n");
            resultado.append("- Utiliza sistemas de captación de agua de lluvia para riego o usos no potables, lo que reduce la necesidad de agua tratada.\n");
            resultado.append("- Si es posible, contrata a proveedores de electricidad que utilicen fuentes renovables como energía eólica, solar o hidráulica.\n");
        } else {
            resultado.append("\nSu huella de carbono está por debajo del promedio de la ciudad.\n");
            resultado.append("Recomendaciones básicas para mantener su huella de carbono baja:\n");
            resultado.append("- Continúe reciclando y usando transporte sostenible.\n");
            resultado.append("- Evita los productos de un solo uso y opta por alternativas reutilizables.\n");
            resultado.append("- Opta por productos con envases mínimos o compostables.\n");
        }

        resultadoArea.setText(resultado.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            carbono365 app = new carbono365();
            app.setVisible(true);
        });
    }
}
