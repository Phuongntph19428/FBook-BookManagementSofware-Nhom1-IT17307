/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

import repository.impl.*;
import java.util.List;
import javax.persistence.TypedQuery;
import model.HinhThucThanhToan;
import model.HoaDon;
import model.Sach;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.HinhThucThanhToanRepository;
import util.HibernateUtil;

/**
 *
 * @author ppolo
 */
public class HinhThucThanhToanRepositoryImpl implements HinhThucThanhToanRepository{

    private void deleteHinhThucThanhToan(HoaDon hoaDon) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession();) {
            session.beginTransaction();
            String hql = "DELETE FROM HinhThucThanhToan h WHERE h.hoaDon = :hoaDon";
            TypedQuery<Sach> query = session.createQuery(hql);
            query.setParameter("hoaDon", hoaDon);
            query.executeUpdate();
//            session.getTransaction().commit();
        }
    }
    
    @Override
    public boolean addHinhThucThanhToan(List<HinhThucThanhToan> lstHinhThucTT) {
        if (lstHinhThucTT.isEmpty()) {
            return true;
        }
        try ( Session session = HibernateUtil.getSessionFactory().openSession();) {

            Transaction tran = session.beginTransaction();
            
            try {
                final int batchSize = 20;
                int size = lstHinhThucTT.size();
                for (int i = 0; i < size; i++) {
                    session.persist(lstHinhThucTT.get(i));

                    if (i % batchSize == 0 && i != size && i != 0) {
                        session.flush();
                        session.clear();
                    }

                }

                tran.commit();
                return true;
            } catch (HibernateException e) {
                tran.rollback();
                return false;
            }
        }
    }

    @Override
    public boolean updateHinhThucThanhToan(List<HinhThucThanhToan> lstHinhThucTT) {
        if (lstHinhThucTT.isEmpty()) {
            return true;
        }
        try ( Session session = HibernateUtil.getSessionFactory().openSession();) {

            Transaction tran = session.beginTransaction();
            deleteHinhThucThanhToan(lstHinhThucTT.get(0).getHoaDon());
            
            try {
                final int batchSize = 20;
                int size = lstHinhThucTT.size();
                for (int i = 1; i < size; i++) {
                    session.persist(lstHinhThucTT.get(i-1));

                    if (i % batchSize == 0 && i != size) {
                        session.flush();
                        session.clear();
                    }

                }

                tran.commit();
                return true;
            } catch (HibernateException e) {
                tran.rollback();
                return false;
            }
        }
    }
    
}
