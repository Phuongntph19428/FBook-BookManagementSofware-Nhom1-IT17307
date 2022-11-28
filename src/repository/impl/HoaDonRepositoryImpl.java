/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.HoaDon;
import model.HoaDonChiTiet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.HoaDonRepository;
import util.HibernateUtil;

/**
 *
 * @author ppolo
 */
public class HoaDonRepositoryImpl implements HoaDonRepository {

    @Override
    public boolean taoHoaDon(HoaDon hoaDon) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(hoaDon);
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
    public boolean deleteHoaDonChiTiet(HoaDon hoaDon) {

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                String hql = "delete HoaDonChiTiet h WHERE h.hoaDon = :hoaDon";
                Query<HoaDonChiTiet> query = session.createQuery(hql);
                query.setParameter("hoaDon", hoaDon);

                query.executeUpdate();
                tran.commit();
                return true;
            } catch (Exception e) {
                tran.rollback();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertHoaDonChiTiet(HoaDonChiTiet hoaDonCT) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(hoaDonCT);
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
    public boolean removeHoaDonChiTiet(HoaDonChiTiet hoaDonCT) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.delete(hoaDonCT);
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
    public boolean updateHoaDonChiTiet(HoaDonChiTiet hoaDonCT) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(hoaDonCT);
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
    public boolean updateHoaDon(HoaDon hoaDon) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(hoaDon);
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
    public String getLastHoaDon() {
        String ma = "";
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT h.ma FROM HoaDon h order by h.ma desc";
            TypedQuery<String> query = session.createQuery(hql);

            try {
                ma = query.getSingleResult();
            } catch (NoResultException e) {
                return "HD00";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ma;
    }

    @Override
    public List<HoaDon> sellectAll() {
        List<HoaDon> lstHoaDon = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT h FROM HoaDon h order by h.ma desc";
            TypedQuery<HoaDon> query = session.createQuery(hql);

            try {
                lstHoaDon = query.getResultList();
            } catch (NoResultException e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDon;
    }

    @Override
    public List<HoaDon> sellectAllHoaDonCho() {
        List<HoaDon> lstHoaDon = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT h FROM HoaDon h where h.trangThai = 3 order by h.ma desc";
            TypedQuery<HoaDon> query = session.createQuery(hql);

            try {
                lstHoaDon = query.getResultList();
            } catch (NoResultException e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDon;
    }

    @Override
    public List<HoaDon> selectAllHoaDonDaThanhToan() {
        List<HoaDon> lstHoaDon = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT h FROM HoaDon h where h.trangThai = 1 order by h.ma desc";
            TypedQuery<HoaDon> query = session.createQuery(hql);

            try {
                lstHoaDon = query.getResultList();

            } catch (NoResultException e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDon;
    }

    @Override
    public List<HoaDon> sellectAllHoaDonDangVanChuyen() {
        List<HoaDon> lstHoaDon = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT h FROM HoaDon h where h.trangThai = 2 order by h.ma desc";
            TypedQuery<HoaDon> query = session.createQuery(hql);
            try {
                lstHoaDon = query.getResultList();
            } catch (NoResultException e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDon;
    }

    @Override
    public List<HoaDon> sellectAllHoaDonDaHuy() {
        List<HoaDon> lstHoaDon = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT h FROM HoaDon h where h.trangThai = 0 ordery by h.ma desc";
            TypedQuery<HoaDon> query = session.createQuery(hql);

            try {
                lstHoaDon = query.getResultList();
            } catch (NoResultException e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDon;
    }
    
    @Override
    public List<HoaDonChiTiet> getAllByMaHD(String maHD) {
        List<HoaDonChiTiet> lstHoaDonCT = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT h FROM HoaDonChiTiet h where h.hoaDon.ma = :ma";
            TypedQuery<HoaDonChiTiet> query = session.createQuery(hql);
            query.setParameter("ma", maHD);
            try {
                lstHoaDonCT = query.getResultList();
            } catch (NoResultException e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDonCT;
    }

}
