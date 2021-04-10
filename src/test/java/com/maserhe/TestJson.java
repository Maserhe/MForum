package com.maserhe;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述:
 * 测试FastJson
 *
 * @author Maserhe
 * @create 2021-04-09 18:02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJson {


    @Test
    public void test() {
        JSONObject json = new JSONObject();
        json.put("aa", 131434);
        json.put("bb", 1111);
        int[] a = {1, 2, 3, 4, 5};
        int[][] bb = {{1, 3, 4, 5, 5}, {1, 2,3 ,3 ,4, 5}};
        json.put("cc", a);
        json.put("dd", bb);
        System.out.println(json.toJSONString());
        JSONArray jsonArray = json.getJSONArray("dd");
        System.out.println(jsonArray);

    }
}
