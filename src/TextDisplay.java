import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * Enkel klasse som er laget for å kun vise verdien til en slider.
 */
public class TextDisplay extends TextField {
    public TextDisplay(String initial, Slider slider){
        super(initial);
        setEditable(false);
        textProperty().bind(slider.valueProperty().asString("%.2f"));
        setPrefColumnCount(2);
    }
    public void setInteger(Slider slider){
        textProperty().bind(slider.valueProperty().asString("%.0f"));
    }
}
