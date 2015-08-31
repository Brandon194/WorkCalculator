package gui;

import core.Settings;
import core.WorkCalculator;
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

    private JLabel lblClose = new JLabel("Exit WorkCalcualtor");
    private JButton btnClose = new JButton("Close");

    private String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday","Thursday", "Friday", "Saturday", "Sunday"};
    private JLabel lblEndOfWeek = new JLabel("End of The Week: ");
    private JComboBox comboBoxEndOfWeek = new JComboBox(daysOfWeek);

    private WorkCalculator wc;

    private boolean tray = false;
    private boolean debug = false;


    public pnlSettings(WorkCalculator w){
        super(w.SETTINGS.getJobHandler());
        wc = w;
        this.pnlhours = wc.SETTINGS.getPnlhours();
        this.pnljobs = wc.SETTINGS.getPnljobs();


        this.add(pnlMain);
        addComponents();
        this.revalidate();
        pnlMain.revalidate();
    }

    public void addComponents() {


        pnlMain.removeAll();
        pnlMain.setLayout(new GridLayout(6,2));
        debugButton(debug);

        if (!tray){
            pnlMain.add(lblClose);
            pnlMain.add(btnClose);
            btnClose.addActionListener(this);
        }

        pnlMain.add(lblEndOfWeek);
        pnlMain.add(comboBoxEndOfWeek);

        comboBoxEndOfWeek.setSelectedIndex(wc.SETTINGS.getBackup()-1);
        comboBoxEndOfWeek.addActionListener(this);

        pnlMain.revalidate();
    }



    private void debugButton(boolean debug){
        if (debug) {
            btnDebug = new JButton("DEBUG");
            pnlMain.add(new JLabel("Debug Mode: " + this.debug));
            pnlMain.add(btnDebug);
            btnDebug.addActionListener(this);
        }
    }

    public void setTray(boolean t){
        tray = t;
    }

    public void actionPerformed(ActionEvent event){
        if (event.getSource() == btnDebug) {
            wc.SETTINGS.toggleDebug();
        }else if (event.getSource() == btnClose){
            wc.SETTINGS.close();
        } else if (event.getSource() == comboBoxEndOfWeek){
            wc.SETTINGS.setBackup(comboBoxEndOfWeek.getSelectedIndex()+1);
        }

        addComponents();
    }
}
