import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ComputerReservationSystem {

    private static final int TOTAL_COMPUTERS = 20;
    private static final String[] DAYS_OF_WEEK = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private static final String[] HOURS_OF_DAY = {"9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
    
    private static Map<String, Integer> reservations = new HashMap<>();
    private static int availableComputers = TOTAL_COMPUTERS;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Computer Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        JLabel nameLabel = new JLabel("Professor Name:");
        JTextField nameField = new JTextField();
        panel.add(nameLabel);
        panel.add(nameField);

        JLabel pcLabel = new JLabel("Number of PCs:");
        JTextField pcField = new JTextField();
        panel.add(pcLabel);
        panel.add(pcField);

        JLabel dayLabel = new JLabel("Day of the Week:");
        JComboBox<String> dayComboBox = new JComboBox<>(DAYS_OF_WEEK);
        panel.add(dayLabel);
        panel.add(dayComboBox);

        JLabel hourLabel = new JLabel("Hour of the Day:");
        JComboBox<String> hourComboBox = new JComboBox<>(HOURS_OF_DAY);
        panel.add(hourLabel);
        panel.add(hourComboBox);

        JButton reserveButton = new JButton("Reserve");
        JLabel statusLabel = new JLabel("Available PCs: " + availableComputers);
        panel.add(reserveButton);
        panel.add(statusLabel);

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String professorName = nameField.getText();
                int pcsToReserve;

                try {
                    pcsToReserve = Integer.parseInt(pcField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number of PCs.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (pcsToReserve <= 0 || pcsToReserve > availableComputers) {
                    JOptionPane.showMessageDialog(frame, "Invalid number of PCs to reserve.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String day = (String) dayComboBox.getSelectedItem();
                String hour = (String) hourComboBox.getSelectedItem();
                String key = day + " " + hour;

                if (reservations.containsKey(key)) {
                    JOptionPane.showMessageDialog(frame, "This time slot is already reserved.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                reservations.put(key, pcsToReserve);
                availableComputers -= pcsToReserve;
                statusLabel.setText("Available PCs: " + availableComputers);
                JOptionPane.showMessageDialog(frame, "Reservation successful for Professor " + professorName + " at " + day + " " + hour + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
