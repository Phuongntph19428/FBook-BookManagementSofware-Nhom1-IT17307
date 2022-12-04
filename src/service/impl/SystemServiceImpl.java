/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import model.Sach;
import service.ISystemService;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quanc
 */
public class SystemServiceImpl implements ISystemService {

    // Find your Account Sid and Token at twilio.com/console 
    public static final String ACCOUNT_SID = "AC03d4a2d2defffec65d58d99d0ab048e3";
    public static final String AUTH_TOKEN = "4ee3872df3e87927cf283ad9edf87b60";

    final String fromEmail = "bongngoc713@gmail.com";
    final String password = "rqtiziyaegpvqjxa";

    private final SachServiceImpl sachSer = new SachServiceImpl();
    private List<Sach> list;

    public void SendSMStoManager() {
        list = sachSer.selectAllLowerThan(20);
        String content = "";
        if (list != null) {
            for (Sach sach : list) {
                content += "Tên Sách: " + sach.getTen() + ", Số lượng: " + sach.getSoLuong() + "\n";
            }
            System.out.println("bat dau gui");
            SendSMS("396189965", content);
            sendEmail("quanchun11022@gmail.com", content);
        }
    }

    @Override
    public void SendSMS(String PhoneNumber, String content) {
        String ct = content;

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+84396189965"),
                "MG460bfa62917ce55950114f6444d25e08", content)
                .create();

        System.out.println(message.getSid());
    }

    @Override
    public void sendEmail(String email, String content) {
        try {

            // dia chi email nguoi nhan
            final String toEmail = "quanchun11022@gmail.com";
            final String subject = "Báo Cáo Quản Lý Bán Sách";
            final String body = content;
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            try {
                msg.setFrom(new InternetAddress(fromEmail, "Phần Mềm FBook"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(SystemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            msg.setReplyTo(InternetAddress.parse(fromEmail, false));
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(msg);
            System.out.println("Gui mail thanh cong");
        } catch (MessagingException ex) {
            Logger.getLogger(SystemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
    SystemServiceImpl s = new SystemServiceImpl();
    s.SendSMStoManager();
            }

}
