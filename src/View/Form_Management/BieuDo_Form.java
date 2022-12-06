/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import View.ColorFrame;
import View.DesignComponent.DC_ModelBieuDo;
import View.DesignComponent.ModelPieChart;
import View.ManagementBookForm;
import com.fasterxml.jackson.datatype.jsr310.deser.MonthDayDeserializer;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.HoaDonChiTiet;

import service.HoaDonService;
import service.impl.HoaDonServiceImpl;

/**
 *
 * @author quanc
 */
public class BieuDo_Form extends javax.swing.JPanel {

    private HoaDonService donService;
    private List<HoaDonChiTiet> list3;
    private List<HoaDonChiTiet> list2;
    private List<HoaDonChiTiet> list1;

    public BieuDo_Form() {
        initComponents();
        Icon iconDS = new ImageIcon("image/icons8_total_sales_30px_1.png");
        Icon iconTopSell = new ImageIcon("image/icons8_best_seller_30px_1.png");
        Icon iconHDDays = new ImageIcon("image/icons8_purchase_order_40px.png");
        Icon iconBooks = new ImageIcon("image/icons8_books_40px.png");
        Icon iconDoanhThu = new ImageIcon("image/icons8_money_40px.png");
        Icon iconIn = new ImageIcon("image/icons8_print_25px.png");
        Icon iconPhantram1 = new ImageIcon("image/icons8_up_20px_1.png");
        Icon iconPhantram2 = new ImageIcon("image/icons8_down_30px.png");
        Icon iconTopSeller = new ImageIcon("image/icons8_best_seller_26px_1.png");
        Icon iconThuChi = new ImageIcon("image/icons8_receive_dollar_26px.png");
        this.lblPhanTram3.setIcon(iconPhantram1);
        this.lblPhanTram2.setIcon(iconPhantram1);
        this.lblPhanTram1.setIcon(iconPhantram2);
        this.lblPhanTram0.setIcon(iconPhantram1);
        this.lbTopSell.setIcon(iconTopSeller);
        this.lbThuChi.setIcon(iconThuChi);

        chart.addLegend("Tiền Thu", Color.GREEN);
        chart.addLegend("Tiền Chi", Color.RED);
   
        loadData();

    }
    
    public void loadData() {
        pieChart1.clearData();
        chart.clear();
        chart.start();
        donService = new HoaDonServiceImpl();
        list3 = new ArrayList<>();
        list2 = new ArrayList<>();
        list1 = new ArrayList<>();
        
        daTaBieuDoCot();
        tongSoLuong0();
        tongSoLuong1();
        tongSoLuong2();
        tongSoLuong3();
        thang();

        tongTien3Thang();
        phanTramDoanhThuTheoThang3();
        phanTramDoanhThuTheoThang2();
        phanTramDoanhThuTheoThang4();
//        lblPhanTram0.setText("-");
        daTaBieuDoTron();
    }

    private void daTaBieuDoCot() {

        // Định dạng thời gian
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Calendar c3 = Calendar.getInstance();
        Calendar c4 = Calendar.getInstance();
        Calendar c5 = Calendar.getInstance();

        // Định nghĩa mốc thời gian ban đầu là ngày 31-07-2011
//        System.out.println("Ngày ban đầu : " + dateFormat.format(c1.getTime()));
        // Tăng ngày thêm 8 ngày -- Sử dụng phương thức roll()
        c1.roll(Calendar.DAY_OF_YEAR, -1);
        c2.roll(Calendar.DAY_OF_YEAR, -2);
        c3.roll(Calendar.DAY_OF_YEAR, -3);
        c4.roll(Calendar.DAY_OF_YEAR, -4);
        c5.roll(Calendar.DAY_OF_YEAR, -5);

//        // c1.roll(Calendar.DATE, -8); // Giảm ngày 8 ngày ==> 23-07-2011
//        System.out.println("Ngày được tăng thêm 8 ngày (Sử dụng Roll) : " + dateFormat.format(c1.getTime()));
        dateFormat.format(c1.getTime());

//            chart.addData(new DC_ModelBieuDo(String.valueOf(sdf.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThuHienThiNgay().get(0))), new double[]{Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu().get(0))), 0, 1}));
//            chart.addData(new DC_ModelBieuDo(String.valueOf(sdf.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThuHienThiNgay().get(1))), new double[]{Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu().get(1))), 0, 300}));
//            chart.addData(new DC_ModelBieuDo(String.valueOf(sdf.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThuHienThiNgay().get(2))), new double[]{Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu().get(2))), 0, 400}));
//            chart.addData(new DC_ModelBieuDo(String.valueOf(sdf.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThuHienThiNgay().get(3))), new double[]{Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu().get(3))), 0, 600}));
//            chart.addData(new DC_ModelBieuDo(String.valueOf(sdf.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThuHienThiNgay().get(4))), new double[]{Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu().get(4))), 0, 700}));

        try {
            if (donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi1().get(0) == null) {
                chart.addData(new DC_ModelBieuDo(dateFormat.format(c1.getTime()), new double[]{Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu1().get(0))), 0, 1}));
            } else {
                try {
                    chart.addData(new DC_ModelBieuDo(dateFormat.format(c1.getTime()), new double[]{Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu1().get(0))), Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi1().get(0))), 1}));
                } catch (Exception e) {
                    chart.addData(new DC_ModelBieuDo(dateFormat.format(c1.getTime()), new double[]{0, Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi1().get(0))), 1}));
                }
            }

        } catch (Exception e) {
            chart.addData(new DC_ModelBieuDo(dateFormat.format(c1.getTime()), new double[]{0, 0, 1}));
        }
