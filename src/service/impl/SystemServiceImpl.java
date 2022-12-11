/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Sach;
import service.ISystemService;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

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

    boolean TurnOnorOff = false;
    int QuantityLowerThan = 9999;
    int hinhThuc = -1;
    String sdt = "-";
    String email = "-";
    int typeSend = -1;

    String htmlSendSMS = "<div style=\"width:78% ; padding-bottom: 26px; background-color: whitesmoke;\n"
            + "    text-align: center;\">\n"
            + "        <h1 style=\"padding: 31px;font-family: sans-serif;     margin: 0;\">Thông Tin Những Sản Phẩm Sắp Hết Hàng</h1>\n"
            + "        <table style=\"margin: auto; background-color: white;border-radius: 10px;border: 1px solid;\">\n"
            + "            <tr style=\"    background: black;\n"
            + "            color: white;\n"
            + "            font-family: sans-serif;\">\n"
            + "                <th style=\"width: 250px; height: 53px;\"><span>Tên Sách</span></th>\n"
            + "                <th style=\"width: 250px;\"><span>Số Lượng Hiện Có</span></th>\n"
            + "                <th style=\"width: 250px;\"><span>Giá Nhập</span></th>\n"
            + "            </tr>\n";

    private final SachServiceImpl sachSer = new SachServiceImpl();
    private List<Sach> list;
    private List<Sach> listProgessedandSend;

    public void SendSMStoManager() {

        getDataSettings();
        if (TurnOnorOff == false) {
            return;
        }

        list = sachSer.selectAllLowerThan(QuantityLowerThan);
        if (typeSend == 1) {
            listProgessedandSend = ProcessingList(list);
            sendMethod(listProgessedandSend);
        } else if (typeSend == 2) {
            setTimeSend();
        }
        // header HTML
        WriteFile(list);
    }

    public void sendMethod(List<Sach> lst) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String content = "";
                for (Sach sach : lst) {
                    content += "Tên " + sach.getTen() + ", Số lượng: " + sach.getSoLuong() + "\n";
                }
                if (hinhThuc == 1 || hinhThuc == 3) {
                    System.out.println(content);
                    SendSMS(sdt, content);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Sach sach : lst) {
                    // Item HTML

                    String itemSach = "<tr style=\"font-family: sans-serif;border: none; height: 48px;\">\n"
                            + "                <td class=\"lalign\">" + sach.getTen() + "</td>\n"
                            + "                <td>" + sach.getSoLuong() + "</td>\n"
                            + "                <td>6,000</td>\n"
                            + "            </tr>\n";
                    // Add Item
                    htmlSendSMS += itemSach;

                }
                htmlSendSMS += "</table>\n"
                        + "    </div>";
                if (hinhThuc == 2 || hinhThuc == 3) {
                    sendEmail(email, htmlSendSMS);
                }
            }
        }).start();
    }

    private List<Sach> ProcessingList(List<Sach> listGetInDB) {
        List<Sach> lstProcessed = new ArrayList<>();
        List<Sach> listGetFile = null;
        try {
            listGetFile = readFile();

        } catch (Exception e) {
        }
        Map<String, Sach> mapListGetInFile = listGetFile.stream().collect(Collectors.toMap(Sach::getId, Sach -> Sach));
        Map<String, Sach> mapListGetInDB = listGetInDB.stream().collect(Collectors.toMap(Sach::getId, Sach -> Sach));

        listGetInDB.forEach((t) -> {
            if (mapListGetInFile.get(t.getId()) == null) {
                lstProcessed.add(t);
            } else {
                if (mapListGetInFile.get(t.getId()).getSoLuong() != t.getSoLuong()) {
                    lstProcessed.add(t);
                }
            }
        });
        return lstProcessed;
    }

    @Override
    public void SendSMS(String PhoneNumber, String content) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+84" + PhoneNumber),
                "MG460bfa62917ce55950114f6444d25e08", "Sách sắp hết hàng \n" + content)
                .create();

        System.out.println(message.getSid());
    }

    @Override
    public void sendEmail(String email, String content) {
        try {

            // dia chi email nguoi nhan
            final String toEmail = "quanchun11022@gmail.vn";
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
            msg.setText(body, "utf-8", "html");
            msg.setSentDate(new Date());
            msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(email, false));
            Transport.send(msg);
            System.out.println("Gui mail thanh cong");
        } catch (MessagingException ex) {
            Logger.getLogger(SystemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getDataSettings() {
        FileInputStream fileInputStream = null;
        try {
            String url = "settingsSend.txt";
            // Đọc dữ liệu từ File với Scanner
            fileInputStream = new FileInputStream(url);
            Scanner scanner = new Scanner(fileInputStream);
            int i = 0;
            List<String> list = new ArrayList<>();
            try {
                while (scanner.hasNextLine()) {
                    list.add(scanner.nextLine());
                }
            } finally {
                try {
                    scanner.close();
                    fileInputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (list.get(0).equals("true")) {
                TurnOnorOff = true;
            }
            QuantityLowerThan = Integer.parseInt(list.get(1));
            hinhThuc = Integer.parseInt(list.get(2));
            sdt = list.get(3);
            email = list.get(4);
            typeSend = Integer.parseInt(list.get(5));
            System.out.println(TurnOnorOff + " - " + QuantityLowerThan + " - " + hinhThuc + " - " + sdt + " - " + email + " - " + typeSend);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SystemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(SystemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void setDataSettings(boolean toggleSends, int quantityLowerThan, int hinhThuc, String sdt, String email, int typeSend) {
        try {
            FileWriter fw = new FileWriter("settingsSend.txt");
            String writeFile = "" + toggleSends + "\n" + quantityLowerThan + "\n" + hinhThuc + "\n" + sdt + "\n" + email + "\n" + typeSend + "\n";
            fw.write(writeFile);
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Success...");
    }

    public void WriteFile(List<Sach> list) {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream("hethang.txt");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.flush();
            oos.close();
            fos.close();
            System.out.println("ghi file thanh cong");
        } catch (IOException ex) {
            Logger.getLogger(SystemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(SystemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Sach> readFile() throws IOException, ClassNotFoundException {
        FileInputStream fos = new FileInputStream("hethang.txt");

        ObjectInputStream oos = new ObjectInputStream(fos);
        return (List<Sach>) oos.readObject();
    }

    public void setTimeSend() {
        try {

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("triggerName", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("00 22 12 * * ?")).build();
            JobDetail job = JobBuilder.newJob(DoTaskSend.class)
                    .withIdentity("jobName", "group1").build();
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException ex) {
            ex.printStackTrace();
        }
    }

    public List<Sach> getListAll() {
        return sachSer.selectAllLowerThan(QuantityLowerThan);
    }

    public static void main(String[] args) {
        SystemServiceImpl s = new SystemServiceImpl();
        
    }

}
