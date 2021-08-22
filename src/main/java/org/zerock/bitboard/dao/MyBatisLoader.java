package org.zerock.bitboard.dao;

import jdk.internal.loader.Resource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public enum MyBatisLoader { // 한 번만 로딩하기위해서 enum으로 선언
    INSTANCE;

    //sqlSession <=> connection
    private SqlSessionFactory sqlSessionFactory;

    // 생성자 만들기 -> enum클래스여서 외부에서 객체생성을 막기위해 public선언을 아예안함
    MyBatisLoader(){
        try{
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SqlSessionFactory getFactory() {
        return this.sqlSessionFactory;
    }

}
