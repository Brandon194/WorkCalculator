package gui;

import handler.JobHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Brandon194 on 4/18/2015.
 */
public class frmNewJob extends JFrame implements ActionListener {

    JobHandler handler;
    pnlJobs pnljob;

    JPanel pnlMain = new JPanel();
    JPanel pnlJob = new JPanel();
    JPanel pnlWage = new JPanel();
    JPanel pnlApply = new JPanel();

    JTextField txtJob = new JTextField();
    JTextField txtWage = new JTextField();

    JButton btnApply = new JButton("Apply");

    Dimension d = new Dimension(310,120);


    public frmNewJob(JobHandler handler, pnlJobs pnljob){
        this.setIconImage(new ImageIcon(getClass().getResource("/image/clock.png")).getImage());

        this.handler = handler;
        this.pnljob = pnljob;

        this.add(pnlMain);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(d);
        this.setResizable(false);
        this.setTitle("New: (Name/Wage)");
        this.setResizable(false);
        pnlMain.setLayout(new BorderLayout());

        pnlJob.add(txtJob);
        pnlWage.add(txtWage);
        pnlApply.add(btnApply);

        txtJob.setPreferredSize(new Dimension(215, 30));
        txtWage.setPreferredSize(new Dimension(60,30));
        btnApply.setPreferredSize(new Dimension(75,30));

        pnlMain.add(pnlJob, BorderLayout.WEST);
        pnlMain.add(pnlWage, BorderLayout.EAST);
        pnlMain.add(pnlApply, BorderLayout.SOUTH);

        btnApply.addActionListener(this);

        this.revalidate();
    }

    public void actionPerformed(ActionEvent event){
        double wage;
        try {
            wage = Double.parseDouble(txtWage.getText());
        }catch (Exception e){
            wage = 10.50;
        }

        handler.addNewJob(txtJob.getText(),wage);
        pnljob.addComponents();

        if (event.getSource() == btnApply) {
            pnljob.frameClose();
            this.dispose();
        }
    }
}
