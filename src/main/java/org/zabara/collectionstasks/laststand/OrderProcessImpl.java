package org.zabara.collectionstasks.laststand;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yaroslav on 10.02.14.
 */
public class OrderProcessImpl<T> implements OrderProcess<T> {

    private List<T> list = new LinkedList<T>();
    //номер, обьекта, которго будут удалять
    private int goNaxIndex = 1;
    //количество "живых"
    private int lastStandCount = 1;

    OrderProcessImpl(List<T> array) {
        this.list = new LinkedList<T>(array);
    }

    OrderProcessImpl(List<T> array, int goNaxIndex) {
        this.list = new LinkedList<T>(array);
        this.goNaxIndex = goNaxIndex;
    }

    OrderProcessImpl(List<T> array, int goNaxIndex, int lastStandCount) {
        this.list = new LinkedList<T>(array);
        this.goNaxIndex = goNaxIndex;
        this.lastStandCount = lastStandCount;
    }

    @Override
    public List<T> getLastStand() {
        //убираем повторения и лишние итерации
        goNaxIndex = goNaxIndex % list.size();
        //ищем сразу кого удалить
        int i = goNaxIndex;
        while (list.size() > lastStandCount) {
            i = fixIndex(i);
            list.remove(i);
            i = i + goNaxIndex - 1;
        }
        return list;
    }

    private int fixIndex(int index) {
        if (index >= list.size()) {
            index = index - list.size();
        }
        return index;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
