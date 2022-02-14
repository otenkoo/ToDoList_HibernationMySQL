package com.gen.Mod5Hib;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MenuItems {

	Configuration con = new Configuration().configure().addAnnotatedClass(ToDoList.class);

	SessionFactory sf = con.buildSessionFactory();

	// simple greeting only to be shown at start of application
	public void greeting() {
		System.out.println("Welcome to your To-Do list!");
	}

	// menu display method
	public void menu() {
		System.out.println();
		System.out.println("Please pick an action from below: ");
		System.out.println("   1. Add a task");
		System.out.println("   2. Remove a task");
		System.out.println("   3. Return your list");
		System.out.println("   4. Exit");
		System.out.println();
		System.out.print("Your choice: ");
	}

	// loops menu until exit condition is met
	public void menuLoop() {
		int choice;
		do {
			menu();
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			choice = input.nextInt();
			menuSwitch(choice);
		} while (choice != 4);

	}

	// switch case for user menu options
	public void menuSwitch(int choice) {
		switch (choice) {
		case 1:
			System.out.println();
			System.out.println("Enter a task to add to the list: ");
			String item = input();
			addTask(item);
			break;
		case 2:
			System.out.println();
			System.out.println("Enter task ID to remove from the list: ");
			System.out.print("Your choice: ");
			int item2 = input2();
			deleteTasks(item2);
			break;
		case 3:
			System.out.println();
			listTasks();
			break;
		case 4:
			System.out.println();
			System.out.println("Goodbye!");
			break;
		default:
			System.out.println();
			System.out.println("Invalid choice");
			break;
		}
	}

	// input string method for faster calls. Used in menuSwitch
	public String input() {
		String task;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		task = input.nextLine();
		return task;

	}

	// input int method for faster calls. Used in menuSwitch
	public int input2() {
		int taskNum;
		@SuppressWarnings("resource")
		Scanner userIn = new Scanner(System.in);
		taskNum = userIn.nextInt();
		return taskNum;
	}

	// Method to CREATE a task in the database
	public Integer addTask(String item) {
		Session session = sf.openSession();
		Transaction tx = null;
		Integer taskID = null;

		tx = session.beginTransaction();
		ToDoList tasks = new ToDoList();
		tasks.setTaskDes(item);
		taskID = (Integer) session.save(tasks);
		session.save(tasks);
		tx.commit();

		return taskID;
	}

	// Method to READ all the tasks
	public void listTasks() {
		Session session = sf.openSession();
		Transaction tx = null;

		tx = session.beginTransaction();
		List<?> taskList = session.createQuery("FROM ToDoList").list();
		for (Iterator<?> iterator = taskList.iterator(); iterator.hasNext();) {
			ToDoList tasks = (ToDoList) iterator.next();
			System.out.println(tasks.getTaskID() + ": " + tasks.getTaskDes());
		}
		tx.commit();

	}

	// Method to DELETE a task
	public void deleteTasks(Integer taskID) {
		Session session = sf.openSession();
		Transaction tx = null;

		tx = session.beginTransaction();
		ToDoList tasks = (ToDoList) session.get(ToDoList.class, taskID);
		session.delete(tasks);
		tx.commit();
	}

}
