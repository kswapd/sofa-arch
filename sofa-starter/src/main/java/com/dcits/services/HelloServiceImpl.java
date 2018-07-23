package com.dcits.services;

/**
 * Created by kongxiangwen on 7/23/18 w:30.
 */
public class HelloServiceImpl implements HelloService {

	public String sayHello(String string) {
		System.out.println("Server receive: " + string);
		return "hello " + string + " ！";
	}
}
