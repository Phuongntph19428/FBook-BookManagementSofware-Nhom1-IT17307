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
import model.ChucVu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.ChucVuRepository;
import util.HibernateUtil;

/**
 *
 * @author ppolo
 */
public class ChucVuRepositoryImpl implements ChucVuRepository {

    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    @Override
    public List<ChucVu> getAllChucVu() {
        List<ChucVu> listChucVu;
        try ( Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ChucVu> cq = criteriaBuilder.createQuery(ChucVu.class);
            Root<ChucVu> root = cq.from(ChucVu.class);
            CriteriaQuery<ChucVu> all = cq.select(root);

            TypedQuery<ChucVu> allQuery = session.createQuery(all);
            listChucVu = allQuery.getResultList();
            session.close();
        }
        return listChucVu;
    }
}
