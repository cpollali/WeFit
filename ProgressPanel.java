/* ProgressPanel.java
 CS230 Final Project 
 Lucy Shen & Christina Pollalis
 Date: 12/3/14
 Description: This is the progress panel, where the user can see 
 the percentatge that he/she has completing their weekly goal (currently 
 set at 100points). It also displays at what percentage the rest of the 
 players are at. 
 Work division: written mostly by Christina
 */

import java.awt.*;
import javax.swing.*;
import java.util.LinkedList; //linkedlist
import javax.swing.event.*;
import java.awt.event.*;

public class ProgressPanel extends JPanel {
  
  /***********************************************
    * Constants
    * ********************************************/
  final private int goal = 100; //goal for a week
  
  /***********************************************
    * Panels
    * ********************************************/
  
  //largeest three panels
  private JPanel top, bottom, bottomLeft, bottomRight;
  
  /***********************************************
    * Components within Panels
    * ********************************************/
  
  //components that go in top
  private JLabel title, filler, directions;
  
  //components that go in bottom
  private JPanel[] progressBars;
  
  //components that go in bottomLeft
  private JPanel user1, user2, user3, user4, user5; //need one more user?
  
  //things that go in the user panels
  private JLabel user1title, user2title, user3title, user4title, user5title;
  private JProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5;
  
  //components that go in bottomRight
  private JButton refreshButton, saveGameButton;
  
  /***********************************************
    * THE GAME
    * ********************************************/
  private Game mainGame;
  
  /***********************************************
    * Listeners
    * ********************************************/
  ButtonListener listener;
  
  /***********************************************
    * Panel constructor
    * ********************************************/
  public ProgressPanel (Game inputGame) {
    mainGame = inputGame;
    //currentPlayer = mainGame.getCurrentPlayer();
    
    setLayout(new GridLayout(2,1));
    
    //initializing the panels
    top = new JPanel();
    bottom = new JPanel();
    bottomLeft = new JPanel();
    bottomRight = new JPanel();
    
    //setting the layouts
    Color lightRed = new Color (200, 250, 200);
    top.setLayout(new BoxLayout(top,BoxLayout.Y_AXIS));
    top.setBackground(lightRed);
    bottom.setLayout(new GridLayout(1,2));
    bottom.setBackground(lightRed);
    bottomLeft.setLayout(new GridLayout(5,1));
    bottomLeft.setBackground(lightRed);
    
    
    //initialize components
    
    //components for top
    title = new JLabel("WeFit");
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.setFont(new Font("New Baskerville", Font.PLAIN, 70)); 
    filler = new JLabel("~~~"); //filler label for layout preferences
    filler.setAlignmentX(Component.CENTER_ALIGNMENT);
    filler.setFont(new Font("New Baskerville", Font.PLAIN, 60));
    directions = new JLabel("See all user progress below!");
    directions.setAlignmentX(Component.CENTER_ALIGNMENT);
    directions.setFont(new Font("New Baskerville", Font.PLAIN, 17)); 
    
    //components for bottom
    progressBars = new JPanel[5];
    
    //all the user panels:
    user1 = new JPanel();
    user1.setLayout(new GridLayout(1,2));
    user2 = new JPanel();
    user2.setLayout(new GridLayout(1,2));
    user3 = new JPanel();
    user3.setLayout(new GridLayout(1,2));
    user4 = new JPanel();
    user4.setLayout(new GridLayout(1,2));
    user5 = new JPanel();
    user5.setLayout(new GridLayout(1,2));
    
    
    //things that go in user1
    user1title = new JLabel(mainGame.getOnePlayer(1).getPlayerName());
    progressBar1 = new JProgressBar(0, 100); //default 100
    progressBar1.setValue(0);
    progressBar1.setStringPainted(true);
    
    //things that go in user2
    user2title = new JLabel(mainGame.getOnePlayer(2).getPlayerName());
    progressBar2 = new JProgressBar(0, 100);
    progressBar2.setValue(0);
    progressBar2.setStringPainted(true);
    
    //things that go in user3
    user3title = new JLabel(mainGame.getOnePlayer(3).getPlayerName());
    progressBar3 = new JProgressBar(0, 100);
    progressBar3.setValue(0);
    progressBar3.setStringPainted(true);
    
    //things that go in user4
    user4title = new JLabel(mainGame.getOnePlayer(4).getPlayerName());
    progressBar4 = new JProgressBar(0, 100);
    progressBar4.setValue(0);
    progressBar4.setStringPainted(true);
    
    //things that go in user5
    user5title = new JLabel(mainGame.getOnePlayer(5).getPlayerName());
    progressBar5 = new JProgressBar(0, 100);
    progressBar5.setValue(0);
    progressBar5.setStringPainted(true);
    
    //populate the array
    progressBars[0] = user1;
    progressBars[1] = user2;
    progressBars[2] = user3;
    progressBars[3] = user4;
    progressBars[4] = user5;
    
    //components that go in bottomRight
    refreshButton = new JButton("Refresh");
    saveGameButton = new JButton("Save Game");
    
    //add components
    //add components in top
    top.add(title);
    top.add(filler);
    top.add(directions);
    
    //add components to the users
    user1.add(user1title);
    user1.add(progressBar1);
    user2.add(user2title);
    user2.add(progressBar2);
    user3.add(user3title);
    user3.add(progressBar3);
    user4.add(user4title);
    user4.add(progressBar4);
    user5.add(user5title);
    user5.add(progressBar5);
    
    //add components in bottom
    bottomLeft.add(user1);
    bottomLeft.add(user2);
    bottomLeft.add(user3);
    bottomLeft.add(user4);
    bottomLeft.add(user5);
    bottomRight.add(refreshButton);
    bottomRight.add(saveGameButton);
    bottom.add(bottomLeft);
    bottom.add(bottomRight);
    
    //adding panels
    add(top);
    add(bottom);
    
    //create listeners
    listener = new ButtonListener();
    
    //adding the listener
    refreshButton.addActionListener(listener);
    saveGameButton.addActionListener(listener);
  }
  
  private class ButtonListener implements ActionListener {
    
    //button listener for the refresh button and the saveGameButton: 
    public void actionPerformed(ActionEvent event){
      
      Object sourceObject = event.getSource();
      
      //if "refresh" is pressed:
      if (sourceObject == refreshButton)  {
        //update progress bars
        progressBar1.setValue(mainGame.getOnePlayer(1).getPlayerPoints()); 
        progressBar2.setValue(mainGame.getOnePlayer(2).getPlayerPoints());
        progressBar3.setValue(mainGame.getOnePlayer(3).getPlayerPoints());
        progressBar4.setValue(mainGame.getOnePlayer(4).getPlayerPoints());
        progressBar5.setValue(mainGame.getOnePlayer(5).getPlayerPoints());
        
        //update name labels
        user1title.setText(mainGame.getOnePlayer(1).getPlayerName());
        user2title.setText(mainGame.getOnePlayer(2).getPlayerName());
        user3title.setText(mainGame.getOnePlayer(3).getPlayerName());
        user4title.setText(mainGame.getOnePlayer(4).getPlayerName());
        user5title.setText(mainGame.getOnePlayer(5).getPlayerName());
      } 
      //if the save button is pressed
      else if (sourceObject == saveGameButton){
        mainGame.saveGame(); //calls method in game to save the game
      }
    }
    //System.out.println("Current player is: " + mainGame.getCurrentPlayer().getName()); //testing
  }
}

