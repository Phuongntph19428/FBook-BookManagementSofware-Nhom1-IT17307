/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
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

    @Override
    public List<KhachHang> sellectAllBySDT(String sdt) {
        List<KhachHang> lstKhachHang = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT t FROM KhachHang t where t.sdt like :sdt";
            TypedQuery<KhachHang> query = session.createQuery(hql);
            query.setParameter("sdt", "%" + sdt);

            lstKhachHang = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstKhachHang;
    }

    @Override
    public boolean addDiemKhachHang(KhachHang khachHang, int diem) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                khachHang.setDiemTichLuy(khachHang.getDiemTichLuy() + diem);
                session.update(khachHang);
                tran.commit();
                return true;

            } catch (Exception e) {
                tran.rollback();
                return false;
            }
        }
    }

    @Override
    public KhachHang sellectByMa(KhachHang khachHang) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT k FROM KhachHang k WHERE k.ma = :ma or k.sdt = :sdt";
            TypedQuery<KhachHang> query = session.createQuery(hql);
            query.setParameter("ma", khachHang.getMa());
            query.setParameter("sdt", khachHang.getSdt());

            try {
                KhachHang khachHang2 = query.getSingleResult();
                return khachHang2;
            } catch (NoResultException e) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public KhachHang selectUpdateByMa(KhachHang khachHang) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT k FROM KhachHang k WHERE (k.ma = :ma or k.sdt = :sdt) and k.id != :id";
            TypedQuery<KhachHang> query = session.createQuery(hql);
            query.setParameter("ma", khachHang.getMa());
            query.setParameter("sdt", khachHang.getSdt());
            query.setParameter("id", khachHang.getId());

            try {
                KhachHang khachHangUpdate = query.getSingleResult();
                return khachHangUpdate;
            } catch (NoResultException e) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
