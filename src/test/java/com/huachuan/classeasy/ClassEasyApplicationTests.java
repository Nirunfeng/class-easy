package com.huachuan.classeasy;

import com.huachuan.classeasy.repository.LoginRepository;
import com.huachuan.classeasy.repository.entity.Login;
import com.huachuan.classeasy.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClassEasyApplication.class)
class ClassEasyApplicationTests {

    @Autowired
    private LoginRepository loginRepository;

    @Test
    void contextLoads() {
        List<Login> logins=loginRepository.findAll();
        System.out.println(JsonUtil.toJson(logins));
    }

}
