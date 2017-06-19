package Vue;
import Modele.MyShape;
import Modele.Slide;
import Modele.Presentation;
import Modele.Item;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MiniSlidePanel extends JPanel implements MouseListener{
    private Slide slide;
    private Presentation presentation;
    
    public MiniSlidePanel(Slide slide, Presentation presentation) {
        this.slide = slide;
        this.setLayout(null);
        this.presentation = presentation;
        /*for(Component current : this.slide.getItemsMiniSlide()) {
            this.add(current);
        }*/
        for(Item current : this.slide.getItemsCurrentSlide()) {
            if(!(current instanceof MyShape)) {
                this.add(current.itemCopy());
            }
        }
        this.setPreferredSize(new Dimension(950*10/45,600*10/45)); //   /4.5 ratio
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.white);
        addMouseListener(this);
    }

    public Slide getSlide(){
        return this.slide;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getButton() == MouseEvent.BUTTON1){
            for(Slide current : this.presentation.getSlides()){
                current.setHighlight(false);
            }
            this.slide.setHighlight(true);
            this.presentation.setCurrentSlideModel(slide);
            this.presentation.notifyObserver();
        }
        else if(me.getButton() == MouseEvent.BUTTON3){
            PopUpMiniatures menu = new PopUpMiniatures(this.presentation, this.slide);
            menu.show(this, me.getX(), me.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
    
    public void paintComponent(Graphics g){ //dessine sur le JPanel principal
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        //ajout de nos shapes
        for(MyShape current : this.slide.getShapesTab()) { //on ajoute nos shapes au JPanel principal
            Item item = current.itemCopy();
            MyShape shape = (MyShape)item;
            if(shape.getShapeBackground() != null) {  //on met la couleur correspondant au contour 
                g2d.setPaint(shape.getBackgroundColor());
                g2d.fill(shape.getShapeBackground()); 
                g2d.draw(shape.getShapeBackground());
            }
            
            if(current.isSelected()) {  //si la shape est sélectionnée, on change sa couleur en dark (l'intérieur)
                Color selectedColor = shape.getForegroundColor();
                selectedColor = selectedColor.brighter();
                g2d.setPaint(shape.getForegroundColor().darker());
            }
            else {
                g2d.setPaint(shape.getForegroundColor());
            }
            g2d.fill(shape.getShapeForeground());
            g2d.draw(shape.getShapeForeground());
        }
    }
}
