/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

/**
 *
 * @author ECO
 */
public class Testnhanvien {
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

    @Override
    public boolean insert(TheLoai km) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(km);
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
    public boolean update(TheLoai km) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(km);
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
    public List<TheLoai> selectAll() {
        List<TheLoai> pes;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<TheLoai> query = session.createQuery("FROM TheLoai p");
            pes = query.getResultList();
            session.close();
        }
        return pes;
    }

    @Override
    public List<TheLoai> selectTimKiem(String ma) {
        List<TheLoai> lists = new ArrayList<TheLoai>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM TheLoai WHERE Ma LIKE '%" + ma + "%'";

            TypedQuery<TheLoai> query = session.createQuery(hql, TheLoai.class);
            lists = query.getResultList();

        }
        return lists;
    }
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
    public boolean insert(ChucVu cv) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(cv);
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
    public boolean update(ChucVu cv) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(cv);
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
    public List<ChucVu> selectAll() {
        List<ChucVu> pas;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<ChucVu> query = session.createQuery("From ChucVu k");
            pas = query.getResultList();
            session.close();
        }
//        System.out.println(pes);
        return pas;
    }
    @Override
    public List<ChucVu> SelectbyName(String name) {
        List<ChucVu> pas;
        String nameSelect = "%"+name+"%";
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<ChucVu> query = session.createQuery("From ChucVu  WHERE Ten like :key");
            query.setParameter("key", nameSelect);
            System.out.println(query);
            pas = query.getResultList();
            session.close();

        }
//        System.out.println(pes);
        return pas;
    }
    
}
