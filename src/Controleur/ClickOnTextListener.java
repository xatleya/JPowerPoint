package Controleur;

import Modele.Item;
import Modele.Label;
import Modele.Presentation;
import Vue.CurrentSlideView;
import Modele.Resizable;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import static java.awt.event.MouseEvent.BUTTON1;
import java.awt.event.MouseListener;
import java.io.Serializable;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextPane;


public class ClickOnTextListener implements MouseListener, Serializable{
    Resizable zr;
    Presentation app;
    
    public ClickOnTextListener(Resizable zr, Presentation app) {
        this.zr = zr;
        this.app = app;
    }

    
    @Override
    public void mouseClicked(MouseEvent me) {
        
    }
    

    @Override
    public void mousePressed(MouseEvent me) {
        if(me.getButton() == BUTTON1 && zr.getBorder() == null){
            zr.setBorder(BorderFactory.createTitledBorder(""));
            CurrentSlideView currentSlide = zr.getCurrentSlideView();
            for(Item current : currentSlide.getSlide().getItemsCurrentSlide()) {
                current.setSelected(false);
            }
            for(int i = currentSlide.getSlide().getItemsCurrentSlide().size()-1; i>=0; i--){
                Component previous = currentSlide.getSlide().getItemsCurrentSlide().get(i);
                if(previous instanceof Resizable) {
                    Resizable zr2 = (Resizable)previous;
                    if(zr2.getImage() == null){
                        if(zr2.getTextZone() != (JTextPane)me.getSource()){
                            zr2.setSelected(false);
                            
                        } 
                        Font font = zr2.getTextZone().getFont();
                        Label labelOnMiniSlide = new Label(zr2.getTextZone().getText());
                        labelOnMiniSlide.getMyLabel().setSize(zr2.getSize().height*10/45, zr2.getSize().width*10/45);
                        labelOnMiniSlide.setSize(zr2.getSize().height, zr2.getSize().width*10/45);
                        labelOnMiniSlide.setLocation(zr2.getX()*10/45, zr2.getY()*10/45);
                        labelOnMiniSlide.getMyLabel().setFont(new Font("Serif", font.getStyle(), font.getSize()*20/45));
                        zr2.setText(labelOnMiniSlide);
                        zr.setSelected(true);
                        this.app.notifyObserver();
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
  
    }

    @Override
    public void mouseExited(MouseEvent me) {
       
    }
    
}
