package com.avans.database;

import java.sql.*;

public class Database {

    private static Database database;

    private final String hostName;
    private final int port;
    private final String databaseName;
    private final String userName;
    private final String password;

    private Connection connection;

    public Database(String hostName, int port, String databaseName, String userName, String password) {
        this.hostName = hostName;
        this.port = port;
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;

        database = this;
    }

    /*
     *
     *   Connection methods
     *
     */
    public void openConnection() {
        try {
            this.connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", hostName, port, databaseName), userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkConnection() throws SQLException {
        if (connection.isClosed())
            openConnection();
    }

    public static Database get() {
        return database;
    }

    /*
     *
     *   Ivnit methods
     *
     */
    public void setupTables() {
        for (Table table : Table.ALL) {

            /* Create table if it does not exist */
            StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS `").append(table.toString()).append("` (");

            for (int i = 0; i < table.getColumns().length; i++) {
                if (i != 0)
                    query.append(", ");

                Column column = table.getColumns()[i];
                query.append(column.toTypeString(true));
            }
            query.append(");");

            try {
                checkConnection();

                PreparedStatement ps = connection.prepareStatement(query.toString());
                ps.execute();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        setupColumns();
    }

    private void setupColumns() {
        DatabaseMetaData data;

        try {
            checkConnection();

            data = connection.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        //TODO: FINISH UP THE COLUMNS!
        for(Table table : Table.ALL){

        }
    }
}
