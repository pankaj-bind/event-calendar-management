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

    
}
