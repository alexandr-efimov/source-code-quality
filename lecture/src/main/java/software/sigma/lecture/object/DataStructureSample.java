package software.sigma.lecture.object;

public class DataStructureSample {
    public class Square {
        public double side;
    }

    public class Rectangle {
        public double height;
        public double width;
    }

    public class Circle {
        public double radius;
    }

    public class Geometry {
        public final double PI = 3.141592653589793;

        public double area(Object shape) throws NoSuchShapeException {
            if (shape instanceof Square) {
                Square s = (Square) shape;
                return s.side * s.side;
            } else if (shape instanceof Rectangle) {
                Rectangle r = (Rectangle) shape;
                return r.height * r.width;
            } else if (shape instanceof Circle) {
                Circle c = (Circle) shape;
                return PI * c.radius * c.radius;
            }
            throw new NoSuchShapeException();
        }
    }
}

class NoSuchShapeException extends RuntimeException {
}
