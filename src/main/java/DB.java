import org.sql2o.Sql2o;

public class DB {

//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "billyayiera", "theboyme");
      public static String connectionString = "jdbc:postgresql://ec2-184-72-235-80.compute-1.amazonaws.com:5432/db9mi904g6otb2";
      public static Sql2o sql2o = new Sql2o(connectionString, "ocaoyylacmruev", "e21560efd2757a2eb13da85a421e410791d3ac68e83f5d548f13440ac5bc46f1");

}
