package Modele;
import Observe.*;
import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.ArrayList;

public class Presentation implements Observable, Serializable{
    private ArrayList<Slide> slides = new ArrayList<Slide>();
    private int slideNumber = 1;
    private transient ArrayList<Observer> listObserver = new ArrayList<Observer>();
    private Slide currentSlideModel;
    private transient Color textColor;
    private transient int textSize = 30;
    private transient Font font = new Font("Arial", Font.PLAIN, textSize);
    
    public Presentation() {
        this.slides.add(new Slide(0, this));  //par défaut on a un slide
    }
    
    public void addSlide() {
        this.slideNumber++;
        this.slides.add(new Slide(this.slideNumber-1, this));
        this.currentSlideModel.setHighlight(true);
        this.notifyObserver();
    }
    
    public void removeSlide(int id) {
        this.slideNumber--;
        this.slides.remove(id);
        for(int i=id;i<this.slideNumber;i++) {
            Slide current = this.slides.get(i);
            current.setId(i);
        }      
    }
    
    public void swapSlides(Slide s1, Slide s2) {
        int temp = s1.getId();
        s1.setId(s2.getId());
        s2.setId(temp);
        this.slides.sort(null);
    }
    
    public void swapSlides(int id1, int id2) {
        this.slides.get(id1).setId(id2);
        this.slides.get(id2).setId(id1);
        this.slides.sort(null);
    }

    public ArrayList<Slide> getSlides() {
        return slides;
    }
    
    public Slide getSlideById(int id) {
        for(Slide slide : this.getSlides()) {
            if(slide.getId() == id) {
                return slide;
            }
        }
        return null;
    }

    public int getSlideNumber() {
        return slideNumber;
    }

    public Slide getCurrentSlideModel() {
        return currentSlideModel;
    }

    public void setCurrentSlideModel(Slide currentSlideModel) {
        this.currentSlideModel = currentSlideModel;
    }

    @Override
    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }

    @Override
    public void removeObserver() {
        this.listObserver = new ArrayList<Observer>();
    }

    @Override
    public void notifyObserver() {
        for(Observer obs : this.listObserver){
            obs.update(this);
        }
    }
    
    @Override
    public String toString() {
        String result = new String();
        for(Slide current : this.slides) {
            result += current.getId() + " ";
        }
        return result;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setListObserver(ArrayList<Observer> listObserver) {
        this.listObserver = listObserver;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }    
}
