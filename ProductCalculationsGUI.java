package Product;
import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class ProductCalculationsGUI extends JFrame {
    private final JTextField txtName = new JTextField(20);
    private final JTextField txtPrice = new JTextField(20);
    private final JComboBox<String> cmbProduct = new JComboBox<>(
            new String[]{"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"});

    public ProductCalculationsGUI() {
        super("Product App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");

        JMenuItem miProcess = new JMenuItem("Process");
        JMenuItem miClear = new JMenuItem("Clear");
        JMenuItem miExit = new JMenuItem("Exit");
        file.add(miExit);
        edit.add(miProcess);
        edit.add(miClear);
        bar.add(file);
        bar.add(edit);
        setJMenuBar(bar);

        JPanel form = new JPanel(new GridLayout(3, 2, 8, 12));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));
        form.add(new JLabel("Customer Name:"));
        form.add(txtName);
        form.add(new JLabel("Select Product:"));
        form.add(cmbProduct);
        form.add(new JLabel("Enter Price:"));
        form.add(txtPrice);
        JButton btnProcess = new JButton("PROCESS");
        JPanel south = new JPanel();
        south.add(btnProcess);
        add(form, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
        btnProcess.addActionListener(e -> process());
        miProcess.addActionListener(e -> process());   // same logic
        miClear.addActionListener(e -> clear());
        miExit.addActionListener(e -> System.exit(0));
        pack();
        setLocationRelativeTo(null);
    }

    private void process() {
        String name = txtName.getText().trim();
        String product = (String) cmbProduct.getSelectedItem();
        double price;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric price.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (name.isEmpty() || price < 0) {
            JOptionPane.showMessageDialog(this, "Please enter a customer name and a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        IProductCalculations calc = new ProductCalculations(price);
        double vat = calc.GetVat(), commission = calc.GetCommission(), total = calc.GetTotal();
        String msg = "Customer Name: " + name + "Product: " + product
                + "VAT: R " + vat + "COMMISSION: R " + commission + "\nTOTAL: R " + total;
        JOptionPane.showMessageDialog(this, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
        try (PrintWriter pw = new PrintWriter(new FileWriter("data.txt", true))) {
            pw.println("DATA LOG");
            pw.println("*".repeat(40));
            pw.println("Customer Name: " + name);
            pw.println("Product: " + product);
            pw.println("VAT: R " + vat);
            pw.println("COMMISSION: R " + commission);
            pw.println("TOTAL: R " + total);
            pw.println("*".repeat(40));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not write to data.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clear() {
        txtName.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
    }
}
