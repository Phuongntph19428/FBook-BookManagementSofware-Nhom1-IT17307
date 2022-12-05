/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import View.DesignComponent.ModelProfile;
import View.ThongBao;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import model.HinhThucThanhToan;
import model.HoaDon;
import static model.HoaDon.CHUATHANHTOAN;
import static model.HoaDon.DAHUY;
import static model.HoaDon.DANGVANCHUYEN;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.Sach;
import service.HinhThucThanhToanService;
import service.HoaDonService;
import service.SachService;
import service.impl.HinhThucThanhToanServiceImpl;
import service.impl.HoaDonServiceImpl;
import service.impl.SachServiceImpl;

/**
 *
 * @author quanc
 */
public class HoaDon_Form extends javax.swing.JPanel {

    private final SachService _sachService;

    private final HoaDonService _hoaDonService;
    private List<HoaDon> _lstHoaDon;
    private int _status = -1;
    private HoaDon _hoaDon;
    private List<HoaDonChiTiet> _lstHoaDonCT;

    private BigDecimal _tongTien;

    private final HinhThucThanhToanService _hinhHinhThucThanhToanService;

    /**
     * Creates new form HoaDon_Form
     */
    public HoaDon_Form() {
        initComponents();
        setIcon();
        this.tblHoaDon.setRowHeight(50);
        this.tblHoaDon.setBackground(Color.white);

        this.tblHoaDonChiTiet.setRowHeight(50);
        this.tblHoaDonChiTiet.setBackground(Color.white);

        DialogUpdate.setLocationRelativeTo(null);

        _hoaDonService = new HoaDonServiceImpl();
        _lstHoaDon = _hoaDonService.sellectAll();

        _hinhHinhThucThanhToanService = new HinhThucThanhToanServiceImpl();

        _sachService = new SachServiceImpl();

        loadTableHoaDon(_lstHoaDon);
        setEventTable();
    }

    private void setIcon() {
        btnQRGiaoHangThanhCong.setIcon(new ImageIcon(new ImageIcon("image//genQR.png").getImage().getScaledInstance(46, 44, Image.SCALE_DEFAULT)));
    }
    
    public JTable getJTable() {
        return this.tblHoaDon;
    }

    public void showTab() {
        this.tabPaneHoaDon.setSelectedIndex(1);
    }

    private void loadTableHoaDon(List<HoaDon> lstHoaDon) {
        DefaultTableModel dtm = (DefaultTableModel) tblHoaDon.getModel();
        dtm.setRowCount(0);
        for (HoaDon hoaDon : lstHoaDon) {
            dtm.addRow(hoaDon.toDataRow());
        }
    }

