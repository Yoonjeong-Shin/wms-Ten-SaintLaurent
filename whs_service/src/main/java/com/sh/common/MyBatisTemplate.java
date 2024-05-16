package com.sh.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class MyBatisTemplate {
    // 프로그램당 하나의 객체를 사용. static 초기화 이후 getSqlSession 재사용
    private static SqlSessionFactory sqlSessionFactory;
    static {
        String resource = "mybatis-config.xml"; // resources폴더 하위경로
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
