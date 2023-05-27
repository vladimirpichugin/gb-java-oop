import Classes.Market;
import Classes.OrdinaryClient;
import Classes.SpecialClient;

import Interfaces.iActorBehaviour;

public class App {
    public static void main(String[] args) throws Exception {
       Market market = new Market();

       iActorBehaviour client0 = new OrdinaryClient("Владимир");
       iActorBehaviour client1 = new SpecialClient("Антон", 1000);

       market.acceptToMarket(client0);
       market.acceptToMarket(client1);

       market.update();
    }
}
