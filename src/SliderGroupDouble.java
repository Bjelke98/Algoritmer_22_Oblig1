import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Klasse for å holde på en gruppe med 2 slider og dere informasjon.
 * Relativt lik SliderGroup klassen, men er laget for å holde på en "øvre" og "nedre" del.
 */
public class SliderGroupDouble extends BorderPane {
    private final Slider sliderA;
    private final Slider sliderB;
    private TextDisplay displayA;
    private TextDisplay displayB;

    /**
     * Hovedkonstruktør for klassen
     * @param rangeA
     * @param rangeB
     * @param startA
     * @param startB
     * @param name
     */
    public SliderGroupDouble(DoubleRange rangeA, DoubleRange rangeB, double startA, double startB, String name){
        sliderA = new Slider(rangeA.min(), rangeA.max(), startA);
        sliderA.setPrefWidth(60);
        sliderB = new Slider(rangeB.min(), rangeB.max(), startB);
        sliderB.setPrefWidth(60);

        Label groupName = new Label(name);

        displayA = new TextDisplay("" + sliderA.getValue(), sliderA);
        displayB = new TextDisplay("" + sliderB.getValue(), sliderB);

        HBox lblBox = new HBox();
        lblBox.setAlignment(Pos.CENTER);
        lblBox.getChildren().add(groupName);

        HBox valueBox = new HBox();
        valueBox.setAlignment(Pos.CENTER);
        valueBox.getChildren().addAll(sliderA, displayA, sliderB, displayB);

        setLeft(lblBox);
        setRight(valueBox);
    }

    /**
     * Konstruktør for integer visning.
     * @param rangeA
     * @param rangeB
     * @param startA
     * @param startB
     * @param name
     * @param isInteger
     */
    public SliderGroupDouble(DoubleRange rangeA, DoubleRange rangeB, double startA, double startB, String name, boolean isInteger){
        this(rangeA, rangeB, startA, startB, name);
        displayA.setInteger(sliderA);
        displayB.setInteger(sliderB);
    }

    public Slider getSliderA() {
        return sliderA;
    }
    public Slider getSliderB() {
        return sliderB;
    }
}
