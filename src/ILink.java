public interface ILink {
    int getLength();
    ICity getAdj(ICity c);
    boolean isUsed();
    void setUsed(boolean u);
    String toString();
    boolean isValidLink(String property);
    int compareTo(ILink l);
    ICity getCity1();
    ICity getCity2();
}
