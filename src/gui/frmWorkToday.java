package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Brandon194 on 5/24/2015.
 */
public class frmWorkToday implements Runnable{

    private static JFrame frame = new JFrame("Did you work today");
    private JPanel panel1;
    private JButton noButton;
    private JButton yesButton;
    private boolean today = false;
    private LocalDate localDate = LocalDate.of(1994,4,30);

    private final Dimension FRAME_DIM = new Dimension(150,100);

    public frmWorkToday() {
        addComponents();
        frame.setContentPane(panel1);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(FRAME_DIM);
        frame.setPreferredSize(FRAME_DIM);
        frame.setMaximumSize(FRAME_DIM);
        frame.setMinimumSize(FRAME_DIM);
    }

    public void addComponents(){
        panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel2.setLayout(new BorderLayout());
        yesButton = new JButton("Yes");
        noButton = new JButton("No");

        panel2.add(yesButton, BorderLayout.WEST);
        panel2.add(noButton, BorderLayout.EAST);

        panel1.add(new JLabel("Did you work today?"), BorderLayout.CENTER);
        panel1.add(panel2, BorderLayout.SOUTH);

        yesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new core.WorkCalculator();
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
            if (localDate != LocalDate.now() && LocalTime.now().isAfter(LocalTime.parse("12:00"))){
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
