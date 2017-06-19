package Vue;

import Modele.Slide;
import Modele.Presentation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;


public class PopUpMiniatures extends JPopupMenu{
    private JMenuItem deleteItem, duplicateItem;
    private Presentation app;
    private Slide slide;
    
    
    public PopUpMiniatures(Presentation app, Slide slide){
        this.app = app;
        this.slide = slide;
        deleteItem = new JMenuItem("Delete");
        ActionListener deleteListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int id = slide.getId();
                if(app.getSlideNumber()-1 == 0) {
                    JOptionPane.showMessageDialog(null, "You cannot delete the only slide", "WARNING", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    if(id == 0) {
                        app.removeSlide(id);
                        app.setCurrentSlideModel(app.getSlideById(0));
                        app.getSlideById(0).setHighlight(true);
                    }
                    else {
                        app.removeSlide(id);
                        app.setCurrentSlideModel(app.getSlideById(id-1));
                        app.getSlideById(id-1).setHighlight(true);
                    }
                    app.notifyObserver();
                }
            }          
        };
        deleteItem.addActionListener(deleteListener);
        add(deleteItem);
    }
}
