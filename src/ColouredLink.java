public class ColouredLink extends Link{

    private String colour;

    public ColouredLink(ICity c1, ICity c2, Integer len, String colour){
        super(c1, c2, len);
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public boolean isValidLink(String colourToCheck) {
        return getColour() == null || getColour().equals(colourToCheck);
    }

    @Override
    public String toString()  {
        return city1.toString() + " " + length + " " + city2.toString() + " " + colour;
    }
}
