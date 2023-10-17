package com.ism.services;

import java.util.ArrayList;

public interface RdvService<T> extends IService<T> {
    ArrayList<T> show() throws ClassNotFoundException;

    ArrayList<T> showMdc(String nom, String date) throws ClassNotFoundException;

    int cancel(int id) throws ClassNotFoundException;
    
}
