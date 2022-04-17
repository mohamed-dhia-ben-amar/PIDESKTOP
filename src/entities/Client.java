/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;

/**
 *
 * @author MSI
 */
public class Client implements Serializable{
    private int idClient,age,phone;
    private String name,prenom,adresse,mail,login,mdp,image;

    public Client() {
    }

    public Client(int age, int phone, String name, String prenom, String adresse, String mail, String login, String mdp, String image) {
        this.age = age;
        this.phone = phone;
        this.name = name;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mail = mail;
        this.login = login;
        this.mdp = mdp;
        this.image = image;
    }

    public Client(int idClient, int age, int phone, String name, String prenom, String adresse, String mail, String login, String mdp, String image) {
        this.idClient = idClient;
        this.age = age;
        this.phone = phone;
        this.name = name;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mail = mail;
        this.login = login;
        this.mdp = mdp;
        this.image = image;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "\n********************" +
                "\nClient :"  
                + " \nID Du Client : " + idClient 
                + " . \nAge Du Client : " + age 
                + " . \nNumero Du Telephone Du Client : " + phone 
                + " . \nNom Du Client : " + name 
                + " . \nPrenom Du Client : " + prenom 
                + " . \nAdresse Du Client : " + adresse 
                + " . \nMail Du Client : " + mail 
                + " . \nImage Du Client : " + image;
    }
    
    
}
