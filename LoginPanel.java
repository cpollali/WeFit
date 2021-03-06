/* LoginPanel.java
 CS230 Final Project 
 Lucy Shen & Christina Pollalis
 Date: 12/3/14
 Description: This is the login panel. The user clicks on a button that is "Empty"
 to create a user. Or, if he/she has already created a user, they can click on 
 the specific button and log in to put in more workouts or to see their progress/records etc.
 Work division: written mostly by Christina
 */

import java.awt.*;
import javax.swing.*;
import java.util.LinkedList; //linkedlist
import javax.swing.event.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {
  
  private Image background; 
  
  /***********************************************
    * Panels
    * ********************************************/
  //largest three panels
  private JPanel top, middle;
  
  /***********************************************
    * Components in panels
    * ********************************************/
  //components that go in top
  private JLabel title, filler, directions;
  
  //components that go in middle
  private JButton user1, user2, user3, user4, user5;
  private JButton[] userButtons;
  
  /***********************************************
    * THE GAME
    * ********************************************/
  private Game mainGame;
  private PetExpert petSuggestion;
  
  /***********************************************
    * will be intialized by the action listeners
    * ********************************************/
  private String password;
  private final int NOT_FOUND = -1;
  private int userIndex = NOT_FOUND; //default: no user
  private String userName;
  private Player buttonPlayer;
  
  /***********************************************
    * Listeners
    * ********************************************/
  ButtonListener listener;
  
  /***********************************************
    * Panel constructor
    * ********************************************/
  public LoginPanel(Game inputGame) {
    mainGame = inputGame;
    petSuggestion = new PetExpert();
    
    setLayout(new GridLayout(2,1));
    
    //initializing the panels
    Color lightRed = new Color (255, 191, 191);
    top = new JPanel();
    top.setBackground(lightRed);
    
    middle = new JPanel();
    middle.setBackground(lightRed);
    
    //setting the layouts for the two panels
    top.setLayout(new BoxLayout(top,BoxLayout.Y_AXIS));
    middle.setLayout(new GridLayout(1,1));
    
    //initialize components
    
    //components for top
    title = new JLabel("WeFit");
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.setFont(new Font("New Baskerville", Font.PLAIN, 70)); 
    filler = new JLabel("~~~"); //filler label for layout preferences
    filler.setAlignmentX(Component.CENTER_ALIGNMENT);
    filler.setFont(new Font("New Baskerville", Font.PLAIN, 60));
    directions = new JLabel("Which user are you? ");
    directions.setAlignmentX(Component.CENTER_ALIGNMENT);
    directions.setFont(new Font("New Baskerville", Font.PLAIN, 17)); 
    
    //components for middle
    //first
    buttonPlayer = mainGame.getOnePlayer(1);
    if (buttonPlayer.getPet().equals("pet")){ //checks to see if the user has been created yet
      user1 = new JButton(buttonPlayer.getPlayerName()); //if not, just puts "Empty" on the button
      user1.setPreferredSize(new Dimension(100,100));
    } else {
      //user has been created so it places his/her name and his/her pet on the appropriate button
      user1 = new JButton(buttonPlayer.getPlayerName()); 
      user1.setIcon(new ImageIcon("images/"+buttonPlayer.getPet()+".png"));
      user1.setPreferredSize(new Dimension(100,100));
    }
    
    //second --same thing as for first
    buttonPlayer = mainGame.getOnePlayer(2);
    if (buttonPlayer.getPet().equals("pet")){
      user2 = new JButton(buttonPlayer.getPlayerName());
      user2.setPreferredSize(new Dimension(100,100));
    } else {
      user2 = new JButton(buttonPlayer.getPlayerName());
      user2.setIcon(new ImageIcon("images/"+buttonPlayer.getPet()+".png"));
      user2.setPreferredSize(new Dimension(100,100));
    }
    
    //third --same thing as for first
    buttonPlayer = mainGame.getOnePlayer(3);
    if (buttonPlayer.getPet().equals("pet")){
      user3 = new JButton(buttonPlayer.getPlayerName());
      user3.setPreferredSize(new Dimension(100,100));
    } else {
      user3 = new JButton(buttonPlayer.getPlayerName());
      user3.setIcon(new ImageIcon("images/"+buttonPlayer.getPet()+".png"));
      user3.setPreferredSize(new Dimension(100,100));
    }
    
    //fourth --same thing as for first
    Player buttonPlayer = mainGame.getOnePlayer(4);
    if (buttonPlayer.getPet().equals("pet")){
      user4 = new JButton(buttonPlayer.getPlayerName());
      user4.setPreferredSize(new Dimension(100,100));
    } else {
      user4 = new JButton(buttonPlayer.getPlayerName());
      user4.setIcon(new ImageIcon("images/"+buttonPlayer.getPet()+".png"));
      user4.setPreferredSize(new Dimension(100,100));
    }
    
    //fifth --same thing as for first
    buttonPlayer = mainGame.getOnePlayer(5);
    if (buttonPlayer.getPet().equals("pet")){
      user5 = new JButton(buttonPlayer.getPlayerName());
      user5.setPreferredSize(new Dimension(100,100));
    } else {
      user5 = new JButton(buttonPlayer.getPlayerName());
      user5.setIcon(new ImageIcon("images/"+buttonPlayer.getPet()+".png"));
      user5.setPreferredSize(new Dimension(100,100));
    }
    
    //creates an array of buttons (so that we can get the index number and determine the source
    //later on
    userButtons = new JButton[5];
    userButtons[0] = user1;
    userButtons[1] = user2;
    userButtons[2] = user3;
    userButtons[3] = user4;
    userButtons[4] = user5;
    
    //add components
    //add components in top
    top.add(title);
    top.add(filler);
    top.add(directions);
    
    //add components in middle
    middle.add(user1);
    middle.add(user2);
    middle.add(user3);
    middle.add(user4);
    middle.add(user5);
    
    //adding panels
    add(top);
    add(middle);
    
    //create listeners
    listener = new ButtonListener();
    
    //add listeners to the buttons: 
    user1.addActionListener(listener);
    user2.addActionListener(listener);
    user3.addActionListener(listener);
    user4.addActionListener(listener);
    user5.addActionListener(listener);
  }
  
  private class ButtonListener implements ActionListener {
    
    //if the user hits "ok" on a dialogue box without entering anything,
    //the input is stored as an empty string. All of the little while loops
    //here were added to safeguard against that and make sure the user
    //enters something, or they've have an account with an empty name and empty password.
    
    //gets the name inputted in the text field to add
    public void actionPerformed(ActionEvent event){
      
      Object sourceObject = event.getSource();
      
      //if a user is pressed: 
      if (sourceObject == user1 || sourceObject == user2 || sourceObject == user3||sourceObject == user4||sourceObject == user5) {
        JButton oneButton = (JButton)sourceObject;
        for (int i = 0; i < 5; i++){
          if (userButtons[i] == oneButton){
            userIndex = i+1;
            System.out.println("User index is: " + userIndex);
          }
        }
        
        try { //catches the null pointer exception if the player ever hits cancel and breaks out of the rest of the code
          
          //user does not exist yet
          if (mainGame.getOnePlayer(userIndex).getPlayerName().equals("Empty")){
            
            userName = JOptionPane.showInputDialog(null,"Welcome to WeFit! Please enter your name: ");
            
            //if no username is entered, prompt again
            while (userName.equals("")) {
              userName = JOptionPane.showInputDialog(null,"Sorry, please enter a valid username: ");
            }
            
            while (mainGame.nameTaken(userName)) {
              
              userName = JOptionPane.showInputDialog(null,"Sorry, that name is taken. Please enter another name: ");
              
              while (userName.equals("")) {
                userName = JOptionPane.showInputDialog(null,"Please input a valid username: ");
              }
              
            }
            
            password = JOptionPane.showInputDialog(null,"Please enter your password: ");
            
            //if no password is entered, prompt again
            while (password.equals("")){
              password = JOptionPane.showInputDialog(null,"Sorry, please enter a valid password: ");
            }
            
            //System.out.println("Submitted username is: " + userName + " password is: " + password); //testing
            //adds the player based on the gotten username and password
            //only run the following code if the password and userName are not null
            mainGame.addPlayer(userIndex, userName, password);
            mainGame.setCurrentPlayer(userIndex);
            //sets the text on the button to display the username
            oneButton.setText(userName);
            //prompts the decision tree so that the user can "choose" its pet
            String petName = petSuggestion.suggestPet();
            //sets the button to display the pet
            oneButton.setIcon(new ImageIcon("images/"+petName+".png"));
            //sets the pet to that user
            mainGame.getCurrentPlayer().setPet(petName);
            
          } else { //user has already been created:
            
            //check if user has already logged in
            if (!mainGame.getOnePlayer(userIndex).toString().equals(mainGame.getCurrentPlayer().toString())){ //if the user hasn't logged in yet
              //asks the user to input his/her password
              password = JOptionPane.showInputDialog(null, "Welcome " + mainGame.getOnePlayer(userIndex) + "! \nPlease enter your password: "); 
              
              //if the password is not null
              while (!mainGame.getOnePlayer(userIndex).isPassword(password)) { //password is incorrect/does not match
                password = JOptionPane.showInputDialog(null, "Password Incorrect. Please re-enter.");
              }
              
              //if the password is correct and not cancelled
              if (password != null){ //if the player hit cancel, this chunk of code would not run
                if (mainGame.getOnePlayer(userIndex).isPassword(password)) { 
                  mainGame.setCurrentPlayer(userIndex);
                  JOptionPane.showMessageDialog(null, "Login Successful! Please process to the Workout tab.");
                }
              }
              
            } else {
              //if the user is already logged in
              JOptionPane.showMessageDialog(null, "You have already logged in!");
            }
            
          }
        } catch (NullPointerException ex){
          System.out.println("Player hit cancel.");
        }
      } // end of pressing user button
    } // end of actionPerformed
  } //end of button listener
  
}



