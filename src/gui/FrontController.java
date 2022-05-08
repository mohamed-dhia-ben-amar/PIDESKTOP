/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import Test.Sms;
import entities.Livraison;
import javax.mail.PasswordAuthentication;
import entities.Livreur;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import services.ServiceLivreur;
import javafx.scene.image.Image;
import Test.MyListener;
import desktopfxmlpidev.DesktopFXMLPiDev;
import javafx.scene.control.Alert;
import services.ServiceLivraison;
import java.io.File;
import java.util.Properties;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FrontController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private VBox chosenplat;
    @FXML
    private Label prenom;

    @FXML
    private Label zone;

    @FXML
    private Button but;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    private List<Livreur> listArt = new ArrayList<>();
    private MyListener myListener;
    Livreur liv = new Livreur();
    @FXML
    private ImageView img;
    @FXML
    private TextField tfMethod;
    @FXML
    private TextField tfIdClient;
    @FXML
    private TextField tfIdProd;
    @FXML
    private TextField tfAdresse;
    @FXML
    private Label error;
    ServiceLivraison sl = new ServiceLivraison();
    @FXML
    private Label cin;
    Random rn = new Random();
    int randomNumber = rn.nextInt(3) + 1;
    @FXML
    private ImageView cap;
    @FXML
    private TextField tfcap;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showScreen();
        Image im = new Image(this.getClass().getResourceAsStream("/captcha/" + "" + randomNumber + ".png"));
        cap.setFitHeight(150);
        cap.setFitWidth(150);
        cap.setImage(im);
        System.out.println(randomNumber);
    }

    private void setChosenPlat(Livreur livreur) {

        String path = "/img/" + livreur.getImage();
        Image image = new Image(getClass().getResourceAsStream(path));
        System.out.println("test");

        System.out.println("test1");
        prenom.setText(livreur.getPrenom());
        zone.setText(livreur.getZone());
        img.setImage(image);
        cin.setText("" + livreur.getCin());
        System.out.println("test2");

        chosenplat.setStyle("-fx-background-radius: 30;");
    }

    public void showScreen() {
        ServiceLivreur sl = new ServiceLivreur();
        listArt.addAll(sl.recuperer());

        if (listArt.size() > 0) {
            setChosenPlat(listArt.get(0));
            System.out.println(listArt.get(0).getPrenom());
            myListener = new MyListener() {
                @Override
                public void onClickListener(Livreur nomp) {
                    setChosenPlat(nomp);
                    liv.setCin(nomp.getCin());
                    liv.setName(nomp.getName());
                    liv.setPrenom(nomp.getPrenom());
                    liv.setDate_naissance(nomp.getDate_naissance());
                    liv.setImage(nomp.getImage());
                    liv.setRating(nomp.getRating());
                    liv.setZone(nomp.getZone());
                    liv.setTel(nomp.getTel());
                    System.out.println("Coach get" + liv);

                }

            };
        }

        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/LivreurItem.fxml"));
                AnchorPane abc = fxmlLoader.load();
                ItemController_1 platController = fxmlLoader.getController();
                platController.setData(listArt.get(i), myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(abc, column++, row); //(child,column,row)
                //set grid width

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(abc, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ajouterLivraison(ActionEvent event) throws IOException {

        if (tfMethod.getText().isEmpty() || tfAdresse.getText().isEmpty() || tfIdClient.getText().isEmpty() || tfIdProd.getText().isEmpty()) {
            error.setText("Verifier les entrées s'il vous plait");
        } else {// FIXME: change the id user from 1 to the current logged in user.

            String s = "" + randomNumber + ".png";

            System.out.println(randomNumber);
            System.out.println("nabil");
            Tesseract tesseract = new Tesseract();
            System.out.println("hzzae");

            try {
                System.out.println("uu");
                String path = "C:\\Users\\MSI\\Desktop\\wetransfer_tess4j_2022-05-08_1344\\Tess4J\\Tess4J\\tessdata";
                tesseract.setDatapath(path);
                System.out.println("oooo");
                System.out.println(s);
                String te = tesseract.doOCR(new File("C:\\Users\\MSI\\Documents\\NetBeansProjects\\DesktopFXMLPiDev\\src\\captcha\\" + s));
                System.out.println(te);
                System.out.print(te);

                System.out.println("DONE");
                String input = tfcap.getText();
                String in = te.replaceAll("\\s+", "");
                if (input.equals(in)) {
                    sl.ajouter(new Livraison(tfMethod.getText(), (Integer.parseInt(cin.getText())), Integer.parseInt(tfIdClient.getText()), Integer.parseInt(tfIdProd.getText()), tfAdresse.getText(), "en cour"));
                    Sms.nabil("Une Livraison a été ajouté:\n methode de paiment : " + tfMethod.getText() + "\nid du produit : " + tfIdProd.getText() + "\n id client : " + tfIdClient.getText() + "\nadresse du client : " + tfAdresse.getText() + "\nCin livreur : " + cin.getText() + "\nZone : " + zone.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    sendEmail();
                    alert.setTitle("Livraison added");
                    alert.setContentText("Livraison added succesfuuly!");
                } else {
                    System.out.println("wrong");

                }
            } catch (TesseractException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void sendEmail() {
        String to = "mednabil.kallel@esprit.tn";
        String from = "hamatalbi9921@gmail.com";
        String host = "smtp.gmail.com";
        final String username = "hamatalbi9921@gmail.com";
        final String password = "123456789hama";

        //setup mail server
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject("TheAces");
            m.setText("Une Livraison a été ajouté:\n methode de paiment : " + tfMethod.getText() + "\nid du produit : " + tfIdProd.getText() + "\n id client : " + tfIdClient.getText() + "\nadresse du client : " + tfAdresse.getText() + "\nCin livreur : " + cin.getText() + "\nZone : " + zone.getText());

            Transport.send(m);

            System.out.println("Message sent!");

        } catch (MessagingException e) {
            e.printStackTrace();
//        } catch (SQLException ex) {
//            Logger.getLogger(RestPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void LogOut(MouseEvent event) throws IOException {
        DesktopFXMLPiDev m = new DesktopFXMLPiDev();
        root = FXMLLoader.load(getClass().getResource("../gui/Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
