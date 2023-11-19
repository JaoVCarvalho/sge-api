package ufla.sge.api.controller;

import java.util.Date;

public class Log {

    private static final Log instance = new Log();
    private Log() {
    }
    public static Log getInstance() {
        return instance;
    }

    public void log(String message) {
        System.out.println("[" + new Date() + "] " + message);
    }
}