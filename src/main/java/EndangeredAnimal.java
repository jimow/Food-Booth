import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class EndangeredAnimal  extends Animal{
    public String health;
    public String age;
    public static final String DATABASE_TYPE = "Endangered";

    public  EndangeredAnimal(String name, String health, String age){
        this.name = name;
        this.health = health;
        this.age = age;
        type = DATABASE_TYPE;
    }

    public String getHealth(){

        return health;
    }
    public String getAge(){
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndangeredAnimal animal = (EndangeredAnimal ) o;
        return id == animal.id &&
                health.equals(animal.health) &&
                age.equals(animal.age) &&
                name.equals(animal.name) &&
                type.equals(animal.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(health, age, name, id, type);
    }

    public static List<EndangeredAnimal> all(){
        String sql = "SELECT * FROM animals WHERE type='Endangered'";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(EndangeredAnimal.class);
        }
    }

    public void save(){        EndangeredAnimal testEndangeredAnimal1 = new EndangeredAnimal("cat","okay","young");

        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name,type,health,age) VALUES (:name,:type,:health,:age);";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("type",this.type)
                    .addParameter("health",this.health)
                    .addParameter("age",this.age)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static EndangeredAnimal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            EndangeredAnimal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return animal;
        }
    }
}
