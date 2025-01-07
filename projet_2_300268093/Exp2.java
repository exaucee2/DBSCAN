
/*Nom et prénom : Exaucée MBUYI KABAMBA
 * Numéro d'étudiant : 300268093
 */
import java.util.List;

import java.util.ArrayList;

public class Exp2 {

    public static void main(String[] args) throws Exception {

        // not reading args[0]

        double eps = Double.parseDouble(args[1]);

        // reads the csv file
        List<Point3D> points = Exp1.read(args[2]);

        List<Long> temp1lin = new ArrayList<Long>();
        List<Long> temp2kd = new ArrayList<Long>();

        for (int i = 0; i <= points.size(); i = i + 10) {

            Point3D query = points.get(i);

            // creates the NearestNeighbor instance ln
            NearestNeighbors nn = new NearestNeighbors(points);
            long startTime = System.nanoTime();// start time
            nn.rangeQuery(query, eps);
            long endTime = System.nanoTime();// end time
            long duration = (endTime - startTime);

            temp1lin.add(duration);// duration with linear method

            // creates the NearestNeighbor instance kd
            NearestNeighborsKD nn1 = new NearestNeighborsKD(points);
            long startTime1 = System.nanoTime();// start time
            nn1.rangeQuery(query, eps);
            long endTime1 = System.nanoTime();// end time
            long duration1 = (endTime1 - startTime1);

            temp2kd.add(duration1);// duration kdtree

        }
        float temp = 0;
        for (Long c : temp1lin) {
            temp += c;
        }
        float s1 = 0;
        for (Long d1 : temp2kd) {
            s1 += d1;
        }

        System.out.println("Linear " + temp / temp1lin.size() + " miliseconde");
        System.out.println("kdtree " + s1 / temp2kd.size() + " milliseconde");

    }
}
