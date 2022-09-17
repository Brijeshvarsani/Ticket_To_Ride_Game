import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

/* Class representing a City
 */
public class City implements ICity {
  /* lookup table of all cities by name */
  public static HashMap<String, ICity> cities = new HashMap<>();
  public String name;
  /* adjacent Links */
  public final HashSet<ILink> links = new HashSet<>();
  /* shortest path distance */
  public int distance;
  /* shortest path parent */
  public ILink parent;

  /* construct a City with name nm
   * add to the HashMap of cities
   */
  public City(String nm) {
    name = nm;
    cities.put(name, this);
  }

  /* find a city with name nm in cities
   * return the city if it exists
   * otherwise return a new city with that name
   */
  public static ICity find(String nm) {
    ICity p = cities.get(nm);
    if (p == null) {
      p = new City(nm);
      return p;
    }
    return p;
  }

  /* add a link to links
   */
  public void addLink(ILink lnk) {
    links.add(lnk);
  }

  /* compare cities by their names
   * return: negative if c1 is alphanumerically less,
   *  0 if names are the same,
   *  positive if c2 is alphanumerically less
   */
  public int compareTo(ICity p) {
    return name.compareTo(p.getName());
  }

  public String getName() {
    return name;
  }

  public ILink getParent() {
    return parent;
  }
  public void setParent(ILink par){
    parent = par;
  }

  public int getDistance(){
    return distance;
  }
  public void setDistance(int d){
    distance = d;
  }
  public HashSet<ILink> getLinks(){
    return links;
  }

  /* return the name of the city
   */
  public String toString() {
    return name;
  }

  /* compare cities by their distance from the start of the rail network
   * return: negative if c1 is closer to 0, 0 if equal distance, positive if c2 is closer to 0
   */
  public int compare(ICity c1, ICity c2) {
    return c1.getDistance() - c2.getDistance();
  }

  /* find a path from this to dest
   * return true if a path is found and false otherwise
   * add the followed Links to routeLinks
   */
  public boolean getLinksTo(ICity dest, Set<ILink> routeLinks, String colour) {
    for (ILink l : links) {
      if (l.isUsed() && (l != parent)) {
        if (l.isValidLink(colour)){
          ICity child = l.getAdj(this);
          if ((dest == child) || child.getLinksTo(dest, routeLinks, colour)) {
            routeLinks.add(l);
            return true;
          }
        }
      }
    }
    return false;
  }

  /* create a shortest path tree starting from this City
   * uses Dijkstra's shortest paths algorithm
   * set the distance of this City to 0 and others to infinity
   * consider each found City closest to the start
   *   update the best known distance to all adjacent cities
   * postcondition: every City.distance is the shortest distance from this to that City
   * postcondition: every City.parent is the Link before that City in the set of shortest paths
   */
  public void makeTree(String colour) {
    Comparator<ICity> comparator = new CityComparator();
    PriorityQueue<ICity> pq = new PriorityQueue<ICity>(comparator);
    for (ICity c : cities.values()) {
      if (c != this) {
        c.setDistance(Integer.MAX_VALUE);
      } else {
        c.setDistance(0);
      }
      pq.add(c);
      c.setParent(null);
      for (ILink l : c.getLinks()) {
        l.setUsed(false);
      }
    }
 
    HashSet<ICity> tree = new HashSet<>();
    while (!pq.isEmpty()) {
      ICity city = pq.poll();
      if (city.getParent() !=  null) {
        city.getParent().setUsed(true);
      }
      tree.add(city);

      for (ILink l : city.getLinks()) {
        if (l.isValidLink(colour)){
          ICity child = l.getAdj(city);
          if (!tree.contains(child)) {
            int length = l.getLength();

            if (child.getDistance() > city.getDistance() + length) {
              pq.remove(child);
              child.setDistance(city.getDistance() + length);
              child.setParent(l);
              pq.add(child);
            }
          }
        }

      }
    }
  }
}
