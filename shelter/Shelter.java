package shelter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Shelter {
	// declarations
	private String name;
	private String filename;
	private ArrayList<Animal> animals;
	private ArrayList<Client> clients;
	private HashMap<Animal, Client> adoptions;

	// methods
	public Shelter(String name){ this.name = name; this.filename = "Untitled.mass";
		this.animals = new ArrayList<>(); this.clients = new ArrayList<>();
		this.adoptions = new HashMap<>();}
	public Shelter(BufferedReader br) throws IOException {
		this.name = br.readLine();
		animals = new ArrayList<>();
		clients = new ArrayList<>();
		adoptions = new HashMap<>();
		int numAnimals = Integer.parseInt(br.readLine());
		while(numAnimals-- > 0) {
			String family = br.readLine();
			if("Guinea Pig".equals(family)) animals.add(new GuineaPig(br));
			else if("Bird".equals(family)) animals.add(new Bird(br));
			else if("Lizard".equals(family)) animals.add(new Lizard(br));
			else throw new IOException("Invalid family: " + family);
		}
		int numClients = Integer.parseInt(br.readLine());
		while(numClients-- > 0) {
			clients.add(new Client(br));
		}
		int numAdoptions = Integer.parseInt(br.readLine());
		while(numAdoptions-- > 0) {
			String family = br.readLine();
			if("Guinea Pig".equals(family)) adoptions.put(new GuineaPig(br), new Client(br));
			else if("Bird".equals(family)) adoptions.put(new Bird(br), new Client(br));
			else if("Lizard".equals(family)) adoptions.put(new Lizard(br), new Client(br));
			else throw new IOException("Invalid family: " + family);
		}
	}
	public void save(BufferedWriter bw) throws IOException {
		bw.write(this.name +"\n");
		bw.write("" + this.animals.size() + "\n");
		for(int i = 0; i != this.animals.size(); i++){
			animals.get(i).save(bw);
		}
		bw.write("" + this.clients.size() + "\n");
		for(int i = 0; i != this.clients.size(); i++){
			clients.get(i).save(bw);
		}
		bw.write("" + this.adoptions.size() + "\n");
		for(Animal a : adoptions.keySet()){
			a.save(bw);
			adoptions.get(a).save(bw);
		}
	}
	public String getFilename() { return filename; }
	public void setFilename(String filename){ this.filename = filename; }

	public void addClient(Client client) {this.clients.add(client);}
	public ListIterator<Client> clientListIterator(){ return clients.listIterator(); }
	public String clientsToString(){
		String result = "";
		for(Client c : clients){
			result += "\n" + c;
		}
		return result;
	}

	public void addAnimal(Animal animal){ animals.add(animal); }
	public ListIterator<Animal> animalListIterator(){ return animals.listIterator(); }
	
	@Override
	public String toString(){
		String result = "";
		for(Animal a : animals){
			result += "\n" + a;
		}
		return result;
	}

	public void adopt(Animal animal, Client client){
		adoptions.put(animal, client);
		animals.remove(animal);
	}

	public ListIterator<Animal> adoptionListIterator(){
		return null;
	} //todo - implement somehow??

	public void getAdoptedClient(Animal animal){ adoptions.get(animal); } // todo - implementation?

	public String adoptionsToString(){
		String result = "";
		for(Animal a : adoptions.keySet()){
			result += "\n" + a + " to " + adoptions.get(a);
		}
		return result;
	}


}