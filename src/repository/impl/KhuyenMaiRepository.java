/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.ArrayList;
import model.KhuyenMai;
import java.util.List;
import java.util.UUID;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import repository.IKhuyenMaiRepository;

/**
 *
 * @author adm
 */
public class KhuyenMaiRepository implements IKhuyenMaiRepository {

    @Override
    public boolean insert(KhuyenMai km) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(km);
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
    public boolean update(KhuyenMai km) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(km);
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

//    @Override
//    public List<KhuyenMai> selectAll() {
//        
//        List<KhuyenMai> pes;
//        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
//            TypedQuery<KhuyenMai> query = session.createQuery("From KhuyenMai k");
//            pes = query.getResultList();
//            session.close();
//        }
////        System.out.println(pes);
//        return pes;
//    }
//    
    @Override
    public List<KhuyenMai> selectAll(String ma) {
        List<KhuyenMai> lists = new ArrayList<KhuyenMai>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM KhuyenMai WHERE Ma LIKE '%" + ma + "%'";
            TypedQuery<KhuyenMai> query = session.createQuery(hql, KhuyenMai.class);
            lists = query.getResultList();
        }
        return lists;
    }

    @Override
    public void delete(String id) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                TypedQuery<KhuyenMai> query = session.createQuery("DELETE FROM KhuyenMai km WHERE km.id = :id");
                query.setParameter("id", id);
                query.executeUpdate();
                tran.commit();

            } catch (Exception e) {
                tran.rollback();
                e.printStackTrace();

            } finally {
                session.close();
            }
        }
    }

    @Override
    public String findById(String ma) {
        String string;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            try {
                String statement = "select km.ma from KhuyenMai km where km.ma = :ma";
                TypedQuery<String> query = session.createQuery(statement, String.class);
                query.setParameter("ma", ma);
                string = query.getSingleResult();
                t.commit();
            } catch (Exception e) {
                t.rollback();
                string = "";
                e.printStackTrace();

            }
            return string;
        }

    }

}
