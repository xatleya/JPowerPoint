package Modele;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import javax.swing.JPanel;

public class MyShape extends Item implements Serializable {    //une shape de notre JPaint (il s'agit d'un JPanel)
    private Slide slide;
    private Shape shapeBackground;      //correspond au contour de la shape 
    private Shape shapeForeground;      //l'interieur de la shape
    private Color foregroundColor;      //couleur de l'interieur
    private Color backgroundColor;      //couleur du contour
    private String type;                //pour savoir de quel type est notre shape (ellipse, rectangle, line,...)
    //private boolean selected = false;   //savoir si notre shape est sélectionnée
    
    private int xOrigin;    //position en x du JPanel associé à notre shape
    private int yOrigin;    //position en y du JPanel associé à notre shape
    
    //contructeur de MyShape
    public MyShape(Slide slide, Shape shapeForeground, Shape shapeBackground, Color foregroundColor, Color backgroundColor, String type) { 
        this.slide = slide;
        this.shapeForeground = shapeForeground;
        this.shapeBackground = shapeBackground;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.type = type;
        this.setBackground(new Color(0,0,0,0));
        this.xOrigin = (int)this.shapeForeground.getBounds2D().getX();  //le JPanel associé à la shape aura la même position
        this.yOrigin = (int)this.shapeForeground.getBounds2D().getY();
    }
    
    public Item itemCopy() {
        MyShape newMyShape;
        Shape shapeBackground = null;
        Shape shapeForeground = null;
        if(this.getType().equals("Ellipse")) {
            Rectangle2D bounds = this.getShapeForeground().getBounds2D();
            shapeForeground = new Ellipse2D.Float((int)bounds.getX()*10/45,(int)bounds.getY()*10/45,(int)bounds.getWidth()*10/45,(int)bounds.getHeight()*10/45);
            bounds = this.getShapeBackground().getBounds2D();
            shapeBackground = new Ellipse2D.Float((int)bounds.getX()*10/45,(int)bounds.getY()*10/45,(int)bounds.getWidth()*10/45,(int)bounds.getHeight()*10/45);
        }
        else if(this.getType().equals("Rectangle")) {
            Rectangle2D bounds = this.getShapeForeground().getBounds2D();
            shapeForeground = new Rectangle2D.Float((int)bounds.getX()*10/45,(int)bounds.getY()*10/45,(int)bounds.getWidth()*10/45,(int)bounds.getHeight()*10/45);
            bounds = this.getShapeBackground().getBounds2D();
            shapeBackground = new Rectangle2D.Float((int)bounds.getX()*10/45,(int)bounds.getY()*10/45,(int)bounds.getWidth()*10/45,(int)bounds.getHeight()*10/45);
        }
        newMyShape = new MyShape(this.slide, shapeForeground, shapeBackground, this.getForegroundColor(), this.getBackgroundColor(), this.getType());
        return newMyShape;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }
    
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Shape getShapeBackground() {
        return shapeBackground;
    }

    public Shape getShapeForeground() {
        return shapeForeground;
    } 

    public Slide getSlide() {
        return slide;
    }

    public void setShapeBackground(Shape shapeBackground) {
        this.shapeBackground = shapeBackground;
    }

    public void setShapeForeground(Shape shapeForeground) {
        this.shapeForeground = shapeForeground;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getType() {
        return type;
    }

    public int getxOrigin() {
        return xOrigin;
    }

    public int getyOrigin() {
        return yOrigin;
    }

    public void setxOrigin(int xOrigin) {
        this.xOrigin = xOrigin;
    }

    public void setyOrigin(int yOrigin) {
        this.yOrigin = yOrigin;
    }
}
