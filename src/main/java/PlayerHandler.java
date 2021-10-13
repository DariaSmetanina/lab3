import sofia_kp.SSAP_sparql_response;
import sofia_kp.iKPIC_subscribeHandler2;
import wrapper.SmartSpaceException;
import wrapper.SmartSpaceKPI;
import wrapper.SmartSpaceTriple;

import java.util.Vector;


public class PlayerHandler implements iKPIC_subscribeHandler2 {

    SmartSpaceKPI kpi;

    String name;
    String somedata;

    PlayerHandler(String nm) throws SmartSpaceException {
        kpi=new SmartSpaceKPI("127.0.0.1", 10010, "y");
        name=nm;
        kpi.insert(new SmartSpaceTriple(name,"is","ready"));
        kpi.subscribe(new SmartSpaceTriple(null,null,null),this);
    }

    @Override
    public void kpic_RDFEventHandler(Vector<Vector<String>> vector, Vector<Vector<String>> vector1, String s, String s1) {
        for (Vector<String> data : vector) {
            if(data.get(0).equals(name) && data.get(1).equals("serve")){
                try {
                    somedata = "Sensor data";
                    GetInfoHowToServe(somedata);
                    kpi.insert(new SmartSpaceTriple(name,"try_win", "point"));
                } catch (SmartSpaceException e) {
                    e.printStackTrace();
                }
            }
            else if (!data.get(0).equals(name) && data.get(1).equals("serve")){
                try {
                    somedata = "Sensor data";
                    GetInfoHowToSave(somedata);
                    kpi.insert(new SmartSpaceTriple(name,"try_save", "point"));
                } catch (SmartSpaceException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void kpic_SPARQLEventHandler(SSAP_sparql_response ssap_sparql_response, SSAP_sparql_response ssap_sparql_response1, String s, String s1) {

    }

    @Override
    public void kpic_UnsubscribeEventHandler(String s) {

    }

    @Override
    public void kpic_ExceptionEventHandler(Throwable throwable) {

    }

    public void GetInfoHowToServe(String data){
        /* TODO */
    }
    public void GetInfoHowToSave(String data){
        /* TODO */
    }
}

