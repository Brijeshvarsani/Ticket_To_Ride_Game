/* Class representing a link between cities
 */
public abstract class Link implements Comparable<ILink>, ILink {
  public ICity city1;
  public ICity city2;
  public int length;

  /* true if and only if this link is part of the set of shortest paths */
  public boolean used = true;

  /* Construct a Link between c1 and c2 with length len
   * The City alphanumerically smaller is stored as city1 and the other will be city2
   * add the link to both cities
   */
//  public Link(City c1, City c2, Integer len) {
//    if (len == null) {
//      throw new IllegalStateException("len cannot be null");
//    }
//
//    if (c1.compareTo(c2) < 0) {
//      city1 = c1;
//      city2 = c2;
//    } else {
//      city1 = c2;
//      city2 = c1;
//    }
//    length = len;
//    colour = null;
//    c1.addLink(this);
//    c2.addLink(this);
//  }

  public Link(ICity c1, ICity c2, Integer len) {
    if (len == null) {
      throw new IllegalStateException("len cannot be null");
    }

    if (c1.compareTo(c2) < 0) {
      city1 = c1;
      city2 = c2;
    } else {
      city1 = c2;
      city2 = c1;
    }
    length = len;
    c1.addLink(this);
    c2.addLink(this);
  }

  public ICity getCity1(){
    return city1;
  }

  public ICity getCity2(){
    return city2;
  }

  /* return the length of this link */
  public int getLength() {
    return length;
  }

  /* get the opposite city from c
   * return city1 if c is city2
   * return city2 if c is city1
   * behaviour is unspecified if c is not city1 or city2
   */
  public ICity getAdj(ICity c) {
     return c == city1 ? city2 : city1;
  }

  public abstract boolean isValidLink(String property);

  /* return true if this link is on a shortest path and false otherwise */
  public boolean isUsed() {
    return used;
  }

  /* set used to u */
  public void setUsed(boolean u) {
    used = u;
  }

  /* return a string representation of the Link
   * e.g. "City1 3 City2"
   * The city names should be in sorted order, e.g. Halifax comes before Toronto
   */
  public abstract String toString();

  /* compare this Link to Link l
   * returns 0 if both links have the same city1 and city2
   * return negative int if this.city1 < l.city1 or the city1 are equal and this.city2 < l.city2
   * else return a positive int
   */
  public int compareTo(ILink l) {
    int diff = city1.compareTo(l.getCity1());
    if (diff == 0) {
      diff = city2.compareTo(l.getCity2());
    }
    return diff;
  }
}
