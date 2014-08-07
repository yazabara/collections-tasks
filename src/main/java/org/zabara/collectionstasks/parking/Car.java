package org.zabara.collectionstasks.parking;

import java.util.List;

/**
 * Created by Yaroslav on 13.02.14.
 */
public class Car implements Runnable {

    List parking = null;
    int carNumber = -1;

    Car(int carNumber, List parking) {
        this.carNumber = carNumber;
        this.parking = parking;
    }

    @Override
    public void run() {
        boolean isRun = true;
        while (isRun) {
            for (int i = 0; i < parking.size(); i++) {
				//synchronized (parking){ //list is sync
					if (parking.get(i).equals(-1)) {
						parking.set(i, carNumber);
						isRun = false;
						break;
					}
				//}
            }
        }
    }
}
