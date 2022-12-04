/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

/**
 *
 * @author quanc
 */
public class ThongBao {

    public static int YES = 1;
    public static int NO = 0;
    public static int CONTINUE = 2;
    static int Selected = -1;
    static int width = 441, height = 215;
    static Icon iconSuccess = new ImageIcon("image/icons8_ok_65px.png");
    static Icon iconError = new ImageIcon("image/icons8_box_important_65px.png");
    static Icon iconConfirm = new ImageIcon("image/icons8_help_65px.png");
    static Notification_Quest notifi ; 

    public static void showNoti_Succes(Component frame, String s) {
        notifi = new Notification_Quest();
        setStyle("Success",iconSuccess,new Color(0,153,0));
        notifi.getButtonYes().setText("Continue");
        JDialog j = new JDialog();
        j.setResizable(false);
        j.setSize(531, 186);
        j.setLocation(frame.getSize().width / 2 - 30, frame.getSize().height / 2 - 110);
        j.setUndecorated(true);
        notifi.getButtonYes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Selected = CONTINUE;
                j.dispose();

            }
        });
        notifi.getButtonCancel().setVisible(false);
        notifi.setContent(s);

        j.add(notifi, BorderLayout.CENTER);
        j.setModal(true);
        j.setVisible(true);
    }

    public static void showNoti_Error(Component frame, String s) {
        notifi = new Notification_Quest();
        setStyle("Error",iconError,new Color(204,0,0));
        notifi.getButtonYes().setText("Try Again");
        JDialog j = new JDialog();
        j.setResizable(false);
        j.setSize(531, 186);
        j.setLocation(frame.getSize().width / 2 - 30, frame.getSize().height / 2 - 110);
        j.setUndecorated(true);
        notifi.getButtonYes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Selected = CONTINUE;
                j.dispose();

            }
        });
        notifi.getButtonCancel().setVisible(false);
        notifi.setContent(s);
        j.add(notifi, BorderLayout.CENTER);
        j.setModal(true);
        j.setVisible(true);
    }

    public static void showNoti_Confirm(Component frame, String s) {
        notifi = new Notification_Quest();
        setStyle("Confirm",iconConfirm,new Color(49,134,234));
        JDialog j = new JDialog();
        j.setResizable(false);
        j.setSize(531, 186);
        j.setLocation(frame.getSize().width / 2 - 30, frame.getSize().height / 2 - 110);
        j.setUndecorated(true);
        notifi.getButtonCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Selected = NO;
                j.dispose();

            }
        });
        notifi.setContent(s);
        notifi.getButtonYes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Selected = YES;
                j.dispose();

            }
        });

        j.add(notifi, BorderLayout.CENTER);
        j.setModal(true);
        j.setVisible(true);
    }

    public static void setStyle(String name, Icon icon, Color color ) {
        notifi.setIcon(icon);
        notifi.setName(name);
        notifi.setBarColor(color);
        notifi.getButtonYes().setBackground(color);
    }

    public static int getSelected() {
        return Selected;
    }

}
