import java.io.IOException;
import java.util.*;

/*Nom et prénom : Exaucée MBUYI KABAMBA
 * Numéro d'étudiant : 300268093
 */
public class Exp3 {

	public static void main(String[] args) throws IOException {

		double eps = Double.parseDouble(args[0]);
		int minPts = Integer.parseInt(args[1]);

		// With kdtree
		long startTime1 = System.nanoTime();
		List<Point3D> list2 = new ArrayList<Point3D>();

		list2 = DBScan.read(args[2]);

		DBScan Pointskd = new DBScan(list2);

		Pointskd.setEps(eps);

		Pointskd.setMinPts(minPts);

		Pointskd.findClustersKD();

		Pointskd.save(
				"Point_Cloud_1kd" + eps + "_" + minPts + "_" + Pointskd.getNumberOfClusters() + ".csv");
		long endTime1 = System.nanoTime();
		long duration1 = (endTime1 - startTime1) / 1000000;// duration kdtree

		// Linear DBScan
		long startTime = System.nanoTime();

		List<Point3D> list1 = new ArrayList<Point3D>();

		list1 = DBScan.read(args[2]);

		DBScan Pointsln = new DBScan(list1);

		Pointsln.setEps(eps);

		Pointsln.setMinPts(minPts);

		Pointsln.findClusters();

		Pointsln.save("Point_Cloud_1ln" + eps + "_" + minPts + "_" + Pointsln.getNumberOfClusters() + ".csv");

		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;// duration linear

		System.out.println("Linear " + duration + " millisecondes");
		System.out.println(" kdtree " + duration1 + " millisecondes");

	}

}
