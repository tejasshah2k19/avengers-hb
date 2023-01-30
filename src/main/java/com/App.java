package com;

import java.util.List;
import java.util.Scanner;

import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();

		Session session = sf.openSession();
		UserEntity user = new UserEntity();
		System.out.println(session);

		while (true) {
			System.out.println("0. for Exit");
			System.out.println("1. for Insert");
			System.out.println("2. for Update");
			System.out.println("3. for Delete");
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
				break;

			default:
				break;
			}

		}

	}
	
}
