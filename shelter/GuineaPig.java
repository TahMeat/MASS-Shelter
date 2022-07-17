package shelter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class GuineaPig extends Animal{
    // declarations
    private GuineaPigBreed breed;

    // methods
    public GuineaPig(){ this(GuineaPigBreed.American, "Default", Gender.Male, 0); }

    public GuineaPig(GuineaPigBreed breed, String name, Gender gender, int age) {
        super(name, gender, age);
        this.breed = breed;
    }

    public GuineaPig(BufferedReader br) throws IOException {
        super(br);
        this.breed = GuineaPigBreed.valueOf(br.readLine());
    }

    @Override
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw);
        bw.write(breed.name() + "\n");
    }

    @Override
    public void create(Object breed, String name, Gender gender, int age) {
        super.create(breed, name, gender, age);
        this.breed = (GuineaPigBreed) breed;
    }

    @Override
    public String family(){return "Guinea Pig";}

    @Override
    public String breed() {return breed.name();}

    @Override
    public String toString(){
        return super.toString() + " " + breed() + " " + family() + ")";
    }
}
