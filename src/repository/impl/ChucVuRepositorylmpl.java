/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.List;
import javax.persistence.TypedQuery;
import model.ChucVu;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.ChucVuRepository;
import util.HibernateUtil;

/**
 *
 * @author ECO
 */
public class ChucVuRepositorylmpl implements ChucVuRepository {

    @Override
    public boolean insert(ChucVu cv) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(cv);
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
    public boolean update(ChucVu cv) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(cv);
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
    public List<ChucVu> selectAll() {
        List<ChucVu> pas;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<ChucVu> query = session.createQuery("From ChucVu k");
            pas = query.getResultList();
            session.close();
        }
//        System.out.println(pes);
        return pas;
    }
    @Override
    public List<ChucVu> SelectbyName(String name) {
        List<ChucVu> pas;
        String nameSelect = "%"+name+"%";
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<ChucVu> query = session.createQuery("From ChucVu  WHERE Ten like :key");
            query.setParameter("key", nameSelect);
            System.out.println(query);
            pas = query.getResultList();
            session.close();
            
        }
//        System.out.println(pes);
        return pas;
    }

}
