package main;

import java.util.Arrays;

public class App {

	public static void main(String[] args) {
		UserRepo repo = new FirstUserRepo();
		System.out.println(repo.read());
		repo.create(Arrays.asList(new User(1, 44, "F", 94, "4494"), new User(2, 94, "F", 44, "9444")));
		System.out.println(repo.read());
	}

}
