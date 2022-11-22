/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.NhaCungCap;
import model.NhanVien;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.NhaCungCapRepository;
import repository.NhanVienRepository;
import util.HibernateUtil;

/**
 *
 * @author ppolo
 */
public class NhaCungCapRepositoryImpl implements NhaCungCapRepository {

    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    @Override
    public boolean addNhaCungCap(NhaCungCap ncc) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(ncc);
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
    public List<NhaCungCap> getAllNhaCungCap() {
        List<NhaCungCap> listNhaCungCap;
        try ( Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<NhaCungCap> cq = criteriaBuilder.createQuery(NhaCungCap.class);
            Root<NhaCungCap> root = cq.from(NhaCungCap.class);
            CriteriaQuery<NhaCungCap> all = cq.select(root);

            TypedQuery<NhaCungCap> allQuery = session.createQuery(all);
            listNhaCungCap = allQuery.getResultList();
            session.close();
        }

        return listNhaCungCap;
    }

    @Override
    public boolean updateNhaCungCap(NhaCungCap nhaCungCap) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(nhaCungCap);
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
}
