package handler;

import core.Job;
import fileIO.FileReadWrite;

/**
 * Created by Brandon194 on 5/15/2015.
 */
public class JobHandler {
    private FileReadWrite frw;

    private Job[] jobs = null;

    private boolean debug = false;

    public JobHandler(){
        frw = new FileReadWrite("WorkCalculator", "JobHandler", debug);
        String[] jobNames = frw.reader();

        for (int i=0;i<jobNames.length;i++) {
            addExistingJob(jobNames[i]);
        }
    }

    /**
     * expands the array by 1
     */
    private void expandArray(){
        if (jobs == null){
            jobs = new Job[1];
        } else {
            Job[] temp = new Job[jobs.length + 1];
            int lastIndex = 1;
            for (int i = 0; i < jobs.length; i++) {
                temp[i] = jobs[i];
                lastIndex++;
            }
            jobs = temp;
        }
    }

    /**
     * Adds existing job from handler file to program
     * @param jobName Name of the existing job to find
     */
    private void addExistingJob(String jobName){
        expandArray();

        FileReadWrite frw2 = new FileReadWrite("WorkCalculator", jobName, debug);
        String[] job = frw2.reader();

        if (job.length == 1){
            addNewJob("Errmac",0);
        }else if (job.length == 2){
            jobs[jobs.length-1] = new Job(job[0],Double.parseDouble(job[1]));
        } else if (job.length >= 3){
            jobs[jobs.length-1] = new Job(job[0],Double.parseDouble(job[1]),Integer.parseInt(job[2]));
        }
    }

    /**
     * Adds new job to the array
     * @param name name of job
     * @param wage wage of job
     */
    public void addNewJob(String name, double wage){
        expandArray();
        jobs[jobs.length-1] = new Job(name,wage,debug);
        saveChanges();
    }

    /**
     * Deletes job at index, reduses length of array
     * @param index of job to be deleted
     */
    public void deleteJob(int index){
        jobs[index] = null;
        int nullCount = 0;

        for (int i = 0; i < jobs.length; i++) {
            if (jobs[i] != null) {
                jobs[i - nullCount] = jobs[i];
            } else {
                nullCount++;
            }
        }

        Job[] temp = new Job[jobs.length-nullCount];

        for (int i=0;i<temp.length;i++){
            temp[i] = jobs[i];
        }

        jobs = temp;
        saveChanges();
    }

    /**
     * Writes changes to the array to the disk
     */
    private void saveChanges(){

        frw = new FileReadWrite("WorkCalculator", "JobHandler", debug);

        int nullCount = 0;
        for (Job j : jobs){
            if (j == null) nullCount++;
        }

        String[] jobNames = new String[jobs.length-nullCount];

        for (int i=0;i<jobNames.length;i++){
            if (jobs[i] != null) {
                jobNames[i] = jobs[i].getName();
            }
        }
        frw.writer(jobNames);
    }

    public Job getJob(int index){
        return jobs[index];
    }
    public int getNumOfJobs(){
        if (jobs == null){
            return 0;
        }
        return jobs.length;
    }

    public void toggleDebug(){
        debug = !debug;
        jobs = null;
    }
    public void setDebug(boolean debug){
        if (this.debug != debug){
            this.toggleDebug();
        }
    }
}
