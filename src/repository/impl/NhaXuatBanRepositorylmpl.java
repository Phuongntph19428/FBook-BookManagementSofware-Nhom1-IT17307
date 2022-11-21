/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.List;
import javax.persistence.TypedQuery;
import model.NhaXuatBan;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.NhaXuatBanRepository;
import util.HibernateUtil;
/**
 *
 * @author ECO
 */
public class NhaXuatBanRepositorylmpl implements NhaXuatBanRepository{

    @Override
    public boolean insert(NhaXuatBan nxb) {
            try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.save(nxb);
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
    public boolean update(NhaXuatBan nxb) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = session.beginTransaction();
            try {
                session.update(nxb);
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
    public List<NhaXuatBan> selectAll() {
           List<NhaXuatBan> pos;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<NhaXuatBan> query = session.createQuery("From NhaXuatBan k");
            pos = query.getResultList();
            session.close();
        }
//        System.out.println(pes);
        return pos;
    }

    @Override
    public List<NhaXuatBan> SelectbyName(String name) {
              List<NhaXuatBan> pos;
        String nameSelect = "%"+name+"%";
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<NhaXuatBan> query = session.createQuery("From NhaXuatBan  WHERE Ten like :key");
            query.setParameter("key", nameSelect);
            System.out.println(query);
            pos = query.getResultList();
            session.close();
            
        }
//        System.out.println(pes);
        return pos;
    }
    
}
