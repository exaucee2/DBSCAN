/*Nom et prénom : Exaucée MBUYI KABAMBA
 * Numéro d'étudiant : 300268093
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class DBScan {
  /* creation d'attributs */

  private double eps;
  private int minPts;
  private List<Point3D> point;
  private int NumberOfClusters = 0;

  /* le constructeur qui prend la liste de points */
  public DBScan(List<Point3D> point) {
    this.point = point;
  }

  /* methode set qui retourne un resultat type double */
  public double setEps(double eps) {
    this.eps = eps;
    return eps;
  }

  /* methode set qui retourne un resultat int */
  public int setMinPts(int minPts) {
    this.minPts = minPts;
    return minPts;
  }

  public double getEps(double eps) {
    return eps;
  }

  public int getMinpts(int minPts) {
    return minPts;
  }

  public void findClusters() {
    int Noise = 0;
    NumberOfClusters = 0;

    // parcourrir chacun de point de la liste
    for (Point3D point1 : point) {
      // creation de la liste des points voisins
      List<Point3D> listes = new ArrayList<Point3D>();
      // les nombres de clusters = 0 pour dire indefine et -1 pour noise
      if (point1.getcluster() > 0) {
        continue;
      }
      // si le point n'est pas indefini, on le met dans le clusterlabel puis on fait
      // une sauvergarde de ses voisins
      NearestNeighbors n = new NearestNeighbors(point);
      listes = n.rangeQuery(point1, eps);
      // la condition si on a peu de voisin alors le point est consideré comme noise
      if (listes.size() < minPts) {
        point1.setCluster(Noise);
        continue;
      }
      // on incremente le nombre de cluster et on affecte ce nombre au label
      ++NumberOfClusters;
      point1.setCluster(NumberOfClusters);
      /*
       * on cree une pile
       * on ajoute tous les voisins dans la pile et si le point est un bruit alors on
       * changera son label tant que la pile n'est pas vide
       */
      Stack<Point3D> s = new Stack<Point3D>();
      for (int i = 0; i < listes.size(); i++) {
        s.push(listes.get(i));
      }

      while (!s.isEmpty()) {
        Point3D Q = s.pop();
        if (Q.getcluster() == Noise) {
          Q.setCluster(NumberOfClusters);
        }
        if (Q.getcluster() > 0) {
          continue;
        }
        Q.setCluster(NumberOfClusters);
        /* on store les voisins dans le voisinage du point */
        /*
         * listes= n.rangeQuery(Q, this.eps);
         * 
         * for(Point3D j:listes){
         * if (listes.size()>=minPts){
         * s.push(j);
         * }
         * }
         */
        NearestNeighbors n2 = new NearestNeighbors(point);
        List<Point3D> neighborsOfn2 = n2.rangeQuery(Q, eps);
        if (neighborsOfn2.size() >= minPts) {
          for (int i = 0; i < neighborsOfn2.size(); i++) {
            s.push(neighborsOfn2.get(i));
          }
        }

      }

    }
  }

  public void findClustersKD() {
    int Noise = 0;
    NumberOfClusters = 0;

    // parcourrir chacun de point de la liste
    for (Point3D point1 : point) {
      // creation de la liste des points voisins
      List<Point3D> listes = new ArrayList<Point3D>();
      // les nombres de clusters = 0 pour dire indefine et -1 pour noise
      if (point1.getcluster() > 0) {
        continue;
      }
      // si le point n'est pas indefini, on le met dans le clusterlabel puis on fait
      // une sauvergarde de ses voisins
      NearestNeighborsKD n = new NearestNeighborsKD(point);
      listes = n.rangeQuery(point1, eps);
      // la condition si on a peu de voisin alors le point est consideré comme noise
      if (listes.size() < minPts) {
        point1.setCluster(Noise);
        continue;
      }
      // on incremente le nombre de cluster et on affecte ce nombre au label
      ++NumberOfClusters;
      point1.setCluster(NumberOfClusters);
      /*
       * on cree une pile
       * on ajoute tous les voisins dans la pile et si le point est un bruit alors on
       * changera son label tant que la pile n'est pas vide
       */
      Stack<Point3D> s = new Stack<Point3D>();
      for (int i = 0; i < listes.size(); i++) {
        s.push(listes.get(i));
      }

      while (!s.isEmpty()) {
        Point3D Q = s.pop();
        if (Q.getcluster() == Noise) {
          Q.setCluster(NumberOfClusters);
        }
        if (Q.getcluster() > 0) {
          continue;
        }
        Q.setCluster(NumberOfClusters);
        /* on store les voisins dans le voisinage du point */
        /*
         * listes= n.rangeQuery(Q, this.eps);
         * 
         * for(Point3D j:listes){
         * if (listes.size()>=minPts){
         * s.push(j);
         * }
         * }
         */
        NearestNeighborsKD n2 = new NearestNeighborsKD(point);
        List<Point3D> neighborsOfn2 = n2.rangeQuery(Q, eps);
        if (neighborsOfn2.size() >= minPts) {
          for (int i = 0; i < neighborsOfn2.size(); i++) {
            s.push(neighborsOfn2.get(i));
          }
        }

      }

    }
  }

  /* retourne le nombre de clusters */
  public int getNumberOfClusters() {
    int nombres = 0;
    for (Point3D x : point) {
      if (x.getcluster() > nombres) {
        nombres = x.getcluster();
      }
    }
    return nombres;
  }

  /* retourne la liste de point */
  public List<Point3D> getPoints() {
    return point;
  }

  public static List<Point3D> read(String filename) throws IOException {
    /* creation d'une nouvelle liste */
    List<Point3D> liste3 = new ArrayList<Point3D>();
    String line = "";

    try {
      FileReader fileReader = new FileReader(filename);
      BufferedReader br = new BufferedReader(fileReader);
      line = br.readLine();

      while ((line = br.readLine()) != null) {
        String[] tab = line.split(";");
        Double[] coordonnees = new Double[tab.length];
        for (int i = 0; i < tab.length; i++) {
          coordonnees[i] = Double.parseDouble(tab[i]);
        }
        liste3.add(new Point3D(coordonnees[0], coordonnees[1], coordonnees[2]));
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return liste3;
  }

  public void save(String filename) {

    /*
     * try (PrintWriter pr = new PrintWriter(filename)) {
     * System.out.println(getNumberOfClusters());
     * int nombre = getNumberOfClusters();
     * String[][] colorList = new String[nombre + 1][];
     * for (int i = 0; i < nombre + 1; i++) {
     * String[] temp = new String[3];
     * Random rand = new Random();
     * String R = String.valueOf(rand.nextDouble(1.0));
     * pr.println("\n");
     * String G = String.valueOf(rand.nextDouble(1.0));
     * String B = String.valueOf(rand.nextDouble(1.0));
     * temp[0] = R;
     * temp[1] = G;
     * temp[2] = B;
     * colorList[i] = temp;
     * }
     * /*
     * List<Point3D> lyst = getPoints();
     * for (int j = 0; j < lyst.size(); j++) {
     * Point3D pts = lyst.get(j);
     * int NumberOfClusters = pts.getcluster();
     * String[] array = new String[3];
     * for (int k = 0; k < nombre; k++) {
     * if (NumberOfClusters == k + 1) {
     * array = colorList[k];
     * }
     * pr.println();
     * }
     * 
     * 
     * File fichier = new File(filename);
     * PrintWriter ecriture = new PrintWriter(fichier);
     * 
     * ecriture.println("x" + ";" + "y" + ";" + "z" + ";" + "C" + ";" + "R" + ";" +
     * "G" + ";" + "B");
     * for (Point3D p : point) {
     * ecriture.println(p.getX() + ";" + p.getY() + ";" + p.getZ() + ";" +
     * p.getcluster()
     * + colorList[p.getcluster()][0] + "," + colorList[p.getcluster()][1]
     * + "," + colorList[p.getcluster()][2]);
     * 
     * }
     * ecriture.close();
     * }
     * 
     * catch (FileNotFoundException e) {
     * e.printStackTrace();
     * }
     * 
     * 
     * 
     * /*
     * bonjour professeur mon RGB genere une boucle infinie c'est pouquoi je l'ai
     * mis en commentaire
     */

    try {

      File fichier = new File(filename);
      PrintWriter ecriture = new PrintWriter(fichier);

      ecriture.println("x" + ";" + "y" + ";" + "z" + ";" + "C" + ";" + "R" + ";" + "G" + ";" + "B");
      for (Point3D p : point) {
        ecriture.println(p.getX() + ";" + p.getY() + ";" + p.getZ() + ";" +
            p.getcluster());
      }
      ecriture.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }
}