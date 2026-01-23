package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Locale locale = new Locale("en-IND");
		Faker faker = new Faker(locale);
		String first_Name = faker.name().firstName();
		String last_Name = faker.name().lastName();
		System.out.println(first_Name+" "+last_Name);
		
		System.out.println(faker.address().buildingNumber());
		System.out.println(faker.address().streetAddress());
		System.out.println(faker.address().city());
		
		
		System.out.println(faker.number().digits(9));
		System.out.println(faker.numerify("987#######"));
		System.out.println(faker.numerify("987#######"));
		
		
		
	}

}
