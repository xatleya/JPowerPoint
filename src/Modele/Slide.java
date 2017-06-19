package Modele;

import java.io.Serializable;
import java.util.ArrayList;

public class Slide implements Comparable, Serializable{
    private Presentation presentation;
    private int id; 
    private ArrayList<Item> itemsCurrentSlide = new ArrayList<Item>();
    private boolean isHighlighted = false;
    
    public Slide(int id, Presentation presentation) {
        this.id = id;
        this.presentation = presentation;
    }
    
    public void addCurrentSlideComponent (Item item) {
        this.itemsCurrentSlide.add(item);
    }

    public ArrayList<Item> getItemsCurrentSlide() {
        return itemsCurrentSlide;
    }

    public int getId() {
        return id;
    }
    
    public ArrayList<MyShape> getShapesTab() {
        ArrayList<MyShape> shapesTab = new ArrayList<MyShape>();
        for(Item current : this.itemsCurrentSlide) {
            if(current instanceof MyShape) {
                MyShape myShape = (MyShape)current;
                shapesTab.add(myShape);
            }
        }
        return shapesTab;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setHighlight(boolean isHighlighted) {
        this.isHighlighted = isHighlighted;
    }
    
    public boolean getHighlight(){
        return this.isHighlighted;
    }

    public Presentation getPresentation() {
        return presentation;
    } 
    
    @Override
    public int compareTo(Object t) {
        Slide other = (Slide)t;
        int currentId = this.getId();
        int otherId = other.getId();
        if(currentId < otherId) {
            return -1;
        }
        else if(currentId > otherId) {
            return 1;
        }
        else {
            return 0;
        }
    }    
}
