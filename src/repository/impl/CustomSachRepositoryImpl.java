/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import custommodel.CustomSach;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.StoredProcedureQuery;
import org.hibernate.Session;
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
            StoredProcedureQuery query = session.createStoredProcedureQuery("{CALL proc_getListCustomSach (:position, :pageSize)}");
            query.setParameter("positon", position);
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
                BigDecimal giaSale = null;
                if(!giaSaleStr.equalsIgnoreCase("null")) {
                    giaSale = BigDecimal.valueOf(Double.parseDouble(giaSaleStr));
                }
                String chietKhauStr = CsArr[6] + "";
                Integer chietKhau = null;
                if(chietKhauStr.equalsIgnoreCase("null")) {
                    chietKhau = Integer.valueOf(chietKhauStr);
                }
                byte[] hinh = (byte[]) CsArr[7];
                
                CustomSach customSach = new CustomSach(id, ma, ten, soLuong, giaBan, giaSale, chietKhau, hinh);
                lstCustomSach.add(customSach);
            }

        }
        return lstCustomSach;
    }

    @Override
    public List<CustomSach> getAllByKeyword(String keyword) {
        List<CustomSach> lstCustomSach = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            StoredProcedureQuery query = session.createStoredProcedureQuery("{CALL proc_getListCustomSach (:keyword)}");
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
                BigDecimal giaSale = null;
                if(!giaSaleStr.equalsIgnoreCase("null")) {
                    giaSale = BigDecimal.valueOf(Double.parseDouble(giaSaleStr));
                }
                String chietKhauStr = CsArr[6] + "";
                Integer chietKhau = null;
                if(chietKhauStr.equalsIgnoreCase("null")) {
                    chietKhau = Integer.valueOf(chietKhauStr);
                }
                byte[] hinh = (byte[]) CsArr[7];
                
                CustomSach customSach = new CustomSach(id, ma, ten, soLuong, giaBan, giaSale, chietKhau, hinh);
                lstCustomSach.add(customSach);
            }

        }
        return lstCustomSach;
    }

}
