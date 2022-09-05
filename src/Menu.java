import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Meny klasse som representerer høyrepanelet til applikasjonen.
 */
public class Menu extends Stage {

    /**
     * Lagrer canvas for senere bruk.
     */
    private final Canvas canvas;
    private final Tree tree;

    private DoubleRange size = new DoubleRange(1, 20);
    private SliderGroup sizeSlider;

    private DoubleRange length = new DoubleRange(.01, .5);
    private SliderGroup lengthSlider;

    private DoubleRange lengthMultiplier = new DoubleRange(.2, 2.0);
    private SliderGroup lengthMultiplierSlider;

    private DoubleRange angle = new DoubleRange(2, 24);
    private SliderGroup angleSlider;

    private DoubleRange sizeRandom = new DoubleRange(0, 20);
    private SliderGroupDouble sizeRandomSlider;

    private DoubleRange angleRandom = new DoubleRange(0, 90);
    private SliderGroupDouble angleRandomSlider;

    /**
     * Konstruktør for å opprette program menyen.
     * @param canvas Tegnebrett
     * @param x Meny plassering X
     * @param y Meny plassering Y
     * @param w Meny bredde
     * @param h Meny høyde
     */
    public Menu(Canvas canvas, double x, double y, double w, double h){
        this.canvas = canvas;

        VBox root = new VBox();
        root.setPadding(new Insets(5));
        root.setSpacing(5);

        Scene scene = new Scene(root, w,h);
        setScene(scene);
        setX(x);
        setY(y);
        tree = new Tree(canvas);

        sizeSlider = new SliderGroup(size, tree.getSize(), "Size: ", true);
        lengthSlider = new SliderGroup(length, .2, "Length: ");
        lengthMultiplierSlider = new SliderGroup(lengthMultiplier, tree.getLengthMultiplier(), "Length multiplier: ");
        angleSlider = new SliderGroup(angle, 6, "Angle (PI/x): ");

        Label lblRandom = new Label("Tree random properies: ");
        lblRandom.setPadding(new Insets(20, 0, 0, 0));

        sizeRandomSlider = new SliderGroupDouble(sizeRandom, sizeRandom, tree.getRandomLower(), tree.getRandomHigher(), "Random size (less | more)", true);
        angleRandomSlider = new SliderGroupDouble(angleRandom, angleRandom, tree.getRandomAngleLeft(), tree.getRandomAngleRight(), "Random angle (left | right)", true);

        Button redraw = new Button("Redraw");

        root.getChildren().addAll(
                new Label("Tree base properties: "),
                sizeSlider,
                lengthSlider,
                lengthMultiplierSlider,
                angleSlider,
                lblRandom,
                sizeRandomSlider,
                angleRandomSlider,
                redraw
        );

        show();

        setOnCloseRequest(e->{
            Stage mainStage = (Stage) canvas.getScene().getWindow();
            mainStage.close();
        });

        // Setter opp alle nødvendige bindings / event lyttere
        sizeSlider.getSlider().setOnMouseDragged(e-> tree.setSize(sizeSlider.getSlider().getValue()));
        lengthSlider.getSlider().setOnMouseDragged(e-> tree.setLength(lengthSlider.getSlider().getValue()));
        lengthMultiplierSlider.getSlider().setOnMouseDragged(e-> tree.setLengthMultiplier(lengthMultiplierSlider.getSlider().getValue()));
        angleSlider.getSlider().setOnMouseDragged(e-> tree.setAngle(angleSlider.getSlider().getValue()));

        sizeRandomSlider.getSliderA().setOnMouseDragged(e-> tree.setRandomLower(sizeRandomSlider.getSliderA().getValue()));
        sizeRandomSlider.getSliderB().setOnMouseDragged(e-> tree.setRandomHigher(sizeRandomSlider.getSliderB().getValue()));

        angleRandomSlider.getSliderA().setOnMouseDragged(e-> tree.setRandomAngleLeft(angleRandomSlider.getSliderA().getValue()));
        angleRandomSlider.getSliderB().setOnMouseDragged(e-> tree.setRandomAngleRight(angleRandomSlider.getSliderB().getValue()));

        redraw.setOnAction(e->tree.drawTree());

    }
}
