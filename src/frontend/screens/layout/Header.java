package frontend.screens.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.modules.Modules;

public class Header {
    public JPanel header = new JPanel(new BorderLayout());
    
    public Header() {
        header.setPreferredSize(new Dimension(Layout.frame.getWidth(), 100));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        JLabel imageLabel = new JLabel();

        int width = 300;
        int height = 60;
        imageLabel.setPreferredSize(new Dimension(width, height));

        ImageIcon logoImage = new ImageIcon("E:/code/codes/Java/OOP-assignment/assets/exammr.png");
        Image scaledImage = logoImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        JPanel emptyPanel = new JPanel(new BorderLayout());
        emptyPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        emptyPanel.add(imageLabel);

        header.add(emptyPanel, BorderLayout.WEST);

        try {
            JPanel profile = new RoundImage(Modules.login.ProfileImagePath);
            header.add(profile, BorderLayout.EAST);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public class RoundImage extends JPanel {
        private BufferedImage image;
    
        public RoundImage(String imagePath) throws IOException {
            image = ImageIO.read(new File(imagePath));
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
    
            Graphics2D g2d = (Graphics2D) g.create();
            Shape clip = new Ellipse2D.Float(0, 0, 80, 80);
            g2d.setClip(clip);
    
            g2d.drawImage(image, 0, 0, 80, 80, null);
            g2d.dispose();
        }
    }

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
}
