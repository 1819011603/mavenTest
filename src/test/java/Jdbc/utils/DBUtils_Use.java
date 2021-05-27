package Jdbc.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Apache-DBUtils_Use使用
 */
public class DBUtils_Use {

    @Test
    public void testDML() throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtilsByDruid.getDs());
        int affectRows = runner.update("update actor set name=? where id = ?","张三丰",3);
        System.out.println(affectRows > 0?"success":"failed");
        affectRows = runner.update("insert into actor values(null,?,?,?,?)","林青霞","女","1984-01-21","744");
        System.out.println(affectRows > 0?"success":"failed");
        affectRows = runner.update("delete from actor where id= ?",4);
        System.out.println(affectRows > 0?"success":"failed");

    }
    @Test
    public void testQueryMany1() throws Exception{

        QueryRunner queryRunner = new QueryRunner(JDBCUtilsByDruid.getDs());
        List<Actor> list = queryRunner.query("select * from actor",new BeanListHandler<>(Actor.class));
        for (Actor actor:list){
            System.out.println(actor);
        }


        System.out.println("actor1表： ");
        List<Actor1> list1 = queryRunner.query("select * from actor1",new BeanListHandler<>(Actor1.class));
        for (Actor1 actor:list1){
            System.out.println(actor);
        }

    }
    @Test
    public void testQueryMany() throws Exception{
        Connection connection = JDBCUtilsByDruid.getConnection();

        QueryRunner queryRunner = new QueryRunner();
        List<Actor> list = queryRunner.query(connection,"select * from actor",new BeanListHandler<>(Actor.class));
        for (Actor actor:list){
            System.out.println(actor);
        }
        JDBCUtilsByDruid.close(null,null,connection);
    }


    @Test
    public void testSingleQuery()throws Exception{
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        Actor query = queryRunner.query(connection, "select * from actor where id=?", new BeanHandler<>(Actor.class), 2);
        System.out.println(query);
        JDBCUtilsByDruid.close(null,null,connection);

    }

    @Test
    public void testDoubleSingleQuery()throws Exception{
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        Object query = queryRunner.query(connection, "select borndate from actor where id=?", new ScalarHandler<>(), 2);
        System.out.println(query);
        JDBCUtilsByDruid.close(null,null,connection);

    }


}
