package handler;

import core.Job;
import fileIO.FileReadWrite;

/**
 * Created by Brandon194 on 5/15/2015.
 */
public class JobHandler {
    private final FileReadWrite frw = new FileReadWrite("WorkCalculator", "JobHandler");

    private Job[] jobs = null;

    public JobHandler(){
        String[] jobNames = frw.reader();

        for (int i=0;i<jobNames.length;i++) {
            addExistingJob(jobNames[i]);
        }
    }

    /**
     * expands the array by 1
     */
    private void expandArray(){
        System.out.println("Expanding Jobs Array");
        if (jobs == null){
            jobs = new Job[1];
        } else {
            System.out.println("Size before expand: " + jobs.length);
            Job[] temp = new Job[jobs.length + 1];
            int lastIndex = 1;
            for (int i = 0; i < jobs.length; i++) {
                temp[i] = jobs[i];
                lastIndex++;
            }
            jobs = temp;
        }
        System.out.println("Array Expanded, Current Length: " + jobs.length);
    }

    /**
     * Adds existing job from handler file to program
     * @param jobName Name of the existing job to find
     */
    private void addExistingJob(String jobName){
        expandArray();

        FileReadWrite frw2 = new FileReadWrite("WorkCalculator", jobName);
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
        jobs[jobs.length-1] = new Job(name,wage);
        saveChanges();
    }

    /**
     * Deletes job at index, reduses length of array
     * @param index of job to be deleted
     */
    public void deleteJob(int index){
        jobs[index] = null;
        Job[] temp = new Job[jobs.length-1];

        for (int i=0;i<jobs.length;i++){
            if (i>jobs.length-1) {
                if (jobs[i] == null) {
                    jobs[i] = jobs[i + 1];
                    jobs[i + 1] = null;
                }
                temp[i] = jobs[i];
            }
        }
        jobs = temp;
        saveChanges();
    }

    /**
     * Writes changes to the array to the disk
     */
    private void saveChanges(){
        String[] jobNames = new String[jobs.length];

        for (int i=0;i<jobNames.length;i++){
            try {
                jobNames[i] = jobs[i].getName();
            }catch(Exception e){
                jobNames[i] = "";
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
}
