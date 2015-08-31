package gui;

import core.WorkCalculator;
import misc.Parse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Brandon194 on 5/24/2015.
 */
public class frmWorkToday implements Runnable, ActionListener{

    private static JFrame frame = new JFrame();
    private JButton btnApply = new JButton("Apply");

    private WorkCalculator workCalculator;

    private JPanel pnlMain;
    private JButton noButton;
    private JButton yesButton;
    private boolean today = false;
    private LocalDate localDate = LocalDate.of(1994,4,30);

    private JTextField[] txtHours;

    private final Dimension FRAME_DIM = new Dimension(130,100);

    public frmWorkToday(WorkCalculator wc) {

        workCalculator = wc;

        addComponents();
        frame.setContentPane(pnlMain);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(FRAME_DIM);
        frame.setResizable(false);

        frame.setIconImage(core.WorkCalculator.SETTINGS.getImage());
    }

    public void addComponents() {
        pnlMain = new JPanel();
        pnlMain.setLayout(new BorderLayout());
        btnApply = new JButton("Apply");

        JPanel pnlJobNames = new JPanel();
        pnlJobNames.setLayout(new GridLayout(workCalculator.SETTINGS.getJobHandler().getNumOfJobs()+1,2));


        txtHours = new JTextField[workCalculator.SETTINGS.getJobHandler().getNumOfJobs()];

        for (int i=0;i<workCalculator.SETTINGS.getJobHandler().getNumOfJobs();i++){
            pnlJobNames.add(new JLabel(workCalculator.SETTINGS.getJobHandler().getJob(i).getName()));

            txtHours[i] = new JTextField("0");
            pnlJobNames.add(txtHours[i]);
        }

        pnlJobNames.add(new JPanel());
        pnlJobNames.add(btnApply);
        btnApply.addActionListener(this);

        pnlMain.add(pnlJobNames, BorderLayout.CENTER);

        pnlMain.revalidate();
    }

    public void setVisible(boolean vis){
        frame.setVisible(vis);
    }


    public void actionPerformed(ActionEvent event){
        if (event.getSource() == btnApply){
            for(int i=0;i<workCalculator.SETTINGS.getJobHandler().getNumOfJobs();i++){
                workCalculator.SETTINGS.getJobHandler().getJob(i).addHours(Parse.parseInt(txtHours[i].getText()));
            }

            frame.setVisible(false);
        }
    }

    public void run(){
        while (true){
            if (!localDate.equals(LocalDate.now()) && LocalTime.now().isAfter(LocalTime.parse("12:00"))){
                frame.requestFocus();
                frame.setVisible(true);
                localDate = LocalDate.now();
            }
            try{
                Thread.sleep(3600000);
            }catch (InterruptedException exception){

            }
        }
    }
}
