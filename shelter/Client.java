package shelter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Client {
    private String name;
    private String phone;

    public Client(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    public Client(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.phone = br.readLine();
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write("" + this.name + "\n");
        bw.write("" + this.phone + "\n");
    }

    @Override
    public String toString() {
        return name + " (" + phone + ")";
    }

}
