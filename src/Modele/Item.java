package Modele;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public abstract class Item extends JPanel{
    private boolean selected = false;

    public Item() {
        //this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setOpaque(false);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
    
    public abstract Item itemCopy();
}
