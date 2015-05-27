package gui;

import core.WorkCalculator;

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
public class frmWorkToday implements Runnable{

    private static JFrame frame = new JFrame();

    private WorkCalculator workCalculator = new WorkCalculator();

    private JPanel panel1;
    private JButton noButton;
    private JButton yesButton;
    private boolean today = false;
    private LocalDate localDate = LocalDate.of(1994,4,30);

    private final Dimension FRAME_DIM = new Dimension(130,100);

    public frmWorkToday() {
        addComponents();
        frame.setContentPane(panel1);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(false);
        frame.setSize(FRAME_DIM);
        frame.setResizable(false);

        frame.setIconImage(core.WorkCalculator.SETTINGS.getImage());
    }

    public void addComponents() {
        panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.setLayout(new BorderLayout());
        yesButton = new JButton("Yes");
        noButton = new JButton("No");
        JLabel label = new JLabel("Did you work today");

        panel2.add(yesButton);
        panel2.add(noButton);
        label.setHorizontalAlignment(JLabel.CENTER);

        panel1.add(label, BorderLayout.CENTER);
        panel1.add(panel2, BorderLayout.SOUTH);

        yesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                workCalculator.setVisible(true);
                workCalculator.requestFocus();
                frame.setVisible(false);
            }
        });
        noButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
            }
        });

        panel1.revalidate();
    }

    public void run(){
        while (true){
            if (!localDate.equals(LocalDate.now()) && LocalTime.now().isAfter(LocalTime.parse("12:00"))){
                frame.requestFocus();
                frame.setVisible(true);
                localDate = LocalDate.now();
            } else {
                frame.setVisible(false);
            }
            try{
                Thread.sleep(3600000);
            }catch (InterruptedException exception){

            }
        }
    }
}
