package org.zabara.collectionstasks.parking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yaroslav on 13.02.14.
 */
public class Parking {

    public List showParking(int carCount) {
        //init
        List list = initList(carCount);
        List<Thread>cars = new ArrayList<Thread>();

        for (int i = 0; i < carCount; i++) {
            Thread car = new Thread(new Car(i, list));
            cars.add(car);
            car.start();
        }

        /**
         * Each join will block until the respective thread has completed.
         * Threads may complete in a different order than you joining them,
         * but that's not a problem: when the loop exits, all threads are completed.
         */
        for(Thread car : cars)
            try {
				car.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return list;
    }

    private List initList(int carCount) {
        List list = new ArrayList(carCount);
        for (int i = 0; i < carCount; i++) {
            list.add(-1);
        }
        list = Collections.synchronizedList(list);
        return list;
    }
}
