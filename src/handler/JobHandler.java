package handler;

import core.Job;
import fileIO.FileReadWrite;

/**
 * Created by Brandon194 on 4/17/2015.
 */
public class JobHandler {
    /** FileReadWrite \\Brandon194\WorkCalculator\JobHandler */
    FileReadWrite frw = new FileReadWrite("WorkCalculator", "JobHandler");

    /** Array of Jobs */
    Job[] jobs = new Job[0];
    /** Number of Jobs in the array */
    int numOfJobs = 0;

    public JobHandler(){
        String[] jobNames = frw.reader();
        jobs = new Job[jobNames.length];

        for (int i=0; i<jobNames.length;i++){
            try {
                newJob(Job.readJob(jobNames[i]));
            }catch(Exception e){
                newJob(new Job("" + i,0,0));
                System.out.println(Job.readJob(jobNames[i]).getName());
                System.out.println("JobHandler (26): Job Read Fail");
            }
        }
    }

    /**
     * Adding a new Job to the array
     * @param newJob Job to be handled
     */
    public void newJob(Job newJob){
        Job[] temp = new Job[jobs.length+1];

        for (int i=0;i<jobs.length;i++){
            temp[i] = jobs[i];
        }
        temp[jobs.length] = newJob;
         numOfJobs++;

        jobs = temp;

        saveChanges();
    }

    /**
     * Adding a new Job to the array
     * @param name Name of the job
     * @param wage Wage of the job
     */
    public void newJob(String name, double wage){
        newJob(new Job(name, wage));
    }

    /**
     * Adds hours to the Jobs in the array in order
     * @param hoursArray Array of Integers ready to be applied to the jobs in order
     * @throws IndexOutOfBoundsException
     */
    public void addHours(int[] hoursArray) throws IndexOutOfBoundsException{
        for (int i=0;i<hoursArray.length;i++){
            if (jobs[i] == null){
                break;
            }else{
                try{
                    jobs[i].setHours(hoursArray[i]);
                }catch(IndexOutOfBoundsException e){
                    new IndexOutOfBoundsException("Error in intHours");
                }
            }

        }
        saveChanges();
    }

    /**
     * Deletes job
     * @param jobName Name to search for job
     */
    public void deleteJob(String jobName){
        for (int i=0;i<numOfJobs;i++){
            if (jobs[i].getName().equals(jobName)){
                for (int ii = i;i<numOfJobs-1;i++){
                    jobs[ii] = jobs[ii+1];
                    jobs[ii+1] = null;
                }
                numOfJobs--;
                saveChanges();
                jobs[i].delete();
                break;
            }
        }
    }

    /**
     * Deletes job
     * @param jobNumber number of job in array
     */
    public void deleteJob(int jobNumber){
        for (int i=jobNumber;i<numOfJobs-1;i++){
            jobs[i] = jobs[i+1];
        }
        numOfJobs--;
        saveChanges();
        jobs[jobNumber].delete();
    }

    /**
     * Deletes old file, and creates new one with the new data
     */
    private void saveChanges(){
        String[] jobNames = new String[numOfJobs];
        for (int i=0;i<numOfJobs;i++){
            jobNames[i] = jobs[i].getName();
        }

        frw.writer(jobNames);
    }

    /**
     * Gets job
     * @param jobName Name of job to return
     * @return Job with name the same as jobName
     */
    public Job getJob(String jobName){
        for (int i=0;i<numOfJobs;i++){
            if (jobs[i].getName().equals(jobName))
                return jobs[i];
        }
        System.out.println("No Results Found");
        return null;
    }

    /**
     * Gets job
     * @param num position of job wanted in the array
     * @return
     */
    public Job getJob(int num){
        return jobs[num];
    }

    public Job[] getAllJobs(){
        return jobs;
    }
    public int getNumOfJobs(){
        return numOfJobs;
    }
}
