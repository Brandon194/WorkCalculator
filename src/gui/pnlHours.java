package gui;

import handler.JobHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Brandon194 on 4/17/2015.
 */
public class pnlHours extends DefaultPanel implements ActionListener {
    JTextField[] txtHours;


    public pnlHours(JobHandler handler){
        super(handler);
        txtHours = new JTextField[handler.getNumOfJobs()];
        this.add(pnlMain);

        addComponents();
    }

    @Override
    public void addComponents(){
        loadData();

        pnlMain.setLayout(new GridLayout(handler.getNumOfJobs()+3, 2));
        pnlMain.removeAll();

        pnlMain.add(new JLabel("Jobs"));
        pnlMain.add(new JLabel("Hours Worked"));
        for (int i=0;i<handler.getNumOfJobs();i++){
            try {
                pnlMain.add(new JLabel(handler.getJob(i).getName()));
            }catch(Exception e){
                pnlMain.add(new JLabel("failed to load name"));
            }
            txtHours[i] = new JTextField();
            pnlMain.add(txtHours[i]);
            txtHours[i].addActionListener(this);
            try {
                txtHours[i].setText("" + handler.getJob(i).getHours());
            } catch (Exception e){
                txtHours[i].setText("0");
            }
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
            try {
                total += handler.getJob(i).getHours();
            }catch (NullPointerException exception){
                System.out.println("pnlHours (77): No job at " + i + " to get hours");
            }
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
