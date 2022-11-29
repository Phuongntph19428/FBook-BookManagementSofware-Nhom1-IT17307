/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import View.DesignComponent.Item_Pos;
import View.DesignComponent.ModelProfile;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import custommodel.CustomSach;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.status.TrangThaiHoaDon;
import service.CustomSachService;
import service.HoaDonService;
import service.KhachHangService;
import service.impl.CustomSachServiceImpl;
import service.impl.HoaDonServiceImpl;
import service.impl.KhachHangServiceImpl;
import util.Auth;

public class Pos_MayBanHang extends javax.swing.JPanel {

    private final HoaDonService _hoaDonService;
    private List<HoaDon> _lstHoaDon;

    private final CustomSachService _customSachService;
    private List<CustomSach> _lstCustomSach;

    private HashMap<String, HoaDonChiTiet> _lstHoaDonChiTiet;

    private int _totalPage;
    private int _currentPage = 1;
    private final int _pageSize = 6;
    private boolean searcher = false;

    private final KhachHangService _khachHangService;
    private List<KhachHang> _lstKhachHang;
    private KhachHang _khachHang = null;
    private int _diemSuDung = 0;
    private BigDecimal _tongTien = BigDecimal.ZERO;
    private BigDecimal _tienThua = BigDecimal.ZERO;

    private HoaDon _hoaDon = null;

//    List<SachFake> listS = new ArrayList<>();
    public Pos_MayBanHang() {
        initComponents();
        setComponent();
        _hoaDonService = new HoaDonServiceImpl();
        _customSachService = new CustomSachServiceImpl();
        _khachHangService = new KhachHangServiceImpl();

        loadCustomSach();
        loadTableHoaDon();
    }

    public void loadCustomSach() {
        loadSach(1, _pageSize);
        setPageLabel(false);
    }

    private void setPageLabel(boolean searching) {
        if (searching) {
            int result = _lstCustomSach.size();
            if (result % _pageSize == 0) {
                _totalPage = result / _pageSize;
            } else {
                _totalPage = result / _pageSize + 1;
            }
        } else {
            int result = _customSachService.countAllSach();
            if (result % _pageSize == 0) {
                _totalPage = result / _pageSize;
            } else {
                _totalPage = result / _pageSize + 1;
            }
        }
        setLabelPage();

    }

    private void setLabelPage() {
        lblPage.setText(_currentPage + "/" + _totalPage);
    }

    private void setComponent() {
        setKhachHangDialog();
        tblHoaDonChiTiet.setRowHeight(60);
        tblHoaDon.setRowHeight(40);
        Color color = new Color(17, 28, 68);
        Icon prev = new ImageIcon("image/icons8_previous_37px.png");
        Icon next = new ImageIcon("image/icons8_next_37px.png");
        Icon fist = new ImageIcon("image/icons8_skip_to_start_37px.png");
        Icon last = new ImageIcon("image/icons8_end_37px.png");
        Icon kh = new ImageIcon("image/icons8_search_25px.png");
        btnPrev.setIcon(prev);
        btnPrev.setBackground(color);
        btnNext.setIcon(next);
        btnNext.setBackground(color);
        btnFist.setIcon(fist);
        btnFist.setBackground(color);
        btnLast.setIcon(last);
        btnLast.setBackground(color);
        btnSearchKhachHang.setIcon(kh);
    }

    private void loadSach(int position, int pageSize) {
        _lstCustomSach = _customSachService.getList(position, pageSize);
        int size = _lstCustomSach.size();
        initJpanelSach(0, size);
    }

