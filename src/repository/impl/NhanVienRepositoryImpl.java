/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.NhanVien;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import static org.hibernate.criterion.Projections.id;
import repository.NhanVienRepository;
import util.HibernateUtil;

/**
 *
 * @author ppolo
 */
public class NhanVienRepositoryImpl implements NhanVienRepository {

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
        try ( Session session = factory.openSession()) {
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
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
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
}
