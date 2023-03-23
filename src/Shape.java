import java.awt.*;

interface Surefaceable {
//    void draw(Drawable draw);
}

interface Drawable extends Surefaceable {
    void draw();

    void fill();

    Color getFillColor();

    double getArea();

    double getPerimeter();

}

interface Scalable extends Drawable {
    double getScale();

    void scale();
}

interface Borderable extends Drawable {
    double getBorderWidth();

    Color getBorderColor();

    void border();
}

class Point implements Drawable {
    private String name = "point";

    public String getName() {
        return name;
    }

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        return 0;
    }

    @Override
    public void draw() {
        System.out.println("This is a point");
    }

    @Override
    public void fill() {
        System.out.println("The point doesn't have body and doesn't need color");
    }

    @Override
    public Color getFillColor() {
        return null;
    }
}

abstract class Shape implements Borderable, Scalable {

    public abstract String getName();

    protected double scale = 1;
    protected Color fillColor;
    protected Color borderColor;
    protected double borderWidth;

    @Override
    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    public double getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(double borderWidth) {
        this.borderWidth = borderWidth;
    }

    public void draw() {
        System.out.println("Let's draw " + getName());
    }

    @Override
    public void fill() {
        System.out.println("Fill color " + getFillColor());
    }

    public void border() {
        System.out.println("Set border " + getBorderColor() + getBorderWidth());
    }

    public void scale() {
        System.out.println("Change dimensions by scale " + getScale());
    }

    @Override
    public abstract double getPerimeter();

    @Override
    public abstract double getArea();
}

class Line extends Shape {
    private String name = "line";
    public String getName() {
        return name;
    }

    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public double getLength() {
        return Math.sqrt(Math.pow((end.getX() - start.getX()), 2) +
                Math.pow((end.getY() - start.getY()), 2));
    }

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        return getLength();
    }
}

abstract class ConvexShapes extends Shape {
    private String name = "Convex Shape";
    public String getName() {
        return name;
    }

}

abstract class RegularConvexShapes extends ConvexShapes {
    private String name = "Regular Convex Shape";
    public String getName() {
        return name;
    }

}

abstract class PointCurveShape extends RegularConvexShapes {
    private String name = "This shapes may be bounded by curves such as the circle or the ellipse";
    public String getName() {
        return name;
    }

}

class Ellipse extends PointCurveShape {
    private String name = "ellipse";
    public String getName() {
        return name;
    }

    protected double radiusOne;
    protected double radiusTwo;

//    public Ellipse(double radiusOne, double radiusTwo) {
//        this.radiusOne = radiusOne;
//        this.radiusTwo = radiusTwo;
//    }

    public double getRadiusOne() {
        return radiusOne;
    }

    public void setRadiusOne(double radiusOne) {
        this.radiusOne = radiusOne;
    }

    public double getRadiusTwo() {
        return radiusTwo;
    }

    public void setRadiusTwo(double radiusTwo) {
        this.radiusTwo = radiusTwo;
    }

    @Override
    public double getArea() {
        return Math.PI * radiusOne * radiusTwo;
    }

    @Override
    public double getPerimeter() {
        return 4 * (Math.PI * radiusOne * radiusTwo + Math.pow((radiusOne - radiusTwo), 2)) /
                (radiusOne - radiusTwo);
    }
}

class Circle extends Ellipse {
    protected String name = "circle";
    public String getName() {
        return name;
    }

    private double radius;

//    public Circle(double radius) {
//        super(radius, radius);
//    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

abstract class PointLineShape extends RegularConvexShapes {
    private String name = "This shapes can be defined by a set of points " +
            "and lines connecting the points in a closed chain";
    public String getName() {
        return name;
    }
}

class Triangle extends PointLineShape {
    private String name = "triangle";
    public String getName() {
        return name;
    }

    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public void setSideC(double sideC) {
        this.sideC = sideC;
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public double getArea() {
        double p = this.getPerimeter();
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }
}

abstract class Quadrilateral extends PointLineShape {
    private String name = "A quadrilateral is a four-sided polygon, " +
            "having four edges (sides) and four corners (vertices)";
    public String getName() {
        return name;
    }

}

class Parallelogram extends Quadrilateral {
    private String name = "parallelogram";
    public String getName() {
        return name;
    }

    protected double sideA;
    protected double sideB;
    protected double angle;

    public Parallelogram(double sideA, double sideB, double angle) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.angle = angle; //degrees
    }

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public double getPerimeter() {
        return 2 * (sideA + sideB);
    }

    @Override
    public double getArea() {
        return sideA * sideB * Math.sin(Math.toRadians(angle));
    }
}

class Rhombus extends Parallelogram {
    private String name = "rhombus";
    public String getName() {
        return name;
    }

    public Rhombus(double sideA, double angle) {
        super(sideA, sideA, angle);
    }

    @Override
    public double getPerimeter() {
        return 4 * sideA;
    }

    @Override
    public double getArea() {
        return Math.pow(sideA, 2) * Math.sin(Math.toRadians(angle));
    }
}

class Quad extends Rhombus {
    protected String name = "quad";
    public String getName() {
        return name;
    }

    public Quad(double sideA) {
        super(sideA, 90);
    }

    @Override
    public double getArea() {
        return Math.pow(sideA, 2);
    }
}

class Rectangle extends Parallelogram {
    private String name = "rectangle";

    public Rectangle(double sideA, double sideB) {
        super(sideA, sideB, 90);
    }

    @Override
    public double getArea() {
        return sideA * sideB;
    }
}


