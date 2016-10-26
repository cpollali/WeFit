/* AboutPanel.java
 CS230 Final Project 
 Lucy Shen & Christina Pollalis
 Date: 12/3/14
 Description: This is the first panel in our GUI. It displays
 information about the game and gives credit to the artists, whose
 work we used.
 Work division: written mostly by Christina
 */

//imports
import java.awt.*;
import javax.swing.*;


public class AboutPanel extends JPanel {
  
  private Image background; 
  
  //initialize panels
  //largeest three panels
  private JPanel top, middle, bottom;
  
  //components that go in top
  private JLabel title, minTitle;
  
  //components that go in middle
  private JLabel text1, text2, text3, text4, text5, text6, text7;
  
  //components that go in bottom
  private ImageIcon pet1, pet2, pet3, pet4, pet5, pet6, pet7, pet8;
  private JLabel pet1label, pet2label, pet3label, pet4label, pet5label, pet6label, pet7label, pet8label;  
  
  /***********************************************
    * Panel constructor
    * ********************************************/
  public AboutPanel() {
    
    setLayout(new GridLayout(3,1));
    
    //initializing the panels + setting color
    Color lilac = new Color (209, 193, 230);
    top = new JPanel();
    top.setBackground(lilac);
    
    middle = new JPanel();
    middle.setBackground(lilac);
    
    bottom = new JPanel();
    bottom.setBackground(lilac);
    
    //setting layouts for panels
    top.setLayout(new BoxLayout(top,BoxLayout.Y_AXIS));
    middle.setLayout(new BoxLayout(middle,BoxLayout.Y_AXIS));
    bottom.setLayout(new GridLayout(1,5));
    
    //initialize components
    
    //components for top
    title = new JLabel("~~Welcome to WeFit~~");
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.setFont(new Font("New Baskerville", Font.PLAIN, 70)); 
    minTitle = new JLabel("The next killer app brought to you by Lucy and Christina!"); //filler label for layout preferences
    minTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    minTitle.setFont(new Font("New Baskerville", Font.PLAIN, 30));
    
    //components for middle
    text1 = new JLabel("WeFit is a health and fitness application targeted at grade-school children to encourage healthy lifestyle choices.");
    text2 = new JLabel("Users can log in as themselves and compete against friends to see who is being more fit. Progress is measured through");
    text3 = new JLabel("user inputs of their exercise for the day, and the weekly progress is visualized with progress bars that will show how close");
    text4 = new JLabel("each user is to their weekly goal.");
    text1.setAlignmentX(Component.CENTER_ALIGNMENT);
    text1.setFont(new Font("New Baskerville", Font.PLAIN, 17));
    text2.setAlignmentX(Component.CENTER_ALIGNMENT);
    text2.setFont(new Font("New Baskerville", Font.PLAIN, 17));
    text3.setAlignmentX(Component.CENTER_ALIGNMENT);
    text3.setFont(new Font("New Baskerville", Font.PLAIN, 17));
    text4.setAlignmentX(Component.CENTER_ALIGNMENT);
    text4.setFont(new Font("New Baskerville", Font.PLAIN, 17));
    text5 = new JLabel("    "); //spacer
    text5.setFont(new Font("New Baskerville", Font.PLAIN, 50));
    text6 = new JLabel("All images are from the Noun Project.");
    text6.setAlignmentX(Component.CENTER_ALIGNMENT);
    text6.setFont(new Font("New Baskerville", Font.PLAIN, 17));
    text7 = new JLabel("Artists include: Cecilia Morales, Jake Dunham, Claudio Gomboli, and Douglas Gordon.");
    text7.setAlignmentX(Component.CENTER_ALIGNMENT);
    text7.setFont(new Font("New Baskerville", Font.PLAIN, 14));
    
    
    //components for bototm
    pet1 = new ImageIcon("images/Bird.png");
    pet2 = new ImageIcon("images/Lion.png");
    pet3 = new ImageIcon("images/Fish.png");
    pet4 = new ImageIcon("images/Rat.png");
    pet5 = new ImageIcon("images/Giraffe.png");
    pet6 = new ImageIcon("images/Hippo.png");
    pet7 = new ImageIcon("images/Fox.png");
    pet8 = new ImageIcon("images/Frog.png");
    
    pet1label = new JLabel(pet1);
    pet2label = new JLabel(pet2);
    pet3label = new JLabel(pet3);
    pet4label = new JLabel(pet4);
    pet5label = new JLabel(pet5);
    pet6label = new JLabel(pet6);
    pet7label = new JLabel(pet7);
    pet8label = new JLabel(pet8);
    
    //add components
    //add components in top
    top.add(title);
    top.add(minTitle);
    
    //add components in middle
    middle.add(text1);
    middle.add(text2);
    middle.add(text3);
    middle.add(text4);
    middle.add(text5);
    middle.add(text6);
    middle.add(text7);
    
    //add components in bottom
    bottom.add(pet1label);
    bottom.add(pet2label);
    bottom.add(pet3label);
    bottom.add(pet4label);
    bottom.add(pet5label);
    bottom.add(pet6label);
    bottom.add(pet7label);
    bottom.add(pet8label);
    
    
    //adding panels
    add(top);
    add(middle);
    add(bottom);
    
  }
  
  
  
}