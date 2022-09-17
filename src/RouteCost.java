import java.util.*;

import static java.lang.System.exit;

public class  RouteCost {

  public static void main(String [] args) {
    Scanner inp = new Scanner(System.in);
    TreeSet<ILink> links = new TreeSet<ILink>();
    ArrayList<String> playerColor = new ArrayList<>();
    playerColor.add("green");
    playerColor.add("red");

    for (String name = inp.nextLine(); !name.equals("done"); name = inp.nextLine()) {
      String[] arg = name.split(" ");
      ICity c1 = City.find(arg[0]);
      Integer length = null;
      try {
        length = Integer.parseInt(arg[1]);
      }
      catch(NumberFormatException e){
        System.out.println("Invalid line: " + name);
        exit(1);
      }
      if (arg.length < 3 || arg.length > 4){
        System.out.println("Invalid line: " + name);
        exit(1);
      }
      ICity c2 = City.find(arg[2]);

      String colour = null;

      if (arg.length == 4) {
        colour = arg[3];
      }

      if ((arg.length == 4 && playerColor.contains(colour) )) {
        Link l = new ColouredLink(c1, c2, length, colour);
      }
      else if (arg.length == 3){
        Link l = new RegularLink(c1, c2, length);
      }
      else {
        System.out.println("Invalid line: " + name);
        exit(1);
      }
    }
    String colourOne = null;
    int size = 0;
    for (String name = inp.nextLine(); !name.equals("done"); name = inp.nextLine()) {
      String[] argTwo = name.split(" ");
      ICity c = City.find(argTwo[0]);
      size = argTwo.length;
      if (argTwo.length == 3) {
        colourOne = argTwo[2];
      }
      c.makeTree(colourOne);
      if (argTwo.length == 3 && playerColor.contains(colourOne)){
        if (!c.getLinksTo(City.find(argTwo[1]), links, argTwo[2])) {
          System.out.println("Error: Route not found! " + name);
          return;
        }
      }
      else if (argTwo.length == 2) {
        if (!c.getLinksTo(City.find(argTwo[1]), links, null)) {
          System.out.println("Error: Route not found! " + name);
          return;
        }
      }
      else {
          System.out.println("Invalid line: " + name);
          exit(1);
      }

    }

    int total = 0;
    System.out.println("The rail network consists of:");
    for (ILink l : links) {
      System.out.println(l);
//      if (l.getColour() != null){
//        System.out.println("  " + l + " " + l.getColour());
//      }else {
//        System.out.println("  " + l );
//      }
      total += l.getLength();
    }
    System.out.println("The total cost is: " + total);
  }
}
