import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Передано неверное количество аргументов");
        }
        String circlePathFile = args[0];
        String pointsPathFile = args[1];

        Scanner circle = new Scanner(new File(circlePathFile));
        double centerY = circle.nextDouble();
        double centerX = circle.nextDouble();
        double radius = circle.nextDouble();
        circle.close();

        Scanner points = new Scanner(new File(pointsPathFile));
        double radiusPowTwo = Math.pow(radius, 2);
        while (points.hasNext()) {
            double x = points.nextDouble();
            double y = points.nextDouble();
            double lenPowTwo = Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2);
            if (lenPowTwo > radiusPowTwo) {
                System.out.println(2);
            } else if (lenPowTwo < radiusPowTwo) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
        points.close();
    }
}