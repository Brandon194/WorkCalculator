package gui;

import core.WorkCalculator;
import handler.JobHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Brandon194 on 4/17/2015.
 */
public class pnlJobs extends DefaultPanel implements ActionListener {
    JButton[] btnDel;
    JButton btnNew = new JButton("New");

    JPanel pnlMain = new JPanel();

    frmNewJob frm;
    boolean frameOpen = false;

    private WorkCalculator wc;

    public pnlJobs(JobHandler handler, WorkCalculator wc){
        super(handler);

        this.wc = wc;
        addComponents();

        this.add(pnlMain);
    }

    @Override
    public void addComponents(){
        pnlMain.removeAll();


        pnlMain.setLayout(new GridLayout(handler.getNumOfJobs()+1,3));
        btnDel = new JButton[handler.getNumOfJobs()];

        for (int i=0;i<handler.getNumOfJobs();i++){
            try {
                pnlMain.add(new JLabel(handler.getJob(i).getName()));
                pnlMain.add(new JLabel("" + handler.getJob(i).getWage()));
            } catch (Exception e){
                System.out.println("Index " + i);
            }
            btnDel[i] = new JButton("Delete");
            pnlMain.add(btnDel[i]);
            btnDel[i].addActionListener(this);
        }
        pnlMain.add(new JPanel());
        pnlMain.add(new JPanel());
        btnNew.addActionListener(this);
        pnlMain.add(btnNew);
    }
    public boolean isFrameOpen(){
        return frameOpen;
    }
    public void frameClose(){
        frameOpen = false;
        addComponents();
    }

    public void actionPerformed(ActionEvent event){
        for (int i=0;i<handler.getNumOfJobs();i++){
            if (event.getSource() == btnDel[i]){
                handler.deleteJob(i);
                wc.SETTINGS.addComponents();
            }
        }

        if (event.getSource() == btnNew && !frameOpen){
            frm = new frmNewJob(wc, this);
            frameOpen = true;
        }

    }
}
