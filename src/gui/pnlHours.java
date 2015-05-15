package gui;

import handler.JobHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Brandon194 on 4/17/2015.
 */
public class pnlHours extends JPanel implements ActionListener {
    JTextField[] txtHours;
    JobHandler handler;
    JPanel pnlMain = new JPanel();

    public boolean debug = false;

    public pnlHours(JobHandler handler){
        this.handler = handler;
        txtHours = new JTextField[handler.getNumOfJobs()];
        this.add(pnlMain);

        addComponents();
    }

    public void addComponents(){
        loadData();

        pnlMain.setLayout(new GridLayout(handler.getNumOfJobs()+3, 2));
        pnlMain.removeAll();

        pnlMain.add(new JLabel("Jobs"));
        pnlMain.add(new JLabel("Hours Worked"));
        for (int i=0;i<handler.getNumOfJobs();i++){
            pnlMain.add(new JLabel(handler.getJob(i).getName()));
            txtHours[i] = new JTextField();
            pnlMain.add(txtHours[i]);
            txtHours[i].addActionListener(this);
            txtHours[i].setText("" + handler.getJob(i).getHours());
        }
        pnlMain.add(new JLabel("Total hours"));
        pnlMain.add(new JLabel("" + addHours()));
        pnlMain.add(new JLabel("Gross Income"));
        pnlMain.add(new JLabel("$" + incomeHours()));

        pnlMain.revalidate();
    }
    private void loadData(){
        txtHours = new JTextField[handler.getNumOfJobs()];

        for (int i=0;i<handler.getNumOfJobs();i++){
            txtHours[i] = new JTextField();

            try{
                txtHours[i].setText("" + handler.getJob(i).getHours());
            }catch (Exception e){
                txtHours[i].setText("0");
                System.out.println("pnlHours (56): Failed to Parse Hours On Load | Job " + i);
            }
        }


    }
    private int addHours(){
        int total = 0;
        for(int i=0;i<handler.getNumOfJobs();i++){
            total+=handler.getJob(i).getHours();
        }
        return total;
    }
    private double incomeHours(){
        double total = 0;
        for (int i=0;i<handler.getNumOfJobs();i++){
            double temp = handler.getJob(i).getWage() * handler.getJob(i).getHours();
            total+=temp;
        }

        return total;
    }

    public void debug(){
        String intHoursString = "intHours {";
        for (int i=0;i<handler.getNumOfJobs();i++){
            intHoursString+= "" + handler.getJob(i).getHours() + ", ";
        }
        intHoursString+= "}";

        System.out.println("\npnlHours Debug {");
        System.out.println("\t" + intHoursString);

        System.out.println("}\n");

    }

    public void actionPerformed(ActionEvent event){
        for (int i=0;i<handler.getNumOfJobs();i++){
            try{
                handler.getJob(i).setHours(Integer.parseInt(txtHours[i].getText()));
            }catch(Exception exception) {
                handler.getJob(i).setHours(0);
                System.out.println("Failed to parse on write");
            }
        }
        addComponents();

    }
}
