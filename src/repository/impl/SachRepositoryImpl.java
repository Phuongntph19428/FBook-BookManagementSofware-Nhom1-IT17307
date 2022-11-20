/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import model.Sach;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.SachRepositoty;
import util.HibernateUtil;

/**
 *
 * @author ppolo
 */
public class SachRepositoryImpl implements SachRepositoty{
    
    @Override
    public List<Sach> getList(int position, int pageSize) {

        List<Sach> lstSach = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "SELECT s FROM Sach s";
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
                session.flush();
                session.close();
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
                session.flush();
                session.close();
            }
        }
    }

}
