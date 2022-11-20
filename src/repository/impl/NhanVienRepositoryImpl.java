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
import model.NhanVien;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
            session.close();
        }
        return listNhanVien;
    }
}
