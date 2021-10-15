import sofia_kp.SSAP_sparql_response;
import sofia_kp.iKPIC_subscribeHandler2;
import wrapper.SmartSpaceException;
import wrapper.SmartSpaceKPI;
import wrapper.SmartSpaceTriple;

import java.util.Vector;

public class RefereeHandler implements iKPIC_subscribeHandler2 {

    SmartSpaceKPI kpi;
    private Player player1;
    private Player player2;
    private String result;

    RefereeHandler(SmartSpaceKPI kp) {
        kpi = kp;
        player1 = new Player();
        player2 = new Player();
    }

    @Override
    public void kpic_RDFEventHandler(Vector<Vector<String>> vector, Vector<Vector<String>> vector1, String s, String s1) {
        for (Vector<String> data : vector) {
            /// сообщение о готовности, начало игры
            // > user is ready
            // < user serve game
            if(data.get(1).equals("is")){
                if(!player1.isReady()){
                    player1.setName(data.get(0));
                    player1.setReady();
                }
                else{
                    player2.setName(data.get(0));
                    player2.setReady();
                }
                if(player1.isReady() && player2.isReady()){
                    int randomNumber=((int)(Math.random() * (1)));
                    String userName="";
                    if(randomNumber==0){
                        userName= player1.getName();
                    }
                    else{
                        userName= player2.getName();
                    }
                    try {
                        kpi.insert(new SmartSpaceTriple(userName,"serve","game"));
                    } catch (SmartSpaceException e) {
                        e.printStackTrace();
                    }
                }
            }
            /// отслеживание игры, начисление баллов
            // > user try_save point
            // < user serve game
            /// остановка игры 11+
            // < user wins game
            if(data.get(1).equals("try_save")){
                SmartSpaceTriple tri = watchGame();
                try {
                    kpi.remove(new SmartSpaceTriple(null,"serve","game"));
                    kpi.insert(tri);
                } catch (SmartSpaceException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private SmartSpaceTriple watchGame(){
        int randomNumber=((int)(Math.random() * (102)));
        String subj="";
        String prdc="serve";
        String obj="game";
        if(randomNumber%2==1){
            if(player1.increaseScore()){
                subj=player2.getName();
            }
            else{
                subj=player1.getName();
                prdc="wins";
                obj="game";
                result = player1.score+":"+player2.score +" "+ subj +" "+ prdc;
                System.out.println(result);
            }
        }
        if(randomNumber%2==0){
            if(player2.increaseScore()){
                subj=player1.getName();
            }
            else{
                subj=player2.getName();
                prdc="wins";
                obj="game";
                result = player1.score+":"+player2.score +" "+ subj +" "+ prdc;
                System.out.println(result);
            }
        }
        System.out.println(subj + prdc + obj);
        return new SmartSpaceTriple(subj, prdc, obj);
    }

    private class Player{
        private String name="";
        private boolean ready=false;
        private int score=0;

        private String getName() {
            return name;
        }

        private void setName(String name) {
            this.name = name;
        }

        private boolean isReady() {
            return ready;
        }

        private void setReady() {
            this.ready = true;
        }

        private boolean increaseScore() {
            this.score++;
            if(score<11){
                return true;
            }
            else{
                return false;
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

    public String getResults(){
        return result;
    }

}
