
/*Nom et prénom : Exaucée MBUYI KABAMBA
 * Numéro d'étudiant : 300268093
 */
import java.util.*;

public class NearestNeighborsKD {

    KDtree kdtree;
    public List<Point3D> liste;

    public NearestNeighborsKD(List<Point3D> liste) {
        this.liste = liste;

        KDtree kdtree = new KDtree();
        this.kdtree = kdtree;

        for (Point3D p : liste) {

            kdtree.add(p);
        }
    }

    public void rangeQuery(Point3D p, double eps, List<Point3D> N, KDtree.KDnode node) {
        if (node == null)
            return;
        if (p.distance(node.point) < eps)
            N.add(node.point);
        if (p.get(node.axis) - eps <= node.value)
            rangeQuery(p, eps, N, node.left);
        if (p.get(node.axis) + eps > node.value)
            rangeQuery(p, eps, N, node.right);
        return;

    }

    public List<Point3D> rangeQuery(Point3D p, double eps) {
        List<Point3D> neighbors = new ArrayList<Point3D>();

        rangeQuery(p, eps, neighbors, this.kdtree.root);
        return neighbors;
    }

}
