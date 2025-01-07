/*Nom et prénom : Exaucée MBUYI KABAMBA
 * Numéro d'étudiant : 300268093
 */


import java.util.*;


public  class NearestNeighbors{
    private List<Point3D> DB;
    /* un constructeur qui prend une liste de points  */
   public  NearestNeighbors(List<Point3D> DB){
       this.DB = DB;
   }
   /* une methode qui retourne une liste des points qui sont proches
    *  tout d'abord on initialise une nouvelle liste de point ensuite pour tous les points dans la sequence de point DB
    si la distance entre les points crées et le point Q egale ou est inferieur à epsilon alors on ajoute ces points à la liste 
    ensuite on retourne la liste 
     */
   public List<Point3D> rangeQuery( Point3D Q ,double eps){
      List<Point3D> liste=new ArrayList<Point3D>();
      for(Point3D p:DB){
      if (p.distance(Q)<=eps){
         liste.add(p);
      }
   
   }
   return liste;
}
}