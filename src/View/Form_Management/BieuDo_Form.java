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
import java.awt.Color;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
        donService = new HoaDonServiceImpl();
        list3 = new ArrayList<>();
        list2 = new ArrayList<>();
        list1 = new ArrayList<>();
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

//        this.doanhsobanhang.setIcon(iconDS);
//        this.topbanchay.setIcon(iconTopSell);
//        this.HoaDon.setIcon(iconHDDays);
//        this.books.setIcon(iconBooks);
//        this.tongtien.setIcon(iconDoanhThu);
//        this.tienchi.setIcon(iconDoanhThu);
        this.btnInBaoCao.setIcon(iconIn);

        chart.addLegend("Tiền Thu", Color.GREEN);
        chart.addLegend("Tiền Chi", Color.RED);

        chart.addData(new DC_ModelBieuDo("T1", new double[]{999, 200, 200}));
        chart.addData(new DC_ModelBieuDo("T2", new double[]{233, 34, 300}));
        chart.addData(new DC_ModelBieuDo("T3", new double[]{500, 200, 400}));
        chart.addData(new DC_ModelBieuDo("T4", new double[]{500, 200, 600}));
        chart.addData(new DC_ModelBieuDo("T5", new double[]{500, 200, 700}));
        // 

        pieChart1.addData(new ModelPieChart("Nam1", 33.3, new Color(255, 188, 0)));
        pieChart1.addData(new ModelPieChart("Nam2", 44, new Color(250, 92, 124)));
        pieChart1.addData(new ModelPieChart("Nam5", 22, new Color(10, 207, 151)));
        pieChart1.addData(new ModelPieChart("Nam4", 102, new Color(114, 124, 245)));
        pieChart1.addData(new ModelPieChart("Nam1", 33.3, new Color(255, 188, 44)));
        tongSoLuong0();
        tongSoLuong1();
        tongSoLuong2();
        tongSoLuong3();
        thang3();

        tongTien3Thang();
        phanTramDoanhThuTheoThang3();
        phanTramDoanhThuTheoThang2();

    }

    private void thang3() {
        LocalDateTime localDate = LocalDateTime.now();
        int month3 = localDate.getMonthValue() - 3;
        lblThang3.setText(String.valueOf("Tháng " + month3));
        int month2 = localDate.getMonthValue() - 2;
        lblThang2.setText(String.valueOf("Tháng " + month2));
        int month1 = localDate.getMonthValue() - 1;
        lblThang1.setText(String.valueOf("Tháng " + month1));

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
        DecimalFormat df = new DecimalFormat("####");
        double a = Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsTongTien2().get(0)));
        double b = Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsTongTien3().get(0)));
        double c = ((a - b) / b) * 100;
        lblPhanTram2.setText(df.format(c) + "%");

    }

    private void phanTramDoanhThuTheoThang2() {
        DecimalFormat df = new DecimalFormat("####");
        double a = Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsTongTien1().get(0)));
        double b = Double.parseDouble(String.valueOf(donService.sellectAllHoaDonChiTietsTongTien2().get(0)));
        double c = ((a - b) / b) * 100;
        lblPhanTram1.setText(df.format(c) + "%");

    }

