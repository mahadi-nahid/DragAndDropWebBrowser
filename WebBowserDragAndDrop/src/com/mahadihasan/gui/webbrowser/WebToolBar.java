
package com.mahadihasan.gui.webbrowser;

import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 *
 * @author MAHADI HASAN NAHID
 */
public class WebToolBar extends JToolBar
        implements HyperlinkListener {

    private WebBrowserPane webBrowserPane;
    private final JButton backButton;
    private final JButton forwardButton;
    private JTextField urlTextField;

    public WebToolBar(WebBrowserPane browser) {


        super("Web Navigation");

        webBrowserPane = browser;
        webBrowserPane.addHyperlinkListener(this);


        urlTextField = new JTextField(25);
        urlTextField.addActionListener(
                (ActionEvent ae) -> {
                    try {
                        
                        URL url = new URL(urlTextField.getText());
                        webBrowserPane.gotoURL(url);
                        
                    } catch (MalformedURLException urlException) {
                        urlException.printStackTrace();
                    }
        });

        backButton = new JButton(
                new ImageIcon(getClass().getResource("back.png")));

        backButton.addActionListener(
                (ActionEvent ae) -> {
                    URL url = webBrowserPane.back();
                    
                    urlTextField.setText(url.toString());
        });



        forwardButton = new JButton(
                new ImageIcon(getClass().getResource("forward.png")));

        forwardButton.addActionListener(
                (ActionEvent ae) -> {
                    URL url = webBrowserPane.forward();
                    
                    urlTextField.setText(url.toString());
        });


        add(backButton);
        add(forwardButton);
        add(urlTextField);


    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent he) {

        if (he.getEventType()
                == HyperlinkEvent.EventType.ACTIVATED) {

            URL url = he.getURL();


            webBrowserPane.gotoURL(url);
            urlTextField.setText(url.toString());
        }
    }
}
