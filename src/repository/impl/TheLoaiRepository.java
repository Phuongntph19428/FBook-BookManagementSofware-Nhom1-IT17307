/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import model.TheLoai;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.ITheLoaiRepository;
import util.HibernateUtil;

/**
 *
 * @author adm
 */
public class TheLoaiRepository implements ITheLoaiRepository {

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

}
