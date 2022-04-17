/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class BarChartRecsController implements Initializable {

    @FXML
    private Button loadChart;
    @FXML
    private NumberAxis countAxis;
    @FXML
    private CategoryAxis stateAxis;
    @FXML
    private BarChart<String, Number> RecBarChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loadchart(MouseEvent event) throws SQLException {
        ReclamationService RS = new ReclamationService();



        // Series 1 
        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        dataSeries1.setName("Pending");

        dataSeries1.getData().add(new XYChart.Data<String, Number>("", RS.SelectPending()));

        // Series 2 
        XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<String, Number>();
        dataSeries2.setName("Treated");

        dataSeries2.getData().add(new XYChart.Data<String, Number>("", RS.SelectTreated()));

        // Add Series to BarChart.
        RecBarChart.getData().add(dataSeries1);
        RecBarChart.getData().add(dataSeries2);

        RecBarChart.setTitle("Complaint States");

    }

}
