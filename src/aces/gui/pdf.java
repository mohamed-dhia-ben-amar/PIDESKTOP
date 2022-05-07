/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aces.gui;

import aces.entities.Article;
import aces.services.CrudArticles;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
 
/**
 *
 * @author USER
 */

public class pdf {
    
     public void liste_Article() throws FileNotFoundException, DocumentException {

            CrudArticles cc = new CrudArticles(); 
            String message = "\n\n Voici votre liste de vos articles \n\n";


        String file_name = "src/ressources/MyArticles.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
    
        List<Article> ar =cc.afficherArticle();
        System.out.println(ItemController.cid);
        System.out.println(ar);
     
        PdfPTable table = new PdfPTable(2);

        
        
        PdfPCell cl1 = new PdfPCell(new Phrase("Titre"));
        table.addCell(cl1);
        PdfPCell cl = new PdfPCell(new Phrase("Contenu"));
        table.addCell(cl);
    
       
     
        
        
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < ar.size(); i++) {
            
            table.addCell("" + ar.get(i).getTitre());
            table.addCell("" + ar.get(i).getContenu());
          
      

        }
        document.add(table);

        document.close();

    }
}