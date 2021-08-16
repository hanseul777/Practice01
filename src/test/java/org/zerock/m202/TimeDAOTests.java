package org.zerock.m202;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zerock.m2.dao.TimeDAO;

//test에서는 Log4j2를 안쓴다. -> 럼벅을 build.gradle에 추가할 때 테스트용도 추가해야 사용이 가능하다.
// testCompileOnly group: 'org.projectlombok', name: 'lombok', version: '1.18.20' 추가

@Log4j2

public class TimeDAOTests {

    @Test
    public void testGetTime(){
        log.info("test get time............"); // log연결부터 확인

        String timeStr = TimeDAO.INSTANCE.getTime();

        // assertNotNull() : 객체의 값이 null값인지 확인해주는 메서드
        // 이 테스트 결과는 null이 아닐거라는 확신이 있어야함. (TDD-> 일단 실패하는 코드를 만들고 (null출력) 테스트 후 성공하는 코드를 만드는 것)
        Assertions.assertNotNull(timeStr);
    }
}
