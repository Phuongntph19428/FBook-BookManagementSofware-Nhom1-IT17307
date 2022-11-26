/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.util.Properties;
import model.ChucVu;
import model.HinhThucThanhToan;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.KhuyenMai;
import model.KhuyenMaiChiTiet;
import model.NhaCungCap;
import model.NhaXuatBan;
import model.NhanVien;
import model.PhieuNhap;
import model.PhieuNhapChiTiet;
import model.Sach;
import model.SachTacGia;
import model.TacGia;
import model.TheLoai;
import model.TheLoaiChiTiet;
import model.ViTri;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author ppolo
 */
public class HibernateUtil {

    //Tạo session factory
    private static final SessionFactory FACTORY;

    static {
        //Configuration
        Configuration cfg = new Configuration();

        //Thuộc tính của configuration
        Properties prop = new Properties();
        prop.put(Environment.DIALECT, "org.hibernate.dialect.SQLServer2008Dialect");
        prop.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        prop.put(Environment.URL, "jdbc:sqlserver://DESKTOP-0AAN2UB\\SQLEXPRESS:1433;databaseName=PROP1041_QuanLySach;encrypt=true;trustServerCertificate=true;");
        prop.put(Environment.USER, "sa");
        prop.put(Environment.PASS, "123456");
        prop.put(Environment.SHOW_SQL, "true");
        
//        prop.put(Environment.HBM2DDL_AUTO, "create");

        //set property cho configuration
        cfg.setProperties(prop); 
        
        //mapping các class với table trong db
        cfg.addAnnotatedClass(NhanVien.class);
        cfg.addAnnotatedClass(ChucVu.class);
        cfg.addAnnotatedClass(HoaDon.class);
        cfg.addAnnotatedClass(HoaDonChiTiet.class);
        cfg.addAnnotatedClass(HinhThucThanhToan.class);
        cfg.addAnnotatedClass(KhachHang.class);
        cfg.addAnnotatedClass(KhuyenMai.class);
        cfg.addAnnotatedClass(KhuyenMaiChiTiet.class);
        cfg.addAnnotatedClass(NhaCungCap.class);
        cfg.addAnnotatedClass(NhaXuatBan.class);
        cfg.addAnnotatedClass(PhieuNhap.class);
        cfg.addAnnotatedClass(PhieuNhapChiTiet.class);
        cfg.addAnnotatedClass(Sach.class);
        cfg.addAnnotatedClass(SachTacGia.class);
        cfg.addAnnotatedClass(TacGia.class);
        cfg.addAnnotatedClass(TheLoai.class);
        cfg.addAnnotatedClass(TheLoaiChiTiet.class);
        cfg.addAnnotatedClass(ViTri.class);
        
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        FACTORY = cfg.buildSessionFactory(registry);
    }

    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }

    //close session factory
    public static void shutdown() {
        getSessionFactory().close();
    }
    
    public static void main(String[] args) {
        getSessionFactory();        
    }
}
