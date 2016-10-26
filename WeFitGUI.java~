/* WeFitGUI.java
 CS230 Final Project
 Lucy Shen & Christina Pollalis
 Date: 12/3/14
 Description: GUI for the whole application, includes 5 panels: 
 about, login, add workout, records, progress. All these panels
 take in a new game except for login (because it is not needed).
 Work division: written together by Lucy and Christina
 */
//-----------

//imports
import java.awt.*;
import javax.swing.*;

public class WeFitGUI {
  
  public static void main (String[]args) {
    JFrame frame = new JFrame ("WeFit GUI");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //creates game that holds consistent over all tabs
    Game mainGame = new Game();
    
    //creates all tabs in a tabbed pane
    JTabbedPane tp = new JTabbedPane();
    tp.addTab ("About", new AboutPanel()); //does not need a game
    tp.addTab ("Login", new LoginPanel(mainGame));
    tp.addTab ("Add Workout", new WorkoutPanel(mainGame));
    tp.addTab ("Records", new RecordsPanel(mainGame));
    tp.addTab ("Progress", new ProgressPanel(mainGame));

    
    //adds the tabbed pane to the frame
    frame.getContentPane().add(tp);
    
    frame.pack();
    frame.setVisible(true);
    
  }
}