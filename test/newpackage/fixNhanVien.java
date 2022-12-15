/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

/**
 *
 * @author ECO
 */
public class fixNhanVien {
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
     
     boolean addNhanVien(NhanVien nhanVien);
    
    List<NhanVien> getAllNhanVien();
    
    List<NhanVien> getListNhanVienByName(String name);
    
    NhanVien getNhanVienById(String Id);
    
    boolean updateNhanVien(NhanVien nhanVien);
    
    NhanVien getNhanVien(String ma, String password);
    
    NhanVien getNhanVienByEmail(String Email);
     @Override
    public List<ViTri> getAllViTri() {
        List<ViTri> listViTri = new ArrayList<>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            listViTri = session.createQuery("from ViTri").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return listViTri;

    }

    @Override
    public boolean addViTri(ViTri viTri) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(viTri);
                tran.commit();
                return true;
            } catch (Exception e) {
                tran.rollback();
                e.printStackTrace();
                return false;
            } finally {
//                session.flush();
                session.close();
            }
        }
        
         @Override
    public List<PhieuNhapChiTiet> getAllPhieuNhapChiTiet() {
        List<PhieuNhapChiTiet> listPhieuNhapChiTiet = new ArrayList<>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            listPhieuNhapChiTiet = session.createQuery("from PhieuNhapChiTiet").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return listPhieuNhapChiTiet;
    }

    @Override
    public boolean themPhieuNhapCT(List<PhieuNhapChiTiet> lstPhieuNhapCT) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                int size = lstPhieuNhapCT.size();
                final int batchSize = 20;
                for (int i = 0; i < size; i++) {
                    session.persist(lstPhieuNhapCT.get(i));
                    
                    if (i % batchSize == 0 && i != size && i != 0) {
                        session.flush();
                        session.clear();
                    }
                }
                tran.commit();
                return true;

            } catch (Exception e) {
                tran.rollback();
                return false;
            }
        }
    }
    
    @Override
    public boolean themPNCT(PhieuNhapChiTiet phieuNhapChiTiet) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(phieuNhapChiTiet);
                tran.commit();
                return true;
            } catch (Exception e) {
                tran.rollback();
                e.printStackTrace();
                return false;
            } finally {
//                session.flush();
                session.close();
            }
        }
    }

    @Override
    public boolean suaPNCT(PhieuNhapChiTiet phieuNhapChiTiet) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(phieuNhapChiTiet);
                tran.commit();
                return true;
            } catch (Exception e) {
                tran.rollback();
                e.printStackTrace();
                return false;
            } finally {
//                session.flush();
                session.close();
            }
        }
    }

    @Override
    public String xoaPNCT(String id) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                String hql = "DELETE PhieuNhapChiTiet pn WHERE pn.id = :id";
                Query query = session.createQuery(hql);
                query.setParameter("id", id);
                int result = query.executeUpdate();
                if (result == 0) {
                    id = "";
                }
                transaction.commit();
            } catch (Exception e) {
                id = "";
            }
        }
        return id;
    }
