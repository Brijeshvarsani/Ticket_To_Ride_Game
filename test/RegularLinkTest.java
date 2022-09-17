import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegularLinkTest {
    public static final String city1Name = "City1";
    public static final String city2Name = "City2";
    public static final String city3Name = "City3";
    public static final int cityDistanceShort = 1;
    public static final int cityDistanceLong = 2;

    @Test
    void other_player_can_use_uncolored_link(){
        ICity city1 = new City(city1Name);
        ICity city2 = new City(city2Name);
        RegularLink l = new RegularLink(city1 , city2 , cityDistanceLong);
        assertTrue(l.isValidLink("red" ) , "Any player can use uncoloured link");
    }
}