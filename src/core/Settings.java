package core;

import fileIO.Config;
import gui.pnlHours;
import gui.pnlJobs;
import gui.pnlSettings;
import handler.JobHandler;
import handler.TrayHandler;
import misc.Parse;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Brandon194 on 5/22/2015.
 */
public class Settings {
    private final Image image;


    private WorkCalculator wc;

    private final Config CONFIGS;
    private JobHandler jobHandler;
    private TrayHandler trayHandler;
    private pnlJobs pnljobs;
    private pnlHours pnlhours;
    private pnlSettings pnlsettings;

    private boolean debug = false;

    public Settings(JobHandler jHander, TrayHandler hHandler){
        image = new ImageIcon(getClass().getResource("/image/clock.png")).getImage();
        CONFIGS = new Config("WorkCalculator");
        jobHandler = jHander;
        trayHandler = hHandler;
    }

    private void applyConfig(){
        if (CONFIGS.doesExist("debug")){
            setDebug(Parse.parseBoolean(CONFIGS.getValue("debug")));
        } else {
            setDebug(false);
        }

        jobHandler.load();
        pnlhours.addComponents();
    }

    public void setPanels(WorkCalculator w, pnlJobs j, pnlSettings s, pnlHours h){
        pnljobs = j;
        pnlsettings = s;
        pnlhours = h;
        wc = w;

        applyConfig();
    }

    public void addComponents(){
        pnljobs.addComponents();
        pnlhours.addComponents();
        pnlsettings.addComponents();
    }

    public void setDebug(boolean debug){
        try {
            this.debug = debug;
            jobHandler.setDebug(debug);
            jobHandler.load();
            CONFIGS.newConfigValue("debug", "" + debug);
            pnlsettings.setDebug(debug);
            pnlhours.setDebug(debug);
            pnljobs.setDebug(debug);

            addComponents();
        }catch (Exception e){

        }
    }
    public void toggleDebug(){
        debug = !debug;
        jobHandler.toggleDebug();
        jobHandler.load();
        CONFIGS.newConfigValue("debug", "" + debug);
        pnlsettings.toggleDebug();
        pnlhours.toggleDebug();
        pnljobs.toggleDebug();
    }

    public void close(){
        trayHandler.onClose();
        System.exit(1);
    }

    public boolean getDebug(){
        return debug;
    }
    public JobHandler getJobHandler() {
        return jobHandler;
    }
    public TrayHandler getTrayHandler() {
        return trayHandler;
    }
    public pnlJobs getPnljobs() {
        return pnljobs;
    }
    public pnlHours getPnlhours() {
        return pnlhours;
    }
    public pnlSettings getPnlsettings() {
        return pnlsettings;
    }
    public WorkCalculator setWorkCalculator() {
        return wc;
    }
    public Config getCONFIGS() {
        return CONFIGS;
    }
    public Image getImage(){
        return image;
    }
}
