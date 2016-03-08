package com.main;

import com.jdbc.Dao;

public class Main {
	public static void main(String[] args) {
		Dao util=new Dao();
		Thread t1 = new Thread(util);
		t1.start();
	}
}	
