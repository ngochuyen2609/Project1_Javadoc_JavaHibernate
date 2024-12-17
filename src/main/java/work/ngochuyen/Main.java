package work.ngochuyen;

import org.hibernate.Session;
import org.hibernate.Transaction;
import work.ngochuyen.Entity.Product;
import work.ngochuyen.JavaBasic.Calculator;
import work.ngochuyen.Util.HibernateUtil;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Calculator cal = new Calculator();
        int a = 10;
        int b = 20;
        System.out.println(cal.add(a,b));
        System.out.println(cal.subtract(a,b));

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        //Luu du lieu vao bang
        try {
            transaction = session.beginTransaction();

            // Tạo đối tượng Product và lưu vào DB
            Product product = new Product("Laptop", 1200.0);
            session.save(product);

            transaction.commit();
            System.out.println("Dữ liệu đã được thêm thành công!");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.shutdown();
        }

        //Doc du lieu tu bang
        try {
            Product product = session.get(Product.class, 1L);
            if (product != null) {
                System.out.println("Product: " + product.getName() + ", Price: " + product.getPrice());
            } else {
                System.out.println("Không tìm thấy sản phẩm!");
            }
        } finally {
            session.close();
            HibernateUtil.shutdown();
        }

        //Cap nhat du lieu
        Product product = session.get(Product.class, 1L);
        if (product != null) {
            product.setPrice(1500.0);
            session.update(product);
            transaction.commit();
        }

        //Xoa du lieu
        product = session.get(Product.class, 1L);
        if (product != null) {
            session.delete(product);
            transaction.commit();
        }
    }

}