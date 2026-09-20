import java.util.Arrays;
import java.util.Comparator;

public class ClosestPairSolver {
    public int maxDepth = 0;

    public double findClosestPair(Point[] points) {
        maxDepth = 0;
        Point[] pointsX = points.clone();
        Arrays.sort(pointsX, Comparator.comparingDouble(p -> p.x));
        Point[] pointsY = points.clone();
        return closestUtil(pointsX, pointsY, 0, points.length - 1, 1);
    }

    private double closestUtil(Point[] pX, Point[] pY, int left, int right, int depth) {
        maxDepth = Math.max(maxDepth, depth);
        if (right - left <= 3) {
            return bruteForce(pX, left, right);
        }

        int mid = left + (right - left) / 2;
        Point midPoint = pX[mid];

        Point[] pYLeft = new Point[mid - left + 1];
        Point[] pYRight = new Point[right - mid];
        int li = 0, ri = 0;
        for (Point point : pY) {
            if (point.x <= midPoint.x && li < pYLeft.length) pYLeft[li++] = point;
            else pYRight[ri++] = point;
        }

        double dl = closestUtil(pX, pYLeft, left, mid, depth + 1);
        double dr = closestUtil(pX, pYRight, mid + 1, right, depth + 1);
        double d = Math.min(dl, dr);

        Point[] strip = new Point[right - left + 1];
        int j = 0;
        for (Point point : pY) {
            if (Math.abs(point.x - midPoint.x) < d) {
                strip[j++] = point;
            }
        }

        return Math.min(d, stripClosest(strip, j, d));
    }

    private double stripClosest(Point[] strip, int size, double d) {
        double min = d;
        for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j) {
                if (strip[i].distance(strip[j]) < min) {
                    min = strip[i].distance(strip[j]);
                }
            }
        }
        return min;
    }

    public double bruteForce(Point[] points, int left, int right) {
        double min = Double.MAX_VALUE;
        for (int i = left; i <= right; ++i) {
            for (int j = i + 1; j <= right; ++j) {
                if (points[i].distance(points[j]) < min) {
                    min = points[i].distance(points[j]);
                }
            }
        }
        return min;
    }
}