/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.PhieuNhap;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.PhieuNhapRepository;
import util.HibernateUtil;

/**
 *
 * @author Dieu Linh
 */
public class PhieuNhapRepositoryImpl implements PhieuNhapRepository {

    @Override
    public List<PhieuNhap> getAllPhieuNhap() {
        List<PhieuNhap> listPhieuNhap;
        try ( Session session = HibernateUtil.getSessionFactory().openSession();) {
            listPhieuNhap = session.createQuery("from PhieuNhap").list();
        }
        return listPhieuNhap;
    }

    @Override
    public List<PhieuNhap> getPhieuChuaNhap() {
        List<PhieuNhap> listPhieuNhap;
        try ( Session session = HibernateUtil.getSessionFactory().openSession();) {
            listPhieuNhap = session.createQuery("from PhieuNhap p where p.trangThai  = 0").list();
        }
        return listPhieuNhap;
    }

    @Override
    public List<PhieuNhap> getPhieuDaNhap() {
        List<PhieuNhap> listPhieuNhap;
        try ( Session session = HibernateUtil.getSessionFactory().openSession();) {
            listPhieuNhap = session.createQuery("from PhieuNhap p where p.trangThai = 1").list();
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

    @Override
    public PhieuNhap getByMa(String ma) {

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT pn FROM PhieuNhap pn where pn.ma = :ma";
            TypedQuery<PhieuNhap> query = session.createQuery(hql, PhieuNhap.class);
            query.setParameter("ma", ma);
            try {
                PhieuNhap phieuNhap = query.getSingleResult();
                return phieuNhap;
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    @Override
    public List<String> cbbNcc() {
        List<String> cbbNcc = new ArrayList<>();
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select ten from NhaCungCap";
            TypedQuery<String> query = s.createQuery(hql, String.class);
            cbbNcc = query.getResultList();
        }
        return cbbNcc;
    }

//    public static void main(String[] args) {
//        List<String> listcbb = new PhieuNhapRepositoryImpl().cbbNcc();
//        System.out.println(listcbb);
//    }
    @Override
    public String xoa(String id) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                String hql = "DELETE PhieuNhap pn WHERE pn.id = :id";
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

    @Override
    public String findById(String ten) {
        String uuid;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String statement = "select p.id from NhaCungCap p where p.ten = :ten";
            TypedQuery<String> query = session.createQuery(statement, String.class);
            query.setParameter("ten", ten);
            uuid = query.getSingleResult();
        }
        return uuid;

    }

    @Override
    public boolean updatePhieuNhap(PhieuNhap phieuNhap) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(phieuNhap);
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
    public PhieuNhap getUpdateMa(PhieuNhap phieuNhap) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT pn FROM PhieuNhap pn where pn.ma = :ma and pn.id != :id";
            TypedQuery<PhieuNhap> query = session.createQuery(hql, PhieuNhap.class);
            query.setParameter("ma", phieuNhap.getMa());
            query.setParameter("id", phieuNhap.getId());
            try {
                PhieuNhap pn = query.getSingleResult();
                return pn;
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    @Override
    public List<PhieuNhap> selectByDay(Date date) {
        List<PhieuNhap> listPhieuNhap;
        try ( Session session = HibernateUtil.getSessionFactory().openSession();) {
            listPhieuNhap = session.createQuery("from PhieuNhap p where p.ngayNhap = :ngayNhap").setParameter("ngayNhap", date).list();
        }
        return listPhieuNhap;
    }
}
