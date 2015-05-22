package core;

import fileIO.Config;
import gui.pnlHours;
import gui.pnlJobs;
import gui.pnlSettings;
import handler.JobHandler;
import misc.Parse;

/**
 * Created by Brandon194 on 5/22/2015.
 */
public class Settings {

    private final Config CONFIGS;
    private final pnlJobs pnljobs;
    private final pnlHours pnlhours;
    private final JobHandler handler;
    private final pnlSettings pnlsettings;

    private boolean debug = false;

    public Settings(JobHandler jHander, pnlHours hours, pnlJobs jobs, pnlSettings pnlsettings){
        CONFIGS = new Config("WorkCalculator");
        pnlhours = hours;
        pnljobs = jobs;
        handler = jHander;
        this.pnlsettings = pnlsettings;

        applyConfig();
    }

    private void applyConfig(){
        if (CONFIGS.doesExist("debug")){
            setDebug(Parse.parseBoolean(CONFIGS.getValue("debug")));
        } else {
            setDebug(false);
        }

        pnlhours.addComponents();
    }

    public void setDebug(boolean debug){
        this.debug = debug;
        CONFIGS.newConfigValue("debug", "" + debug);
        pnlsettings.setDebug(debug);
        pnlhours.setDebug(debug);
        pnljobs.setDebug(debug);
        handler.setDebug(debug);
    }
    public void toggleDebug(){
        debug = !debug;
        CONFIGS.newConfigValue("debug", "" + debug);
        pnlsettings.toggleDebug();
        handler.toggleDebug();
        pnlhours.toggleDebug();
        pnljobs.toggleDebug();
    }
    public boolean getDebug(){
        return debug;
    }
}
