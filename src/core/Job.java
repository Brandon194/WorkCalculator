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

        for (int i=0;i<readerData.length;i++){
            System.out.println("READERDATA " + i + ": " + readerData[i]);
        }

        try{
            String name = readerData[0];
            double wage = Double.parseDouble(readerData[1]);
            int hours = Integer.parseInt(readerData[2]);
        }catch (Exception e){
            System.out.println("Fucking cunt");
            e.printStackTrace();
        }
        //Job j = new Job(readerData[0],Double.parseDouble(readerData[1]),Integer.parseInt(readerData[3]));

        return new Job("", 0);
    }

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
