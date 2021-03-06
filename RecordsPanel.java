/* RecordsPanel.java
 CS230 Final Project 
 Lucy Shen & Christina Pollalis
 Date: 12/3/14
 Description: This is the records panel. It is where the user can 
 access all of his excercises for the week. It also displays the 
 user's pet and the total points that user has accumulated throughout
 the week. 
 Work division: written together by Lucy and Christina
 */

//imports 
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*; //for border
import javax.swing.JScrollPane; //for scrolling in table
import java.awt.Dimension; //for dimension of table
import java.awt.GridLayout; //for grid layout of table
import javax.swing.table.DefaultTableModel; //table
import java.util.Hashtable; //hashtable
import java.util.LinkedList; //linkedlist
import javax.swing.event.*;
import java.awt.event.*;

public class RecordsPanel extends JPanel {
  
  /***********************************************
    * Button listener
    * ********************************************/
  ButtonListener listener;
  
  /***********************************************
    * Panels
    * ********************************************/
  private JPanel top, middle, bottom;
  
  /***********************************************
    * Panel Components
    * ********************************************/
  //components that go in top
  private JLabel title, filler, directions;
  private JButton refreshButton;
  
  //components that go in middle
  private JPanel leftMiddle, rightMiddle;
  
  //things that go in leftmiddle:
  private JLabel iconLabel;
  
  //things that go in rightmiddle: 
  private JPanel rightNested;
  private JLabel pastMonthTitle;
  
  //things that go in rightNested: 
  private JPanel week1, week2, week3, week4;
  
  //array to store medal image file names
  private String[] medalList;
  
  //things that go in week1 panel: 
  private JLabel week1title, week1medal;
  
  //things that go in week2 panel: 
  private JLabel week2title, week2medal;
  
  //things that go in week3 panel: 
  private JLabel week3title, week3medal;
  
  //things that go in week4 panel: 
  private JLabel week4title, week4medal;
  
  //things that go in bottom: 
  private JPanel leftBottom, rightBottom;
  
  //things that go in leftbottom panel: 
  private JLabel thisWeek;
  private JPanel leftNested;
  
  //things that go in leftNested panel: 
  private JLabel[] weekLabels;
  private JPanel day0Panel, day1Panel, day2Panel, day3Panel, day4Panel, day5Panel, day6Panel;
  
  //things that go in day0Panel: 
  private JLabel day0title, day0number;
  
  //things that go in day1Panel: 
  private JLabel day1title, day1number;
  
  //things that go in day2Panel: 
  private JLabel day2title, day2number;
  
  //things that go in day3Panel: 
  private JLabel day3title, day3number;
  
  //things that go in day4Panel: 
  private JLabel day4title, day4number;
  
  //things that go in day5Panel: 
  private JLabel day5title, day5number;
  
  //things that go in day6Panel: 
  private JLabel day6title, day6number;
  
  //things that go in rightBottom: 
  private JLabel totalTitle;
  private JPanel totalPoints;
  
