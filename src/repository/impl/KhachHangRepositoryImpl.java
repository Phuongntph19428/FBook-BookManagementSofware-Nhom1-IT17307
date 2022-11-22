/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import model.KhachHang;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.KhachHangRepository;
import util.HibernateUtil;

/**
 *
 * @author ppolo
 */
public class KhachHangRepositoryImpl implements KhachHangRepository {

    @Override
    public List<KhachHang> selectAll() {
        List<KhachHang> lstKhachHang = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT t FROM KhachHang t";
            TypedQuery<KhachHang> query = session.createQuery(hql);

            lstKhachHang = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstKhachHang;

    }

    @Override
    public boolean insertKhachHang(KhachHang khachHang) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(khachHang);
                tran.commit();
                return true;
            } catch (Exception e) {
                tran.rollback();
                e.printStackTrace();
                return false;
            } finally {
                session.close();
            }
        }
    }

    @Override
    public boolean updateKhachHang(KhachHang khachHang) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(khachHang);
                tran.commit();
                return true;
            } catch (Exception e) {
                tran.rollback();
                e.printStackTrace();
                return false;
            } finally {
                session.close();
            }
        }
    }

}
