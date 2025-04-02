package dk.dtu.compute.course02324.assignment3.lists.uses;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;

/**
 * A simple JavaFX application with a simple GUI for manually
 * maintaining a list of Persons.
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public class PersonsApp extends Application {

    /**
     * The stage of the GUI of this test application.
     */
    private Stage stage;

    /**
     * The pane on which the actual interaction with the
     * list of persons will be added.
     */
    private Pane root;

    /**
     * The GUI for a specific list.
     */
    private PersonsGUI personsGUI = null;

    /**
     * The method starting the application, which sets up the GUI
     * elements of this application.
     *
     * @param stage the stage for this application (provided by JavaFX)
     * @throws Exception if something should go wrong (required by super class)
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        // Start with a fresh ArrayList of persons
        List<Person> personsList = new ArrayList<>();

        // Add some sample persons if needed
        personsList.add(new Person("John", 75.5, 25));
        personsList.add(new Person("Alice", 62.0, 30));
        personsList.add(new Person("Bob", 85.2, 42));

        // Set up the GUI with the list
        switchImpl(personsList);

        // Show the stage
        stage.show();
    }

    /**
     * Methods for creating the menu bar of the application.
     *
     * @return the menubar for the application
     */
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> System.exit(0));
        fileMenu.getItems().add(exitItem);

        menuBar.getMenus().add(fileMenu);

        return menuBar;
    }

    /**
     * Methods used for setting up the GUI.
     *
     * @param list person list for which GUI should be initialized; it can
     *              be <code>null</code>
     */
    private void switchImpl(List<Person> list) {
        if (root == null) {
            // first initialization
            root = new VBox();
            ((VBox) root).getChildren().add(createMenuBar());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Persons Application");
        }

        if (personsGUI != null) {
            ((VBox) root).getChildren().remove(personsGUI);
        }

        // Create a new list if none was provided
        if (list == null) {
            list = new ArrayList<>();
        }

        personsGUI = new PersonsGUI(list);
        ((VBox) root).getChildren().add(personsGUI);

        stage.sizeToScene();
    }

    /**
     * The main method used to start the JavaFX application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}