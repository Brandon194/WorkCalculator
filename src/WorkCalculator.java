import gui.pnlHours;
import gui.pnlJobs;
import handler.JobHandler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by Brandon194 on 4/15/2015.
 */
public class WorkCalculator extends JFrame implements ChangeListener{

    public static final JobHandler jobHandler = new JobHandler();

    private final JTabbedPane tabbedPane = new JTabbedPane();

    pnlHours pnlhours = new pnlHours(jobHandler);
    pnlJobs pnljobs = new pnlJobs(jobHandler, this);


    public WorkCalculator() {
        this.setLocationRelativeTo(null);
        this.setTitle("Work Times Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(tabbedPane);
        this.setSize(275, 175);
        //Debug();

        tabbedPane.add("Hours", pnlhours);
        tabbedPane.add("Jobs", pnljobs);

        this.revalidate();

        tabbedPane.addChangeListener(this);
    }

    public static void main(String[] args) throws IllegalArgumentException{
        new WorkCalculator();
    }

    private void Debug(){
        System.out.println("" + jobHandler.getNumOfJobs() + " jobs loaded");
        pnlhours.debug();
    }

    public void stateChanged(ChangeEvent e) {
        pnlhours.addComponents();
        pnljobs.addComponents();
    }


}