  //things that go in totalPoints: 
  private JLabel totalPointsTitle;
  
  
  /***********************************************
    * THE GAME
    * ********************************************/
  private Game mainGame;
  private Player loadingPlayer;
  
  
  /***********************************************
    * Panel constructor
    * ********************************************/
  public RecordsPanel(Game inputGame) {
    //creates the game and the player
    mainGame = inputGame;
    loadingPlayer = mainGame.getCurrentPlayer();
    
    //sets the layout for the whole panel
    setLayout(new GridLayout(3,1));
    
    //creates the two colors used in this panel
    Color lightGreen = new Color (135, 206, 150);
    Color whiteBG = new Color (255, 255, 255);
    
    
    //initializing the panels
    top = new JPanel();
    top.setBackground(lightGreen);
    top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
    middle = new JPanel();
    middle.setLayout(new GridLayout(1,2));
    bottom = new JPanel();
    bottom.setLayout(new GridLayout(1,2));
    
    
    //things inside top panel
    title = new JLabel("WeFit");
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.setFont(new Font("New Baskerville", Font.PLAIN, 70)); 
    filler = new JLabel("~~~"); //filler label for layout preferences
    filler.setAlignmentX(Component.CENTER_ALIGNMENT);
    filler.setFont(new Font("New Baskerville", Font.PLAIN, 60));
    directions = new JLabel("Records");
    directions.setAlignmentX(Component.CENTER_ALIGNMENT);
    directions.setFont(new Font("New Baskerville", Font.PLAIN, 17)); 
    refreshButton = new JButton("Refresh");
    refreshButton.setFont(new Font("New Baskerville", Font.PLAIN, 15));
    refreshButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    //adding all components to the top panel
    top.add(title);
    top.add(filler);
    top.add(directions);
    top.add(refreshButton);
    
    //things that go in middle panel
    leftMiddle = new JPanel();
    leftMiddle.setBackground(lightGreen);
    rightMiddle = new JPanel();
    rightMiddle.setBackground(lightGreen);
    
    //adding all components to the middle panel
    middle.add(leftMiddle);
    middle.add(rightMiddle);
    
    //things that go in leftmiddle:
    iconLabel = new JLabel("");
    
    //adding component to the left middle
    leftMiddle.add(iconLabel);
    
    //populate the medalList array
    medalList = new String[5];
    medalList[0] = "firstMedal.png";
    medalList[1] = "secondMedal.png";
    medalList[2] = "thirdMedal.png";
    medalList[3] = "fourthMedal.png";
    medalList[4] = "fifthMedal.png";
    
    //things that go in bottom panel
    leftBottom = new JPanel(); 
    rightBottom = new JPanel();
    leftBottom.setBackground(lightGreen);
    rightBottom.setBackground(lightGreen);
    
    //adding the components to the bottom panel
    bottom.add(leftBottom);
    bottom.add(rightBottom);
    
    //things that go in leftBottom panel
    thisWeek = new JLabel("This week: ");
    leftNested = new JPanel();
    leftNested.setLayout(new GridLayout(1, 7));
    thisWeek.setHorizontalAlignment(JLabel.CENTER);
    leftNested.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    //adding components to the leftBottom panel
    leftBottom.add(thisWeek);
    leftBottom.add(leftNested);
    
    //things that go in leftNested panel
    day0Panel = new JPanel();
    day0Panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
    day0Panel.setBackground(whiteBG);
    
    day1Panel = new JPanel();
    day1Panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
    day1Panel.setBackground(whiteBG);
    
    day2Panel = new JPanel();
    day2Panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
    day2Panel.setBackground(whiteBG);
    
    day3Panel = new JPanel();
    day3Panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
    day3Panel.setBackground(whiteBG);
    
    day4Panel = new JPanel();
    day4Panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
    day4Panel.setBackground(whiteBG);
    
    day5Panel = new JPanel();
    day5Panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
    day5Panel.setBackground(whiteBG);
    
    day6Panel = new JPanel();
    day6Panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
    day6Panel.setBackground(whiteBG);
    
    //adding all the components to the left nested panel
    leftNested.add(day0Panel);
    leftNested.add(day1Panel);
    leftNested.add(day2Panel);
    leftNested.add(day3Panel);
    leftNested.add(day4Panel);
    leftNested.add(day5Panel);
    leftNested.add(day6Panel);
    
    //things that go in day0Panel: 
    day0title = new JLabel("Sun:");
    //System.out.println(currentPlayer.getOneDayPoints(0)); //testing
    day0number = new JLabel(Integer.toString(loadingPlayer.getOneDayPoints(0))); //sunday is 0
    
    day0title.setHorizontalAlignment(JLabel.CENTER);
    day0number.setHorizontalAlignment(JLabel.CENTER);
    
    //adding
    day0Panel.add(day0title);
    day0Panel.add(day0number);
    
    //things that go in day1Panel: 
    day1title = new JLabel("Mon:");
    day1number = new JLabel(Integer.toString(loadingPlayer.getOneDayPoints(1))); //monday is 1
    
    day1title.setHorizontalAlignment(JLabel.CENTER);
    day1number.setHorizontalAlignment(JLabel.CENTER);
    
    //adding
    day1Panel.add(day1title);
    day1Panel.add(day1number);
    
    //things that go in day2Panel: 
    day2title = new JLabel("Tues:");
    day2number = new JLabel(Integer.toString(loadingPlayer.getOneDayPoints(2))); //tuesday is 2
    
    day2title.setHorizontalAlignment(JLabel.CENTER);
    day2number.setHorizontalAlignment(JLabel.CENTER);
    
    //adding
    day2Panel.add(day2title);
    day2Panel.add(day2number);
    
    //things that go in day3Panel: 
    day3title = new JLabel("Wed:");
    day3number = new JLabel(Integer.toString(loadingPlayer.getOneDayPoints(3))); //wednesday is 3
    
    day3title.setHorizontalAlignment(JLabel.CENTER);
    day3number.setHorizontalAlignment(JLabel.CENTER);
    
    day3Panel.add(day3title);
    day3Panel.add(day3number);
    
    //things that go in day4Panel: 
    day4title = new JLabel("Thurs:");
    day4number = new JLabel(Integer.toString(loadingPlayer.getOneDayPoints(4))); //thursday is 4
    
    day4title.setHorizontalAlignment(JLabel.CENTER);
    day4number.setHorizontalAlignment(JLabel.CENTER);
    
    //adding
    day4Panel.add(day4title);
    day4Panel.add(day4number);
    
    //things that go in day5Panel: 
    day5title = new JLabel("Fri:");
    day5number = new JLabel(Integer.toString(loadingPlayer.getOneDayPoints(5))); //friday is 5
    
    day5title.setHorizontalAlignment(JLabel.CENTER);
    day5number.setHorizontalAlignment(JLabel.CENTER);
    
    //adding
    day5Panel.add(day5title);
    day5Panel.add(day5number);
    
    //things that go in day6Panel: 
    day6title = new JLabel("Sat:");
    day6number = new JLabel(Integer.toString(loadingPlayer.getOneDayPoints(6))); //saturday is 6
    
    day6title.setHorizontalAlignment(JLabel.CENTER);
    day6number.setHorizontalAlignment(JLabel.CENTER);
    
    //adding
    day6Panel.add(day6title);
    day6Panel.add(day6number);
    
    //creating the array of number labels
    weekLabels = new JLabel[7]; //SUNDAY IS ALWAYS 0
    weekLabels[0] = day0number;
    weekLabels[1] = day1number;
    weekLabels[2] = day2number;
    weekLabels[3] = day3number;
    weekLabels[4] = day4number;
    weekLabels[5] = day5number;
    weekLabels[6] = day6number;
    
    //things that go in rightBottom:
    totalTitle = new JLabel("Total:");
    totalPoints = new JPanel();
    
    totalPoints.setAlignmentX(Component.CENTER_ALIGNMENT);
    totalPoints.setBackground(whiteBG);
    totalPoints.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
    totalTitle.setHorizontalAlignment(JLabel.CENTER);
    
    //adding
    rightBottom.add(totalTitle);
    rightBottom.add(totalPoints);
    
    //things that go in totalPoints panel: 
    totalPointsTitle = new JLabel(Integer.toString(loadingPlayer.getPlayerPoints()));
    totalPointsTitle.setHorizontalAlignment(JLabel.CENTER);
    
    //create listener
    listener = new ButtonListener();
    
    //add listeners to the buttons: 
    refreshButton.addActionListener(listener);
    
    //adding to the totalPoints panel (the title)
    totalPoints.add(totalPointsTitle);
    
    //adding all the bigger panels
    add(top);
    add(middle);
    add(bottom);
  }
  
  //button listener for the "refresh button"
  private class ButtonListener implements ActionListener {
    
    //gets the name inputted in the text field to add
    public void actionPerformed(ActionEvent event){
      
      Object sourceObject = event.getSource();
      
      //if "refresh" is pressed:
      if (sourceObject == refreshButton) {
        //gets the day index to be used later on
        int todayIndex = mainGame.getWeekDay();
        Player currentPlayer = mainGame.getCurrentPlayer();
        for (int i = 0; i<7; i++){ //loop through the week and set all the labels
          weekLabels[i].setText(Integer.toString(currentPlayer.getOneDayPoints(i)));
        }
        //sets the text for the total amount of points
        totalPointsTitle.setText(Integer.toString(currentPlayer.getPlayerPoints()));
        //System.out.println("currentplayer is: " + currentPlayer + " and pet is " + currentPlayer.getPet()); //testing
        //sets the image as the user's pet
        iconLabel.setIcon(new ImageIcon("images/"+currentPlayer.getPet()+".png"));
      }
    }
    //System.out.println("Current player is: " + mainGame.getCurrentPlayer().getName());
  }
}

