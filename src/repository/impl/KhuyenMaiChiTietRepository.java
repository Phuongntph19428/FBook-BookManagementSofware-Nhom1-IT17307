/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import model.KhuyenMai;
import model.KhuyenMaiChiTiet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.IKhuyenMaiChiTietRepository;
import util.HibernateUtil;

/**
 *
 * @author adm
 */
public class KhuyenMaiChiTietRepository implements IKhuyenMaiChiTietRepository {

    @Override
    public List<KhuyenMai> selectAll() {
        List<KhuyenMai> pes;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<KhuyenMai> query = session.createQuery("From KhuyenMai k");
            pes = query.getResultList();
            session.close();
        }

        return pes;
    }

    @Override
    public List<KhuyenMaiChiTiet> selectAllChiKhuyenMais() {
        List<KhuyenMaiChiTiet> pes;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<KhuyenMaiChiTiet> query = session.createQuery("From KhuyenMaiChiTiet k");
            pes = query.getResultList();
            session.close();
        }
        return pes;
    }

    @Override
    public void delete(String id) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                TypedQuery<KhuyenMaiChiTietRepository> query = session.createQuery("DELETE FROM KhuyenMaiChiTiet km WHERE km.khuyenMai.id = :IdKhuyenMai");
                query.setParameter("IdKhuyenMai", id);
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
    public boolean update(KhuyenMaiChiTiet km) {
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

    @Override
    public void delete() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                TypedQuery<KhuyenMaiChiTietRepository> query = session.createQuery("DELETE FROM KhuyenMaiChiTiet km");

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
    public boolean insert(KhuyenMaiChiTiet km) {
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
    public List<KhuyenMaiChiTiet> selectAll(String id) {
        List<KhuyenMaiChiTiet> lists = new ArrayList<KhuyenMaiChiTiet>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM KhuyenMaiChiTiet WHERE IdKhuyenMai LIKE '%" + id + "%'";
            TypedQuery<KhuyenMaiChiTiet> query = session.createQuery(hql, KhuyenMaiChiTiet.class);
            lists = query.getResultList();
        }
        return lists;
    }

}
