package com.gochinatv.cdn.api.ognl;

import ognl.Ognl;


/**
 * 获取属性值
 */
public class IsTruckTest {

    public void testIsTruckMethod() throws Exception{
        boolean actual = (Boolean) Ognl.getValue("isTruck", new TruckHolder());
        System.out.println(actual);
    }

    public static void main(String[] args) throws Exception {
        IsTruckTest test = new IsTruckTest();
        test.testIsTruckMethod();
    }

}

class TruckHolder {
    public boolean getIsTruck() {
        return false;
    }

}