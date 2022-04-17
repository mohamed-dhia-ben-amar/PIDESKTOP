/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import com.mysql.cj.protocol.Message;
import com.mysql.cj.xdevapi.Session;
import desktopfxmlpidev.DesktopFXMLPiDev;
import entities.Client;
import entities.SendMail;
import entities.Reclamation;
import entities.Reparation;
import entities.SendMail;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ClientService;
import services.ReclamationService;
import services.ReparationService;
import sun.plugin2.message.transport.Transport;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private TextField tfDesc;
    @FXML
    private ComboBox<String> targetCB;
    @FXML
    private ComboBox<String> methodCB;
    @FXML
    private Button saveBTN;
    @FXML
    private Button clearBTN;

    private Stage stage;
    private Scene scene;
    private Parent root;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Reclamation reclamation = null;
    private boolean update;
    int reclamationid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        targetCB.getItems().addAll(
                "Product", "Tournament", "Deliverer");
        methodCB.getItems().addAll(
                "Reparation", "Refund", "Change");
    }

    @FXML
    private void saveRec(ActionEvent event) throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wlhfinal", "root", "");
        String desc = tfDesc.getText();
        String target = targetCB.getValue();
        String method = methodCB.getValue();

        if (desc.isEmpty() || target.isEmpty() || method.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clearForm();
            DesktopFXMLPiDev m = new DesktopFXMLPiDev();
            root = FXMLLoader.load(getClass().getResource("../gui/FrontAfficher.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    @FXML
    private void clearForm() {
        tfDesc.setText(null);
        targetCB.setValue(null);
        methodCB.setValue(null);
    }

    void setUpdate(boolean b) {
        this.update = b;
    }

    void setTextField(String description) {
        tfDesc.setText(description);
    }

    private void getQuery() {

        System.out.println(reclamationid);

        if (update == false) {

            query = "insert into Reclamation(description,date,etat,"
                    + "method_remb,target,idClient,idRep) "
                    + "VALUES (?,?,?,?,?,?,?)";

        } else {
            query = "update Reclamation"
                    + " set description = ?, "
                    + "date = ?, "
                    + "etat = ?, "
                    + "method_remb = ?, "
                    + "target = ?, "
                    + "idClient = ?, "
                    + "idRep = ? "
                    + "where idRec = " + reclamationid + "";
        }

    }

    private void insert() throws SQLException, IOException {

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wlhfinal", "root", "");
            //Instansation de service 
            ReclamationService rs = new ReclamationService();
            ReparationService reps = new ReparationService();
            ClientService cs = new ClientService();
            //Instansation d'un client
            Client cl = new Client(22, 53140939, "dhia", "amar", "mnihla", "dhia@gmail.com", "admin", "admin", "image/dhia.png");
            //Instansation d'une reparation
            Reparation rep = new Reparation(java.time.LocalDate.now().toString());
            //Ajouter Client
            int idclient = cs.ajouter(cl);
            cl.setIdClient(idclient); //Recuperation de l'ID CLient
            //Ajouter Reparation
            int idrep = reps.ajouter(new Reclamation(), rep, cl);
            rep.setIdRep(idrep); //Recuperation de l'ID Reparation

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Complaint");
            alert.setHeaderText("Results:");
            alert.setContentText("Complaint added successfully!");
            alert.showAndWait();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfDesc.getText());
            preparedStatement.setString(2, java.time.LocalDate.now().toString());
            preparedStatement.setString(3, "Pending");
            preparedStatement.setString(4, methodCB.getValue());
            preparedStatement.setString(5, targetCB.getValue());
            preparedStatement.setObject(6, cl.getIdClient());
            preparedStatement.setObject(7, rep.getIdRep());
            preparedStatement.executeUpdate();

            // Create some properties and get the default Session;
            final String username = "mohameddhia.benamar@esprit.tn";
            final String password = "191JMT0647";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            javax.mail.Session session = javax.mail.Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(username, password);
                }
            });

            // Create a message.
            MimeMessage msg = new MimeMessage(session);

            // extracts the senders and adds them to the message.
            // Sender is a comma-separated list of e-mail addresses as per RFC822.
            {
                InternetAddress[] TheAddresses = InternetAddress.parse("mohameddhia.benamar@esprit.tn");
                msg.addFrom(TheAddresses);
            }

            // Extract the recipients and assign them to the message.
            // Recipient is a comma-separated list of e-mail addresses as per RFC822.
            {
                InternetAddress[] TheAddresses = InternetAddress.parse("Rawaa.blh@gmail.com");
                msg.addRecipients(javax.mail.Message.RecipientType.TO, TheAddresses);
            }

            // Subject field
            msg.setSubject("Adding Complaint");

            // Create the Multipart to be added the parts to
            Multipart mp = new MimeMultipart();

            // Create and fill the first message part
            {
                MimeBodyPart mbp = new MimeBodyPart();
                mbp.setText("Complaint Added Successfully");

                // Attach the part to the multipart;
                mp.addBodyPart(mbp);
            }
            String Attachments = null;

            // Attach the files to the message
            if (null != Attachments) {
                int StartIndex = 0, PosIndex = 0;
                while (-1 != (PosIndex = Attachments.indexOf("///", StartIndex))) {
                    // Create and fill other message parts;
                    MimeBodyPart mbp = new MimeBodyPart();
                    FileDataSource fds
                            = new FileDataSource(Attachments.substring(StartIndex, PosIndex));
                    mbp.setDataHandler(new DataHandler(fds));
                    mbp.setFileName(fds.getName());
                    mp.addBodyPart(mbp);
                    PosIndex += 3;
                    StartIndex = PosIndex;
                }
                // Last, or only, attachment file;
                if (StartIndex < Attachments.length()) {
                    MimeBodyPart mbp = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(Attachments.substring(StartIndex));
                    mbp.setDataHandler(new DataHandler(fds));
                    mbp.setFileName(fds.getName());
                    mp.addBodyPart(mbp);
                }
            }

            // Add the Multipart to the message
            msg.setContent(mp);

            // Set the Date: header
            msg.setSentDate(new Date());

            // Send the message;
            javax.mail.Transport.send(msg);
        } catch (MessagingException MsgException) {
            System.out.println("blows here");
            String ErrorMessage = MsgException.toString();
            Exception TheException = null;
            if ((TheException = MsgException.getNextException()) != null) {
                ErrorMessage += "\n" + TheException.toString();
            }
            int ErrorStatus = 1;

        }

    }

}
