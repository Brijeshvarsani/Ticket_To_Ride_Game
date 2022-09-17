public class RegularLink extends Link{

    private String colour;
    public RegularLink(ICity c1, ICity c2, Integer len){
        super(c1, c2, len);
    }
    @Override
    public boolean isValidLink(String property) {
        return true;
    }

    @Override
    public String toString()  {
        return city1.toString() + " " + length + " " + city2.toString();
    }
}
