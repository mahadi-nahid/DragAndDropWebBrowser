
package com.mahadihasan.gui.webbrowser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JEditorPane;

/**
 *
 * @author MAHADI HASAN NAHID
 */
public class WebBrowserPane extends JEditorPane {

    private final List history = new ArrayList();
    private int historyIndex;

    public WebBrowserPane() {

        setEditable(false);
    }

    public void gotoURL(URL url) {

        displayPage(url);
        history.add(url);
        historyIndex = history.size() - 1;

    }

    public URL forward() {

        historyIndex++;

        if (historyIndex >= history.size()) {
            historyIndex = history.size() - 1;
        }

        URL url = (URL) history.get(historyIndex);

        displayPage(url);

        return url;
    }

    public URL back() {

        historyIndex--;

        if (historyIndex < 0) {
            historyIndex = 0;
        }

        URL url = (URL) history.get(historyIndex);
        displayPage(url);

        return url;
    }

    private void displayPage(URL pageURL) {

        try {

            setPage(pageURL);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
