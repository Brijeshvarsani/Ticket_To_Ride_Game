import java.util.HashSet;
import java.util.Set;

public interface ICity {
    static ICity find(String nm) {
        ICity p = City.cities.get(nm);
        if (p == null) {
            p = new City(nm);
            return p;
        }
        return p;
    }
    void addLink(ILink lnk);
    int compareTo(ICity p);
    String getName();
    ILink getParent();
    void setParent(ILink par);
    int getDistance();
    void setDistance(int d);
    HashSet<ILink> getLinks();
    String toString();
    int compare(ICity c1, ICity c2);
    boolean getLinksTo(ICity dest, Set<ILink> routeLinks, String colour);
    void makeTree(String colour);
}
