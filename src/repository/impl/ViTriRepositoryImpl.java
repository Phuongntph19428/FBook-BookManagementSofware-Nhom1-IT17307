/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.ArrayList;
import java.util.List;
import model.ViTri;
import repository.ViTriRepository;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author Dieu Linh
 */
public class ViTriRepositoryImpl implements ViTriRepository {

    @Override
    public List<ViTri> getAllViTri() {
        List<ViTri> listViTri = new ArrayList<>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            listViTri = session.createQuery("from ViTri").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return listViTri;

    }

    @Override
    public boolean addViTri(ViTri viTri) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(viTri);
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
    public boolean updateViTri(ViTri viTri) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(viTri);
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
//    public static void main(String[] args) {
//        ViTri viTri= new ViTri("hihi", "hihihi");
//        ViTriRepositoryImpl viTriRepositoryImpl= new ViTriRepositoryImpl();
//        viTriRepositoryImpl.addViTri(viTri);
//    }
}
