package javaFX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class PieChart extends Application {
    private Stage window;
    private EventMass eventMass;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        eventMass = new EventMass();

        window = primaryStage;
        window.setTitle("PieChart");
        window.setResizable(false);

        new InputData().inputData();

        javafx.scene.chart.PieChart pieChart = new javafx.scene.chart.PieChart();
        pieChart.setData(getDataPieChart());

        Pane container = new Pane();
        container.getChildren().add(pieChart);

        Scene scene = new Scene(container);
        window.setScene(scene);

        window.show();

    }

    private ObservableList<javafx.scene.chart.PieChart.Data> getDataPieChart() {
        ObservableList<javafx.scene.chart.PieChart.Data> data = FXCollections.observableArrayList();

        for(HashMap.Entry<String, Double> mass: eventMass.getMapEvent().entrySet()) {
            data.addAll(new javafx.scene.chart.PieChart.Data(mass.getKey(), mass.getValue()));
        }

        return data;
    }

    public class EventMass {
        private HashMap<String, Double> mapEvent;

        public EventMass() {
            this.mapEvent = new LinkedHashMap<>();
        }

        public void addEventMass(String text, double value) {
            mapEvent.put(text, value);
        }

        public HashMap<String, Double> getMapEvent() {
            return mapEvent;
        }
    }

    public class InputData {
        private String os = null;
        private double value = 0;

        void inputData() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter countOS: ");
            int countBranch = Integer.parseInt(reader.readLine());

            while(countBranch > 0) {

            try{
                System.out.print("Enter OS: ");
                os = reader.readLine();
                System.out.print("Enter value_OS: ");
                value = Double.parseDouble(reader.readLine());

            } catch (IOException e) {
                System.out.println("Error input value!");
            }
                if(!os.isEmpty() && value != 0) {
                    eventMass.addEventMass(os, value);
                    countBranch--;
                }
            }
        }
    }
}
