/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.NhaCungCap;

/**
 *
 * @author ppolo
 */
public interface NhaCungCapService {

    String addNhaCungCap(NhaCungCap ncc);

    List<NhaCungCap> getAllNhaCungCap();

    public String updateNhaCungCap(NhaCungCap nhaCungCap);
}
