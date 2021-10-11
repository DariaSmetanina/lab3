import sofia_kp.SSAP_sparql_response;
import sofia_kp.iKPIC_subscribeHandler2;
import wrapper.SmartSpaceKPI;

import java.util.Vector;

public class RefereeHandler implements iKPIC_subscribeHandler2 {

    SmartSpaceKPI kpi;

    RefereeHandler(SmartSpaceKPI kp) {
        kpi = kp;
    }

    @Override
    public void kpic_RDFEventHandler(Vector<Vector<String>> vector, Vector<Vector<String>> vector1, String s, String s1) {
        for (Vector<String> data : vector) {

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
}
