package Vue;

import Controleur.BinaryFileTools;
import Modele.Presentation;
import Observe.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
    private JMenu fileMenu = new JMenu("File");
    private MainFrame mainFrame;
    
    
    public Menu(MainFrame mainFrame){  
        this.mainFrame = mainFrame;
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem openF = new JMenuItem("Open", KeyEvent.VK_O);
        ActionListener OpenListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser loadFile = new JFileChooser();
                int action = loadFile.showOpenDialog(null);
                if(action == loadFile.APPROVE_OPTION){
                    File file = loadFile.getSelectedFile();
                    Presentation presentationLoad = BinaryFileTools.LoadPresentation(file.toString());
                    presentationLoad.setListObserver(new ArrayList<Observer>());
                    mainFrame.getContentPane().removeAll();
                    mainFrame.setPresentation(presentationLoad);
                    
                    mainFrame.newToolbar();
                    mainFrame.newMiniaturesView();
                    
                    mainFrame.newCurrentSlideView();
                    mainFrame.invalidate();
                    mainFrame.revalidate();
                    mainFrame.repaint();
                    presentationLoad.notifyObserver();
                }
            }
        };
        openF.addActionListener(OpenListener);
        fileMenu.add(openF);
        
        JMenuItem saveF = new JMenuItem("Save", KeyEvent.VK_S);
        ActionListener SaveListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    JFileChooser saveFile = new JFileChooser();
                    saveFile.setSelectedFile(new File("newPresentation.ser"));
                    saveFile.showSaveDialog(saveFile);
                    BinaryFileTools.SavePresentation(saveFile.getSelectedFile().toString(), mainFrame.getPresentation());
                }
        };       
        saveF.addActionListener(SaveListener);
        fileMenu.add(saveF);
        
        this.add(fileMenu);

}
}
