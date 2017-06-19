package Controleur;
import Modele.Presentation;
import Vue.MainFrame;

public class main{

    public static void main(String[] args) {
        Presentation presentation = new Presentation();
        MainFrame mainFrame = new MainFrame(presentation);
    }   
}
