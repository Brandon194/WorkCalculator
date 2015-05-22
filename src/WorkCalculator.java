import fileIO.FileReadWrite;
import gui.pnlHours;
import gui.pnlJobs;
import gui.pnlSettings;
import handler.JobHandler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by Brandon194 on 4/15/2015.
 */
public class WorkCalculator extends JFrame implements ChangeListener{

    public final JobHandler jobHandler = new JobHandler();
    private final JTabbedPane tabbedPane = new JTabbedPane();
    public final static double VERSION_ID = 2.0;

    private final pnlHours pnlhours;
    private final pnlJobs pnljobs;
    private final pnlSettings pnlsettings;

    private static final String[] INFO = {
            "Author: Brandon194",
            "Version: B2.2"
    };

    private static final String[] CONFIG = {
            "debug=true"
    };

    public WorkCalculator(boolean debug) {
        pnlhours = new pnlHours(jobHandler);
        pnljobs = new pnlJobs(jobHandler, this);
        pnlsettings = new pnlSettings(jobHandler, pnlhours, pnljobs);

        this.setLocationRelativeTo(null);
        this.setTitle("Work Times Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(tabbedPane);
        this.setSize(275, 175);
        //Debug();

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
        readConfigs();
        new WorkCalculator(false);
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
    public static void readConfigs(){
        FileReadWrite cfg = new FileReadWrite("WorkCalculator", "WorkCalculator", ".cfg");
    }
}
