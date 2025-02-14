import javax.swing.*;
import java.util.ArrayList;

public class TiendaFrutas_GUI_SWING {
    private JPanel Ventana;
    private JRadioButton manzanaRadioButton;
    private JRadioButton naranjaRadioButton;
    private JRadioButton platanoRadioButton;
    private JButton Añadirbutton;
    private JList<String> carritoList;
    private DefaultListModel<String> carritoModel;
    private JButton QuitarButton;
    private JComboBox<Integer> comboBoxEuros;
    private JSpinner spinnerCentimos;
    private JTextField pagoTextField;
    private JButton PagarButton;
    private JButton NeuvaCompraButton;
    private JLabel precioLabel;
    private JLabel totalLabel;
    private JLabel vueltoLabel;
    private ArrayList<String> carrito = new ArrayList<>();
    private double total = 0.0;

    public TiendaFrutas_GUI_SWING() {
        ButtonGroup grupoFrutas = new ButtonGroup();
        grupoFrutas.add(manzanaRadioButton);
        grupoFrutas.add(naranjaRadioButton);
        grupoFrutas.add(platanoRadioButton);

        carritoModel = new DefaultListModel<>();
        carritoList.setModel(carritoModel);
        carritoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Añadirbutton.setEnabled(false);
        QuitarButton.setEnabled(false);
        PagarButton.setEnabled(false);

        manzanaRadioButton.addActionListener(e -> actualizarPrecio(1.00));
        platanoRadioButton.addActionListener(e -> actualizarPrecio(0.90));
        naranjaRadioButton.addActionListener(e -> actualizarPrecio(1.50));

        Añadirbutton.addActionListener(e -> agregarAlCarrito());

        QuitarButton.addActionListener(e -> quitarDelCarrito());

        carritoList.addListSelectionListener(e -> QuitarButton.setEnabled(!carritoList.isSelectionEmpty()));

        comboBoxEuros.addActionListener(e -> actualizarPago());
        spinnerCentimos.addChangeListener(e -> actualizarPago());

        PagarButton.addActionListener(e -> realizarPago());

        NeuvaCompraButton.addActionListener(e -> reiniciarCompra());

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 90, 10);
        spinnerCentimos.setModel(spinnerModel);

        for (int i = 0; i <= 20; i++) {
            comboBoxEuros.addItem(i);
        }
    }

    private void actualizarPrecio(double precio) {
        precioLabel.setText(String.format("%.2f €", precio));
        Añadirbutton.setEnabled(true);
    }

    private void agregarAlCarrito() {
        String fruta = obtenerFrutaSeleccionada();
        if (fruta == null) return;

        double precio = obtenerPrecioFruta(fruta);
        carrito.add(fruta);
        carritoModel.addElement(fruta);
        total += precio;

        actualizarTotal();
        actualizarEstadoBotones();
    }

    private void quitarDelCarrito() {
        int index = carritoList.getSelectedIndex();
        if (index != -1) {
            String fruta = carritoModel.getElementAt(index);
            carrito.remove(index);
            carritoModel.remove(index);
            total -= obtenerPrecioFruta(fruta);

            actualizarTotal();
            actualizarEstadoBotones();
        }
    }

    private void actualizarTotal() {
        totalLabel.setText(String.format("%.2f €", total));
        actualizarPago();
    }

    private void actualizarEstadoBotones() {
        QuitarButton.setEnabled(!carrito.isEmpty());
        PagarButton.setEnabled(total > 0);
    }

    private String obtenerFrutaSeleccionada() {
        if (manzanaRadioButton.isSelected()) return "Manzana";
        if (platanoRadioButton.isSelected()) return "Plátano";
        if (naranjaRadioButton.isSelected()) return "Naranja";
        return null;
    }

    private double obtenerPrecioFruta(String fruta) {
        switch (fruta) {
            case "Manzana": return 1.00;
            case "Plátano": return 0.90;
            case "Naranja": return 1.50;
            default: return 0.0;
        }
    }

    private void actualizarPago() {
        int euros = (int) comboBoxEuros.getSelectedItem();
        int centimos = (int) spinnerCentimos.getValue();
        double pago = euros + (centimos / 100.0);
        pagoTextField.setText(String.format("%.2f €", pago));

        PagarButton.setEnabled(pago >= total && total > 0);
    }

    private void realizarPago() {
        int euros = (int) comboBoxEuros.getSelectedItem();
        int centimos = (int) spinnerCentimos.getValue();
        double pago = euros + (centimos / 100.0);
        double vuelto = pago - total;

        vueltoLabel.setText(String.format("%.2f €", vuelto));
        JOptionPane.showMessageDialog(Ventana, "Pago realizado con éxito!");
    }

    private void reiniciarCompra() {
        manzanaRadioButton.setSelected(false);
        platanoRadioButton.setSelected(false);
        naranjaRadioButton.setSelected(false);
        Añadirbutton.setEnabled(false);
        QuitarButton.setEnabled(false);
        PagarButton.setEnabled(false);

        carrito.clear();
        carritoModel.clear();
        total = 0.0;
        actualizarTotal();

        comboBoxEuros.setSelectedIndex(0);
        spinnerCentimos.setValue(0);
        pagoTextField.setText("0.00 €");
        vueltoLabel.setText("0.00 €");
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Compra de Frutas");
        frame.setContentPane(new TiendaFrutas_GUI_SWING().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
