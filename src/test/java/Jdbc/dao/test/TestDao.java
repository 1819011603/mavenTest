package Jdbc.dao.test;

import Jdbc.dao.dao.ActorDao;
import Jdbc.dao.javaBean.Actor;
import org.junit.Test;

import java.util.List;

public class TestDao {
    @SuppressWarnings("unchecked")
    @Test
    public void testActorDao(){
        ActorDao actorDao = new ActorDao();
        List<Actor> list = (List<Actor>) actorDao.queryMulti("select * from actor1 where id >= ?",Actor.class,1);
        for (Actor actor:list){
            System.out.println(actor);
        }

        Actor actor = (Actor) actorDao.querySingle("select * from actor1 where id = ?",Actor.class,2);
        System.out.println(actor);

        Object o = actorDao.queryScalar("select borndate from actor1 where id = ?",1);
        System.out.println(o);

        int row = actorDao.update("update actor1 set NAME=? where NAME=?","张三丰","jack");
        System.out.println(row);
        row = actorDao.update("insert into actor1 Values(null,?,?,?,?)",actor.getNAME(),actor.getSex(),actor.getBorndate(),actor.getPhone());
        System.out.println(row);
        row = actorDao.update("delete from actor1 where NAME=?","jack");
        System.out.println(row);

        row = actorDao.insert("insert into actor1 Values(null,?,?,?,?)",actor);
        System.out.println(row);


    }
}
