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
    static int CONTINUE = 2;
    static int Selected = -1;
    static int width = 441, height = 215;
    public static void showNoti_Succes(Component frame, String s) {
        Notification_Success notifi = new Notification_Success();
        JDialog j = new JDialog();
        j.setResizable(false);
        j.setSize(531, 186);
        j.setLocation(frame.getSize().width / 2-30 , frame.getSize().height / 2 - 110);
        j.setUndecorated(true);
        notifi.getJButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Selected = CONTINUE;
                j.dispose();
                
            }
        });

        j.add(notifi, BorderLayout.CENTER);
        j.setModal(true);
        j.setVisible(true);
    }
    
    public static void showNoti_Error(Component frame, String s) {
        Notification_Success notifi = new Notification_Success();
        JDialog j = new JDialog();
        j.setResizable(false);
        j.setSize(531, 186);
        j.setLocation(frame.getSize().width / 2-30 , frame.getSize().height / 2 - 110);
        j.setUndecorated(true);
        notifi.getJButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Selected = CONTINUE;
                j.dispose();
                
            }
        });

        j.add(notifi, BorderLayout.CENTER);
        j.setModal(true);
        j.setVisible(true);
    }
    
    public static void showNoti_Confirm(Component frame, String s) {
        Notification_Quest notifi = new Notification_Quest();
        JDialog j = new JDialog();
        j.setResizable(false);
        j.setSize(531, 186);
        j.setLocation(frame.getSize().width / 2-30 , frame.getSize().height / 2 - 110);
        j.setUndecorated(true);
        notifi.getButtonCancel().addActionListener(new ActionListener() {
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

        j.add(notifi, BorderLayout.CENTER);
        j.setModal(true);
        j.setVisible(true);
    }

    public static int getSelected() {
        return Selected;
    }

}
