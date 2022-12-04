/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import custommodel.CustomSach;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.CustomSachRepository;
import util.HibernateUtil;

/**
 *
 * @author ppolo
 */
public class CustomSachRepositoryImpl implements CustomSachRepository {

    @Override
    public List<CustomSach> getList(int position, int pageSize) {
        List<CustomSach> lstCustomSach = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createSQLQuery("{CALL proc_getListCustomSach (:position, :pageSize)}");
            query.setParameter("position", (position -1) * pageSize );
            query.setParameter("pageSize", pageSize);
            
            List lst = query.getResultList();
            for (Object object : lst) {
                Object[] CsArr = (Object[]) object;
                String id = CsArr[0] + "";
                String ma = CsArr[1] + ""; 
                String ten = CsArr[2] + ""; 
                int soLuong = Integer.parseInt(CsArr[3] + ""); 
                String giaBanStr = CsArr[4] + "";
                BigDecimal giaBan = BigDecimal.valueOf(Double.parseDouble(giaBanStr));
                String giaSaleStr = CsArr[5] + "";
                BigDecimal giaSale;
                if(!giaSaleStr.equalsIgnoreCase("null")) {
                    giaSale = BigDecimal.valueOf(Double.parseDouble(giaSaleStr));
                }else {
                    giaSale = giaBan;
                }
                String chietKhauStr = CsArr[6] + "";
                Integer chietKhau = 0;
                if(!chietKhauStr.equalsIgnoreCase("null")) {
                    chietKhau = Integer.valueOf(chietKhauStr);
                }
                byte[] hinh = (byte[]) CsArr[7];
                String viTri = CsArr[8] + "";
                String tacGia = CsArr[9] + "";
                String theLoai = CsArr[10] + "";
                String nhaXuatBan = CsArr[11] + "";
                
                CustomSach customSach = new CustomSach(id, ma, ten, soLuong, giaBan, giaSale, chietKhau, hinh, viTri, tacGia, theLoai, nhaXuatBan);
                lstCustomSach.add(customSach);
            }

        }
        return lstCustomSach;
    }

    @Override
    public List<CustomSach> getAllByKeyword(String keyword) {
        List<CustomSach> lstCustomSach = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createSQLQuery("{CALL proc_getAllByKeyword (:keyword)}");
            query.setParameter("keyword", keyword);
            
            List lst = query.getResultList();
            for (Object object : lst) {
                Object[] CsArr = (Object[]) object;
                String id = CsArr[0] + "";
                String ma = CsArr[1] + ""; 
                String ten = CsArr[2] + ""; 
                int soLuong = Integer.parseInt(CsArr[3] + ""); 
                String giaBanStr = CsArr[4] + "";
                BigDecimal giaBan = BigDecimal.valueOf(Double.parseDouble(giaBanStr));
                String giaSaleStr = CsArr[5] + "";
                BigDecimal giaSale;
                if(!giaSaleStr.equalsIgnoreCase("null")) {
                    giaSale = BigDecimal.valueOf(Double.parseDouble(giaSaleStr));
                }else {
                    giaSale = giaBan;
                }
                String chietKhauStr = CsArr[6] + "";
                Integer chietKhau = 0;
                if(!chietKhauStr.equalsIgnoreCase("null")) {
                    chietKhau = Integer.valueOf(chietKhauStr);
                }
                byte[] hinh = (byte[]) CsArr[7];
                String viTri = CsArr[8] + "";
                String tacGia = CsArr[9] + "";
                String theLoai = CsArr[10] + "";
                String nhaXuatBan = CsArr[11] + "";
                
                CustomSach customSach = new CustomSach(id, ma, ten, soLuong, giaBan, giaSale, chietKhau, hinh, viTri, tacGia, theLoai, nhaXuatBan);
                lstCustomSach.add(customSach);
            }

        }
        return lstCustomSach;
    }
    
    @Override
    public int countAllSach() {
        int total = 0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(s.id) FROM Sach s";
            TypedQuery<Long> query = session.createQuery(hql, Long.class);
            total = Integer.parseInt(query.getSingleResult() + "");
        }
        return total;
    }

    @Override
    public CustomSach getSachByBarCode(String barCode) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createSQLQuery("{CALL proc_getCustomSachByBarCode (:keyword)}");
            query.setParameter("keyword", barCode);
            
            List lst = query.getResultList();
            for (Object object : lst) {
                Object[] CsArr = (Object[]) object;
                String id = CsArr[0] + "";
                String ma = CsArr[1] + ""; 
                String ten = CsArr[2] + ""; 
                int soLuong = Integer.parseInt(CsArr[3] + ""); 
                String giaBanStr = CsArr[4] + "";
                BigDecimal giaBan = BigDecimal.valueOf(Double.parseDouble(giaBanStr));
                String giaSaleStr = CsArr[5] + "";
                BigDecimal giaSale;
                if(!giaSaleStr.equalsIgnoreCase("null")) {
                    giaSale = BigDecimal.valueOf(Double.parseDouble(giaSaleStr));
                }else {
                    giaSale = giaBan;
                }
                String chietKhauStr = CsArr[6] + "";
                Integer chietKhau = 0;
                if(!chietKhauStr.equalsIgnoreCase("null")) {
                    chietKhau = Integer.valueOf(chietKhauStr);
                }
                byte[] hinh = (byte[]) CsArr[7];
                String viTri = CsArr[8] + "";
                String tacGia = CsArr[9] + "";
                String theLoai = CsArr[10] + "";
                String nhaXuatBan = CsArr[11] + "";
                
                CustomSach customSach = new CustomSach(id, ma, ten, soLuong, giaBan, giaSale, chietKhau, hinh, viTri, tacGia, theLoai, nhaXuatBan);
                return customSach;
            }

        }
        return null;
    }

}
