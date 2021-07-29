import java.util.ArrayList;
import java.util.List;

public class ShipsCounter {
    /*
Завдання:
Використовуючи Core Java, підрахувати кількість кораблів. Візьміть до уваги, що тут правила
 розташування кораблів як в морському бою - всі сусідні одинички вважаються одним кораблем.
 Дякую*/

    static int[][] ships = {
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},//0
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 0},//1      //1.7
            {0, 1, 0, 1, 1, 0, 0, 0, 0, 0},//2
            {0, 1, 0, 1, 1, 0, 0, 1, 1, 1},//3    //3.4     3.9
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},//4    //4.1
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//5
            {1, 1, 1, 1, 0, 1, 0, 0, 0, 0},//6     //6.3   6.5
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//7    //
            {1, 1, 0, 0, 0, 0, 0, 1, 0, 0},//8     //8.7
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 1}//9        //9.1     9.9
    };

    public static void main(String[] args) {
        System.out.println("Кількість кораблів " + counterShips(ships));
    }

    static class Point {
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static List<Point> points = new ArrayList<>();

    public static int counterShips(int[][] ships) {
        int sum = 0;
        for (int i = 0; i < ships.length; i++) {
            for (int j = 0; j < ships[i].length; j++) {
                if (ships[i][j] == 1) {
                    findShip(i, j);
                }
            }
        }

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                if ((points.get(i).x - points.get(j).x == 1 && points.get(i).y == points.get(j).y)
                        || (points.get(i).x == points.get(j).x && points.get(i).y - points.get(j).y == 1) ) {
                    points.remove(i);
                }
            }
        }

        for (int i = 0; i < points.size(); i++) {
            if(points.get(i) != null) {
                //System.out.println("Координати кораблів " + points.get(i).toString());
                sum++;
            }
        }
        return sum;
    }
    public static List<Point> findShip (int x, int y) {
        for(int i = 0; i < points.size(); i++) {
            if ((Math.abs(points.get(i).x - x)== 1 && points.get(i).y == y ) ||
                    (points.get(i).x == x && Math.abs(points.get(i).y - y) == 1)) {

                if (points.get(i).x == x && Math.abs(points.get(i).y - y) <= 1) {
                    Point point = new Point(x, y);
                    points.remove(i);
                    points.add(i, point);
                    return points;
                }
                if (points.get(i).y== y && Math.abs(points.get(i).x - x) <= 1) {
                        points.remove(i);
                        points.add(i, new Point(x, y));
                        return points;
                }
                return points;
            }
        }
        points.add(new Point(x, y));
        return points;
    }
}
