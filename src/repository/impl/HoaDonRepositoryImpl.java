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
import model.PhieuNhapChiTiet;
import model.Sach;
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
    public boolean updateHoaDonChiTiet(List<HoaDonChiTiet> lstHoaDonCT, HoaDon hoaDon) {
        deleteHoaDonChiTiet(hoaDon);
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                int size = lstHoaDonCT.size();
                final int batchSize = 20;
                for (int i = 0; i < size; i++) {
                    HoaDonChiTiet hoaDonChiTiet = lstHoaDonCT.get(i);
                    session.save(hoaDonChiTiet);
                    if (i % batchSize == 0 && i != size && i != 0) {
                        session.flush();
                        session.clear();
                    }
                }
                tran.commit();
                return true;

            } catch (Exception e) {
                tran.rollback();
                return false;
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
    public List<HoaDon> sellectAllHoaDon(int trangThai){
        List<HoaDon> lstHoaDon = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT h FROM HoaDon h where h.trangThai = :trangThai order by len(h.ma) desc, h.ma desc";
            TypedQuery<HoaDon> query = session.createQuery(hql);
            query.setParameter("trangThai", trangThai);

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
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien4() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(hdct.soLuong * hdct.donGia) FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE MONTH(NgayThanhToan) = MONTH(DATEADD(MONTH, -4, getDate())) AND YEAR(NgayThanhToan) = YEAR(DATEADD(MONTH, -4, getDate())) AND h.trangThai =1";
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
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong4() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT hdct.soLuong FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE MONTH(NgayThanhToan) = MONTH(DATEADD(MONTH, -4, getDate())) AND YEAR(NgayThanhToan) = YEAR(DATEADD(MONTH, -4, getDate()))AND h.trangThai =1 ";
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
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsTongTien3() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(hdct.soLuong * hdct.donGia) FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE MONTH(NgayThanhToan) = MONTH(DATEADD(MONTH, -3, getDate())) AND YEAR(NgayThanhToan) = YEAR(DATEADD(MONTH, -3, getDate())) AND h.trangThai =1 ";
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
//WHERE MONTH(NgayThanhToan) = MONTH(DATEADD(MONTH, -1, getDate())) -2 AND YEAR(NgayThanhToan) = YEAR(DATEADD(MONTH, -1, getDate())) 

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsCoutSoLuong3() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT hdct.soLuong FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE MONTH(NgayThanhToan) = MONTH(DATEADD(MONTH, -3, getDate()))AND YEAR(NgayThanhToan) = YEAR(DATEADD(MONTH, -3, getDate())) AND h.trangThai =1";
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
            String hql = "SELECT SUM(hdct.soLuong * hdct.donGia) FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE MONTH(NgayThanhToan) = MONTH(DATEADD(MONTH, -2, getDate()))AND YEAR(NgayThanhToan) = YEAR(DATEADD(MONTH, -2, getDate())) AND h.trangThai =1";
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
            String hql = "SELECT hdct.soLuong FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE MONTH(NgayThanhToan) = MONTH(DATEADD(MONTH, -2, getDate()))AND YEAR(NgayThanhToan) = YEAR(DATEADD(MONTH, -2, getDate())) AND h.trangThai =1";
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
            String hql = "SELECT SUM(hdct.soLuong * hdct.donGia) FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE MONTH(NgayThanhToan) = MONTH(DATEADD(MONTH, -1, getDate()))AND YEAR(NgayThanhToan) = YEAR(DATEADD(MONTH, -1, getDate()))AND h.trangThai =1";
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
            String hql = "SELECT hdct.soLuong FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE MONTH(NgayThanhToan) = MONTH(DATEADD(MONTH, -1, getDate()))AND YEAR(NgayThanhToan) = YEAR(DATEADD(MONTH, -1, getDate()))AND h.trangThai =1";
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
            String hql = "SELECT SUM(hdct.soLuong * hdct.donGia) FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE DAY(h.ngayThanhToan)=DAY(GETDATE()) AND  MONTH(h.ngayThanhToan)= MONTH(GETDATE()) AND YEAR(h.ngayThanhToan)= YEAR(GETDATE())AND h.trangThai =1";
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
            String hql = "SELECT hdct.soLuong FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE DAY(h.ngayThanhToan)=DAY(GETDATE()) AND  MONTH(h.ngayThanhToan)= MONTH(GETDATE()) AND YEAR(h.ngayThanhToan)= YEAR(GETDATE())AND h.trangThai =1";
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
//WHERE DAY(hd.NgayThanhToan)=DAY(GETDATE()) AND  MONTH(hd.NgayThanhToan)= MONTH(GETDATE()) AND YEAR(hd.NgayThanhToan)= YEAR(GETDATE())

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeBieuDoTron() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT SUM(hdct.soLuong) FROM  Sach h inner join h.lstHoaDonCT hdct GROUP BY h.ten,h.id ORDER BY   SUM(hdct.soLuong) DESC";
            TypedQuery<HoaDonChiTiet> query = session.createQuery(hql);

            try {
                lstHoaDon = query.getResultList();

            } catch (NoResultException e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lstHoaDon;
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoTron() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT h.ten FROM  Sach h inner join h.lstHoaDonCT hdct GROUP BY h.ten,h.id ORDER BY   SUM(hdct.soLuong) DESC";
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

//FROM User u LEFT JOIN u.userProfile p WHERE u.id = :id
    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThuHienThiNgay() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                         SELECT h.ngayThanhToan FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE h.ngayThanhToan < GETDATE()-1
                                                  GROUP BY h.ngayThanhToan
                                                  ORDER BY h.ngayThanhToan DESC""";
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
    public List<PhieuNhapChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi1() {
        List<PhieuNhapChiTiet> lstPhieuNhap = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            var hql = """
                      SELECT Sum(s.giaNhap*pnct.soLuong) FROM  PhieuNhap pn inner join pn.lstPhieuNhapCT pnct inner join pnct.sach s WHERE DAY(pn.ngayNhap) = DAY(DATEADD(day, -1, getDate())) AND MONTH(pn.ngayNhap) = MONTH(DATEADD(day, -1, getDate()))AND YEAR(pn.ngayNhap) = YEAR(DATEADD(day, -1, getDate()))""";

            TypedQuery<PhieuNhapChiTiet> query = session.createQuery(hql);
            try {
                lstPhieuNhap = query.getResultList();
            } catch (NoResultException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstPhieuNhap;
    }

    @Override
    public List<PhieuNhapChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi2() {
        List<PhieuNhapChiTiet> lstPhieuNhap = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            var hql = """
                      SELECT Sum(s.giaNhap*pnct.soLuong) FROM  PhieuNhap pn inner join pn.lstPhieuNhapCT pnct inner join pnct.sach s WHERE DAY(pn.ngayNhap) = DAY(DATEADD(day, -2, getDate())) AND MONTH(pn.ngayNhap) = MONTH(DATEADD(day, -2, getDate()))AND YEAR(pn.ngayNhap) = YEAR(DATEADD(day, -2, getDate()))""";

            TypedQuery<PhieuNhapChiTiet> query = session.createQuery(hql);
            try {
                lstPhieuNhap = query.getResultList();
            } catch (NoResultException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstPhieuNhap;
    }

    @Override
    public List<PhieuNhapChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi3() {
        List<PhieuNhapChiTiet> lstPhieuNhap = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {

            var hql = """
                      SELECT Sum(s.giaNhap*pnct.soLuong) FROM  PhieuNhap pn inner join pn.lstPhieuNhapCT pnct inner join pnct.sach s WHERE DAY(pn.ngayNhap) = DAY(DATEADD(day, -3, getDate())) AND MONTH(pn.ngayNhap) = MONTH(DATEADD(day, -3, getDate()))AND YEAR(pn.ngayNhap) = YEAR(DATEADD(day, -3, getDate()))""";

            TypedQuery<PhieuNhapChiTiet> query = session.createQuery(hql);
            try {
                lstPhieuNhap = query.getResultList();
            } catch (NoResultException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstPhieuNhap;
    }

    @Override
    public List<PhieuNhapChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi4() {
        List<PhieuNhapChiTiet> lstPhieuNhap = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            var hql = """
                      SELECT Sum(s.giaNhap*pnct.soLuong) FROM  PhieuNhap pn inner join pn.lstPhieuNhapCT pnct inner join pnct.sach s WHERE DAY(pn.ngayNhap) = DAY(DATEADD(day, -4, getDate())) AND MONTH(pn.ngayNhap) = MONTH(DATEADD(day, -4, getDate()))AND YEAR(pn.ngayNhap) = YEAR(DATEADD(day, -4, getDate()))""";

            TypedQuery<PhieuNhapChiTiet> query = session.createQuery(hql);
            try {
                lstPhieuNhap = query.getResultList();
            } catch (NoResultException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstPhieuNhap;
    }

    @Override
    public List<PhieuNhapChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi5() {
        List<PhieuNhapChiTiet> lstPhieuNhap = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            var hql = """
                      SELECT Sum(s.giaNhap*pnct.soLuong) FROM  PhieuNhap pn inner join pn.lstPhieuNhapCT pnct inner join pnct.sach s WHERE DAY(pn.ngayNhap) = DAY(DATEADD(day, -5, getDate())) AND MONTH(pn.ngayNhap) = MONTH(DATEADD(day, -5, getDate()))AND YEAR(pn.ngayNhap) = YEAR(DATEADD(day, -5, getDate()))""";

            TypedQuery<PhieuNhapChiTiet> query = session.createQuery(hql);
            try {
                lstPhieuNhap = query.getResultList();
            } catch (NoResultException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstPhieuNhap;
    }

    @Override
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu1() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                         SELECT SUM(hdct.soLuong * hdct.donGia) FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE DAY(h.ngayThanhToan) = DAY(DATEADD(day, -1, getDate())) AND MONTH(h.ngayThanhToan) = MONTH(DATEADD(day, -1, getDate()))AND YEAR(h.ngayThanhToan) = YEAR(DATEADD(day, -1, getDate())) AND h.trangThai =1
                                                  GROUP BY h.ngayThanhToan """;
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
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu2() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                         SELECT SUM(hdct.soLuong * hdct.donGia) FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE DAY(h.ngayThanhToan) = DAY(DATEADD(day, -2, getDate())) AND MONTH(h.ngayThanhToan) = MONTH(DATEADD(day, -2, getDate()))AND YEAR(h.ngayThanhToan) = YEAR(DATEADD(day, -2, getDate())) AND h.trangThai =1
                                                  GROUP BY h.ngayThanhToan """;
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
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu3() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                         SELECT SUM(hdct.soLuong * hdct.donGia) FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE DAY(h.ngayThanhToan) = DAY(DATEADD(day, -3, getDate())) AND MONTH(h.ngayThanhToan) = MONTH(DATEADD(day, -3, getDate()))AND YEAR(h.ngayThanhToan) = YEAR(DATEADD(day, -3, getDate())) AND h.trangThai =1
                                                  GROUP BY h.ngayThanhToan """;
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
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu4() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                         SELECT SUM(hdct.soLuong * hdct.donGia) FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE DAY(h.ngayThanhToan) = DAY(DATEADD(day, -4, getDate())) AND MONTH(h.ngayThanhToan) = MONTH(DATEADD(day, -4, getDate()))AND YEAR(h.ngayThanhToan) = YEAR(DATEADD(day, -4, getDate())) AND h.trangThai =1
                                                  GROUP BY h.ngayThanhToan """;
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
    public List<HoaDonChiTiet> sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu5() {
        List<HoaDonChiTiet> lstHoaDon = new ArrayList<>();

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
                         SELECT SUM(hdct.soLuong * hdct.donGia) FROM  HoaDon h inner join h.lstHoaDonCT hdct WHERE DAY(h.ngayThanhToan) = DAY(DATEADD(day, -5, getDate())) AND MONTH(h.ngayThanhToan) = MONTH(DATEADD(day, -5, getDate()))AND YEAR(h.ngayThanhToan) = YEAR(DATEADD(day, -5, getDate())) AND h.trangThai =1
                                                  GROUP BY h.ngayThanhToan """;
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
    public HoaDon getByMaHD(String maHD) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT h FROM HoaDon h where h.ma = :ma";
            TypedQuery<HoaDon> query = session.createQuery(hql);
            query.setParameter("ma", maHD);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
