package net.shvdy.nutrition_tracker.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FunctionalInterface
public interface ResponseAction {

    void response(String path, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;
}
