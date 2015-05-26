package gui;

import core.Settings;
import handler.JobHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Brandon194 on 5/21/2015.
 */
public class pnlSettings extends DefaultPanel implements ActionListener {

    private final pnlHours pnlhours;
    private final pnlJobs pnljobs;

    private JPanel pnlMain = new JPanel();

    private JButton btnDebug;

    private final Settings SETTINGS;

    public pnlSettings(JobHandler handler, pnlHours pnlhours, pnlJobs pnljobs){
        super(handler);
        this.pnlhours = pnlhours;
        this.pnljobs = pnljobs;
        this.SETTINGS = new Settings(handler, pnlhours, pnljobs, this);


        this.add(pnlMain);
        addComponents();
        this.revalidate();
        pnlMain.revalidate();
    }

    public void addComponents() {


        pnlMain.removeAll();
        pnlMain.setLayout(new GridLayout(2, 2));
        debugButton(true);

        pnlMain.add(new JLabel());
    }

    private void debugButton(boolean debug){
        if (debug) {
            btnDebug = new JButton("DEBUG");
            pnlMain.add(new JLabel("Debug Mode: " + this.debug));
            pnlMain.add(btnDebug);
            btnDebug.addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent event){
        if (event.getSource() == btnDebug) {
            SETTINGS.toggleDebug();
        }

        addComponents();
    }
}
