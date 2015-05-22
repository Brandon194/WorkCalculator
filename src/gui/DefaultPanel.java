package gui;

import handler.JobHandler;

import javax.swing.*;

/**
 * Created by Brandon194 on 5/21/2015.
 */
public class DefaultPanel extends JPanel{

    protected JobHandler handler;
    protected JPanel pnlMain = new JPanel();

    protected boolean debug = false;

    public DefaultPanel(JobHandler handler){
        this.handler = handler;
    }
    public void addComponents(){
       System.out.println("AN ERROR HAS OCCURED");
        System.out.println("DEFAULT PANEL ADDCOMPONENTS");
    }

    public void toggleDebug(){
        debug = !debug;
    }
    public void setDebug(boolean debug){
        this.debug = debug;
    }
    public boolean getDebug(){
        return debug;
    }

}
