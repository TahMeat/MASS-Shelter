package shelter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Lizard extends Animal{
    // declarations
    private LizardBreed breed;

    // methods
    public Lizard(){ this(LizardBreed.Gecko, "Default", Gender.Male, 0); }

    public Lizard(LizardBreed breed,String name, Gender gender, int age) {
        super(name, gender, age);
        this.breed = breed;
    }

    public Lizard(BufferedReader br) throws IOException{
        super(br);
        this.breed = LizardBreed.valueOf(br.readLine());
    }

    @Override
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw);
        bw.write(breed.name() + "\n");
    }

    @Override
    public void create(Object breed, String name, Gender gender, int age) {
        super.create(breed, name, gender, age);
        this.breed = (LizardBreed) breed;
    }

    @Override
    public String family(){return "Lizard";}

    @Override
    public String breed() {return breed.name();}

    @Override
    public String toString(){
        return super.toString() + " " + breed() + " " + family() + ")";
    }
}
