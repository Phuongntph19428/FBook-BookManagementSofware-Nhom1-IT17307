/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import custommodel.ThuChiModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import repository.ThuChiRepository;
import util.HibernateUtil;

/**
 *
 * @author Dieu Linh
 */
public class ThuChiRepositoryImpl implements ThuChiRepository {

    @Override
    public List<String> cbbNhanVien() {
        List<String> cbbNhanVien = new ArrayList<>();
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select ten from NhanVien";
            TypedQuery<String> query = s.createQuery(hql, String.class);
            cbbNhanVien = query.getResultList();
            s.close();
        }
        return cbbNhanVien;
    }

    @Override
    public List<String> cbbKhachHang() {
        List<String> cbbKhachHang = new ArrayList<>();
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select ten from KhachHang";
            TypedQuery<String> query = s.createQuery(hql, String.class);
            cbbKhachHang = query.getResultList();
            s.close();
        }
        return cbbKhachHang;
    }

//    @Override
//    public List<ThuChiModel> dsThuChi(int loaiThuChi, Date ngayBatDau, Date ngayKetThuc, int loaiNguoi) {
//       List<ThuChiModel> ds = new ArrayList<>();
//       try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
//            String hql = "select ten from NhanVien";
//            TypedQuery<String> query = s.createQuery(hql, String.class);
////            cbbNhanVien = query.getResultList();
//            s.close();
//        }
//       
//       return ds;
//    }
    @Override
    public List<ThuChiModel> getThuNhanVien(String idNhanVien, Date ngayBatDau, Date ngayKetThuc) {
        List<ThuChiModel> thuNhanVien = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT IdNhanVien,ten, sum(soLuong*DonGia) as tienThu, ngayThanhToan FROM HoaDon, HoaDonChiTiet, NhanVien  "
                    + "WHERE  HoaDon.id = Hoadonchitiet.idhoadon "
                    + "AND  NhanVien.Id = HoaDon.IdNhanVien "
                    + "AND (idNhanvien= :idNhanVien OR  :idNhanVien IS NULL) "
                    + "AND (NgayThanhToan >= :ngayBatDau OR :ngayBatDau IS NULL) "
                    + "AND (NgayThanhToan <= :ngayKetThuc OR :ngayKetThuc IS NULL) "
                    + "GROUP BY IdNhanVien, ngayThanhToan, ten";

            List<Object> abc = session.createNativeQuery(hql)
                    .setParameter("idNhanVien", idNhanVien)
                    .setParameter("ngayBatDau", ngayBatDau, TemporalType.DATE)
                    .setParameter("ngayKetThuc", ngayKetThuc, TemporalType.DATE)
                    .getResultList();

            SimpleDateFormat ngay = new SimpleDateFormat("dd-mm-yyyy");
            for (Object object : abc) {
                Object[] tt = (Object[]) object;
                String id = tt[0] + "";
                String ten = tt[1] + "";
                Double tienThu = Double.parseDouble(tt[2] + "");
                Date ngayThanhToan = (Date) tt[3];
                ThuChiModel thuChiModel = new ThuChiModel(idNhanVien, ten, tienThu, ngayThanhToan);
                thuNhanVien.add(thuChiModel);
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thuNhanVien;
    }

    @Override
    public List<ThuChiModel> getKhachHang(String idKhachHang, Date ngayBatDau, Date ngayKetThuc) {
        List<ThuChiModel> thuNhanVien = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT IdKhachHang,ten, sum(soLuong*DonGia) as tienThu, ngayThanhToan FROM HoaDon, HoaDonChiTiet, KhachHang  "
                    + "WHERE  HoaDon.id = Hoadonchitiet.idhoadon "
                    + "AND  KhachHang.Id = HoaDon.IdKhachHang "
                    + "AND (idKhachHang= :idKhachHang OR  :idKhachHang IS NULL) "
                    + "AND (NgayThanhToan >= :ngayBatDau OR :ngayBatDau IS NULL) "
                    + "AND (NgayThanhToan <= :ngayKetThuc OR :ngayKetThuc IS NULL) "
                    + "GROUP BY IdKhachHang, ngayThanhToan, ten";

            List<Object> abc = session.createNativeQuery(hql)
                    .setParameter("idKhachHang", idKhachHang)
                    .setParameter("ngayBatDau", ngayBatDau, TemporalType.DATE)
                    .setParameter("ngayKetThuc", ngayKetThuc, TemporalType.DATE)
                    .getResultList();

            SimpleDateFormat ngay = new SimpleDateFormat("dd-mm-yyyy");
            for (Object object : abc) {
                Object[] tt = (Object[]) object;
                String id = tt[0] + "";
                String ten = tt[1] + "";
                Double tienThu = Double.parseDouble(tt[2] + "");
                Date ngayThanhToan = (Date) tt[3];
                ThuChiModel thuChiModel = new ThuChiModel(idKhachHang, ten, tienThu, ngayThanhToan);
                thuNhanVien.add(thuChiModel);
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thuNhanVien;
    }

    @Override
    public String findById(String ten) {
        String uuid;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String statement = "select p.id from NhanVien p where p.ten = :ten";
            TypedQuery<String> query = session.createQuery(statement, String.class);
            query.setParameter("ten", ten);
            uuid = query.getSingleResult();
        }
        return uuid;

    }

    @Override
    public List<ThuChiModel> tienChi(Date ngayBatDau, Date ngayKetThuc) {
        List<ThuChiModel> thuNhanVien = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT sum (PhieuNhapChiTiet.soLuong* giaNhap) as tienChi , ngayNhap FROM PhieuNhap, PhieuNhapChiTiet, Sach  "
                    + "WHERE  PhieuNhap.id = PhieuNhapChiTiet.idPhieuNhap "
                    + "AND  Sach.Id = PhieuNhapChiTiet.IdSach "
                    + "AND (NgayNhap >= :ngayBatDau OR :ngayBatDau IS NULL) "
                    + "AND (NgayNhap <= :ngayKetThuc OR :ngayKetThuc IS NULL) "
                    + "GROUP BY  NgayNhap";

            List<Object> abc = session.createNativeQuery(hql)
                    .setParameter("ngayBatDau", ngayBatDau, TemporalType.DATE)
                    .setParameter("ngayKetThuc", ngayKetThuc, TemporalType.DATE)
                    .getResultList();

            SimpleDateFormat ngay = new SimpleDateFormat("dd-mm-yyyy");
            for (Object object : abc) {
                Object[] tt = (Object[]) object;
                Double tienThu = Double.parseDouble(tt[0] + "");
                Date ngayThanhToan = (Date) tt[1];
                ThuChiModel thuChiModel = new ThuChiModel( tienThu, ngayThanhToan);
                thuNhanVien.add(thuChiModel);
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thuNhanVien;
    }

}
