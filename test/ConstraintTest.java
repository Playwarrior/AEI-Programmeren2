/*
    Created By Robin Egberts On 1/18/2019
    Copyrighted By OrbitMines ©2019
*/

import com.avans.database.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstraintTest {

    private static ColumnKey key1;
    private static ColumnKey key2;
    private static ColumnKey key3;

    @BeforeAll
    void init(){
        key1 = new ColumnKey("key1", Column.Type.VARCHAR, ColumnKey.Key.PRIMARY, 36);
        new TestTable("Test1", key1);

        key2 = new ColumnKey("key2", Column.Type.VARCHAR, ColumnKey.Key.FOREIGN, 36);
        new TestTable("Test2", key2);

        key3 = new ColumnKey("key3", Column.Type.VARCHAR, ColumnKey.Key.CANDIDATE, 36);
        new TestTable("Test3", key1);
    }


    @Test
    void testWithConstraintPrimaryKeyToString(){
        //Arrange
        String expectedResult = "CONSTRAINT PKTest1 PRIMARY KEY (key1)";
        Constraint cs = new Constraint("Test1", Constraint.Type.PRIMARY, key1);

        //Act
        String result = cs.toString();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testWithConstraintForeignKeyToString(){
        //Arrange
        String expectedResult = "CONSTRAINT FKTest2 FOREIGN KEY (key1)";
        Constraint cs = new Constraint("Test2", Constraint.Type.FOREIGN, key1, key2);

        //Act
        String result = cs.toString();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testWithConstraintForeignKeyToStringWithResponses(){
        //Arrange
        String expectedResult = "CONSTRAINT FKTest2 FOREIGN KEY (key1) ON DELETE CASCADE";
        Constraint cs = new Constraint("Test2", Constraint.Type.FOREIGN, key1, key2);
        cs.addResponses(Constraint.Action.ON_DELETE, Constraint.Response.CASCADE);

        //Act
        String result = cs.toString();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testWithConstraintUniqueConstraintToString(){
        //Arrange
        String expectedResult = "CONSTRAINT UQTest3 UNIQUE (key3)";
        Constraint cs = new Constraint("Test3", Constraint.Type.UNIQUE, key3);

        //Act
        String result = cs.toString();

        //Assert
        assertEquals(expectedResult, result);
    }

    static class TestTable extends Table {

        TestTable(String name, Column... columns) {
            super(name, columns);
        }
    }
}
