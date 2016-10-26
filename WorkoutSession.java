/* WorkoutSession.java
 CS230 Final Project
 Lucy Shen & Christina Pollalis
 Date: 12/4/14
 Description: This is the class that holds a workout session. It
 creates a workout session by taking in a workoutkind and the time 
 spent on it. It can then get the duration of the workout, its session
 points, the session name, and can print the workout session (toString).
 Work division: written mostly by Lucy
 */
//-----------

public class WorkoutSession  {
  
  /***********************************************
    * Instance variables
    * ********************************************/
  private WorkoutKind oneWorkout;
  private int workoutTime;
  private int sessionPoints;
  
  /***********************************************
    * Constructor
    * ********************************************/
  public WorkoutSession(WorkoutKind workoutType, int timeSpent){
    oneWorkout = workoutType;
    workoutTime = timeSpent;
    sessionPoints = workoutTime*oneWorkout.getPPM();
  }
  
  /***********************************************
    * Getters
    * ********************************************/
  
  /***********************************************
    * Returns the duration of the workout
    * ********************************************/
  public int getDuration() {
    return workoutTime;
  }
  
  /***********************************************
    * Returns the number of points for this session
    * ********************************************/
  public int getSessionPoints() {    
    return sessionPoints;
  } 
  
  /***********************************************
    * Returns the name of the session
    * ********************************************/
  public String getSessionName(){
    return oneWorkout.getName();
  }
  
  /***********************************************
    * Instance methods
    * ********************************************/
  
  
  /***********************************************
    * ToString - returns a string representation, 
    * which is used to save the file
    * ********************************************/
  public String toString(){
    return oneWorkout.getName() + "," + Integer.toString(workoutTime);
  }
  
  /***********************************************
    * Main method - FOR TESTING
    * ********************************************/
  public static void main (String[] args){
    WorkoutSession tester = new WorkoutSession(new WorkoutKind("running",2), 5);
    System.out.println(tester);
  }
  
}