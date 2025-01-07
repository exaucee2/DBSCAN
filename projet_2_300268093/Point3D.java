/*Nom et prénom : Exaucée MBUYI KABAMBA
 * Numéro d'étudiant : 300268093
 */

public class Point3D {

    /* attributs de type double x, y et z */
    private double x, y, z;

    /* un cluster label */
    private int cluster;

    /* un constructeur qui prend trois parametres */
    public Point3D(double x, double y, double z) {
        this.cluster = -1; // point indéterminé
        this.x = x;
        this.y = y;
        this.z = z;

    }

    /* une methodse set qui prend en parametre le cluster */
    public void setCluster(int cluster) {
        this.cluster = cluster;
    }
    /* les methodes get qui retournent des doubles */

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public int getcluster() {
        return this.cluster;
    }

    // gets coordinate x, y or z if axis 0, 1, or 2
    public double get(int axis) {

        switch (axis) {
            case 0:
                return x;
            case 1:
                return y;
            case 2:
                return z;
            default:
                return 0.0;
        }
    }

    // gets the euclidean distance between two points
    public double distance(Point3D pt) {
        return Math.sqrt((this.getX() - pt.getX()) * (this.getX() - pt.getX()) +
                (this.getY() - pt.getY()) * (this.getY() - pt.getY()) +
                (this.getZ() - pt.getZ()) * (this.getZ() - pt.getZ()));
    }

    // gets String representation
    public String toString() {

        return "(" + x + "," + y + "," + z + ")";
    }

}
