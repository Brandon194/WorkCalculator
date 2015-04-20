package core;

import fileIO.FileReadWrite;

/**
 * Created by Brandon194 on 4/17/2015.
 */
public class Job {
    private String name;
    private double wagePerHour;
    private FileReadWrite frw;

    public Job(String name, double wagePerHour){
        this.name = name;
        this.wagePerHour = wagePerHour;
        writeJob();
    }

    public void writeJob(){
        frw = new FileReadWrite("WorkCalculator",name);
        String[] writerData = {name, "" + wagePerHour};
        frw.writer(writerData);
    }
    public void delete(){
        frw.delete();
    }

    public static Job readJob(String jobName){
        FileReadWrite frw = new FileReadWrite("WorkCalculator",jobName);
        String[] readerData = frw.reader();

        return new Job(readerData[0],Double.parseDouble(readerData[1]));
    }

    public String getName(){
        return name;
    }
    public double getWage(){
        return wagePerHour;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setWage(double wagePerHour){
        this.wagePerHour = wagePerHour;
    }
    public String toString(){
        return "Name: " + name + "    Wage: " + wagePerHour;
    }
}
