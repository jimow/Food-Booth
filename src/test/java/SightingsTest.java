import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class SightingsTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    Sightings testSighting =new Sightings(1,"Zone A","Lenny");

    @Test
    public void sighting_instanceOfSightings_true(){
        assertEquals(true, testSighting instanceof Sightings);
    }
    @Test
    public void getAnimalId_getTheAnimalId_int(){
        assertEquals(1,testSighting.getAnimalId());
    }
    @Test
    public void getLocation_getSightingLocation_location(){
        assertEquals("Zone A",testSighting.getSightLocation());
    }
    @Test
    public void getRanger_getTheRangerName_ranger(){
        assertEquals("Lenny",testSighting.getRangerName());
    }
    @Test
    public void equal_checkWhetherSightingsContentAreTheSame_true(){
        Sightings testSighting1 =new Sightings(1,"Zone A","Lenny");
        assertTrue(testSighting.equals(testSighting1));
    }
    @Test
    public void save_returnsTrueIfTheSavedAreEqual(){
        testSighting.save();
        assertTrue(Sightings.all().get(0).equals(testSighting));
    }
    @Test
    public void save_assignsIdToSpecificInstance(){
        testSighting.save();
        Sightings savedSightings = Sightings.all().get(0);
        assertEquals(savedSightings.getId(),testSighting.getId());
    }
    @Test
    public void all_returnsAllInstancesOfSightings_true(){
        testSighting.save();
        Sightings testSighting1 =new Sightings(1,"ZoneA","Lenny");
        testSighting1.save();
        assertEquals(true,Sightings.all().get(0).equals(testSighting));
        assertEquals(true,Sightings.all().get(1).equals(testSighting1));

    }
    @Test
    public void find_returnSpecificSighting_testSighting1(){
        testSighting.save();
        Sightings testSighting1 =new Sightings(1,"Zone A","Lenny");
        testSighting1.save();
        assertEquals(Sightings.find(testSighting1.getId()),testSighting1);
    }
    @Test
    public void save_saveSAnimalIDIntoDb_true(){
        NormalAnimal testNormalAnimal = new NormalAnimal("cat");
        testNormalAnimal.save();
        Sightings testSighting1 =new Sightings(testNormalAnimal.getId(),"Zone A","Lenny");
        testSighting1.save();
        Sightings savedSighting = Sightings.find(testSighting1.getId());
        assertEquals(savedSighting.getAnimalId(), testNormalAnimal.getId());
    }
    @Test
    public void save_recordsWhenTheSightingWasViewed(){
        testSighting.save();
        Timestamp sightingViewed = Sightings.find(testSighting.getId()).getViewed();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(rightNow.getDay(),sightingViewed.getDay());
    }

}