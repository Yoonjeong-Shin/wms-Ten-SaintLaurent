package com.sh.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class MyBatisTemplate {

    private static SqlSessionFactory sqlSessionFactory;
    static {
        String resource = "tenfacdb-config.xml";
        try {
            sqlSessionFactory =  new SqlSessionFactoryBuilder().build(
                    Resources.getResourceAsStream(resource));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession(false);
    }
}
