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
 
 public void print(){
     SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Calendar c3 = Calendar.getInstance();
        Calendar c4 = Calendar.getInstance();
        Calendar c5 = Calendar.getInstance();

        c1.roll(Calendar.DAY_OF_YEAR, -1);
        c2.roll(Calendar.DAY_OF_YEAR, -2);
        c3.roll(Calendar.DAY_OF_YEAR, -3);
        c4.roll(Calendar.DAY_OF_YEAR, -4);
        c5.roll(Calendar.DAY_OF_YEAR, -5);

        dateFormat.format(c1.getTime());

        DecimalFormat df = new DecimalFormat("#,###");
        if (JOptionPane.showConfirmDialog(this, "In", "Xác nhận", JOptionPane.OK_CANCEL_OPTION) == 0) {
            Document document = new Document();
            String hd = "Thong Ke Tong Quan";
            String hd1 = ">==========================================<";

            try {
                LocalDateTime localDate = LocalDateTime.now();
                int ngay = localDate.getDayOfMonth();
                int thang = localDate.getMonthValue();
                int nam = localDate.getYear();
                int gio = localDate.getHour();
                int phut = localDate.getMinute();

                // khởi tạo một PdfWriter truyền vào document và FileOutputStream
                File directory = new File("thongKe");
                String path = "thongKe//" + "thongke_in_ngay" + String.valueOf(ngay + "_" + thang + "_" + nam + "TG" + gio + "h" + phut + "p") + ".pdf";
                File file = new File(path);
                if (!file.exists()) {
                    directory.mkdirs();
                    file.createNewFile();
                    System.out.println(file.getAbsolutePath());
                }
                PdfWriter.getInstance(document, new FileOutputStream(file));
                // mở file để thực hiện viết
                document.open();
                // thêm nội dung sử dụng add function

                PdfPTable table = new PdfPTable(3);
                PdfPTable table1 = new PdfPTable(5);
                //Khởi tạo 3 ô header
//                -- tao anh  
                document.add(new Paragraph("FBook"));
                try {
                    Image image4 = Image.getInstance("D:\\FPTPOLYTECH\\duanbieudo\\New folder (3)\\duan3\\image\\ssqqqsqsqsqs.png");
                    image4.setAbsolutePosition(350, 215);
                    document.add(image4);
                } catch (Exception e) {
                }
                try {
                    Image image1 = Image.getInstance("D:\\FPTPOLYTECH\\duanbieudo\\New folder (3)\\duan3\\image\\logo-fpt-fpt-polytechnic-tach-nen_043151201 (5).png");
                    image1.setAbsolutePosition(250, 10);
                    document.add(image1);
                } catch (Exception e) {
                }
                try {
                    Image image3 = Image.getInstance("D:\\FPTPOLYTECH\\duanbieudo\\New folder (3)\\duan3\\image\\11111.png");
                    image3.setAbsolutePosition(60, 730);
                    document.add(image3);
                } catch (Exception e) {
                }
                try {
                    Image image2 = Image.getInstance("D:\\FPTPOLYTECH\\duanbieudo\\New folder (3)\\duan3\\image\\icons8_best_seller_30px_1.png");
                    document.add(image2);
                } catch (Exception e) {
                }

                Paragraph paragraph3 = new Paragraph(hd);
                Paragraph paragraph1 = new Paragraph(hd1);
                Paragraph paragraph16 = new Paragraph("- Thoi Gian In : " + String.valueOf(ngay + "_" + thang + "_" + nam + "TG" + gio + "h" + phut + "p"));
                Paragraph paragraph24 = new Paragraph("1. Ngay Hom Nay - " + String.valueOf(ngay + "_" + thang + "_" + nam));
                Paragraph paragraph25 = new Paragraph("- Hoa Don: " + lblHoaDon0.getText().trim() + "    ");
                Paragraph paragraph26 = new Paragraph("- Thu: " + lblDoanhThu0.getText().trim() + " VND");
                Paragraph paragraph2 = new Paragraph("2. Doanh Thu 3 Thang Gan Nhat.");
                Paragraph paragraph17 = new Paragraph("3. Top 5 San Pham Ban Chay Tai Cua Hang.");
                Paragraph paragraph23 = new Paragraph("4. Thu Chi 5 Ngay Gan Nhat .");
                Paragraph paragraph18 = new Paragraph("- Top 1 San Pham: ");

                Paragraph paragraph19 = new Paragraph("- Top 2 San Pham: ");

                Paragraph paragraph20 = new Paragraph("- Top 3 San Pham: ");

                Paragraph paragraph21 = new Paragraph("- Top 4 San Pham: ");

                Paragraph paragraph22 = new Paragraph("- Top 5 San Pham: ");

                Paragraph paragraph5 = new Paragraph("");
                PdfPCell header1 = new PdfPCell(new Paragraph("- Doanh Thu " + lblThang2.getText().trim() + "    "));
                PdfPCell header2 = new PdfPCell(new Paragraph("- Doanh Thu " + lblThang1.getText().trim() + "    "));
                PdfPCell header3 = new PdfPCell(new Paragraph("- Doanh Thu " + lblThang3.getText().trim() + "    "));
                PdfPCell header10 = new PdfPCell(new Paragraph("Hoa Don: " + lblHoaDon3.getText().trim() + "    "));
                PdfPCell header11 = new PdfPCell(new Paragraph("Hoa Don: " + lblHoaDon2.getText().trim() + "    "));
                PdfPCell header12 = new PdfPCell(new Paragraph("Hoa Don: " + lblHoaDon1.getText().trim() + "    "));
                PdfPCell header13 = new PdfPCell(new Paragraph("Thu: " + lblDoanhThu3.getText().trim() + " VND"));
                PdfPCell header14 = new PdfPCell(new Paragraph("Thu: " + lblDoanhThu2.getText().trim() + " VND"));
                PdfPCell header15 = new PdfPCell(new Paragraph("Thu: " + lblDoanhThu1.getText().trim() + " VND"));

                PdfPCell header20 = new PdfPCell(new Paragraph("Ngay " + dateFormat.format(c1.getTime()) + "    "));
                PdfPCell header21 = new PdfPCell(new Paragraph("Ngay " + dateFormat.format(c2.getTime()) + "    "));
                PdfPCell header22 = new PdfPCell(new Paragraph("Ngay " + dateFormat.format(c3.getTime()) + "    "));
                PdfPCell header23 = new PdfPCell(new Paragraph("Ngay " + dateFormat.format(c4.getTime()) + "    "));
                PdfPCell header24 = new PdfPCell(new Paragraph("Ngay " + dateFormat.format(c5.getTime()) + "    "));

                //Thêm 3 ô header vào table
//              -- ten Doanh thu 3 thang
                table.addCell(header3);
                table.addCell(header1);
                table.addCell(header2);
//              -- tong so luong hoa don 
                table.addCell(header10);
                table.addCell(header11);
                table.addCell(header12);
//               -- tong doanh thu
                table.addCell(header13);
                table.addCell(header14);
                table.addCell(header15);

//                -- 4
                table1.addCell(header20);
                table1.addCell(header21);
                table1.addCell(header22);
                table1.addCell(header23);
                table1.addCell(header24);
//                --thu
                try {
                    PdfPCell header25 = new PdfPCell(new Paragraph("Thu:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu1().get(0))) + " VND"));
                    table1.addCell(header25);
                } catch (Exception e) {
                    PdfPCell header25 = new PdfPCell(new Paragraph("Thu: 0" + " VND"));
                    table1.addCell(header25);
                }
                try {
                    PdfPCell header26 = new PdfPCell(new Paragraph("Thu:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu2().get(0))) + " VND"));
                    table1.addCell(header26);
                } catch (Exception e) {
                    PdfPCell header26 = new PdfPCell(new Paragraph("Thu: 0" + " VND"));
                    table1.addCell(header26);
                }
                try {
                    PdfPCell header27 = new PdfPCell(new Paragraph("Thu:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu3().get(0))) + " VND"));
                    table1.addCell(header27);
                } catch (Exception e) {
                    PdfPCell header27 = new PdfPCell(new Paragraph("Thu: 0" + " VND"));
                    table1.addCell(header27);
                }
                try {
                    PdfPCell header28 = new PdfPCell(new Paragraph("Thu:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu4().get(0))) + " VND"));
                    table1.addCell(header28);
                } catch (Exception e) {
                    PdfPCell header28 = new PdfPCell(new Paragraph("Thu: 0" + " VND"));
                    table1.addCell(header28);
                }
                try {
                    PdfPCell header29 = new PdfPCell(new Paragraph("Thu:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotThu5().get(0))) + " VND"));
                    table1.addCell(header29);
                } catch (Exception e) {
                    PdfPCell header29 = new PdfPCell(new Paragraph("Thu: 0" + " VND"));
                    table1.addCell(header29);
                }
                try {
                    //                --chi
                    PdfPCell header30 = new PdfPCell(new Paragraph("Chi:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi1().get(0))) + " VND"));
                    table1.addCell(header30);
                } catch (Exception e) {
                    PdfPCell header30 = new PdfPCell(new Paragraph("Chi: 0" + " VND"));
                    table1.addCell(header30);
                }
                try {
                    PdfPCell header31 = new PdfPCell(new Paragraph("Chi:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi2().get(0))) + " VND"));
                    table1.addCell(header31);
                } catch (Exception e) {
                    PdfPCell header31 = new PdfPCell(new Paragraph("Chi: 0" + " VND"));
                    table1.addCell(header31);
                }
                try {
                    PdfPCell header32 = new PdfPCell(new Paragraph("Chi:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi3().get(0))) + " VND"));
                    table1.addCell(header32);
                } catch (Exception e) {
                    PdfPCell header32 = new PdfPCell(new Paragraph("Chi: 0" + " VND"));
                    table1.addCell(header32);
                }
                try {
                    PdfPCell header33 = new PdfPCell(new Paragraph("Chi:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi4().get(0))) + " VND"));
                    table1.addCell(header33);
                } catch (Exception e) {
                    PdfPCell header33 = new PdfPCell(new Paragraph("Chi: 0" + " VND"));
                    table1.addCell(header33);
                }
                try {
                    PdfPCell header34 = new PdfPCell(new Paragraph("Chi:      " + String.valueOf(df.format(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoCotChi5().get(0))) + " VND"));
                    table1.addCell(header34);
                } catch (Exception e) {
                    PdfPCell header34 = new PdfPCell(new Paragraph("Chi: 0" + " VND"));
                    table1.addCell(header34);
                }

                paragraph1.setIndentationLeft(80);

                paragraph1.setAlignment(Element.ALIGN_CENTER);
                paragraph1.setSpacingAfter(15);
                paragraph3.setIndentationLeft(80);

                paragraph3.setAlignment(Element.ALIGN_CENTER);

                paragraph2.setSpacingBefore(15);
                paragraph2.setAlignment(Element.ALIGN_LEFT);
                paragraph5.setSpacingBefore(15);
                paragraph5.setAlignment(Element.ALIGN_LEFT);
                paragraph16.setSpacingBefore(15);
                paragraph16.setAlignment(Element.ALIGN_LEFT);

                paragraph25.setIndentationLeft(52);
                paragraph25.setAlignment(Element.ALIGN_LEFT);
                paragraph26.setIndentationLeft(52);
                paragraph26.setAlignment(Element.ALIGN_LEFT);

                document.add(paragraph16);
                document.add(paragraph3);
                document.add(paragraph1);
                document.add(paragraph24);
                document.add(paragraph25);
                document.add(paragraph26);
                document.add(paragraph2);
                document.add(paragraph5);

                paragraph18.setIndentationLeft(52);
                paragraph18.setAlignment(Element.ALIGN_LEFT);
                paragraph19.setIndentationLeft(52);
                paragraph19.setAlignment(Element.ALIGN_LEFT);
                paragraph20.setIndentationLeft(52);
                paragraph20.setAlignment(Element.ALIGN_LEFT);
                paragraph21.setIndentationLeft(52);
                paragraph21.setAlignment(Element.ALIGN_LEFT);
                paragraph22.setIndentationLeft(52);
                paragraph22.setAlignment(Element.ALIGN_LEFT);
                paragraph23.setSpacingBefore(10);

                document.add(table);
                document.add(paragraph17);
                document.add(paragraph18);
                try {
                    Paragraph paragraph33 = new Paragraph("+ Ten San Pham: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoTron().get(0)));
                    Paragraph paragraph34 = new Paragraph("+ So Luong: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeBieuDoTron().get(0)));
                    paragraph33.setIndentationLeft(80);
                    paragraph33.setIndentationRight(230);
                    paragraph33.setAlignment(Element.ALIGN_LEFT);
                    paragraph34.setIndentationLeft(80);
                    paragraph34.setIndentationRight(230);
                    paragraph34.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph33);
                    document.add(paragraph34);
                } catch (Exception e) {
                    Paragraph paragraph33 = new Paragraph("+ Ten San Pham: " + "khong co");
                    Paragraph paragraph34 = new Paragraph("+ So Luong: 0");
                    paragraph33.setIndentationLeft(80);
                    paragraph33.setIndentationRight(230);
                    paragraph33.setAlignment(Element.ALIGN_LEFT);
                    paragraph34.setIndentationLeft(80);
                    paragraph34.setIndentationRight(230);
                    paragraph34.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph33);
                    document.add(paragraph34);
                }

                document.add(paragraph19);
                try {
                    Paragraph paragraph35 = new Paragraph("+ Ten San Pham: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoTron().get(1)));
                    Paragraph paragraph36 = new Paragraph("+ So Luong: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeBieuDoTron().get(1)));
                    paragraph35.setIndentationLeft(80);
                    paragraph35.setIndentationRight(230);
                    paragraph35.setAlignment(Element.ALIGN_LEFT);
                    paragraph36.setIndentationLeft(80);
                    paragraph36.setIndentationRight(230);
                    paragraph36.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph35);
                    document.add(paragraph36);
                } catch (Exception e) {
                    Paragraph paragraph35 = new Paragraph("+ Ten San Pham: " + "khong co");
                    Paragraph paragraph36 = new Paragraph("+ So Luong: 0");
                    paragraph35.setIndentationLeft(80);
                    paragraph35.setIndentationRight(230);
                    paragraph35.setAlignment(Element.ALIGN_LEFT);
                    paragraph36.setIndentationLeft(80);
                    paragraph36.setIndentationRight(230);
                    paragraph36.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph35);
                    document.add(paragraph36);
                }
                document.add(paragraph20);

                try {

                    Paragraph paragraph37 = new Paragraph("+ Ten San Pham: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoTron().get(2)));
                    Paragraph paragraph38 = new Paragraph("+ So Luong: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeBieuDoTron().get(2)));
                    paragraph37.setIndentationLeft(80);
                    paragraph37.setIndentationRight(230);
                    paragraph37.setAlignment(Element.ALIGN_LEFT);
                    paragraph38.setIndentationLeft(80);
                    paragraph38.setIndentationRight(230);
                    paragraph38.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph37);
                    document.add(paragraph38);
                } catch (Exception e) {

                    Paragraph paragraph37 = new Paragraph("+ Ten San Pham: " + "khong co");
                    Paragraph paragraph38 = new Paragraph("+ So Luong: 0");
                    paragraph37.setIndentationLeft(80);
                    paragraph37.setIndentationRight(230);
                    paragraph37.setAlignment(Element.ALIGN_LEFT);
                    paragraph38.setIndentationLeft(80);
                    paragraph38.setIndentationRight(230);
                    paragraph38.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph37);
                    document.add(paragraph38);
                }
                document.add(paragraph21);

                try {
                    Paragraph paragraph39 = new Paragraph("+ Ten San Pham: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoTron().get(3)));
                    Paragraph paragraph40 = new Paragraph("+ So Luong: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeBieuDoTron().get(3)));
                    paragraph39.setIndentationLeft(80);
                    paragraph39.setIndentationRight(230);
                    paragraph39.setAlignment(Element.ALIGN_LEFT);
                    paragraph40.setIndentationLeft(80);
                    paragraph40.setIndentationRight(230);
                    paragraph40.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph39);
                    document.add(paragraph40);
                } catch (Exception e) {
                    Paragraph paragraph39 = new Paragraph("+ Ten San Pham: " + "khong co");
                    Paragraph paragraph40 = new Paragraph("+ So Luong: 0");
                    paragraph39.setIndentationLeft(80);
                    paragraph39.setIndentationRight(230);
                    paragraph39.setAlignment(Element.ALIGN_LEFT);
                    paragraph40.setIndentationLeft(80);
                    paragraph40.setIndentationRight(230);
                    paragraph40.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph39);
                    document.add(paragraph40);
                }
                document.add(paragraph22);
                try {

                    Paragraph paragraph41 = new Paragraph("+ Ten San Pham: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeSachBieuDoTron().get(4)));
                    Paragraph paragraph42 = new Paragraph("+ So Luong: " + String.valueOf(donService.sellectAllHoaDonChiTietsThongKeBieuDoTron().get(4)));
                    paragraph41.setIndentationLeft(80);
                    paragraph41.setIndentationRight(230);
                    paragraph41.setAlignment(Element.ALIGN_LEFT);
                    paragraph42.setIndentationLeft(80);
                    paragraph42.setIndentationRight(230);
                    paragraph42.setAlignment(Element.ALIGN_LEFT);

                    document.add(paragraph41);
                    document.add(paragraph42);
                } catch (Exception e) {

                    Paragraph paragraph41 = new Paragraph("+ Ten San Pham: " + "khong co");
                    Paragraph paragraph42 = new Paragraph("+ So Luong: 0");
                    paragraph41.setIndentationLeft(80);
                    paragraph41.setIndentationRight(230);
                    paragraph41.setAlignment(Element.ALIGN_LEFT);
                    paragraph42.setIndentationLeft(80);
                    paragraph42.setIndentationRight(230);
                    paragraph42.setAlignment(Element.ALIGN_LEFT);
                    document.add(paragraph41);
                    document.add(paragraph42);
                }
                document.add(paragraph23);
                document.add(paragraph5);
                document.add(table1);

                // đóng file
                document.close();

            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BieuDo_Form.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BieuDo_Form.class.getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(this, "In Thanh Cong");
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
