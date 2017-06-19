package Controleur;

import Modele.Presentation;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BinaryFileTools {
    public static void SavePresentation(String fileName, Presentation presentation){
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(presentation);
            oos.close();
            System.out.println("Save Success");
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Presentation LoadPresentation(String fileName) {
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(file);
            Presentation presentation = (Presentation) ois.readObject();           
            ois.close();
            System.out.println("Load Success");
            return presentation;
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            return null;
        } 
        catch (IOException e) {
            e.printStackTrace();
            return null;
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
