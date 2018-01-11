package com.hyike.zuuldemo;

public class SingletonBest {
    private static SingletonBest singletonBest;

    private SingletonBest(){}

    public static SingletonBest getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static SingletonBest instance = new SingletonBest();
    }
}
