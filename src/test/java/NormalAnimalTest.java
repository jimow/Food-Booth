import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
public class NormalAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    NormalAnimal testNormalAnimal = new NormalAnimal("cat");

    @Test
    public void NormalAnimal_instanceOfNormalAnimal_true(){
        assertEquals(true, testNormalAnimal instanceof NormalAnimal);
    }
    @Test
    public void getName_returnNameOfNormalAnimal_cat(){
        assertEquals("cat", testNormalAnimal.getName());
    }
    @Test
    public void equals_returnsTrueIfNamesAreTheSame_true(){
        NormalAnimal testNormalAnimal1 = new NormalAnimal("cat");
        assertTrue(testNormalAnimal.equals(testNormalAnimal1));
    }
    @Test
    public void save_savesNormalAnimalInstanceToDB_NormalAnimal(){
        testNormalAnimal.save();
        assertTrue(NormalAnimal.all().get(0).equals(testNormalAnimal));
    }
    @Test
    public void all_returnsAllInstancesOfNormalAnimal_true(){
        NormalAnimal testNormalAnimal1 = new NormalAnimal("Dog");
        testNormalAnimal1.save();
        testNormalAnimal.save();
        assertEquals(true, testNormalAnimal1.all().get(0).equals(testNormalAnimal1));
        assertEquals(true, testNormalAnimal.all().get(1).equals(testNormalAnimal));
    }
    @Test
    public void id_assignIdToSpecificInstance(){
        testNormalAnimal.save();
        NormalAnimal savedNormalAnimal = NormalAnimal.all().get(0);
        assertEquals(testNormalAnimal.getId(), savedNormalAnimal.getId());
    }
    @Test
    public void getSighting_retrievesAllSightingFromDB_monsterList(){
        NormalAnimal testNormalAnimal1 = new NormalAnimal("cat");
        testNormalAnimal1.save();
        Sightings firstSightings = new Sightings(testNormalAnimal1.getId(),"Zone A","Lenny");
        firstSightings.save();
        Sightings secondSightings = new Sightings(testNormalAnimal1.getId(),"Zone A","Lenny");
        secondSightings.save();
        Sightings[] sightings = new Sightings[]{firstSightings,secondSightings};
        assertTrue(testNormalAnimal1.getSightings().containsAll(Arrays.asList(sightings)));

    }
}