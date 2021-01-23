/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shepards.inverter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author rxiao
 */
public class ShepardsInverter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        JFileChooser selpatts = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        selpatts.setDialogTitle("Select the Shepards Pattern Files:");
        selpatts.setMultiSelectionEnabled(true);
        selpatts.setAcceptAllFileFilterUsed(false);
        selpatts.addChoosableFileFilter(new FileNameExtensionFilter(".txt Files", "txt"));
        if(selpatts.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) System.exit(0);
        
        JFileChooser targetfolder = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        targetfolder.setDialogTitle("Where to Save your Files:");
        targetfolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(targetfolder.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) System.exit(0);
        
        Variables vars = new Variables(selpatts.getSelectedFiles());
        //vars.flipOverX();
        vars.flipOverY();
        ImgWriter writer = new ImgWriter(targetfolder.getSelectedFile().getPath(), vars);
        
        JOptionPane.showMessageDialog(null, "Images Generated", "Complete", JOptionPane.INFORMATION_MESSAGE);
    }
}
