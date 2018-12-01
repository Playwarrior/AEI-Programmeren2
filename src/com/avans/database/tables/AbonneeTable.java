package com.avans.database.tables;

import com.avans.database.Column;
import com.avans.database.ColumnKey;
import com.avans.database.Constraint;
import com.avans.database.Table;

public class AbonneeTable extends Table {

    public static final ColumnKey NAME = new ColumnKey("NAME", Column.Type.VARCHAR, Table.ABONNEETABLE, ColumnKey.Key.PRIMARY);

    public AbonneeTable() {
        super("Abonnee", NAME);

        this.addConstraint(new Constraint("Abonnee", Constraint.Type.PRIMARY, NAME));

    }
}
