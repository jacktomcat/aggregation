package com.gochinatv.cdn.api.taobao;

import com.chsoft.datacollection.entity.User;
import com.taobao.text.Color;
import com.taobao.text.Decoration;
import com.taobao.text.ui.TableElement;
import com.taobao.text.util.RenderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2019-01-06 下午8:57
 */
public class RenderTest {


    public static void main(String[] args) {
        String text = "  ,---.\n" +
                      " /  O  \\\n" +
                      "|  .-.  |\n" +
                      "|  | |  |\n" +
                      "`--' `--'";
        TableElement logoTable = new TableElement();
        logoTable.row(text).style(Decoration.bold.fg(Color.green));
        String result = RenderUtil.render(logoTable);
        System.out.println(result);


        User user  = new User();
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user);
        result = RenderUtil.render(users);
        System.out.println(result);
    }


}
