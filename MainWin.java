import javax.swing.*;

import shelter.*;
import java.io.*;

import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class MainWin extends JFrame {
    private Shelter shelter;
    private JLabel data;
    private enum DataView{ANIMALS, CLIENTS, ADOPTIONS}

    public MainWin(String title){
        super(title);
        this.shelter = new Shelter(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set layout and size
        setSize(675, 400);

        // set menu bar.
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem newShelter = new JMenuItem("New Shelter");
        JMenuItem openShelter = new JMenuItem("Open Shelter");
        JMenuItem saveShelter = new JMenuItem("Save Shelter");
        JMenuItem saveShelterAs = new JMenuItem("Save Shelter As");
        JMenuItem quit = new JMenuItem("Quit");

        JMenu animal = new JMenu("Animal");
        JMenuItem newGPig = new JMenuItem("New Guinea Pig");
        JMenuItem newBird = new JMenuItem("New Bird");
        JMenuItem newLizard = new JMenuItem("New Lizard");
        JMenuItem listAnimals = new JMenuItem("List Available");
        JMenuItem listAdopted = new JMenuItem("List Adopted");

        JMenu client = new JMenu("Client");
        JMenuItem newClient = new JMenuItem("New Client");
        JMenuItem listClients = new JMenuItem("List Clients");
        JMenuItem adoptAnimal = new JMenuItem("Adopt Animal");

        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");

        //action-listeners
        newShelter.addActionListener(event -> onNewShelterClick());
        openShelter.addActionListener(event -> onOpenShelterClick());
        saveShelter.addActionListener(event -> onSaveShelterClick());
        saveShelterAs.addActionListener(event -> onSaveShelterAsClick());
        quit.addActionListener(event -> onQuitClick());
        newClient.addActionListener(event -> onNewClientClick());
        newGPig.addActionListener(event -> onNewGPigClick());
        newBird.addActionListener(event -> onNewBirdClick());
        newLizard.addActionListener(event -> onNewLizardClick());
        about.addActionListener(event -> onAboutClick());
        adoptAnimal.addActionListener(event -> onAdoptAnimalClick());

        listAnimals.addActionListener(event -> updateDisplay(DataView.ANIMALS));
        listClients.addActionListener(event -> updateDisplay(DataView.CLIENTS));
        listAdopted.addActionListener(event -> updateDisplay(DataView.ADOPTIONS));

        //add to menus
        menu.add(file); file.add(newShelter); file.add(openShelter);
        file.add(saveShelter); file.add(saveShelterAs); file.add(quit);
        menu.add(animal); animal.add(newGPig); animal.add(newBird); animal.add(newLizard); animal.add(listAnimals);
        animal.add(listAdopted); menu.add(client);
        client.add(newClient); client.add(listClients); client.add(adoptAnimal);
        menu.add(help); help.add(about);
        setJMenuBar(menu);
        // ===============================

        // Toolbar
        JToolBar toolbar = new JToolBar("Commands");

        // Animals
        JButton anewGP = new JButton(new ImageIcon("newgpig.png"));
        anewGP.setActionCommand("New Guinea Pig");
        anewGP.setToolTipText("Adds a new Guinea Pig to the shelter!");

        JButton anewBird = new JButton(new ImageIcon("newbird.png"));
        anewBird.setActionCommand("New Bird");
        anewBird.setToolTipText("Adds a new Bird to the shelter!");

        JButton anewLizard = new JButton(new ImageIcon("newlizard.png"));
        anewLizard.setActionCommand("New Lizard");
        anewLizard.setToolTipText("Adds a new Lizard to the shelter!");

        JButton alistAnimals = new JButton(new ImageIcon("listanimal.png"));
        alistAnimals.setActionCommand("List Animals");
        alistAnimals.setToolTipText("List all animals.");

        // Client
        JButton anewClient = new JButton(new ImageIcon("newclient.png"));
        anewClient.setActionCommand("New Client");
        anewClient.setToolTipText("Adds a client to the shelter list.");

        JButton alistClient = new JButton(new ImageIcon("listclient.png"));
        alistClient.setActionCommand("List Clients");
        alistClient.setToolTipText("List all clients.");

        // Adoption
        JButton anewAdopt = new JButton(new ImageIcon("newadopt.png"));
        anewAdopt.setActionCommand("New Adoption");
        anewAdopt.setToolTipText("Adopt a new pet!");

        JButton alistAdopted = new JButton(new ImageIcon("listadopted.png"));
        alistAdopted.setActionCommand("List Adopted");
        alistAdopted.setToolTipText("List all adopted animals.");

        // File tools
        JButton anewFile = new JButton(new ImageIcon("newfile.png"));
        anewFile.setActionCommand("New File");
        anewFile.setToolTipText("Wipes the current shelter to replace with a new one.");

        JButton loadFile = new JButton(new ImageIcon("load.png"));
        loadFile.setActionCommand("Load File");
        loadFile.setToolTipText("Loads a user-selected file to the program.");

        JButton saveFile = new JButton(new ImageIcon("save.png"));
        saveFile.setActionCommand("Save File");
        saveFile.setToolTipText("Saves the current shelter to a file.");

        JButton saveAsFile = new JButton(new ImageIcon("saveas.png"));
        saveAsFile.setActionCommand("Save As File");
        saveAsFile.setToolTipText("Saves the current shelter to as a specific file.");

        //file tools
        toolbar.add(anewFile); toolbar.add(Box.createHorizontalStrut(3));
        toolbar.add(loadFile); toolbar.add(Box.createHorizontalStrut(3));
        toolbar.add(saveFile); toolbar.add(Box.createHorizontalStrut(3));
        toolbar.add(saveAsFile); toolbar.add(Box.createHorizontalStrut(3));
        anewFile.addActionListener(event -> onNewShelterClick());
        loadFile.addActionListener(event -> onOpenShelterClick());
        saveFile.addActionListener(event -> onSaveShelterClick());
        saveAsFile.addActionListener(event -> onSaveShelterAsClick());

        //animals
        toolbar.add(Box.createHorizontalStrut(25));
        toolbar.add(anewGP);
        toolbar.add(Box.createHorizontalStrut(3));
        toolbar.add(anewBird);
        toolbar.add(Box.createHorizontalStrut(3));
        toolbar.add(anewLizard);
        toolbar.add(Box.createHorizontalStrut(3));
        toolbar.add(alistAnimals);
        anewGP.addActionListener(event -> onNewGPigClick());
        anewBird.addActionListener(event -> onNewBirdClick());
        anewLizard.addActionListener(event -> onNewLizardClick());
        alistAnimals.addActionListener(event -> updateDisplay(DataView.ANIMALS));

        //clients
        toolbar.add(Box.createHorizontalStrut(25));
        toolbar.add(anewClient);
        toolbar.add(Box.createHorizontalStrut(3));
        toolbar.add(alistClient);
        anewClient.addActionListener(event -> onNewClientClick());
        alistClient.addActionListener(event -> updateDisplay(DataView.CLIENTS));

        //animals
        toolbar.add(Box.createHorizontalStrut(25));
        toolbar.add(anewAdopt);
        toolbar.add(Box.createHorizontalStrut(3));
        toolbar.add(alistAdopted);
        anewAdopt.addActionListener(event -> onAdoptAnimalClick());
        alistAdopted.addActionListener(event -> updateDisplay(DataView.ADOPTIONS));

        getContentPane().add(toolbar, BorderLayout.PAGE_START);
        // ===============================

        // Display Data
        this.data = new JLabel();
        getContentPane().add(data, JLabel.NORTH);
        data.setFont(new Font("Arial", Font.BOLD, 20));

        // ===============================

        // Set display to center of screen and make visible.
        setLocationRelativeTo(null);
        setVisible(true);
        // ===============================

    }

    // ===============================

    public void onNewShelterClick(){
        // Get shelter's name
        JLabel name = new JLabel("<HTML><br/>Name</HTML>");
        JTextField names = new JTextField(20);
        // ===============================

        // Display
        Object[] objects = {
                name,   names};
        int button = JOptionPane.showConfirmDialog(
                    this,
                    objects,
                    "Shelter Name",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
        // get data
        if(button == JOptionPane.OK_OPTION){
            this.setTitle(names.getText());
            this.shelter = new Shelter(names.getText());
            shelter.setFilename("Untitled.mass");
            updateDisplay(DataView.ANIMALS);
        }
    }

    public void onOpenShelterClick(){
        JFileChooser fc = new JFileChooser(shelter.getFilename());
        FileFilter massFile = new FileNameExtensionFilter("MASS shelter files", "mass");
        fc.addChoosableFileFilter(massFile);
        fc.setFileFilter(massFile);
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File filename = fc.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                br.mark(1000);
                this.setTitle(br.readLine());
                br.reset(); // resets to get title again
                shelter = new Shelter(br);
                shelter.setFilename(filename.getName());
                updateDisplay(DataView.ANIMALS);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Unable to open " + shelter.getFilename() + '\n' + e,
                        "Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void onSaveShelterClick(){
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(shelter.getFilename()))) {
        shelter.save(bw);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Unable to open " + shelter.getFilename() + '\n' + e, "Failed",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onSaveShelterAsClick(){
        final JFileChooser fc = new JFileChooser();
        FileFilter massFile = new FileNameExtensionFilter("MASS shelter files", "mass");
        fc.addChoosableFileFilter(massFile);
        fc.setFileFilter(massFile);

        int result = fc.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File filename = fc.getSelectedFile();
            this.shelter.setFilename(filename.getAbsolutePath());
            if(!filename.getAbsolutePath().endsWith(".mass")) {
                filename = new File(filename.getAbsolutePath() + ".mass");
                this.shelter.setFilename(filename.getAbsolutePath());
            }
            onSaveShelterClick();
        }
    }

    // ===============================
    public <T extends Animal> void newAnimal(T animal, JComboBox breed){
        // Generic Label
        JLabel breedname = new JLabel("<HTML><br/>Breed</HTML>");

        // Get generic's name
        JLabel name = new JLabel("<HTML><br/>Name</HTML>");
        JTextField names = new JTextField(20);
        // ===============================

        // Get gender
        JLabel gender = new JLabel("<HTML><br/>Gender</HTML>");
        JComboBox<Gender> genders = new JComboBox<>(Gender.values());
        // ===============================

        // Get age
        JLabel age = new JLabel("<HTML><br/>Age</HTML>");
        SpinnerModel range = new SpinnerNumberModel(0, 0, 100, 1);
        JSpinner ages = new JSpinner(range);
        // ===============================
        // Selective decide what image to represent
        String familyName;
        if(animal.family().equals("Guinea Pig")){
            familyName = "newgpig.png";
        }else if(animal.family().equals("Bird")){
            familyName = "newbird.png";
        }else{ familyName = "newlizard.png"; }
        // Display
        Object[] objects = {
                breedname, breed,
                name,   names,
                gender, genders,
                age,    ages};
        int button = JOptionPane.showConfirmDialog(
                this,
                objects,
                "New " + animal.family(),
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                new ImageIcon(familyName));
        // get data
        if(button == JOptionPane.OK_OPTION){
            animal.create( breed.getSelectedItem(), names.getText(), (Gender)genders.getSelectedItem(), (Integer) ages.getValue() );
            shelter.addAnimal(animal);
            updateDisplay(DataView.ANIMALS);
        }
    }
    // ===============================

    public void onNewGPigClick(){ newAnimal(new GuineaPig(), new JComboBox(GuineaPigBreed.values())); }

    public void onNewBirdClick() { newAnimal(new Bird(), new JComboBox(BirdBreed.values())); }

    public void onNewLizardClick() { newAnimal(new Lizard(), new JComboBox(LizardBreed.values())); }

    public void onNewClientClick(){
        // Get client's name
        JLabel name = new JLabel("<HTML><br/>Name</HTML>");
        JTextField names = new JTextField(20);
        // ===============================
        // Get client's number
        JLabel number = new JLabel("<HTML><br/>Phone</HTML>");
        JTextField pnumber = new JTextField(20);
        // ===============================

        // Display
        Object[] objects = {
                name,   names,
                number,    pnumber};
        int button = JOptionPane.showConfirmDialog(
                this,
                objects,
                "New Client",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                new ImageIcon("newclient.png"));
        // get data
        if(button == JOptionPane.OK_OPTION){
            Client client = new Client(names.getText(), pnumber.getText());
            shelter.addClient(client);
            updateDisplay(DataView.CLIENTS);
        }
    }

    public void onAdoptAnimalClick(){
        // ===============================
        // Get animal for adoption
        JLabel animal = new JLabel("<HTML><br/>Animal</HTML>");
        ArrayList<Object> ani = new ArrayList<>();
        Iterator aniIterator = shelter.animalListIterator();
        while (aniIterator.hasNext()) {
            ani.add(aniIterator.next());
        }
        JComboBox<?> animals = new JComboBox<>(ani.toArray());

        // ===============================
        // Get client
        JLabel client = new JLabel("<HTML><br/>Client</HTML>");
        ArrayList<Object> cli = new ArrayList<>();
        Iterator cliIterator = shelter.clientListIterator();
        while (cliIterator.hasNext()) {
            cli.add(cliIterator.next());
        }
        JComboBox<?> clients = new JComboBox<>(cli.toArray());

        // ===============================

        // Display
        if (animals.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "No animals available to adopt!", "Failed", JOptionPane.ERROR_MESSAGE);
        }else if(clients.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "No clients available!", "Failed", JOptionPane.ERROR_MESSAGE);
        }else{
            Object[] objects = {
                    animal, animals,
                    client, clients};
            int button = JOptionPane.showConfirmDialog(
                    this,
                    objects,
                    "Adopt",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("newadopt.png"));
            // get data
            if (button == JOptionPane.OK_OPTION) {
                shelter.adopt((Animal) animals.getSelectedItem(), (Client) clients.getSelectedItem());
                updateDisplay(DataView.ADOPTIONS);
            }
        }
    }

    public void onAboutClick(){
        // Create dialog
        JDialog about = new JDialog();
        about.setLayout(new FlowLayout());
        about.setTitle("Mav's Animal Shelter Software");
        JLabel pig = new JLabel(new ImageIcon("creditguinea.jpg"));
        about.add(pig);
        JLabel bird = new JLabel(new ImageIcon("creditparrot.jpg"));
        about.add(bird);
        JLabel leezard = new JLabel(new ImageIcon("creditleezard.jpg"));
        about.add(leezard);


        JLabel title = new JLabel("<html>"
                + "<p><font size=+4>MASS</font></p>\n"
                + "Mav's Animal Shelter Software"
                + "</html>");
        about.add(title, BorderLayout.CENTER);

        JLabel artists = new JLabel("<html>"
                + "</br>\n"
                + "<p>Version 1.0</p>"
                + "<p>Copyright 2022 by Ron Nguyen</p>"
                + "<p>Licensed under Gnu GPL 3.0</p>"
                + "<p><a href=\"https://www.freepik.com/photos/cockatiel\" title=\"Bird\">"
                + "Bird</a>, "
                + "<a href=\"https://www.freepik.com/photos/hamster\" title=\"Guinea Pig\">"
                + "Guinea Pig</a> -- Logos created by wirestock, used under the Freepik License</p><p>"
                + "<a href=\"https://www.freepik.com/photos/chameleon\" title=\"Lizard\">"
                + "Lizard</a> -- Chameleon photo created by kuritafsheen77 - www.freepik.com</p><p></p>"
                + "<p><a href=\"https://www.flaticon.com/free-icons/rodent\" title=\"Rodent icons\">"
                + "Rodent icons created by Freepik - Flaticon</a></p>"
                + "<p><a href=\"https://www.flaticon.com/free-icons/bird\" title=\"bird icons\">"
                + "Bird icons created by Freepik - Flaticon</a></p>"
                + "<p><a href=\"https://www.flaticon.com/free-icons/lizard\" title=\"lizard icons\">"
                + "Lizard icons created by Freepik - Flaticon</a></p>"
                + "<p><a href=\"https://www.flaticon.com/free-icons/dog\" title=\"dog icons\">"
                + "Dog icons created by mynamepong - Flaticon</a></p>"
                + "<p><a href=\"https://www.flaticon.com/free-icons/man\" title=\"man icons\">"
                + "Man icons created by Freepik - Flaticon</a></p>"


                + "<p></p><p><a href=\"https://www.flaticon.com/free-icons/files-and-folders\" \" title=\"File Icon\">"
                + "Files and folders icons created by Freepik - Flaticon</a></p>"
                + "<p><a href=\"https://www.flaticon.com/free-icons/download\" title=\"Load File Icon\">"
                + "Download icons created by Freepik - Flaticon</a></p>"
                + "<p><a href=\"https://www.flaticon.com/free-icons/upload\" title=\"bird icons\">"
                + "Upload icons created by Freepik - Flaticon</a></p>"
                 + "<p><a href=\"https://www.flaticon.com/free-icons/folder\" title=\"bird icons\">"
                + "Folder icons created by Freepik - Flaticon</a></p>");
        about.add(artists, BorderLayout.CENTER);
        JButton ok = new JButton("OK");
        ok.addActionListener(event -> about.setVisible(false));
        about.add(ok, BorderLayout.PAGE_END);

        about.setSize(750,600);
        about.setLocationRelativeTo(null);
        about.setVisible(true);
    }

    public void onQuitClick(){ System.exit(0); }

    // ===============================

    private void updateDisplay(DataView view){
        if(view == DataView.valueOf("ANIMALS")) {
            data.setText("<html>" + shelter.toString()
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\n", "<br/>")
                    + "</html>");
        }else if(view == DataView.valueOf("CLIENTS")){
            data.setText("<html>" + shelter.clientsToString()
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\n", "<br/>")
                    + "</html>");
        }else{
            data.setText("<html>" + shelter.adoptionsToString()
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\n", "<br/>")
                    + "</html>");
        }
    }

}
