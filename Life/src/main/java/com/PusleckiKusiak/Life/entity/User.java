package com.PusleckiKusiak.Life.entity.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;
	private boolean doneDaily = false;
	private boolean doneWeekly = false;
	private int xp = 0;
	private String role;


	public User(){
	    this.role = "User";
    }
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDoneDaily() {
        return doneDaily;
    }

    public void setDoneDaily(boolean doneDaily) {
        this.doneDaily = doneDaily;
    }

    public boolean isDoneWeekly() {
        return doneWeekly;
    }

    public void setDoneWeekly(boolean doneWeekly) {
        this.doneWeekly = doneWeekly;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
