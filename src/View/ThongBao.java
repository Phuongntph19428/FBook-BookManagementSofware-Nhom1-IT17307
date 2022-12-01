/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import View.Form_Management.BieuDo_Form;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author quanc
 */
public class ThongBao {

    static int YES = 1;
    static int NO = 0;
    static int Selected = -1;
    static int width = 441, height = 215;

    public static void showConfirm(Component frame, String s) {
        Notification_Form notifi = new Notification_Form();
        notifi.setText(s);
        JDialog j = new JDialog();
        j.setResizable(false);
        j.setLocation(frame.getSize().width + 20 / 2, frame.getSize().height / 2 - 140);
        j.setSize(width, height);
        j.setUndecorated(true);
        j.add(notifi, BorderLayout.CENTER);

        notifi.getButtonNo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Selected = NO;
                j.dispose();
            }
        });

        notifi.getButtonYes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Selected = YES;
                j.dispose();
            }
        });
        j.setModal(true);
        j.setVisible(true);
    }

    public static void showMessage(Component frame, String s) {
        Notification_Form notifi = new Notification_Form();
        notifi.setText(s);
        JDialog j = new JDialog();
        j.setResizable(false);
        j.setLocation(frame.getSize().width / 2 + 150, frame.getSize().height / 2 - 80);
        j.setSize(width, height);
        j.setUndecorated(true);
        //[0,102,34]
        notifi.getButtonNo().setText("OK");
        notifi.getButtonYes().show(false);
        notifi.getButtonNo().setBackground(new Color(0, 153, 51));
        notifi.getButtonNo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
            }
        });

        j.add(notifi, BorderLayout.CENTER);
        j.setModal(true);
        j.setVisible(true);
    }

    //[0,153,51]
    public static int getSelected() {
        return Selected;
    }

}
