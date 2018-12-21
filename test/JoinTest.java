import com.avans.database.Column;
import com.avans.database.Join;
import com.avans.database.Table;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
    Created By Robin Egberts On 12/21/2018
    Copyrighted By OrbitMines ©2018
*/
class JoinTest {

    private static TestTable testTable;
    private static TestTable testTable2;
    private static TestTable testTable3;

    private static Column testColumn;
    private static Column testColumn2;
    private static Column testColumn3;

    @BeforeAll
    static void setupTest(){
        testColumn = new Column("test", Column.Type.VARCHAR, 50);
        testColumn2 = new Column("test", Column.Type.VARCHAR, 50);
        testColumn3 = new Column("test", Column.Type.VARCHAR, 50);

        testTable = new TestTable("Test1", testColumn);
        testTable2 = new TestTable("Test2", testColumn2);
        testTable3 = new TestTable("Test3", testColumn3);
    }

    @Test
    void testJoinConstructorWithTrueArguments(){
        assertDoesNotThrow(() -> new Join(Join.Type.INNER_JOIN, testColumn, testColumn2));
    }

    @Test
    void testJoinConstructorWithFalseArguments(){
        assertThrows(IllegalStateException.class, () -> new Join(Join.Type.INNER_JOIN, testColumn, null));
    }

    @Test
    void testJoinConstructorWithTheSameArgument(){
        assertThrows(IllegalStateException.class, () -> new Join(Join.Type.INNER_JOIN, testColumn, testColumn));
    }

    @Test
    void testJoinToStringWithTestTable1AndTestTable2InAnInnerJoin(){
        //Arrange
        String expectedResult = "Test1 INNER JOIN Test2 ON (Test1.test = Test2.test)";
        Join join = new Join(Join.Type.INNER_JOIN, testColumn, testColumn2);

        //Act
        String result = join.toString();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testJoinToStringWithTestTable1AndTestTable2InAnCrossJoin(){
        //Arrange
        String expectedResult = "Test1 CROSS JOIN Test2";
        Join join = new Join(Join.Type.CROSS_JOIN, testColumn, testColumn2);

        //Act
        String result = join.toString();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testJoinToStringWithTestTable1AndTestTable2InAChildJoin(){
        //Arrange
        String expectedResult = "Test1 INNER JOIN Test2 ON (Test1.test = Test2.test) INNER JOIN Test3 ON (Test2.test = Test3.test)";
        Join join = new Join(Join.Type.INNER_JOIN, testColumn, testColumn2);
        Join childJoin = new Join(Join.Type.INNER_JOIN, testColumn2, testColumn3);
        join.addChild(childJoin);

        //Act
        String result = join.toString();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testJoinToStringWithTestTable1AndTestTable2InACrossChildJoin(){
        //Arrange
        String expectedResult = "Test1 INNER JOIN Test2 ON (Test1.test = Test2.test) CROSS JOIN Test3";
        Join join = new Join(Join.Type.INNER_JOIN, testColumn, testColumn2);
        Join childJoin = new Join(Join.Type.CROSS_JOIN, testColumn2, testColumn3);

        join.addChild(childJoin);

        //Act
        String result = join.toString();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void testJoinToStringWithTestTable1AndTestTable2InACrossJoin(){
        //Arrange
        String expectedResult = "Test1 CROSS JOIN Test2 CROSS JOIN Test3";
        Join join = new Join(Join.Type.CROSS_JOIN, testColumn, testColumn2);
        Join childJoin = new Join(Join.Type.CROSS_JOIN, testColumn2, testColumn3);
        join.addChild(childJoin);

        //Act
        String result = join.toString();

        //Assert
        assertEquals(expectedResult, result);
    }

    static class TestTable extends Table{

        TestTable(String name, Column... columns) {
            super(name, columns);
        }
    }
}