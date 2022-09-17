import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ColouredLinkTest {
    public static final String city1Name = "City1";
    public static final String city2Name = "City2";
    public static final String city3Name = "City3";
    public static final int cityDistanceShort = 1;
    public static final int cityDistanceLong = 2;

    @Test
    void constructor_right_color(){
        ICity city1 = new City(city1Name);
        ICity city2 = new City(city2Name);
        String playerColour = "red";
        ColouredLink l = new ColouredLink(city1 , city2 , cityDistanceLong , playerColour);
        assertEquals("red" , l.getColour() , "Method should return correct colour");
    }

    @Test
    void constructor_wrong_color(){
        ICity city1 = new City(city1Name);
        ICity city2 = new City(city2Name);
        String playerColour = "red";
        ColouredLink l = new ColouredLink(city1 , city2 , cityDistanceLong , playerColour);
        assertNotEquals("green" , l.getColour() , "Method should return correct colour");
    }

    @Test
    void checkValidLink_if_wrong_colour_passed(){
        ICity city1 = new City(city1Name);
        ICity city2 = new City(city2Name);
        String playerColour = "red";
        ColouredLink l = new ColouredLink(city1 , city2 , cityDistanceLong , "green");
        assertFalse(l.isValidLink(playerColour));
    }

    @Test
    void checkValidLink_if_player_uses_uncoloured_link(){
        ICity city1 = new City(city1Name);
        ICity city2 = new City(city2Name);
        String playerColour = "red";
        RegularLink l = new RegularLink(city1 , city2 , cityDistanceLong);
        assertTrue(l.isValidLink(playerColour));
    }

    @Test
    void checkValidLink_if_right_colour_passed(){
        ICity city1 = new City(city1Name);
        ICity city2 = new City(city2Name);
        String playerColour = "red";
        ColouredLink l = new ColouredLink(city1 , city2 , cityDistanceLong , "red");
        assertTrue(l.isValidLink(playerColour));
    }

    @Test
    void toStringMethod(){
        ICity city1 = new City(city1Name);
        ICity city2 = new City(city2Name);
        String playerColour = "red";
        ColouredLink l = new ColouredLink(city1 , city2 , cityDistanceLong , playerColour);
        assertEquals("City1 2 City2 red" , l.toString());
    }

}