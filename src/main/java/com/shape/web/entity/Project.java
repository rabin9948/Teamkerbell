package com.shape.web.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Sort;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "projectidx")
@Table(name = "Project")
public class Project implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "PROJECTIDX")
    private Integer projectidx;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LEADERIDX")
    private Integer leaderidx;

    @Column(name = "MINUTE")
    private String minute;

    @Column(name = "PROCESSED")
    private boolean processed = true;

    @ManyToMany(mappedBy = "projects",fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<User>();

    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private Set<Alarm> alarms = new HashSet<Alarm>();
    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private Set<FileDB> filedbs = new HashSet<FileDB>();
    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private Set<Minute> minutes;

    @JsonIgnore
    @OneToMany(mappedBy = "project")
    @Sort
    private Set<Schedule> schedules = new HashSet<Schedule>();
    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private Set<Todolist> todolists = new HashSet<Todolist>();

    public Set<Alarm> getAlarms() {
        return alarms;
    }

    public void addAlarms(Alarm alarm) {
        this.alarms.add(alarm);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public Project() {
        minutes = new HashSet<Minute>();
    }

    public Project(String name, Integer leaderidx, String minute) {
        this.name = name;
        this.leaderidx = leaderidx;
        this.minute = minute;
        minutes = new HashSet<Minute>();
    }

    public Integer getProjectidx() {
        return projectidx;
    }

    public void setProjectidx(Integer projectidx) {
        this.projectidx = projectidx;
    }

    public Integer getLeaderidx() {
        return leaderidx;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLeaderidx(Integer leaderidx) {
        this.leaderidx = leaderidx;
    }

    public Set<FileDB> getFiledbs() {
        return filedbs;
    }

    public Set<Minute> getMinutes() {
        return minutes;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public Set<Todolist> getTodolists() {
        return todolists;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    @Override
    public boolean equals(Object o ){
        Project pj=(Project)o;
        return this.projectidx == pj.getProjectidx();
    }

}
