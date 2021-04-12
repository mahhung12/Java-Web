/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thuongnnse05095
 */
public class DBContext {

    public Connection getConnection() throws Exception {
//        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        return DriverManager.getConnection(url, userID, password);

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection cnn = DriverManager.getConnection("jdbc:sqlserver://"
                + serverName + ":"
                + portNumber + ";databaseName="
                + dbName + ";integratedSecurity=true;");
        return cnn;
    }

    public void close(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
    private final String serverName = "localhost"; //127.0.0.1
    private final String dbName = "J3.L.P0018";
    private final String portNumber = "1433";
    private final String instance = "";

    public String getResource() {
        return "./image/";
    }

}
