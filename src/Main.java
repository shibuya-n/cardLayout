import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        //#1 Main window and Main JPanel
        JFrame window = new JFrame("CARD LAYOUT");
        JPanel cardPanel = new JPanel();
        CardLayout layoutObject = new CardLayout();
        cardPanel.setLayout(layoutObject);

        //#2 Panels to switch between
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new JLabel("Panel One"));
        File imageOne = new File("src/image_1.png");
        addImage(imageOne, panel1, window);


        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(new JLabel("Panel Two"));
        File imageTwo = new File("src/image_2.png");
        addImage(imageTwo, panel2, window);


        //#3 Add the panels to switch between ONTO the "Main JPanel"
        cardPanel.add(panel1, "1");
        cardPanel.add(panel2, "2");

        //#4 Buttons
        JPanel buttonPanel = new JPanel();
        JButton buttonOne = new JButton("1");
        buttonOne.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                layoutObject.show(cardPanel, "1");


            }
        });

        JButton buttonTwo = new JButton("2");
        buttonTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layoutObject.show(cardPanel, "2");
            }
        });

        buttonPanel.add(buttonOne);
        buttonPanel.add(buttonTwo);

        //#5 Final touches
        window.add(cardPanel, BorderLayout.NORTH);
        window.add(buttonPanel, BorderLayout.SOUTH);
        window.setBounds(500, 500, 250, 500);
        window.setResizable(false);
        window.setVisible(true);



    }

    public static void addImage(File input, JPanel addToPanel, JFrame yourFrame){
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(input);
        } catch (java.io.IOException ioe){
            ioe.printStackTrace();
        }
        ImageIcon imageIcon = new ImageIcon(myPicture);
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(250,250, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(newImg));

        addToPanel.add(picLabel);
        yourFrame.revalidate();
        yourFrame.repaint();

    }
}