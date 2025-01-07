/*Nom et prénom : Exaucée MBUYI KABAMBA
 * Numéro d'étudiant : 300268093
 */



import java.util.*;


public class Point3D{
    
    /* attributs de type double x, y et z  */
    private double x,y,z;
    
    /*un cluster label  */
    private int cluster;
    /* un constructeur qui prend trois parametres  */
    public Point3D(double x,double y, double z ){
        this.cluster = -1; // point indéterminé 
        this.x=x;
        this.y=y;
        this.z=z;
       
    }
    /* une methodse set qui prend en parametre le cluster */
public void setCluster(int cluster){
    this.cluster=cluster;
}
/* les methodes get qui retournent des doubles  */
    
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public double getZ(){
        return this.z;
    }
    public int getcluster(){
        return this.cluster;
    }
    /* une methode qui calcule la distance entre deux points */
    public double distance (Point3D pt){
        return Math.sqrt((Math.pow(this.x-pt.getX(),2))- (Math.pow(this.y-pt.getY(),2))-(Math.pow(this.z-pt.getZ(),2)));
        
    }
   
}
    