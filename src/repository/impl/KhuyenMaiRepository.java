/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import model.KhuyenMai;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import repository.IKhuyenMaiRepository;

/**
 *
 * @author adm
 */
public class KhuyenMaiRepository implements IKhuyenMaiRepository {

    @Override
    public boolean insert(KhuyenMai km) {
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
    public boolean update(KhuyenMai km) {
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
    public List<KhuyenMai> selectAll() {
        
        List<KhuyenMai> pes;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<KhuyenMai> query = session.createQuery("From KhuyenMai k");
            pes = query.getResultList();
            session.close();
        }
//        System.out.println(pes);
        return pes;
    }
    
}