//
        try {
            if (donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi2().get(0) == null) {
                chart.addData(new DC_ModelBieuDo(dateFormat.format(c2.getTime()), new double[]{Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu2().get(0))), 0, 1}));
            } else {
                try {
                    chart.addData(new DC_ModelBieuDo(dateFormat.format(c2.getTime()), new double[]{Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu2().get(0))), Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi2().get(0))), 1}));
                } catch (Exception e) {
                    chart.addData(new DC_ModelBieuDo(dateFormat.format(c2.getTime()), new double[]{0, Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi2().get(0))), 1}));

                }
            }

        } catch (Exception e) {
            chart.addData(new DC_ModelBieuDo(dateFormat.format(c2.getTime()), new double[]{0, 0, 1}));
        }
        try {
            if (donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi3().get(0) == null) {
                chart.addData(new DC_ModelBieuDo(dateFormat.format(c3.getTime()), new double[]{Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu3().get(0))), 0, 1}));
            } else {
                try {
                    chart.addData(new DC_ModelBieuDo(dateFormat.format(c3.getTime()), new double[]{Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu3().get(0))), Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi3().get(0))), 1}));
                } catch (Exception e) {
                    chart.addData(new DC_ModelBieuDo(dateFormat.format(c3.getTime()), new double[]{0, Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi3().get(0))), 1}));

                }
            }

        } catch (Exception e) {
            chart.addData(new DC_ModelBieuDo(dateFormat.format(c3.getTime()), new double[]{0, 0, 1}));
        }

        try {
            if (donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi4().get(0) == null) {
                chart.addData(new DC_ModelBieuDo(dateFormat.format(c4.getTime()), new double[]{Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu4().get(0))), 0, 1}));
            } else {
                try {
                    chart.addData(new DC_ModelBieuDo(dateFormat.format(c4.getTime()), new double[]{Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu4().get(0))), Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi4().get(0))), 1}));
                } catch (Exception e) {
                    chart.addData(new DC_ModelBieuDo(dateFormat.format(c4.getTime()), new double[]{0, Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi4().get(0))), 1}));

                }
            }

        } catch (Exception e) {
            chart.addData(new DC_ModelBieuDo(dateFormat.format(c4.getTime()), new double[]{0, 0, 1}));
        }

        try {
            if (donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi5().get(0) == null) {
                chart.addData(new DC_ModelBieuDo(dateFormat.format(c5.getTime()), new double[]{Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu5().get(0))), 0, 1}));
            } else {
                try {
                    chart.addData(new DC_ModelBieuDo(dateFormat.format(c5.getTime()), new double[]{Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu5().get(0))), Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi5().get(0))), 1}));
                } catch (Exception e) {
                    chart.addData(new DC_ModelBieuDo(dateFormat.format(c5.getTime()), new double[]{0, Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi5().get(0))), 1}));

                }
            }

        } catch (Exception e) {
            chart.addData(new DC_ModelBieuDo(dateFormat.format(c5.getTime()), new double[]{0, 0, 1}));
        }
    }

    private void daTaBieuDoTron() {

        try {
            pieChart1.addData(new ModelPieChart(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoTron().get(0)), Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeBieuDoTron().get(0))), new Color(255, 188, 0)));

        } catch (Exception e) {
        }
        try {
            pieChart1.addData(new ModelPieChart(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoTron().get(1)), Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeBieuDoTron().get(1))), new Color(250, 92, 124)));

        } catch (Exception e) {
        }
        try {
            pieChart1.addData(new ModelPieChart(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoTron().get(2)), Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeBieuDoTron().get(2))), new Color(10, 207, 151)));

        } catch (Exception e) {
        }
        try {
            pieChart1.addData(new ModelPieChart(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoTron().get(3)), Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeBieuDoTron().get(3))), new Color(114, 124, 245)));

        } catch (Exception e) {
        }
        try {
            pieChart1.addData(new ModelPieChart(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoTron().get(4)), Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsThongKeBieuDoTron().get(4))), new Color(139, 69, 0)));

        } catch (Exception e) {
        }
    }

    private void thang() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Calendar c3 = Calendar.getInstance();

 
        c1.roll(Calendar.MONTH, -1);
        c2.roll(Calendar.MONTH, -2);
        c3.roll(Calendar.MONTH, -3);


        dateFormat.format(c1.getTime());

        LocalDateTime localDate = LocalDateTime.now();
//        int month3 = localDate.getMonthValue() - 3;
        lblThang3.setText(String.valueOf("Tháng " + dateFormat.format(c3.getTime())));
//        int month2 = localDate.getMonthValue() - 2;
        lblThang2.setText(String.valueOf("Tháng " + dateFormat.format(c2.getTime())));
