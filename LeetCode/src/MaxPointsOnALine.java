import java.util.HashSet;
import java.util.Set;

class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}

class Solution {
    public int maxPoints(Point[] points) {
        int result = 0;
        
        Set<Line> setOfLines = new HashSet<Line>();

        for (int i = 1; i < points.length; i++) {
            Point point = points[i];
            for(int j = 0; j < i ; j++) {
                if(point.x != points[j].x || point.y != points[j].y) {
                    setOfLines.add(new Line(points[j], point));
                }
            }

        }
        
        if(setOfLines.isEmpty()) result = points.length;
        
        for(Line line : setOfLines) {
            int numberOfContainingPoints = 0;
            for(Point point : points) {
                if(line.has(point)) numberOfContainingPoints++;
            }
            if(result < numberOfContainingPoints) result = numberOfContainingPoints;
        }
        
        
      
//        for (int i = 0; i < points.length; i++) {
//            Map<Line, Integer> mapOfLines = new HashMap<Line, Integer>();
//            int resulti = 0;
//            Point point = points[i];
//            int overlaps = 0;
//            for (int j = i+1; j < points.length ; j++) {
//                if (point.x != points[j].x || point.y != points[j].y) {
//                    Line newLine = new Line(points[j], point);
//                    if (mapOfLines.containsKey(newLine)) {
//                        int numberOfContainingPoints = mapOfLines.get(newLine) + 1;
//                        mapOfLines.put(newLine, numberOfContainingPoints);
//                        if (resulti < numberOfContainingPoints) resulti = numberOfContainingPoints;
//                    } else {
//                        mapOfLines.put(newLine, 2);
//                        if (resulti < 2) resulti = 2;
//                    }
//                } else {
//                    overlaps++;
//                }
//            }
//            resulti += overlaps;
//            if(result < resulti) result = resulti;
//
//        }

        return result;
    }
}

class Line {
    int a;
    int b;
    int c;

    Line(Point p1, Point p2) {
        this.a = p2.y - p1.y;
        this.b = p1.x - p2.x;
        this.c = -a * p1.x - b * p1.y;
    }

    boolean has(Point p) {
        if (a * p.x + b * p.y + c == 0)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + java.lang.Math.abs(a);
        result = prime * result + java.lang.Math.abs(b);
        result = prime * result + java.lang.Math.abs(c);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Line other = (Line) obj;
        if (a == other.a && b == other.b && c == other.c)
            return true;
        if (-a == other.a && -b == other.b && -c == other.c)
            return true;
        return false;
    }
}

public class MaxPointsOnALine {
    public static void main(String... args) {
        Point[] points = new Point[] {
                new Point(0,9),
                new Point(138,429),
                new Point(115,359),
                new Point(115,359),
                new Point(-30,-102),
                new Point(230,709),
                new Point(-150,-686),
                new Point(-135,-613),
                new Point(-60,-248),
                new Point(-161,-481),
                new Point(207,639),
                new Point(23,79),
                new Point(-230,-691),
                new Point(-115,-341),
                new Point(92,289),
                new Point(60,336),
                new Point(-105,-467),
                new Point(135,701),
                new Point(-90,-394),
                new Point(-184,-551),
                new Point(150,774)
//                new Point(1,2),
//                new Point(1,1),
//                new Point(1,1),
//                new Point(1,2)
                };
        System.out.println(new Solution().maxPoints(points));
    }
}