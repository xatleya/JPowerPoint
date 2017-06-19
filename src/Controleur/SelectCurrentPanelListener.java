package Controleur;

import Modele.Item;
import Modele.Label;
import Modele.Presentation;
import Vue.CurrentSlideView;
import Modele.Resizable;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;

public class SelectCurrentPanelListener extends MouseInputAdapter{
    private Presentation presentation;
    
    public SelectCurrentPanelListener(Presentation presentation) {
        this.presentation = presentation;
    }
    
    @Override
    public void mousePressed(MouseEvent me) {   
        CurrentSlideView currentSlide = (CurrentSlideView)me.getSource();
        for(Item current : currentSlide.getSlide().getItemsCurrentSlide()) {
            current.setSelected(false);
        }
        this.presentation.notifyObserver();
        for(int i=0;i<=currentSlide.getSlide().getItemsCurrentSlide().size()-1;i++) {
                Component current = currentSlide.getSlide().getItemsCurrentSlide().get(i);
                if(current instanceof Resizable) {
                    Resizable zr = (Resizable)current;
                    
                    if(zr.isSelected() && zr.getImage() == null){
                        if(!zr.getTextZone().getText().isEmpty()) {               
                            zr.setSelected(false);
                            this.presentation.notifyObserver();  
                        }
                        else {
                            currentSlide.getSlide().getItemsCurrentSlide().remove(zr);
                            this.presentation.notifyObserver();
                        }
                    }
                }
            }

    }
}
