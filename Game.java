/* Game.java
 CS230 Final Project
 Lucy Shen & Christina Pollalis
 Date: 12/4/14
 Description: This class is the Game Class. It creates a game where players
 are added. It also has a default player in case no player has been created
 yet. This class also uses the day and calendar objects to determine what day and week of 
 the month it is. This class can add a player, sets the current player, returns all the players in the 
 game, gets a particular player, gets the current player, gets the day of the week, the week
 number and determines whether a player exists in the game (based on an index).
 Work division: written mostly by Lucy 
 */
//-----------
import java.util.LinkedList;
import java.util.Calendar; //below all for calendar and date objects
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.GregorianCalendar;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Game {
  
  /***********************************************
    * Instance Variables
    * ********************************************/
  
  private Player[] allPlayers; //basketball, swimming, jogging, pushups etc
  private int[] pastMonth; //array that stores the previous month's rankings
  private final int total = 6;
  private Player currentPlayer; //stores the player that logged in
  private WorkoutList possibleWorkouts; //stores all the possible workouts
  // get the supported ids for GMT-05:00 (Pacific Standard Time)
  private String[] ids = TimeZone.getAvailableIDs(-5 * 60 * 60 * 1000);
  private Calendar calendar;
  private Date trialTime;
  // create a Eastern Standard Time time zone
  private SimpleTimeZone pdt = new SimpleTimeZone(-5 * 60 * 60 * 1000, ids[0]);
  private final int NOT_FOUND = -1;
  
  private int weekDayIndex; //Sunday is 1, Saturday is 7
  
  /***********************************************
    * Constructor
    * ********************************************/
  
  public Game() {
    allPlayers = new Player[total]; //array of null Players
    addPlayer(0, "Empty", ""); //defualt current player to test if anyone has logged in yet
    currentPlayer = allPlayers[0];
    possibleWorkouts = new WorkoutList();
    
    //---------setting up the date and time----------
    calendar = new GregorianCalendar(pdt); //uses the Calendar object in java (to get what day and week it is)
    trialTime = new Date(); //uses the Date object in java to set the time in the Calendar object
    calendar.setTime(trialTime); //sets time to a new Date
    //System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK)); //testing
    //System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
    //REMINDER: SUNDAY IS ALWAYS 0.
    //the below two are going to be used in order to add a specific workout to the correct day
    //and display the total points for each week depending on the week number
    weekDayIndex = calendar.get(Calendar.DAY_OF_WEEK)-1; //subtract 1 for 0-indexing
    
    //read in savedGame.txt and add all the existing players to the game
    try{
      Scanner scanFile = new Scanner(new File("saveFiles/savedGame.txt"));
      scanFile.useDelimiter(",|\n");
      while (scanFile.hasNext()){
        int playerIndex = Integer.parseInt(scanFile.next());
        //System.out.println("playerIndex: " + playerIndex);
        String playerName = scanFile.next();
        //System.out.println("playerName: " + playerName);
        String playerPass = scanFile.next();
        //System.out.println("playerPass: " + playerPass);
        addPlayer(playerIndex, playerName, playerPass);
      }
      scanFile.close();
    } catch (IOException ex){
      //System.out.println("File not found!");
    }
    
    //read in each individual player and add their information to the game
    try {
      for (int i = 1; i < allPlayers.length; i++){ //loop through all 5 players
        Player scanningPlayer = allPlayers[i];
        if (scanningPlayer != null){
          Scanner playerScan = new Scanner (new File("saveFiles/"+scanningPlayer.getPlayerName()+".txt"));
          //System.out.println("-----Currently scanning: " + scanningPlayer.getName());
          playerScan.useDelimiter(",|\n");
          //scan in the name of the player's pet
          scanningPlayer.setPet(playerScan.next());
          //check if it's a new day
          int savedDay = playerScan.nextInt();
          //System.out.println("Last login was: " + savedDay);
          if (savedDay==weekDayIndex){ //if it is still the same day, read in the workouts
            //today's workouts
            String nextItem = playerScan.next();
            while (!nextItem.equals("#")){
              String workoutName = nextItem;
              //System.out.println("Workout name is: " + workoutName);
              WorkoutKind findKind = possibleWorkouts.getWorkoutKind(workoutName);
              int timeSpent = Integer.parseInt(playerScan.next());
              WorkoutSession toAdd = new WorkoutSession(findKind,timeSpent);
              scanningPlayer.getOneDay(weekDayIndex).readWorkouts(toAdd);
              nextItem = playerScan.next();
            }
            
            //then read in the current week of points
            for (int k = 0; k < 7; k++){
              int dayPoints = Integer.parseInt(playerScan.next());
              System.out.println("Day " + k + " points: " + dayPoints);
              scanningPlayer.setDayPoints(k,dayPoints);
            }
          } else if (savedDay > weekDayIndex){ //if it's not the same day, check if a new week has begun.
            //don't bother reading in point info, the game basically resets with empty players
          } else { //if a new week hasn't begun but it's a new day, read in the points in the week so far.
            //skip past the workouts because we don't care about them if it's a new day
            while(!playerScan.next().equals("#")){
              playerScan.next();
            }
            //read in the week's previous points
            for (int k = 0; k < 7; k++){
              int dayPoints = Integer.parseInt(playerScan.next());
              System.out.println("Day " + k + " points: " + dayPoints);
              scanningPlayer.setDayPoints(k,dayPoints);
            }
          }
          
        }
      }
    } catch (IOException ex){ //catch the exception is the file does not exist
      System.out.println("File not found!");
    }
  }
  
  /***********************************************
    * Setter Methods
    * ********************************************/
  
  /******************************************************************
    Sets the current active Player.
    @param playerIndex - Index position of the currently active player.
  ******************************************************************/
  public void setCurrentPlayer(int playerIndex){
    currentPlayer = allPlayers[playerIndex];
  }
  
  /***********************************************
    * Getter Methods
    * ********************************************/
  
  /******************************************************************
    Returns the Player object at the inputted index.
    @param playerIndex - Index position of the Player to retrieve.
  ******************************************************************/
  public Player getOnePlayer(int playerIndex){
    Player result = allPlayers[playerIndex];
    if (result != null){
      return result;
    } else {
      return allPlayers[0]; //return default nonplayer if player doesn't exist yet
    }
  }
  
  /******************************************************************
    Returns the Player object that is the currently active Player.
  ******************************************************************/
  public Player getCurrentPlayer(){
    return currentPlayer;
  }
  
  /******************************************************************
    Returns the current day of the week.
  ******************************************************************/
  public int getWeekDay(){
    return weekDayIndex;
  }
  
  /***********************************************
    * Instance Methods
    * ********************************************/
  
  /******************************************************************
    Returns TRUE if the inputted name is already taken by another Player
    @param inputName - String name to check.
  ******************************************************************/
  public boolean nameTaken(String inputName){
    for (int i = 1; i < 6; i++){
      if (allPlayers[i] != null){ //if a player exists in this slot
        if (allPlayers[i].getPlayerName().equals(inputName)){ //check if the name is taken
          return true;
        }
      }
    }
    return false; //default false
  }
  
  /******************************************************************
    Returns TRUE if the player at the inputted playerIndex exists.
    @param playerIndex - Index of the player to check.
  ******************************************************************/
  public boolean playerExists(int playerIndex){
    return allPlayers[playerIndex] != null;
  }
  
  /******************************************************************
    Adds a player to the array of Players in a game. 
    @param index - Integer index of the space to add the Player
    @param name - The String name of the Player to add.
    @param password - The String password of the Player to add.
  ******************************************************************/
  public void addPlayer(int index, String name, String password) {
    allPlayers[index] = new Player(name, password);
  }  
  
  /******************************************************************
    Saves the data of the game and each individual player to a text file.
  ******************************************************************/
  public void saveGame(){
    //saving all the players' names
    try {
      PrintWriter playerWriter = new PrintWriter(new File("saveFiles/savedGame.txt"));
      
      //prints each of the player names, ignoring the first null player
      for (int i = 1; i < allPlayers.length; i++) {
        if (allPlayers[i] != null){
          playerWriter.print(i+","+allPlayers[i]+","+allPlayers[i].getPassword()+"\n");
        }
      }
      playerWriter.close();
    } catch (IOException ex) {
      System.out.println("***(T)ERROR*** The file could not be saved: " + ex);
    }
    
    //saves each individual players' data
    try {
      for (int i = 1; i < allPlayers.length; i++){
        Player writingPlayer = allPlayers[i];
        if (writingPlayer != null){
          //creates a player-specific file
          PrintWriter dataWriter = new PrintWriter(new File("saveFiles/"+writingPlayer.getPlayerName()+".txt"));
          //pet name
          dataWriter.print(writingPlayer.getPet()+"\n");
          //workouts for the current day
          dataWriter.print(writingPlayer.getOneDay(weekDayIndex).getWorkouts()+"\n");
          dataWriter.print("#\n");
          //current week point totals
          dataWriter.print(writingPlayer.getWeekPoints()+"\n");
          dataWriter.close();
        }
      }
    } catch (IOException ex) {
      System.out.println("***(T)ERROR*** The file could not be saved: " + ex);
    }
  }
  
  /***********************************************
    * Main method for testing
    * ********************************************/
  public static void main (String[] args){
    Game tester = new Game();
    System.out.println("Today is day " + tester.getWeekDay() + ".");
  }
  
}