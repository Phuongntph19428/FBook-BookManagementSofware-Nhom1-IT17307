/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.ArrayList;
import java.util.List;
import model.PhieuNhap;
import model.ViTri;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.PhieuNhapRepository;
import util.HibernateUtil;

/**
 *
 * @author Dieu Linh
 */
public class PhieuNhapRepositoryImpl implements PhieuNhapRepository {

    @Override
    public List<PhieuNhap> getAllPhieuNhap() {
        List<PhieuNhap> listPhieuNhap = new ArrayList<>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            listPhieuNhap = session.createQuery("from PhieuNhap").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return listPhieuNhap;

    }

    @Override
    public boolean addphieuNhap(PhieuNhap phieuNhap) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(phieuNhap);
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

}
