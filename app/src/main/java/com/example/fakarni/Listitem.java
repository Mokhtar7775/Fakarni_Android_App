package com.example.fakarni;

public class Listitem {
    private String TaskName,Date, Description;


    public Listitem(String Name, String Descreption, String Date) {
        this.TaskName=Name;
        this.Description =Descreption;
        this.Date=Date;

    }
    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }


    public String getName() {
        return TaskName;
    }

    public void setName(String name) {
        TaskName = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
