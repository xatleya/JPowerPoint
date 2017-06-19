package Vue;

import Modele.Presentation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame{
    private Presentation presentation;
    private MiniaturesView miniaturesView;
    private CurrentSlideView currentSlideView;
    private Toolbar toolbar;
    private StatusPanel statusPanel = new StatusPanel(this);

    public MainFrame (Presentation presentation){
        super ("PowerPoint");
        this.presentation = presentation;
        this.setLayout(new BorderLayout(5,5));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.newToolbar();
        
        Menu menu = new Menu(this);
        this.setJMenuBar(menu);
        
        this.newMiniaturesView();
        this.newCurrentSlideView();
        this.add(this.statusPanel, BorderLayout.SOUTH);
          
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 700);
        this.setVisible(true);
    }
            
    
    public void newMiniaturesView() {
        MiniaturesView miniaturesView = new MiniaturesView(this.presentation);
        this.presentation.addObserver(miniaturesView);
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(5,5));
        JScrollPane scroll = new JScrollPane(miniaturesView);
        scroll.setPreferredSize(new Dimension(230,600));
        leftPanel.add(scroll, BorderLayout.NORTH);
        this.miniaturesView = miniaturesView;
        this.add(leftPanel, BorderLayout.WEST);
    }
    
    public void newCurrentSlideView() {
        CurrentSlideView currentSlideView = new CurrentSlideView(this.presentation.getSlides().get(0), this.presentation, this);
        this.presentation.setCurrentSlideModel(currentSlideView.getSlide());
        this.presentation.addObserver(currentSlideView);
        JPanel rightPanel = new JPanel();
        rightPanel.add(currentSlideView);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.currentSlideView = currentSlideView;
        this.add(rightPanel, BorderLayout.CENTER);
    }
    
    public void newToolbar() {
        Toolbar toolbar = new Toolbar(this.presentation, this);
        this.toolbar = toolbar;
        this.add(toolbar, BorderLayout.NORTH);
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public MiniaturesView getMiniaturesView() {
        return miniaturesView;
    }

    public CurrentSlideView getCurrentSlideView() {
        return currentSlideView;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public StatusPanel getStatusPanel() {
        return statusPanel;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public void setMiniaturesView(MiniaturesView miniaturesView) {
        this.miniaturesView = miniaturesView;
    }

    public void setCurrentSlideView(CurrentSlideView currentSlideView) {
        this.currentSlideView = currentSlideView;
    }
}
