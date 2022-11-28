/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Form_Management;

import View.DesignComponent.Item_Pos;
import View.DesignComponent.ModelProfile;
import custommodel.CustomSach;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.status.TrangThaiHoaDon;
import service.CustomSachService;
import service.HoaDonService;
import service.impl.CustomSachServiceImpl;
import service.impl.HoaDonServiceImpl;
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

//    List<SachFake> listS = new ArrayList<>();
    public Pos_MayBanHang() {
        initComponents();
        setComponent();
        _hoaDonService = new HoaDonServiceImpl();
        _customSachService = new CustomSachServiceImpl();

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
            item.setGia(customSach.getGiaBan());
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
        int row = tblHoaDon.getSelectedRow();
        if (row == -1) {
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
        int row = tblHoaDon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn");
            return;
        }

        HoaDonChiTiet hoaDonCT = new HoaDonChiTiet();
        hoaDonCT.setSach(customSach.getSach());
        hoaDonCT.setDonGia(customSach.getGiaSale());
        hoaDonCT.setSoLuong(customSach.getSoLuong());
        hoaDonCT.setHoaDon(_lstHoaDon.get(row));
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
        loadTableHoaDonCT(hoaDonCT.getHoaDon().getMa());
        if (searcher) {
            loadSachSearch(_lstCustomSach, _currentPage, _pageSize);
        } else {
            loadSach(_currentPage, _pageSize);
        }

    }

    private void loadTableHoaDonCT(String maHD) {
        List<HoaDonChiTiet> lst = _hoaDonService.getAllByMaHD(maHD);
        _lstHoaDonChiTiet = new HashMap<>();
        for (HoaDonChiTiet hoaDonChiTiet : lst) {
            _lstHoaDonChiTiet.put(hoaDonChiTiet.getSach().getId(), hoaDonChiTiet);
        }

        DefaultTableModel dtm = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        dtm.setRowCount(0);
        for (HoaDonChiTiet hoaDonChiTiet : _lstHoaDonChiTiet.values()) {
            dtm.addRow(new Object[]{hoaDonChiTiet.getSach().getMa(), hoaDonChiTiet.getSach().getTen(), hoaDonChiTiet.getSoLuong(), df.format(hoaDonChiTiet.getDonGia()),
                hoaDonChiTiet.getSach().getHinh() == null ? "" : new ModelProfile(new ImageIcon(new ImageIcon(hoaDonChiTiet.getSach().getHinh()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)))});
        }
    }
    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTimKiem1 = new View.DesignComponent.TextField();
        comboboxTrangThai2 = new View.DesignComponent.Combobox();
        jPanelBourder8 = new View.DesignComponent.JPanelBourder();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        comboboxTrangThai = new View.DesignComponent.Combobox();
        txtSearch = new View.DesignComponent.TextField();
        txtSearch1 = new View.DesignComponent.TextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnInBaoCao3 = new View.ButtonDesign.Button();
        btnInBaoCao5 = new View.ButtonDesign.Button();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanelBourder9 = new View.DesignComponent.JPanelBourder();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnInBaoCao6 = new View.ButtonDesign.Button();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jPanelBourder10 = new View.DesignComponent.JPanelBourder();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        comboboxTrangThai1 = new View.DesignComponent.Combobox();
        btnInBaoCao8 = new View.ButtonDesign.Button();
        jLabel29 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();

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
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setResizable(false);
        }

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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Thời gian tạo :");

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("26/11/2022");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Điểm sử dụng :");

        jTextField1.setBackground(new java.awt.Color(16, 31, 86));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setBackground(new java.awt.Color(102, 102, 102));
        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("Thông tin cơ bản");

        txtTimKiem1.setBackground(new java.awt.Color(16, 31, 86));
        txtTimKiem1.setForeground(new java.awt.Color(255, 255, 255));
        txtTimKiem1.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTimKiem1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTimKiem1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTimKiem1.setLabelText("Tìm Kiếm Theo SĐT");
        txtTimKiem1.setLineColor(new java.awt.Color(255, 255, 255));
        txtTimKiem1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiem1KeyReleased(evt);
            }
        });

        comboboxTrangThai2.setBackground(new java.awt.Color(16, 31, 86));
        comboboxTrangThai2.setForeground(new java.awt.Color(255, 255, 255));
        comboboxTrangThai2.setMaximumRowCount(10);
        comboboxTrangThai2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Số lượng", "A-Z" }));
        comboboxTrangThai2.setSelectedIndex(-1);
        comboboxTrangThai2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        comboboxTrangThai2.setLabeText("Khách Hàng");
        comboboxTrangThai2.setLineColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelBourder7Layout = new javax.swing.GroupLayout(jPanelBourder7);
        jPanelBourder7.setLayout(jPanelBourder7Layout);
        jPanelBourder7Layout.setHorizontalGroup(
            jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder7Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelBourder7Layout.createSequentialGroup()
                        .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBourder7Layout.createSequentialGroup()
                                .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearchKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(comboboxTrangThai2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(jPanelBourder7Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanelBourder7Layout.setVerticalGroup(
            jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder7Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(comboboxTrangThai2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelBourder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jPanelBourder8.setBackground(new java.awt.Color(16, 31, 86));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tổng Tiền :");

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 102, 102));
        jLabel11.setText("100,000 VND");

        comboboxTrangThai.setBackground(new java.awt.Color(16, 31, 86));
        comboboxTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        comboboxTrangThai.setMaximumRowCount(10);
        comboboxTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tiền Mặt", "Chuyển Khoản", "Kết Hợp" }));
        comboboxTrangThai.setSelectedIndex(2);
        comboboxTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        comboboxTrangThai.setLabeText("Hình Thức Thanh Toán");
        comboboxTrangThai.setLineColor(new java.awt.Color(255, 255, 255));
        comboboxTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxTrangThaiActionPerformed(evt);
            }
        });

        txtSearch.setBackground(new java.awt.Color(16, 31, 86));
        txtSearch.setForeground(new java.awt.Color(255, 255, 255));
        txtSearch.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSearch.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSearch.setLabelText("Tiền Mặt ( Khách Trả )");
        txtSearch.setLineColor(new java.awt.Color(255, 255, 255));
        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });

        txtSearch1.setBackground(new java.awt.Color(16, 31, 86));
        txtSearch1.setForeground(new java.awt.Color(255, 255, 255));
        txtSearch1.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSearch1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSearch1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSearch1.setLabelText("Tiền Chuyển Khoản");
        txtSearch1.setLineColor(new java.awt.Color(255, 255, 255));
        txtSearch1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearch1CaretUpdate(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tiền Thừa :");

        jLabel16.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 204, 0));
        jLabel16.setText("20,000 VND");

        btnInBaoCao3.setBackground(new java.awt.Color(35, 35, 132));
        btnInBaoCao3.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnInBaoCao3.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao3.setText("Thanh Toán và In");
        btnInBaoCao3.setFocusable(false);
        btnInBaoCao3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCao3ActionPerformed(evt);
            }
        });

        btnInBaoCao5.setBackground(new java.awt.Color(35, 35, 132));
        btnInBaoCao5.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnInBaoCao5.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao5.setText("Thanh Toán");
        btnInBaoCao5.setFocusable(false);
        btnInBaoCao5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCao5ActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(102, 102, 102));
        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Quầy Thanh Toán");

        javax.swing.GroupLayout jPanelBourder8Layout = new javax.swing.GroupLayout(jPanelBourder8);
        jPanelBourder8.setLayout(jPanelBourder8Layout);
        jPanelBourder8Layout.setHorizontalGroup(
            jPanelBourder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBourder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder8Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                    .addGroup(jPanelBourder8Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelBourder8Layout.createSequentialGroup()
                        .addGroup(jPanelBourder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboboxTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearch1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder8Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnInBaoCao3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnInBaoCao5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelBourder8Layout.setVerticalGroup(
            jPanelBourder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder8Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanelBourder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboboxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBourder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(jPanelBourder8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInBaoCao3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInBaoCao5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Nhân Viên :");

        jLabel18.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Nguyễn Văn A");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Khách Hàng :");

        jLabel20.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Nguyễn Văn B");

        btnInBaoCao6.setBackground(new java.awt.Color(19, 29, 243));
        btnInBaoCao6.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnInBaoCao6.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao6.setText("+");
        btnInBaoCao6.setFocusable(false);
        btnInBaoCao6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao6.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnInBaoCao6.setRound(2);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Thời gian tạo :");

        jLabel22.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("26/11/2022");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Điểm sử dụng :");

        jTextField2.setBackground(new java.awt.Color(16, 31, 86));
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel24.setBackground(new java.awt.Color(102, 102, 102));
        jLabel24.setFont(new java.awt.Font("Segoe UI Semibold", 1, 13)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 204, 204));
        jLabel24.setText("Thông tin cơ bản");

        javax.swing.GroupLayout jPanelBourder9Layout = new javax.swing.GroupLayout(jPanelBourder9);
        jPanelBourder9.setLayout(jPanelBourder9Layout);
        jPanelBourder9Layout.setHorizontalGroup(
            jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder9Layout.createSequentialGroup()
                        .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel17))
                        .addGap(24, 24, 24)
                        .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBourder9Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnInBaoCao6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelBourder9Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelBourder9Layout.createSequentialGroup()
                        .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBourder9Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel24))
                        .addGap(0, 11, Short.MAX_VALUE))))
        );
        jPanelBourder9Layout.setVerticalGroup(
            jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder9Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInBaoCao6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanelBourder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );

        jPanelBourder10.setBackground(new java.awt.Color(16, 31, 86));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Tổng Tiền :");

        jLabel26.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 102, 102));
        jLabel26.setText("100,000 VND");

        comboboxTrangThai1.setBackground(new java.awt.Color(16, 31, 86));
        comboboxTrangThai1.setForeground(new java.awt.Color(255, 255, 255));
        comboboxTrangThai1.setMaximumRowCount(10);
        comboboxTrangThai1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tiền Mặt", "Chuyển Khoản", "Kết Hợp" }));
        comboboxTrangThai1.setSelectedIndex(2);
        comboboxTrangThai1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        comboboxTrangThai1.setLabeText("Hình Thức Thanh Toán");
        comboboxTrangThai1.setLineColor(new java.awt.Color(255, 255, 255));
        comboboxTrangThai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxTrangThai1ActionPerformed(evt);
            }
        });

        btnInBaoCao8.setBackground(new java.awt.Color(19, 29, 243));
        btnInBaoCao8.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnInBaoCao8.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao8.setText("Thanh Toán");
        btnInBaoCao8.setFocusable(false);
        btnInBaoCao8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCao8ActionPerformed(evt);
            }
        });

        jLabel29.setBackground(new java.awt.Color(102, 102, 102));
        jLabel29.setFont(new java.awt.Font("Segoe UI Semibold", 1, 13)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(204, 204, 204));
        jLabel29.setText("Quầy Thanh Toán");

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
                    .addGroup(jPanelBourder10Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                    .addGroup(jPanelBourder10Layout.createSequentialGroup()
                        .addComponent(comboboxTrangThai1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanelBourder10Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnInBaoCao8, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelBourder10Layout.setVerticalGroup(
            jPanelBourder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder10Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addGroup(jPanelBourder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboboxTrangThai1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInBaoCao8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jPanelBourder9, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnInBaoCao3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao3ActionPerformed

    private void comboboxTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboboxTrangThaiActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate

    }//GEN-LAST:event_txtSearchCaretUpdate

    private void txtSearch1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearch1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch1CaretUpdate

    private void btnInBaoCao5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao5ActionPerformed

    private void comboboxTrangThai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxTrangThai1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboboxTrangThai1ActionPerformed

    private void btnInBaoCao8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCao8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInBaoCao8ActionPerformed

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

    private void txtTimKiem1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiem1KeyReleased

    }//GEN-LAST:event_txtTimKiem1KeyReleased

    private void btnDeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAllActionPerformed
        int row = tblHoaDon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn");
            return;
        }
        if (_lstHoaDonChiTiet.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có gì để xóa");
            return;
        }
        _hoaDonService.removeAllHoaDonCT(_lstHoaDon.get(row));
        refreshSP();
    }//GEN-LAST:event_btnDeleteAllActionPerformed

    private void refreshSP() {
        setPageLabel(searcher);
        if (searcher) {
            loadSachSearch(_lstCustomSach, _currentPage, _totalPage);
        } else {
            loadSach(_currentPage, _totalPage);
        }
    }
    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tblHoaDonChiTiet.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm");
            return;
        }

        int row2 = tblHoaDon.getSelectedRow();
        if (row2 == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn");
            return;
        }
        for (HoaDonChiTiet hoaDonCT : _lstHoaDonChiTiet.values()) {
            if (hoaDonCT.getSach().getMa().equalsIgnoreCase(tblHoaDonChiTiet.getValueAt(row, 0) + "")) {
                JOptionPane.showConfirmDialog(this, "Xóa?");
                _hoaDonService.removeHoaDonChiTiet(hoaDonCT);
                loadTableHoaDonCT(_lstHoaDon.get(row).getMa());
                refreshSP();
            }
        }
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
        loadTableHoaDonCT(_lstHoaDon.get(row).getMa());
    }//GEN-LAST:event_tblHoaDonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelShowBook;
    private View.ButtonDesign.Button btnAll;
    private View.ButtonDesign.Button btnDeleteAll;
    private View.ButtonDesign.Button btnFist;
    private View.ButtonDesign.Button btnInBaoCao3;
    private View.ButtonDesign.Button btnInBaoCao5;
    private View.ButtonDesign.Button btnInBaoCao6;
    private View.ButtonDesign.Button btnInBaoCao8;
    private View.ButtonDesign.Button btnInBaoCao9;
    private View.ButtonDesign.Button btnLast;
    private View.ButtonDesign.Button btnNext;
    private View.ButtonDesign.Button btnPrev;
    private View.ButtonDesign.Button btnSearch;
    private View.ButtonDesign.Button btnSearchKhachHang;
    private View.ButtonDesign.Button btnTaoHoaDon;
    private View.ButtonDesign.Button btnXoa;
    private View.DesignComponent.Combobox comboboxTrangThai;
    private View.DesignComponent.Combobox comboboxTrangThai1;
    private View.DesignComponent.Combobox comboboxTrangThai2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private View.DesignComponent.JPanelBourder jPanelBourder10;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblPage;
    private View.DesignComponent.Table tblHoaDon;
    private View.DesignComponent.Table tblHoaDonChiTiet;
    private View.DesignComponent.TextField txtSearch;
    private View.DesignComponent.TextField txtSearch1;
    private View.DesignComponent.TextField txtTimKiem;
    private View.DesignComponent.TextField txtTimKiem1;
    // End of variables declaration//GEN-END:variables
}