    private void setEventTable() {
        tblHoaDonCTDialogUpdate.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (javax.swing.SwingUtilities.isRightMouseButton(e)) {
                    int r = tblHoaDonCTDialogUpdate.rowAtPoint(e.getPoint());
                    tblHoaDonCTDialogUpdate.setRowSelectionInterval(r, r);
                    int rowindex = tblHoaDonCTDialogUpdate.getSelectedRow();
                    if (rowindex < 0) {
                        return;
                    }

                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem item = new JMenuItem("Xóa sản phẩm");
                    popup.add(item);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                    item.addActionListener((java.awt.event.ActionEvent evt) -> {
                        _lstHoaDonCT.remove(rowindex);
                        loadTableHoaDonCTUpdate(_lstHoaDonCT);
                    });

                }
            }

        }
        );

        tblHoaDonCTDialogUpdate.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                changedItem(e);
            }
        });
    }

    private void changedItem(TableModelEvent e) {
        if (e.getColumn() != 4) {
            return;
        }

        int row = tblHoaDonCTDialogUpdate.getSelectedRow();
        if (row == -1) {
            return;
        }
        HoaDonChiTiet hoaDonCT = _lstHoaDonCT.get(row);
        int amountChanged = Integer.parseInt(tblHoaDonCTDialogUpdate.getValueAt(e.getFirstRow(), 4) + "");
        if (amountChanged < 0) {
            ThongBao.showNoti_Error(this, "Số lượng phải lớn hơn 0");
            tblHoaDonCTDialogUpdate.setValueAt(hoaDonCT.getSoLuong(), e.getFirstRow(), 4);
            return;
        } else if (amountChanged == 0) {
            ThongBao.showNoti_Confirm(this, "Số lượng = 0. Bạn muốn xóa sản phẩm này?");
            if (ThongBao.getSelected() == ThongBao.YES) {
                _lstHoaDonCT.remove(row);
                loadTableHoaDonCTUpdate(_lstHoaDonCT);
            }
            return;
        } else if (amountChanged > hoaDonCT.getSoLuong()) {
            ThongBao.showNoti_Error(this, "Số lượng thay đổi phải nhỏ hơn số lượng đang có");
            tblHoaDonCTDialogUpdate.setValueAt(hoaDonCT.getSoLuong(), e.getFirstRow(), 4);
            return;
        }

        _lstHoaDonCT.get(row).setSoLuong(amountChanged);
        loadTableHoaDonCTUpdate(_lstHoaDonCT);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        DialogUpdate = new javax.swing.JDialog();
        jPanelBourder15 = new View.DesignComponent.JPanelBourder();
        jPanelBourder16 = new View.DesignComponent.JPanelBourder();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblHoaDonCTDialogUpdate = new View.DesignComponent.Table();
        lblTongTienDialogUpdate = new javax.swing.JLabel();
        btnUpdateDialogUpdate = new View.ButtonDesign.Button();
        tabPaneHoaDon = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanelBourder7 = new View.DesignComponent.JPanelBourder();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        rdoDaThanhToan = new javax.swing.JRadioButton();
        rdoChuaThanhToan = new javax.swing.JRadioButton();
        rdoDangGiao = new javax.swing.JRadioButton();
        rdoTatCa = new javax.swing.JRadioButton();
        rdoDaHuy = new javax.swing.JRadioButton();
        jPanelBourder9 = new View.DesignComponent.JPanelBourder();
        panel = new View.DesignComponent.JPanelBourder();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new View.DesignComponent.Table();
        jLabel1 = new javax.swing.JLabel();
        btnRefresh = new View.ButtonDesign.Button();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanelBourder13 = new View.DesignComponent.JPanelBourder();
        jPanelBourder14 = new View.DesignComponent.JPanelBourder();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHinhThucThanhToan = new View.DesignComponent.Table();
        lblTong = new javax.swing.JLabel();
        jPanelBourder10 = new View.DesignComponent.JPanelBourder();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanelBourder11 = new View.DesignComponent.JPanelBourder();
        jPanelBourder12 = new View.DesignComponent.JPanelBourder();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new View.DesignComponent.Table();
        lblTongTien = new javax.swing.JLabel();
        lblDiemSuDung = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblKhachHang = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        txtMoTa = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnQRGiaoHangThanhCong = new View.ButtonDesign.Button();
        jPanelBourder19 = new View.DesignComponent.JPanelBourder();
        jPanelBourder20 = new View.DesignComponent.JPanelBourder();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblHinhThucThanhToan2 = new View.DesignComponent.Table();
        lblTong2 = new javax.swing.JLabel();
        btnHuyHoaDon = new View.ButtonDesign.Button();
        btnUpdateHoaDon = new View.ButtonDesign.Button();
        btnGiaoHangThanhCong = new View.ButtonDesign.Button();

        DialogUpdate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogUpdate.setMinimumSize(new java.awt.Dimension(1020, 610));
        DialogUpdate.setModal(true);
        DialogUpdate.setResizable(false);

        jPanelBourder15.setBackground(new java.awt.Color(47, 55, 90));

        jPanelBourder16.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setBorder(null);
        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane6.setPreferredSize(new java.awt.Dimension(452, 395));

        tblHoaDonCTDialogUpdate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Hình", "Đơn giá", "Số lượng", "Tổng tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonCTDialogUpdate.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblHoaDonCTDialogUpdate.setRowHeight(50);
        tblHoaDonCTDialogUpdate.setRowMargin(5);
        tblHoaDonCTDialogUpdate.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane6.setViewportView(tblHoaDonCTDialogUpdate);

        lblTongTienDialogUpdate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongTienDialogUpdate.setForeground(new java.awt.Color(51, 51, 51));
        lblTongTienDialogUpdate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTienDialogUpdate.setText("Tổng tiền:");

        javax.swing.GroupLayout jPanelBourder16Layout = new javax.swing.GroupLayout(jPanelBourder16);
        jPanelBourder16.setLayout(jPanelBourder16Layout);
        jPanelBourder16Layout.setHorizontalGroup(
            jPanelBourder16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder16Layout.createSequentialGroup()
                .addContainerGap(563, Short.MAX_VALUE)
                .addComponent(lblTongTienDialogUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanelBourder16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder16Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelBourder16Layout.setVerticalGroup(
            jPanelBourder16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder16Layout.createSequentialGroup()
                .addContainerGap(426, Short.MAX_VALUE)
                .addComponent(lblTongTienDialogUpdate)
                .addGap(33, 33, 33))
            .addGroup(jPanelBourder16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder16Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(83, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanelBourder15Layout = new javax.swing.GroupLayout(jPanelBourder15);
        jPanelBourder15.setLayout(jPanelBourder15Layout);
        jPanelBourder15Layout.setHorizontalGroup(
            jPanelBourder15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBourder15Layout.setVerticalGroup(
            jPanelBourder15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnUpdateDialogUpdate.setBackground(new java.awt.Color(35, 35, 132));
        btnUpdateDialogUpdate.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnUpdateDialogUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateDialogUpdate.setText("Cập nhật");
        btnUpdateDialogUpdate.setFocusable(false);
        btnUpdateDialogUpdate.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnUpdateDialogUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateDialogUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DialogUpdateLayout = new javax.swing.GroupLayout(DialogUpdate.getContentPane());
        DialogUpdate.getContentPane().setLayout(DialogUpdateLayout);
        DialogUpdateLayout.setHorizontalGroup(
            DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogUpdateLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DialogUpdateLayout.createSequentialGroup()
                        .addComponent(jPanelBourder15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DialogUpdateLayout.createSequentialGroup()
                        .addComponent(btnUpdateDialogUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(383, 383, 383))))
        );
        DialogUpdateLayout.setVerticalGroup(
            DialogUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdateDialogUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DialogUpdate.getAccessibleContext().setAccessibleDescription("");

        setBackground(new java.awt.Color(11, 20, 55));

        tabPaneHoaDon.setBackground(new java.awt.Color(11, 20, 55));
        tabPaneHoaDon.setForeground(new java.awt.Color(204, 204, 204));
        tabPaneHoaDon.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabPaneHoaDonStateChanged(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(11, 20, 55));

        jPanelBourder7.setBackground(new java.awt.Color(17, 28, 68));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(104, 143, 222));
        jLabel4.setText("Danh sách hóa đơn");

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(1, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        rdoDaThanhToan.setBackground(new java.awt.Color(17, 28, 68));
        buttonGroup1.add(rdoDaThanhToan);
        rdoDaThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoDaThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        rdoDaThanhToan.setText("Đã thanh toán");
        rdoDaThanhToan.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        rdoDaThanhToan.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdoDaThanhToanStateChanged(evt);
            }
        });

        rdoChuaThanhToan.setBackground(new java.awt.Color(17, 28, 68));
        buttonGroup1.add(rdoChuaThanhToan);
        rdoChuaThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoChuaThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        rdoChuaThanhToan.setText("Chưa thanh toán");
        rdoChuaThanhToan.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        rdoChuaThanhToan.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdoChuaThanhToanStateChanged(evt);
            }
        });

        rdoDangGiao.setBackground(new java.awt.Color(17, 28, 68));
        buttonGroup1.add(rdoDangGiao);
        rdoDangGiao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoDangGiao.setForeground(new java.awt.Color(255, 255, 255));
        rdoDangGiao.setText("Đang giao");
        rdoDangGiao.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        rdoDangGiao.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdoDangGiaoStateChanged(evt);
            }
        });

        rdoTatCa.setBackground(new java.awt.Color(17, 28, 68));
        buttonGroup1.add(rdoTatCa);
        rdoTatCa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoTatCa.setForeground(new java.awt.Color(255, 255, 255));
        rdoTatCa.setSelected(true);
        rdoTatCa.setText("Tất cả");
        rdoTatCa.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        rdoTatCa.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdoTatCaStateChanged(evt);
            }
        });

        rdoDaHuy.setBackground(new java.awt.Color(17, 28, 68));
        buttonGroup1.add(rdoDaHuy);
        rdoDaHuy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoDaHuy.setForeground(new java.awt.Color(255, 255, 255));
        rdoDaHuy.setText("Đã hủy");
        rdoDaHuy.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        rdoDaHuy.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdoDaHuyStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelBourder7Layout = new javax.swing.GroupLayout(jPanelBourder7);
        jPanelBourder7.setLayout(jPanelBourder7Layout);
        jPanelBourder7Layout.setHorizontalGroup(
            jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(rdoTatCa)
                .addGap(45, 45, 45)
                .addComponent(rdoDaThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(rdoChuaThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(rdoDangGiao)
                .addGap(45, 45, 45)
                .addComponent(rdoDaHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(288, Short.MAX_VALUE))
        );
        jPanelBourder7Layout.setVerticalGroup(
            jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoChuaThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdoTatCa)
                                .addComponent(rdoDaThanhToan))
                            .addComponent(rdoDangGiao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdoDaHuy))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelBourder9.setBackground(new java.awt.Color(47, 55, 90));

        panel.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(452, 395));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Đắc Nhân Tâm", "NXB1", "S1", "VT1", "15", "200", "20000"},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Nhân viên", "Khách hàng", "Ngày tạo", "Ngày ship", "Ngày nhận", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblHoaDon.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblHoaDon.setShowGrid(true);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setMinWidth(100);
            tblHoaDon.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblHoaDon.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1302, Short.MAX_VALUE)
            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 612, Short.MAX_VALUE)
            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanelBourder9Layout = new javax.swing.GroupLayout(jPanelBourder9);
        jPanelBourder9.setLayout(jPanelBourder9Layout);
        jPanelBourder9Layout.setHorizontalGroup(
            jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBourder9Layout.setVerticalGroup(
            jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Management Book - Quản Lý Hóa Đơn");

        btnRefresh.setBackground(new java.awt.Color(35, 35, 132));
        btnRefresh.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Refresh");
        btnRefresh.setFocusable(false);
        btnRefresh.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelBourder9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 51, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(jLabel1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jPanelBourder7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 59, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(jPanelBourder9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 2, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanelBourder7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 685, Short.MAX_VALUE)))
        );

        tabPaneHoaDon.addTab("Hóa Đơn", jPanel3);

        jPanel5.setBackground(new java.awt.Color(11, 20, 55));

        jPanel6.setBackground(new java.awt.Color(11, 20, 55));

        jPanelBourder13.setBackground(new java.awt.Color(47, 55, 90));

        jPanelBourder14.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane5.setPreferredSize(new java.awt.Dimension(452, 395));

        tblHinhThucThanhToan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hình thức", "Số tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHinhThucThanhToan.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblHinhThucThanhToan.setRowHeight(40);
        tblHinhThucThanhToan.setRowMargin(10);
        tblHinhThucThanhToan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblHinhThucThanhToan.setShowGrid(true);
        jScrollPane5.setViewportView(tblHinhThucThanhToan);

        lblTong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTong.setText("Tổng:");

        javax.swing.GroupLayout jPanelBourder14Layout = new javax.swing.GroupLayout(jPanelBourder14);
        jPanelBourder14.setLayout(jPanelBourder14Layout);
        jPanelBourder14Layout.setHorizontalGroup(
            jPanelBourder14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder14Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblTong))
        );
        jPanelBourder14Layout.setVerticalGroup(
            jPanelBourder14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder14Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelBourder14Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(lblTong))
        );

        javax.swing.GroupLayout jPanelBourder13Layout = new javax.swing.GroupLayout(jPanelBourder13);
        jPanelBourder13.setLayout(jPanelBourder13Layout);
        jPanelBourder13Layout.setHorizontalGroup(
            jPanelBourder13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelBourder14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(246, 246, 246))
        );
        jPanelBourder13Layout.setVerticalGroup(
            jPanelBourder13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelBourder14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelBourder10.setBackground(new java.awt.Color(17, 28, 68));

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(104, 143, 222));
        jLabel5.setText("Hóa đơn chi tiết");

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel7.setPreferredSize(new java.awt.Dimension(1, 30));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelBourder10Layout = new javax.swing.GroupLayout(jPanelBourder10);
        jPanelBourder10.setLayout(jPanelBourder10Layout);
        jPanelBourder10Layout.setHorizontalGroup(
            jPanelBourder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder10Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel5)
                .addGap(20, 20, 20)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1078, Short.MAX_VALUE))
        );
        jPanelBourder10Layout.setVerticalGroup(
            jPanelBourder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelBourder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelBourder11.setBackground(new java.awt.Color(47, 55, 90));

        jPanelBourder12.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane4.setPreferredSize(new java.awt.Dimension(452, 395));

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Hình", "Đơn giá", "Số lượng", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonChiTiet.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblHoaDonChiTiet.setRowHeight(50);
        tblHoaDonChiTiet.setRowMargin(5);
        tblHoaDonChiTiet.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane4.setViewportView(tblHoaDonChiTiet);

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(51, 51, 51));
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTien.setText("Tổng tiền:");

        lblDiemSuDung.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDiemSuDung.setForeground(new java.awt.Color(51, 51, 51));
        lblDiemSuDung.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDiemSuDung.setText("Điểm sử dụng:");

        javax.swing.GroupLayout jPanelBourder12Layout = new javax.swing.GroupLayout(jPanelBourder12);
        jPanelBourder12.setLayout(jPanelBourder12Layout);
        jPanelBourder12Layout.setHorizontalGroup(
            jPanelBourder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder12Layout.createSequentialGroup()
                .addContainerGap(529, Short.MAX_VALUE)
                .addGroup(jPanelBourder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiemSuDung, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
            .addGroup(jPanelBourder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelBourder12Layout.setVerticalGroup(
            jPanelBourder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder12Layout.createSequentialGroup()
                .addContainerGap(398, Short.MAX_VALUE)
                .addComponent(lblDiemSuDung)
                .addGap(18, 18, 18)
                .addComponent(lblTongTien)
                .addGap(18, 18, 18))
            .addGroup(jPanelBourder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(83, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanelBourder11Layout = new javax.swing.GroupLayout(jPanelBourder11);
        jPanelBourder11.setLayout(jPanelBourder11Layout);
        jPanelBourder11Layout.setHorizontalGroup(
            jPanelBourder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelBourder11Layout.setVerticalGroup(
            jPanelBourder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Management Book - Quản Lý Hóa Đơn");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Mã HD:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Ngày Tạo :");

        lblMaHD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaHD.setForeground(new java.awt.Color(255, 255, 255));
        lblMaHD.setText("HD1");

        lblNgayTao.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgayTao.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayTao.setText("23/03/2022");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Khách hàng:");

        lblKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lblKhachHang.setText("Nguyễn Văn A");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Mô Tả :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nhân viên:");

        lblNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setText("Nguyễn Văn A");

        txtMoTa.setEditable(false);
        txtMoTa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Trạng thái:");

        lblTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        lblTrangThai.setText("Đã thanh toán");

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(104, 143, 222));
        jLabel9.setText("Chi tiết thanh toán");

        btnQRGiaoHangThanhCong.setBackground(new java.awt.Color(204, 204, 204));
        btnQRGiaoHangThanhCong.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnQRGiaoHangThanhCong.setForeground(new java.awt.Color(255, 255, 255));
        btnQRGiaoHangThanhCong.setToolTipText("Quét QR giao hàng thành công");
        btnQRGiaoHangThanhCong.setFocusable(false);
        btnQRGiaoHangThanhCong.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnQRGiaoHangThanhCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQRGiaoHangThanhCongActionPerformed(evt);
            }
        });

        jPanelBourder19.setBackground(new java.awt.Color(47, 55, 90));

        jPanelBourder20.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane8.setBorder(null);
        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane8.setPreferredSize(new java.awt.Dimension(452, 395));

        tblHinhThucThanhToan2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hình thức", "Số tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHinhThucThanhToan2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblHinhThucThanhToan2.setRowHeight(40);
        tblHinhThucThanhToan2.setRowMargin(10);
        tblHinhThucThanhToan2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblHinhThucThanhToan2.setShowGrid(true);
        jScrollPane8.setViewportView(tblHinhThucThanhToan2);

        jPanelBourder20.add(jScrollPane8);

        lblTong2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTong2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTong2.setText("Tổng:");
        jPanelBourder20.add(lblTong2);

        javax.swing.GroupLayout jPanelBourder19Layout = new javax.swing.GroupLayout(jPanelBourder19);
        jPanelBourder19.setLayout(jPanelBourder19Layout);
        jPanelBourder19Layout.setHorizontalGroup(
            jPanelBourder19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelBourder20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(446, 446, 446))
        );
        jPanelBourder19Layout.setVerticalGroup(
            jPanelBourder19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelBourder20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQRGiaoHangThanhCong, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanelBourder11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelBourder13, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel18))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addComponent(jLabel13)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTrangThai)
                                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanelBourder10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnQRGiaoHangThanhCong, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(422, 422, 422)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelBourder13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(lblMaHD))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblNgayTao)
                                    .addComponent(jLabel8)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(lblNhanVien)
                                    .addComponent(jLabel18)
                                    .addComponent(lblTrangThai))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(lblKhachHang)
                                    .addComponent(jLabel13)
                                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(20, 20, 20)
                        .addComponent(jPanelBourder10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelBourder11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        btnHuyHoaDon.setBackground(new java.awt.Color(204, 204, 204));
        btnHuyHoaDon.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnHuyHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyHoaDon.setText("Hủy hóa đơn");
        btnHuyHoaDon.setEnabled(false);
        btnHuyHoaDon.setFocusable(false);
        btnHuyHoaDon.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonActionPerformed(evt);
            }
        });

        btnUpdateHoaDon.setBackground(new java.awt.Color(204, 204, 204));
        btnUpdateHoaDon.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnUpdateHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateHoaDon.setText("Chỉnh sửa");
        btnUpdateHoaDon.setEnabled(false);
        btnUpdateHoaDon.setFocusable(false);
        btnUpdateHoaDon.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnUpdateHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateHoaDonActionPerformed(evt);
            }
        });

        btnGiaoHangThanhCong.setBackground(new java.awt.Color(204, 204, 204));
        btnGiaoHangThanhCong.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnGiaoHangThanhCong.setForeground(new java.awt.Color(255, 255, 255));
        btnGiaoHangThanhCong.setText("Giao hàng thành công");
        btnGiaoHangThanhCong.setEnabled(false);
        btnGiaoHangThanhCong.setFocusable(false);
        btnGiaoHangThanhCong.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnGiaoHangThanhCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaoHangThanhCongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(btnUpdateHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(btnGiaoHangThanhCong, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGiaoHangThanhCong, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabPaneHoaDon.addTab("Hóa Đơn Chi Tiết", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabPaneHoaDon))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabPaneHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        rdoTatCa.setSelected(true);
        _lstHoaDon = _hoaDonService.sellectAll();
        loadTableHoaDon(_lstHoaDon);
        _status = -1;
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void rdoDaThanhToanStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoDaThanhToanStateChanged
        if (rdoDaThanhToan.isSelected()) {
            if (_status == HoaDon.DATHANHTOAN) {
                return;
            }
            _lstHoaDon = _hoaDonService.selectAllHoaDonDaThanhToan();
            loadTableHoaDon(_lstHoaDon);
            _status = HoaDon.DATHANHTOAN;
        }
    }//GEN-LAST:event_rdoDaThanhToanStateChanged

    private void rdoTatCaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoTatCaStateChanged
        if (rdoTatCa.isSelected()) {
            if (_status == -1) {
                return;
            }
            _lstHoaDon = _hoaDonService.sellectAll();
            loadTableHoaDon(_lstHoaDon);
            _status = -1;
        }
    }//GEN-LAST:event_rdoTatCaStateChanged

    private void rdoChuaThanhToanStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoChuaThanhToanStateChanged
        if (rdoChuaThanhToan.isSelected()) {
            if (_status == HoaDon.CHUATHANHTOAN) {
                return;
            }
            _lstHoaDon = _hoaDonService.sellectAllHoaDonCho();
            loadTableHoaDon(_lstHoaDon);
            _status = HoaDon.CHUATHANHTOAN;
        }
    }//GEN-LAST:event_rdoChuaThanhToanStateChanged

    private void rdoDangGiaoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoDangGiaoStateChanged
        if (rdoDangGiao.isSelected()) {
            if (_status == HoaDon.DANGVANCHUYEN) {
                return;
            }
            _lstHoaDon = _hoaDonService.sellectAllHoaDonDangVanChuyen();
            loadTableHoaDon(_lstHoaDon);
            _status = HoaDon.DANGVANCHUYEN;
        }
    }//GEN-LAST:event_rdoDangGiaoStateChanged

    private void rdoDaHuyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoDaHuyStateChanged
        if (rdoDaHuy.isSelected()) {
            if (_status == HoaDon.DAHUY) {
                return;
            }
            _lstHoaDon = _hoaDonService.sellectAllHoaDonDaHuy();
            loadTableHoaDon(_lstHoaDon);
            _status = HoaDon.DAHUY;
        }
    }//GEN-LAST:event_rdoDaHuyStateChanged

    private final DecimalFormat df = new DecimalFormat("#,###");

    private void setFormHoaDonCT() {
        tabPaneHoaDon.setSelectedIndex(1);
        lblMaHD.setText(_hoaDon.getMa());
        lblNgayTao.setText(_hoaDon.getNgayTao() + "");
        lblNhanVien.setText(_hoaDon.getNhanVien().getHo() + " " + _hoaDon.getNhanVien().getTenDem() + " " + _hoaDon.getNhanVien().getTen());
        lblKhachHang.setText(_hoaDon.getKhachHang() == null ? "-" : _hoaDon.getKhachHang().getHo() + " " + _hoaDon.getKhachHang().getTenDem() + " " + _hoaDon.getKhachHang().getTen());
        lblDiemSuDung.setText("Điểm sử dụng: " + _hoaDon.getSoDiemSuDung() + "");
        lblTrangThai.setText(_hoaDon.getTrangThai() == CHUATHANHTOAN
                ? "<html><p style= 'color:red'>Chưa thanh toán</p></html>" : _hoaDon.getTrangThai() == DAHUY ? "<html><p style= 'color:red'>Đã hủy</p></html>"
                : _hoaDon.getTrangThai() == DANGVANCHUYEN ? "<html><p style= 'color:blue'>Đang vận chuyển</p></html>" : "<html><p style= 'color:#00FF33'>Đã thanh toán</p></html>");
        txtMoTa.setText(_hoaDon.getMoTa() == null ? "" : _hoaDon.getMoTa());

        DefaultTableModel dtmHoaDonCT = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        dtmHoaDonCT.setRowCount(0);
        _tongTien = BigDecimal.ZERO;
        for (HoaDonChiTiet hoaDonChiTiet : _hoaDon.getLstHoaDonCT()) {
            dtmHoaDonCT.addRow(new Object[]{hoaDonChiTiet.getSach().getMa(), hoaDonChiTiet.getSach().getTen(),
                hoaDonChiTiet.getSach().getHinh() == null ? "" : new ModelProfile(new ImageIcon(new ImageIcon(hoaDonChiTiet.getSach().getHinh()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT))),
                "<html>" + df.format(hoaDonChiTiet.getDonGia()) + "<sup style='Color: Red'>vnđ</sup></html>", hoaDonChiTiet.getSoLuong(),
                "<html>" + df.format(hoaDonChiTiet.getDonGia().multiply(BigDecimal.valueOf(hoaDonChiTiet.getSoLuong()))) + "<sup style='Color: Red'>vnđ</sup></html>"});
            _tongTien = _tongTien.add(BigDecimal.valueOf(Double.parseDouble(hoaDonChiTiet.getSoLuong() + "")).multiply(hoaDonChiTiet.getDonGia()));
        }
        lblTongTien.setText("<html> Tổng tiền: " + df.format(_tongTien) + "<sup style='Color: Red'>vnđ</sup></html>");

        DefaultTableModel dtmHinhThucTT = (DefaultTableModel) tblHinhThucThanhToan.getModel();
        dtmHinhThucTT.setRowCount(0);
        BigDecimal tongTien = BigDecimal.ZERO;
        for (HinhThucThanhToan hinhThucThanhToan : _hoaDon.getLstHinhThucThanhToan()) {
            dtmHinhThucTT.addRow(new Object[]{hinhThucThanhToan.getHinhThucThanhToan() == HinhThucThanhToan.THANHTOANBANGTIENMAT ? "Tiền mặt" : "Chuyển khoản",
                "<html>" + df.format(hinhThucThanhToan.getTienThanhToan() == null ? 0 : hinhThucThanhToan.getTienThanhToan()) + "<sup style='Color: Red'>vnđ</sup></html>"});
            tongTien = tongTien.add(BigDecimal.valueOf(Double.parseDouble(hinhThucThanhToan.getTienThanhToan() == null ? "0" : (hinhThucThanhToan.getTienThanhToan() + ""))));
        }
        lblTong.setText("<html> Tổng: " + df.format(tongTien) + "<sup style='Color: Red'>vnđ</sup></html>");
        setEnableButton();
    }

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        if (evt.getClickCount() == 2) {
            int row = tblHoaDon.getSelectedRow();
            if (row == -1) {
                return;
            }
            _hoaDon = _lstHoaDon.get(row);
            setFormHoaDonCT();
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void setEnableButton() {
        if (_hoaDon == null) {
            return;
        }

        if (_hoaDon.getTrangThai() == HoaDon.CHUATHANHTOAN || _hoaDon.getTrangThai() == HoaDon.DANGVANCHUYEN) {
            btnHuyHoaDon.setEnabled(true);
            btnHuyHoaDon.setBackground(new Color(35, 35, 132));
            if (!_hoaDon.getLstHoaDonCT().isEmpty()) {
                btnUpdateHoaDon.setEnabled(true);
                btnUpdateHoaDon.setBackground(new Color(35, 35, 132));
            }
        } else {
            btnHuyHoaDon.setEnabled(false);
            btnHuyHoaDon.setBackground(new Color(204, 204, 204));
            btnUpdateHoaDon.setEnabled(false);
            btnUpdateHoaDon.setBackground(new Color(204, 204, 204));
        }

        if (_hoaDon.getTrangThai() == HoaDon.DANGVANCHUYEN) {
            btnGiaoHangThanhCong.setEnabled(true);
            btnGiaoHangThanhCong.setBackground(new Color(35, 35, 132));
        } else {
            btnGiaoHangThanhCong.setEnabled(false);
            btnGiaoHangThanhCong.setBackground(new Color(204, 204, 204));

        }
    }

    private void btnHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHoaDonActionPerformed
        String lyDo = JOptionPane.showInputDialog("Lý do hủy: ");
        if (lyDo != null) {
            if (JOptionPane.showConfirmDialog(this, "Xác nhận hủy?") == JOptionPane.YES_OPTION) {
                _hoaDon.setMoTa(lyDo);
                _hoaDon.setTrangThai(HoaDon.DAHUY);
                _hoaDonService.updateHoaDon(_hoaDon);

                List<Sach> lstSach = new ArrayList<>();
                for (HoaDonChiTiet hoaDonChiTiet : _hoaDon.getLstHoaDonCT()) {
                    lstSach.add(hoaDonChiTiet.getSach());
                }

                _sachService.updateSoLuongSach(lstSach);
                ThongBao.showNoti_Succes(this, "Thành công");
                setFormHoaDonCT();
            }
        }
    }//GEN-LAST:event_btnHuyHoaDonActionPerformed

    private void loadTableHoaDonCTUpdate(List<HoaDonChiTiet> lstHoaDonCT) {
        DefaultTableModel dtm = (DefaultTableModel) tblHoaDonCTDialogUpdate.getModel();
        dtm.setRowCount(0);
        BigDecimal tongTien = BigDecimal.ZERO;
        for (HoaDonChiTiet hoaDonChiTiet : lstHoaDonCT) {
            dtm.addRow(new Object[]{hoaDonChiTiet.getSach().getMa(), hoaDonChiTiet.getSach().getTen(),
                hoaDonChiTiet.getSach().getHinh() == null ? "" : new ModelProfile(new ImageIcon(new ImageIcon(hoaDonChiTiet.getSach().getHinh()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT))),
                "<html>" + df.format(hoaDonChiTiet.getDonGia()) + "<sup style='Color: Red'>vnđ</sup></html>", hoaDonChiTiet.getSoLuong(),
                "<html>" + df.format(hoaDonChiTiet.getDonGia().multiply(BigDecimal.valueOf(hoaDonChiTiet.getSoLuong()))) + "<sup style='Color: Red'>vnđ</sup></html>"});
            tongTien = tongTien.add(BigDecimal.valueOf(Double.parseDouble(hoaDonChiTiet.getSoLuong() + "")).multiply(hoaDonChiTiet.getDonGia()));
        }
        lblTongTienDialogUpdate.setText("Tổng tiền: " + df.format(tongTien) + " VNĐ");
    }

    private void btnUpdateHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateHoaDonActionPerformed
        _lstHoaDonCT = new ArrayList<>();
        for (HoaDonChiTiet hdct : _hoaDon.getLstHoaDonCT()) {
            _lstHoaDonCT.add(hdct);
        }
        loadTableHoaDonCTUpdate(_lstHoaDonCT);
        DialogUpdate.setVisible(true);
    }//GEN-LAST:event_btnUpdateHoaDonActionPerformed

    private void btnGiaoHangThanhCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaoHangThanhCongActionPerformed
        ThongBao.showNoti_Confirm(this, "Xác nhận giao hàng thành công?");
        if(ThongBao.getSelected() == ThongBao.YES) {
            _hoaDon.setTrangThai(HoaDon.DATHANHTOAN);
            _hoaDon.setNgayNhan(new Date());
            _hoaDon.setNgayThanhToan(new Date());
            _hoaDonService.updateHoaDon(_hoaDon);
            List<HinhThucThanhToan> lstHinhThucTT = new ArrayList<>();
            lstHinhThucTT.add(new HinhThucThanhToan(null, _hoaDon, HoaDon.DATHANHTOAN, _tongTien));
            _hinhHinhThucThanhToanService.addHinhThucThanhToan(lstHinhThucTT);
            ThongBao.showNoti_Succes(this, "Thành công");
            setFormHoaDonCT();
            
        }
    }//GEN-LAST:event_btnGiaoHangThanhCongActionPerformed

    private void btnUpdateDialogUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateDialogUpdateActionPerformed
        ThongBao.showNoti_Confirm(this, "Xác nhận cập nhật?");
        if (ThongBao.getSelected() == ThongBao.YES) {
            _hoaDon.setLstHoaDonCT(_lstHoaDonCT);
            _hoaDonService.updateHoaDonChiTiet(_hoaDon.getLstHoaDonCT(), _hoaDon);
            DialogUpdate.dispose();
            ThongBao.showNoti_Succes(this, "Cập nhật thành công");
        }

    }//GEN-LAST:event_btnUpdateDialogUpdateActionPerformed

    private void btnQRGiaoHangThanhCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQRGiaoHangThanhCongActionPerformed
        CamJFrame cam = new CamJFrame();
        cam.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                cam.webcam.close();
                cam.dispose();
                closedCam(cam);
            }
        });
        cam.setTitle("QR/ BarCode Scanner");
        cam.setVisible(true);
        cam.btnCapture.setVisible(false);

        Thread th = new Thread(() -> {
            try {
                cam.webcam.open();
            } catch (Exception e) {
                ThongBao.showNoti_Error(this, "Không thể mở camera");
                closedCam(cam);
            }
            while (true) {
                Image image = cam.getImage();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Result result = null;
                LuminanceSource source = new BufferedImageLuminanceSource((BufferedImage) image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException ex) {
                    //not result
                }
                if (result != null) {
                    closedCam(cam);
                    HoaDon hoaDon = _hoaDonService.getByMaHD(result + "");
                    if (hoaDon == null) {
                        ThongBao.showNoti_Error(this, "Không tìm thấy hóa đơn");
                        return;
                    }

                    if (hoaDon.getTrangThai() != HoaDon.DANGVANCHUYEN) {
                        ThongBao.showNoti_Error(this, "Hóa đơn hiện không đang trạng thái vận chuyển");
                        return;
                    }

                    ThongBao.showNoti_Confirm(this, "Xác nhận thanh toán?");
                    if (ThongBao.getSelected() == ThongBao.YES) {
                        hoaDon.setTrangThai(HoaDon.DATHANHTOAN);
                        hoaDon.setNgayNhan(new Date());
                        hoaDon.setNgayThanhToan(new Date());
                        _hoaDon = hoaDon;
                        _hoaDonService.updateHoaDon(hoaDon);
                        setFormHoaDonCT();
                        KhachHang khachHang = _hoaDon.getKhachHang();
                        khachHang.setDiemTichLuy(khachHang.getDiemTichLuy() + _tongTien.divide(BigDecimal.valueOf(100000)).intValue());
                        ThongBao.showNoti_Succes(this, "Thanh toán thành công");
                    }
                }

            }
        });
        th.start();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean x = true;
                while (x) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    if (!cam.isFocusOwner()) {
                        closedCam(cam);
                        btnQRGiaoHangThanhCong.setEnabled(true);
                        x = false;
                    }

                }
            }
        });
        thread.start();
    }//GEN-LAST:event_btnQRGiaoHangThanhCongActionPerformed

    private void tabPaneHoaDonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabPaneHoaDonStateChanged
        if(_hoaDonService == null) {
            return;
        }
        if (tabPaneHoaDon.getSelectedIndex() == 0) {
            _lstHoaDon = switch (_status) {
                case -1 -> _hoaDonService.sellectAll();
                case HoaDon.CHUATHANHTOAN -> _hoaDonService.sellectAllHoaDonCho();
                case HoaDon.DAHUY -> _hoaDonService.sellectAllHoaDonDaHuy();
                case HoaDon.DANGVANCHUYEN -> _hoaDonService.sellectAllHoaDonDangVanChuyen();
                default -> _hoaDonService.selectAllHoaDonDaThanhToan();
            };
            loadTableHoaDon(_lstHoaDon);
        }
        System.out.println("change: " + tabPaneHoaDon.getSelectedIndex());
    }//GEN-LAST:event_tabPaneHoaDonStateChanged

    private void closedCam(CamJFrame cam) {
        cam.webcam.close();
        cam.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogUpdate;
    private View.ButtonDesign.Button btnGiaoHangThanhCong;
    private View.ButtonDesign.Button btnHuyHoaDon;
    private View.ButtonDesign.Button btnQRGiaoHangThanhCong;
    private View.ButtonDesign.Button btnRefresh;
    private View.ButtonDesign.Button btnUpdateDialogUpdate;
    private View.ButtonDesign.Button btnUpdateHoaDon;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private View.DesignComponent.JPanelBourder jPanelBourder10;
    private View.DesignComponent.JPanelBourder jPanelBourder11;
    private View.DesignComponent.JPanelBourder jPanelBourder12;
    private View.DesignComponent.JPanelBourder jPanelBourder13;
    private View.DesignComponent.JPanelBourder jPanelBourder14;
    private View.DesignComponent.JPanelBourder jPanelBourder15;
    private View.DesignComponent.JPanelBourder jPanelBourder16;
    private View.DesignComponent.JPanelBourder jPanelBourder19;
    private View.DesignComponent.JPanelBourder jPanelBourder20;
    private View.DesignComponent.JPanelBourder jPanelBourder7;
    private View.DesignComponent.JPanelBourder jPanelBourder9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblDiemSuDung;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblTong;
    private javax.swing.JLabel lblTong2;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTongTienDialogUpdate;
    private javax.swing.JLabel lblTrangThai;
    private View.DesignComponent.JPanelBourder panel;
    private javax.swing.JRadioButton rdoChuaThanhToan;
    private javax.swing.JRadioButton rdoDaHuy;
    private javax.swing.JRadioButton rdoDaThanhToan;
    private javax.swing.JRadioButton rdoDangGiao;
    private javax.swing.JRadioButton rdoTatCa;
    private javax.swing.JTabbedPane tabPaneHoaDon;
    private View.DesignComponent.Table tblHinhThucThanhToan;
    private View.DesignComponent.Table tblHinhThucThanhToan2;
    private View.DesignComponent.Table tblHoaDon;
    private View.DesignComponent.Table tblHoaDonCTDialogUpdate;
    private View.DesignComponent.Table tblHoaDonChiTiet;
    private javax.swing.JTextField txtMoTa;
    // End of variables declaration//GEN-END:variables
}
