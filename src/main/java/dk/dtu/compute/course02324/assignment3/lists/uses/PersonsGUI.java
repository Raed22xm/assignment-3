package dk.dtu.compute.course02324.assignment3.lists.uses;

import dk.dtu.compute.course02324.assignment3.lists.uses.Person;
import javafx.scene.control.Alert;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.util.List;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.ArrayList;


/**
 * A GUI element that allows the user to interact and change a list of persons.
 */
public class PersonsGUI extends GridPane {

    final private List<Person> persons;

    private GridPane personsPane;
    private TextField ageField;
    private TextField minAgeField;
    private TextField maxAgeField;
    private TextField avgWeightField;

    public PersonsGUI(@NotNull List<Person> persons) {
        if (persons == null) {
            throw new IllegalArgumentException("The list of persons must not be null!");
        }
        this.persons = persons;

        setAlignment(Pos.CENTER);
        setPadding(new Insets(10, 10, 10, 10));
        setHgap(10);
        setVgap(5);

        // Form for adding new persons
        add(new Label("Name:"), 0, 0);
        TextField nameField = new TextField();
        add(nameField, 1, 0);

        add(new Label("Weight:"), 0, 1);
        TextField weightField = new TextField();
        add(weightField, 1, 1);

        add(new Label("Age:"), 0, 2);
        ageField = new TextField();
        add(ageField, 1, 2);

        Button addButton = new Button("Add Person");
        add(addButton, 1, 3);
        addButton.setOnAction(e -> {
            try {
                double weight = Double.parseDouble(weightField.getText());
                int age = Integer.parseInt(ageField.getText());
                String name = nameField.getText();

                Person person = new Person(name, weight, age);
                persons.add(person);
                update();

                nameField.clear();
                weightField.clear();
                ageField.clear();
            } catch (NumberFormatException exception) {
                showError("Please enter valid numbers for weight and age!");
            } catch (IllegalArgumentException exception) {
                showError(exception.getMessage());
            }
        });

        // Grid for displaying persons
        personsPane = new GridPane();
        personsPane.setAlignment(Pos.CENTER);
        personsPane.setHgap(10);
        personsPane.setVgap(5);
        personsPane.setPadding(new Insets(10, 10, 10, 10));

        ScrollPane scrollPane = new ScrollPane(personsPane);
        scrollPane.setFitToWidth(true);
        add(scrollPane, 0, 4, 2, 1);

        // Statistics fields
        GridPane statsPane = new GridPane();
        statsPane.setHgap(10);
        statsPane.setVgap(5);
        statsPane.setPadding(new Insets(10, 10, 10, 10));

        statsPane.add(new Label("Min Age:"), 0, 0);
        minAgeField = new TextField();
        minAgeField.setEditable(false);
        statsPane.add(minAgeField, 1, 0);

        statsPane.add(new Label("Max Age:"), 0, 1);
        maxAgeField = new TextField();
        maxAgeField.setEditable(false);
        statsPane.add(maxAgeField, 1, 1);

        statsPane.add(new Label("Avg Weight:"), 0, 2);
        avgWeightField = new TextField();
        avgWeightField.setEditable(false);
        statsPane.add(avgWeightField, 1, 2);

        add(statsPane, 0, 5, 2, 1);

        update();
    }

    private void update() {
        personsPane.getChildren().clear();
        personsPane.add(new Label("Name"), 0, 0);
        personsPane.add(new Label("Age"), 1, 0);
        personsPane.add(new Label("Weight"), 2, 0);
        personsPane.add(new Label("Action"), 3, 0);

        int row = 1;
        for (Person person : persons) {
            personsPane.add(new Label(person.name), 0, row);
            personsPane.add(new Label(Integer.toString(person.getAge())), 1, row);
            personsPane.add(new Label(Double.toString(person.weight)), 2, row);

            Button removeButton = new Button("Remove");
            final int index = row - 1;
            removeButton.setOnAction(e -> {
                persons.remove(index);
                update();
            });
            personsPane.add(removeButton, 3, row);
            row++;
        }

        updateStatistics();
    }

    private void updateStatistics() {
        if (persons.isEmpty()) {
            minAgeField.setText("N/A");
            maxAgeField.setText("N/A");
            avgWeightField.setText("N/A");
            return;
        }

        minAgeField.setText(Integer.toString(
                persons.stream().mapToInt(Person::getAge).min().orElse(0)));

        maxAgeField.setText(Integer.toString(
                persons.stream().mapToInt(Person::getAge).max().orElse(0)));

        avgWeightField.setText(String.format("%.2f",
                persons.stream().mapToDouble(p -> p.weight).average().orElse(0.0)));
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
