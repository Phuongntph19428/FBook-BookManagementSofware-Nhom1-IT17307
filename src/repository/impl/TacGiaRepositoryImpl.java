/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import model.TacGia;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.TacGiaRepository;
import util.HibernateUtil;

/**
 *
 * @author ppolo
 */
public class TacGiaRepositoryImpl implements TacGiaRepository {

    @Override
    public List<TacGia> selectAll() {
        List<TacGia> lstTacGia = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT t FROM TacGia t";
            TypedQuery<TacGia> query = session.createQuery(hql);

            lstTacGia = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTacGia;
    }

    @Override
    public boolean insertTacGia(TacGia tacGia) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(tacGia);
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
    public boolean updateTacGia(TacGia tacGia) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(tacGia);
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
    public List<TacGia> searchTacGiaByKeyWord(String keyword) {
        List<TacGia> lstTacGia = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT t FROM TacGia t WHERE t.ten LIKE :ten OR t.ma LIKE :ma";
            TypedQuery<TacGia> query = session.createQuery(hql);
            query.setParameter("ten", "%" + keyword + "%");
            query.setParameter("ma", "%" + keyword + "%");

            try {
                lstTacGia = query.getResultList();
            } catch (Exception e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTacGia;
    }

}
