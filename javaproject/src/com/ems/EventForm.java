package com.ems;

import com.ems.db.DatabaseConnector;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EventForm extends JFrame {
    JTextField nameField = new JTextField(20);
    JTextField dateField = new JTextField(10); // Format: YYYY-MM-DD
    JButton saveButton = new JButton("Add Event");

    public EventForm() {
        setTitle("Add Event");
        setLayout(new GridLayout(3, 2));
        add(new JLabel("Event Name:"));
        add(nameField);
        add(new JLabel("Event Date (YYYY-MM-DD):"));
        add(dateField);
        add(saveButton);

        saveButton.addActionListener(e -> saveEvent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    private void saveEvent() {
        String name = nameField.getText();
        String date = dateField.getText();
        try (Connection conn = DatabaseConnector.getConnection()) {
            String sql = "INSERT INTO events (name, date) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, date);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Event added!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
