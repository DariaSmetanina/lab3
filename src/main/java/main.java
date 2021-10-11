import wrapper.SmartSpaceException;
import wrapper.SmartSpaceKPI;
import wrapper.SmartSpaceTriple;

import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Scanner;


public class main {
    public static void main(String args[]) throws ParseException {
        try {
            System.out.println("___________________________________________");
            System.out.println("Tennis");
            SmartSpaceKPI kp1 = new SmartSpaceKPI("127.0.0.1", 10010, "y");
            kp1.remove(new SmartSpaceTriple(null,null,null));

            SmartSpaceTriple subscrAll = new SmartSpaceTriple(null,null,null);

            RefereeHandler server = new RefereeHandler(kp1);
            kp1.subscribe(subscrAll, server);

            PlayerHandler p1=new PlayerHandler( "p1");
            PlayerHandler p2=new PlayerHandler( "p2");

            boolean exit=true;
            while(exit) {
                if (new Scanner(new InputStreamReader(System.in)).nextLine().length() > 0) {
                    exit=false;
                }
            }

            kp1.unsubscribe(subscrAll, true);

            kp1.leave();

            System.out.println("end");
        } catch (SmartSpaceException e) {
            e.printStackTrace();
        }
    }
}
