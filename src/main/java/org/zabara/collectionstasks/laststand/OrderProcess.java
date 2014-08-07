package org.zabara.collectionstasks.laststand;

import java.util.List;

/**
 *
 * Created by Yaroslav on 10.02.14.
 * В кругу стоят N человек, пронумерованных от 1 до N.
 * При ведении счета по кругу вычеркивается каждый второй человек, пока не останется один.
 * Составить программу, моделирующую процесс.
 *
 */
public interface OrderProcess <T> {

    public List<T> getLastStand();
}
