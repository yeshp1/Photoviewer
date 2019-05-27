/**
 * @author Michael Kolling 
 * @author Yeshvanth Prabakar
 * @version 27 November 2017
 */
public class ImageViewer
{      
    private ImageViewerGUI gui;     // the Graphical User Interface
    ImageViewer viewer;
    private Album album;
    private int imageIndex=1; //Instance variable that stores index of image as integer
    private Image currentImage; 
    private int sumOfImageWidths; // Instance variable that stores the sum of widths of images viewed
    private int averageWidth; //Instance variable that stores the average width of images viewed
    
    /**
     * Create an ImageViewer and display its GUI on screen. getImage method 
     * from album class loads the image (at index 0) which is stored in object 
     * variable currentImage. Corresponding methods from ImageViewerGUI class are 
     * called to display the image, name and dimension.
     */
    public ImageViewer()
    {
        gui = new ImageViewerGUI(this);
        album = new Album("images");
        currentImage=album.getImage(0);
        gui.showImage(currentImage);
        gui.showName(currentImage.getName()); 
        gui.showStatus(Dimensions()); 
        gui.setImageSize(500, 500);// Calls GUI’s setImageSize method to set maximum image size (500x500) and prevents image resizing
        System.out.println("Number of images viewed: "+gui.getNumberOfImagesViewed());
        System.out.println("Average image width: "+averageImageWidth());
    }

    /**
     Each time nextImage method is called, Imageindex is incremented 
     by 1 (index of next image). Once image at index 6 is reached, it 
     is reset to hold image at index 0.
     */
    public void nextImage()
    {
    if(imageIndex>5)    
    {
        imageIndex=0;    
    }
    currentImage=album.getImage(imageIndex);
    gui.showImage(currentImage);
    gui.showName(currentImage.getName());
    gui.showStatus(Dimensions());
    imageIndex++;
    System.out.println("Number of images viewed: "+gui.getNumberOfImagesViewed());
    System.out.println("Average image width: "+averageImageWidth());
    }   
    
    /**
     Eachtime previousImage method is called, the ImageIndex value is decreased by 1
     to hold the index of previous image. If imageIndex is less than 0, it is reset to
     5. 
     */
    public void previousImage()
    {
    if(imageIndex<0)
    {
        imageIndex=5;    
    } 
    currentImage=album.getImage(imageIndex);
    gui.showImage(currentImage);
    gui.showName(currentImage.getName());
    gui.showStatus(Dimensions());
    imageIndex--; 
    System.out.println("Number of images viewed: "+gui.getNumberOfImagesViewed());
    System.out.println("Average image width: "+averageImageWidth());
    }
    
    /**
     * Acesses the image dimensions (height, width) from methods in Image class 
     * and returns values as String
     */
    public String Dimensions()
    {
    String imageDimensions="Height: "+currentImage.getHeight()+" Width: "+currentImage.getWidth();
    return imageDimensions;
    }
    
    /**
     *Sums image widths of all images viewed. Calculates average
     *by dividing by number of images viewed and returns the 
     *value as an integer.
     */
    public int averageImageWidth()
    {
    sumOfImageWidths+=currentImage.getWidth();
    averageWidth=sumOfImageWidths/gui.getNumberOfImagesViewed();
    return averageWidth;    
    }

    /**
     *Fish eye method is called from Image class to apply fisheye effect on the image.
     *Image is then displayed using showImage method from ImageViewerGUI class.
     */
    public void fishEye()
    {
    currentImage.fishEye();
    gui.showImage(currentImage); 
    }

}
