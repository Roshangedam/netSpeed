/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package netspeeed;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;

/**
 *
 * @author rgedam
 */
class FrameDragListener extends MouseAdapter {

    private final JFrame frame;
    private Point mouseDownCompCoords = null;

    public FrameDragListener(JFrame frame) {
        this.frame = frame;
    }

    public void mouseReleased(MouseEvent e) {
        mouseDownCompCoords = null;
    }

    public void mousePressed(MouseEvent e) {
        mouseDownCompCoords = e.getPoint();
    }

    public void mouseDragged(MouseEvent e) {
        Point currCoords = e.getLocationOnScreen();
        frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
    }
}

public class NetSpeeed extends JFrame {

    public NetSpeeed() {
        Net net = new Net();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    net.update();
                } catch (InterruptedException ex) {
                }
            }
        }).start();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(1200, 10);
        setUndecorated(true);
        //setIconImage(new ImageIcon("icon.png").getImage());
        setSize(200, 200);
        setBackground(new Color(0, 0, 0, 0));
        setAlwaysOnTop(true);
        setType(Type.UTILITY); 
        FrameDragListener frameDragListener = new FrameDragListener(this);
        addMouseListener(frameDragListener);
        addMouseMotionListener(frameDragListener);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel upSpeed = new JLabel();
        JLabel kbps1 = new JLabel("mb/s");
        JLabel downSpeed = new JLabel();
        JLabel kbps2 = new JLabel("mb/s");

         add(kbps2);
        add(upSpeed);
        add(kbps1);
        add(downSpeed);

        downSpeed.setBounds(20, 50, 100, 100);
        kbps1.setBounds(30, 50, 100, 100);
        upSpeed.setBounds(80, 50, 100, 100);
        kbps2.setBounds(110, 50, 100, 100);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (net.getFlag()) {
                       
                        try {
                            if (net.downSpeed.intValue() != 0) {
                                downSpeed.setText(net.downSpeed.toString().substring(0, 3) );
                                kbps1.setText("mbps ↓");                             
                            } else {
                                downSpeed.setText(net.downSpeed.toString().substring(2, 5));
                                kbps1.setText("kbps ↓");                    
                            }
                        } catch (Exception e) {
                            downSpeed.setText(net.downSpeed.toString().substring(2));                           
                            kbps1.setText("kbps ↓");
                        }
                        try {
                            if (net.upSpeed.intValue() != 0) {
                             
                                upSpeed.setText(net.upSpeed.toString().substring(0, 3) );                           
                                kbps2.setText("mbps ↑");
                            } else {
                                upSpeed.setText(net.upSpeed.toString().substring(2, 5));
                                kbps2.setText("kbps ↑");
                            }
                        } catch (Exception e) {
                            upSpeed.setText(net.upSpeed.toString().substring(2));
                            kbps2.setText("kbps ↑");
                        }
                    }
                } catch (Exception ex) {
                }
            }
        }).start();

        setVisible(true);
    }

    public static void main(String[] args) {
        new NetSpeeed();
    }
}