//    public static void main(String[] args) {
//        BieuDo_Form bdf = new BieuDo_Form();
//        bdf.phanTramDoanhThuTheoThang3();
//
//    }
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
        panelHoaDon = new View.DesignComponent.JPanelBourder();
        lblThang3 = new javax.swing.JLabel();
        lbHoaDon = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblHoaDon3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblDoanhThu3 = new javax.swing.JLabel();
        lblPhanTram3 = new javax.swing.JLabel();
        panelHoaDon1 = new View.DesignComponent.JPanelBourder();
        lblThang2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblPhanTram2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblHoaDon2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblDoanhThu2 = new javax.swing.JLabel();
        panelHoaDon4 = new View.DesignComponent.JPanelBourder();
        lblThang1 = new javax.swing.JLabel();
        HoaDon4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblPhanTram1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblHoaDon1 = new javax.swing.JLabel();
        lblDoanhThu1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        panelHoaDon5 = new View.DesignComponent.JPanelBourder();
        lblThang0 = new javax.swing.JLabel();
        HoaDon5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
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
        combobox1 = new View.DesignComponent.Combobox();
        btnInBaoCao = new View.ButtonDesign.Button();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(11, 20, 55));
        setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        setPreferredSize(new java.awt.Dimension(1373, 770));

        jPanel4.setBackground(new java.awt.Color(11, 20, 55));

        panelHoaDon.setBackground(new java.awt.Color(17, 28, 68));

        lblThang3.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblThang3.setForeground(new java.awt.Color(255, 255, 255));
        lblThang3.setText("Tháng 9");

        lbHoaDon.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lbHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lbHoaDon.setText("Hóa Đơn    -");

        jPanel1.setBackground(new java.awt.Color(0, 204, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        javax.swing.GroupLayout panelHoaDonLayout = new javax.swing.GroupLayout(panelHoaDon);
        panelHoaDon.setLayout(panelHoaDonLayout);
        panelHoaDonLayout.setHorizontalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDonLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelHoaDonLayout.createSequentialGroup()
                        .addComponent(lblThang3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPhanTram3))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHoaDonLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHoaDonLayout.createSequentialGroup()
                        .addComponent(lblHoaDon3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(17, 17, 17))
                    .addGroup(panelHoaDonLayout.createSequentialGroup()
                        .addComponent(lblDoanhThu3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelHoaDonLayout.setVerticalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDonLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblThang3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPhanTram3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoaDon3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDoanhThu3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        panelHoaDon1.setBackground(new java.awt.Color(17, 28, 68));
        panelHoaDon1.setForeground(new java.awt.Color(0, 204, 51));
        panelHoaDon1.setPreferredSize(new java.awt.Dimension(310, 166));

        lblThang2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblThang2.setForeground(new java.awt.Color(255, 255, 255));
        lblThang2.setText("Tháng 10");

        jPanel3.setBackground(new java.awt.Color(0, 204, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        javax.swing.GroupLayout panelHoaDon1Layout = new javax.swing.GroupLayout(panelHoaDon1);
        panelHoaDon1.setLayout(panelHoaDon1Layout);
        panelHoaDon1Layout.setHorizontalGroup(
            panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDon1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHoaDon1Layout.createSequentialGroup()
                        .addGroup(panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHoaDon2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelHoaDon1Layout.createSequentialGroup()
                                .addComponent(lblDoanhThu2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(2, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelHoaDon1Layout.createSequentialGroup()
                        .addGroup(panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelHoaDon1Layout.createSequentialGroup()
                                .addComponent(lblThang2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPhanTram2))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 13, Short.MAX_VALUE))))
        );
        panelHoaDon1Layout.setVerticalGroup(
            panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDon1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblThang2)
                    .addComponent(lblPhanTram2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDoanhThu2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        panelHoaDon4.setBackground(new java.awt.Color(17, 28, 68));
        panelHoaDon4.setPreferredSize(new java.awt.Dimension(310, 166));

        lblThang1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblThang1.setForeground(new java.awt.Color(255, 255, 255));
        lblThang1.setText("Tháng 11");

        HoaDon4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        HoaDon4.setForeground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        javax.swing.GroupLayout panelHoaDon4Layout = new javax.swing.GroupLayout(panelHoaDon4);
        panelHoaDon4.setLayout(panelHoaDon4Layout);
        panelHoaDon4Layout.setHorizontalGroup(
            panelHoaDon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDon4Layout.createSequentialGroup()
                .addGroup(panelHoaDon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelHoaDon4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelHoaDon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelHoaDon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHoaDon1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDoanhThu1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelHoaDon4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(panelHoaDon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelHoaDon4Layout.createSequentialGroup()
                                .addComponent(lblThang1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(HoaDon4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPhanTram1))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        panelHoaDon4Layout.setVerticalGroup(
            panelHoaDon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDon4Layout.createSequentialGroup()
                .addGroup(panelHoaDon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(HoaDon4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThang1)
                    .addComponent(lblPhanTram1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelHoaDon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelHoaDon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDoanhThu1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        panelHoaDon5.setBackground(new java.awt.Color(17, 28, 68));
        panelHoaDon5.setPreferredSize(new java.awt.Dimension(310, 166));

        lblThang0.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblThang0.setForeground(new java.awt.Color(255, 255, 255));
        lblThang0.setText("Hôm Nay");

        HoaDon5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        HoaDon5.setForeground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(0, 204, 51));
        jPanel6.setPreferredSize(new java.awt.Dimension(290, 1));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
        lblPhanTram0.setText("5,5%");

        javax.swing.GroupLayout panelHoaDon5Layout = new javax.swing.GroupLayout(panelHoaDon5);
        panelHoaDon5.setLayout(panelHoaDon5Layout);
        panelHoaDon5Layout.setHorizontalGroup(
            panelHoaDon5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDon5Layout.createSequentialGroup()
                .addGroup(panelHoaDon5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHoaDon5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblThang0, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(HoaDon5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPhanTram0))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHoaDon5Layout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addGroup(panelHoaDon5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHoaDon5Layout.createSequentialGroup()
                                .addGroup(panelHoaDon5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel30))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelHoaDon5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHoaDon0, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDoanhThu0, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        panelHoaDon5Layout.setVerticalGroup(
            panelHoaDon5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDon5Layout.createSequentialGroup()
                .addGroup(panelHoaDon5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(HoaDon5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThang0)
                    .addComponent(lblPhanTram0))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelHoaDon5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoaDon0, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelHoaDon5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addComponent(panelHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelHoaDon4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(panelHoaDon5, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelHoaDon5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(panelHoaDon4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(panelHoaDon1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)))
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

        combobox1.setBackground(new java.awt.Color(11, 20, 55));
        combobox1.setForeground(new java.awt.Color(255, 255, 255));
        combobox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18/11/2022" }));
        combobox1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        combobox1.setLabeText("");
        combobox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox1ActionPerformed(evt);
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
                        .addComponent(btnInBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnInBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        ManagementBookForm mng = new ManagementBookForm();
        mng.runNotification();
    }//GEN-LAST:event_btnInBaoCaoActionPerformed

    private void combobox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combobox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HoaDon4;
    private javax.swing.JLabel HoaDon5;
    private View.ButtonDesign.Button btnInBaoCao;
    private View.DesignComponent.BieuDo chart;
    private View.DesignComponent.Combobox combobox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
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
    private View.DesignComponent.JPanelBourder panelHoaDon;
    private View.DesignComponent.JPanelBourder panelHoaDon1;
    private View.DesignComponent.JPanelBourder panelHoaDon4;
    private View.DesignComponent.JPanelBourder panelHoaDon5;
    private View.DesignComponent.PieChart pieChart1;
    // End of variables declaration//GEN-END:variables
}
