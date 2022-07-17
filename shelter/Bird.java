package shelter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Bird extends Animal{
    // declarations
    private BirdBreed breed;

    // methods
    public Bird(){ this(BirdBreed.Budgerigar, "Default", Gender.Male, 0); }

    public Bird(BirdBreed breed,String name, Gender gender, int age) {
        super(name, gender, age);
        this.breed = breed;
    }

    public Bird(BufferedReader br) throws IOException{
        super(br);
        this.breed = BirdBreed.valueOf(br.readLine());
    }

    @Override
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw);
        bw.write(breed.name() + "\n");
    }

    @Override
    public void create(Object breed, String name, Gender gender, int age) {
        super.create(breed, name, gender, age);
        this.breed = (BirdBreed) breed;
    }

    @Override
    public String family(){return "Bird";}

    @Override
    public String breed() {return breed.name();}

    @Override
    public String toString(){
        return super.toString() + " " + breed() + " " + family() + ")";
    }
}
