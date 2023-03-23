public class ShapeTest {
    public static void main(String[] args) {

        Quad quad = new Quad(6);

//        Circle circle = new Circle(20);
        Circle circle = new Circle();
        circle.setRadius(20);

        Parallelogram romb = new Rhombus(6, 30);

        Point point1 = new Point(2.25, 2);
        Point point2 = new Point(4, 6.5);
        Line line = new Line(point1, point2);

        Printer printer = new Printer();
        printer.printArea(quad); //36
        printer.printArea(circle); //1256.6.....
        printer.printArea(romb); // 18
        printer.printArea(line); //0
        printer.printPerimeter(line); //
        printer.printPerimeter(circle); //125.6...
        printer.printPerimeter(quad); //24

        System.out.println("romb.getName() = " + romb.getName());

        ShapeName nameOfShape = new ShapeName();
        nameOfShape.shapeName(quad);
        nameOfShape.shapeName(circle);
        nameOfShape.shapeName(romb);
        nameOfShape.shapeName(line);
    }
}