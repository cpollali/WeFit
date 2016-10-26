/* WorkoutList.java
 CS230 Final Project
 Lucy Shen & Christina Pollalis
 Date: 12/4/14
 Description: The WorkoutList object stores all the possible workouts
 that a user can do in the form of a hashtable of WorkoutKind objects
 constructed with information from a source file. The keys are the names
 of each possible workout.
 Work division: written together by Lucy and Christina
 */
//-----------

import java.io.*;
import java.util.*;

public class WorkoutList {
  
  /***********************************************
    * Instance Variables
    * ********************************************/
  
  //stores all the possible workouts and their corresponding WorkoutKind object
  private Hashtable<String,WorkoutKind> allWorkouts;
  
  
  /****************************************************************************
    * Constructor: Reads in the file that stores all the information about
    * possible workouts and creates a WorkoutKind object for each possibility,
    * then maps the WorkoutKind object to a hashtable with its name as the key.
    * *************************************************************************/
  public WorkoutList() {
    try {
      allWorkouts = new Hashtable<String,WorkoutKind>();
      Scanner reader = new Scanner(new File("workoutListing.txt"));
      while(reader.hasNext()){
        //System.out.println("in the while loop!");
        String name = reader.next();
        int points = Integer.parseInt(reader.next());
        //System.out.println("Name is: " + name + " points are: " + points);
        WorkoutKind toAdd = new WorkoutKind(name,points);
        allWorkouts.put(name, toAdd);
        //System.out.println("New workout! " + toAdd);
      }
    } catch (FileNotFoundException e) {
      System.out.println("ERROR. Workout listing file is missing.");
    }
    
  }
  
  /****************************************************************************
    * Getters
    * *************************************************************************/
  
  /****************************************************************************
    * Returns the workoutkind based on the name of the workout
    * *************************************************************************/
  public WorkoutKind getWorkoutKind(String workoutName){
    return allWorkouts.get(workoutName);
  }
  
  /****************************************************************************
    * Returns all the possible workouts in a linkedlist
    * *************************************************************************/
  public LinkedList<WorkoutKind> getAllWorkouts(){
    LinkedList<WorkoutKind> result = new LinkedList<WorkoutKind>();
    Enumeration<WorkoutKind> toLoop = allWorkouts.elements();
    while (toLoop.hasMoreElements()){
      result.add(toLoop.nextElement());
    }
    return result;
  }
  
  
  /****************************************************************************
    * Main method - for testing
    * *************************************************************************/
  public static void main (String[] args){
    WorkoutList tester = new WorkoutList();
    System.out.println(tester.getAllWorkouts());
  }
  
}