//        int month1 = localDate.getMonthValue() - 1;
        lblThang1.setText(String.valueOf("Tháng " + dateFormat.format(c1.getTime())));
        int ngay = localDate.getDayOfMonth();
        int thang = localDate.getMonthValue();
        int nam = localDate.getYear();
        int gio = localDate.getHour();
        int phut = localDate.getMinute();
        cbNgayHienTai.addItem(ngay + "-" + thang + "-" + nam + "-" + gio + ":" + phut);

    }

    private void tongSoLuong3() {
        list3 = donService.sellectAllHoaDonChiTietsCoutSoLuong3();
        int count = 0;
        for (int i = 0; i < list3.size(); i++) {

            count++;

        }
        lblHoaDon3.setText(String.valueOf(count));

    }

    private void tongSoLuong2() {
        list3 = donService.sellectAllHoaDonChiTietsCoutSoLuong2();
        int count = 0;
        for (int i = 0; i < list3.size(); i++) {

            count++;

        }
        lblHoaDon2.setText(String.valueOf(count));

    }

    private void tongSoLuong1() {
        list3 = donService.sellectAllHoaDonChiTietsCoutSoLuong1();
        int count = 0;
        for (int i = 0; i < list3.size(); i++) {

            count++;

        }
        lblHoaDon1.setText(String.valueOf(count));

    }

    private void tongSoLuong0() {
        list3 = donService.sellectAllHoaDonChiTietsCoutSoLuong0();
        int count = 0;
        for (int i = 0; i < list3.size(); i++) {

            count++;

        }
        lblHoaDon0.setText(String.valueOf(count));

    }

    private void phanTramDoanhThuTheoThang3() {
        try {
            DecimalFormat df = new DecimalFormat("####");
            double a = Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsTongTien2().get(0)));
            double b = Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsTongTien3().get(0)));
            double c = ((a - b) / (a < b ? a : b)) * 100;

            if (c < 0) {
                lblPhanTram2.setForeground(Color.RED);
                lblPhanTram2.setText(df.format(c) + "%");
                lblgach2.setBackground(Color.RED);

            } else if (c >= 0) {
                lblPhanTram2.setForeground(Color.GREEN);
                lblPhanTram2.setText(df.format(c) + "%");
                lblgach2.setBackground(Color.GREEN);

            }
        } catch (Exception e) {
            lblPhanTram2.setForeground(Color.red);
            lblPhanTram2.setText("-100" + "%");
            lblgach2.setBackground(Color.RED);

        }

    }

    private void phanTramDoanhThuTheoThang2() {
        try {
            DecimalFormat df = new DecimalFormat("####");
            double a = Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsTongTien1().get(0)));
            double b = Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsTongTien2().get(0)));
            double c = ((a - b) / (a < b ? a : b)) * 100;
            lblPhanTram1.setText(df.format(c) + "%");
            if (c < 0) {
                lblPhanTram1.setForeground(Color.RED);
                lblPhanTram1.setText(df.format(c) + "%");
                lblgach1.setBackground(Color.RED);
            } else if (c >= 0) {
                lblPhanTram1.setForeground(Color.GREEN);
                lblPhanTram1.setText(df.format(c) + "%");
                lblgach1.setBackground(Color.GREEN);

            }
        } catch (Exception e) {
            lblPhanTram1.setText("-100" + "%");
            lblPhanTram1.setForeground(Color.red);
            lblgach1.setBackground(Color.RED);
        }

    }

    private void phanTramDoanhThuTheoThang4() {
        try {
            DecimalFormat df = new DecimalFormat("####");
            double a = Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsTongTien3().get(0)));
            double b = Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsTongTien4().get(0)));
            double c = ((a - b) / (a < b ? a : b)) * 100;
            lblPhanTram3.setText(df.format(c) + "%");
            if (c < 0) {
                lblPhanTram3.setForeground(Color.RED);
                lblPhanTram3.setText(df.format(c) + "%");
                lblgach3.setBackground(Color.RED);
            } else if (c >= 0) {
                lblPhanTram3.setForeground(Color.GREEN);
                lblPhanTram3.setText(df.format(c) + "%");
                lblgach3.setBackground(Color.GREEN);

            }
        } catch (Exception e) {
            lblPhanTram3.setForeground(Color.red);
            lblPhanTram3.setText("-100" + "%");
            lblgach3.setBackground(Color.RED);

        }

    }

    private void tongTien3Thang() {
        DecimalFormat df = new DecimalFormat("#,###");
        if (donService.sellectAllHoaDonChiTietsTongTien3().get(0) == null) {
            lblDoanhThu3.setText("0.0");
        } else {
            lblDoanhThu3.setText(String.valueOf(df.format(donService.sellectAllHoaDonChiTietsTongTien3().get(0))));
        }
        if (donService.sellectAllHoaDonChiTietsTongTien1().get(0) == null) {
            lblDoanhThu1.setText("0.0");
        } else {
            lblDoanhThu1.setText(String.valueOf(df.format(donService.sellectAllHoaDonChiTietsTongTien1().get(0))));
        }
        if (donService.sellectAllHoaDonChiTietsTongTien2().get(0) == null) {
            lblDoanhThu2.setText("0.0");
        } else {
            lblDoanhThu2.setText(String.valueOf(df.format(donService.sellectAllHoaDonChiTietsTongTien2().get(0))));
        }
        if (donService.sellectAllHoaDonChiTietsTongTien0().get(0) == null) {
            lblDoanhThu0.setText("0.0");
        } else {
            lblDoanhThu0.setText(String.valueOf(df.format(donService.sellectAllHoaDonChiTietsTongTien0().get(0))));
        }

    }

    public void startTrungGian() {
        chart.start();
    }

    // chart.start();
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        panelHoaDon3 = new View.DesignComponent.JPanelBourder();
        lblThang3 = new javax.swing.JLabel();
        lbHoaDon = new javax.swing.JLabel();
        lblgach3 = new javax.swing.JPanel();
        lblHoaDon3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblDoanhThu3 = new javax.swing.JLabel();
        lblPhanTram3 = new javax.swing.JLabel();
        panelHoaDon2 = new View.DesignComponent.JPanelBourder();
        lblThang2 = new javax.swing.JLabel();
        lblgach2 = new javax.swing.JPanel();
        lblPhanTram2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblHoaDon2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblDoanhThu2 = new javax.swing.JLabel();
        panelHoaDon1 = new View.DesignComponent.JPanelBourder();
        lblThang1 = new javax.swing.JLabel();
        HoaDon4 = new javax.swing.JLabel();
        lblgach1 = new javax.swing.JPanel();
        lblPhanTram1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblHoaDon1 = new javax.swing.JLabel();
        lblDoanhThu1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        panelHoaDon0 = new View.DesignComponent.JPanelBourder();
        lblThang0 = new javax.swing.JLabel();
        HoaDon5 = new javax.swing.JLabel();
        lblgach0 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        lblHoaDon0 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblDoanhThu0 = new javax.swing.JLabel();
        lblPhanTram0 = new javax.swing.JLabel();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        pieChart1 = new View.DesignComponent.PieChart();
        lbTopSell = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelBourder3 = new View.DesignComponent.JPanelBourder();
        lbThuChi = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        chart = new View.DesignComponent.BieuDo();
        jLabel13 = new javax.swing.JLabel();
        cbNgayHienTai = new View.DesignComponent.Combobox();
        btnInBaoCao = new View.ButtonDesign.Button();
        jLabel8 = new javax.swing.JLabel();
        btnInBaoCao1 = new View.ButtonDesign.Button();

        setBackground(new java.awt.Color(11, 20, 55));
        setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        setPreferredSize(new java.awt.Dimension(1373, 770));

        jPanel4.setBackground(new java.awt.Color(11, 20, 55));

        panelHoaDon3.setBackground(new java.awt.Color(17, 28, 68));

        lblThang3.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblThang3.setForeground(new java.awt.Color(255, 255, 255));
        lblThang3.setText("Tháng 9");

        lbHoaDon.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lbHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lbHoaDon.setText("Hóa Đơn    -");

        lblgach3.setBackground(new java.awt.Color(0, 204, 51));

        javax.swing.GroupLayout lblgach3Layout = new javax.swing.GroupLayout(lblgach3);
        lblgach3.setLayout(lblgach3Layout);
        lblgach3Layout.setHorizontalGroup(
            lblgach3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );
        lblgach3Layout.setVerticalGroup(
            lblgach3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        lblHoaDon3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        lblHoaDon3.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDon3.setText("320");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Doanh Thu -");

        lblDoanhThu3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        lblDoanhThu3.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThu3.setText("4.500.000");

        lblPhanTram3.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lblPhanTram3.setForeground(new java.awt.Color(0, 204, 51));
        lblPhanTram3.setText("5,5%");

        javax.swing.GroupLayout panelHoaDon3Layout = new javax.swing.GroupLayout(panelHoaDon3);
        panelHoaDon3.setLayout(panelHoaDon3Layout);
        panelHoaDon3Layout.setHorizontalGroup(
            panelHoaDon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDon3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelHoaDon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelHoaDon3Layout.createSequentialGroup()
                        .addComponent(lblThang3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPhanTram3))
                    .addComponent(lblgach3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHoaDon3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelHoaDon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelHoaDon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHoaDon3Layout.createSequentialGroup()
                        .addComponent(lblHoaDon3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(17, 17, 17))
                    .addGroup(panelHoaDon3Layout.createSequentialGroup()
                        .addComponent(lblDoanhThu3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelHoaDon3Layout.setVerticalGroup(
            panelHoaDon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDon3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelHoaDon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblThang3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPhanTram3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblgach3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelHoaDon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoaDon3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelHoaDon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDoanhThu3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        panelHoaDon2.setBackground(new java.awt.Color(17, 28, 68));
        panelHoaDon2.setForeground(new java.awt.Color(0, 204, 51));
        panelHoaDon2.setPreferredSize(new java.awt.Dimension(310, 166));

        lblThang2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblThang2.setForeground(new java.awt.Color(255, 255, 255));
        lblThang2.setText("Tháng 10");

        lblgach2.setBackground(new java.awt.Color(0, 204, 51));

        javax.swing.GroupLayout lblgach2Layout = new javax.swing.GroupLayout(lblgach2);
        lblgach2.setLayout(lblgach2Layout);
        lblgach2Layout.setHorizontalGroup(
            lblgach2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );
        lblgach2Layout.setVerticalGroup(
            lblgach2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        lblPhanTram2.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lblPhanTram2.setForeground(new java.awt.Color(0, 204, 51));
        lblPhanTram2.setText("5,5%");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Hóa Đơn    -");

        lblHoaDon2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        lblHoaDon2.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDon2.setText("320");

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Doanh Thu -");

        lblDoanhThu2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        lblDoanhThu2.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThu2.setText("4.500.000");

        javax.swing.GroupLayout panelHoaDon2Layout = new javax.swing.GroupLayout(panelHoaDon2);
        panelHoaDon2.setLayout(panelHoaDon2Layout);
        panelHoaDon2Layout.setHorizontalGroup(
            panelHoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDon2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelHoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHoaDon2Layout.createSequentialGroup()
                        .addGroup(panelHoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelHoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHoaDon2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelHoaDon2Layout.createSequentialGroup()
                                .addComponent(lblDoanhThu2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(2, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelHoaDon2Layout.createSequentialGroup()
                        .addGroup(panelHoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelHoaDon2Layout.createSequentialGroup()
                                .addComponent(lblThang2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPhanTram2))
                            .addComponent(lblgach2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 13, Short.MAX_VALUE))))
        );
        panelHoaDon2Layout.setVerticalGroup(
            panelHoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDon2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelHoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblThang2)
                    .addComponent(lblPhanTram2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblgach2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelHoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelHoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDoanhThu2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        panelHoaDon1.setBackground(new java.awt.Color(17, 28, 68));
        panelHoaDon1.setPreferredSize(new java.awt.Dimension(310, 166));

        lblThang1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblThang1.setForeground(new java.awt.Color(255, 255, 255));
        lblThang1.setText("Tháng 11");

        HoaDon4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        HoaDon4.setForeground(new java.awt.Color(255, 255, 255));

        lblgach1.setBackground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout lblgach1Layout = new javax.swing.GroupLayout(lblgach1);
        lblgach1.setLayout(lblgach1Layout);
        lblgach1Layout.setHorizontalGroup(
            lblgach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );
        lblgach1Layout.setVerticalGroup(
            lblgach1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        lblPhanTram1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lblPhanTram1.setForeground(new java.awt.Color(204, 0, 0));
        lblPhanTram1.setText("5,5%");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Hóa Đơn    -");

        lblHoaDon1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        lblHoaDon1.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDon1.setText("320");

        lblDoanhThu1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        lblDoanhThu1.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThu1.setText("4.500.000");

        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Doanh Thu -");

        javax.swing.GroupLayout panelHoaDon1Layout = new javax.swing.GroupLayout(panelHoaDon1);
        panelHoaDon1.setLayout(panelHoaDon1Layout);
        panelHoaDon1Layout.setHorizontalGroup(
            panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDon1Layout.createSequentialGroup()
                .addGroup(panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelHoaDon1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHoaDon1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDoanhThu1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelHoaDon1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelHoaDon1Layout.createSequentialGroup()
                                .addComponent(lblThang1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(HoaDon4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPhanTram1))
                            .addComponent(lblgach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        panelHoaDon1Layout.setVerticalGroup(
            panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDon1Layout.createSequentialGroup()
                .addGroup(panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(HoaDon4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThang1)
                    .addComponent(lblPhanTram1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(lblgach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDoanhThu1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        panelHoaDon0.setBackground(new java.awt.Color(17, 28, 68));
        panelHoaDon0.setPreferredSize(new java.awt.Dimension(310, 166));

        lblThang0.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblThang0.setForeground(new java.awt.Color(255, 255, 255));
        lblThang0.setText("Hôm Nay");

        HoaDon5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        HoaDon5.setForeground(new java.awt.Color(255, 255, 255));

        lblgach0.setBackground(new java.awt.Color(0, 204, 51));
        lblgach0.setPreferredSize(new java.awt.Dimension(290, 1));

        javax.swing.GroupLayout lblgach0Layout = new javax.swing.GroupLayout(lblgach0);
        lblgach0.setLayout(lblgach0Layout);
        lblgach0Layout.setHorizontalGroup(
            lblgach0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        lblgach0Layout.setVerticalGroup(
            lblgach0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jLabel28.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Hóa Đơn    -");

        lblHoaDon0.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        lblHoaDon0.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDon0.setText("320");

        jLabel30.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Doanh Thu -");

        lblDoanhThu0.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        lblDoanhThu0.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThu0.setText("4.500.000");

        lblPhanTram0.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lblPhanTram0.setForeground(new java.awt.Color(0, 204, 51));

        javax.swing.GroupLayout panelHoaDon0Layout = new javax.swing.GroupLayout(panelHoaDon0);
        panelHoaDon0.setLayout(panelHoaDon0Layout);
        panelHoaDon0Layout.setHorizontalGroup(
            panelHoaDon0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDon0Layout.createSequentialGroup()
                .addGroup(panelHoaDon0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHoaDon0Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblThang0, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(HoaDon5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPhanTram0))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHoaDon0Layout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addGroup(panelHoaDon0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblgach0, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHoaDon0Layout.createSequentialGroup()
                                .addGroup(panelHoaDon0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel30))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelHoaDon0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHoaDon0, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDoanhThu0, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        panelHoaDon0Layout.setVerticalGroup(
            panelHoaDon0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDon0Layout.createSequentialGroup()
                .addGroup(panelHoaDon0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(HoaDon5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThang0)
                    .addComponent(lblPhanTram0))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblgach0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelHoaDon0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoaDon0, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelHoaDon0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDoanhThu0, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelHoaDon3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelHoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(panelHoaDon0, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHoaDon3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelHoaDon0, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(panelHoaDon1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(panelHoaDon2, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)))
        );

        jPanelBourder2.setBackground(new java.awt.Color(17, 28, 68));

        pieChart1.setBackground(new java.awt.Color(242, 243, 247));

        lbTopSell.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        lbTopSell.setForeground(new java.awt.Color(255, 255, 255));
        lbTopSell.setText("Top 5 sản phẩm bán chạy");

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanelBourder2Layout = new javax.swing.GroupLayout(jPanelBourder2);
        jPanelBourder2.setLayout(jPanelBourder2Layout);
        jPanelBourder2Layout.setHorizontalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder2Layout.createSequentialGroup()
                        .addComponent(pieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanelBourder2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTopSell, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(343, Short.MAX_VALUE))))
        );
        jPanelBourder2Layout.setVerticalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbTopSell)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelBourder3.setBackground(new java.awt.Color(17, 28, 68));

        lbThuChi.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        lbThuChi.setForeground(new java.awt.Color(255, 255, 255));
        lbThuChi.setText("Thu Chi 5 Ngày Gần Nhất");

        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));

        chart.setBackground(new java.awt.Color(17, 28, 68));

        javax.swing.GroupLayout jPanelBourder3Layout = new javax.swing.GroupLayout(jPanelBourder3);
        jPanelBourder3.setLayout(jPanelBourder3Layout);
        jPanelBourder3Layout.setHorizontalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder3Layout.createSequentialGroup()
                .addGroup(jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBourder3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanelBourder3Layout.setVerticalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(lbThuChi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 0, 28)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Dashboard");

        cbNgayHienTai.setBackground(new java.awt.Color(11, 20, 55));
        cbNgayHienTai.setForeground(new java.awt.Color(255, 255, 255));
        cbNgayHienTai.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        cbNgayHienTai.setLabeText("");
        cbNgayHienTai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNgayHienTaiActionPerformed(evt);
            }
        });

        btnInBaoCao.setBackground(new java.awt.Color(19, 29, 243));
        btnInBaoCao.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnInBaoCao.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao.setText("In Báo Cáo");
        btnInBaoCao.setFocusable(false);
        btnInBaoCao.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCaoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tổng quan bán hàng ngày hôm nay");

        btnInBaoCao1.setBackground(new java.awt.Color(19, 29, 243));
        btnInBaoCao1.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnInBaoCao1.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao1.setText("Refresh");
        btnInBaoCao1.setFocusable(false);
        btnInBaoCao1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCao1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInBaoCao1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnInBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbNgayHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanelBourder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanelBourder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel8))
                    .addComponent(cbNgayHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnInBaoCao1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBourder2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelBourder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCaoActionPerformed
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Calendar c3 = Calendar.getInstance();
        Calendar c4 = Calendar.getInstance();
        Calendar c5 = Calendar.getInstance();

        c1.roll(Calendar.DAY_OF_YEAR, -1);
        c2.roll(Calendar.DAY_OF_YEAR, -2);
        c3.roll(Calendar.DAY_OF_YEAR, -3);
        c4.roll(Calendar.DAY_OF_YEAR, -4);
        c5.roll(Calendar.DAY_OF_YEAR, -5);

        dateFormat.format(c1.getTime());

        DecimalFormat df = new DecimalFormat("#,###");
        if (JOptionPane.showConfirmDialog(this, "In", "Xác nhận", JOptionPane.OK_CANCEL_OPTION) == 0) {
            Document document = new Document();
            String hd = "Thong Ke Tong Quan";
            String hd1 = ">==========================================<";

            try {
                // khởi tạo một PdfWriter truyền vào document và FileOutputStream

                PdfWriter.getInstance(document, new FileOutputStream("ThongKe.pdf"));
                // mở file để thực hiện viết
                document.open();
                // thêm nội dung sử dụng add function


                PdfPTable table = new PdfPTable(3);
                PdfPTable table1 = new PdfPTable(5);
                //Khởi tạo 3 ô header
//                -- tao anh  
                document.add(new Paragraph("FBook"));
                try {
                    Image image4 = Image.getInstance("D:\\FPTPOLYTECH\\duanbieudo\\New folder (3)\\duan3\\image\\ssqqqsqsqsqs.png");
                    image4.setAbsolutePosition(350, 215);
                    document.add(image4);
                } catch (Exception e) {
                }
                try {
                    Image image1 = Image.getInstance("D:\\FPTPOLYTECH\\duanbieudo\\New folder (3)\\duan3\\image\\logo-fpt-fpt-polytechnic-tach-nen_043151201 (5).png");
                    image1.setAbsolutePosition(250, 10);
                    document.add(image1);
                } catch (Exception e) {
                }
                try {
                    Image image3 = Image.getInstance("D:\\FPTPOLYTECH\\duanbieudo\\New folder (3)\\duan3\\image\\11111.png");
                    image3.setAbsolutePosition(60, 730);
                    document.add(image3);
                } catch (Exception e) {
                }
                try {
                    Image image2 = Image.getInstance("D:\\FPTPOLYTECH\\duanbieudo\\New folder (3)\\duan3\\image\\icons8_best_seller_30px_1.png");
                    document.add(image2);
                } catch (Exception e) {
                }

                Paragraph paragraph3 = new Paragraph(hd);
                Paragraph paragraph1 = new Paragraph(hd1);
                Paragraph paragraph16 = new Paragraph("- Thoi Gian In : " + cbNgayHienTai.getItemAt(0));
                Paragraph paragraph24 = new Paragraph("1. Ngay Hom Nay - " + cbNgayHienTai.getItemAt(0));
                Paragraph paragraph25 = new Paragraph("- Hoa Don: " + lblHoaDon0.getText().trim() + "    ");
                Paragraph paragraph26 = new Paragraph("- Thu: " + lblDoanhThu0.getText().trim() + " VND");
                Paragraph paragraph2 = new Paragraph("2. Doanh Thu 3 Thang Gan Nhat.");
                Paragraph paragraph17 = new Paragraph("3. Top 5 San Pham Ban Chay Tai Cua Hang.");
                Paragraph paragraph23 = new Paragraph("4. Thu Chi 5 Ngay Gan Nhat .");
                Paragraph paragraph18 = new Paragraph("- Top 1 San Pham: ");

                Paragraph paragraph19 = new Paragraph("- Top 2 San Pham: ");

                Paragraph paragraph20 = new Paragraph("- Top 3 San Pham: ");

                Paragraph paragraph21 = new Paragraph("- Top 4 San Pham: ");

                Paragraph paragraph22 = new Paragraph("- Top 5 San Pham: ");

                Paragraph paragraph5 = new Paragraph("");
                PdfPCell header1 = new PdfPCell(new Paragraph("- Doanh Thu " + lblThang2.getText().trim() + "    "));
                PdfPCell header2 = new PdfPCell(new Paragraph("- Doanh Thu " + lblThang1.getText().trim() + "    "));
                PdfPCell header3 = new PdfPCell(new Paragraph("- Doanh Thu " + lblThang3.getText().trim() + "    "));
                PdfPCell header10 = new PdfPCell(new Paragraph("Hoa Don: " + lblHoaDon3.getText().trim() + "    "));
                PdfPCell header11 = new PdfPCell(new Paragraph("Hoa Don: " + lblHoaDon2.getText().trim() + "    "));
                PdfPCell header12 = new PdfPCell(new Paragraph("Hoa Don: " + lblHoaDon1.getText().trim() + "    "));
                PdfPCell header13 = new PdfPCell(new Paragraph("Thu: " + lblDoanhThu3.getText().trim() + " VND"));
                PdfPCell header14 = new PdfPCell(new Paragraph("Thu: " + lblDoanhThu2.getText().trim() + " VND"));
                PdfPCell header15 = new PdfPCell(new Paragraph("Thu: " + lblDoanhThu1.getText().trim() + " VND"));

                PdfPCell header20 = new PdfPCell(new Paragraph("Ngay " + dateFormat.format(c1.getTime()) + "    "));
                PdfPCell header21 = new PdfPCell(new Paragraph("Ngay " + dateFormat.format(c2.getTime()) + "    "));
                PdfPCell header22 = new PdfPCell(new Paragraph("Ngay " + dateFormat.format(c3.getTime()) + "    "));
                PdfPCell header23 = new PdfPCell(new Paragraph("Ngay " + dateFormat.format(c4.getTime()) + "    "));
                PdfPCell header24 = new PdfPCell(new Paragraph("Ngay " + dateFormat.format(c5.getTime()) + "    "));

                //Thêm 3 ô header vào table
//              -- ten Doanh thu 3 thang
                table.addCell(header3);
                table.addCell(header1);
                table.addCell(header2);
//              -- tong so luong hoa don 
                table.addCell(header10);
                table.addCell(header11);
                table.addCell(header12);
//               -- tong doanh thu
                table.addCell(header13);
                table.addCell(header14);
                table.addCell(header15);

//                -- 4
                table1.addCell(header20);
                table1.addCell(header21);
                table1.addCell(header22);
                table1.addCell(header23);
                table1.addCell(header24);
//                --thu
                try {
                    PdfPCell header25 = new PdfPCell(new Paragraph("Thu:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu1().get(0))) + " VND"));
                    table1.addCell(header25);
                } catch (Exception e) {
                    PdfPCell header25 = new PdfPCell(new Paragraph("Thu: 0" + " VND"));
                    table1.addCell(header25);
                }
                try {
                    PdfPCell header26 = new PdfPCell(new Paragraph("Thu:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu2().get(0))) + " VND"));
                    table1.addCell(header26);
                } catch (Exception e) {
                    PdfPCell header26 = new PdfPCell(new Paragraph("Thu: 0" + " VND"));
                    table1.addCell(header26);
                }
                try {
                    PdfPCell header27 = new PdfPCell(new Paragraph("Thu:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu3().get(0))) + " VND"));
                    table1.addCell(header27);
                } catch (Exception e) {
                    PdfPCell header27 = new PdfPCell(new Paragraph("Thu: 0" + " VND"));
                    table1.addCell(header27);
                }
                try {
                    PdfPCell header28 = new PdfPCell(new Paragraph("Thu:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu4().get(0))) + " VND"));
                    table1.addCell(header28);
                } catch (Exception e) {
                    PdfPCell header28 = new PdfPCell(new Paragraph("Thu: 0" + " VND"));
                    table1.addCell(header28);
                }
                try {
                    PdfPCell header29 = new PdfPCell(new Paragraph("Thu:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu5().get(0))) + " VND"));
                    table1.addCell(header29);
                } catch (Exception e) {
                    PdfPCell header29 = new PdfPCell(new Paragraph("Thu: 0" + " VND"));
                    table1.addCell(header29);
                }
                try {
                    //                --chi
                    PdfPCell header30 = new PdfPCell(new Paragraph("Chi:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi1().get(0))) + " VND"));
                    table1.addCell(header30);
                } catch (Exception e) {
                    PdfPCell header30 = new PdfPCell(new Paragraph("Chi: 0" + " VND"));
                    table1.addCell(header30);
                }
                try {
                    PdfPCell header31 = new PdfPCell(new Paragraph("Chi:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi2().get(0))) + " VND"));
                    table1.addCell(header31);
                } catch (Exception e) {
                    PdfPCell header31 = new PdfPCell(new Paragraph("Chi: 0" + " VND"));
                    table1.addCell(header31);
                }
                try {
                    PdfPCell header32 = new PdfPCell(new Paragraph("Chi:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi3().get(0))) + " VND"));
                    table1.addCell(header32);
                } catch (Exception e) {
                    PdfPCell header32 = new PdfPCell(new Paragraph("Chi: 0" + " VND"));
                    table1.addCell(header32);
                }
                try {
                    PdfPCell header33 = new PdfPCell(new Paragraph("Chi:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi4().get(0))) + " VND"));
                    table1.addCell(header33);
                } catch (Exception e) {
                    PdfPCell header33 = new PdfPCell(new Paragraph("Chi: 0" + " VND"));
                    table1.addCell(header33);
                }
                try {
                    PdfPCell header34 = new PdfPCell(new Paragraph("Chi:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi5().get(0))) + " VND"));
                    table1.addCell(header34);
                } catch (Exception e) {
                    PdfPCell header34 = new PdfPCell(new Paragraph("Chi: 0" + " VND"));
                    table1.addCell(header34);
                }

                paragraph1.setIndentationLeft(80);

                paragraph1.setAlignment(Element.ALIGN_CENTER);
                paragraph1.setSpacingAfter(15);
                paragraph3.setIndentationLeft(80);

                paragraph3.setAlignment(Element.ALIGN_CENTER);

                paragraph2.setSpacingBefore(15);
                paragraph2.setAlignment(Element.ALIGN_LEFT);
                paragraph5.setSpacingBefore(15);
                paragraph5.setAlignment(Element.ALIGN_LEFT);
                paragraph16.setSpacingBefore(15);
                paragraph16.setAlignment(Element.ALIGN_LEFT);

                paragraph25.setIndentationLeft(52);
                paragraph25.setAlignment(Element.ALIGN_LEFT);
                paragraph26.setIndentationLeft(52);
                paragraph26.setAlignment(Element.ALIGN_LEFT);

     
                document.add(paragraph16);
                document.add(paragraph3);
                document.add(paragraph1);
                document.add(paragraph24);
                document.add(paragraph25);
                document.add(paragraph26);
                document.add(paragraph2);
                document.add(paragraph5);

                paragraph18.setIndentationLeft(52);
                paragraph18.setAlignment(Element.ALIGN_LEFT);
                paragraph19.setIndentationLeft(52);
                paragraph19.setAlignment(Element.ALIGN_LEFT);
                paragraph20.setIndentationLeft(52);
                paragraph20.setAlignment(Element.ALIGN_LEFT);
                paragraph21.setIndentationLeft(52);
                paragraph21.setAlignment(Element.ALIGN_LEFT);
                paragraph22.setIndentationLeft(52);
                paragraph22.setAlignment(Element.ALIGN_LEFT);
                paragraph23.setSpacingBefore(10);

                document.add(table);
                document.add(paragraph17);
                document.add(paragraph18);
                try {
                    Paragraph paragraph33 = new Paragraph("+ Ten San Pham: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoTron().get(99999)));
                    Paragraph paragraph34 = new Paragraph("+ So Luong: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeBieuDoTron().get(0)));
                    paragraph33.setIndentationLeft(80);
                    paragraph33.setIndentationRight(230);
                    paragraph33.setAlignment(Element.ALIGN_LEFT);
                    paragraph34.setIndentationLeft(80);
                    paragraph34.setIndentationRight(230);
                    paragraph34.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph33);
                    document.add(paragraph34);
                } catch (Exception e) {
                    Paragraph paragraph33 = new Paragraph("+ Ten San Pham: " + "khong co");
                    Paragraph paragraph34 = new Paragraph("+ So Luong: 0");
                    paragraph33.setIndentationLeft(80);
                    paragraph33.setIndentationRight(230);
                    paragraph33.setAlignment(Element.ALIGN_LEFT);
                    paragraph34.setIndentationLeft(80);
                    paragraph34.setIndentationRight(230);
                    paragraph34.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph33);
                    document.add(paragraph34);
                }

                document.add(paragraph19);
                try {
                    Paragraph paragraph35 = new Paragraph("+ Ten San Pham: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoTron().get(1)));
                    Paragraph paragraph36 = new Paragraph("+ So Luong: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeBieuDoTron().get(1)));
                    paragraph35.setIndentationLeft(80);
                    paragraph35.setIndentationRight(230);
                    paragraph35.setAlignment(Element.ALIGN_LEFT);
                    paragraph36.setIndentationLeft(80);
                    paragraph36.setIndentationRight(230);
                    paragraph36.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph35);
                    document.add(paragraph36);
                } catch (Exception e) {
                    Paragraph paragraph35 = new Paragraph("+ Ten San Pham: " + "khong co");
                    Paragraph paragraph36 = new Paragraph("+ So Luong: 0");
                    paragraph35.setIndentationLeft(80);
                    paragraph35.setIndentationRight(230);
                    paragraph35.setAlignment(Element.ALIGN_LEFT);
                    paragraph36.setIndentationLeft(80);
                    paragraph36.setIndentationRight(230);
                    paragraph36.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph35);
                    document.add(paragraph36);
                }
                document.add(paragraph20);

                try {

                    Paragraph paragraph37 = new Paragraph("+ Ten San Pham: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoTron().get(2)));
                    Paragraph paragraph38 = new Paragraph("+ So Luong: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeBieuDoTron().get(2)));
                    paragraph37.setIndentationLeft(80);
                    paragraph37.setIndentationRight(230);
                    paragraph37.setAlignment(Element.ALIGN_LEFT);
                    paragraph38.setIndentationLeft(80);
                    paragraph38.setIndentationRight(230);
                    paragraph38.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph37);
                    document.add(paragraph38);
                } catch (Exception e) {

                    Paragraph paragraph37 = new Paragraph("+ Ten San Pham: " + "khong co");
                    Paragraph paragraph38 = new Paragraph("+ So Luong: 0");
                    paragraph37.setIndentationLeft(80);
                    paragraph37.setIndentationRight(230);
                    paragraph37.setAlignment(Element.ALIGN_LEFT);
                    paragraph38.setIndentationLeft(80);
                    paragraph38.setIndentationRight(230);
                    paragraph38.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph37);
                    document.add(paragraph38);
                }
                document.add(paragraph21);

                try {
                    Paragraph paragraph39 = new Paragraph("+ Ten San Pham: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoTron().get(3)));
                    Paragraph paragraph40 = new Paragraph("+ So Luong: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeBieuDoTron().get(3)));
                    paragraph39.setIndentationLeft(80);
                    paragraph39.setIndentationRight(230);
                    paragraph39.setAlignment(Element.ALIGN_LEFT);
                    paragraph40.setIndentationLeft(80);
                    paragraph40.setIndentationRight(230);
                    paragraph40.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph39);
                    document.add(paragraph40);
                } catch (Exception e) {
                    Paragraph paragraph39 = new Paragraph("+ Ten San Pham: " + "khong co");
                    Paragraph paragraph40 = new Paragraph("+ So Luong: 0");
                    paragraph39.setIndentationLeft(80);
                    paragraph39.setIndentationRight(230);
                    paragraph39.setAlignment(Element.ALIGN_LEFT);
                    paragraph40.setIndentationLeft(80);
                    paragraph40.setIndentationRight(230);
                    paragraph40.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph39);
                    document.add(paragraph40);
                }
                document.add(paragraph22);
                try {

                    Paragraph paragraph41 = new Paragraph("+ Ten San Pham: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoTron().get(4)));
                    Paragraph paragraph42 = new Paragraph("+ So Luong: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeBieuDoTron().get(4)));
                    paragraph41.setIndentationLeft(80);
                    paragraph41.setIndentationRight(230);
                    paragraph41.setAlignment(Element.ALIGN_LEFT);
                    paragraph42.setIndentationLeft(80);
                    paragraph42.setIndentationRight(230);
                    paragraph42.setAlignment(Element.ALIGN_LEFT);

                    document.add(paragraph41);
                    document.add(paragraph42);
                } catch (Exception e) {

                    Paragraph paragraph41 = new Paragraph("+ Ten San Pham: " + "khong co");
                    Paragraph paragraph42 = new Paragraph("+ So Luong: 0");
                    paragraph41.setIndentationLeft(80);
                    paragraph41.setIndentationRight(230);
                    paragraph41.setAlignment(Element.ALIGN_LEFT);
                    paragraph42.setIndentationLeft(80);
                    paragraph42.setIndentationRight(230);
                    paragraph42.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph41);
                    document.add(paragraph42);
                }
                document.add(paragraph23);
                document.add(paragraph5);
                document.add(table1);

                // đóng file
                document.close();

            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BieuDo_Form.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BieuDo_Form.class.getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(this, "In Thanh Cong");
        }

    }//GEN-LAST:event_btnInBaoCaoActionPerformed

    private void cbNgayHienTaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNgayHienTaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNgayHienTaiActionPerformed

    private void btnInBaoCao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao1ActionPerformed
        loadData();
        this.repaint();//
        this.validate();
    }//GEN-LAST:event_btnInBaoCao1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HoaDon4;
    private javax.swing.JLabel HoaDon5;
    private View.ButtonDesign.Button btnInBaoCao;
    private View.ButtonDesign.Button btnInBaoCao1;
    private View.DesignComponent.Combobox cbNgayHienTai;
    private View.DesignComponent.BieuDo chart;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel4;
    private View.DesignComponent.JPanelBourder jPanelBourder2;
    private View.DesignComponent.JPanelBourder jPanelBourder3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbHoaDon;
    private javax.swing.JLabel lbThuChi;
    private javax.swing.JLabel lbTopSell;
    private javax.swing.JLabel lblDoanhThu0;
    private javax.swing.JLabel lblDoanhThu1;
    private javax.swing.JLabel lblDoanhThu2;
    private javax.swing.JLabel lblDoanhThu3;
    private javax.swing.JLabel lblHoaDon0;
    private javax.swing.JLabel lblHoaDon1;
    private javax.swing.JLabel lblHoaDon2;
    private javax.swing.JLabel lblHoaDon3;
    private javax.swing.JLabel lblPhanTram0;
    private javax.swing.JLabel lblPhanTram1;
    private javax.swing.JLabel lblPhanTram2;
    private javax.swing.JLabel lblPhanTram3;
    private javax.swing.JLabel lblThang0;
    private javax.swing.JLabel lblThang1;
    private javax.swing.JLabel lblThang2;
    private javax.swing.JLabel lblThang3;
    private javax.swing.JPanel lblgach0;
    private javax.swing.JPanel lblgach1;
    private javax.swing.JPanel lblgach2;
    private javax.swing.JPanel lblgach3;
    private View.DesignComponent.JPanelBourder panelHoaDon0;
    private View.DesignComponent.JPanelBourder panelHoaDon1;
    private View.DesignComponent.JPanelBourder panelHoaDon2;
    private View.DesignComponent.JPanelBourder panelHoaDon3;
    private View.DesignComponent.PieChart pieChart1;
    // End of variables declaration//GEN-END:variables
}