    private void initJpanelSach(int start, int size) {
        JpanelShowBook.removeAll();

        for (int i = start; i < size && i < _lstCustomSach.size(); i++) {
            // tạo Sản Phẩm từ list
            CustomSach customSach = _lstCustomSach.get(i);
            Item_Pos item = new Item_Pos();
            item.setIcon(customSach.getHinh());
            item.setName(customSach.getTen());
            item.setGia(customSach.getGiaBan(), customSach.getChietKhau());
            item.setGiaSale(customSach.getGiaSale());
            item.setSoluongTon(customSach.getSoLuong());
            item.setSTT(i);
            item.setToolTipText(customSach.getTen());

            item.jPanelBourder1.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        CustomSach newCustomSach = getCustomSach(customSach);
                        if (newCustomSach == null) {
                            return;
                        }
                        addHoaDonCT(newCustomSach);
                    }

                }
            });
            item.lbGia.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        CustomSach newCustomSach = getCustomSach(customSach);
                        if (newCustomSach == null) {
                            return;
                        }
                        addHoaDonCT(newCustomSach);
                    }
                }
            });
            item.jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        CustomSach newCustomSach = getCustomSach(customSach);
                        if (newCustomSach == null) {
                            return;
                        }
                        addHoaDonCT(newCustomSach);
                    }
                }
            });
            item.jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        CustomSach newCustomSach = getCustomSach(customSach);
                        if (newCustomSach == null) {
                            return;
                        }
                        addHoaDonCT(newCustomSach);
                    }
                }
            });

            item.lbName.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        CustomSach newCustomSach = getCustomSach(customSach);
                        if (newCustomSach == null) {
                            return;
                        }
                        addHoaDonCT(newCustomSach);
                    }
                }
            });
            item.lbIcon.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        CustomSach newCustomSach = getCustomSach(customSach);
                        if (newCustomSach == null) {
                            return;
                        }
                        addHoaDonCT(newCustomSach);
                    }
                }
            });
            item.lblSoLuongTon.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        CustomSach newCustomSach = getCustomSach(customSach);
                        if (newCustomSach == null) {
                            return;
                        }
                        addHoaDonCT(newCustomSach);
                    }
                }
            });
            item.lbGiaSale.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        CustomSach newCustomSach = getCustomSach(customSach);
                        if (newCustomSach == null) {
                            return;
                        }
                        addHoaDonCT(newCustomSach);
                    }
                }
            });
            item.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        CustomSach newCustomSach = getCustomSach(customSach);
                        if (newCustomSach == null) {
                            return;
                        }
                        addHoaDonCT(newCustomSach);
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    item.hovering();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    item.exited();
                }

            });

            JpanelShowBook.add(item);
        }
        repaint();
        revalidate();
    }

    public void loadSachSearch(List<CustomSach> lst, int position, int pageSize) {
        _lstCustomSach = lst;
        int size = pageSize * position;
        initJpanelSach((position - 1) * pageSize, size);
        JpanelShowBook.removeAll();
    }

    private void loadTableHoaDon() {
        _lstHoaDon = _hoaDonService.sellectAllHoaDonCho();
        DefaultTableModel dtm = (DefaultTableModel) tblHoaDon.getModel();
        dtm.setRowCount(0);
        for (HoaDon hoaDon : _lstHoaDon) {
            dtm.addRow(new Object[]{hoaDon.getMa()});
        }
    }

    private CustomSach getCustomSach(CustomSach customSach) {

        if (_hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn");
            return null;
        }
        String input = JOptionPane.showInputDialog(this, "Mời bạn nhập số lượng: ");
        if (input == null) {
            return null;
        }
        if (!input.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Không đúng định dạng");
            return null;
        }

        if (customSach.getSoLuong() < Integer.parseInt(input)) {
            JOptionPane.showMessageDialog(this, "Số lượng không đủ.");
            return null;
        }
        customSach.setSoLuong(Integer.parseInt(input));
        return customSach;
    }

    private void addHoaDonCT(CustomSach customSach) {

        if (_hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn");
            return;
        }

        HoaDonChiTiet hoaDonCT = new HoaDonChiTiet();
        hoaDonCT.setSach(customSach.getSach());
        hoaDonCT.setDonGia(customSach.getGiaSale());
        hoaDonCT.setSoLuong(customSach.getSoLuong());
        hoaDonCT.setHoaDon(_hoaDon);
        if (_lstHoaDonChiTiet.get(hoaDonCT.getSach().getId()) == null) {
            _hoaDonService.insertHoaDonChiTiet(hoaDonCT);
        } else {
            int soLuong = _lstHoaDonChiTiet.get(hoaDonCT.getSach().getId()).getSoLuong() + customSach.getSoLuong();
            hoaDonCT.setSoLuong(soLuong);
            hoaDonCT.setId(_lstHoaDonChiTiet.get(hoaDonCT.getSach().getId()).getId());
            _hoaDonService.updateHoaDonChiTiet(hoaDonCT);
        }
        _lstHoaDonChiTiet.put(hoaDonCT.getSach().getId(), hoaDonCT);
        for (HoaDonChiTiet value : _lstHoaDonChiTiet.values()) {
            System.out.println(value);
        }
        loadTableHoaDonCT();
        if (searcher) {
            loadSachSearch(_lstCustomSach, _currentPage, _pageSize);
        } else {
            loadSach(_currentPage, _pageSize);
        }

    }

    private void loadTableHoaDonCT() {
        DefaultTableModel dtm = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        if (_hoaDon == null) {
            _lstHoaDonChiTiet = new HashMap<>();
            dtm.setRowCount(0);
        }
        List<HoaDonChiTiet> lst = _hoaDonService.getAllByMaHD(_hoaDon.getMa());
        _lstHoaDonChiTiet = new HashMap<>();
        for (HoaDonChiTiet hoaDonChiTiet : lst) {
            _lstHoaDonChiTiet.put(hoaDonChiTiet.getSach().getId(), hoaDonChiTiet);
        }
        _tongTien = BigDecimal.ZERO;
        dtm.setRowCount(0);
        for (HoaDonChiTiet hoaDonChiTiet : _lstHoaDonChiTiet.values()) {
            dtm.addRow(new Object[]{hoaDonChiTiet.getSach().getMa(), hoaDonChiTiet.getSach().getTen(), hoaDonChiTiet.getSoLuong(), df.format(hoaDonChiTiet.getDonGia()),
                hoaDonChiTiet.getSach().getHinh() == null ? "" : new ModelProfile(new ImageIcon(new ImageIcon(hoaDonChiTiet.getSach().getHinh()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)))});
            _tongTien = _tongTien.add(BigDecimal.valueOf(Double.parseDouble(hoaDonChiTiet.getSoLuong() + "")).multiply(hoaDonChiTiet.getDonGia()));
        }
        setTienThanhToan(_tongTien);
        refreshSP();
    }
    private final DecimalFormat df = new DecimalFormat("#,###.00");

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanelChonKhachHang = new View.DesignComponent.JPanelBourder();
        lbNameForm = new javax.swing.JLabel();
        jPanelBourder11 = new View.DesignComponent.JPanelBourder();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKhachHang = new View.DesignComponent.Table();
        txtTuKhoa = new javax.swing.JLabel();
        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        btnTaoHoaDon = new View.ButtonDesign.Button();
        jLabel13 = new javax.swing.JLabel();
        jPanelBourder6 = new View.DesignComponent.JPanelBourder();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new View.DesignComponent.Table();
        jSeparator2 = new javax.swing.JSeparator();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        jPanelBourder5 = new View.DesignComponent.JPanelBourder();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new View.DesignComponent.Table();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnDeleteAll = new View.ButtonDesign.Button();
        btnXoa = new View.ButtonDesign.Button();
        btnInBaoCao9 = new View.ButtonDesign.Button();
        jPanelBourder4 = new View.DesignComponent.JPanelBourder();
        jScrollPane1 = new javax.swing.JScrollPane();
        JpanelShowBook = new javax.swing.JPanel();
        txtTimKiem = new View.DesignComponent.TextField();
        btnFist = new View.ButtonDesign.Button();
        btnPrev = new View.ButtonDesign.Button();
        btnNext = new View.ButtonDesign.Button();
        btnLast = new View.ButtonDesign.Button();
        lblPage = new javax.swing.JLabel();
        btnAll = new View.ButtonDesign.Button();
        btnSearch = new View.ButtonDesign.Button();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelBourder3 = new View.DesignComponent.JPanelBourder();
        jPanelBourder7 = new View.DesignComponent.JPanelBourder();
        btnSearchKhachHang = new View.ButtonDesign.Button();
        jLabel5 = new javax.swing.JLabel();
        lblThoiGianTao = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDiemSuDung = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSoDienThoai = new View.DesignComponent.TextField();
        btnDungDiem = new View.ButtonDesign.Button();
        jLabel27 = new javax.swing.JLabel();
        lblKhachHang = new javax.swing.JLabel();
        rdoClearKhachHang = new javax.swing.JRadioButton();
        jPanelBourder8 = new View.DesignComponent.JPanelBourder();
        jLabel10 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        cbHinhThucThanhToan = new View.DesignComponent.Combobox();
        txtTienMat = new View.DesignComponent.TextField();
        txtTienChuyenKhoan = new View.DesignComponent.TextField();
        jLabel12 = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        btnThanhToanVaIn = new View.ButtonDesign.Button();
        btnThanhToan = new View.ButtonDesign.Button();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblDiemDangDung = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanelBourder9 = new View.DesignComponent.JPanelBourder();
        jLabel21 = new javax.swing.JLabel();
        lblThoiGianTaoDatHang = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtSoDienThoaiDatHang = new View.DesignComponent.TextField();
        btnTimKiem = new View.ButtonDesign.Button();
        jLabel28 = new javax.swing.JLabel();
        lblKhachHangDatHang = new javax.swing.JLabel();
        rdoClearKHDatHang = new javax.swing.JRadioButton();
        jLabel30 = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        jPanelBourder10 = new View.DesignComponent.JPanelBourder();
        jLabel25 = new javax.swing.JLabel();
        lblTongTienDH = new javax.swing.JLabel();
        comboboxTrangThai1 = new View.DesignComponent.Combobox();
        btnDatHang = new View.ButtonDesign.Button();
        jLabel29 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();

        jpanelChonKhachHang.setBackground(new java.awt.Color(47, 55, 90));

        lbNameForm.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbNameForm.setForeground(new java.awt.Color(255, 255, 255));
        lbNameForm.setText("Danh sách khách hàng");

        jPanelBourder11.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane4.setBorder(null);

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên", "Tên đệm", "Họ", "SĐT", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setMinimumSize(new java.awt.Dimension(90, 50));
        tblKhachHang.setRowHeight(40);
        tblKhachHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblKhachHang);

        javax.swing.GroupLayout jPanelBourder11Layout = new javax.swing.GroupLayout(jPanelBourder11);
        jPanelBourder11.setLayout(jPanelBourder11Layout);
        jPanelBourder11Layout.setHorizontalGroup(
            jPanelBourder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelBourder11Layout.setVerticalGroup(
            jPanelBourder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtTuKhoa.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        txtTuKhoa.setForeground(new java.awt.Color(255, 255, 255));
        txtTuKhoa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTuKhoa.setText("Danh sách khách hàng");

        javax.swing.GroupLayout jpanelChonKhachHangLayout = new javax.swing.GroupLayout(jpanelChonKhachHang);
        jpanelChonKhachHang.setLayout(jpanelChonKhachHangLayout);
        jpanelChonKhachHangLayout.setHorizontalGroup(
            jpanelChonKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelChonKhachHangLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbNameForm)
                .addGap(236, 236, 236)
                .addComponent(txtTuKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jpanelChonKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelChonKhachHangLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelBourder11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jpanelChonKhachHangLayout.setVerticalGroup(
            jpanelChonKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelChonKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelChonKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNameForm, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTuKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(400, Short.MAX_VALUE))
            .addGroup(jpanelChonKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelChonKhachHangLayout.createSequentialGroup()
                    .addGap(63, 63, 63)
                    .addComponent(jPanelBourder11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        setBackground(new java.awt.Color(11, 20, 55));
        setPreferredSize(new java.awt.Dimension(1362, 790));

        jPanelBourder1.setBackground(new java.awt.Color(17, 28, 68));

        btnTaoHoaDon.setBackground(new java.awt.Color(19, 29, 243));
        btnTaoHoaDon.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnTaoHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoHoaDon.setText("Tạo Hóa Đơn");
        btnTaoHoaDon.setFocusable(false);
        btnTaoHoaDon.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 0, 28)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Hóa Đơn");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanelBourder6.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setBorder(null);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Mã Hóa Đơn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanelBourder6Layout = new javax.swing.GroupLayout(jPanelBourder6);
        jPanelBourder6.setLayout(jPanelBourder6Layout);
        jPanelBourder6Layout.setHorizontalGroup(
            jPanelBourder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelBourder6Layout.setVerticalGroup(
            jPanelBourder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );

        javax.swing.GroupLayout jPanelBourder1Layout = new javax.swing.GroupLayout(jPanelBourder1);
        jPanelBourder1.setLayout(jPanelBourder1Layout);
        jPanelBourder1Layout.setHorizontalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBourder6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2))
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBourder1Layout.setVerticalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(8, 8, 8)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelBourder6, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanelBourder2.setBackground(new java.awt.Color(17, 28, 68));

        jPanelBourder5.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBorder(null);

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên Sách", "Số Lượng", "Đơn Giá", "Hình"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonChiTiet.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tblHoaDonChiTiet);

        javax.swing.GroupLayout jPanelBourder5Layout = new javax.swing.GroupLayout(jPanelBourder5);
        jPanelBourder5.setLayout(jPanelBourder5Layout);
        jPanelBourder5Layout.setHorizontalGroup(
            jPanelBourder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanelBourder5Layout.setVerticalGroup(
            jPanelBourder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI Black", 0, 28)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Hóa Đơn Chi Tiết");

        btnDeleteAll.setBackground(new java.awt.Color(35, 35, 132));
        btnDeleteAll.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnDeleteAll.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteAll.setText("Xóa toàn bộ");
        btnDeleteAll.setFocusable(false);
        btnDeleteAll.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnDeleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAllActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(35, 35, 132));
        btnXoa.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.setFocusable(false);
        btnXoa.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnInBaoCao9.setBackground(new java.awt.Color(35, 35, 132));
        btnInBaoCao9.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnInBaoCao9.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao9.setText("Scan");
        btnInBaoCao9.setFocusable(false);
        btnInBaoCao9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCao9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBourder2Layout = new javax.swing.GroupLayout(jPanelBourder2);
        jPanelBourder2.setLayout(jPanelBourder2Layout);
        jPanelBourder2Layout.setHorizontalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder2Layout.createSequentialGroup()
                .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelBourder2Layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(jLabel14)
                        .addGap(0, 323, Short.MAX_VALUE))
                    .addGroup(jPanelBourder2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelBourder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelBourder2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnInBaoCao9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelBourder2Layout.setVerticalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(8, 8, 8)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelBourder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInBaoCao9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelBourder4.setBackground(new java.awt.Color(17, 28, 68));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        JpanelShowBook.setLayout(new javax.swing.BoxLayout(JpanelShowBook, javax.swing.BoxLayout.X_AXIS));
        jScrollPane1.setViewportView(JpanelShowBook);

        txtTimKiem.setBackground(new java.awt.Color(17, 28, 68));
        txtTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        txtTimKiem.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTimKiem.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTimKiem.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        txtTimKiem.setLabelText("Tìm kiếm theo tên, thể loại, tác giả, barcode, ...");
        txtTimKiem.setLineColor(new java.awt.Color(255, 255, 255));
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        btnFist.setBackground(new java.awt.Color(31, 31, 111));
        btnFist.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnFist.setForeground(new java.awt.Color(255, 255, 255));
        btnFist.setFocusable(false);
        btnFist.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnFist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFistActionPerformed(evt);
            }
        });

        btnPrev.setBackground(new java.awt.Color(31, 31, 111));
        btnPrev.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnPrev.setForeground(new java.awt.Color(255, 255, 255));
        btnPrev.setFocusable(false);
        btnPrev.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(31, 31, 111));
        btnNext.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setFocusable(false);
        btnNext.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(31, 31, 111));
        btnLast.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setFocusable(false);
        btnLast.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        lblPage.setBackground(new java.awt.Color(102, 102, 102));
        lblPage.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        lblPage.setForeground(new java.awt.Color(255, 255, 255));
        lblPage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPage.setText("1/12");
        lblPage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnAll.setBackground(new java.awt.Color(35, 35, 132));
        btnAll.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnAll.setForeground(new java.awt.Color(255, 255, 255));
        btnAll.setText("All");
        btnAll.setFocusable(false);
        btnAll.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(35, 35, 132));
        btnSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tìm kiếm");
        btnSearch.setFocusable(false);
        btnSearch.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBourder4Layout = new javax.swing.GroupLayout(jPanelBourder4);
        jPanelBourder4.setLayout(jPanelBourder4Layout);
        jPanelBourder4Layout.setHorizontalGroup(
            jPanelBourder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder4Layout.createSequentialGroup()
                .addGroup(jPanelBourder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 988, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelBourder4Layout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addComponent(btnFist, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPage, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBourder4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAll, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanelBourder4Layout.setVerticalGroup(
            jPanelBourder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBourder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAll, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBourder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFist, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPage, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.setBackground(new java.awt.Color(51, 51, 255));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N

        jPanelBourder3.setBackground(new java.awt.Color(11, 20, 55));

        jPanelBourder7.setBackground(new java.awt.Color(16, 31, 86));

        btnSearchKhachHang.setBackground(new java.awt.Color(35, 35, 132));
        btnSearchKhachHang.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnSearchKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchKhachHang.setFocusable(false);
        btnSearchKhachHang.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnSearchKhachHang.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnSearchKhachHang.setRound(6);
        btnSearchKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchKhachHangActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Thời gian tạo :");

        lblThoiGianTao.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lblThoiGianTao.setForeground(new java.awt.Color(255, 255, 255));
        lblThoiGianTao.setText("26/11/2022");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Điểm sử dụng :");

        txtDiemSuDung.setBackground(new java.awt.Color(16, 31, 86));
        txtDiemSuDung.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setBackground(new java.awt.Color(102, 102, 102));
        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("Thông tin cơ bản");

        txtSoDienThoai.setBackground(new java.awt.Color(16, 31, 86));
        txtSoDienThoai.setForeground(new java.awt.Color(255, 255, 255));
        txtSoDienThoai.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSoDienThoai.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSoDienThoai.setLabelText("Tìm Kiếm Theo SĐT");
        txtSoDienThoai.setLineColor(new java.awt.Color(255, 255, 255));
        txtSoDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoDienThoaiKeyReleased(evt);
            }
        });

        btnDungDiem.setBackground(new java.awt.Color(35, 35, 132));
        btnDungDiem.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnDungDiem.setForeground(new java.awt.Color(255, 255, 255));
        btnDungDiem.setText("Dùng");
        btnDungDiem.setFocusable(false);
        btnDungDiem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnDungDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDungDiemActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Khách Hàng:");

        lblKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKhachHang.setForeground(new java.awt.Color(255, 255, 255));

        rdoClearKhachHang.setToolTipText("clearKhachHang");
        rdoClearKhachHang.setEnabled(false);
        rdoClearKhachHang.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdoClearKhachHangStateChanged(evt);
            }
        });
        rdoClearKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoClearKhachHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelBourder7Layout = new javax.swing.GroupLayout(jPanelBourder7);
        jPanelBourder7.setLayout(jPanelBourder7Layout);
        jPanelBourder7Layout.setHorizontalGroup(
            jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanelBourder7Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(lblThoiGianTao, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBourder7Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtDiemSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDungDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBourder7Layout.createSequentialGroup()
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rdoClearKhachHang))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBourder7Layout.createSequentialGroup()
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSearchKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanelBourder7Layout.setVerticalGroup(
            jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder7Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder7Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addComponent(lblKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(rdoClearKhachHang)
                        .addGap(26, 26, 26)))
                .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThoiGianTao, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiemSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDungDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        jPanelBourder8.setBackground(new java.awt.Color(16, 31, 86));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tổng Tiền :");

        lblTongTien.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 0, 0));
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTongTien.setToolTipText("");
        lblTongTien.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lblTongTienPropertyChange(evt);
            }
        });

        cbHinhThucThanhToan.setBackground(new java.awt.Color(16, 31, 86));
        cbHinhThucThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        cbHinhThucThanhToan.setMaximumRowCount(10);
        cbHinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tiền Mặt", "Chuyển Khoản", "Kết Hợp" }));
        cbHinhThucThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbHinhThucThanhToan.setLabeText("Hình Thức Thanh Toán");
        cbHinhThucThanhToan.setLineColor(new java.awt.Color(255, 255, 255));
        cbHinhThucThanhToan.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbHinhThucThanhToanPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cbHinhThucThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHinhThucThanhToanActionPerformed(evt);
            }
        });

        txtTienMat.setBackground(new java.awt.Color(16, 31, 86));
        txtTienMat.setForeground(new java.awt.Color(255, 255, 255));
        txtTienMat.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTienMat.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTienMat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTienMat.setLabelText("Tiền Mặt ( Khách Trả )");
        txtTienMat.setLineColor(new java.awt.Color(255, 255, 255));
        txtTienMat.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienMatCaretUpdate(evt);
            }
        });
        txtTienMat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTienMatFocusLost(evt);
            }
        });

        txtTienChuyenKhoan.setEditable(false);
        txtTienChuyenKhoan.setBackground(new java.awt.Color(16, 31, 86));
        txtTienChuyenKhoan.setForeground(new java.awt.Color(51, 255, 51));
        txtTienChuyenKhoan.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTienChuyenKhoan.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTienChuyenKhoan.setEnabled(false);
        txtTienChuyenKhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTienChuyenKhoan.setLabelText("Tiền Chuyển Khoản");
        txtTienChuyenKhoan.setLineColor(new java.awt.Color(255, 255, 255));
        txtTienChuyenKhoan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienChuyenKhoanCaretUpdate(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tiền Thừa :");

        lblTienThua.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        lblTienThua.setForeground(new java.awt.Color(51, 204, 0));
        lblTienThua.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTienThua.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lblTienThuaPropertyChange(evt);
            }
        });

        btnThanhToanVaIn.setBackground(new java.awt.Color(204, 204, 204));
        btnThanhToanVaIn.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnThanhToanVaIn.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToanVaIn.setText("Thanh Toán và In");
        btnThanhToanVaIn.setEnabled(false);
        btnThanhToanVaIn.setFocusable(false);
        btnThanhToanVaIn.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnThanhToanVaIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanVaInActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(204, 204, 204));
        btnThanhToan.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.setToolTipText("");
        btnThanhToan.setEnabled(false);
        btnThanhToan.setFocusable(false);
        btnThanhToan.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(102, 102, 102));
        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Quầy Thanh Toán");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Điểm dùng:");

        lblDiemDangDung.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        lblDiemDangDung.setForeground(new java.awt.Color(51, 204, 0));
        lblDiemDangDung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDiemDangDung.setToolTipText("");
        lblDiemDangDung.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelBourder8Layout = new javax.swing.GroupLayout(jPanelBourder8);
        jPanelBourder8.setLayout(jPanelBourder8Layout);
        jPanelBourder8Layout.setHorizontalGroup(
            jPanelBourder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBourder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbHinhThucThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTienMat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTienChuyenKhoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelBourder8Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThanhToanVaIn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBourder8Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelBourder8Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelBourder8Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(lblDiemDangDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelBourder8Layout.setVerticalGroup(
            jPanelBourder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder8Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanelBourder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiemDangDung, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanelBourder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbHinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTienChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBourder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelBourder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToanVaIn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanelBourder3Layout = new javax.swing.GroupLayout(jPanelBourder3);
        jPanelBourder3.setLayout(jPanelBourder3Layout);
        jPanelBourder3Layout.setHorizontalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBourder7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelBourder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBourder3Layout.setVerticalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Bán Tại Quầy", jPanelBourder3);

        jPanel1.setBackground(new java.awt.Color(16, 31, 86));

        jPanelBourder9.setBackground(new java.awt.Color(16, 31, 86));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Thời gian tạo :");

        lblThoiGianTaoDatHang.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lblThoiGianTaoDatHang.setForeground(new java.awt.Color(255, 255, 255));
        lblThoiGianTaoDatHang.setText("26/11/2022");

        jLabel24.setBackground(new java.awt.Color(102, 102, 102));
        jLabel24.setFont(new java.awt.Font("Segoe UI Semibold", 1, 13)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 204, 204));
        jLabel24.setText("Thông tin cơ bản");

        txtSoDienThoaiDatHang.setBackground(new java.awt.Color(16, 31, 86));
        txtSoDienThoaiDatHang.setForeground(new java.awt.Color(255, 255, 255));
        txtSoDienThoaiDatHang.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSoDienThoaiDatHang.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSoDienThoaiDatHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSoDienThoaiDatHang.setLabelText("Tìm Kiếm Theo SĐT");
        txtSoDienThoaiDatHang.setLineColor(new java.awt.Color(255, 255, 255));
        txtSoDienThoaiDatHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoDienThoaiDatHangKeyReleased(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(35, 35, 132));
        btnTimKiem.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setFocusable(false);
        btnTimKiem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnTimKiem.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnTimKiem.setRound(6);
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Khách Hàng:");

        lblKhachHangDatHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKhachHangDatHang.setForeground(new java.awt.Color(255, 255, 255));

        rdoClearKHDatHang.setToolTipText("clearKhachHang");
        rdoClearKHDatHang.setEnabled(false);
        rdoClearKHDatHang.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdoClearKHDatHangStateChanged(evt);
            }
        });
        rdoClearKHDatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoClearKHDatHangMouseClicked(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Nhân viên:");

        lblNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelBourder9Layout = new javax.swing.GroupLayout(jPanelBourder9);
        jPanelBourder9.setLayout(jPanelBourder9Layout);
        jPanelBourder9Layout.setHorizontalGroup(
            jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder9Layout.createSequentialGroup()
                        .addComponent(txtSoDienThoaiDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(jPanelBourder9Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblKhachHangDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoClearKHDatHang))
                    .addGroup(jPanelBourder9Layout.createSequentialGroup()
                        .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel24)
                            .addGroup(jPanelBourder9Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(lblThoiGianTaoDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelBourder9Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(4, 4, 4)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanelBourder9Layout.setVerticalGroup(
            jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder9Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel24)
                .addGap(11, 11, 11)
                .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(lblNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoDienThoaiDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblKhachHangDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdoClearKHDatHang)
                        .addGap(8, 8, 8))
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThoiGianTaoDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanelBourder10.setBackground(new java.awt.Color(16, 31, 86));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Tổng Tiền :");

        lblTongTienDH.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        lblTongTienDH.setForeground(new java.awt.Color(255, 102, 102));

        comboboxTrangThai1.setBackground(new java.awt.Color(16, 31, 86));
        comboboxTrangThai1.setForeground(new java.awt.Color(255, 255, 255));
        comboboxTrangThai1.setMaximumRowCount(10);
        comboboxTrangThai1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Thanh toán khi nhận hàng" }));
        comboboxTrangThai1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        comboboxTrangThai1.setLabeText("Hình Thức Thanh Toán");
        comboboxTrangThai1.setLineColor(new java.awt.Color(255, 255, 255));
        comboboxTrangThai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxTrangThai1ActionPerformed(evt);
            }
        });

        btnDatHang.setBackground(new java.awt.Color(19, 29, 243));
        btnDatHang.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnDatHang.setForeground(new java.awt.Color(255, 255, 255));
        btnDatHang.setText("Đặt hàng");
        btnDatHang.setFocusable(false);
        btnDatHang.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatHangActionPerformed(evt);
            }
        });

        jLabel29.setBackground(new java.awt.Color(102, 102, 102));
        jLabel29.setFont(new java.awt.Font("Segoe UI Semibold", 1, 13)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(204, 204, 204));
        jLabel29.setText("Thông tin đặt hàng");

        jPanel2.setBackground(new java.awt.Color(11, 20, 55));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 267, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelBourder10Layout = new javax.swing.GroupLayout(jPanelBourder10);
        jPanelBourder10.setLayout(jPanelBourder10Layout);
        jPanelBourder10Layout.setHorizontalGroup(
            jPanelBourder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBourder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addGroup(jPanelBourder10Layout.createSequentialGroup()
                        .addGroup(jPanelBourder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboboxTrangThai1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelBourder10Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTongTienDH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel29))
                        .addContainerGap())))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelBourder10Layout.setVerticalGroup(
            jPanelBourder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder10Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addGroup(jPanelBourder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTongTienDH, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboboxTrangThai1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBourder9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelBourder10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đặt Hàng", jPanel1);

        jLabel15.setFont(new java.awt.Font("Segoe UI Black", 0, 28)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Thanh Toán");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelBourder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelBourder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel15))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelBourder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelBourder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelBourder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Bán");
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        int maHoaDon = _hoaDonService.genMaHoaDon();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa("HD" + maHoaDon);
        hoaDon.setNhanVien(Auth.getNhanVien());
        hoaDon.setNgayTao(new Date());
        hoaDon.setTrangThai(TrangThaiHoaDon.CHUATHANHTOAN);

        _hoaDonService.taoHoaDon(hoaDon);

        loadTableHoaDon();
        tblHoaDon.setRowSelectionInterval(0, 0);
        _hoaDon = _lstHoaDon.get(0);
        lblThoiGianTao.setText(_hoaDon.getNgayTao() + "");
        loadTableHoaDonCT();

    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnThanhToanVaInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanVaInActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thanh toán?");
        if (confirm == JOptionPane.YES_OPTION) {
            Date ngayThanhToan = new Date();
            _hoaDon.setNgayThanhToan(ngayThanhToan);
            _hoaDon.setNgayNhan(ngayThanhToan);
            _hoaDon.setSoDiemSuDung(_diemSuDung);
            _hoaDon.setTrangThai(TrangThaiHoaDon.DATHANHTOAN);
            PrintOrder print = new PrintOrder();
            boolean printSuccess = print.print();
            if (!printSuccess) {
                return;
            }
            boolean updateStatus = _hoaDonService.updateHoaDon(_hoaDon);
            JOptionPane.showMessageDialog(this, updateStatus ? "Thành công" : "Thất bại");
            loadTableHoaDon();
            loadTableHoaDonCT();
        }
    }//GEN-LAST:event_btnThanhToanVaInActionPerformed

    private void cbHinhThucThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHinhThucThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbHinhThucThanhToanActionPerformed

    private void txtTienMatCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienMatCaretUpdate

    }//GEN-LAST:event_txtTienMatCaretUpdate

    private void txtTienChuyenKhoanCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienChuyenKhoanCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienChuyenKhoanCaretUpdate

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thanh toán?");
        if (confirm == JOptionPane.YES_OPTION) {
            Date ngayThanhToan = new Date();
            _hoaDon.setNgayThanhToan(ngayThanhToan);
            _hoaDon.setNgayNhan(ngayThanhToan);
            _hoaDon.setSoDiemSuDung(_diemSuDung);
            _hoaDon.setTrangThai(TrangThaiHoaDon.DATHANHTOAN);
            boolean updateStatus = _hoaDonService.updateHoaDon(_hoaDon);
            JOptionPane.showMessageDialog(this, updateStatus ? "Thành công" : "Thất bại");
            loadTableHoaDon();
            loadTableHoaDonCT();
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void comboboxTrangThai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxTrangThai1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboboxTrangThai1ActionPerformed

    private void btnDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDatHangActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnFistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFistActionPerformed
        if (_currentPage == 1) {
            return;
        }
        _currentPage = 1;
        if (searcher) {
            loadSachSearch(_lstCustomSach, 1, _pageSize);
        } else {
            loadCustomSach();
        }
        setLabelPage();
    }//GEN-LAST:event_btnFistActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        if (_currentPage == 1) {
            return;
        }
        setLabelPage();
        _currentPage = _currentPage--;
        refreshSP();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (_currentPage == _totalPage || _currentPage > _totalPage) {
            return;
        }
        _currentPage = _currentPage++;
        refreshSP();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        if (_currentPage == _totalPage || _currentPage > _totalPage) {
            return;
        }
        setLabelPage();
        _currentPage = _totalPage;
        refreshSP();
    }//GEN-LAST:event_btnLastActionPerformed

    private void txtSoDienThoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKeyReleased

    }//GEN-LAST:event_txtSoDienThoaiKeyReleased

    private void btnDeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAllActionPerformed

        if (_hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn");
            return;
        }
        if (_lstHoaDonChiTiet.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có gì để xóa");
            return;
        }
        _hoaDonService.removeAllHoaDonCT(_hoaDon);
    }//GEN-LAST:event_btnDeleteAllActionPerformed

    private void refreshSP() {
        setPageLabel(searcher);
        if (searcher) {
            loadSachSearch(_lstCustomSach, _currentPage, _totalPage);
        } else {
            loadSach(_currentPage, _pageSize);
        }
    }
    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tblHoaDonChiTiet.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm");
            return;
        }

        if (_hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn");
            return;
        }
        for (HoaDonChiTiet hoaDonCT : _lstHoaDonChiTiet.values()) {
            if (hoaDonCT.getSach().getMa().equalsIgnoreCase(tblHoaDonChiTiet.getValueAt(row, 0) + "")) {
                JOptionPane.showConfirmDialog(this, "Xóa?");
                _hoaDonService.removeHoaDonChiTiet(hoaDonCT);
            }
        }
        loadTableHoaDonCT();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnInBaoCao9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao9ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String keyword = txtTimKiem.getText();
        searcher = true;
        if (keyword.isBlank()) {
            setPageLabel(false);
            loadSach(1, _pageSize);
            searcher = false;
            return;
        }
        _currentPage = 1;
        loadSachSearch(_customSachService.getAllByKeyword(keyword), _currentPage, _pageSize);
        System.out.println(_lstCustomSach.size());
        setPageLabel(true);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllActionPerformed
        searcher = false;
        _currentPage = 1;
        loadSach(_currentPage, _pageSize);
        txtTimKiem.setText("");
    }//GEN-LAST:event_btnAllActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int row = tblHoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }
        _hoaDon = _lstHoaDon.get(row);
        loadTableHoaDonCT();
        lblThoiGianTao.setText(_hoaDon.getNgayTao() + "");
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnDungDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDungDiemActionPerformed

        String diemStr = txtDiemSuDung.getText().trim();

        if (_khachHang == null) {
            JOptionPane.showMessageDialog(this, "Chưa chọn khách hàng");
            return;
        }
        if (!diemStr.matches("\\d+") || diemStr.equals("0")) {
            JOptionPane.showMessageDialog(this, "Không đúng định dạng");
            return;
        }
        if (diemStr.length() > 8) {
            JOptionPane.showMessageDialog(this, "Số điểm quá lớn. Không thể dùng");
            return;
        }

        int diem = Integer.parseInt(diemStr);
        if (_khachHang.getDiemTichLuy() < diem) {
            JOptionPane.showMessageDialog(this, "Điểm không đủ. Bạn đang có: " + _khachHang.getDiemTichLuy());
            return;
        }
        JOptionPane.showMessageDialog(this, "Thành công");
        _diemSuDung = diem;
        lblDiemDangDung.setText("-" + diemStr + ",000 VNĐ");
        setTienThanhToan(_tongTien);
    }//GEN-LAST:event_btnDungDiemActionPerformed

    private void setTienThanhToan(BigDecimal tongTien) {
        int hinhThuc = cbHinhThucThanhToan.getSelectedIndex();
        BigDecimal tienPhaiTra = _tongTien.subtract(BigDecimal.valueOf(_diemSuDung).multiply(BigDecimal.valueOf(1000)));
        lblTongTien.setText(df.format(tienPhaiTra) + " VNĐ");
        if (hinhThuc == 0) {
            txtTienMat.setEnabled(true);
            txtTienChuyenKhoan.setText("");

        } else {
            txtTienMat.setEnabled(false);
            txtTienMat.setText("");
            txtTienChuyenKhoan.setText(df.format(tienPhaiTra));
            _tienThua = BigDecimal.ZERO;
        }
        BigDecimal tienDaTra = BigDecimal.ZERO;
        if (!txtTienMat.getText().isBlank()) {
            tienDaTra = BigDecimal.valueOf(Double.parseDouble(txtTienMat.getText() + ""));
        }
        _tienThua = tienDaTra.subtract(_tongTien.subtract(BigDecimal.valueOf(_diemSuDung).multiply(BigDecimal.valueOf(1000))));
        lblTienThua.setText(df.format(_tienThua) + " VNĐ");
        if (_tienThua.compareTo(BigDecimal.ZERO) < 0) {
            lblTienThua.setForeground(Color.red);
        } else {
            lblTienThua.setForeground(Color.GREEN);
        }

    }

    private void cbHinhThucThanhToanPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbHinhThucThanhToanPopupMenuWillBecomeInvisible
        setTienThanhToan(_tongTien);
        setEnableButton();
    }//GEN-LAST:event_cbHinhThucThanhToanPopupMenuWillBecomeInvisible
    private final JDialog _khachHangDialog = new JDialog();

    private void setKhachHangDialog() {
        _khachHangDialog.setSize(912, 500);
        _khachHangDialog.setModal(true);
        _khachHangDialog.add(this.jpanelChonKhachHang);
        _khachHangDialog.setLocationRelativeTo(null);
    }
    private void btnSearchKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchKhachHangActionPerformed
        String sdt = txtSoDienThoai.getText();
        if (!sdt.matches("(\\+)?\\d+")) {
            JOptionPane.showMessageDialog(this, "Không đúng định dạng");
            return;
        }
        _lstKhachHang = _khachHangService.sellectAllBySDT(sdt);
        if (_lstKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy");
        } else if (_lstKhachHang.size() == 1) {
            _khachHang = _lstKhachHang.get(0);
        } else {
            loadTableKhachHang();
            _khachHangDialog.setVisible(true);
        }
        loadLabelKhachHang(_khachHang);
    }//GEN-LAST:event_btnSearchKhachHangActionPerformed

    private void loadLabelKhachHang(KhachHang khachHang) {
        if (khachHang == null) {
            lblKhachHang.setText("");
            return;
        }
        rdoClearKhachHang.setSelected(true);
        lblKhachHang.setText(khachHang.toString());
    }

    private void loadTableKhachHang() {
        DefaultTableModel dtm = (DefaultTableModel) tblKhachHang.getModel();
        dtm.setRowCount(0);
        for (KhachHang khachHang : _lstKhachHang) {
            dtm.addRow(new Object[]{khachHang.getMa(), khachHang.getTen(), khachHang.getTenDem(), khachHang.getHo(), khachHang.getSdt(), khachHang.getDiaChi()});
        }
    }

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        if (evt.getClickCount() == 2) {
            int row = tblKhachHang.getSelectedRow();
            if (row == -1) {
                return;
            }

            _khachHang = _lstKhachHang.get(row);
            loadLabelKhachHang(_khachHang);
            _khachHangDialog.setVisible(false);
        }

    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void rdoClearKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoClearKhachHangMouseClicked

    }//GEN-LAST:event_rdoClearKhachHangMouseClicked

    private void rdoClearKhachHangStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoClearKhachHangStateChanged
        if (rdoClearKhachHang.isSelected()) {
            rdoClearKhachHang.setEnabled(true);
        } else {
            rdoClearKhachHang.setEnabled(false);
            _khachHang = null;
            _diemSuDung = 0;
            lblDiemDangDung.setText("");
            setTienThanhToan(_tongTien);
            loadLabelKhachHang(_khachHang);
        }
    }//GEN-LAST:event_rdoClearKhachHangStateChanged

    private void txtSoDienThoaiDatHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiDatHangKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiDatHangKeyReleased

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void rdoClearKHDatHangStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoClearKHDatHangStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoClearKHDatHangStateChanged

    private void rdoClearKHDatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoClearKHDatHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoClearKHDatHangMouseClicked

    private void setEnableButton() {

        if (_hoaDon == null) {
            btnThanhToan.setEnabled(false);
            btnThanhToanVaIn.setEnabled(false);
            btnThanhToan.setBackground(new Color(204, 204, 204));
            btnThanhToanVaIn.setBackground(new Color(204, 204, 204));
            txtTienMat.setEnabled(false);
            return;
        }

        if (_tienThua.compareTo(BigDecimal.ZERO) < 0) {
            btnThanhToan.setEnabled(false);
            btnThanhToanVaIn.setEnabled(false);
            btnThanhToan.setBackground(new Color(204, 204, 204));
            btnThanhToanVaIn.setBackground(new Color(204, 204, 204));
        } else {
            btnThanhToan.setEnabled(true);
            btnThanhToanVaIn.setEnabled(true);
            btnThanhToan.setBackground(new Color(35, 35, 132));
            btnThanhToanVaIn.setBackground(new Color(35, 35, 132));
        }
    }

    private void lblTienThuaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lblTienThuaPropertyChange
        setEnableButton();
    }//GEN-LAST:event_lblTienThuaPropertyChange

    private void txtTienMatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTienMatFocusLost
        String tienMat = txtTienMat.getText();
        if (!tienMat.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Không đúng định dạng");
            txtTienMat.setText("");
            return;
        }
        setTienThanhToan(_tongTien);
    }//GEN-LAST:event_txtTienMatFocusLost

    private void lblTongTienPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lblTongTienPropertyChange
        setTienThanhToan(_tongTien);
        setEnableButton();
    }//GEN-LAST:event_lblTongTienPropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelShowBook;
    private View.ButtonDesign.Button btnAll;
    private View.ButtonDesign.Button btnDatHang;
    private View.ButtonDesign.Button btnDeleteAll;
    private View.ButtonDesign.Button btnDungDiem;
    private View.ButtonDesign.Button btnFist;
    private View.ButtonDesign.Button btnInBaoCao9;
    private View.ButtonDesign.Button btnLast;
    private View.ButtonDesign.Button btnNext;
    private View.ButtonDesign.Button btnPrev;
    private View.ButtonDesign.Button btnSearch;
    private View.ButtonDesign.Button btnSearchKhachHang;
    private View.ButtonDesign.Button btnTaoHoaDon;
    private View.ButtonDesign.Button btnThanhToan;
    private View.ButtonDesign.Button btnThanhToanVaIn;
    private View.ButtonDesign.Button btnTimKiem;
    private View.ButtonDesign.Button btnXoa;
    private View.DesignComponent.Combobox cbHinhThucThanhToan;
    private View.DesignComponent.Combobox comboboxTrangThai1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private View.DesignComponent.JPanelBourder jPanelBourder10;
    private View.DesignComponent.JPanelBourder jPanelBourder11;
    private View.DesignComponent.JPanelBourder jPanelBourder2;
    private View.DesignComponent.JPanelBourder jPanelBourder3;
    private View.DesignComponent.JPanelBourder jPanelBourder4;
    private View.DesignComponent.JPanelBourder jPanelBourder5;
    private View.DesignComponent.JPanelBourder jPanelBourder6;
    private View.DesignComponent.JPanelBourder jPanelBourder7;
    private View.DesignComponent.JPanelBourder jPanelBourder8;
    private View.DesignComponent.JPanelBourder jPanelBourder9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private View.DesignComponent.JPanelBourder jpanelChonKhachHang;
    private javax.swing.JLabel lbNameForm;
    private javax.swing.JLabel lblDiemDangDung;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblKhachHangDatHang;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblPage;
    private javax.swing.JLabel lblThoiGianTao;
    private javax.swing.JLabel lblThoiGianTaoDatHang;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTongTienDH;
    private javax.swing.JRadioButton rdoClearKHDatHang;
    private javax.swing.JRadioButton rdoClearKhachHang;
    private View.DesignComponent.Table tblHoaDon;
    private View.DesignComponent.Table tblHoaDonChiTiet;
    private View.DesignComponent.Table tblKhachHang;
    private javax.swing.JTextField txtDiemSuDung;
    private View.DesignComponent.TextField txtSoDienThoai;
    private View.DesignComponent.TextField txtSoDienThoaiDatHang;
    private View.DesignComponent.TextField txtTienChuyenKhoan;
    private View.DesignComponent.TextField txtTienMat;
    private View.DesignComponent.TextField txtTimKiem;
    private javax.swing.JLabel txtTuKhoa;
    // End of variables declaration//GEN-END:variables

    class PrintOrder {

        boolean print() {
            try {
                String path = "hoaDon//" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSSZ").format(_hoaDon.getNgayThanhToan()) + ".pdf";
                File file = new File(path);
                if (!file.exists()) {
                    file.createNewFile();
                }
                CustomDashedLineSeparator separator = new CustomDashedLineSeparator();
                Document document = new Document();

                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();

                Paragraph conpany = new Paragraph("FBook");
                Paragraph title = new Paragraph("HOA DON THANH TOAN", FontFactory.getFont(FontFactory.TIMES_BOLD, 25f));
                title.setAlignment(Element.ALIGN_CENTER);

                Paragraph nhanVien = new Paragraph("Nhan Vien:       " + separator.removeAccent(Auth.getNhanVien().getHo() + " " + Auth.getNhanVien().getTenDem() + " " + Auth.getNhanVien().getTen()));

                Paragraph khachHang;
                if (_khachHang == null) {
                    khachHang = new Paragraph("Khach Hang:      ");
                } else {
                    khachHang = new Paragraph("Khach Hang:      " + _khachHang == null ? "" : separator.removeAccent(_khachHang.getHo() + " " + _khachHang.getTenDem() + " " + _khachHang.getTen()));
                }

                Paragraph ngayTao = new Paragraph("Ngày thanh toán: " + _hoaDon.getNgayThanhToan());

                separator.setDash(8);
                separator.setGap(7);
                separator.setLineWidth(1);
                Chunk linebreak = new Chunk(separator);

                PdfPTable table = new PdfPTable(new float[]{4, 1, 2, 2});

                PdfPCell c1 = new PdfPCell(new Phrase("Ten San Pham"));
                c1.setBorder(Rectangle.BOTTOM);
                c1.setVerticalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase("So luong"));
                c1.setBorder(Rectangle.BOTTOM);
                c1.setVerticalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase("Don gia"));
                c1.setBorder(Rectangle.BOTTOM);
                c1.setVerticalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase("Tong"));
                c1.setBorder(Rectangle.BOTTOM);
                c1.setVerticalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                table.setHeaderRows(1);

                for (HoaDonChiTiet hoaDonCT : _lstHoaDonChiTiet.values()) {
                    c1 = new PdfPCell(new Phrase(separator.removeAccent(hoaDonCT.getSach().getTen())));
                    c1.setBorder(Rectangle.NO_BORDER);
                    table.addCell(c1);

                    c1 = new PdfPCell(new Phrase(hoaDonCT.getSoLuong()));
                    c1.setBorder(Rectangle.NO_BORDER);
                    table.addCell(c1);

                    c1 = new PdfPCell(new Phrase(df.format(hoaDonCT.getDonGia()) + " VND"));
                    c1.setBorder(Rectangle.NO_BORDER);
                    table.addCell(c1);

                    c1 = new PdfPCell(new Phrase(df.format(hoaDonCT.getDonGia().multiply(BigDecimal.valueOf(hoaDonCT.getSoLuong()))) + " VND"));
                    c1.setBorder(Rectangle.NO_BORDER);
                    table.addCell(c1);

                }

                Paragraph tongTien = new Paragraph("Tong ten: " + df.format(_tongTien) + " VND");
                tongTien.setAlignment(Element.ALIGN_RIGHT);

                Paragraph diemDung = new Paragraph("Diem dung: " + _diemSuDung);
                diemDung.setAlignment(Element.ALIGN_RIGHT);

                Paragraph phaiTra = new Paragraph("Phai tra : " + df.format(_tongTien.subtract(BigDecimal.valueOf(_diemSuDung).multiply(BigDecimal.valueOf(1000)))) + " VNĐ");
                phaiTra.setAlignment(Element.ALIGN_RIGHT);

                Paragraph khachDua = new Paragraph("Khach tra: " + df.format(_tongTien.subtract(BigDecimal.valueOf(_diemSuDung).multiply(BigDecimal.valueOf(1000))).add(_tienThua)) + " VND");
                khachDua.setAlignment(Element.ALIGN_RIGHT);

                Paragraph tienThua = new Paragraph("Tien thua: " + df.format(_tienThua) + " VND");
                tienThua.setAlignment(Element.ALIGN_RIGHT);

                document.add(conpany);
                document.add(title);
                document.add(nhanVien);
                document.add(khachHang);
                document.add(ngayTao);
                document.add(linebreak);
                document.add(table);
                document.add(linebreak);
                document.add(tongTien);
                document.add(diemDung);
                document.add(phaiTra);
                document.add(khachDua);
                document.add(tienThua);

                document.close();

            } catch (IOException ex) {
                Logger.getLogger(Pos_MayBanHang.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (DocumentException ex) {
                Logger.getLogger(Pos_MayBanHang.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            return true;
        }

        class CustomDashedLineSeparator extends DottedLineSeparator {

            protected float dash = 5;
            protected float phase = 2.5f;

            public float getDash() {
                return dash;
            }

            public float getPhase() {
                return phase;
            }

            public void setDash(float dash) {
                this.dash = dash;
            }

            public void setPhase(float phase) {
                this.phase = phase;
            }

            @Override
            public void draw(PdfContentByte canvas,
                    float llx, float lly, float urx, float ury, float y) {
                canvas.saveState();
                canvas.setLineWidth(lineWidth);
                canvas.setLineDash(dash, gap, phase);
                drawLine(canvas, llx, urx, y);
                canvas.restoreState();
            }

            public String removeAccent(String s) {
                String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
                Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
                temp = pattern.matcher(temp).replaceAll("");
                return temp.replaceAll("đ", "d").replaceAll("Đ", "D");
            }
        }
    }
}
