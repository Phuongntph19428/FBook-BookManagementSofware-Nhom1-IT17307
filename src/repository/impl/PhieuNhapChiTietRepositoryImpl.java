/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.ArrayList;
import java.util.List;
import model.PhieuNhapChiTiet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.PhieuNhapChiTietRepository;
import util.HibernateUtil;

/**
 *
 * @author Dieu Linh
 */
public class PhieuNhapChiTietRepositoryImpl implements PhieuNhapChiTietRepository {

    @Override
    public List<PhieuNhapChiTiet> getAllPhieuNhapChiTiet() {
        List<PhieuNhapChiTiet> listPhieuNhapChiTiet = new ArrayList<>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            listPhieuNhapChiTiet = session.createQuery("from PhieuNhapChiTiet").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return listPhieuNhapChiTiet;
    }

    @Override
    public boolean themPNCT(PhieuNhapChiTiet phieuNhapChiTiet) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(phieuNhapChiTiet);
                tran.commit();
                return true;
            } catch (Exception e) {
                tran.rollback();
                e.printStackTrace();
                return false;
            } finally {
//                session.flush();
                session.close();
            }
        }
    }

    @Override
    public boolean suaPNCT(PhieuNhapChiTiet phieuNhapChiTiet) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(phieuNhapChiTiet);
                tran.commit();
                return true;
            } catch (Exception e) {
                tran.rollback();
                e.printStackTrace();
                return false;
            } finally {
//                session.flush();
                session.close();
            }
        }
    }

    @Override
    public String xoaPNCT(String id) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                String hql = "DELETE PhieuNhapChiTiet pn WHERE pn.id = :id";
                Query query = session.createQuery(hql);
                query.setParameter("id", id);
                int result = query.executeUpdate();
                if (result == 0) {
                    id = "";
                }
                transaction.commit();
            } catch (Exception e) {
                id = "";
            }
        }
        return id;
    }

}
