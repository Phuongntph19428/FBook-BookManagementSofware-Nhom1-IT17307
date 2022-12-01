/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

/**
 *
 * @author quanc
 */
public interface ISystemService {

    public void SendSMS(String PhoneNumber, String content);

    public void sendEmail(String email, String content);
}
