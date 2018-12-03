package com.avans.database;

import java.sql.*;

public class Database {

    //TODO: CHANGE TABLE TO A FROM OBJECT!

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

    public static Database get() {
        return database;
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
            System.out.println("Database driver couldn't be found!");
            System.out.println("Shutting down application....");
            System.exit(1);
        }
    }

    private void checkConnection() throws SQLException {
        if (connection.isClosed())
            openConnection();
    }

    /*
     *
     *   Init methods
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

                for(Constraint constraint : table.getConstraints()){
                    query.append(constraint.toString());
                }
            }
            query.append(");");

            this.executeQuery(query.toString());
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

        for (Table table : Table.ALL) {

            for (int i = 1; i < table.getColumns().length; i++) {
                Column column = table.getColumns()[i];

                try {
                    if (data.getColumns(null, null, table.toString(), column.toString()).next())
                        continue;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    continue;
                }

                String query = String.format("ALTER TABLE `%s` ADD COLUMN %s AFTER `%s`;", table.toString(), column.toTypeString(false), table.getColumns()[i - 1].toString());

                this.executeQuery(query);

                //TODO: FIX UP DEFAULT VALUE! & FIX THE CONSTAINT IF ANY NEW HAS BEEN ADDED! CHECK IF THE SQL CODE ALREADY CONTAINS THE CONSTAINT! LOOK INTO THAT!
            }
        }
    }

    public boolean contains(Table table, Column[] columns, Where... wheres) {
        String query = String.format("SELECT %s FROM `%s` %s;", toString(columns), table.toString(), toString(wheres));

        try {
            checkConnection();

            ResultSet rs = connection.prepareStatement(query).executeQuery();

            boolean b1 = rs.next();
            rs.close();

            return b1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean contains(Table table, Column column, Where... wheres) {
        return contains(table, new Column[]{column}, wheres);
    }

    public void insert(Table table, String... values) {
        this.executeQuery(String.format("INSERT INTO `%s` (%s) %s;", table.toString(), toString(table.getColumns()), table.values(values)));
    }

    public void update(Table table, Set set, Where... wheres) {
        update(table, new Set[]{set}, wheres);
    }

    public void update(Table table, Set[] sets, Where[] wheres) {
        try {
            checkConnection();

            Statement s = connection.createStatement();
            s.executeUpdate(String.format("UPDATE `%s` %s%s", table.toString(), toString(sets), toString(wheres)));


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int getCount(Table table, Where... wheres) {
        int count = 0;

        String query = String.format("SELECT COUNT(*) AS count FROM `%s` %s;", table.toString(), toString(wheres));

        try {
            ResultSet rs = connection.prepareStatement(query).executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public long getLongSum(Table table, Column column, Where... wheres) {
        long sum = 0;

        String query = String.format("SELECT SUM(%s) AS sum FROM `%s`%s", column.toString(), table.toString(), toString(wheres));

        try {
            checkConnection();

            ResultSet rs = connection.prepareStatement(query).executeQuery();

            while (rs.next()) {
                sum = rs.getLong("sum");
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return sum;
    }

    public int getInt(Table table, Column column, Where... wheres) {
        int integer = 0;

        String query = String.format("SELECT `%s` FROM `%s`%s", column.toString(), table.toString(), toString(wheres));

        try {
            checkConnection();

            ResultSet rs = connection.prepareStatement(query).executeQuery();

            while (rs.next()) {
                integer = rs.getInt(column.toString());
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return integer;
    }

    public long getLong(Table table, Column column, Where... wheres) {
        long l = 0;

        String query = String.format("SELECT `%s` FROM `%s`%s", column.toString(), table.toString(), toString(wheres));

        try {

            ResultSet rs = connection.prepareStatement(query).executeQuery();

            while (rs.next()) {
                l = rs.getLong(column.toString());
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return l;
    }

    public boolean getBoolean(Table table, Column column, Where... wheres) {
        return "1".equals(getString(table, column, wheres));
    }

    public String getString(Table table, Column column, Where... wheres) {
        String string = "";

        String query = "SELECT `" + column.toString() + "` FROM `" + table.toString() + "`" + toString(wheres) + ";";

        try {
            checkConnection();

            ResultSet rs = connection.prepareStatement(query).executeQuery();

            while (rs.next()) {
                string = rs.getString(column.toString());
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return string;
    }

    private void executeQuery(String query) {
        try {
            checkConnection();

            PreparedStatement ps = connection.prepareStatement(query);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //TODO:!
    private String toString(Set[] sets) {
        return null;
    }

    private String toString(Column[] columns) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < columns.length; i++) {
            sb.append(columns[i]);

            if (i == 0 || i == (columns.length - 1))
                continue;

            sb.append(", ");
        }

        return sb.toString();
    }

    private String toString(Where[] wheres) {


        return null;
    }
}
