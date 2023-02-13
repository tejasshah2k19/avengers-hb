package com;

import java.util.List;
import java.util.Scanner;

import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entity.UserEntity;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Scanner scr = new Scanner(System.in);

		System.out.println("Hello World!");
		int choice;
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");// hibernate -> data-> execute
		SessionFactory sf = cfg.buildSessionFactory();// build
		// Session created using sessionFactory -> sessionFactory will read all
		// confgiuration ->
		// session -> query execute ..

		// insert list delete

		Session session = sf.openSession();
		UserEntity user = new UserEntity();

		System.out.println(session);
		while (true) {
			System.out.println("0. for Exit");
			System.out.println("1. for Insert");
			System.out.println("2. for Get");
			System.out.println("3. for Delete");
			System.out.println("4. for update");
			System.out.println("Enter Your Choice : => ");
			choice = scr.nextInt();

			switch (choice) {

			case 0:
				session.close();
				System.exit(0);

			case 1:
				System.out.println("Enter Your Fname");
				user.setUserName(scr.next());
				System.out.println("Enter Your Email");
				user.setEmail(scr.next());
				System.out.println("Enter Your Password");
				user.setPassword(scr.next());
				session.save(user);
				break;

			case 2:
				javax.persistence.Query query = session.createQuery("from UserEntity");
				List<UserEntity> users = query.getResultList();
				for (UserEntity userEntity : users) {
					System.out.println(userEntity.getUserId());
					System.out.println(userEntity.getUserName());
					System.out.println(userEntity.getEmail());
					System.out.println(userEntity.getPassword());
				}

				break;

			case 3:

				// delete from users where userId = 9
				System.out.println("Enter userId...");
				int userId = scr.nextInt();

				Transaction tx = session.beginTransaction();

				UserEntity user1 = session.get(UserEntity.class, userId); // select * from users where userid = 1 ;
				try {
					session.delete(user1);
				} catch (Exception e) {
					tx.rollback();
				}
				// commit
				tx.commit();
				break;
			case 4:
				System.out.println("Enter userId... For Update");
				int userkey = scr.nextInt();
				UserEntity useR = session.get(UserEntity.class, userkey); // select * from users where userid = 1 ;
				if (useR == null) {
					System.out.println(" User id is Wrong");
				} else {
					Transaction tx1 = session.beginTransaction();
					try {
						// tdl is for transcation we need to commit

						System.out.println("Your UserName is +" + useR.getUserName());
						System.out.println("Enter New UserName or Enter Old One ");
						useR.setUserName(scr.next());
						session.update(useR);
					} catch (Exception e) {
						tx1.commit();
					}
				}
				break;

			default:
				break;
			}

		}

	}

}
