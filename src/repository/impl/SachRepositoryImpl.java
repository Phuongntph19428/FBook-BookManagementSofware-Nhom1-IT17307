/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.Sach;
import model.SachTacGia;
import model.TheLoaiChiTiet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.SachRepositoty;
import util.HibernateUtil;

/**
 *
 * @author ppolo
 */
public class SachRepositoryImpl implements SachRepositoty {

    @Override
    public List<Sach> getList(int position, int pageSize) {

        List<Sach> lstSach = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT s FROM Sach s order by s.ma asc";
            TypedQuery<Sach> query = session.createQuery(hql);
            query.setFirstResult(position);
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
    public Sach getSachByMa(String ma) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Sach s WHERE s.ma = :ma";
            TypedQuery<Sach> query = session.createQuery(hql);
            query.setParameter("ma", ma);

            List<Sach> lstsach = query.getResultList();

            return lstsach.get(0);
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
            String hql = "SELECT s FROM Sach s WHERE s.soLuong < :soLuong";
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
            String hql = "FROM Sach s WHERE s.ma = :ma and s.id != :id";
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

}
