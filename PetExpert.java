/* PetExpert.java
 CS230 Final Project 
 Lucy Shen & Christina Pollalis
 Date: 12/3/14
 Description: This is the class that hold the binary decision tree. 
 The lab (Stella) helped us build it (it still uses some of the same code).
 It sets up the tree and then uses dialog boxes to get answers from the user. 
 (It is used in the Login Panel in our GUI).
 Work division: written mostly by Christina
 */

//imports
import java.util.Scanner;  // for reading from keyboard
import javafoundations.*; // for the LinkedBinaryTree and all
import javax.swing.JOptionPane;

public class PetExpert {
  
  /***********************************************
    * Instance Variables
    * ********************************************/
  private LinkedBinaryTree<String> theTree; //setting up the tree
  
  /***********************************************
    * Constructor - creates the tree
    * ********************************************/
  public PetExpert(){
    String e1 = "Do you like big animals?";
    String e2 = "Do you like animals with sharp teeth?";
    String e3 = "Do you like dangerous animals?";
    String e4 = "Do you like animals that swim?";
    String e5 = "Do you like animals with tails?";
    String e6 = "Do you like tall and skinny animals?";
    String e7 = "Do you like red animals?";
    String e8 = "Bird";
    String e9 = "Fish";
    String e10 = "Frog";
    String e11 = "Rat";
    String e12 = "Hippo";
    String e13 = "Giraffe";
    String e14 = "Lion";
    String e15 = "Fox";
    
    //assigns all the strings above to nodes on the trees (uses BTNODE)
    LinkedBinaryTree<String> n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15;
    n1 = new LinkedBinaryTree<String>(e4, 
                                      new LinkedBinaryTree<String>(e8), 
                                      new LinkedBinaryTree<String>(e9));
    n2 = new LinkedBinaryTree<String>(e5, 
                                      new LinkedBinaryTree<String>(e10), 
                                      new LinkedBinaryTree<String>(e11));
    n3 = new LinkedBinaryTree<String>(e6, 
                                      new LinkedBinaryTree<String>(e12), 
                                      new LinkedBinaryTree<String>(e13));
    n4 = new LinkedBinaryTree<String>(e7, 
                                      new LinkedBinaryTree<String>(e14), 
                                      new LinkedBinaryTree<String>(e15));
    n5 = new LinkedBinaryTree<String>(e2, n1, n2);
    n6 = new LinkedBinaryTree<String>(e3, n3, n4);
    
    theTree = new LinkedBinaryTree<String>(e1, n5, n6);
  }
  
  /***********************************************
    * Instance Methods
    * ********************************************/
  
  
 /******************************************************************
  * This method suggests a pet by using dialog boxes and posing questions
   to the user by using dialog boxes. It needs not be void so that it 
   returns the pet and can be stored within the GUI as well.
  *****************************************************************/
  public String suggestPet() { 
    
    //the tree
    LinkedBinaryTree current = theTree;
    
    while (current.size() > 1) { //not at a leaf (where the answer is stored)
      //System.out.println(current.getRootElement()); //testing
      
      //int?
      int answer = JOptionPane.showConfirmDialog(null, current.getRootElement(), "Choose your pet!", JOptionPane.YES_NO_OPTION);
      //chooses no, get left 
      if (answer == JOptionPane.NO_OPTION) {
        current = current.getLeft();
        //chooses yes, get right 
      } else if (answer == JOptionPane.YES_OPTION) { 
        current = current.getRight();
        
      } else { //exits if press cancel
        System.exit(0);
      }
    }
    //System.out.println(current);
    JOptionPane.showMessageDialog(null, "Your pet is a: " + current); //lets the user know what pet he/she got
    
    return current.toString(); //returns the string representation of the pet
  }
  
    
  /***********************************************
    * Main method for testing
    * ********************************************/
  public static void main (String[] args ) {
    //for testing purposes
    PetExpert expert = new PetExpert();
    expert.suggestPet();
  }
  
} 
