import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("cat","okay","young");

    @Test
    public void EndangeredAnimal_instanceOfEndangeredAnimal_true(){
        assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
    }
    @Test
    public void getName_returnNameOfEndangeredAnimal_cat(){

        assertEquals("cat", testEndangeredAnimal.getName());
    }
    @Test
    public void getHealth_returnHealthLevelOfTheAnimal_okay(){
        assertEquals("okay", testEndangeredAnimal.getHealth());
    }
    @Test
    public void getAge_returnAgeLevelOfTheAnimal_okay(){
        assertEquals("young", testEndangeredAnimal.getAge());
    }
    @Test
    public void equals_returnsTrueIfNamesAreTheSame_true(){
        EndangeredAnimal testEndangeredAnimal1 = new EndangeredAnimal("cat","okay","young");
        assertTrue(testEndangeredAnimal.equals(testEndangeredAnimal1));
    }
    @Test
    public void save_savesEndangeredAnimalInstanceToDB_EndangeredAnimal(){
        testEndangeredAnimal.save();
        assertTrue(EndangeredAnimal.all().get(0).equals(testEndangeredAnimal));
    }
    @Test
    public void all_returnsAllInstancesOfEndangeredAnimal_true(){
        EndangeredAnimal testEndangeredAnimal1 = new EndangeredAnimal("cat","okay","young");
        testEndangeredAnimal1.save();
        testEndangeredAnimal.save();
        assertEquals(true, testEndangeredAnimal1.all().get(0).equals(testEndangeredAnimal1));
        assertEquals(true, testEndangeredAnimal.all().get(1).equals(testEndangeredAnimal));
    }
    @Test
    public void id_assignIdToSpecificInstance(){
        testEndangeredAnimal.save();
        EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.all().get(0);
        assertEquals(testEndangeredAnimal.getId(), savedEndangeredAnimal.getId());
    }
    @Test
    public void getSightings_retrievesAllSightingsFromDB_sightingList(){
        EndangeredAnimal testEndangeredAnimal1 = new EndangeredAnimal("cat","okay","young");
        testEndangeredAnimal1.save();
        Sightings firstSightings = new Sightings(testEndangeredAnimal1.getId(),"Zone A","Lenny");
        firstSightings.save();
        Sightings secondSightings = new Sightings(testEndangeredAnimal1.getId(),"Zone A","Lenny");
        secondSightings.save();
        Sightings[] sightings = new Sightings[]{firstSightings,secondSightings};
        assertTrue(testEndangeredAnimal1.getSightings().containsAll(Arrays.asList(sightings)));

    }
    @Test
    public void EndangeredAnimal_instantiatesWithOkayHealthLevel(){
        assertEquals(testEndangeredAnimal.getHealth(),"okay");
    }
    @Test
    public void EndangeredAnimal_instantiatesWithYoungAgeLevel(){
        assertEquals(testEndangeredAnimal.getAge(),"young");
    }
}