package handler;

import core.Job;
import fileIO.FileReadWrite;

/**
 * Created by Brandon194 on 4/17/2015.
 */
public class JobHandler {
    FileReadWrite frw = new FileReadWrite("WorkCalculator", "JobHandler");

    Job[] jobs = new Job[12];
    int numOfJobs = 0;

    public JobHandler(){
        String[] jobNames = frw.reader();

        for (int i=0; i<jobNames.length;i++){
            jobs[i] = Job.readJob(jobNames[i]);
        }

        numOfJobs = jobNames.length;
    }

    public void newJob(Job newJob){
        jobs[numOfJobs] = newJob;
        numOfJobs++;

        saveChanges();
    }

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

    public void newJob(String name, double wage){
        newJob(new Job(name, wage));
    }
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
    public void deleteJob(int jobNumber){
        for (int i=jobNumber;i<numOfJobs-1;i++){
            jobs[i] = jobs[i+1];
        }
        numOfJobs--;
        saveChanges();
        jobs[jobNumber].delete();
    }
    private void saveChanges(){
        String[] jobNames = new String[numOfJobs];
        for (int i=0;i<numOfJobs;i++){
            jobNames[i] = jobs[i].getName();
        }

        frw.writer(jobNames);
    }

    public Job getJob(String jobName){
        for (int i=0;i<numOfJobs;i++){
            if (jobs[i].getName().equals(jobName))
                return jobs[i];
        }
        System.out.println("No Results Found");
        return null;
    }
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
