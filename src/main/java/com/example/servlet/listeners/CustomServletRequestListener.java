package com.example.servlet.listeners;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class CustomServletRequestListener implements ServletRequestListener {
    private final String NAME_ATTRIBUTE = "Name";

    public void requestInitialized(ServletRequestEvent sre){
        ServletRequest servletRequest = sre.getServletRequest();
        servletRequest.setAttribute(NAME_ATTRIBUTE,"VOLHA");
        System.out.println("Servlet Request initialized. IP = " + servletRequest.getRemoteHost());
    }
    public void requestDestroyed(ServletRequestEvent sre){
        ServletRequest servletRequest = sre.getServletRequest();
        System.out.println(servletRequest.getAttribute(NAME_ATTRIBUTE));
        System.out.println("Servlet Request destroyed. IP = " + servletRequest.getRemoteHost());

    }
}
