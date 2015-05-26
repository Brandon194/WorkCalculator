package core;

import fileIO.FileReadWrite;
import gui.frmWorkToday;
import gui.pnlHours;
import gui.pnlJobs;
import gui.pnlSettings;
import handler.JobHandler;
import handler.TrayHandler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by Brandon194 on 4/15/2015.
 */
public class WorkCalculator extends JFrame implements ChangeListener{
    public static Image image;

    public final JobHandler jobHandler = new JobHandler();
    private final TrayHandler trayHandler;

    private final JTabbedPane tabbedPane = new JTabbedPane();
    public final static double VERSION_ID = 2.5;

    private final pnlHours pnlhours;
    private final pnlJobs pnljobs;
    private final pnlSettings pnlsettings;
    private static final String[] INFO = {
            "Author: Brandon194",
            "Version: Beta " + VERSION_ID
    };

    public WorkCalculator() {
        image = new ImageIcon(getClass().getResource("/image/clock.png")).getImage();
        trayHandler = new TrayHandler(this);
        pnlhours = new pnlHours(jobHandler);
        pnljobs = new pnlJobs(jobHandler, this);

        pnlsettings = new pnlSettings(jobHandler, pnlhours, pnljobs);

        this.setLocationRelativeTo(null);
        this.setTitle("Work Times Calculator");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(false);
        this.add(tabbedPane);
        this.setSize(275, 300);
        this.setMinimumSize(new Dimension(275, 180));
        this.setIconImage(core.WorkCalculator.image);

        tabbedPane.add("Hours", pnlhours);
        tabbedPane.add("Jobs", pnljobs);
        tabbedPane.add("Settings", pnlsettings);

        this.revalidate();

        tabbedPane.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        pnlhours.addComponents();
        pnljobs.addComponents();
    }
    public static void main(String[] args){

        checkVersion();
        new Thread(new frmWorkToday()).start();
    }
    public static void checkVersion(){
        FileReadWrite frw = new FileReadWrite("WorkCalculator", "WorkCalculator");
        String[] versionInfo = null;
        boolean changes = false;

        versionInfo = frw.reader();
        try{
            if (!versionInfo[1].equals(INFO[1])){
                frw.writer(INFO);
                changes = true;
            } else {
            }
        } catch(Exception e){
            frw.writer(INFO);
            changes = true;
        }

        if (changes) {

            frw = new FileReadWrite("WorkCalculator", "JobHandler");
            String[] jobs = frw.reader();

            for (String s : jobs) {
                try {
                    frw = new FileReadWrite("WorkCalculator", s);
                    frw.delete();
                } catch (Exception e) {
                }
            }
        }
    }
}
