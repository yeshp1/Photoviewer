import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Michael Kolling 
 * @author Yeshvanth Prabakar
 * @version 27 November 2017
 * 
 * ImageViewerGUI provides the GUI for the project. It displays the image and strings,
 * and it listens to button clicks.
 */
public class ImageViewerGUI
{
    // fields:
    private JFrame frame;
    private JLabel imagePanel;
    private JLabel textLabel;
    private JLabel statusLabel;
    private Image currentImage;
    private ImageViewer viewer;
    private boolean fixedSize;
    private int imageViewCount=1; //Instance variable that stores the count of images that have been viewed

    /**
     * Create an ImageViewer and display its GUI on screen.
     */
    public ImageViewerGUI(ImageViewer viewer)
    {
        currentImage = null;
        this.viewer = viewer;
        fixedSize = false;
        makeFrame();
    }
    // ---- public view functions ----
    
    /**
     * Display a given image
     */
    public void showImage(Image image)
    {
        imagePanel.setIcon(new ImageIcon(image));
        if(!fixedSize) {
            frame.pack();
        }
    }
    
    /**
     * Set a fixed size for image display. If set, this size will be used for all images.
     * If not set, the GUI will resize for each image.
     */
    public void setImageSize(int width, int height)
    {
        imagePanel.setPreferredSize(new Dimension(width, height));
        frame.pack();
        fixedSize = true;
    }
    
    /**
     * Show the name of the image in the GUI.
     */
    public void showName(String name)
    {
        textLabel.setText("Image: " + name);
    }

    /**
     * Show a message in the status bar at the bottom of the screen.
     */
    public void showStatus(String text)
    {
        statusLabel.setText(text);
    }

    
    
    // ---- implementation of button functions ----
    
    /**
     * Called when the 'Next' button was clicked.
     */
    private void nextButton()
    {
        imageViewCount++;
        viewer.nextImage();
    }
    
    public void selection()
    {
        ImageViewerGUI obj=new ImageViewerGUI(viewer);  
    }

    /**
     * Called when the 'Previous' button was clicked.
     */
    private void previousButton()
    {
        imageViewCount++;
        viewer.previousImage();
    }
    
     /**
     *Returns number of images viewed, as an integer value, since image viewer 
     *class has been opened.
     */
    public int getNumberOfImagesViewed()
    {
        return imageViewCount;
    }

    /**
     * Do an image special effect.
     */
    private void fishEyeButton()
    {
        viewer.fishEye();
    }

    // ---- swing stuff to build the frame and all its components ----
    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {
        frame = new JFrame("KCL Slide Show Viewer");
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));

        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(6, 6));

        // Create the image pane in the center
        imagePanel = new JLabel((Icon)null, SwingConstants.CENTER);
        imagePanel.setBorder(new EtchedBorder());
        contentPane.add(imagePanel, BorderLayout.CENTER);
        
        // Create two labels at top and bottom for the file name and status message
        textLabel = new JLabel();
        contentPane.add(textLabel, BorderLayout.NORTH);

        statusLabel = new JLabel("Version 1.0");
        contentPane.add(statusLabel, BorderLayout.SOUTH);
        
        // Create the toolbar with the buttons
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(0, 1));
        
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { nextButton(); }
                           });
        toolbar.add(nextButton);
        
        JButton previousButton = new JButton("Previous");
        previousButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { previousButton(); }
                           });
        toolbar.add(previousButton);

        JButton effectButton = new JButton("Fisheye");
        effectButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { fishEyeButton(); }
                           });
        toolbar.add(effectButton);

        // Add toolbar into panel with flow layout for spacing
        JPanel flow = new JPanel();
        flow.add(toolbar);
        
        contentPane.add(flow, BorderLayout.WEST);
        
        // building is done - arrange the components     
        frame.pack();
        
        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }    
}
