package com.example.servlet.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


@WebListener
public class CustomServletListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println(se);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println(se);
    }

}
