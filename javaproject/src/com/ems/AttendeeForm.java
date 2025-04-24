package com.ems;

import com.ems.db.DatabaseConnector;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AttendeeForm extends JFrame {
    JTextField nameField = new JTextField(20);
    JComboBox<EventItem> eventCombo = new JComboBox<>();
    JButton saveButton = new JButton("Add Attendee");

    public AttendeeForm() {
        setTitle("Add Attendee");
        setLayout(new GridLayout(3, 2));
        add(new JLabel("Attendee Name:"));
        add(nameField);
        add(new JLabel("Select Event:"));
        add(eventCombo);
        add(saveButton);

        loadEvents();

        saveButton.addActionListener(e -> saveAttendee());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    private void loadEvents() {
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name FROM events")) {
            while (rs.next()) {
                eventCombo.addItem(new EventItem(rs.getInt("id"), rs.getString("name")));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading events: " + ex.getMessage());
        }
    }

    private void saveAttendee() {
        String name = nameField.getText();
        EventItem selectedEvent = (EventItem) eventCombo.getSelectedItem();
        if (selectedEvent == null) {
            JOptionPane.showMessageDialog(this, "No event selected!");
            return;
        }
        try (Connection conn = DatabaseConnector.getConnection()) {
            String sql = "INSERT INTO attendees (name, event_id) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, selectedEvent.id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Attendee added!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // Helper class for event combo box
    static class EventItem {
        int id;
        String name;
        EventItem(int id, String name) { this.id = id; this.name = name; }
        public String toString() { return name; }
    }
}
