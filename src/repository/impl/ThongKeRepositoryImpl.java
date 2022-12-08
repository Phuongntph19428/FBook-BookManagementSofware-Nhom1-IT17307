/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import custommodel.CustomThuChi;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.KhachHang;
import model.NhanVien;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author ppolo
 */
public class ThongKeRepositoryImpl implements repository.ThongKeRepository {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    @Override
    public List<CustomThuChi> getAll(Date tuNgay, Date denNgay) {
        List<CustomThuChi> lstCustomThuChi = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select 'Hoa don', hd.ma, hd.ngayThanhToan, sum(hdct.soLuong * hdct.donGia) "
                    + "from HoaDon hd inner join hd.lstHoaDonCT hdct "
                    + "where hd.trangThai = 1 and hd.ngayThanhToan between :tuNgay and :denNgay group by hd.ma, hd.ngayThanhToan";
            Query query = session.createQuery(hql);
            query.setParameter("tuNgay", tuNgay);
            query.setParameter("denNgay", denNgay);

            List results = query.getResultList();
            for (Object object : results) {
                Object[] element = (Object[]) object;
                CustomThuChi custom = new CustomThuChi(element[0] + "", element[1] + "", sdf.parse(element[2] + ""), BigDecimal.valueOf(Double.parseDouble(element[3] + "")));
                lstCustomThuChi.add(custom);
            }

            hql = "select 'Phieu nhap', pn.ma, pn.ngayNhap, sum(pnct.soLuong * s.giaNhap) "
                    + "from PhieuNhap pn inner join pn.lstPhieuNhapCT pnct inner join pnct.sach s "
                    + "where pn.trangThai = 1 and pn.ngayNhap between :tuNgay and :denNgay group by pn.ma, pn.ngayNhap";
            query = session.createQuery(hql);
            query.setParameter("tuNgay", tuNgay);
            query.setParameter("denNgay", denNgay);
            results = query.getResultList();
            for (Object object : results) {
                Object[] element = (Object[]) object;
                Date ngay = null;
                if((element[2] + "").matches("\\d{4}-\\d{2}-\\d{2}")) {
                    ngay = new SimpleDateFormat("yyyy-MM-dd").parse(element[2] + "");
                } else {
                    ngay = sdf.parse(element[2] + "");
                }
                CustomThuChi custom = new CustomThuChi(element[0] + "", element[1] + "", ngay, BigDecimal.valueOf(Double.parseDouble(element[3] + "")));
                lstCustomThuChi.add(custom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCustomThuChi;
    }

    @Override
    public List<CustomThuChi> getAllByNhanVien(NhanVien nhanVien, Date tuNgay, Date denNgay) {
        List<CustomThuChi> lstCustomThuChi = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select 'Hoa don', hd.ma, hd.ngayThanhToan, sum(hdct.soLuong * hdct.donGia) "
                    + "from HoaDon hd inner join hd.lstHoaDonCT hdct "
                    + "where hd.trangThai = 1 and hd.ngayThanhToan between :tuNgay and :denNgay and hd.nhanVien = :nhanVien group by hd.ma, hd.ngayThanhToan";
            Query query = session.createQuery(hql);
            query.setParameter("tuNgay", tuNgay);
            query.setParameter("denNgay", denNgay);
            query.setParameter("nhanVien", nhanVien);

            List results = query.getResultList();
            for (Object object : results) {
                Object[] element = (Object[]) object;
                CustomThuChi custom = new CustomThuChi(element[0] + "", element[1] + "", sdf.parse(element[2] + ""), BigDecimal.valueOf(Double.parseDouble(element[3] + "")));
                lstCustomThuChi.add(custom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCustomThuChi;

    }

    @Override
    public List<CustomThuChi> getAllByKhachhang(KhachHang khachHang, Date tuNgay, Date denNgay) {

        List<CustomThuChi> lstCustomThuChi = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select 'Hoa don', hd.ma, hd.ngayThanhToan, sum(hdct.soLuong * hdct.donGia) "
                    + "from HoaDon hd inner join hd.lstHoaDonCT hdct "
                    + "where hd.trangThai = 1 and hd.ngayThanhToan between :tuNgay and :denNgay and hd.khachHang = :khachHang group by hd.ma, hd.ngayThanhToan";
            Query query = session.createQuery(hql);
            query.setParameter("tuNgay", tuNgay);
            query.setParameter("denNgay", denNgay);
            query.setParameter("khachHang", khachHang);

            List results = query.getResultList();
            for (Object object : results) {
                Object[] element = (Object[]) object;
                CustomThuChi custom = new CustomThuChi(element[0] + "", element[1] + "", sdf.parse(element[2] + ""), BigDecimal.valueOf(Double.parseDouble(element[3] + "")));
                lstCustomThuChi.add(custom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCustomThuChi;
    }


    @Override
    public List<CustomThuChi> getAllChi(Date tuNgay, Date denNgay) {

        List<CustomThuChi> lstCustomThuChi = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "select 'Phieu nhap', pn.ma, pn.ngayNhap, sum(pnct.soLuong * s.giaNhap) "
                    + "from PhieuNhap pn inner join pn.lstPhieuNhapCT pnct inner join pnct.sach s "
                    + "where pn.trangThai = 1 and pn.ngayNhap between :tuNgay and :denNgay group by pn.ma, pn.ngayNhap";
            Query query = session.createQuery(hql);
            query.setParameter("tuNgay", tuNgay);
            query.setParameter("denNgay", denNgay);
            List results = query.getResultList();
            
            for (Object object : results) {
                Object[] element = (Object[]) object;
                Date ngay = null;
                if((element[2] + "").matches("\\d{4}-\\d{2}-\\d{2}")) {
                    ngay = new SimpleDateFormat("yyyy-MM-dd").parse(element[2] + "");
                } else {
                    ngay = sdf.parse(element[2] + "");
                }
                CustomThuChi custom = new CustomThuChi(element[0] + "", element[1] + "", ngay, BigDecimal.valueOf(Double.parseDouble(element[3] + "")));
                lstCustomThuChi.add(custom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCustomThuChi;
    }

    @Override
    public List<CustomThuChi> getAllThu(Date tuNgay, Date denNgay) {
        List<CustomThuChi> lstCustomThuChi = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "select 'Hoa don', hd.ma, hd.ngayThanhToan, sum(hdct.soLuong * hdct.donGia) "
                    + "from HoaDon hd inner join hd.lstHoaDonCT hdct "
                    + "where hd.trangThai = 1 and hd.ngayThanhToan between :tuNgay and :denNgay group by hd.ma, hd.ngayThanhToan";
            Query query = session.createQuery(hql);
            query.setParameter("tuNgay", tuNgay);
            query.setParameter("denNgay", denNgay);
            List results = query.getResultList();
            for (Object object : results) {
                Object[] element = (Object[]) object;
                CustomThuChi custom = new CustomThuChi(element[0] + "", element[1] + "", sdf.parse(element[2] + ""), BigDecimal.valueOf(Double.parseDouble(element[3] + "")));
                lstCustomThuChi.add(custom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCustomThuChi;
    }
}
