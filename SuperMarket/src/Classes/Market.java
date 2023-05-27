package Classes;

import java.util.ArrayList;
import java.util.List;

import Interfaces.iActorBehaviour;
import Interfaces.iMarketBehaviour;
import Interfaces.iQueueBehaviour;

public class Market implements iMarketBehaviour,iQueueBehaviour{
    //private List<Actor> queue;
    private List<iActorBehaviour> queue;

    public Market() {
        this.queue = new ArrayList<iActorBehaviour>();
    }

    @Override
    public void acceptToMarket(iActorBehaviour actor) {
        System.out.println("Клиент «" + actor.getActor().getName() + "» пришёл в магазин.");
        takeInQueue(actor);
    }

    @Override
    public void takeInQueue(iActorBehaviour actor) {
        this.queue.add(actor);
        System.out.println("Клиент «" + actor.getActor().getName() + "» теперь в очереди.");
    }

    @Override
    public void releaseFromMarket(List<Actor> actors) {
        for(Actor actor:actors)
        {
            System.out.println("Клиент «" + actor.getName() + "» покинул магазин.");
            queue.remove(actor);
        }
        
    }

    @Override
    public void update() {
       takeOrder();
       giveOrder();
       releaseFromQueue();
    }

    @Override
    public void giveOrder() {
        for(iActorBehaviour actor: queue)
        {
            if(actor.isMakeOrder())
            {
                actor.setTakeOrder(true);
                System.out.println("Клиент «" + actor.getActor().getName() + "» забрал заказ.");
            }
        }
        
    }

    @Override
    public void releaseFromQueue() {
       List<Actor> releaseActors = new ArrayList<>();
       for(iActorBehaviour actor:queue)
       {
        if(actor.isTakeOrder())
        {
            releaseActors.add(actor.getActor());
            System.out.println("Клиент «" + actor.getActor().getName() + "» покинул очередь.");
        }

       }
    releaseFromMarket(releaseActors);
    }



    @Override
    public void takeOrder() {
        for(iActorBehaviour actor:queue)
        {
            if(!actor.isMakeOrder())
            {
                actor.setMakeOrder(true);
                System.out.println("Клиент «" + actor.getActor().getName() + "» оформил заказ.");

            }
        }
        
    }
}
