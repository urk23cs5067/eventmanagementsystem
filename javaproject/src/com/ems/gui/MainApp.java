package com.ems.gui;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainMenu = new JFrame("Event Management System");
            JButton eventBtn = new JButton("Add Event");
            JButton attendeeBtn = new JButton("Add Attendee");

            eventBtn.addActionListener(e -> new EventForm().setVisible(true));
            attendeeBtn.addActionListener(e -> new AttendeeForm().setVisible(true));

            JPanel panel = new JPanel();
            panel.add(eventBtn);
            panel.add(attendeeBtn);

            mainMenu.setContentPane(panel);
            mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainMenu.pack();
            mainMenu.setVisible(true);
        });
    }
}

