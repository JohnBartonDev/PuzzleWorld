package com.john.jam.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class EventManager<T> {

    private boolean disableEvents;
    private ObjectMap<Integer, Array<EventListener<T>>> events;

    public EventManager(int... ev) {
        events = new ObjectMap<>();

        for (int e : ev) {
            createEvent(e);
        }
    }

    public void createEvent(int event) {
        events.put(event, new Array<>());
    }

    public void createEvents(int... events) {
        for (int e : events) {
            createEvent(e);
        }
    }

    public void subscribe(int type, EventListener<T> listener) {
        events.get(type).add(listener);
    }

    public void unsubscribe(int type, EventListener<T> listener) {
        events.get(type).removeValue(listener, false);
    }

    public void notify(int type, T t) {
        Array<EventListener<T>> listeners = events.get(type);

        for (EventListener<T> l :listeners) {
            l.update(type, t);
        }
    }
}
