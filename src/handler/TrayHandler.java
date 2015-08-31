package handler;

import core.WorkCalculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Brandon194 on 5/26/2015.
 */
public class TrayHandler implements ActionListener {

    private WorkCalculator workCalculator;

    private SystemTray tray;
    private PopupMenu pMenu = new PopupMenu();
    private PopupMenu visibility = new PopupMenu("Show/Hide");
    private MenuItem showWC = new MenuItem("Show");
    private MenuItem hideWC = new MenuItem("Hide");
    private MenuItem name = new MenuItem("By Brandon194");
    private MenuItem close = new MenuItem("Exit");


    private PopupMenu schedule = new PopupMenu("Schedule");
    private MenuItem showSchedule = new MenuItem("Show Schedules");
    private MenuItem addHours = new MenuItem("Add Hours");


    private TrayIcon trayIcon;

    public TrayHandler(){
        if (SystemTray.isSupported()){
            tray = SystemTray.getSystemTray();

            visibility.add(showWC);
            visibility.add(hideWC);

            schedule.add(showSchedule);
            schedule.add(addHours);

            pMenu.add(name);
            pMenu.add(visibility);
            pMenu.add(schedule);
            pMenu.add(close);

            name.setEnabled(false);

            showSchedule.addActionListener(this);
            addHours.addActionListener(this);
            showWC.addActionListener(this);
            hideWC.addActionListener(this);
            close.addActionListener(this);

            try{
                tray.add(trayIcon);
            }catch (Exception e){}
        } else {
        }
    }

    public void setIcon(Image i){
        trayIcon = new TrayIcon(i, "WorkCalculator", pMenu);
        trayIcon.addActionListener(this);
        try {
            tray.add(trayIcon);
        }catch(Exception e){}
    }
    public void setWorkCalculator(WorkCalculator w){
        workCalculator = w;
        if (SystemTray.isSupported()){
            workCalculator.SETTINGS.getPnlsettings().setTray(true);
        } else {
            workCalculator.SETTINGS.getPnlsettings().setTray(false);
        }
        workCalculator.SETTINGS.getPnlsettings().addComponents();
    }

    public void onClose(){
        tray.remove(trayIcon);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == showWC){
            workCalculator.setVisible(true);
        } else if(e.getSource() == hideWC){
            workCalculator.setVisible(false);
        } else if(e.getSource() == close){
            workCalculator.SETTINGS.close();
        } else if(e.getSource() == trayIcon){
            workCalculator.setVisible(!workCalculator.isVisible());
        } else if(e.getSource() == addHours){
            workCalculator.SETTINGS.getFRMWorkToday().setVisible(true);
            workCalculator.SETTINGS.getFRMWorkToday().addComponents();
        } else if(e.getSource() == showSchedule){

        }
    }

}
