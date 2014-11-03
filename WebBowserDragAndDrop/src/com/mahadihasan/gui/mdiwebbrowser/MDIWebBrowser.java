
package com.mahadihasan.gui.mdiwebbrowser;


import com.mahadihasan.gui.webbrowser.WebToolBar;
import com.mahadihasan.gui.webbrowser.WebBrowserPane;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/**
 *
 * @author MAHADI HASAN NAHID
 */



public class MDIWebBrowser extends JFrame {

    JDesktopPane desktopPane = new JDesktopPane();
    WebBrowserPane browserPane = new WebBrowserPane();

    public MDIWebBrowser() {

        super("MDI Web Browser");

        createNewWindow();

        Container contentPane = getContentPane();
        contentPane.add(desktopPane);

        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new NewWindowAction());
        fileMenu.addSeparator();
        fileMenu.add(new ExitAction());
        fileMenu.setMnemonic('F');

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
    }

    private void createNewWindow() {

        JInternalFrame frame = new JInternalFrame(
                "Browser",
                true,
                true,
                true,
                true);

        WebToolBar toolBar = new WebToolBar(browserPane);
        

        Container contentPane = frame.getContentPane();
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(browserPane),
                BorderLayout.CENTER);

        frame.setSize(320, 240);

        int offset = 30 * desktopPane.getAllFrames().length;
        frame.setLocation(offset, offset);

        desktopPane.add(frame);

        frame.setVisible(true);
    }
    
    
    private class NewWindowAction extends AbstractAction {

        public NewWindowAction() {
            
            putValue(Action.NAME, "New Window");
            putValue(Action.SHORT_DESCRIPTION, 
                    "Create New WeB Browser Window");
            
            putValue(Action.MNEMONIC_KEY, (int) 'N');
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            createNewWindow();
        }
        
    }
    
    
    private class ExitAction extends AbstractAction {

        
        public ExitAction() {
            
            putValue(Action.NAME, "Exit");
            putValue(Action.SHORT_DESCRIPTION, "Exit Application");
            putValue(Action.MNEMONIC_KEY, (int) 'X');
            
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            System.exit(0);
        }
        
        
    }
    
    //-------------------------------------------------------------
   
    
    /*
    
    public static void main(String[] args) {
        
        MDIWebBrowser browser = new MDIWebBrowser();
        
        browser.setDefaultCloseOperation(EXIT_ON_CLOSE);
        browser.setSize(760, 600);
        browser.setVisible(true);
    }
     
     */
}
