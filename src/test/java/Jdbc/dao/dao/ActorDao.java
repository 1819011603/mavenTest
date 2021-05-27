package Jdbc.dao.dao;

import Jdbc.dao.javaBean.Actor;

public class ActorDao extends BasicDao<Actor>{
    private static  final ActorDao actorDao = new ActorDao();

    public int insert(String sql,Actor actor){
      return actorDao.update(sql,actor.getNAME(),actor.getSex(),actor.getBorndate(),actor.getPhone());

    }
}
