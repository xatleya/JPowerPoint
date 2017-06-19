package Tools;

import Modele.Item;
import Modele.Label;
import Modele.Presentation;
import Vue.CurrentSlideView;
import Modele.Resizable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import static java.awt.event.MouseEvent.BUTTON1;
import java.io.Serializable;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;


public class TextToolListener extends MouseInputAdapter {
    private Presentation presentation;
    
    public TextToolListener(Presentation presentation) {
        this.presentation = presentation;
    }
    
    @Override
    public void mousePressed(MouseEvent me) {   
        if(me.getButton() == BUTTON1){
            CurrentSlideView currentSlide = (CurrentSlideView)me.getSource();
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
                            currentSlide.remove(zr);
                            currentSlide.getSlide().getItemsCurrentSlide().remove(zr);
                            this.presentation.notifyObserver();
                        }
                        return;
                    }
                }
            }


            Resizable zr = new Resizable(5, 20, me.getX(), me.getY(), currentSlide, null, presentation);
            zr.setBorder(BorderFactory.createTitledBorder(""));
            currentSlide.getSlide().addCurrentSlideComponent(zr);

            currentSlide.add(zr);
            currentSlide.add(zr.getDragTopLeft());
            currentSlide.add(zr.getDragTopRight());
            currentSlide.add(zr.getDragBotLeft());
            currentSlide.add(zr.getDragBotRight());
            currentSlide.setComponentZOrder(zr, 1);
            currentSlide.setComponentZOrder(zr.getDragTopLeft(), 0);
            currentSlide.setComponentZOrder(zr.getDragTopRight(), 0);
            currentSlide.setComponentZOrder(zr.getDragBotLeft(), 0);
            currentSlide.setComponentZOrder(zr.getDragBotRight(), 0);
            this.presentation.notifyObserver();
        }
    }
    
}
