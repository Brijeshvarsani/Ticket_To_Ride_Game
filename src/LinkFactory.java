import java.util.ArrayList;
import java.util.Arrays;

public class LinkFactory {

    static ArrayList<String> playerColor = new ArrayList<>(Arrays.asList("green", "red"));

    public static ILink getLink(ICity c1, ICity c2, int length, String property) {
        if (property == null) {
            return new RegularLink(c1, c2, length);
        }
        else if (playerColor.contains(property)) {
            return new ColouredLink(c1, c2, length, property);
        }
        else {
            return null;
        }
    }
}
