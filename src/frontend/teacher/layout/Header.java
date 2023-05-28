package frontend.teacher.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import frontend.modules.Modules;

public class Header {
    public JPanel header = new JPanel(new FlowLayout(FlowLayout.LEADING));
    
    public Header() {
        header.setPreferredSize(new Dimension(Layout.frame.getWidth(), 75));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        JLabel imageLabel = new JLabel();

        int width = 300;
        int height = 60;
        imageLabel.setPreferredSize(new Dimension(width, height));

        ImageIcon logoImage = new ImageIcon("./assets/exammr.png");
        Image scaledImage = logoImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        JPanel emptyPanel = new JPanel(new BorderLayout());
        emptyPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        emptyPanel.add(imageLabel);
        header.add(emptyPanel, BorderLayout.WEST);

        JLabel imageLabel1= new JLabel();
        int width1 = 50;
        int height1 = 50;
        imageLabel1.setPreferredSize(new Dimension(width1, height1));
        
        ImageIcon proImage = new ImageIcon("./assets/profile1.png");
        Image scaledImage1 = proImage.getImage().getScaledInstance(width1, height1, Image.SCALE_SMOOTH);
        imageLabel1.setIcon(new ImageIcon(scaledImage1));
        JPanel myPanel = new JPanel(new BorderLayout());
        myPanel.setBorder(BorderFactory.createEmptyBorder(0, 1000, 0, 0));
        myPanel.add(imageLabel1);
        header.add(myPanel, BorderLayout.EAST);

        // try {
        //     JPanel profile = new RoundImage(Modules.login.ProfileImagePath);
        //     header.add(profile, BorderLayout.EAST);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        
              // Create menu bar
             
              JMenuBar menuBar = new JMenuBar();
              menuBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
              header.add(menuBar, BorderLayout.EAST);
      
              // Create menu
              JMenu menu = new JMenu("Username");
              menuBar.add(menu);
      
              // Create menu items
              JMenuItem item1 = new JMenuItem("Profile");
              JMenuItem item2 = new JMenuItem("Log Out");

            //   //actionListener
            //   item2.addActionListener(new ActionListener() {
            //     public void actionPerformed(ActionEvent e) {
            //         // Perform logout operation
            //         // Here, assuming you have a reference to the main application frame
            //         JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(header);
            //         frame.setContentPane(new LoginPanel()); // Set the login panel as the content pane
            //         frame.pack(); // Pack the frame to update its layout
            //     }
            
            // });
              // Add menu items to menu
              menu.add(item1);
              menu.add(item2);
              // Set username in the menu
              if (Modules.login != null) {
                  menu.setText(Modules.login.Username);
              }
          }
      
        

    }
    // public class RoundImage extends JPanel {
    //     private BufferedImage image;
    
    //     public RoundImage(String imagePath) throws IOException {
    //         image = ImageIO.read(new File(imagePath));
    //     }
    
    //     @Override
    //     protected void paintComponent(Graphics g) {
    //         super.paintComponent(g);
    
    //         Graphics2D g2d = (Graphics2D) g.create();
    //         Shape clip = new Ellipse2D.Float(0, 0, 80, 80);
    //         g2d.setClip(clip);
    
    //         g2d.drawImage(image, 0, 0, 80, 80, null);
    //         g2d.dispose();
    //     }
    // }

    // public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
    //     int w = image.getWidth();
    //     int h = image.getHeight();
    //     BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

    //     Graphics2D g2 = output.createGraphics();

    //     g2.setComposite(AlphaComposite.Src);
    //     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    //     g2.setColor(Color.WHITE);
    //     g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

    //     g2.setComposite(AlphaComposite.SrcAtop);
    //     g2.drawImage(image, 0, 0, null);

    //     g2.dispose();

    //     return output;
    // }

