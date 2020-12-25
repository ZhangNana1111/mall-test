package com.mall.algorithm;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Zhangnana
 * @DATE 2020/12/19 8:40
 * @Version 1.0
 */

@Controller
public class TestController {

    public void save(){
        System.out.println("testController test");
    }

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        User user = new User();
        user.setUserName(list);
        //user.setId("");
        list.add("dfasdf");
        System.out.println(user);

    }
}

@Data
@NoArgsConstructor
class User{

    @NotNull
    private String id;
    private List<String> userName;
}
