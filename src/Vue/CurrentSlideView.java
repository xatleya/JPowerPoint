package Vue;
import Modele.Resizable;
import Modele.MyShape;
import Modele.Slide;
import Modele.Presentation;
import Modele.Item;
import Observe.Observer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class CurrentSlideView extends JPanel implements Observer{
    private Slide slide;
    private Presentation presentation;
    private MainFrame mainFrame;
    
    public CurrentSlideView(Slide slide, Presentation presentation, MainFrame mainFrame) {
        this.slide = slide;
        this.presentation = presentation;
        this.mainFrame = mainFrame;
        this.setPreferredSize(new Dimension(950,600));
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.white);
        for(Component current : this.slide.getItemsCurrentSlide()) { 
            this.add(current);
        }
        this.revalidate();
        this.repaint();
        
        //CREATION DES RACOURCIES CLAVIERS
        
        //DELETE
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
        this.getActionMap().put("delete", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {   //quand on clique sur suppr
                for(Item current : slide.getItemsCurrentSlide()) {
                    if(current.isSelected()) {
                        slide.getItemsCurrentSlide().remove(current);
                        presentation.notifyObserver();
                        break;
                    }
                }
            }    
        });
    }

    public void setSlide(Slide slide) {
        this.slide = slide;
    }

    public Slide getSlide() {
        return slide;
    }

    public Presentation getPresentation() {
        return presentation;
    }
    
    public void removeListeners() { //fonction qui permet de supprimer les listeners d'un JPanel
        for(MouseListener current : this.getMouseListeners()) { //supprime les mouseListeners du JPanel principal
            this.removeMouseListener(current);
        }
        for(Item current : this.slide.getItemsCurrentSlide()) {    //supprime les MouseListeners des JPanel associés aux shapes
                if(current instanceof MyShape) {
                    JPanel panel = (JPanel)current;
                    for(MouseListener ml : panel.getMouseListeners()) {
                        panel.removeMouseListener(ml);
                    }
                    for(MouseMotionListener mml : panel.getMouseMotionListeners()) {    //supprime les MouseMotionListeners des JPanel associés aux shapes
                        panel.removeMouseMotionListener(mml);
                    }
                    current.setSelected(false);
                }
            }
    }
    
    public void paintComponent(Graphics g){ //dessine sur le JPanel principal
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        //ajout des panels de nos shapes au JPanel principal
        int i = this.slide.getShapesTab().size()-1;
        while(i!=-1) {  //on ajoute les JPanel dans le désordre car le 1er panel ajouté est celui pris en compte en cas de collision
            MyShape current = this.slide.getShapesTab().get(i);
            
            if(current.isSelected()) {  //si une forme est sélectionnée, on vérifie si elle a bougée
                current.setLocation(new Point(current.getxOrigin(), current.getyOrigin()));
                current.setBackground(new Color(0,0,0,0));
            }
            else {
                this.remove(current);   //si elle n'est pas séléctionnée, on la supprime et la recrée
                current.setBounds(current.getxOrigin(), current.getyOrigin(), (int)current.getShapeForeground().getBounds2D().getWidth(), (int)current.getShapeForeground().getBounds2D().getHeight());
                this.add(current);
            }
            i--;
        }
        
        //ajout de nos shapes
        for(MyShape current : this.slide.getShapesTab()) { //on ajoute nos shapes au JPanel principal
            if(current.getShapeBackground() != null) {  //on met la couleur correspondant au contour 
                g2d.setPaint(current.getBackgroundColor());
                g2d.fill(current.getShapeBackground()); 
                g2d.draw(current.getShapeBackground());
            }
            
            if(current.isSelected()) {  //si la shape est sélectionnée, on change sa couleur en dark (l'intérieur)
                Color selectedColor = current.getForegroundColor();
                selectedColor = selectedColor.brighter();
                g2d.setPaint(current.getForegroundColor().darker());
            }
            else {
                g2d.setPaint(current.getForegroundColor());
            }
            g2d.fill(current.getShapeForeground());
            g2d.draw(current.getShapeForeground());
        }
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
    
    


    @Override
    public void update(Presentation presentation) {
        this.slide = this.presentation.getCurrentSlideModel();
        this.removeAll();
        for(Item current : this.slide.getItemsCurrentSlide()) { 
            if(current instanceof MyShape && current.isSelected()) {
                this.add(current);
            }
            if(current instanceof Resizable) {
                if(current.isSelected()) {
                    current.setBorder(BorderFactory.createLineBorder(Color.black));
                    current.repaint();
                }
                else {
                    current.setBorder(null);
                    current.repaint();
                }
                this.add(current);
                Resizable zr = (Resizable)current;
                this.add(zr.getDragTopLeft());
                this.add(zr.getDragTopRight());
                this.add(zr.getDragBotLeft());
                this.add(zr.getDragBotRight());
                this.setComponentZOrder(zr, 1);
                this.setComponentZOrder(zr.getDragTopLeft(), 0);
                this.setComponentZOrder(zr.getDragTopRight(), 0);
                this.setComponentZOrder(zr.getDragBotLeft(), 0);
                this.setComponentZOrder(zr.getDragBotRight(), 0);
            }
        }
        this.repaint();
        this.revalidate();
    }
}
