/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import service.ISystemService;

/**
 *
 * @author quanc
 */
public class SystemServiceImpl implements ISystemService {

    // Find your Account Sid and Token at twilio.com/console 
    public static final String ACCOUNT_SID = "AC03d4a2d2defffec65d58d99d0ab048e3";
    public static final String AUTH_TOKEN = "4ee3872df3e87927cf283ad9edf87b60";

    @Override
    public void SendSMS(String PhoneNumber, String content) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+84" + PhoneNumber),
                "MG460bfa62917ce55950114f6444d25e08",
                content)
                .create();

        System.out.println(message.getSid());
    }

    @Override
    public void sendEmail(String email, String content) {
    }

}
