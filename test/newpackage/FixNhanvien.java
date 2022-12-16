/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

/**
 *
 * @author ECO
 */
public class FixNhanvien {
     private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    @Override
    public boolean addNhanVien(NhanVien nhanVien) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(nhanVien);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;

        } finally {
            session.close();
        }
    }

    @Override
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> listNhanVien;
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<NhanVien> cq = criteriaBuilder.createQuery(NhanVien.class);
            Root<NhanVien> root = cq.from(NhanVien.class);
            CriteriaQuery<NhanVien> all = cq.select(root);

            TypedQuery<NhanVien> allQuery = session.createQuery(all);
            listNhanVien = allQuery.getResultList();
        }

        return listNhanVien;
    }

    @Override
    public List<NhanVien> getListNhanVienByName(String name) {
        List<NhanVien> listNhanVien = null;
        String nameSelect = "%" + name + "%";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<NhanVien> query = session.createQuery("From NhanVien WHERE Ten like :key");
            query.setParameter("key", nameSelect);
            listNhanVien = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanVien;

    }

    @Override
    public NhanVien getNhanVienById(String Id) {
        NhanVien nhanVien = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            nhanVien = session.get(NhanVien.class, Id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanVien != null ? nhanVien : null;
    }

    @Override
    public boolean updateNhanVien(NhanVien nhanVien) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(nhanVien);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;

        } finally {
            session.close();
        }
    }

    @Override
    public NhanVien getNhanVien(String ma, String password) {
        NhanVien nhanVien = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<NhanVien> query = session.createQuery("from NhanVien n where n.ma = :ma and n.matKhau = :matKhau");
            query.setParameter("ma", ma);
            query.setParameter("matKhau", password);

            nhanVien = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanVien;
    }

    @Override
    public NhanVien getNhanVienByEmail(String Email) {
        NhanVien nhanVien = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<NhanVien> query = session.createQuery("from NhanVien n where n.email = :email");
            query.setParameter("email", Email);

            nhanVien = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanVien;
    }
    
    
    private final String DEFAULT_IMAGE = "image/dacnhantam.jpg";

    private final ViTriService _viTriService;
    private final NhaXuatBanService _nhaXuatBanService;

    private String currentDirectory;
    private byte[] _hinh = null;
    private final SachService _sachService;

    private final TacGiaService _tacGiaService;
    private List<TacGia> _lstAllTacGia;
    private HashMap<String, TacGia> _lstTacGia;

    private final TheLoaiServicer _theLoaiService;
    private List<TheLoai> _lstAllTheLoai;
    private HashMap<String, TheLoai> _lstTheLoai;

    public Sach_ChucNang_Form() {
        initComponents();
        this.Form_Chon_TacGia.show(false);
        this.background.show(false);
//            this.jScrollPane1.show(false);
        ScrollBarCustom scr = new ScrollBarCustom();
        this.scroll.setVerticalScrollBar(scr);
        this.cbSelect.removeAllItems();

        Icon icon = new ImageIcon(new ImageIcon(DEFAULT_IMAGE).getImage().getScaledInstance(260, 320, Image.SCALE_DEFAULT));
        this.lblAvartar.setIcon(icon);

        Icon iconbgr = new ImageIcon("image/demobgr.png");
        this.background.setIcon(iconbgr);
        this.background.setBackground(new Color(0, 0, 0, 0));

        _sachService = new SachServiceImpl();
        _nhaXuatBanService = new NhaXuatBanServicelmpl();
        _viTriService = new ViTriServiceImpl();

        loadCbo();
        _tacGiaService = new TacGiaServiceImpl();
        _theLoaiService = new TheLoaiServicer();

    }

    public void loadCbo() {
        cboNhaXuatBan.setModel(new DefaultComboBoxModel(_nhaXuatBanService.selectAll().toArray()));
        cboViTri.setModel(new DefaultComboBoxModel(_viTriService.getAllViTri().toArray()));
    }

    public void setFormByMa(String ma) {
        Sach sach = _sachService.getSachByMa(ma);
        setForm(sach);
    }

    private final DecimalFormat df = new DecimalFormat("###");

    private void setForm(Sach sach) {
        _hinh = sach.getHinh();
        setAvartar();
        txtId.setText(sach.getId());
        txtBarCode.setText(sach.getBarCode());
        txtGiaBan.setText(df.format(sach.getGiaBan()) + "");
        txtGiaNhap.setText(df.format(sach.getGiaNhap()) + "");
        txtMa.setText(sach.getMa());
        txtMoTa.setText(sach.getMoTa());
        txtSoLuong.setText(sach.getSoLuong() + "");
        txtSoTrang.setText(sach.getSoTrang() + "");
        txtTen.setText(sach.getTen());

        cboNhaXuatBan.setSelectedItem(sach.getNhaXuatBan());
        cboViTri.setSelectedItem(sach.getViTri());

        if (!sach.getLstSachTacGia().isEmpty()) {
            _lstTacGia = new HashMap<>();
            for (SachTacGia sachTacGia : sach.getLstSachTacGia()) {
                _lstTacGia.put(sachTacGia.getTacGia().getMa(), sachTacGia.getTacGia());
            }
            loadTacGia();
        }

        if (!sach.getLstTheLoaiCT().isEmpty()) {
            _lstTheLoai = new HashMap<>();
            for (TheLoaiChiTiet theLoaiCT : sach.getLstTheLoaiCT()) {
                _lstTheLoai.put(theLoaiCT.getTheLoai().getMa(), theLoaiCT.getTheLoai());
            }
            loadTheLoai();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jSeparator4 = new javax.swing.JSeparator();
        j = new javax.swing.JDialog();
        buttonGroup1 = new javax.swing.ButtonGroup();
        TruongThongTin = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        btnSelectTheLoai = new View.ButtonDesign.Button();
        txtId = new View.DesignComponent.TextField();
        txtMa = new View.DesignComponent.TextField();
        txtTen = new View.DesignComponent.TextField();
        txtSoLuong = new View.DesignComponent.TextField();
        txtSoTrang = new View.DesignComponent.TextField();
        txtMoTa = new View.DesignComponent.TextField();
        txtGiaBan = new View.DesignComponent.TextField();
        txtGiaNhap = new View.DesignComponent.TextField();
        jLabel1 = new javax.swing.JLabel();
        txtBarCode = new View.DesignComponent.TextField();
        btnCamBarCode = new View.ButtonDesign.Button();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnSelectTacGia = new View.ButtonDesign.Button();
        btnAdd = new View.ButtonDesign.Button();
        btnUpdate = new View.ButtonDesign.Button();
        btnClear = new View.ButtonDesign.Button();
        txtTacGia = new View.DesignComponent.TextField();
        txtTheLoai = new View.DesignComponent.TextField();
        rdoDangKinhDoanh = new View.ComboBoxDesign.RadioButtonCustom();
        rdoNgungKinhDoanh = new View.ComboBoxDesign.RadioButtonCustom();
        cboNhaXuatBan = new View.DesignComponent.Combobox();
        cboViTri = new View.DesignComponent.Combobox();
        btnClear1 = new View.ButtonDesign.Button();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        lblAvartar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnChooseImage = new View.ButtonDesign.Button();
        btnCameraImage = new View.ButtonDesign.Button();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Form_Chon_TacGia = new View.DesignComponent.JPanelBourder();
        cbSelect = new View.ComboBoxDesign.ComboBoxSuggestion();
        jPanel3 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        btnInBaoCao = new View.ButtonDesign.Button();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbNameForm = new javax.swing.JLabel();
        background = new View.ButtonDesign.Background();

        j.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        j.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        j.setResizable(false);

        javax.swing.GroupLayout jLayout = new javax.swing.GroupLayout(j.getContentPane());
        j.getContentPane().setLayout(jLayout);
        jLayout.setHorizontalGroup(
            jLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jLayout.setVerticalGroup(
            jLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(29, 32, 57));
        setPreferredSize(new java.awt.Dimension(1373, 850));
        setLayout(null);

        TruongThongTin.setBackground(new java.awt.Color(11, 20, 55));
        TruongThongTin.setLayout(null);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        TruongThongTin.add(jSeparator2);
        jSeparator2.setBounds(0, 120, 1370, 3);

        jPanelBourder1.setBackground(new java.awt.Color(47, 55, 90));

        btnSelectTheLoai.setBackground(new java.awt.Color(55, 69, 128));
        btnSelectTheLoai.setBorder(null);
        btnSelectTheLoai.setForeground(new java.awt.Color(255, 255, 255));
        btnSelectTheLoai.setText("...");
        btnSelectTheLoai.setFocusable(false);
        btnSelectTheLoai.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnSelectTheLoai.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSelectTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectTheLoaiActionPerformed(evt);
            }
        });

        txtId.setBackground(new java.awt.Color(47, 55, 90));
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setCaretColor(new java.awt.Color(255, 255, 255));
        txtId.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtId.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtId.setLabelText("ID");
        txtId.setLineColor(new java.awt.Color(255, 255, 255));

        txtMa.setBackground(new java.awt.Color(47, 55, 90));
        txtMa.setForeground(new java.awt.Color(255, 255, 255));
        txtMa.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMa.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMa.setLabelText("Mã Sách");
        txtMa.setLineColor(new java.awt.Color(255, 255, 255));

        txtTen.setBackground(new java.awt.Color(47, 55, 90));
        txtTen.setForeground(new java.awt.Color(255, 255, 255));
        txtTen.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTen.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTen.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTen.setLabelText("Tên Sách");
        txtTen.setLineColor(new java.awt.Color(255, 255, 255));

        txtSoLuong.setBackground(new java.awt.Color(47, 55, 90));
        txtSoLuong.setForeground(new java.awt.Color(255, 255, 255));
        txtSoLuong.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSoLuong.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSoLuong.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtSoLuong.setLabelText("Số lượng");
        txtSoLuong.setLineColor(new java.awt.Color(255, 255, 255));
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        txtSoTrang.setBackground(new java.awt.Color(47, 55, 90));
        txtSoTrang.setForeground(new java.awt.Color(255, 255, 255));
        txtSoTrang.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSoTrang.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSoTrang.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtSoTrang.setLabelText("Số Trang");
        txtSoTrang.setLineColor(new java.awt.Color(255, 255, 255));
        txtSoTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoTrangActionPerformed(evt);
            }
        });

        txtMoTa.setBackground(new java.awt.Color(47, 55, 90));
        txtMoTa.setForeground(new java.awt.Color(255, 255, 255));
        txtMoTa.setCaretColor(new java.awt.Color(255, 255, 255));
        txtMoTa.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtMoTa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtMoTa.setLabelText("Mô Tả");
        txtMoTa.setLineColor(new java.awt.Color(255, 255, 255));
        txtMoTa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTaActionPerformed(evt);
            }
        });

        txtGiaBan.setBackground(new java.awt.Color(47, 55, 90));
        txtGiaBan.setForeground(new java.awt.Color(255, 255, 255));
        txtGiaBan.setCaretColor(new java.awt.Color(255, 255, 255));
        txtGiaBan.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtGiaBan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtGiaBan.setLabelText("Giá Bán");
        txtGiaBan.setLineColor(new java.awt.Color(255, 255, 255));
        txtGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaBanActionPerformed(evt);
            }
        });

        txtGiaNhap.setBackground(new java.awt.Color(47, 55, 90));
        txtGiaNhap.setForeground(new java.awt.Color(255, 255, 255));
        txtGiaNhap.setCaretColor(new java.awt.Color(255, 255, 255));
        txtGiaNhap.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtGiaNhap.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtGiaNhap.setLabelText("Giá Nhập");
        txtGiaNhap.setLineColor(new java.awt.Color(255, 255, 255));
        txtGiaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaNhapActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Trạng Thái");

        txtBarCode.setBackground(new java.awt.Color(47, 55, 90));
        txtBarCode.setForeground(new java.awt.Color(255, 255, 255));
        txtBarCode.setCaretColor(new java.awt.Color(255, 255, 255));
        txtBarCode.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtBarCode.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtBarCode.setLabelText("Barcode");
        txtBarCode.setLineColor(new java.awt.Color(255, 255, 255));
        txtBarCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarCodeActionPerformed(evt);
            }
        });

        btnCamBarCode.setBackground(new java.awt.Color(47, 55, 90));
        btnCamBarCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image_Hub/icons8_camera_30px.png"))); // NOI18N
        btnCamBarCode.setToolTipText("Quét mã barcode");
        btnCamBarCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCamBarCodeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Basic information");

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(824, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        btnSelectTacGia.setBackground(new java.awt.Color(55, 69, 128));
        btnSelectTacGia.setBorder(null);
        btnSelectTacGia.setForeground(new java.awt.Color(255, 255, 255));
        btnSelectTacGia.setText("...");
        btnSelectTacGia.setFocusable(false);
        btnSelectTacGia.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnSelectTacGia.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSelectTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectTacGiaActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(31, 31, 111));
        btnAdd.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm sách");
        btnAdd.setToolTipText("Thêm mới sách");
        btnAdd.setFocusable(false);
        btnAdd.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(31, 31, 111));
        btnUpdate.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Cập Nhật");
        btnUpdate.setToolTipText("Cập nhật sách");
        btnUpdate.setFocusable(false);
        btnUpdate.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(31, 31, 111));
        btnClear.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Làm Mới");
        btnClear.setToolTipText("Clear form");
        btnClear.setFocusable(false);
        btnClear.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        txtTacGia.setEditable(false);
        txtTacGia.setBackground(new java.awt.Color(47, 55, 90));
        txtTacGia.setForeground(new java.awt.Color(255, 255, 255));
        txtTacGia.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTacGia.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTacGia.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTacGia.setLabelText("Tác Giả");
        txtTacGia.setLineColor(new java.awt.Color(255, 255, 255));
        txtTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTacGiaActionPerformed(evt);
            }
        });

        txtTheLoai.setEditable(false);
        txtTheLoai.setBackground(new java.awt.Color(47, 55, 90));
        txtTheLoai.setForeground(new java.awt.Color(255, 255, 255));
        txtTheLoai.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTheLoai.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTheLoai.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtTheLoai.setLabelText("Thể Loại");
        txtTheLoai.setLineColor(new java.awt.Color(255, 255, 255));
        txtTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTheLoaiActionPerformed(evt);
            }
        });

        rdoDangKinhDoanh.setBackground(new java.awt.Color(47, 55, 90));
        buttonGroup1.add(rdoDangKinhDoanh);
        rdoDangKinhDoanh.setForeground(new java.awt.Color(255, 255, 255));
        rdoDangKinhDoanh.setSelected(true);
        rdoDangKinhDoanh.setText("Đang kinh doanh");

        rdoNgungKinhDoanh.setBackground(new java.awt.Color(47, 55, 90));
        buttonGroup1.add(rdoNgungKinhDoanh);
        rdoNgungKinhDoanh.setForeground(new java.awt.Color(255, 255, 255));
        rdoNgungKinhDoanh.setText("Ngừng kinh doanh");

        cboNhaXuatBan.setBackground(new java.awt.Color(47, 55, 90));
        cboNhaXuatBan.setForeground(new java.awt.Color(255, 255, 255));
        cboNhaXuatBan.setMaximumRowCount(10);
        cboNhaXuatBan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Số lượng", "A-Z" }));
        cboNhaXuatBan.setSelectedIndex(-1);
        cboNhaXuatBan.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        cboNhaXuatBan.setLabeText("Nhà Xuất Bản");
        cboNhaXuatBan.setLineColor(new java.awt.Color(255, 255, 255));

        cboViTri.setBackground(new java.awt.Color(47, 55, 90));
        cboViTri.setForeground(new java.awt.Color(255, 255, 255));
        cboViTri.setMaximumRowCount(10);
        cboViTri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Số lượng", "A-Z" }));
        cboViTri.setSelectedIndex(-1);
        cboViTri.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        cboViTri.setLabeText("Vị Trí");
        cboViTri.setLineColor(new java.awt.Color(255, 255, 255));

        btnClear1.setBackground(new java.awt.Color(31, 31, 111));
        btnClear1.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnClear1.setForeground(new java.awt.Color(255, 255, 255));
        btnClear1.setText("Refresh");
        btnClear1.setToolTipText("Clear form");
        btnClear1.setFocusable(false);
        btnClear1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBourder1Layout = new javax.swing.GroupLayout(jPanelBourder1);
        jPanelBourder1.setLayout(jPanelBourder1Layout);
        jPanelBourder1Layout.setHorizontalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(cboViTri, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                            .addComponent(txtTacGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(220, Short.MAX_VALUE))
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addComponent(rdoDangKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdoNgungKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBourder1Layout.createSequentialGroup()
                                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboNhaXuatBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSoTrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                            .addComponent(txtBarCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnSelectTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSelectTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCamBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))))
        );
        jPanelBourder1Layout.setVerticalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoDangKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoNgungKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnCamBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelectTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboViTri, javax.swing.GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSelectTheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                    .addComponent(cboNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        TruongThongTin.add(jPanelBourder1);
        jPanelBourder1.setBounds(390, 130, 970, 620);

        jPanelBourder2.setBackground(new java.awt.Color(47, 55, 90));

        lblAvartar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Image Book");

        jPanel5.setPreferredSize(new java.awt.Dimension(311, 1));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 325, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        btnChooseImage.setBackground(new java.awt.Color(35, 35, 93));
        btnChooseImage.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnChooseImage.setForeground(new java.awt.Color(255, 255, 255));
        btnChooseImage.setText("Choose a picture for the book");
        btnChooseImage.setToolTipText("Chọn ảnh cho sách");
        btnChooseImage.setFocusable(false);
        btnChooseImage.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImageActionPerformed(evt);
            }
        });

        btnCameraImage.setBackground(new java.awt.Color(47, 55, 90));
        btnCameraImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image_Hub/icons8_camera_30px.png"))); // NOI18N
        btnCameraImage.setToolTipText("Chụp ảnh sách");
        btnCameraImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCameraImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBourder2Layout = new javax.swing.GroupLayout(jPanelBourder2);
        jPanelBourder2.setLayout(jPanelBourder2Layout);
        jPanelBourder2Layout.setHorizontalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder2Layout.createSequentialGroup()
                .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBourder2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelBourder2Layout.createSequentialGroup()
                                .addComponent(btnChooseImage, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCameraImage, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelBourder2Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabel4))
                    .addGroup(jPanelBourder2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lblAvartar, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanelBourder2Layout.setVerticalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAvartar, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCameraImage, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnChooseImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        TruongThongTin.add(jPanelBourder2);
        jPanelBourder2.setBounds(10, 130, 370, 510);

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Điều chỉnh sản phẩm");
        TruongThongTin.add(jLabel5);
        jLabel5.setBounds(20, 60, 184, 26);

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Management Book - Quản Lý Sách");
        TruongThongTin.add(jLabel6);
        jLabel6.setBounds(20, 20, 420, 33);

        add(TruongThongTin);
        TruongThongTin.setBounds(0, 0, 1370, 800);

        Form_Chon_TacGia.setBackground(new java.awt.Color(47, 55, 90));
        Form_Chon_TacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Form_Chon_TacGiaMouseClicked(evt);
            }
        });

        cbSelect.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        cbSelect.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbSelectPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel4.setBackground(new java.awt.Color(17, 28, 68));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));
        scroll.setViewportView(jPanel4);

        jPanel3.add(scroll);

        btnInBaoCao.setBackground(new java.awt.Color(62, 77, 144));
        btnInBaoCao.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInBaoCao.setForeground(new java.awt.Color(255, 255, 255));
        btnInBaoCao.setText("Đóng");
        btnInBaoCao.setFocusable(false);
        btnInBaoCao.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnInBaoCao.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnInBaoCao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInBaoCaoMouseClicked(evt);
            }
        });
        btnInBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCaoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Danh Sách Chọn ");

        lbNameForm.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbNameForm.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout Form_Chon_TacGiaLayout = new javax.swing.GroupLayout(Form_Chon_TacGia);
        Form_Chon_TacGia.setLayout(Form_Chon_TacGiaLayout);
        Form_Chon_TacGiaLayout.setHorizontalGroup(
            Form_Chon_TacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Form_Chon_TacGiaLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(Form_Chon_TacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Form_Chon_TacGiaLayout.createSequentialGroup()
                        .addGroup(Form_Chon_TacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_Chon_TacGiaLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(52, Short.MAX_VALUE))
                    .addGroup(Form_Chon_TacGiaLayout.createSequentialGroup()
                        .addGroup(Form_Chon_TacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbSelect, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNameForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnInBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
        );
        Form_Chon_TacGiaLayout.setVerticalGroup(
            Form_Chon_TacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_Chon_TacGiaLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(Form_Chon_TacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNameForm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInBaoCao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cbSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(Form_Chon_TacGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        add(Form_Chon_TacGia);
        Form_Chon_TacGia.setBounds(440, 110, 520, 500);

        background.setBackground(new java.awt.Color(0, 0, 0));
        background.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        background.setBorderPainted(false);
        background.setFocusPainted(false);
        background.setFocusable(false);
        background.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backgroundActionPerformed(evt);
            }
        });
        add(background);
        background.setBounds(0, 0, 1370, 840);
    }// </editor-fold>                        
    private EventTags event;
    private void backgroundActionPerformed(java.awt.event.ActionEvent evt) {                                           
        this.Form_Chon_TacGia.show(false);
        this.background.show(false);
        this.btnSelectTheLoai.show(true);
        this.TruongThongTin.show(true);
        if (_lstTacGia != null) {
            getTacGiaString();
        }
        if (_lstTheLoai != null) {
            getTheLoaiString();
        }
//        revalidate();
    }                                          

    private void loadTheLoai() {
        jPanel4.removeAll();
        txtTheLoai.setText("");
        if (_lstTheLoai == null) {
            _lstTheLoai = new HashMap<>();
        }

        for (TheLoai theLoai : _lstTheLoai.values()) {
            Item item = new Item(theLoai.toString());
            item.addEventRemove(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jPanel4.remove(item);
                    _lstTheLoai.remove(theLoai.getMa());
                    repaint();
                    revalidate();
                }
            }
            );
            jPanel4.add(item);
        }
        getTheLoaiString();

        revalidate();

    }

    private String getTheLoaiString() {
        String theLoaiStr = "";
        for (TheLoai theLoai : _lstTheLoai.values()) {
            theLoaiStr = theLoaiStr + ", " + theLoai.getTen();
        }
        if (!theLoaiStr.equals("")) {
            txtTheLoai.setText(theLoaiStr.substring(1));
        }
        return theLoaiStr;
    }

    private void btnSelectTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        this.lbNameForm.setText("Thêm Thể Loại");
        _lstAllTheLoai = _theLoaiService.selectAll();
        DefaultComboBoxModel model = new DefaultComboBoxModel(_lstAllTheLoai.toArray());
        this.cbSelect.setModel(model);
        loadTheLoai();

        this.background.show(true);
        this.btnSelectTheLoai.show(false);
        this.Form_Chon_TacGia.show(true);
        this.TruongThongTin.show(false);

    }                                                

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void txtSoTrangActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void txtMoTaActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void txtGiaBanActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void txtGiaNhapActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void txtBarCodeActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void loadTacGia() {

        jPanel4.removeAll();
        txtTacGia.setText("");
        if (_lstTacGia == null) {
            _lstTacGia = new HashMap<>();
        }

        for (TacGia tacGia : _lstTacGia.values()) {
            Item item = new Item(tacGia.toString());
            item.addEventRemove(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jPanel4.remove(item);
                    _lstTacGia.remove(tacGia.getMa());
                    repaint();
                    revalidate();
                }
            }
            );
            jPanel4.add(item);
        }
        revalidate();
        getTacGiaString();
    }

    private String getTacGiaString() {

        String tacGiaStr = "";
        for (TacGia tacGia : _lstTacGia.values()) {
            tacGiaStr = tacGiaStr + ", " + tacGia.getTen();
        }
        if (!tacGiaStr.equals("")) {
            txtTacGia.setText(tacGiaStr.substring(1));
        }
        return tacGiaStr;

    }

    private void btnSelectTacGiaActionPerformed(java.awt.event.ActionEvent evt) {                                                
        this.lbNameForm.setText("Thêm Tác Giả");
        _lstAllTacGia = _tacGiaService.selectAll();
        DefaultComboBoxModel model = new DefaultComboBoxModel(_lstAllTacGia.toArray());
        loadTacGia();

        this.cbSelect.setModel(model);
        this.background.show(true);
        this.btnSelectTheLoai.show(false);
        this.Form_Chon_TacGia.show(true);
        this.TruongThongTin.show(false);
        //        this.background.doClick();
        //        this.jScrollPane1.show(true);

        revalidate();
    }                                               

    private void clear() {
        _hinh = null;
        setAvartar();
        txtId.setText("");
        txtBarCode.setText("");
        txtGiaBan.setText("");
        txtGiaNhap.setText("");
        txtMa.setText("");
        txtMoTa.setText("");
        txtSoLuong.setText("");
        txtSoTrang.setText("");
        txtTen.setText("");
        rdoDangKinhDoanh.setSelected(true);
        cboNhaXuatBan.setSelectedIndex(0);
        cboViTri.setSelectedIndex(0);
        _lstTacGia = new HashMap<>();
        getTacGiaString();
        _lstTheLoai = new HashMap<>();
        getTheLoaiString();
        txtTacGia.setText("");
        txtTheLoai.setText("");
    }

    private Sach getForm() {
        String id = txtId.getText();
        String ma = txtMa.getText().trim();
        String barCode = txtBarCode.getText().trim();
        String giaBanStr = txtGiaBan.getText().trim();
        String giaNhapStr = txtGiaNhap.getText().trim();
        String moTa = txtMoTa.getText().trim();
        String soLuongStr = txtSoLuong.getText().trim();
        String soTrangStr = txtSoTrang.getText().trim();
        String ten = txtTen.getText().trim();
        int trangThai = rdoDangKinhDoanh.isSelected() ? Sach.DANGKINHDOANH : Sach.NGUNGKINHDOANH;

        if (ma.isBlank() || barCode.isBlank() || giaBanStr.isBlank() || giaNhapStr.isBlank() || soLuongStr.isBlank() || soTrangStr.isBlank() || ten.isBlank()) {
            ThongBao.showNoti_Error(this, "Không được để trống");
            return null;
        }

        if (ma.length() > 30) {
            ThongBao.showNoti_Error(this, "Mã sách không được quá 30 ký tự");
            return null;
        }

        if (!barCode.matches("\\d+") || barCode.length() < 7) {
            ThongBao.showNoti_Error(this, "BarCode không đúng định dạng");
            return null;
        }

        if (!giaBanStr.matches("\\d+(.\\d+)?") || giaBanStr.length() > 30) {
            ThongBao.showNoti_Error(this, "Giá bán không đúng định dạng");
            return null;
        }

        if (!giaNhapStr.matches("\\d+(.\\d+)?") || giaNhapStr.length() > 30) {
            ThongBao.showNoti_Error(this, "Giá nhập không đúng định dạng");
            return null;
        }

        if (!soLuongStr.matches("\\d+") || soLuongStr.length() > 9) {
            ThongBao.showNoti_Error(this, "Số lượng không đúng định dạng");
            return null;
        }

        if (!soTrangStr.matches("\\d+") || soTrangStr.length() > 9) {
            ThongBao.showNoti_Error(this, "Số trang không đúng định dạng");
            return null;
        }

        if (ten.length() > 70) {
            ThongBao.showNoti_Error(this, "Tên sách không được quá dài");
            return null;
        }

        int soLuong = Integer.parseInt(soLuongStr);
        int soTrang = Integer.parseInt(soTrangStr);
        BigDecimal giaNhap = BigDecimal.valueOf(Double.parseDouble(giaNhapStr));
        BigDecimal giaBan = BigDecimal.valueOf(Double.parseDouble(giaBanStr));

        return new Sach(id.isBlank() ? null : id, (NhaXuatBan) cboNhaXuatBan.getSelectedItem(), (ViTri) cboViTri.getSelectedItem(), ma, ten,
                soLuong, soTrang, giaNhap, giaBan, trangThai, _hinh, barCode, moTa);
    }

    private List<SachTacGia> getListSachTacGia(Sach sach) {
        List<SachTacGia> lstSachTacGia = new ArrayList<>();
        for (TacGia tacGia : _lstTacGia.values()) {
            SachTacGia sachTacGia = new SachTacGia(tacGia, sach);
            lstSachTacGia.add(sachTacGia);
        }
        return lstSachTacGia;
    }

    private List<TheLoaiChiTiet> getListTheLoaiCT(Sach sach) {
        List<TheLoaiChiTiet> lstTheLoaiCT = new ArrayList<>();
        for (TheLoai theLoai : _lstTheLoai.values()) {
            TheLoaiChiTiet theLoaiCT = new TheLoaiChiTiet(theLoai, sach);
            lstTheLoaiCT.add(theLoaiCT);
        }
        return lstTheLoaiCT;
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {                                       
        String id = txtId.getText();
        if (!id.isBlank()) {
            String slStr = txtSoLuong.getText().trim();
            if (!slStr.matches("\\d+") || slStr.equals("0") || slStr.isBlank()) {
                ThongBao.showNoti_Error(this, "Só lượng không đúng định dạng");
                return;
            }
            ThongBao.showNoti_Confirm(this, "Sách đã tồn tại. Xác nhận thêm số lượng sách?");
            if (ThongBao.getSelected() == ThongBao.YES) {
                boolean updateStatus = _sachService.updateSoLuongSach(id, Integer.parseInt(slStr));
                if (updateStatus) {
                    ThongBao.showNoti_Succes(this, "Cập nhật thành công");
                } else {
                    ThongBao.showNoti_Error(this, "Cập nhật thất bại");
                }
                return;
            }
        }
        Sach sach = getForm();
        if (sach != null) {
            Sach sachCheck = _sachService.getSachCheck(sach);
            if (sachCheck != null) {
                if (sachCheck.getMa().equals(sach.getMa())) {
                    ThongBao.showNoti_Error(this, "Mã sách đã tồn tại. Mời bạn nhập mã khác");
                } else {
                    ThongBao.showNoti_Error(this, "Mã barCode sách đã tồn tại. Mời bạn nhập mã khác");
                }
                return;
            }

            ThongBao.showNoti_Confirm(this, "Xác nhận thêm?");
            if (ThongBao.getSelected() == ThongBao.YES) {
                _sachService.insertSach(sach);
                _sachService.updateSachTacGia(getListSachTacGia(sach));
                boolean insertStatus = _sachService.updateTheLoaiChiTiet(getListTheLoaiCT(sach));
                if (insertStatus) {
                    ThongBao.showNoti_Succes(this, "Thêm sách thành công");
                } else {
                    ThongBao.showNoti_Error(this, "Thêm sách thất bại");
                }
                clear();
            }
        }
    }                                      

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {                                          
        Sach sach = getForm();
        if (sach.getId() == null) {
            ThongBao.showNoti_Error(this, "Bạn chưa chọn sách");
            return;
        }

        if (sach == null) {
            return;
        }

        Sach sachCheck = _sachService.selectUpdateByMa(sach);
        if (sachCheck != null) {
            if (sachCheck.getMa().equals(sach.getMa())) {
                ThongBao.showNoti_Error(this, "Mã sách đã tồn tại. Mời bạn nhập mã khác");
            } else {
                ThongBao.showNoti_Error(this, "Mã barCode sách đã tồn tại. Mời bạn nhập mã khác");
            }
            return;
        }

        ThongBao.showNoti_Confirm(this, "Xác nhận cập nhật?");
        if (ThongBao.getSelected() == ThongBao.YES) {
            boolean updateStatus = _sachService.updateSach(sach);
            _sachService.updateSachTacGia(getListSachTacGia(sach));
            _sachService.updateTheLoaiChiTiet(getListTheLoaiCT(sach));
            if (updateStatus) {
                ThongBao.showNoti_Succes(this, "Cập nhật thành công");
            } else {
                ThongBao.showNoti_Error(this, "Cập nhật thất bại");
            }
            clear();
        }

    }                                         

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {                                         
        clear();
    }                                        

    private void setAvartar() {
        if (_hinh == null) {
            lblAvartar.setIcon(new ImageIcon(new ImageIcon(DEFAULT_IMAGE).getImage().getScaledInstance(lblAvartar.getWidth(), lblAvartar.getHeight(), Image.SCALE_DEFAULT)));
            return;
        }
        lblAvartar.setIcon(new ImageIcon(new ImageIcon(_hinh).getImage().getScaledInstance(lblAvartar.getWidth(), lblAvartar.getHeight(), Image.SCALE_DEFAULT)));
    }

    private void btnChooseImageActionPerformed(java.awt.event.ActionEvent evt) {                                               
        JFileChooser fileChooser = new JFileChooser(currentDirectory);
        fileChooser.setPreferredSize(new Dimension(800, 600));
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png"));
        int result = fileChooser.showDialog(this, "Chọn ảnh");
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            currentDirectory = file.getAbsolutePath();
            Path p = Paths.get(currentDirectory);
            if (currentDirectory.toLowerCase().endsWith(".png") || currentDirectory.toLowerCase().endsWith(".jpg")) {
                try {
                    _hinh = Files.readAllBytes(p);
                    if (_hinh.length > 1024000) {
                        ThongBao.showNoti_Error(this, "File không được vượt quá 1M");
                        _hinh = null;
                        return;
                    }
                    setAvartar();
                } catch (NoSuchFileException nofile) {
                    ThongBao.showNoti_Error(this, "Không tìm thấy file");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return;
            }

            ThongBao.showNoti_Error(this, "Chỉ hỗ trợ file .jpg | .png");
        }
    }                                              

    private void txtTacGiaActionPerformed(java.awt.event.ActionEvent evt) {                                          
        btnSelectTacGia.doClick();
    }                                         

    private void txtTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {                                           
        btnSelectTheLoai.doClick();
    }                                          

    private void btnCamBarCodeActionPerformed(java.awt.event.ActionEvent evt) {                                              
//        openedCam();
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
                    Thread.sleep(100);
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
                        ThongBao.showNoti_Error(this, "BarCode không hợp lệ");
                        return;
                    }
                    txtBarCode.setText(result + "");
                    closedCam(cam);
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
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    if (!cam.isFocusOwner()) {
                        closedCam(cam);
                        btnCameraImage.setEnabled(true);
                        x = false;
                    }

                }
            }
        });
        thread.start();

    }                                             

    private void btnCameraImageActionPerformed(java.awt.event.ActionEvent evt) {                                               

        btnCameraImage.setEnabled(false);
        CamJFrame cam = new CamJFrame();
        cam.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                cam.webcam.close();
                btnCameraImage.setEnabled(true);
                closedCam(cam);
            }
        });
        cam.setTitle("Take a picture");
        cam.setVisible(true);
        cam.btnCapture.addActionListener((java.awt.event.ActionEvent evt1) -> {
            BufferedImage image = cam.webcam.getImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(image, "png", baos);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            _hinh = baos.toByteArray();
            MySoundEffect.play(MySoundEffect.PATH_CAPTURE_PICTURE);
            if (_hinh != null) {
                lblAvartar.setIcon(new ImageIcon(new ImageIcon(_hinh).getImage().getScaledInstance(lblAvartar.getWidth(), lblAvartar.getHeight(), Image.SCALE_DEFAULT)));
                this.TruongThongTin.show(true);
            }
            btnCameraImage.setEnabled(true);
            closedCam(cam);
        });

        Thread th = new Thread(() -> {
            cam.record();
        });
        th.start();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean x = true;
                while (x) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    if (!cam.isFocusOwner()) {
//                        cam.webcam.close();
//                        cam.dispose();
                        closedCam(cam);
                        btnCameraImage.setEnabled(true);
                        x = false;
                    }

                }
            }
        });
        thread.start();

    }                                              

    private void cbSelectPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {                                                      
        if (lbNameForm.getText().equals("Thêm Tác Giả")) {
            TacGia tacGia = (TacGia) this.cbSelect.getSelectedItem();
            _lstTacGia.put(tacGia.getMa(), tacGia);
            loadTacGia();
        } else if (lbNameForm.getText().equals("Thêm Thể Loại")) {
            TheLoai theLoai = (TheLoai) this.cbSelect.getSelectedItem();
            _lstTheLoai.put(theLoai.getMa(), theLoai);
            loadTheLoai();
        }
    }                                                     

}
