package dk.dtu.compute.course02324.assignment3.lists.uses;

import dk.dtu.compute.course02324.assignment3.lists.implementations.GenericComparator;
import dk.dtu.compute.course02324.assignment3.lists.types.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javax.validation.constraints.NotNull;
import java.util.Comparator;

/**
 * A GUI element that is allows the user to interact and
 * change a list of persons.
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public class PersonsGUI extends GridPane {

    final private List<Person> persons;
    private GridPane personsPane;
    private int weightCount = 1;
    private TextField weightField, indexField;
    private Label avgWeightLabel, mostFrequentNameLabel;

    public PersonsGUI(@NotNull List<Person> persons) {
        this.persons = persons;
        this.setVgap(5.0);
        this.setHgap(5.0);

        TextField field = new TextField();
        field.setPrefColumnCount(5);
        field.setText("name");

        weightField = new TextField();
        weightField.setPromptText("Enter weight");

        indexField = new TextField();
        indexField.setPromptText("Enter index");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addPerson(field));

        Button addAtIndexButton = new Button("Add at index");
        addAtIndexButton.setOnAction(e -> {
            try {
                addPersonAtIndex(field);
            }catch (UnsupportedOperationException exception){
                showError("Cannot insert at an index in a SortedList");
            }
        }
        );

        Comparator<Person> comparator = new GenericComparator<>();
        Button sortButton = new Button("Sort");
        sortButton.setOnAction(e -> {
            try {
                persons.sort(comparator);
                update();
            }
            catch (UnsupportedOperationException ex) {
                showError("Sorting not allowed on a SortedList");
            }
        });

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> {
            persons.clear();
            update();
        });

        avgWeightLabel = new Label("Average Weight: 0");
        mostFrequentNameLabel = new Label("Most Frequent Name: None");

        VBox actionBox = new VBox(field, weightField, addButton, indexField, addAtIndexButton, sortButton, clearButton, avgWeightLabel, mostFrequentNameLabel);
        actionBox.setSpacing(5.0);
        this.add(actionBox, 0, 0);

        Label labelPersonsList = new Label("Persons:");
        personsPane = new GridPane();
        personsPane.setPadding(new Insets(5));
        personsPane.setHgap(5);
        personsPane.setVgap(5);
        ScrollPane scrollPane = new ScrollPane(personsPane);
        scrollPane.setMinWidth(300);
        scrollPane.setMaxWidth(300);
        scrollPane.setMinHeight(300);
        scrollPane.setMaxHeight(300);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        VBox personsList = new VBox(labelPersonsList, scrollPane);
        personsList.setSpacing(5.0);
        this.add(personsList, 1, 0);

        update();
    }

    private void addPerson(TextField field) {
        String name = field.getText().trim();
        String weightText = weightField.getText().trim();
        if (name.isEmpty() || weightText.isEmpty()) {
            showError("Name and weight cannot be empty");
            return;
        }
        try {
            int weight = Integer.parseInt(weightText);
            persons.add(new Person(name, weight));
            update();
        } catch (NumberFormatException e) {
            showError("Weight must be a numeric value");
        }
    }

    private void addPersonAtIndex(TextField field) {
        String name = field.getText().trim();
        String weightText = weightField.getText().trim();
        String indexText = indexField.getText().trim();
        if (name.isEmpty() || weightText.isEmpty() || indexText.isEmpty()) {
            showError("Name, weight, and index cannot be empty");
            return;
        }
        try {
            int weight = Integer.parseInt(weightText);
            int index = Integer.parseInt(indexText);
            persons.add(index, new Person(name, weight));
            update();
        } catch (NumberFormatException e) {
            showError("Weight and index must be numeric values");
        } catch (IndexOutOfBoundsException e) {
            showError("Invalid index");
        } catch (UnsupportedOperationException e) {
            showError("Operation set not allowed on SortedLists");
        }
    }

    private void update() {
        personsPane.getChildren().clear();
        double totalWeight = 0;
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            totalWeight += person.weight;
            Label personLabel = new Label(i + ": " + person.toString());
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(e -> {
                persons.remove(person);
                update();
            });
            HBox entry = new HBox(deleteButton, personLabel);
            entry.setSpacing(5.0);
            entry.setAlignment(Pos.BASELINE_LEFT);
            personsPane.add(entry, 0, i);
        }
        double avgWeight = persons.isEmpty() ? 0 : totalWeight / persons.size();
        avgWeightLabel.setText("Average Weight: " + String.format("%.2f", avgWeight));
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
