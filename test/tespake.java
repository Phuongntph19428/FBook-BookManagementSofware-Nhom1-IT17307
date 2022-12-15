/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import repository.impl.*;
import java.util.List;
import model.KhuyenMaiChiTiet;
import repository.IKhuyenMaiChiTietRepository;
import repository.impl.KhuyenMaiChiTietRepository;

/**
 *
 * @author adm
 */
public class tespake implements Itespake {

    private IKhuyenMaiChiTietRepository chiTietRepository = new KhuyenMaiChiTietRepository();

    @Override
    public List<KhuyenMaiChiTiet> selectAllChiKhuyenMais() {
        return chiTietRepository.selectAllChiKhuyenMais();

    }

    public static void main(String[] args) {
        tespake ga = new tespake();

        List<KhuyenMaiChiTiet> list = new ArrayList<>();

        list = ga.selectAllChiKhuyenMais();

        System.out.println(list);

    }
}