@Override
    public List<PhieuNhap> getAllPhieuNhap() {
        List<PhieuNhap> listPhieuNhap;
        try ( Session session = HibernateUtil.getSessionFactory().openSession();) {
            listPhieuNhap = session.createQuery("from PhieuNhap pn order by pn.ma desc").list();
        }
        return listPhieuNhap;
    }

    @Override
    public List<PhieuNhap> getPhieuChuaNhap() {
        List<PhieuNhap> listPhieuNhap;
        try ( Session session = HibernateUtil.getSessionFactory().openSession();) {
            listPhieuNhap = session.createQuery("from PhieuNhap p where p.trangThai  = 0 order by p.ma desc").list();
        }
        return listPhieuNhap;
    }

    @Override
    public List<PhieuNhap> getPhieuDaNhap() {
        List<PhieuNhap> listPhieuNhap;
        try ( Session session = HibernateUtil.getSessionFactory().openSession();) {
            listPhieuNhap = session.createQuery("from PhieuNhap p where p.trangThai = 1 order by p.ma desc").list();
        }
        return listPhieuNhap;
    }

    @Override
    public boolean addphieuNhap(PhieuNhap phieuNhap) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(phieuNhap);
                tran.commit();
                return true;
            } catch (Exception e) {
                tran.rollback();
                e.printStackTrace();
                return false;
            } finally {
//                session.flush();
                session.close();
            }
        }
    }

    @Override
    public PhieuNhap getByMa(String ma) {

        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT pn FROM PhieuNhap pn where pn.ma = :ma";
            TypedQuery<PhieuNhap> query = session.createQuery(hql, PhieuNhap.class);
            query.setParameter("ma", ma);
            try {
                PhieuNhap phieuNhap = query.getSingleResult();
                return phieuNhap;
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    @Override
    public List<String> cbbNcc() {
        List<String> cbbNcc = new ArrayList<>();
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "select ten from NhaCungCap";
            TypedQuery<String> query = s.createQuery(hql, String.class);
            cbbNcc = query.getResultList();
        }
        return cbbNcc;
    }

//    public static void main(String[] args) {
//        List<String> listcbb = new PhieuNhapRepositoryImpl().cbbNcc();
//        System.out.println(listcbb);
//    }
    @Override
    public String xoa(String id) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                String hql = "DELETE PhieuNhap pn WHERE pn.id = :id";
                Query query = session.createQuery(hql);
                query.setParameter("id", id);
                int result = query.executeUpdate();
                if (result == 0) {
                    id = "";
                }
                transaction.commit();
            } catch (Exception e) {
                id = "";
            }
        }
        return id;
    }

    @Override
    public String findById(String ten) {
        String uuid;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String statement = "select p.id from NhaCungCap p where p.ten = :ten";
            TypedQuery<String> query = session.createQuery(statement, String.class);
            query.setParameter("ten", ten);
            uuid = query.getSingleResult();
        }
        return uuid;

    }

    @Override
    public boolean updatePhieuNhap(PhieuNhap phieuNhap) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(phieuNhap);
                tran.commit();
                return true;
            } catch (Exception e) {
                tran.rollback();
                e.printStackTrace();
                return false;
            } finally {
//                session.flush();
                session.close();
            }
        }

    }

    @Override
    public PhieuNhap getUpdateMa(PhieuNhap phieuNhap) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT pn FROM PhieuNhap pn where pn.ma = :ma and pn.id != :id";
            TypedQuery<PhieuNhap> query = session.createQuery(hql, PhieuNhap.class);
            query.setParameter("ma", phieuNhap.getMa());
            query.setParameter("id", phieuNhap.getId());
            try {
                PhieuNhap pn = query.getSingleResult();
                return pn;
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    @Override
    public List<PhieuNhap> selectByDay(Date date) {
        List<PhieuNhap> listPhieuNhap;
        try ( Session session = HibernateUtil.getSessionFactory().openSession();) {
            listPhieuNhap = session.createQuery("from PhieuNhap p where p.ngayNhap between :ngayNhap and dateadd(day, 1, :ngayNhap)").setParameter("ngayNhap", date).list();
        }
        return listPhieuNhap;
    }
    
    
    @Override
    public List<Sach> getList(int position, int pageSize) {

        List<Sach> lstSach = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT s FROM Sach s order by s.ma asc";
            TypedQuery<Sach> query = session.createQuery(hql);
            query.setFirstResult(position * pageSize);
            query.setMaxResults(pageSize);

            lstSach = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSach;
    }

    @Override
    public boolean insertSach(Sach sach) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(sach);
                tran.commit();
                return true;
            } catch (Exception e) {
                tran.rollback();
                e.printStackTrace();
                return false;
            } finally {
                session.close();
            }
        }
    }

    @Override
    public boolean insertSach(List<Sach> lstSach) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                int size = lstSach.size();
                final int batchSize = 20;
                for (int i = 0; i < size; i++) {
                    Sach sachUpdate = session.get(Sach.class, lstSach.get(i).getId());
                    if (sachUpdate != null) {
                        sachUpdate.setSoLuong(sachUpdate.getSoLuong() + lstSach.get(i).getSoLuong());
                        session.update(sachUpdate);
                    } else {
                        session.saveOrUpdate(lstSach.get(i));
                    }
                    if (i % batchSize == 0 && i != size && i != 0) {
                        session.flush();
                        session.clear();
                    }
                }
                tran.commit();
                return true;

            } catch (Exception e) {
                tran.rollback();
                return false;
            }
        }
    }

    @Override
    public boolean updateSach(Sach sach) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(sach);
                tran.commit();
                return true;
            } catch (Exception e) {
                tran.rollback();
                e.printStackTrace();
                return false;
            } finally {
                session.close();
            }
        }
    }

    @Override
    public boolean updateSoLuongSach(String id, int soLuong) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                Sach sachUpdate = session.get(Sach.class, id);
                sachUpdate.setSoLuong(sachUpdate.getSoLuong() + soLuong);
                session.update(sachUpdate);
                tran.commit();
                return true;

            } catch (Exception e) {
                tran.rollback();
                return false;
            }
        }
    }

    @Override
    public boolean updateSoLuongSach(List<Sach> lstSachUpdate) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                int size = lstSachUpdate.size();
                final int batchSize = 20;
                for (int i = 0; i < size; i++) {
                    Sach sachUpdate = session.get(Sach.class, lstSachUpdate.get(i).getId());
                    if (sachUpdate != null) {
                        sachUpdate.setSoLuong(sachUpdate.getSoLuong() + lstSachUpdate.get(i).getSoLuong());
                    }
                    session.saveOrUpdate(sachUpdate);
                    if (i % batchSize == 0 && i != size && i != 0) {
                        session.flush();
                        session.clear();
                    }
                }
                tran.commit();
                return true;

            } catch (Exception e) {
                tran.rollback();
                return false;
            }
        }
    }

    @Override
    public Sach getSachCheck(Sach sach) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Sach s WHERE s.ma = :ma or s.barCode = :barcode";
            TypedQuery<Sach> query = session.createQuery(hql);
            query.setParameter("ma", sach.getMa());
            query.setParameter("barcode", sach.getBarCode());

            try {
                List<Sach> lstsach = query.getResultList();
                return lstsach.get(0);
            } catch (Exception e) {
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countAllSach() {
        int total = 0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(s.id) FROM Sach s";
            TypedQuery<Long> query = session.createQuery(hql, Long.class);
            total = Integer.parseInt(query.getSingleResult() + "");
        }
        return total;
    }

    @Override
    public List<Sach> getListByKeyword(String keyword) {
        List<Sach> lstSach = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT s FROM Sach s full join s.lstTheLoaiCT t WHERE s.ten LIKE :ten OR s.ma LIKE :ma OR s.nhaXuatBan.ten LIKE :tenNXB ";//OR t.theLoai.ten LIKE :tenTheLoai";
            TypedQuery<Sach> query = session.createQuery(hql);
            query.setParameter("ten", "%" + keyword + "%");
            query.setParameter("ma", "%" + keyword + "%");
            query.setParameter("tenNXB", "%" + keyword + "%");
//            query.setParameter("tenTheLoai", "%" + keyword + "%");

            lstSach = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSach;
    }

    private void deleteSachTacGia(Sach sach) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession();) {
            session.beginTransaction();
            String hql = "DELETE FROM SachTacGia s WHERE s.sach = :sach";
            TypedQuery<Sach> query = session.createQuery(hql);
            query.setParameter("sach", sach);
            query.executeUpdate();
//            session.getTransaction().commit();
        }
    }

    @Override
    public boolean updateSachTacGia(List<SachTacGia> lstSachTacGia) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession();) {

            Transaction tran = session.beginTransaction();
            deleteSachTacGia(lstSachTacGia.get(0).getSach());
            try {
                final int batchSize = 20;
                int size = lstSachTacGia.size();
                for (int i = 0; i < size; i++) {
                    session.persist(lstSachTacGia.get(i));
                    if (i % batchSize == 0 && i != size && i != 0) {
                        session.flush();
                        session.clear();
                    }

                }

                tran.commit();
                return true;
            } catch (HibernateException e) {
                tran.rollback();
                return false;
            }
        }
    }

    private void deleteTheLoaiChiTiet(Sach sach) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession();) {
            session.beginTransaction();
            String hql = "DELETE FROM TheLoaiChiTiet t WHERE t.sach = :sach";
            TypedQuery<Sach> query = session.createQuery(hql);
            query.setParameter("sach", sach);
            query.executeUpdate();
//            session.getTransaction().commit();
        }
    }

    @Override
    public boolean updateTheLoaiChiTiet(List<TheLoaiChiTiet> lstTheLoaiChiTiet) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession();) {

            Transaction tran = session.beginTransaction();
            deleteTheLoaiChiTiet(lstTheLoaiChiTiet.get(0).getSach());
            try {
                final int batchSize = 20;
                int size = lstTheLoaiChiTiet.size();
                for (int i = 0; i < size; i++) {
                    session.persist(lstTheLoaiChiTiet.get(i));

                    if (i % batchSize == 0 && i != size && i != 0) {
                        session.flush();
                        session.clear();
                    }

                }

                tran.commit();
                return true;
            } catch (HibernateException e) {
                tran.rollback();
                return false;
            }
        }
    }

    @Override
    public List<Sach> selectAllLowerThan(int soLuong) {
        List<Sach> lstSach = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT s FROM Sach s WHERE s.soLuong < :soLuong AND s.trangThai = 1";
            TypedQuery<Sach> query = session.createQuery(hql);
            query.setParameter("soLuong", soLuong);

            lstSach = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSach;
    }

    @Override
    public Sach selectUpdateSach(Sach sach) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Sach s WHERE (s.ma = :ma or s.barCode = :ma) and s.id != :id";
            TypedQuery<Sach> query = session.createQuery(hql);
            query.setParameter("ma", sach.getMa());
            query.setParameter("id", sach.getId());

            try {
                Sach sachUpdate = query.getSingleResult();
                return sachUpdate;
            } catch (NoResultException e) {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Sach getSachByMa(String ma) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Sach s WHERE s.ma = :ma";
            TypedQuery<Sach> query = session.createQuery(hql);
            query.setParameter("ma",ma);

            try {
                List<Sach> lstsach = query.getResultList();
                return lstsach.get(0);
            } catch (Exception e) {
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<TacGia> selectAll() {
        List<TacGia> lstTacGia = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT t FROM TacGia t order by t.ma asc";
            TypedQuery<TacGia> query = session.createQuery(hql);

            lstTacGia = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTacGia;
    }

    @Override
    public boolean insertTacGia(TacGia tacGia) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(tacGia);
                tran.commit();
                return true;
            } catch (Exception e) {
                tran.rollback();
                e.printStackTrace();
                return false;
            } finally {
                session.close();
            }
        }
    }

    @Override
    public boolean updateTacGia(TacGia tacGia) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(tacGia);
                tran.commit();
                return true;
            } catch (Exception e) {
                tran.rollback();
                e.printStackTrace();
                return false;
            } finally {
                session.close();
            }
        }
    }

    @Override
    public List<TacGia> searchTacGiaByKeyWord(String keyword) {
        List<TacGia> lstTacGia = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT t FROM TacGia t WHERE t.ten LIKE :ten OR t.ma LIKE :ma";
            TypedQuery<TacGia> query = session.createQuery(hql);
            query.setParameter("ten", "%" + keyword + "%");
            query.setParameter("ma", "%" + keyword + "%");

            try {
                lstTacGia = query.getResultList();
            } catch (Exception e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTacGia;
    }

    @Override
    public TacGia sellectByMa(String ma) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT t FROM TacGia t WHERE t.ma = :ma";
            TypedQuery<TacGia> query = session.createQuery(hql);
            query.setParameter("ma", ma);

            try {
                TacGia tacGia = query.getSingleResult();
                return tacGia;
            } catch (NoResultException e) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TacGia selectUpdateByMa(TacGia tacGia) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT t FROM TacGia t WHERE t.ma = :ma and t.id != :id";
            TypedQuery<TacGia> query = session.createQuery(hql);
            query.setParameter("ma", tacGia.getMa());
            query.setParameter("id", tacGia.getId());

            try {
                TacGia tacGiaUpdate = query.getSingleResult();
                return tacGiaUpdate;
            } catch (NoResultException e) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
 private final PhieuNhapService _phieuNhapService;
    private final PhieuNhapChiTietService _phieuNhapCTService;
    private final SachService _sachService;
    private List<Sach> _lstSach;

    private List<PhieuNhap> _lstPhieuNhap;
    private PhieuNhap _phieuNhap;

    private final HashMap<String, HashMap<String, PhieuNhapChiTiet>> _lstPhieuNhapCT = new HashMap<>();

    public PhieuNhapChiTiet_Form() {
        initComponents();

        _phieuNhapService = new PhieuNhapServiceImpl();
        _phieuNhapCTService = new PhieuNhapChiTietServiceImpl();
        _sachService = new SachServiceImpl();
        loadTablePhieuNhap(-1);
        loadComboboxSach();
        loadTablePhieuNhapCT();
        setChangedAmount();
    }

    private void setChangedAmount() {
        tblPhieuNhapCT.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(_phieuNhap == null) {
                    return;
                }
                if (_phieuNhap.getTrangThai() == PhieuNhap.CHUATHEMVAOKHO) {
                    if(_lstPhieuNhapCT.get(_phieuNhap.getId()).isEmpty()) {
                        return;
                    }
                    List keys = new ArrayList(_lstPhieuNhapCT.get(_phieuNhap.getId()).keySet());
                    PhieuNhapChiTiet phieuNhapCT = _lstPhieuNhapCT.get(_phieuNhap.getId()).get(keys.get(e.getFirstRow()));
                    if (e.getColumn() != 3) {
                        return;
                    }
                    int amountChanged = Integer.parseInt(tblPhieuNhapCT.getValueAt(e.getFirstRow(), 3) + "");
                    if (amountChanged <= 0) {
                        ThongBao.showNoti_Error(PhieuNhapChiTiet_Form.this, "Số lượng phải lớn hơn 0");
                        tblPhieuNhapCT.setValueAt(phieuNhapCT.getSoLuong(), e.getFirstRow(), 3);
                        return;
                    }

                    phieuNhapCT.setSoLuong(amountChanged);
                    _lstPhieuNhapCT.get(_phieuNhap.getId()).put(phieuNhapCT.getSach().getId(), phieuNhapCT);
                    loadTablePhieuNhapCT();
                }
            }
        });
    }
    
    private void loadTablePhieuNhap(int trangThai) {
        DefaultTableModel dtm = (DefaultTableModel) tblPhieuNhap.getModel();
        dtm.setRowCount(0);
        _lstPhieuNhap = switch (trangThai) {
            case -1 ->
                _phieuNhapService.getAllPhieuNhap();
            case 0 ->
                _phieuNhapService.getPhieuChuaNhap();
            default ->
                _phieuNhapService.getPhieuDaNhap();
        };

        for (PhieuNhap phieuNhap : _lstPhieuNhap) {
            dtm.addRow(phieuNhap.toDataRow());
        }
        setEnableButton();
    }

    private void loadComboboxSach() {
        int soLuong = _sachService.countAllSach();
        List<Sach> lstSach = _sachService.getList(0, soLuong);
        cbSach.setModel(new DefaultComboBoxModel(lstSach.toArray()));
    }

    private void loadTablePhieuNhapCT() {
        DefaultTableModel dtm = (DefaultTableModel) tblPhieuNhapCT.getModel();
        dtm.setRowCount(0);
        if (_phieuNhap == null) {
            return;
        }

        if (_phieuNhap.getTrangThai() == PhieuNhap.CHUATHEMVAOKHO) {
            HashMap<String, PhieuNhapChiTiet> lstPhieuNhapCT = _lstPhieuNhapCT.get(_phieuNhap.getId());
            for (PhieuNhapChiTiet phieuNhapCT : lstPhieuNhapCT.values()) {
                dtm.addRow(phieuNhapCT.toDataRow());
            }
        } else {
            for (PhieuNhapChiTiet phieuNhapChiTiet : _phieuNhap.getLstPhieuNhapCT()) {
                dtm.addRow(phieuNhapChiTiet.toDataRow());
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanelBourder4 = new View.DesignComponent.JPanelBourder();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSach = new View.DesignComponent.Table();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuNhap = new View.DesignComponent.Table();
        jPanelBourder3 = new View.DesignComponent.JPanelBourder();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPhieuNhapCT = new View.DesignComponent.Table();
        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        jLabel2 = new javax.swing.JLabel();
        cbSach = new View.DesignComponent.Combobox();
        txtSearch = new View.DesignComponent.TextField();
        btnSearch = new View.ButtonDesign.Button();
        jPanelBourder5 = new View.DesignComponent.JPanelBourder();
        jLabel4 = new javax.swing.JLabel();
        btnRefresh = new View.ButtonDesign.Button();
        rdoDaNhap = new javax.swing.JRadioButton();
        rdoTatCa = new javax.swing.JRadioButton();
        rdoChuaNhap = new javax.swing.JRadioButton();
        btnThemVaoKho = new View.ButtonDesign.Button();
        btnXoa = new View.ButtonDesign.Button();
        btnXoaHet = new View.ButtonDesign.Button();

        jDialog1.setBackground(new java.awt.Color(11, 20, 55));
        jDialog1.setMaximumSize(new java.awt.Dimension(880, 439));
        jDialog1.setMinimumSize(new java.awt.Dimension(880, 439));
        jDialog1.setModal(true);

        jPanel1.setBackground(new java.awt.Color(11, 20, 55));
        jPanel1.setMaximumSize(new java.awt.Dimension(880, 500));
        jPanel1.setMinimumSize(new java.awt.Dimension(880, 500));

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(104, 143, 222));
        jLabel5.setText("Danh Sách Sách");

        jPanelBourder4.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setPreferredSize(new java.awt.Dimension(452, 395));

        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"200", "20000", null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Giá nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSach.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblSach.setRowHeight(40);
        tblSach.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSachMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblSach);

        javax.swing.GroupLayout jPanelBourder4Layout = new javax.swing.GroupLayout(jPanelBourder4);
        jPanelBourder4.setLayout(jPanelBourder4Layout);
        jPanelBourder4Layout.setHorizontalGroup(
            jPanelBourder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 868, Short.MAX_VALUE)
            .addGroup(jPanelBourder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelBourder4Layout.setVerticalGroup(
            jPanelBourder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
            .addGroup(jPanelBourder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jPanelBourder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelBourder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(11, 20, 55));

        jPanelBourder2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 395));

        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"NXB1", "VT1", "S1", "Đắc Nhân Tâm"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã", "Nhà cung cap", "Ngày nhap", "Trang thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuNhap.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblPhieuNhap.setRowHeight(40);
        tblPhieuNhap.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuNhap);

        javax.swing.GroupLayout jPanelBourder2Layout = new javax.swing.GroupLayout(jPanelBourder2);
        jPanelBourder2.setLayout(jPanelBourder2Layout);
        jPanelBourder2Layout.setHorizontalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelBourder2Layout.setVerticalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(38, 38, 38)))
        );

        jPanelBourder3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 395));

        tblPhieuNhapCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"VT1", "200", "20000", null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu nhập", "Mã sách", "Tên sách", "Số lượng", "Giá nhập", "Tổng tiền"
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
        tblPhieuNhapCT.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblPhieuNhapCT.setRowHeight(40);
        tblPhieuNhapCT.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblPhieuNhapCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuNhapCTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPhieuNhapCT);

        javax.swing.GroupLayout jPanelBourder3Layout = new javax.swing.GroupLayout(jPanelBourder3);
        jPanelBourder3.setLayout(jPanelBourder3Layout);
        jPanelBourder3Layout.setHorizontalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1309, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelBourder3Layout.setVerticalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
            .addGroup(jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder3Layout.createSequentialGroup()
                    .addContainerGap(29, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jPanelBourder1.setBackground(new java.awt.Color(17, 28, 68));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(104, 143, 222));
        jLabel2.setText("Chi Tiết Phiếu Nhập");

        cbSach.setBackground(new java.awt.Color(17, 28, 68));
        cbSach.setForeground(new java.awt.Color(255, 255, 255));
        cbSach.setMaximumRowCount(10);
        cbSach.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Số lượng", "A-Z" }));
        cbSach.setSelectedIndex(-1);
        cbSach.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        cbSach.setLabeText("Chọn Sách");
        cbSach.setLineColor(new java.awt.Color(255, 255, 255));
        cbSach.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbSachPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txtSearch.setBackground(new java.awt.Color(47, 55, 90));
        txtSearch.setForeground(new java.awt.Color(255, 255, 255));
        txtSearch.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSearch.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSearch.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtSearch.setLabelText("Nhập mã, tên");
        txtSearch.setLineColor(new java.awt.Color(255, 255, 255));

        btnSearch.setBackground(new java.awt.Color(31, 31, 111));
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

        javax.swing.GroupLayout jPanelBourder1Layout = new javax.swing.GroupLayout(jPanelBourder1);
        jPanelBourder1.setLayout(jPanelBourder1Layout);
        jPanelBourder1Layout.setHorizontalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addGap(86, 86, 86)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addComponent(cbSach, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelBourder1Layout.setVerticalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbSach, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelBourder5.setBackground(new java.awt.Color(17, 28, 68));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(104, 143, 222));
        jLabel4.setText("Danh Sách Phiếu Nhập");

        btnRefresh.setBackground(new java.awt.Color(31, 31, 111));
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

        buttonGroup1.add(rdoDaNhap);
        rdoDaNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoDaNhap.setForeground(new java.awt.Color(255, 255, 255));
        rdoDaNhap.setText("Đã nhập vào kho");
        rdoDaNhap.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoDaNhapItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdoTatCa);
        rdoTatCa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoTatCa.setForeground(new java.awt.Color(255, 255, 255));
        rdoTatCa.setSelected(true);
        rdoTatCa.setText("Tất cả");
        rdoTatCa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoTatCaItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdoChuaNhap);
        rdoChuaNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoChuaNhap.setForeground(new java.awt.Color(255, 255, 255));
        rdoChuaNhap.setText("Chưa nhập vào kho");
        rdoChuaNhap.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoChuaNhapItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelBourder5Layout = new javax.swing.GroupLayout(jPanelBourder5);
        jPanelBourder5.setLayout(jPanelBourder5Layout);
        jPanelBourder5Layout.setHorizontalGroup(
            jPanelBourder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(rdoTatCa)
                .addGap(54, 54, 54)
                .addComponent(rdoDaNhap)
                .addGap(55, 55, 55)
                .addComponent(rdoChuaNhap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanelBourder5Layout.setVerticalGroup(
            jPanelBourder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelBourder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelBourder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoDaNhap)
                    .addComponent(rdoTatCa)
                    .addComponent(rdoChuaNhap))
                .addContainerGap())
        );

        btnThemVaoKho.setBackground(new java.awt.Color(31, 31, 111));
        btnThemVaoKho.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnThemVaoKho.setForeground(new java.awt.Color(255, 255, 255));
        btnThemVaoKho.setText("Thêm vào kho");
        btnThemVaoKho.setFocusable(false);
        btnThemVaoKho.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnThemVaoKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVaoKhoActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(31, 31, 111));
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

        btnXoaHet.setBackground(new java.awt.Color(31, 31, 111));
        btnXoaHet.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnXoaHet.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaHet.setText("Xóa hết");
        btnXoaHet.setFocusable(false);
        btnXoaHet.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnXoaHet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelBourder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThemVaoKho, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnXoaHet, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelBourder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBourder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBourder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemVaoKho, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaHet, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>                        
     public void loadPhieuNhap(List<PhieuNhap> listPN) {

    }
    private void tblPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {                                          
        int row = tblPhieuNhap.getSelectedRow();
        if (row == -1) {
            return;
        }
        _phieuNhap = _lstPhieuNhap.get(row);
        if (!_lstPhieuNhapCT.containsKey(_phieuNhap.getId())) {
            _lstPhieuNhapCT.put(_phieuNhap.getId(), new HashMap<>());
        }
        loadTablePhieuNhapCT();
        setEnableButton();
    }                                         

    private void setEnableButton() {
        if (_phieuNhap == null) {

        }
        Color colorDisable = new Color(204, 204, 204);
        Color colorEnable = new Color(31, 31, 111);
        if (_phieuNhap == null || _phieuNhap.getTrangThai() == PhieuNhap.DATHEMVAOKHO) {

            btnThemVaoKho.setEnabled(false);
            btnThemVaoKho.setBackground(colorDisable);

            cbSach.setEnabled(false);

            btnXoa.setEnabled(false);
            btnXoa.setBackground(colorDisable);

            btnXoaHet.setEnabled(false);
            btnXoaHet.setBackground(colorDisable);

            btnSearch.setEnabled(false);
            btnSearch.setBackground(colorDisable);
        } else {
            btnThemVaoKho.setEnabled(true);
            btnThemVaoKho.setBackground(colorEnable);

            cbSach.setEnabled(true);

            btnXoa.setEnabled(true);
            btnXoa.setBackground(colorEnable);

            btnXoaHet.setEnabled(true);
            btnXoaHet.setBackground(colorEnable);

            btnSearch.setEnabled(true);
            btnSearch.setBackground(colorEnable);
        }
    }

    private void tblPhieuNhapCTMouseClicked(java.awt.event.MouseEvent evt) {                                            
        int row = tblPhieuNhapCT.getSelectedRow();
        if (row == -1) {
            return;
        }


    }                                           

    private void btnThemVaoKhoActionPerformed(java.awt.event.ActionEvent evt) {                                              

        if (_phieuNhap == null) {
            ThongBao.showNoti_Error(this, "Bạn hãy chọn phiếu nhập trước");
            return;
        }
        if (_lstPhieuNhapCT.get(_phieuNhap.getId()).isEmpty()) {
            ThongBao.showNoti_Error(this, "Không có gì để thêm");
            return;
        }
        ThongBao.showNoti_Confirm(this, "Xác nhận thêm vào kho?");
        if (ThongBao.getSelected() == ThongBao.YES) {
            boolean insertStatus = _phieuNhapCTService.themPhieuNhapCT(getListPhieuNhapCT());
            _phieuNhap.setTrangThai(PhieuNhap.DATHEMVAOKHO);
            boolean updateStatus = _phieuNhapService.updatePhieuNhap(_phieuNhap);
            if (insertStatus && updateStatus) {
                ThongBao.showNoti_Succes(this, "Nhập kho thành công");
            } else {
                ThongBao.showNoti_Succes(this, "Nhập kho thất bại");
                _phieuNhap.setTrangThai(PhieuNhap.CHUATHEMVAOKHO);
                _phieuNhapService.updatePhieuNhap(_phieuNhap);
            }
        }
        loadTablePhieuNhap(-1);
        
    }                                             

    private List<PhieuNhapChiTiet> getListPhieuNhapCT() {
        List<PhieuNhapChiTiet> lstPhieuNhapChiTiet = new ArrayList<>();
        for (PhieuNhapChiTiet phieuNhap : _lstPhieuNhapCT.get(_phieuNhap.getId()).values()) {
            lstPhieuNhapChiTiet.add(phieuNhap);
        }
        return lstPhieuNhapChiTiet;
    }

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {                                       

        int row = tblPhieuNhapCT.getSelectedRow();
        if (row == -1) {
            return;
        }
        List keys = new ArrayList(_lstPhieuNhapCT.get(_phieuNhap.getId()).keySet());
        ThongBao.showNoti_Confirm(this, "Xác nhận xóa?");
        if (ThongBao.getSelected() == ThongBao.YES) {
            _lstPhieuNhapCT.get(_phieuNhap.getId()).remove(keys.get(row));
            loadTablePhieuNhapCT();
        }

    }                                      

    private void btnXoaHetActionPerformed(java.awt.event.ActionEvent evt) {                                          
        if (_phieuNhap == null) {
            ThongBao.showNoti_Error(this, "Bạn hãy chọn phiếu nhập trước");
            return;
        }
        if (_lstPhieuNhapCT.get(_phieuNhap.getId()).isEmpty()) {
            ThongBao.showNoti_Error(this, "Không có gì để xóa");
            return;
        }
        ThongBao.showNoti_Confirm(this, "Xác nhận xóa toàn bộ?");
        if (ThongBao.getSelected() == ThongBao.YES) {
            _lstPhieuNhapCT.get(_phieuNhap.getId()).clear();
            loadTablePhieuNhapCT();
        }

    }                                         

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
        rdoTatCa.setSelected(true);
        txtSearch.setText("");
        loadComboboxSach();
        _phieuNhap = null;
        loadTablePhieuNhapCT();
        loadTablePhieuNhap(-1);
        tblPhieuNhap.clearSelection();
    }                                          

    private final DecimalFormat df = new DecimalFormat("#,###");
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String textSearch = txtSearch.getText();
        if (textSearch.isBlank()) {
            ThongBao.showNoti_Error(this, "Bạn hãy nhập từ mã hoặc tên sách vào ô tìm kiếm");
            return;
        }
        _lstSach = _sachService.getListByKeyword(textSearch);
        if (_lstSach.isEmpty()) {
            ThongBao.showNoti_Error(this, "Không tìm thấy");
            return;
        }
        DefaultTableModel dtm = (DefaultTableModel) tblSach.getModel();
        dtm.setRowCount(0);
        for (Sach sach : _lstSach) {
            dtm.addRow(new Object[]{sach.getMa(), sach.getTen(), df.format(sach.getGiaNhap()) + " vnđ"});
        }
        jDialog1.setSize(900, 500);
        jDialog1.setLocationRelativeTo(null);
        jDialog1.setVisible(true);


    }                                         


    private void cbSachPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {                                                    
        Sach sach = (Sach) cbSach.getSelectedItem();
        addSachToPhieuNhapCT(sach);
    }                                                   

    private void addSachToPhieuNhapCT(Sach sach) {
        if (_phieuNhap == null) {
            ThongBao.showNoti_Error(this, "Bạn hãy chọn phiếu nhập trước");
            return;
        }
        PhieuNhapChiTiet phieuNhapCT = new PhieuNhapChiTiet(_phieuNhap, sach, 1);
        if(_lstPhieuNhapCT.get(_phieuNhap.getId()).get(phieuNhapCT.getSach().getId()) != null) {
            ThongBao.showNoti_Error(this, "Sách đã tồn tại");
            return;
        }
        _lstPhieuNhapCT.get(_phieuNhap.getId()).put(phieuNhapCT.getSach().getId(), phieuNhapCT);
        loadTablePhieuNhapCT();
    }

    private void tblSachMouseClicked(java.awt.event.MouseEvent evt) {                                     
        if (evt.getClickCount() == 2) {
            int row = tblSach.getSelectedRow();
            if (row == -1) {
                return;
            }

            Sach sach = _lstSach.get(row);
            if (_lstPhieuNhapCT.get(_phieuNhap.getId()).containsKey(sach.getId())) {
                ThongBao.showNoti_Error(this, "Sách này đã tồn tại");
                return;
            }
            _lstPhieuNhapCT.get(_phieuNhap.getId()).put(sach.getId(), new PhieuNhapChiTiet(_phieuNhap, sach, 1));
            loadTablePhieuNhapCT();
            jDialog1.dispose();
        }
    }                                    

    private void rdoDaNhapItemStateChanged(java.awt.event.ItemEvent evt) {                                           

        loadTablePhieuNhap(PhieuNhap.DATHEMVAOKHO);
    }                                          

    private void rdoChuaNhapItemStateChanged(java.awt.event.ItemEvent evt) {                                             

        loadTablePhieuNhap(PhieuNhap.CHUATHEMVAOKHO);
    }                                            

    private void rdoTatCaItemStateChanged(java.awt.event.ItemEvent evt) {                                          

        loadTablePhieuNhap(-1);
    }                                         


    // Variables declaration - do not modify                     
    private View.ButtonDesign.Button btnRefresh;
    private View.ButtonDesign.Button btnSearch;
    private View.ButtonDesign.Button btnThemVaoKho;
    private View.ButtonDesign.Button btnXoa;
    private View.ButtonDesign.Button btnXoaHet;
    private javax.swing.ButtonGroup buttonGroup1;
    private View.DesignComponent.Combobox cbSach;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private View.DesignComponent.JPanelBourder jPanelBourder2;
    private View.DesignComponent.JPanelBourder jPanelBourder3;
    private View.DesignComponent.JPanelBourder jPanelBourder4;
    private View.DesignComponent.JPanelBourder jPanelBourder5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton rdoChuaNhap;
    private javax.swing.JRadioButton rdoDaNhap;
    private javax.swing.JRadioButton rdoTatCa;
    private View.DesignComponent.Table tblPhieuNhap;
    private View.DesignComponent.Table tblPhieuNhapCT;
    private View.DesignComponent.Table tblSach;
    private View.DesignComponent.TextField txtSearch;
    // End of variables declaration   
     private final PhieuNhapService _phieuNhapService;
    private final PhieuNhapChiTietService _phieuNhapCTService;
    private final SachService _sachService;
    private List<Sach> _lstSach;

    private List<PhieuNhap> _lstPhieuNhap;
    private PhieuNhap _phieuNhap;

    private final HashMap<String, HashMap<String, PhieuNhapChiTiet>> _lstPhieuNhapCT = new HashMap<>();

    public PhieuNhapChiTiet_Form() {
        initComponents();

        _phieuNhapService = new PhieuNhapServiceImpl();
        _phieuNhapCTService = new PhieuNhapChiTietServiceImpl();
        _sachService = new SachServiceImpl();
        loadTablePhieuNhap(-1);
        loadComboboxSach();
        loadTablePhieuNhapCT();
        setChangedAmount();
    }

    private void setChangedAmount() {
        tblPhieuNhapCT.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(_phieuNhap == null) {
                    return;
                }
                if (_phieuNhap.getTrangThai() == PhieuNhap.CHUATHEMVAOKHO) {
                    if(_lstPhieuNhapCT.get(_phieuNhap.getId()).isEmpty()) {
                        return;
                    }
                    List keys = new ArrayList(_lstPhieuNhapCT.get(_phieuNhap.getId()).keySet());
                    PhieuNhapChiTiet phieuNhapCT = _lstPhieuNhapCT.get(_phieuNhap.getId()).get(keys.get(e.getFirstRow()));
                    if (e.getColumn() != 3) {
                        return;
                    }
                    int amountChanged = Integer.parseInt(tblPhieuNhapCT.getValueAt(e.getFirstRow(), 3) + "");
                    if (amountChanged <= 0) {
                        ThongBao.showNoti_Error(PhieuNhapChiTiet_Form.this, "Số lượng phải lớn hơn 0");
                        tblPhieuNhapCT.setValueAt(phieuNhapCT.getSoLuong(), e.getFirstRow(), 3);
                        return;
                    }

                    phieuNhapCT.setSoLuong(amountChanged);
                    _lstPhieuNhapCT.get(_phieuNhap.getId()).put(phieuNhapCT.getSach().getId(), phieuNhapCT);
                    loadTablePhieuNhapCT();
                }
            }
        });
    }
    
    private void loadTablePhieuNhap(int trangThai) {
        DefaultTableModel dtm = (DefaultTableModel) tblPhieuNhap.getModel();
        dtm.setRowCount(0);
        _lstPhieuNhap = switch (trangThai) {
            case -1 ->
                _phieuNhapService.getAllPhieuNhap();
            case 0 ->
                _phieuNhapService.getPhieuChuaNhap();
            default ->
                _phieuNhapService.getPhieuDaNhap();
        };

        for (PhieuNhap phieuNhap : _lstPhieuNhap) {
            dtm.addRow(phieuNhap.toDataRow());
        }
        setEnableButton();
    }

    private void loadComboboxSach() {
        int soLuong = _sachService.countAllSach();
        List<Sach> lstSach = _sachService.getList(0, soLuong);
        cbSach.setModel(new DefaultComboBoxModel(lstSach.toArray()));
    }

    private void loadTablePhieuNhapCT() {
        DefaultTableModel dtm = (DefaultTableModel) tblPhieuNhapCT.getModel();
        dtm.setRowCount(0);
        if (_phieuNhap == null) {
            return;
        }

        if (_phieuNhap.getTrangThai() == PhieuNhap.CHUATHEMVAOKHO) {
            HashMap<String, PhieuNhapChiTiet> lstPhieuNhapCT = _lstPhieuNhapCT.get(_phieuNhap.getId());
            for (PhieuNhapChiTiet phieuNhapCT : lstPhieuNhapCT.values()) {
                dtm.addRow(phieuNhapCT.toDataRow());
            }
        } else {
            for (PhieuNhapChiTiet phieuNhapChiTiet : _phieuNhap.getLstPhieuNhapCT()) {
                dtm.addRow(phieuNhapChiTiet.toDataRow());
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanelBourder4 = new View.DesignComponent.JPanelBourder();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSach = new View.DesignComponent.Table();
        jPanelBourder2 = new View.DesignComponent.JPanelBourder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuNhap = new View.DesignComponent.Table();
        jPanelBourder3 = new View.DesignComponent.JPanelBourder();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPhieuNhapCT = new View.DesignComponent.Table();
        jPanelBourder1 = new View.DesignComponent.JPanelBourder();
        jLabel2 = new javax.swing.JLabel();
        cbSach = new View.DesignComponent.Combobox();
        txtSearch = new View.DesignComponent.TextField();
        btnSearch = new View.ButtonDesign.Button();
        jPanelBourder5 = new View.DesignComponent.JPanelBourder();
        jLabel4 = new javax.swing.JLabel();
        btnRefresh = new View.ButtonDesign.Button();
        rdoDaNhap = new javax.swing.JRadioButton();
        rdoTatCa = new javax.swing.JRadioButton();
        rdoChuaNhap = new javax.swing.JRadioButton();
        btnThemVaoKho = new View.ButtonDesign.Button();
        btnXoa = new View.ButtonDesign.Button();
        btnXoaHet = new View.ButtonDesign.Button();

        jDialog1.setBackground(new java.awt.Color(11, 20, 55));
        jDialog1.setMaximumSize(new java.awt.Dimension(880, 439));
        jDialog1.setMinimumSize(new java.awt.Dimension(880, 439));
        jDialog1.setModal(true);

        jPanel1.setBackground(new java.awt.Color(11, 20, 55));
        jPanel1.setMaximumSize(new java.awt.Dimension(880, 500));
        jPanel1.setMinimumSize(new java.awt.Dimension(880, 500));

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(104, 143, 222));
        jLabel5.setText("Danh Sách Sách");

        jPanelBourder4.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setPreferredSize(new java.awt.Dimension(452, 395));

        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"200", "20000", null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Giá nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSach.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblSach.setRowHeight(40);
        tblSach.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSachMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblSach);

        javax.swing.GroupLayout jPanelBourder4Layout = new javax.swing.GroupLayout(jPanelBourder4);
        jPanelBourder4.setLayout(jPanelBourder4Layout);
        jPanelBourder4Layout.setHorizontalGroup(
            jPanelBourder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 868, Short.MAX_VALUE)
            .addGroup(jPanelBourder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelBourder4Layout.setVerticalGroup(
            jPanelBourder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
            .addGroup(jPanelBourder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jPanelBourder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelBourder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(11, 20, 55));

        jPanelBourder2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 395));

        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"NXB1", "VT1", "S1", "Đắc Nhân Tâm"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã", "Nhà cung cap", "Ngày nhap", "Trang thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuNhap.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblPhieuNhap.setRowHeight(40);
        tblPhieuNhap.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuNhap);

        javax.swing.GroupLayout jPanelBourder2Layout = new javax.swing.GroupLayout(jPanelBourder2);
        jPanelBourder2.setLayout(jPanelBourder2Layout);
        jPanelBourder2Layout.setHorizontalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelBourder2Layout.setVerticalGroup(
            jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelBourder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(38, 38, 38)))
        );

        jPanelBourder3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 395));

        tblPhieuNhapCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"VT1", "200", "20000", null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu nhập", "Mã sách", "Tên sách", "Số lượng", "Giá nhập", "Tổng tiền"
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
        tblPhieuNhapCT.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblPhieuNhapCT.setRowHeight(40);
        tblPhieuNhapCT.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblPhieuNhapCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuNhapCTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPhieuNhapCT);

        javax.swing.GroupLayout jPanelBourder3Layout = new javax.swing.GroupLayout(jPanelBourder3);
        jPanelBourder3.setLayout(jPanelBourder3Layout);
        jPanelBourder3Layout.setHorizontalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBourder3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1309, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelBourder3Layout.setVerticalGroup(
            jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
            .addGroup(jPanelBourder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder3Layout.createSequentialGroup()
                    .addContainerGap(29, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jPanelBourder1.setBackground(new java.awt.Color(17, 28, 68));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(104, 143, 222));
        jLabel2.setText("Chi Tiết Phiếu Nhập");

        cbSach.setBackground(new java.awt.Color(17, 28, 68));
        cbSach.setForeground(new java.awt.Color(255, 255, 255));
        cbSach.setMaximumRowCount(10);
        cbSach.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Số lượng", "A-Z" }));
        cbSach.setSelectedIndex(-1);
        cbSach.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        cbSach.setLabeText("Chọn Sách");
        cbSach.setLineColor(new java.awt.Color(255, 255, 255));
        cbSach.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbSachPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txtSearch.setBackground(new java.awt.Color(47, 55, 90));
        txtSearch.setForeground(new java.awt.Color(255, 255, 255));
        txtSearch.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSearch.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtSearch.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtSearch.setLabelText("Nhập mã, tên");
        txtSearch.setLineColor(new java.awt.Color(255, 255, 255));

        btnSearch.setBackground(new java.awt.Color(31, 31, 111));
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

        javax.swing.GroupLayout jPanelBourder1Layout = new javax.swing.GroupLayout(jPanelBourder1);
        jPanelBourder1.setLayout(jPanelBourder1Layout);
        jPanelBourder1Layout.setHorizontalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addGap(86, 86, 86)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addComponent(cbSach, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelBourder1Layout.setVerticalGroup(
            jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelBourder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbSach, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelBourder5.setBackground(new java.awt.Color(17, 28, 68));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(104, 143, 222));
        jLabel4.setText("Danh Sách Phiếu Nhập");

        btnRefresh.setBackground(new java.awt.Color(31, 31, 111));
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

        buttonGroup1.add(rdoDaNhap);
        rdoDaNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoDaNhap.setForeground(new java.awt.Color(255, 255, 255));
        rdoDaNhap.setText("Đã nhập vào kho");
        rdoDaNhap.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoDaNhapItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdoTatCa);
        rdoTatCa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoTatCa.setForeground(new java.awt.Color(255, 255, 255));
        rdoTatCa.setSelected(true);
        rdoTatCa.setText("Tất cả");
        rdoTatCa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoTatCaItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdoChuaNhap);
        rdoChuaNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoChuaNhap.setForeground(new java.awt.Color(255, 255, 255));
        rdoChuaNhap.setText("Chưa nhập vào kho");
        rdoChuaNhap.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoChuaNhapItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelBourder5Layout = new javax.swing.GroupLayout(jPanelBourder5);
        jPanelBourder5.setLayout(jPanelBourder5Layout);
        jPanelBourder5Layout.setHorizontalGroup(
            jPanelBourder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(rdoTatCa)
                .addGap(54, 54, 54)
                .addComponent(rdoDaNhap)
                .addGap(55, 55, 55)
                .addComponent(rdoChuaNhap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanelBourder5Layout.setVerticalGroup(
            jPanelBourder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBourder5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelBourder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBourder5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelBourder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoDaNhap)
                    .addComponent(rdoTatCa)
                    .addComponent(rdoChuaNhap))
                .addContainerGap())
        );

        btnThemVaoKho.setBackground(new java.awt.Color(31, 31, 111));
        btnThemVaoKho.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnThemVaoKho.setForeground(new java.awt.Color(255, 255, 255));
        btnThemVaoKho.setText("Thêm vào kho");
        btnThemVaoKho.setFocusable(false);
        btnThemVaoKho.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnThemVaoKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVaoKhoActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(31, 31, 111));
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

        btnXoaHet.setBackground(new java.awt.Color(31, 31, 111));
        btnXoaHet.setBorder(javax.swing.BorderFactory.createEmptyBorder(-3, 1, 1, 1));
        btnXoaHet.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaHet.setText("Xóa hết");
        btnXoaHet.setFocusable(false);
        btnXoaHet.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnXoaHet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelBourder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThemVaoKho, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnXoaHet, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelBourder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBourder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBourder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBourder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBourder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemVaoKho, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaHet, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>                        
     public void loadPhieuNhap(List<PhieuNhap> listPN) {

    }
    private void tblPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {                                          
        int row = tblPhieuNhap.getSelectedRow();
        if (row == -1) {
            return;
        }
        _phieuNhap = _lstPhieuNhap.get(row);
        if (!_lstPhieuNhapCT.containsKey(_phieuNhap.getId())) {
            _lstPhieuNhapCT.put(_phieuNhap.getId(), new HashMap<>());
        }
        loadTablePhieuNhapCT();
        setEnableButton();
    }                                         

    private void setEnableButton() {
        if (_phieuNhap == null) {

        }
        Color colorDisable = new Color(204, 204, 204);
        Color colorEnable = new Color(31, 31, 111);
        if (_phieuNhap == null || _phieuNhap.getTrangThai() == PhieuNhap.DATHEMVAOKHO) {

            btnThemVaoKho.setEnabled(false);
            btnThemVaoKho.setBackground(colorDisable);

            cbSach.setEnabled(false);

            btnXoa.setEnabled(false);
            btnXoa.setBackground(colorDisable);

            btnXoaHet.setEnabled(false);
            btnXoaHet.setBackground(colorDisable);

            btnSearch.setEnabled(false);
            btnSearch.setBackground(colorDisable);
        } else {
            btnThemVaoKho.setEnabled(true);
            btnThemVaoKho.setBackground(colorEnable);

            cbSach.setEnabled(true);

            btnXoa.setEnabled(true);
            btnXoa.setBackground(colorEnable);

            btnXoaHet.setEnabled(true);
            btnXoaHet.setBackground(colorEnable);

            btnSearch.setEnabled(true);
            btnSearch.setBackground(colorEnable);
        }
    }

    private void tblPhieuNhapCTMouseClicked(java.awt.event.MouseEvent evt) {                                            
        int row = tblPhieuNhapCT.getSelectedRow();
        if (row == -1) {
            return;
        }


    }                                           

    private void btnThemVaoKhoActionPerformed(java.awt.event.ActionEvent evt) {                                              

        if (_phieuNhap == null) {
            ThongBao.showNoti_Error(this, "Bạn hãy chọn phiếu nhập trước");
            return;
        }
        if (_lstPhieuNhapCT.get(_phieuNhap.getId()).isEmpty()) {
            ThongBao.showNoti_Error(this, "Không có gì để thêm");
            return;
        }
        ThongBao.showNoti_Confirm(this, "Xác nhận thêm vào kho?");
        if (ThongBao.getSelected() == ThongBao.YES) {
            boolean insertStatus = _phieuNhapCTService.themPhieuNhapCT(getListPhieuNhapCT());
            _phieuNhap.setTrangThai(PhieuNhap.DATHEMVAOKHO);
            boolean updateStatus = _phieuNhapService.updatePhieuNhap(_phieuNhap);
            if (insertStatus && updateStatus) {
                ThongBao.showNoti_Succes(this, "Nhập kho thành công");
            } else {
                ThongBao.showNoti_Succes(this, "Nhập kho thất bại");
                _phieuNhap.setTrangThai(PhieuNhap.CHUATHEMVAOKHO);
                _phieuNhapService.updatePhieuNhap(_phieuNhap);
            }
        }
        loadTablePhieuNhap(-1);
        
    }                                             

    private List<PhieuNhapChiTiet> getListPhieuNhapCT() {
        List<PhieuNhapChiTiet> lstPhieuNhapChiTiet = new ArrayList<>();
        for (PhieuNhapChiTiet phieuNhap : _lstPhieuNhapCT.get(_phieuNhap.getId()).values()) {
            lstPhieuNhapChiTiet.add(phieuNhap);
        }
        return lstPhieuNhapChiTiet;
    }

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {                                       

        int row = tblPhieuNhapCT.getSelectedRow();
        if (row == -1) {
            return;
        }
        List keys = new ArrayList(_lstPhieuNhapCT.get(_phieuNhap.getId()).keySet());
        ThongBao.showNoti_Confirm(this, "Xác nhận xóa?");
        if (ThongBao.getSelected() == ThongBao.YES) {
            _lstPhieuNhapCT.get(_phieuNhap.getId()).remove(keys.get(row));
            loadTablePhieuNhapCT();
        }

    }                                      

    private void btnXoaHetActionPerformed(java.awt.event.ActionEvent evt) {                                          
        if (_phieuNhap == null) {
            ThongBao.showNoti_Error(this, "Bạn hãy chọn phiếu nhập trước");
            return;
        }
        if (_lstPhieuNhapCT.get(_phieuNhap.getId()).isEmpty()) {
            ThongBao.showNoti_Error(this, "Không có gì để xóa");
            return;
        }
        ThongBao.showNoti_Confirm(this, "Xác nhận xóa toàn bộ?");
        if (ThongBao.getSelected() == ThongBao.YES) {
            _lstPhieuNhapCT.get(_phieuNhap.getId()).clear();
            loadTablePhieuNhapCT();
        }

    }                                         

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
        rdoTatCa.setSelected(true);
        txtSearch.setText("");
        loadComboboxSach();
        _phieuNhap = null;
        loadTablePhieuNhapCT();
        loadTablePhieuNhap(-1);
        tblPhieuNhap.clearSelection();
    }                                          

    private final DecimalFormat df = new DecimalFormat("#,###");
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String textSearch = txtSearch.getText();
        if (textSearch.isBlank()) {
            ThongBao.showNoti_Error(this, "Bạn hãy nhập từ mã hoặc tên sách vào ô tìm kiếm");
            return;
        }
        _lstSach = _sachService.getListByKeyword(textSearch);
        if (_lstSach.isEmpty()) {
            ThongBao.showNoti_Error(this, "Không tìm thấy");
            return;
        }
        DefaultTableModel dtm = (DefaultTableModel) tblSach.getModel();
        dtm.setRowCount(0);
        for (Sach sach : _lstSach) {
            dtm.addRow(new Object[]{sach.getMa(), sach.getTen(), df.format(sach.getGiaNhap()) + " vnđ"});
        }
        jDialog1.setSize(900, 500);
        jDialog1.setLocationRelativeTo(null);
        jDialog1.setVisible(true);


    }                                         


    private void cbSachPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {                                                    
        Sach sach = (Sach) cbSach.getSelectedItem();
        addSachToPhieuNhapCT(sach);
    }                                                   

    private void addSachToPhieuNhapCT(Sach sach) {
        if (_phieuNhap == null) {
            ThongBao.showNoti_Error(this, "Bạn hãy chọn phiếu nhập trước");
            return;
        }
        PhieuNhapChiTiet phieuNhapCT = new PhieuNhapChiTiet(_phieuNhap, sach, 1);
        if(_lstPhieuNhapCT.get(_phieuNhap.getId()).get(phieuNhapCT.getSach().getId()) != null) {
            ThongBao.showNoti_Error(this, "Sách đã tồn tại");
            return;
        }
        _lstPhieuNhapCT.get(_phieuNhap.getId()).put(phieuNhapCT.getSach().getId(), phieuNhapCT);
        loadTablePhieuNhapCT();
    }

    private void tblSachMouseClicked(java.awt.event.MouseEvent evt) {                                     
        if (evt.getClickCount() == 2) {
            int row = tblSach.getSelectedRow();
            if (row == -1) {
                return;
            }

            Sach sach = _lstSach.get(row);
            if (_lstPhieuNhapCT.get(_phieuNhap.getId()).containsKey(sach.getId())) {
                ThongBao.showNoti_Error(this, "Sách này đã tồn tại");
                return;
            }
            _lstPhieuNhapCT.get(_phieuNhap.getId()).put(sach.getId(), new PhieuNhapChiTiet(_phieuNhap, sach, 1));
            loadTablePhieuNhapCT();
            jDialog1.dispose();
        }
    }                                    

    private void rdoDaNhapItemStateChanged(java.awt.event.ItemEvent evt) {                                           

        loadTablePhieuNhap(PhieuNhap.DATHEMVAOKHO);
    }                                          

    private void rdoChuaNhapItemStateChanged(java.awt.event.ItemEvent evt) {                                             

        loadTablePhieuNhap(PhieuNhap.CHUATHEMVAOKHO);
    }                                            

    private void rdoTatCaItemStateChanged(java.awt.event.ItemEvent evt) {                                          

        loadTablePhieuNhap(-1);
    }                                         


    // Variables declaration - do not modify                     
    private View.ButtonDesign.Button btnRefresh;
    private View.ButtonDesign.Button btnSearch;
    private View.ButtonDesign.Button btnThemVaoKho;
    private View.ButtonDesign.Button btnXoa;
    private View.ButtonDesign.Button btnXoaHet;
    private javax.swing.ButtonGroup buttonGroup1;
    private View.DesignComponent.Combobox cbSach;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private View.DesignComponent.JPanelBourder jPanelBourder1;
    private View.DesignComponent.JPanelBourder jPanelBourder2;
    private View.DesignComponent.JPanelBourder jPanelBourder3;
    private View.DesignComponent.JPanelBourder jPanelBourder4;
    private View.DesignComponent.JPanelBourder jPanelBourder5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton rdoChuaNhap;
    private javax.swing.JRadioButton rdoDaNhap;
    private javax.swing.JRadioButton rdoTatCa;
    private View.DesignComponent.Table tblPhieuNhap;
    private View.DesignComponent.Table tblPhieuNhapCT;
    private View.DesignComponent.Table tblSach;
    private View.DesignComponent.TextField txtSearch;
    // End of variables declaration   
}
