package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Ticketmodel;


public class FXMLDocumentController1 implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    private Label label;
    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
                
        
    }


    @FXML
    private Button buttonCreateTicket;

    @FXML
    private Button buttonRead;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonDelete;

    @FXML
    void create(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        System.out.println("Enter Name:");
        String name = input.next();
        
        System.out.println("Enter Price:");
        double price = input.nextDouble();
        
        System.out.println("purchased?: (true or false)");
        boolean purchased = input.nextBoolean();
        
        // create a student instance
        Ticketmodel ticketmodel = new Ticketmodel();
        
        // set properties
        ticketmodel.setId(id);
        ticketmodel.setName(name);
        ticketmodel.setPrice(price);
        ticketmodel.setPurchased(purchased);
        
        // save this ticket to databse by calling Create operation        
        create(ticketmodel);
    }

    @FXML
    void delete(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
         // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        Ticketmodel t = readById(id);
        System.out.println("Deleting ticket: "+ t.toString());
        delete(t);

    }

    @FXML
    void readAll(ActionEvent event) {
    Scanner input = new Scanner(System.in);
        
        // read input from command line
        
        List<Ticketmodel> t = readAll();
        System.out.println(t.toString());

    }

    @FXML
    void update(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        System.out.println("Enter Name:");
        String name = input.next();
        
        System.out.println("Enter Price:");
        double price = input.nextDouble();
        
        System.out.println("purchased?: (true or false)");
        boolean purchased = input.nextBoolean();
        
        // create a student instance
        Ticketmodel ticketmodel = new Ticketmodel();
        
        // set properties
        ticketmodel.setId(id);
        ticketmodel.setName(name);
        ticketmodel.setPrice(price);
        ticketmodel.setPurchased(purchased);
        
        // save this ticket to databse by calling update operation        
        update(ticketmodel);

    }

    

    
     // Database manager
    EntityManager manager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        // loading data from database
        //database reference: "IntroJavaFXPU"
        manager = (EntityManager) Persistence.createEntityManagerFactory("MauricioLitvakFXMLPU").createEntityManager();
    }
    
    public void create(Ticketmodel ticketmodel) {
        try {
            // begin transaction
            manager.getTransaction().begin();
            
            // sanity check
            if (ticketmodel.getId() != null) {
                
                // create student
                manager.persist(ticketmodel);
                
                // end transaction
                manager.getTransaction().commit();
                
                System.out.println(ticketmodel.toString() + " is created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Ticketmodel> readAll(){
        Query query = manager.createNamedQuery("Ticketmodel.findAll");
        List<Ticketmodel> ticketmodel = query.getResultList();
        ticketmodel.forEach((t) -> {
            System.out.println("" + t.getId() + "   " + t.getName() + "    $" + t.getPrice() + "    " + t.getPurchased() + " ");
        });
        
        return ticketmodel;
    }
    
    public void update(Ticketmodel model) {
        try {

            Ticketmodel existingStudent = manager.find(Ticketmodel.class, model.getId());

            if (existingStudent != null) {
                // begin transaction
                manager.getTransaction().begin();
                
                // update all atttributes
                existingStudent.setName(model.getName());
                existingStudent.setPrice(model.getPrice());
                existingStudent.setPurchased(model.getPurchased());
                
                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void delete(Ticketmodel ticketmodel) {
        try {
            Ticketmodel existingTicket = manager.find(Ticketmodel.class, ticketmodel.getId());

            // sanity check
            if (existingTicket != null) {
                
                // begin transaction
                manager.getTransaction().begin();
                
                //remove student
                manager.remove(existingTicket);
                
                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Ticketmodel readById(int id){
        Query query = manager.createNamedQuery("Ticketmodel.findById");
        
        // setting query parameter
        query.setParameter("id", id);
        
        // execute query
        Ticketmodel ticketmodel = (Ticketmodel) query.getSingleResult();
        if (ticketmodel != null) {
            System.out.println(ticketmodel.getId() + " " + ticketmodel.getName() + " " + ticketmodel.getPrice() + " " + ticketmodel.getPurchased());
        }
        
        return ticketmodel;
    }     
}
