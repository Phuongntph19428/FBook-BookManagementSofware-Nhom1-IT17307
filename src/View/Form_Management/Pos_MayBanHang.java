/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import View.DesignComponent.Item_Pos;
import View.DesignComponent.ModelProfile;
import View.soundeffect.MySoundEffect;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.HinhThucThanhToan;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import service.CustomSachService;
import service.HinhThucThanhToanService;
import service.HoaDonService;
import service.KhachHangService;
import service.impl.CustomSachServiceImpl;
import service.impl.HinhThucThanhToanServiceImpl;
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

    private final HinhThucThanhToanService _hinhThucThanhToanService;

//    List<SachFake> listS = new ArrayList<>();
    public Pos_MayBanHang() {
        initComponents();
        setComponent();
        _hoaDonService = new HoaDonServiceImpl();
        _customSachService = new CustomSachServiceImpl();
        _khachHangService = new KhachHangServiceImpl();
        _hinhThucThanhToanService = new HinhThucThanhToanServiceImpl();

        loadCustomSach();
        loadTableHoaDon();
//        loadTableHoaDonCT();

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
        tblHoaDonChiTiet.setRowHeight(50);
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
        btnTimKiem.setIcon(kh);

        tblHoaDonChiTiet.getModel().addTableModelListener((TableModelEvent e) -> {
            changeAmountProduct(e);
        });

        DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
        rendar.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        tblHoaDonChiTiet.getTableHeader().setDefaultRenderer(rendar);
        tblHoaDonChiTiet.getColumnModel().getColumn(0).setCellRenderer(rendar);
        tblHoaDonChiTiet.getColumnModel().getColumn(3).setCellRenderer(rendar);
        rendar = new DefaultTableCellRenderer();
        rendar.setHorizontalAlignment(javax.swing.JLabel.LEFT);
        tblHoaDonChiTiet.getColumnModel().getColumn(1).setCellRenderer(rendar);
        rendar = new DefaultTableCellRenderer();
        rendar.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        tblHoaDonChiTiet.getColumnModel().getColumn(4).setCellRenderer(rendar);
        tblHoaDonChiTiet.getColumnModel().getColumn(5).setCellRenderer(rendar);
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

    private void changeAmountProduct(TableModelEvent e) {
        if (_lstHoaDonChiTiet.isEmpty()) {
            return;
        }
        HoaDonChiTiet hoaDonCT = (HoaDonChiTiet) _lstHoaDonChiTiet.values().toArray()[0];
        if (e.getColumn() != 2) {
            return;
        }
        int amountChanged = Integer.parseInt(tblHoaDonChiTiet.getValueAt(e.getFirstRow(), 2) + "");

        if (amountChanged <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
            tblHoaDonChiTiet.setValueAt(hoaDonCT.getSoLuong(), e.getFirstRow(), 2);
            return;
        }
        hoaDonCT.setSoLuong(amountChanged);
        _hoaDonService.updateHoaDonChiTiet(hoaDonCT);
        refreshSP();
        _lstHoaDonChiTiet.put(hoaDonCT.getId(), hoaDonCT);
    }

    public void loadSachSearch(List<CustomSach> lst, int position, int pageSize) {
        _lstCustomSach = lst;
        int size = pageSize * position;
        initJpanelSach((position - 1) * pageSize, size);
//        JpanelShowBook.removeAll();
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
            setEnableButton();
            return;
        }
        List<HoaDonChiTiet> lst = _hoaDonService.getAllByMaHD(_hoaDon.getMa());
        _lstHoaDonChiTiet = new HashMap<>();
        for (HoaDonChiTiet hoaDonChiTiet : lst) {
            _lstHoaDonChiTiet.put(hoaDonChiTiet.getSach().getId(), hoaDonChiTiet);
        }
        _tongTien = BigDecimal.ZERO;
        dtm.setRowCount(0);
        for (HoaDonChiTiet hoaDonChiTiet : _lstHoaDonChiTiet.values()) {
            dtm.addRow(new Object[]{hoaDonChiTiet.getSach().getMa(), hoaDonChiTiet.getSach().getTen(),
                hoaDonChiTiet.getSach().getHinh() == null ? "" : new ModelProfile(new ImageIcon(new ImageIcon(hoaDonChiTiet.getSach().getHinh()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT))),
                hoaDonChiTiet.getSoLuong(), "<html>" + df.format(hoaDonChiTiet.getDonGia()) + "<sup style='Color: Red'>vnđ</sup></html>",
                "<html>" + df.format(hoaDonChiTiet.getDonGia().multiply(BigDecimal.valueOf(hoaDonChiTiet.getSoLuong()))) + "<sup style='Color: Red'>vnđ</sup></html>"});
            _tongTien = _tongTien.add(BigDecimal.valueOf(Double.parseDouble(hoaDonChiTiet.getSoLuong() + "")).multiply(hoaDonChiTiet.getDonGia()));
        }
        setTienThanhToan(_tongTien);
        setEnableButton();
        refreshSP();
    }
    private final DecimalFormat df = new DecimalFormat("#,###");

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanelChonKhachHang = new View.DesignComponent.JPanelBourder();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanelBourder13 = new View.DesignComponent.JPanelBourder();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblKhachHang = new View.DesignComponent.Table();
        lblTuKhoa = new javax.swing.JLabel();
        lbNameForm = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtTen = new View.DesignComponent.TextField();
        txtTenDem = new View.DesignComponent.TextField();
        txtHo = new View.DesignComponent.TextField();
        rdoNam = new View.ComboBoxDesign.RadioButtonCustom();
        rdoNu = new View.ComboBoxDesign.RadioButtonCustom();
        jLabel3 = new javax.swing.JLabel();
        txtMa = new View.DesignComponent.TextField();
        txtSDT = new View.DesignComponent.TextField();
        txtNgaySinh = new View.DesignComponent.TextField();
        btnNgaySinh = new View.ButtonDesign.Button();
        txtDiaChi = new View.DesignComponent.TextField();
        btnAddKhachHang = new View.ButtonDesign.Button();
        btnClear = new View.ButtonDesign.Button();
        ngaysinh = new View.DateChooser.DateChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
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
        btnScan = new View.ButtonDesign.Button();
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
        btnDatHang = new View.ButtonDesign.Button();
        jLabel29 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();

        jpanelChonKhachHang.setBackground(new java.awt.Color(47, 55, 90));
        jpanelChonKhachHang.setMaximumSize(new java.awt.Dimension(1099, 509));
        jpanelChonKhachHang.setMinimumSize(new java.awt.Dimension(1099, 509));

        jPanel3.setBackground(new java.awt.Color(47, 55, 90));

        jPanelBourder13.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane6.setBorder(null);

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
        jScrollPane6.setViewportView(tblKhachHang);

        javax.swing.GroupLayout jPanelBourder13Layout = new javax.swing.GroupLayout(jPanelBourder13);
        jPanelBourder13.setLayout(jPanelBourder13Layout);
        jPanelBourder13Layout.setHorizontalGroup(
            jPanelBourder13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1169, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelBourder13Layout.setVerticalGroup(
            jPanelBourder13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblTuKhoa.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lblTuKhoa.setForeground(new java.awt.Color(255, 255, 255));
        lblTuKhoa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTuKhoa.setText("Danh sách khách hàng");

        lbNameForm.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbNameForm.setForeground(new java.awt.Color(255, 255, 255));
        lbNameForm.setText("Danh sách khách hàng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbNameForm, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(467, 467, 467)
                        .addComponent(lblTuKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelBourder13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNameForm, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTuKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Chọn khách hàng", jPanel3);

        jPanel4.setBackground(new java.awt.Color(47, 55, 90));

        txtTen.setBackground(new java.awt.Color(47, 55, 90));
        txtTen.setForeground(new java.awt.Color(255, 255, 255));
        txtTen.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTen.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTen.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTen.setLabelText("Tên");
        txtTen.setLineColor(new java.awt.Color(255, 255, 255));

        txtTenDem.setBackground(new java.awt.Color(47, 55, 90));
        txtTenDem.setForeground(new java.awt.Color(255, 255, 255));
        txtTenDem.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTenDem.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTenDem.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTenDem.setLabelText("Tên Đệm");
        txtTenDem.setLineColor(new java.awt.Color(255, 255, 255));

        txtHo.setBackground(new java.awt.Color(47, 55, 90));
        txtHo.setForeground(new java.awt.Color(255, 255, 255));
        txtHo.setCaretColor(new java.awt.Color(255, 255, 255));
        txtHo.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtHo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtHo.setLabelText("Họ");
        txtHo.setLineColor(new java.awt.Color(255, 255, 255));

        rdoNam.setBackground(new java.awt.Color(47, 55, 90));
        buttonGroup1.add(rdoNam);
        rdoNam.setForeground(new java.awt.Color(255, 255, 255));
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        rdoNu.setBackground(new java.awt.Color(47, 55, 90));
        buttonGroup1.add(rdoNu);
        rdoNu.setForeground(new java.awt.Color(255, 255, 255));
        rdoNu.setText("Nữ");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Giới Tính");

        txtMa.setBackground(new java.awt.Color(47, 55, 90));
        txtMa.setForeground(new java.awt.Color(255, 255, 255));
        txtMa.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMa.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMa.setLabelText("Mã Khách Hàng");
        txtMa.setLineColor(new java.awt.Color(255, 255, 255));

        txtSDT.setBackground(new java.awt.Color(47, 55, 90));
        txtSDT.setForeground(new java.awt.Color(255, 255, 255));
        txtSDT.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSDT.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSDT.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtSDT.setLabelText("SĐT");
        txtSDT.setLineColor(new java.awt.Color(255, 255, 255));

        txtNgaySinh.setBackground(new java.awt.Color(47, 55, 90));
        txtNgaySinh.setForeground(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtNgaySinh.setLabelText("Ngày Sinh");
        txtNgaySinh.setLineColor(new java.awt.Color(255, 255, 255));
        txtNgaySinh.setMargin(new java.awt.Insets(2, 10, 2, 6));

        btnNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgaySinhActionPerformed(evt);
            }
        });

        txtDiaChi.setBackground(new java.awt.Color(47, 55, 90));
        txtDiaChi.setForeground(new java.awt.Color(255, 255, 255));
        txtDiaChi.setCaretColor(new java.awt.Color(255, 255, 255));
        txtDiaChi.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtDiaChi.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtDiaChi.setLabelText("Địa chỉ");
        txtDiaChi.setLineColor(new java.awt.Color(255, 255, 255));

        btnAddKhachHang.setBackground(new java.awt.Color(35, 35, 132));
        btnAddKhachHang.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnAddKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnAddKhachHang.setText("Thêm khách hàng");
        btnAddKhachHang.setFocusable(false);
        btnAddKhachHang.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnAddKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKhachHangActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(35, 35, 132));
        btnClear.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear Form");
        btnClear.setToolTipText("Quét qr code trên căn cước công dân");
        btnClear.setFocusable(false);
        btnClear.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(48, 48, 48)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtHo, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(328, 328, 328)
                        .addComponent(btnAddKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        jTabbedPane2.addTab("Thêm khách hàng", jPanel4);

        javax.swing.GroupLayout jpanelChonKhachHangLayout = new javax.swing.GroupLayout(jpanelChonKhachHang);
        jpanelChonKhachHang.setLayout(jpanelChonKhachHangLayout);
        jpanelChonKhachHangLayout.setHorizontalGroup(
            jpanelChonKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jpanelChonKhachHangLayout.setVerticalGroup(
            jpanelChonKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        ngaysinh.setForeground(new java.awt.Color(47, 55, 90));
        ngaysinh.setTextRefernce(txtNgaySinh);

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
        tblHoaDon.setShowGrid(true);
        tblHoaDon.setSurrendersFocusOnKeystroke(true);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseEntered(evt);
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

        tblHoaDonChiTiet.setForeground(new java.awt.Color(51, 51, 51));
        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên Sách", "Hình", "Số Lượng", "Đơn Giá", "Tổng tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblHoaDonChiTiet.setGridColor(new java.awt.Color(204, 229, 250));
        tblHoaDonChiTiet.setRowMargin(5);
        tblHoaDonChiTiet.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblHoaDonChiTiet.setShowGrid(true);
        tblHoaDonChiTiet.setShowHorizontalLines(false);
        tblHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDonChiTiet);
        if (tblHoaDonChiTiet.getColumnModel().getColumnCount() > 0) {
            tblHoaDonChiTiet.getColumnModel().getColumn(0).setMinWidth(75);
            tblHoaDonChiTiet.getColumnModel().getColumn(0).setPreferredWidth(75);
            tblHoaDonChiTiet.getColumnModel().getColumn(0).setMaxWidth(75);
            tblHoaDonChiTiet.getColumnModel().getColumn(2).setMinWidth(50);
            tblHoaDonChiTiet.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblHoaDonChiTiet.getColumnModel().getColumn(2).setMaxWidth(50);
            tblHoaDonChiTiet.getColumnModel().getColumn(3).setMinWidth(100);
            tblHoaDonChiTiet.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblHoaDonChiTiet.getColumnModel().getColumn(3).setMaxWidth(100);
            tblHoaDonChiTiet.getColumnModel().getColumn(4).setMinWidth(150);
            tblHoaDonChiTiet.getColumnModel().getColumn(4).setPreferredWidth(150);
            tblHoaDonChiTiet.getColumnModel().getColumn(4).setMaxWidth(150);
            tblHoaDonChiTiet.getColumnModel().getColumn(5).setMinWidth(150);
            tblHoaDonChiTiet.getColumnModel().getColumn(5).setPreferredWidth(150);
            tblHoaDonChiTiet.getColumnModel().getColumn(5).setMaxWidth(150);
        }

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

        btnDeleteAll.setBackground(new java.awt.Color(204, 204, 204));
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

        btnXoa.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.setEnabled(false);
        btnXoa.setFocusable(false);
        btnXoa.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnScan.setBackground(new java.awt.Color(204, 204, 204));
        btnScan.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnScan.setForeground(new java.awt.Color(255, 255, 255));
        btnScan.setText("Scan");
        btnScan.setEnabled(false);
        btnScan.setFocusable(false);
        btnScan.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnScan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanActionPerformed(evt);
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
                        .addComponent(btnScan, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnScan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        cbHinhThucThanhToan.setMaximumRowCount(3);
        cbHinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chuyển khoản", "Tiền mặt", "Kết hợp" }));
        cbHinhThucThanhToan.setSelectedIndex(1);
        cbHinhThucThanhToan.setToolTipText("Hình thức thanh toán");
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
        txtTienMat.setForeground(new java.awt.Color(51, 255, 51));
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
        txtTienMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienMatKeyReleased(evt);
            }
        });

        txtTienChuyenKhoan.setEditable(false);
        txtTienChuyenKhoan.setBackground(new java.awt.Color(16, 31, 86));
        txtTienChuyenKhoan.setForeground(new java.awt.Color(51, 255, 51));
        txtTienChuyenKhoan.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTienChuyenKhoan.setDisabledTextColor(new java.awt.Color(255, 255, 255));
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
                        .addGap(0, 8, Short.MAX_VALUE)
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
                .addGroup(jPanelBourder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKhachHangDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelBourder9Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(rdoClearKHDatHang)))
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

        btnDatHang.setBackground(new java.awt.Color(19, 29, 243));
        btnDatHang.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnDatHang.setForeground(new java.awt.Color(255, 255, 255));
        btnDatHang.setText("Đặt hàng");
        btnDatHang.setEnabled(false);
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
        jPanel2.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Thanh toán khi nhận hàng");

        jCheckBox1.setSelected(true);
        jCheckBox1.setEnabled(false);
        jCheckBox1.setMaximumSize(new java.awt.Dimension(25, 25));
        jCheckBox1.setMinimumSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout jPanelBourder10Layout = new javax.swing.GroupLayout(jPanelBourder10);
        jPanelBourder10.setLayout(jPanelBourder10Layout);
        jPanelBourder10Layout.setHorizontalGroup(
            jPanelBourder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelBourder10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBourder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addGroup(jPanelBourder10Layout.createSequentialGroup()
                        .addGroup(jPanelBourder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBourder10Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTongTienDH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel29))
                        .addContainerGap())
                    .addGroup(jPanelBourder10Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel26)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGap(18, 18, 18)
                .addGroup(jPanelBourder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Bán");
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        int maHoaDon = _hoaDonService.genMaHoaDon();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa("HD" + maHoaDon);
        hoaDon.setNhanVien(Auth.getNhanVien());
        hoaDon.setNgayTao(new Date());
        hoaDon.setTrangThai(HoaDon.CHUATHANHTOAN);

        _hoaDonService.taoHoaDon(hoaDon);

        loadTableHoaDon();
        tblHoaDon.setRowSelectionInterval(0, 0);
        _hoaDon = _lstHoaDon.get(0);
        lblThoiGianTao.setText(_hoaDon.getNgayTao() + "");
        loadTableHoaDonCT();
        setEnableButton();

    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void addHinhThucThanhToan() {
        List<HinhThucThanhToan> lstHinhThucTT = new ArrayList<>();
        String tienMatStr = txtTienMat.getText().trim();
        String tongTienStr = lblTongTien.getText();
        BigDecimal tienMat = BigDecimal.ZERO;
        BigDecimal tongTien = BigDecimal.ZERO;
        if (!tienMatStr.isBlank()) {
            tienMat = BigDecimal.valueOf(Double.parseDouble(tienMatStr));
        }

        tongTien = BigDecimal.valueOf(Double.parseDouble(tongTienStr));

        if (tienMat.compareTo(tongTien) == 0) {
            HinhThucThanhToan hinhThucThanhToan = new HinhThucThanhToan(null, _hoaDon, HinhThucThanhToan.THANHTOANBANGTIENMAT, tienMat);
            lstHinhThucTT.add(hinhThucThanhToan);
        } else if (tienMat.compareTo(BigDecimal.ZERO) == 0) {
            HinhThucThanhToan hinhThucThanhToan = new HinhThucThanhToan(null, _hoaDon, HinhThucThanhToan.THANHTOANBANGBANKING, tongTien);
            lstHinhThucTT.add(hinhThucThanhToan);
        } else {
            HinhThucThanhToan thanhToanBangTienMat = new HinhThucThanhToan(null, _hoaDon, HinhThucThanhToan.THANHTOANBANGTIENMAT, tienMat);
            lstHinhThucTT.add(thanhToanBangTienMat);
            HinhThucThanhToan thanhToanBangBanking = new HinhThucThanhToan(null, _hoaDon, HinhThucThanhToan.THANHTOANBANGBANKING, tongTien.subtract(tienMat));
            lstHinhThucTT.add(thanhToanBangBanking);
        }

        _hinhThucThanhToanService.addHinhThucThanhToan(lstHinhThucTT);
    }

    private void btnThanhToanVaInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanVaInActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thanh toán?");
        if (confirm == JOptionPane.YES_OPTION) {
            Date ngayThanhToan = new Date();
            _hoaDon.setNgayThanhToan(ngayThanhToan);
            _hoaDon.setNgayNhan(ngayThanhToan);
            _hoaDon.setSoDiemSuDung(_diemSuDung);
            _hoaDon.setTrangThai(HoaDon.DATHANHTOAN);
            if (_khachHang != null) {
                _hoaDon.setKhachHang(_khachHang);
                _khachHangService.addDiemKhachHang(_khachHang, (_tongTien.divide(BigDecimal.valueOf(100000))).intValue());
            }
            PrintOrder print = new PrintOrder();
            boolean printSuccess = print.print();
            if (!printSuccess) {
                return;
            }
            boolean updateStatus = _hoaDonService.updateHoaDon(_hoaDon);
            addHinhThucThanhToan();
            JOptionPane.showMessageDialog(this, updateStatus ? "Thành công" : "Thất bại");
            loadTableHoaDon();
            clearTableHDCT();
            _hoaDon = null;
            setEnableButton();
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
            _hoaDon.setTrangThai(HoaDon.DATHANHTOAN);
            if (_khachHang != null) {
                _hoaDon.setKhachHang(_khachHang);
                _khachHangService.addDiemKhachHang(_khachHang, (_tongTien.divide(BigDecimal.valueOf(100000))).intValue());
            }
            boolean updateStatus = _hoaDonService.updateHoaDon(_hoaDon);
            addHinhThucThanhToan();
            JOptionPane.showMessageDialog(this, updateStatus ? "Thành công" : "Thất bại");
            loadTableHoaDon();
            clearTableHDCT();
            _hoaDon = null;
            setEnableButton();
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void clearTableHDCT() {
        DefaultTableModel dtm = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        dtm.setRowCount(0);
    }

    private void btnDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatHangActionPerformed
        if (_khachHang == null) {
            JOptionPane.showMessageDialog(this, "Chưa chọn khách hàng");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận đặt hàng?");
        if (confirm == JOptionPane.YES_OPTION) {
            Date ngayShip = new Date();
            _hoaDon.setKhachHang(_khachHang);
            _hoaDon.setNgayShip(ngayShip);
            _hoaDon.setTrangThai(HoaDon.DANGVANCHUYEN);
            PrintOrder print = new PrintOrder();
            boolean printSuccess = print.print();
            if (!printSuccess) {
                return;
            }
            boolean updateStatus = _hoaDonService.updateHoaDon(_hoaDon);
            JOptionPane.showMessageDialog(this, updateStatus ? "Thành công" : "Thất bại");
            loadTableHoaDon();
            clearTableHDCT();
            _hoaDon = null;
            setEnableButton();
        }
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
        _currentPage = _currentPage - 1;
        refreshSP();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (_currentPage == _totalPage || _currentPage > _totalPage) {
            return;
        }
        _currentPage = _currentPage + 1;
        System.out.println("Current: " + _currentPage);
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
        int confirm = JOptionPane.showConfirmDialog(this, "Xóa toàn bộ?");
        if (confirm == JOptionPane.YES_OPTION) {
            _hoaDonService.removeAllHoaDonCT(_hoaDon);
            btnDeleteAll.setBackground(new Color(204, 204, 204));
            btnDeleteAll.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Đã xóa");
        }
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
                int confirm = JOptionPane.showConfirmDialog(this, "Xóa?");
                if (confirm == JOptionPane.YES_OPTION) {
                    _hoaDonService.removeHoaDonChiTiet(hoaDonCT);
                }
            }
        }
        loadTableHoaDonCT();
        btnXoa.setEnabled(false);
        btnXoa.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScanActionPerformed
        openScanner();
    }//GEN-LAST:event_btnScanActionPerformed

    private void openScanner() {
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
                JOptionPane.showMessageDialog(this, "Không thể mở camera");
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
                    MySoundEffect.play(MySoundEffect.PATH_SCAN_SUCCESS);
                    if (!(result + "").matches("\\d+")) {
                        JOptionPane.showMessageDialog(this, "Barcode không hợp lệ");
                        return;
                    }
                    CustomSach sach = _customSachService.getSachByBarCode(result + "");
                    if (sach == null) {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy");
                        return;
                    }
                    if (sach.getSoLuong() <= 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng không đủ");
                        return;
                    }
                    closedCam(cam);
                    sach.setSoLuong(1);
                    addHoaDonCT(sach);

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
                        btnScan.setEnabled(true);
                        continuteScanner();
                        x = false;
                    }

                }
            }
        });
        thread.start();
    }

    private void closedCam(CamJFrame cam) {
        cam.webcam.close();
        cam.dispose();
    }

    private void continuteScanner() {
        int continute = JOptionPane.showConfirmDialog(this, "Bạn muốn quét nữa không?");
        if (continute == JOptionPane.YES_OPTION) {
            btnScan.doClick();
            System.out.println("continute scan");
        }
    }

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
        setPageLabel(true);
    }//GEN-LAST:event_btnAllActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int row = tblHoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }
        _hoaDon = _lstHoaDon.get(row);
        loadTableHoaDonCT();
        lblThoiGianTao.setText(_hoaDon.getNgayTao() + "");
        lblThoiGianTaoDatHang.setText(_hoaDon.getNgayTao() + "");
        setEnableButton();
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
        BigDecimal tienDaTra = BigDecimal.ZERO;
        lblTongTien.setText("<html>" + df.format(tienPhaiTra) + "<sup style='Color: Red'>vnđ</sup></html>");
        if (!txtTienMat.getText().isBlank()) {
            tienDaTra = BigDecimal.valueOf(Double.parseDouble(txtTienMat.getText() + ""));
        }
        _tienThua = tienDaTra.subtract(tienPhaiTra);
        switch (hinhThuc) {
            case 1 -> {
                txtTienMat.setEnabled(true);
                txtTienChuyenKhoan.setText("");
                txtTienChuyenKhoan.setText("");
            }
            case 0 -> {
                txtTienMat.setText("");
                txtTienMat.setEnabled(false);
                txtTienMat.setText("");
                txtTienChuyenKhoan.setText(df.format(tienPhaiTra));
                _tienThua = BigDecimal.ZERO;
            }
            default -> {
                txtTienMat.setEnabled(true);
                if (tienDaTra.compareTo(tienPhaiTra) < 0) {
                    txtTienChuyenKhoan.setText(df.format(_tienThua.multiply(BigDecimal.valueOf(-1))));
                    _tienThua = BigDecimal.ZERO;
                } else {
                    txtTienChuyenKhoan.setText("");
                }

            }
        }
        if (_tienThua.compareTo(BigDecimal.ZERO) < 0) {
            lblTienThua.setForeground(Color.red);
        } else {
            lblTienThua.setForeground(Color.GREEN);
        }
        lblTienThua.setText("<html>" + df.format(_tienThua) + "<sup>vnđ</sup></html>");

    }

    private void cbHinhThucThanhToanPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbHinhThucThanhToanPopupMenuWillBecomeInvisible
        setTienThanhToan(_tongTien);
        setEnableButton();
    }//GEN-LAST:event_cbHinhThucThanhToanPopupMenuWillBecomeInvisible
    private final JDialog _khachHangDialog = new JDialog();

    private void setKhachHangDialog() {
        _khachHangDialog.setSize(1200, 560);
        _khachHangDialog.setModal(true);
        _khachHangDialog.add(this.jpanelChonKhachHang);
        _khachHangDialog.setLocationRelativeTo(null);
    }
    private void btnSearchKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchKhachHangActionPerformed
        String sdt = txtSoDienThoai.getText().trim();
        getKhachHang(sdt);
    }//GEN-LAST:event_btnSearchKhachHangActionPerformed

    private void loadLabelKhachHang(KhachHang khachHang) {
        if (khachHang == null) {
            lblKhachHang.setText("");
            return;
        }
        rdoClearKhachHang.setSelected(true);
        rdoClearKHDatHang.setSelected(true);
        lblKhachHang.setText(khachHang.toString());
        lblKhachHangDatHang.setText(khachHang.toString());
    }

    private void loadTableKhachHang() {
        DefaultTableModel dtm = (DefaultTableModel) tblKhachHang.getModel();
        dtm.setRowCount(0);
        for (KhachHang khachHang : _lstKhachHang) {
            dtm.addRow(new Object[]{khachHang.getMa(), khachHang.getTen(), khachHang.getTenDem(), khachHang.getHo(), khachHang.getSdt(), khachHang.getDiaChi()});
        }
    }

    private void rdoClearKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoClearKhachHangMouseClicked

    }//GEN-LAST:event_rdoClearKhachHangMouseClicked

    private void clearKhachHang() {
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
    }
    private void rdoClearKhachHangStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoClearKhachHangStateChanged
        clearKhachHang();
    }//GEN-LAST:event_rdoClearKhachHangStateChanged

    private void txtSoDienThoaiDatHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiDatHangKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiDatHangKeyReleased

    private void getKhachHang(String sdt) {
        if (!sdt.matches("(\\+)?\\d+") && !sdt.isBlank()) {
            JOptionPane.showMessageDialog(this, "Không đúng định dạng");
            return;
        }
        if (sdt.isBlank()) {
            _lstKhachHang = _khachHangService.sellectAllBySDT(sdt);
            loadTableKhachHang();
            _khachHangDialog.setVisible(true);
        } else {
            _lstKhachHang = _khachHangService.sellectAllBySDT(sdt);
            if (_lstKhachHang.isEmpty()) {
                int confirm = JOptionPane.showConfirmDialog(this, "Không tìm thấy. Bạn muốn thêm khách hàng mới không?");
                if (confirm == JOptionPane.YES_OPTION) {
                    jTabbedPane2.setSelectedIndex(1);
                    _khachHangDialog.setVisible(true);
                }
            } else if (_lstKhachHang.size() == 1) {
                _khachHang = _lstKhachHang.get(0);
            } else {
                loadTableKhachHang();
                _khachHangDialog.setVisible(true);
            }
        }
        loadLabelKhachHang(_khachHang);
    }
    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String sdt = txtSoDienThoaiDatHang.getText().trim();
        getKhachHang(sdt);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void rdoClearKHDatHangStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdoClearKHDatHangStateChanged
        clearKhachHang();
    }//GEN-LAST:event_rdoClearKHDatHangStateChanged

    private void rdoClearKHDatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoClearKHDatHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoClearKHDatHangMouseClicked

    private void setEnableButton() {

        Color enabledFalse = new Color(204, 204, 204);
        Color enabledTrue = new Color(35, 35, 132);

        if (_hoaDon == null) {
            btnThanhToan.setEnabled(false);
            btnDatHang.setEnabled(false);
            btnThanhToanVaIn.setEnabled(false);
            btnDatHang.setBackground(enabledFalse);
            btnThanhToan.setBackground(enabledFalse);
            btnThanhToanVaIn.setBackground(enabledFalse);
            btnScan.setEnabled(false);
            btnScan.setBackground(enabledFalse);
            txtTienMat.setEnabled(false);
            return;
        } else {
            btnDeleteAll.setBackground(enabledTrue);
            btnDatHang.setEnabled(true);
            btnDatHang.setBackground(enabledTrue);
            btnXoa.setEnabled(true);
            btnXoa.setBackground(enabledTrue);
            btnScan.setEnabled(true);
            btnScan.setBackground(enabledTrue);
        }

        if (_tienThua.compareTo(BigDecimal.ZERO) < 0) {
            btnThanhToan.setEnabled(false);
            btnThanhToanVaIn.setEnabled(false);
            btnThanhToan.setBackground(enabledFalse);
            btnThanhToanVaIn.setBackground(enabledFalse);
        } else {
            btnThanhToan.setEnabled(true);
            btnThanhToanVaIn.setEnabled(true);
            btnScan.setEnabled(true);
            btnThanhToan.setBackground(enabledTrue);
            btnThanhToanVaIn.setBackground(enabledTrue);
            btnScan.setBackground(enabledTrue);
        }

        if (_lstHoaDonChiTiet.isEmpty()) {
            btnDeleteAll.setEnabled(false);
            btnDeleteAll.setBackground(enabledFalse);
            btnXoa.setEnabled(false);
            btnXoa.setBackground(enabledFalse);
        }
    }

    private void lblTienThuaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lblTienThuaPropertyChange
        setEnableButton();
    }//GEN-LAST:event_lblTienThuaPropertyChange

    private void txtTienMatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTienMatFocusLost
        String tienMat = txtTienMat.getText().trim();
        if (tienMat.isBlank()) {
            return;
        }
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

    private void btnNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgaySinhActionPerformed
        ngaysinh.showPopup();
    }//GEN-LAST:event_btnNgaySinhActionPerformed

    private KhachHang getForm() {
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        String tenDem = txtTenDem.getText().trim();
        String ho = txtHo.getText().trim();
        String ngaySinhStr = txtNgaySinh.getText().trim();
        String SDT = txtSDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String gioiTinh = rdoNam.isSelected() ? "Nam" : "Nữ";

        Date ngaySinh = null;
        if (!ngaySinhStr.isBlank()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            try {
                ngaySinh = format.parse(ngaySinhStr);
            } catch (ParseException ex) {
                Logger.getLogger(KhachHang_Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return new KhachHang(null, ma, ten, tenDem, ho, gioiTinh, ngaySinh, SDT, 0, diaChi);
    }

    private void btnAddKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKhachHangActionPerformed
        KhachHang khachHang = getForm();
        if (khachHang.getId() != null) {
            JOptionPane.showMessageDialog(this, "Clear form trước khi thêm");
            return;
        }

        _khachHangService.insertKhachHang(khachHang);
        JOptionPane.showMessageDialog(this, "insert successfully");

    }//GEN-LAST:event_btnAddKhachHangActionPerformed

    private void clear() {
        txtDiaChi.setText("");
        txtHo.setText("");
        txtMa.setText("");
        txtNgaySinh.setText("");
        txtSDT.setText("");
        txtTen.setText("");
        txtTenDem.setText("");
        rdoNam.setSelected(true);
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtTienMatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienMatKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            lblKhachHang.requestFocus();
        }
    }//GEN-LAST:event_txtTienMatKeyReleased

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
        btnXoa.setEnabled(true);
        btnXoa.setBackground(new Color(35, 35, 132));
    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked

    private void tblHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelShowBook;
    private View.ButtonDesign.Button btnAddKhachHang;
    private View.ButtonDesign.Button btnAll;
    private View.ButtonDesign.Button btnClear;
    private View.ButtonDesign.Button btnDatHang;
    private View.ButtonDesign.Button btnDeleteAll;
    private View.ButtonDesign.Button btnDungDiem;
    private View.ButtonDesign.Button btnFist;
    private View.ButtonDesign.Button btnLast;
    private View.ButtonDesign.Button btnNext;
    private View.ButtonDesign.Button btnNgaySinh;
    private View.ButtonDesign.Button btnPrev;
    private View.ButtonDesign.Button btnScan;
    private View.ButtonDesign.Button btnSearch;
    private View.ButtonDesign.Button btnSearchKhachHang;
    private View.ButtonDesign.Button btnTaoHoaDon;
    private View.ButtonDesign.Button btnThanhToan;
    private View.ButtonDesign.Button btnThanhToanVaIn;
    private View.ButtonDesign.Button btnTimKiem;
    private View.ButtonDesign.Button btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private View.DesignComponent.Combobox cbHinhThucThanhToan;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private View.DesignComponent.JPanelBourder jPanelBourder10;
    private View.DesignComponent.JPanelBourder jPanelBourder13;
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
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
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
    private javax.swing.JLabel lblTuKhoa;
    private View.DateChooser.DateChooser ngaysinh;
    private javax.swing.JRadioButton rdoClearKHDatHang;
    private javax.swing.JRadioButton rdoClearKhachHang;
    private View.ComboBoxDesign.RadioButtonCustom rdoNam;
    private View.ComboBoxDesign.RadioButtonCustom rdoNu;
    private View.DesignComponent.Table tblHoaDon;
    private View.DesignComponent.Table tblHoaDonChiTiet;
    private View.DesignComponent.Table tblKhachHang;
    private View.DesignComponent.TextField txtDiaChi;
    private javax.swing.JTextField txtDiemSuDung;
    private View.DesignComponent.TextField txtHo;
    private View.DesignComponent.TextField txtMa;
    private View.DesignComponent.TextField txtNgaySinh;
    private View.DesignComponent.TextField txtSDT;
    private View.DesignComponent.TextField txtSoDienThoai;
    private View.DesignComponent.TextField txtSoDienThoaiDatHang;
    private View.DesignComponent.TextField txtTen;
    private View.DesignComponent.TextField txtTenDem;
    private View.DesignComponent.TextField txtTienChuyenKhoan;
    private View.DesignComponent.TextField txtTienMat;
    private View.DesignComponent.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    class PrintOrder {

        boolean print() {
            try {
                File directory = new File("hoaDon");
                String path = "hoaDon//" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSSZ").format(_hoaDon.getNgayThanhToan()) + ".pdf";
                File file = new File(path);
                if (!file.exists()) {
                    directory.mkdirs();
                    file.createNewFile();
                    System.out.println(file.getAbsolutePath());
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

                PdfPTable table = new PdfPTable(new float[]{4, 2, 2, 2});

                PdfPCell c1 = new PdfPCell(new Phrase("Ten San Pham"));
                c1.setBorder(Rectangle.BOTTOM);
                c1.setVerticalAlignment(Element.ALIGN_CENTER);
                c1.setBorderColor(BaseColor.GRAY);
                c1.setBorderWidth(1);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase("So luong"));
                c1.setBorder(Rectangle.BOTTOM);
                c1.setVerticalAlignment(Element.ALIGN_CENTER);
                c1.setBorderColor(BaseColor.GRAY);
                c1.setBorderWidth(1);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase("Don gia"));
                c1.setBorder(Rectangle.BOTTOM);
                c1.setVerticalAlignment(Element.ALIGN_CENTER);
                c1.setBorderColor(BaseColor.GRAY);
                c1.setBorderWidth(1);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase("Tong"));
                c1.setBorder(Rectangle.BOTTOM);
                c1.setVerticalAlignment(Element.ALIGN_CENTER);
                c1.setBorderColor(BaseColor.GRAY);
                c1.setBorderWidth(1);
                table.addCell(c1);

                table.setHeaderRows(1);

                for (HoaDonChiTiet hoaDonCT : _lstHoaDonChiTiet.values()) {
                    c1 = new PdfPCell(new Phrase(separator.removeAccent(hoaDonCT.getSach().getTen())));
                    c1.setBorder(Rectangle.NO_BORDER);
                    table.addCell(c1);

                    c1 = new PdfPCell(new Phrase(hoaDonCT.getSoLuong() + ""));
                    c1.setBorder(Rectangle.NO_BORDER);
                    table.addCell(c1);

                    c1 = new PdfPCell(new Phrase(df.format(hoaDonCT.getDonGia()) + " VND"));
                    c1.setBorder(Rectangle.NO_BORDER);
                    table.addCell(c1);

                    c1 = new PdfPCell(new Phrase(df.format(hoaDonCT.getDonGia().multiply(BigDecimal.valueOf(hoaDonCT.getSoLuong()))) + " VND"));
                    c1.setBorder(Rectangle.NO_BORDER);
                    table.addCell(c1);

                }

                Paragraph tongTien = new Paragraph("Tong tien: " + df.format(_tongTien) + " VND");
                tongTien.setAlignment(Element.ALIGN_LEFT);

                Paragraph diemDung = new Paragraph("Diem dung: " + _diemSuDung);
                diemDung.setAlignment(Element.ALIGN_LEFT);

                Paragraph phaiTra = new Paragraph("Phai tra : " + df.format(_tongTien.subtract(BigDecimal.valueOf(_diemSuDung).multiply(BigDecimal.valueOf(1000)))) + " VNĐ");
                phaiTra.setAlignment(Element.ALIGN_LEFT);

                Paragraph khachDua = new Paragraph("Khach tra: " + df.format(_tongTien.subtract(BigDecimal.valueOf(_diemSuDung).multiply(BigDecimal.valueOf(1000))).add(_tienThua)) + " VND");
                khachDua.setAlignment(Element.ALIGN_LEFT);

                Paragraph tienThua = new Paragraph("Tien thua: " + df.format(_tienThua) + " VND");
                tienThua.setAlignment(Element.ALIGN_LEFT);

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
