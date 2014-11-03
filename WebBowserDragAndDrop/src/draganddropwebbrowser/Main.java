
package draganddropwebbrowser;

import java.awt.*;
import java.awt.dnd.*;
import java.util.*;
import java.io.*;

import java.awt.datatransfer.*;
import javax.swing.*;

import com.mahadihasan.gui.webbrowser.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author MAHADI HASAN NAHID
 */
public class Main extends JFrame {

    private final WebToolBar toolBar;
    private final WebBrowserPane browserPane;

    public Main() {

        super("Drag And Drop Web Browser");

        browserPane = new WebBrowserPane();
        toolBar = new WebToolBar(browserPane);



        browserPane.setDropTarget(
                new DropTarget(browserPane, DnDConstants.ACTION_COPY,
                new DropTargetHandler()));

        Container contentPane = getContentPane();
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(browserPane),
                BorderLayout.CENTER);
    }

    public class DropTargetHandler implements DropTargetListener {

        @Override
        public void dragEnter(DropTargetDragEvent dtde) {

            if (dtde.isDataFlavorSupported(
                    DataFlavor.javaFileListFlavor)) {

                dtde.acceptDrag(DnDConstants.ACTION_COPY);

            } else {

                dtde.rejectDrag();

            }

        }

        @Override
        public void dragOver(DropTargetDragEvent dtde) {
        }

        @Override
        public void dropActionChanged(DropTargetDragEvent dtde) {
        }

        @Override
        public void dragExit(DropTargetEvent dte) {
        }

        @Override
        public void drop(DropTargetDropEvent dtde) {

            Transferable transferable = dtde.getTransferable();

            if (transferable.isDataFlavorSupported(
                    DataFlavor.javaFileListFlavor)) {

                dtde.acceptDrop(DnDConstants.ACTION_COPY);

                try {

                    java.util.List fileList =
                            (java.util.List) transferable.getTransferData(
                            DataFlavor.javaFileListFlavor);


                    Iterator iterator = fileList.iterator();



                    while (iterator.hasNext()) {

                        File file = (File) iterator.next();

                        browserPane.gotoURL(file.toURL());

                    }

                    dtde.dropComplete(true);

                } catch (UnsupportedFlavorException flavorException) {
                    flavorException.printStackTrace();
                    dtde.dropComplete(false);
                } catch (IOException ioException) {

                    ioException.printStackTrace();
                    dtde.dropComplete(false);
                }

            } else {
                dtde.rejectDrop();
            }
        }
    }
    
    //-------------------------------------------------------------------------
    
    

    public static void main(String[] args) {
        Main browser = new Main();
        
        browser.setDefaultCloseOperation(EXIT_ON_CLOSE);
        browser.setSize(640, 480);
        browser.setVisible(true);
    }
    

    
    //-------------------------------------------------------------------------
}
