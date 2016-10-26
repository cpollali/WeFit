/* Day.java
 CS230 Final Project
 Lucy Shen & Christina Pollalis
 Date: 12/4/14
 Description: This is the Day class. It calculates the total number of workout points of that day.
 It also has a linkedlist of workouts of the day, to which it can add workouts for that day.
 Work division: written together by Lucy and Christina
 */
//-----------
import java.util.LinkedList;

public class Day {
  
  /***********************************************
    * Instance Variables
    * ********************************************/
  private int dayNumber;
  private int totalDayPoints;
  private LinkedList<WorkoutSession> workouts;
  
  /***********************************************
    * Constructor
    * ********************************************/
  public Day(int index) {
    //initializations
    dayNumber = index;
    workouts = new LinkedList<WorkoutSession>();
    totalDayPoints = 0; //starts the day off at 0 points
  }
  
  /***********************************************
    * Getters
    * ********************************************/
  
  /******************************************************************
  * Returns the total amount of points of this specific Day object.
  * @return Integer representation of points.
  *****************************************************************/
  public int getDayPoints() {
    //System.out.println("Day Number " + dayNumber + " returns: " + totalDayPoints);
    return totalDayPoints;
  }
  
  /******************************************************************
  * Returns as a string all the workouts in this particular Day.
  * @return String represenation of all workoutsin a Day.
  *****************************************************************/
  public String getWorkouts(){
    String result = Integer.toString(dayNumber);
    for (int i = 0; i<workouts.size(); i++){
      result += "\n" + workouts.get(i).toString();
    }
    return result;
  }

  /******************************************************************
  * Returns all the WorkoutSessions in a Day as a LinkedList.
  * @return LinkedList of WorkoutSession objects.
  *****************************************************************/
  public LinkedList<WorkoutSession> getWorkoutsList(){
    return workouts;
  }
  
  /***********************************************
    * Setters
    * ********************************************/
  //used for save file read-in, sets the point value of a day in the week
  public void setDayPoints(int inputPoints){
    //System.out.println("In Day " + dayNumber + ", setting to: " + inputPoints);
    totalDayPoints = inputPoints;
  }
  
  /***********************************************
    * Instance Methods
    * ********************************************/
  
  /******************************************************************
  * Adds a specific workout to the specific day.
  *****************************************************************/
  public void addWorkoutSession(WorkoutSession inputWorkout) {
    totalDayPoints += inputWorkout.getSessionPoints(); //update day point total
    workouts.add(inputWorkout);
    //System.out.println("Total day points have been updated to : " + totalDayPoints);
  }
  
  /******************************************************************
  * Reads in workouts from save file without actually re-adding points.
  *****************************************************************/
  public void readWorkouts(WorkoutSession inputWorkout){
    workouts.add(inputWorkout);
  }
  
  /***********************************************
    * Main Method for testing
    * ********************************************/
  public static void main(String[] args){
    Day tester = new Day(6); //saturday
  }
  
}