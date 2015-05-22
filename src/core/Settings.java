package core;

import fileIO.Config;
import gui.pnlHours;
import gui.pnlJobs;
import gui.pnlSettings;
import handler.JobHandler;

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
    }

    public void setDebug(boolean debug){
        this.debug = debug;
    }
    public void toggleDebug(){
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
