package shelter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public abstract class Animal {
	// declarations
	protected String name;
	protected Gender gender;
	protected int age;
	
	// methods
	public Animal(String name, Gender gender, int age){
		try{
			// check if age is non-positive
			if(age < 0){throw new IllegalArgumentException("Non-positive age.");}
			else{this.age = age;}
			this.gender = gender;
		} catch (Exception e){
			System.err.println(e.getMessage());
		}
		this.name = name;
	}

	public Animal(BufferedReader br) throws IOException {
		this.name = br.readLine();
		try{
			this.gender = Gender.valueOf(br.readLine());
			// check if age is non-positive
			int age = Integer.parseInt(br.readLine());
			if(age < 0){throw new IllegalArgumentException("Non-positive age.");}
			else{this.age = age;}
		} catch (Exception e){
			System.err.println(e.getMessage());
		}
	}

	public void save(BufferedWriter bw) throws IOException {
		bw.write("" + family() + "\n");
		bw.write("" + this.name + "\n");
		bw.write("" + this.gender + "\n");
		bw.write("" + this.age + "\n");
	}

	public void create(Object breed, String name, Gender gender, int age){
		try{
			// check if age is non-positive
			if(age < 0){throw new IllegalArgumentException("Non-positive age.");}
			else{this.age = age;}
			this.gender = gender;
		} catch (Exception e){
			System.err.println(e.getMessage());
		}
		this.name = name;
	}

	public abstract String family();
	public abstract String breed();

	public String toString() {
		return name + " (" + age + " year old " + gender;
	}
}