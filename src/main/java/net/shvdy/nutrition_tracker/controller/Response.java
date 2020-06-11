package net.shvdy.nutrition_tracker.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public enum Response {
    FORWARD((commandResult, request, response) -> {
        request.getRequestDispatcher(commandResult).forward(request, response);
    }),
    REDIRECT((commandResult, request, response) -> {
        response.sendRedirect(request.getContextPath() + commandResult);
    }),
    JSON((commandResult, request, response) -> {
        respondWithJSON(response, commandResult);
    }),
    OK_200((commandResult, request, response) -> {
        response.setStatus(HttpServletResponse.SC_OK);
    }),
    NOT_FOUND_404((commandResult, request, response) -> {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    });

    private ResponseAction action;

    Response(ResponseAction action) {
        this.action = action;
    }

    public ResponseAction execute() {
        return action;
    }

    private static void respondWithJSON(HttpServletResponse response, String JSONString) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(JSONString);
        out.flush();
    }
}


