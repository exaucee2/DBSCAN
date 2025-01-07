/*Nom et prénom : Exaucée MBUYI KABAMBA
 * Numéro d'étudiant : 300268093
 */
public class KDtree {

    public class KDnode {
        public Point3D point;
        public int axis;
        public double value;
        public KDnode left;
        public KDnode right;

        public KDnode(Point3D pt, int axis) {

            this.point = pt;
            this.axis = axis;
            this.value = pt.get(axis);
            left = right = null;
        }

        public Point3D getPoint(Point3D point) {
            return point;
        }
    }

    KDnode root;

    public KDtree() {

        root = null;

    }

    public void add(Point3D p) {
        if (root == null) {
            root = insert(p, root, 0);
        } else {
            KDnode next = root;
            next = insert(p, next, 0);
        }
    }

    public KDnode insert(Point3D p, KDnode node, int axis) {

        if (node == null) {
            node = new KDnode(p, axis);
        } else if (p.get(axis) <= node.value) {
            node.left = insert(p, node.left, (axis + 1) % 3);
        } else {
            node.right = insert(p, node.right, (axis + 1) % 3);
        }
        return node;
    }

}
