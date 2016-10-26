/* WorkoutKind.java
 CS230 Final Project
 Lucy Shen & Christina Pollalis
 Date: 12/4/14
 Description: The WorkoutKind object is used by the WorkoutList array. 
 It represents a possible workout that the user can add to their day of
 working out. A WorkoutKind object contains a name and a "points per minute (ppm)"
 value that specifies how much a specific WorkoutKind is worth in points
 per minute of the user doing said workout.
 Work division: written mostly by Lucy
 */
//-----------

public class WorkoutKind  {
  /***********************************************
    * Instance variables
    * ********************************************/
  private String name; //Possible workouts: basketball, swimming, jogging, pushups etc
  private int ppm; //points per minute
  
  
  /***********************************************
    * Constructor: takes the name and point-per-minute value of 
    * the workout as parameters
    * ********************************************/
  public WorkoutKind(String workoutName, int points){
    name = workoutName;
    ppm = points;
  }
  
  /******************************************************************
  * Getters
  *****************************************************************/
  
 /******************************************************************
  * Returns the points per minute value
  *****************************************************************/  
  public int getPPM() {
    //System.out.println("Kind ppm is: " + ppm);
    return ppm;
  } 
  
  /******************************************************************
  * returns the name of the WorkoutKind (String)
  *****************************************************************/
  public String getName() {
    return name;
  }
  
  /******************************************************************
  * returns a string representation of the object by its name
  *****************************************************************/
  public String toString(){
    return name;
  }
  
  /******************************************************************
  * Main method for testing
  *****************************************************************/
  public static void main (String[] args){
    WorkoutKind tester = new WorkoutKind("running",5);
    System.out.println(tester.getPPM());
    System.out.println(tester.getName());
    System.out.println(tester);
  }
  
}