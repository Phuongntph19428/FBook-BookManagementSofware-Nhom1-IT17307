/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.math.BigDecimal;
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
                Query query = session.createSQLQuery("{CALL proc_deleteAllHDCT (:idHoaDon)}");
                query.setParameter("idHoaDon", hoaDon.getId());

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
            String hql = "SELECT h.ma FROM HoaDon h order by len(h.ma) desc, h.ma desc";
            TypedQuery<String> query = session.createQuery(hql);

            try {
                List<String> lst = query.getResultList();
                ma = lst.get(0);
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
            String hql = "SELECT h FROM HoaDon h order by len(h.ma) desc, h.ma desc";
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
            String hql = "SELECT h FROM HoaDon h where h.trangThai = 3 order by len(h.ma) desc, h.ma desc";
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
            String hql = "SELECT h FROM HoaDon h where h.trangThai = 1 order by len(h.ma) desc, h.ma desc";
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
            String hql = "SELECT h FROM HoaDon h where h.trangThai = 2 order by len(h.ma) desc, h.ma desc";
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
            String hql = "SELECT h FROM HoaDon h where h.trangThai = 0 ordery by len(h.ma) desc, h.ma desc";
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

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien3() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(hdct.soLuong * hdct.donGia) FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE MONTH(h.ngayThanhToan) = MONTH(GETDATE()) -3";
            TypedQuery<HoaDonChiTiet> query = session.createQuery(hql);

            try {
                lstHoaDon = query.getResultList();

            } catch (NoResultException e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDon;
    }

//    public static void main(String[] args) {
//        HoaDonRepositoryImpl donRepositoryImpl = new HoaDonRepositoryImpl();
//        System.out.println(donRepositoryImpl.sellectAllHoaDonChiTietsCoutSoLuong3());
//        List<HoaDonChiTiet> chiTiets = new ArrayList<>();
//        chiTiets = donRepositoryImpl.sellectAllHoaDonChiTietsCoutSoLuong3();
//
//        int count = 0;
//        for (int i = 0; i < chiTiets.size(); i++) {
//
//            count++;
//
//        }
//        System.out.println(count);
//
//    }
    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong3() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT hdct.soLuong FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE MONTH(h.ngayThanhToan) = MONTH(GETDATE()) -3";
            TypedQuery<HoaDonChiTiet> query = session.createQuery(hql);

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
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien2() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(hdct.soLuong * hdct.donGia) FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE MONTH(h.ngayThanhToan) = MONTH(GETDATE()) -2";
            TypedQuery<HoaDonChiTiet> query = session.createQuery(hql);

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
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong2() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT hdct.soLuong FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE MONTH(h.ngayThanhToan) = MONTH(GETDATE()) -2";
            TypedQuery<HoaDonChiTiet> query = session.createQuery(hql);

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
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien1() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(hdct.soLuong * hdct.donGia) FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE MONTH(h.ngayThanhToan) = MONTH(GETDATE()) -1";
            TypedQuery<HoaDonChiTiet> query = session.createQuery(hql);

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
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong1() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT hdct.soLuong FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE MONTH(h.ngayThanhToan) = MONTH(GETDATE()) -1";
            TypedQuery<HoaDonChiTiet> query = session.createQuery(hql);

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
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien0() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(hdct.soLuong * hdct.donGia) FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE DAY(h.ngayThanhToan) = DAY(GETDATE())";
            TypedQuery<HoaDonChiTiet> query = session.createQuery(hql);

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
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong0() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT hdct.soLuong FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE DAY(h.ngayThanhToan) = DAY(GETDATE())";
            TypedQuery<HoaDonChiTiet> query = session.createQuery(hql);

            try {
                lstHoaDon = query.getResultList();

            } catch (NoResultException e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDon;
    }
}
