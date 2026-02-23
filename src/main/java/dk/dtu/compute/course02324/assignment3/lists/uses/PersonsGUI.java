package dk.dtu.compute.course02324.assignment3.lists.uses;


import dk.dtu.compute.course02324.assignment3.lists.implementations.GenericComparator;
import dk.dtu.compute.course02324.assignment3.lists.types.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import jakarta.validation.constraints.NotNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * A GUI element that is allows the user to interact and
 * change a list of persons.
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public class PersonsGUI extends GridPane {

    private GridPane personsPane;
    private VBox actionBox;
    private VBox personsList;
    private ScrollPane scrollPane;

    private Label averageWeight;
    private Label mostFrequentName;
    private Label labelPersonsList;
    private Label exceptionsLabel;
    private Label nameLabel;
    private Label weightLabel;

    private TextField nameField;
    private TextField weightField;
    private TextField indexField;
    private TextArea exceptionsArea;

    private Button addButton;
    private Button clearButton;
    private Button addAtIndexButton;
    private Button sortButton;

    private List<Person> persons;

    /**
     * Constructor which sets up the GUI attached a list of persons.
     *
     * @param persons the list of persons which is to be maintained in
     *                this GUI component; it must not be <code>null</code>
     */
    public PersonsGUI(@NotNull List<Person> persons) {
        this.persons = persons;

        this.initGUI();
        this.setButtonActions();

        this.update();
    }

    private void initGUI() {
        this.setVgap(5.0);
        this.setHgap(5.0);
        this.setPadding(new Insets(10, 10, 10, 10));

        this.nameField = new TextField();
        this.nameField.setPrefColumnCount(5);
        this.nameField.setPromptText("Name");

        this.weightField = new TextField();
        this.weightField.setPrefColumnCount(5);
        this.weightField.setPromptText("Weight");

        this.indexField = new TextField();
        this.indexField.setPrefColumnCount(4);
        this.indexField.setPromptText("Index");

        this.mostFrequentName = new Label("Most frequent: -");
        this.averageWeight = new Label("Average: -");
        this.labelPersonsList = new Label("Persons:");
        this.exceptionsLabel = new Label("Exceptions:");
        this.nameLabel = new Label("Name:");
        this.weightLabel = new Label("Weight:");

        this.exceptionsArea = new TextArea();
        this.exceptionsArea.setEditable(false);
        this.exceptionsArea.setWrapText(true);
        this.exceptionsArea.setPrefRowCount(5);
        this.exceptionsArea.setPrefColumnCount(16);

        this.addButton = new Button("Add at the end of the list");
        this.clearButton = new Button("Clear");
        this.addAtIndexButton = new Button("Add at index");
        this.sortButton = new Button("Sort");

        GridPane nameAndWeightPane = new GridPane();
        nameAndWeightPane.setHgap(5.0);
        nameAndWeightPane.setVgap(5.0);
        nameAndWeightPane.add(this.nameLabel, 0, 0);
        nameAndWeightPane.add(this.weightLabel, 1, 0);
        nameAndWeightPane.add(this.nameField, 0, 1);
        nameAndWeightPane.add(this.weightField, 1, 1);

        HBox indexAndAddAtIndexBox = new HBox(this.addAtIndexButton, this.indexField);
        indexAndAddAtIndexBox.setSpacing(5.0);
        indexAndAddAtIndexBox.setAlignment(Pos.BASELINE_LEFT);

        HBox sortAndClearBox = new HBox(this.sortButton, this.clearButton);
        sortAndClearBox.setSpacing(5.0);
        sortAndClearBox.setAlignment(Pos.BASELINE_LEFT);

        this.actionBox = new VBox(
                nameAndWeightPane,
                this.addButton,
                indexAndAddAtIndexBox,
                sortAndClearBox,
                this.averageWeight,
                this.mostFrequentName,
                this.exceptionsLabel,
                this.exceptionsArea
        );
        this.actionBox.setSpacing(5.0);
        this.actionBox.setPadding(new Insets(0, 10, 0, 0));

        this.personsPane = new GridPane();
        this.personsPane.setPadding(new Insets(5));
        this.personsPane.setHgap(5);
        this.personsPane.setVgap(5);

        this.scrollPane = new ScrollPane(this.personsPane);
        this.scrollPane.setMinWidth(300);
        this.scrollPane.setMaxWidth(300);
        this.scrollPane.setMinHeight(285);
        this.scrollPane.setMaxHeight(300);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        this.personsList = new VBox(this.labelPersonsList, this.scrollPane);
        this.personsList.setSpacing(5.0);

        this.add(this.actionBox, 0, 0);
        this.add(this.personsList, 1, 0);
    }

    private void setButtonActions() {
        // TODO for all buttons installed below, the actions need to properly
        //      handle (catch) exceptions, and it would be nice if the GUI
        //      could also show the exceptions thrown by user actions on
        //      button pressed (cf. Assignment 2).

        // button for adding a new person to the list (based on
        // the name in the text field (the weight is just incrementing)
        // TODO a text field for the weight could be added to this GUI

        this.addButton.setOnAction(e -> {
            try {
                if(!this.nameField.getText().isBlank() && !this.weightField.getText().isBlank()) {
                    Person person = new Person(this.nameField.getText(), Integer.parseInt(this.weightField.getText()));
                    this.persons.add(person);
                    this.clearException();
                } else {
                    this.showException("Name and weight fields have to be set.");
                }
            } catch (NumberFormatException numberFormatException) {
                this.showException("Weight has to be a number.");
            } catch (IllegalArgumentException exception) {
                this.showException(exception.getMessage());
            }
            update();
        });

        this.addAtIndexButton.setOnAction(e -> {
            try {
                if(!this.nameField.getText().isBlank() && !this.weightField.getText().isBlank() && !this.indexField.getText().isBlank()) {
                    Person person = new Person(this.nameField.getText(), Integer.parseInt(this.weightField.getText()));
                    this.persons.add(Integer.parseInt(this.indexField.getText()), person);
                    this.clearException();
                } else {
                    this.showException("Name, weight and index fields have to be set.");
                }

            } catch (NumberFormatException numberFormatException) {
                this.showException("Weight and index have to be a number.");
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                this.showException("Index out of bounds. Use a value between 0 and " + this.persons.size() + ".");
            } catch (IllegalArgumentException illegalArgumentException) {
                this.showException(illegalArgumentException.getMessage());
            }
            update();
        });

        this.clearButton.setOnAction(e -> {
            this.persons.clear();
            this.clearException();
            update();
        });

        Comparator<Person> comparator = new GenericComparator<>();

        // button for sorting the list (according to the order of Persons,
        // which implement the interface Comparable, which is converted
        // to a Comparator by the GenericComparator above)
        this.sortButton.setOnAction(e -> {
            try {
                this.persons.sort(comparator);
                this.clearException();
            } catch (UnsupportedOperationException exception) {
                this.showException(exception.getMessage());
            }
            update();
        });


    }

    /**
     * Updates the values of the GUI elements with the current values
     * from the list.
     */
    private void update() {
        // makes sure that the GUI is updated accordingly
        double average = 0;
        HashMap<String, Integer> nameCounts = new HashMap<>();

        for (int i = 0; i < this.persons.size(); i++) {
            Person person = this.persons.get(i);
            average += person.weight;
            if (!nameCounts.containsKey(person.name)) {
                nameCounts.put(person.name, 1);
            } else {
                nameCounts.put(person.name, nameCounts.get(person.name) + 1);
            }
        }
        if (!this.persons.isEmpty()) {
            average /= this.persons.size();
            this.averageWeight.setText("Average: " + average);
        } else {
            this.averageWeight.setText("Average: -");
        }

        String mostFrequentName = "-";
        int maxFrequency = 0;

        for (Map.Entry<String, Integer> entry : nameCounts.entrySet()) {
            if (maxFrequency < entry.getValue()) {
                mostFrequentName = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        this.mostFrequentName.setText("Most occurring name: " + maxFrequency + " x " + mostFrequentName);


        this.personsPane.getChildren().clear();
        // adds all persons to the list in the personsPane (with
        // a delete button in front of it)
        for (int i = 0; i < this.persons.size(); i++) {
            Person person = this.persons.get(i);
            Label personLabel = new Label(i + ": " + person.toString());
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(
                    e -> {
                        this.persons.remove(person);
                        update();
                    }
            );
            HBox entry = new HBox(deleteButton, personLabel);
            entry.setSpacing(5.0);
            entry.setAlignment(Pos.BASELINE_LEFT);
            this.personsPane.add(entry, 0, i);
        }
    }

    private void clearException() {
        this.exceptionsArea.clear();
    }

    private void showException(String message) {
        if (message == null || message.isBlank()) {
            this.exceptionsArea.setText("An unexpected error occurred.");
        } else {
            this.exceptionsArea.setText(message);
        }
    }

    // TODO this GUI could be extended by some additional widgets for issuing other
    //      operations of lists. And the possibly thrown exceptions should be caught
    //      in the event handler (and possibly shown in an additional text area for
    //      exceptions; see Assignment 2).




}
