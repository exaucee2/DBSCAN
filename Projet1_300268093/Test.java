
import java.io.IOException;
import java.util.*;
public class Test {
    public static void main ( String [] args) throws IOException{
        List<Point3D> point=DBScan.read("Point_Cloud_3.csv");
        DBScan sc= new DBScan( point);
        sc.setMinPts(5);
        sc.setEps(0.2);
        sc.findClusters();
        
        sc.save("point_cloud_3"+"_" +sc.getEps(0.2)+"_"+sc.getMinpts(6)+"_"+sc.getNumberOfClusters()+".csv");                                    
    }
}