import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EventCalendarManagement extends JFrame {
    private List<Event> events = new ArrayList<>();
    private JTable eventTable;
    private DefaultTableModel tableModel;
    private JButton addButton, editButton, deleteButton, detailsButton, editDetailsButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventCalendarManagement().setVisible(true));
    }
    public EventCalendarManagement() {
        setTitle("Event Calendar Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        initComponents();
    }
    private void initComponents() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Number");
        tableModel.addColumn("Event Name");
        tableModel.addColumn("Event Date");
        tableModel.addColumn("Event Details");

        eventTable = new JTable(tableModel);
        eventTable.getColumnModel().getColumn(0).setPreferredWidth(50); // Set column width for numbers

        addButton = new JButton("Add Event");
        editButton = new JButton("Edit Event");
        deleteButton = new JButton("Delete Event");
        detailsButton = new JButton("Event Details");
        editDetailsButton = new JButton("Edit Details");

        setLayout(new BorderLayout());
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        controlPanel.add(detailsButton);
        controlPanel.add(editDetailsButton);

        add(new JScrollPane(eventTable), BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Event newEvent = createNewEvent();
                if (newEvent != null) {
                    events.add(newEvent);
                    Collections.sort(events);
                    updateEventTable();
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Event selectedEvent = getSelectedEvent();
                if (selectedEvent != null) {
                    editEvent(selectedEvent);
                    Collections.sort(events);
                    updateEventTable();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Event selectedEvent = getSelectedEvent();
                if (selectedEvent != null) {
                    events.remove(selectedEvent);
                    updateEventTable();
                }
            }
        });
        detailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Event selectedEvent = getSelectedEvent();
                if (selectedEvent != null) {
                    showEventDetails(selectedEvent);
                }
            }
        });
        editDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Event selectedEvent = getSelectedEvent();
                if (selectedEvent != null) {
                    editEventDetails(selectedEvent);
                }
            }
        });
    }
    private Event createNewEvent() {
        String name = JOptionPane.showInputDialog(this, "Enter event name:");
        if (name != null && !name.trim().isEmpty()) {
            String dateStr = JOptionPane.showInputDialog(this, "Enter event date (dd-MM-yyyy):");
            if (isValidDate(dateStr)) {
                String details = JOptionPane.showInputDialog(this, "Enter event details (optional):");

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date date = dateFormat.parse(dateStr);
                    return new Event(name, date, details);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "An error occurred while parsing the date. Event not added.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid date format. Event not added.");
            }
        }
        return null;
    }
}
