package core;

import fileIO.FileReadWrite;

/**
 * Created by Brandon194 on 4/17/2015.
 */
public class Job {
    /** Name of the job */
    private String name;
    /** Hourly Wage */
    private double wagePerHour;
    /** How many hours you worked */
    private int hoursWorked = 0;
    /** FileIO Class. How to read and write files */
    private FileReadWrite frw;

    public Job(String name, double wagePerHour, int hoursWorked){
        this.name = name;
        this.wagePerHour = wagePerHour;
        this.hoursWorked = hoursWorked;
        writeJob();
    }
    public Job(String name, double wagePerHour){
        this.name = name;
        this.wagePerHour = wagePerHour;
        this.hoursWorked = 0;
        writeJob();
    }

    /** Write all variables to the file */
    public void writeJob() {
        frw = new FileReadWrite("WorkCalculator", name);
        String[] writerData = {name, "" + wagePerHour, "" + hoursWorked};
        frw.writer(writerData);
    }

    /**
     * @param jobName Name of the job looking to be read
     * @return Job read from file
     */
    public static Job readJob(String jobName){
        FileReadWrite frw = new FileReadWrite("WorkCalculator",jobName);
        String[] readerData = frw.reader();

        System.out.println(readerData[0] + " " + readerData[1] + " " + readerData[2]);

        try{
            double d = Double.parseDouble(readerData[1]);
        } catch (Exception e){
            System.out.println("Double Fail");
        }
        try{
            int i = Integer.parseInt(readerData[2]);
        } catch (Exception e){
            System.out.println("Integer Fail");
        }

        if (readerData.length == 2)
            return new Job(readerData[0], Double.parseDouble(readerData[1]));
        if (readerData.length == 3)
            return new Job(readerData[0],Double.parseDouble(readerData[1]),Integer.parseInt(readerData[2]));
        return null;
    }

    /**
     * Delete's this job
     */
    public void delete(){
        frw.delete();
    }

    public String getName(){
        return name;
    }
    public double getWage(){
        return wagePerHour;
    }
    public int getHours(){
        return hoursWorked;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setWage(double wagePerHour) {
        this.wagePerHour = wagePerHour;
    }
    public void setHours(int hours){
        hoursWorked = hours;
        writeJob();
    }
    public String toString(){
        return "Name: " + name + "    Wage: " + wagePerHour;
    }
}
