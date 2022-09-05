import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Klasse som fullt ut kontrollerer hvordan treet skal formes og inneholder den rekursive metoden.
 */
public class Tree implements Config{

    private Integer size = 4; // Default 4
    private double length; // Default 1/5 of canvas height
    private double lengthMultiplier = .8; // Default 0.8
    private double angle = PI6;

    private Integer randomSizeValue = 20;
    private Integer randomLower = 0;
    private Integer randomHigher = 0;

    private Integer randomAngleLeft = 0;
    private Integer randomAngleRight = 0;

    private final Canvas canvas;
    private Bounds bounds;

    /**
     * Oppretter tree objekt
     * @param canvas
     */
    public Tree(Canvas canvas) {
        this.canvas = canvas;
        bounds = canvas.getBoundsInLocal();
        length = bounds.getMaxY()/5;
        drawTree();
    }

    /**
     * Oppstartsmetode for tegning av tre.
     */
    public void drawTree(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        Platform.runLater(() -> drawTree(size, length, canvas.getWidth()/2, canvas.getHeight(), -PI2, gc));

    }

    /**
     * Rekursiv metode for å tegne hver gren.
     * @param n Nåværende trestørrelse
     * @param length Lengde på pinne som skal tegnes
     * @param x Startpunkt x for pinne
     * @param y Startpunkt y for pinne
     * @param angle nåværende vinkel i treet.
     * @param gc GraphicsContext for å kunne tegne på canvas
     */
    private void drawTree(int n, double length, double x, double y, double angle, GraphicsContext gc){
        double newAngle = randomAngle(angle);
        double x2 = x+length*Math.cos(newAngle);
        double y2 = y+length*Math.sin(newAngle);
        drawBranch(x, y, x2, y2, gc);
        int n2 = randomSize(n);
        if(n>1){
            double l2 = length*lengthMultiplier;
            drawTree(n2-1, l2, x2, y2, newAngle-this.angle, gc);
            drawTree(n2-1, l2, x2, y2, newAngle+this.angle, gc);
        }
    }

    /**
     * Tegner en pinne til canvas
     * @param x StartX
     * @param y StartY
     * @param x2 SluttX
     * @param y2 SluttY
     * @param gc GraphicsContext for å kunne tegne på canvas
     */
    private void drawBranch(double x, double y, double x2, double y2, GraphicsContext gc){
        gc.strokeLine(x, y, x2, y2);
    }

    public double getLengthMultiplier() {
        return lengthMultiplier;
    }

    /**
     * Setter lengde forrhold og tegner treet på nytt.
     * @param lengthMultiplier
     */
    public void setLengthMultiplier(double lengthMultiplier) {
        this.lengthMultiplier = lengthMultiplier;
        drawTree();
    }

    /**
     * Setter startlengde og tegner treet på nytt.
     * @param length
     */
    public void setLength(double length) {
        this.length = bounds.getMaxY()*length;
        drawTree();
    }

    /**
     * Setter ny vinkel faktor og tegner treet på nytt.
     * @param angle
     */
    public void setAngle(double angle) {
        this.angle = Math.PI/angle;
        drawTree();
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = (int)Math.round(size);
        drawTree();
    }

    public Integer getRandomLower() {
        return randomLower;
    }

    public void setRandomLower(double randomLower) {
        this.randomLower = (int)Math.round(randomLower);
        drawTree();
    }

    public Integer getRandomHigher() {
        return randomHigher;
    }

    public void setRandomHigher(double randomHigher) {
        this.randomHigher = (int)Math.round(randomHigher);
        drawTree();
    }

    private int randomSize(int size){
        if(randomLower==0 && randomHigher==0) return size;
        int total = randomSizeValue+randomLower+randomHigher;
        int result = (int)(Math.random()*total);
        if(result<=randomSizeValue){
            return size;
        }
        if(result<=randomSizeValue+randomLower){
            return size-1;
        }
        return size+1;
    }

    public Integer getRandomAngleLeft() {
        return randomAngleLeft;
    }
    public void setRandomAngleLeft(double randomAngleLeft) {
        this.randomAngleLeft = (int)Math.round(randomAngleLeft);
        drawTree();
    }
    public Integer getRandomAngleRight() {
        return randomAngleRight;
    }
    public void setRandomAngleRight(double randomAngleRight) {
        this.randomAngleRight = (int)Math.round(randomAngleRight);
        drawTree();
    }
    private double randomAngle(double angle){
        if(randomAngleLeft == 0 && randomAngleRight == 0) return angle;
        return angle+Math.toRadians(((int)(Math.random()*(randomAngleLeft+randomAngleRight)))-randomAngleLeft);
    }
}
