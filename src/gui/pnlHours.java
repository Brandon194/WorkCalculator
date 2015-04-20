package gui;

import core.Job;
import fileIO.FileReadWrite;
import handler.JobHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Brandon194 on 4/17/2015.
 */
public class pnlHours extends JPanel implements ActionListener {
    int[] intHours;
    JTextField[] txtHours;
    JobHandler handler;
    FileReadWrite frw = new FileReadWrite("WorkCalculator", "Hours");
    JPanel pnlMain = new JPanel();

    public pnlHours(JobHandler handler){
        txtHours = new JTextField[handler.getNumOfJobs()];
        intHours = new int[handler.getNumOfJobs()];
        this.handler = handler;
        pnlMain.setLayout(new GridLayout(handler.getNumOfJobs()+3, 2));
        this.add(pnlMain);

        addComponents();
    }

    public void addComponents(){
        loadData();
        pnlMain.removeAll();

        pnlMain.add(new JLabel("Jobs"));
        pnlMain.add(new JLabel("Hours Worked"));
        for (int i=0;i<handler.getNumOfJobs();i++){
            pnlMain.add(new JLabel(handler.getAllJobs()[i].getName()));
            txtHours[i] = new JTextField();
            pnlMain.add(txtHours[i]);
            txtHours[i].addActionListener(this);
            txtHours[i].setText("" + intHours[i]);
        }
        pnlMain.add(new JLabel("Total hours"));
        pnlMain.add(new JLabel("" + addHours()));
        pnlMain.add(new JLabel("Gross Income"));
        pnlMain.add(new JLabel("$" + incomeHours()));

        pnlMain.revalidate();
    }
    private void loadData(){
        String[] toParse = frw.reader();
        for (int i=0;i<handler.getNumOfJobs();i++){
            try{
                intHours[i] = Integer.parseInt(toParse[i]);
            }catch (Exception e){
                System.out.println("Failed to Parse Hours On Load");
            }
        }
    }
    private int addHours(){
        int total = 0;
        for(int i=0;i<handler.getNumOfJobs();i++){
            total+=intHours[i];
        }
        return total;
    }
    private double incomeHours(){
        double total = 0;
        for (int i=0;i<handler.getNumOfJobs();i++){
            double temp = handler.getJob(i).getWage() * intHours[i];
            total+=temp;
        }

        return total;
    }

    public void debug(){
        String intHoursString = "intHours {";
        for (int i=0;i<handler.getNumOfJobs();i++){
            intHoursString+= "" + intHours[i] + ", ";
        }
        intHoursString+= "}";

        System.out.println("\npnlHours Debug {");
        System.out.println("\t" + intHoursString);

        System.out.println("}\n");

    }

    public void actionPerformed(ActionEvent event){
        String[] writer = new String[handler.getNumOfJobs()];
        for (int i=0;i<handler.getNumOfJobs();i++){
            if (event.getSource() == txtHours[i]){
                try{
                    intHours[i] = Integer.parseInt(txtHours[i].getText());
                }catch(Exception exception) {
                    intHours[i] = 0;
                }
            }
            writer[i] = "" + txtHours[i].getText();
        }
        addComponents();
        frw.writer(writer);

    }
}
