/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crs;

import javax.swing.*; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 *
 * @author LLZ
 */
//display a picture
public class AWTTES extends JPanel {
    public static void main(String[] args) {
        showImg("digital_image_processing.jpg");
    }
    public static void showImg(String imageFileName){
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon(imageFileName);
        JLabel label = new JLabel(icon);
        frame.add(label);
        frame.setDefaultCloseOperation
               (JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
    }
   
}
