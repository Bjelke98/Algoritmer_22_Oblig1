import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Klasse for å enkelt ha et sammensatt panel som representerer en slider.
 */
public class SliderGroup extends BorderPane {
    private final Slider slider;
    private TextDisplay display;

    /**
     * Standard konstruktør som tar inn rekkevidde, startpunkt og visningsnavn
     * @param range
     * @param start
     * @param name
     */
    public SliderGroup(DoubleRange range, double start, String name){
        slider = new Slider(range.min(), range.max(), start);

        Label groupName = new Label(name);

        display = new TextDisplay("" + slider.getValue(), slider);

        HBox lblBox = new HBox();
        lblBox.setAlignment(Pos.CENTER);
        lblBox.getChildren().add(groupName);

        HBox valueBox = new HBox();
        valueBox.setAlignment(Pos.CENTER);
        valueBox.getChildren().addAll(slider, display);

        setLeft(lblBox);
        setRight(valueBox);
    }

    /**
     * Konstruktør som setter visning til integer istedenfor double verdi.
     * @param range
     * @param start
     * @param name
     * @param isInteger
     */
    public SliderGroup(DoubleRange range, double start, String name, boolean isInteger){
        this(range,start,name);
        display.setInteger(slider);
    }

    public Slider getSlider() {
        return slider;
    }
}
