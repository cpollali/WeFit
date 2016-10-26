/* Player.java
 CS230 Final Project
 Lucy Shen & Christina Pollalis
 Date: 12/4/14
 Description: This is the player class. A player has a name, a 
 password and also a collection of days (represented as an array of
 "Days"). In this class, you can set a player's name, password, and all 
 the workouts for the week. You can also retrieve a player's name, 
 total player points, and the points for a particular day. It also 
 tests whether a particular password is really the player's password.
 Work division: written mostly by Lucy 
 */
//-----------

import java.util.LinkedList;

public class Player {
  
  /***********************************************
    * Instance Variables
    * ********************************************/
  private String name, password; 
  private String petName; //stores the file name of the pet image
  private Day[] week; //holds an array of "Days"
  
  /***********************************************
    * Constructor
    * ********************************************/
  public Player(String playerName, String inputPassword) {
    name = playerName;
    password = inputPassword;
    petName = "";
    
    //populates the day array
    week = new Day[7]; //will never exceed as week only ever has 7 days in total
    for (int i = 0; i<week.length; i++){ 
      week[i] = new Day(i);
    }
  }
  
  /***********************************************
    * Getter Methods
    * ********************************************/
  
  /******************************************************************
    Returns as a String the name of this Player object.
  ******************************************************************/
  public String getPlayerName() {
    return name;
  }
  
  /******************************************************************
    Returns as a Integer the total points of this Player object.
  ******************************************************************/
  public int getPlayerPoints() { 
    int result = 0;
    
    for (int i=0; i<week.length; i++) { //goes into the week and aggregates all the points
      result += week[i].getDayPoints();
    }
    return result;
  }
  
  /******************************************************************
    Returns as a String the points per day of the week.
  ******************************************************************/
  public String getWeekPoints(){
    String result = Integer.toString(week[0].getDayPoints());
    for (int i = 1; i<7; i++){
      result += "," + week[i].getDayPoints();
    }
    System.out.println("week points are: " + result);
    return result;
  }
  
  /******************************************************************
    Returns as a LinkedList of WorkoutSession objects all the
    WorkoutSession this Player object has for the specified day.
    @param dayIndex - the index of the Day for which WorkoutSessions are retrieved.
  ******************************************************************/
  public LinkedList<WorkoutSession> getDaySessions(int dayIndex){
    return week[dayIndex].getWorkoutsList();
  }
  
  /******************************************************************
    Returns as an Integer the points for the specified day.
    @param dayIndex - the index of the Day for which points are retrieved.
  ******************************************************************/
  public int getOneDayPoints(int dayIndex){ //remember 0-indexing, Sunday is 0
    return week[dayIndex].getDayPoints();
  }
  
  /******************************************************************
    Returns the Player's password as a String.
  ******************************************************************/
  public String getPassword(){
    return password;
  }
  
  /******************************************************************
    Returns the Day object at the specified index.
    @param dayIndex - the index of the Day to be returned.
  ******************************************************************/
  public Day getOneDay(int dayIndex){
    return week[dayIndex];
  }
  
  /******************************************************************
    Returns the String name of the Player's pet.
  ******************************************************************/
  public String getPet(){
    return petName;
  }
  
  /***********************************************
    * Setter Methods
    * ********************************************/  
  
  /******************************************************************
    Sets the name of the Player to the inputted String.
    @param inputName - String to set the Player's name to.
  ******************************************************************/
  public void setName(String inputName){
    name = inputName;
  }
  
  /******************************************************************
    Sets the name of the Player's pet to the inputted String.
    @param inputPet - String to set the Player's pet's name to.
  ******************************************************************/
  public void setPet(String inputPet){
    petName = inputPet;
  }
  
  /******************************************************************
    Sets the password of the Player to the inputted String.
    @param inputPassword - String to set the Player's password to.
  ******************************************************************/
  public void setPassword(String inputPassword){
    password = inputPassword;
  }
  
  /******************************************************************
    Sets the points in one day of a Player's week.
    @param weekDayIndex - The index of the Day in the week to set points for.
    @param dayPoints - The number of points to set the Day's points to.
  ******************************************************************/
  public void setDayPoints(int weekDayIndex, int dayPoints){
    //System.out.println("setting day " + weekDayIndex + " to points: " + dayPoints);
    week[weekDayIndex].setDayPoints(dayPoints);
  }
  
  /***********************************************
    * Instance Methods
    * ********************************************/
  
  /******************************************************************
    Returns TRUE if the inputted String is the correct password for this Player.
    @param input - String to check password for.
  ******************************************************************/
  public boolean isPassword(String input) { 
    return input.equals(password);
  }
  
  /******************************************************************
    Adds the inputted WorkoutSession to the Day specified by the index.
    @param dayIndex - the index of the Day to add the WorkoutSession to.
    @param inputWorkout - the WorkoutSession to at to the specified Day.
  ******************************************************************/
  public void addWorkoutToDay(int dayIndex, WorkoutSession inputWorkout){
    week[dayIndex].addWorkoutSession(inputWorkout);
    //System.out.println("After addWorkoutToDay, day " + dayIndex + " has: " + week[dayIndex].getDayPoints());
  }
  
  /******************************************************************
    Returns the String representation of the Player object (their name).
  ******************************************************************/
  public String toString(){
    return name;
  }
  
  /***********************************************
    * Main Method
    * ********************************************/
  public static void main (String[] args){
    Player tester = new Player("Lucy","penguin");
  }
 
  
}