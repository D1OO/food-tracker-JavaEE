package net.shvdy.nutrition_tracker.model.dao.impl;

import java.sql.SQLException;

/**
 * 03.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class SQLRuntimeException extends RuntimeException {

    public SQLRuntimeException(SQLException e) {
        super(e);
    }
}
