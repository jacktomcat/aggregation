package com.gochinatv.cdn.api.ognl;


import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * https://github.com/jkuhnert/ognl/blob/ognl-3-0-x/src/test/java/ognl/InExpressionTest.java
 */
public class InExpressionTest {


    public static void main(String[] args) throws OgnlException {
        OgnlContext context = (OgnlContext)Ognl.createDefaultContext(null);
        Object node = Ognl.parseExpression("#name in {\"Greenland\", \"Austin\", \"Africa\", \"Rome\"}");
        context.put("name", "Austin");
        Object root = null;
        Object value = Ognl.getValue(node, context, root);
        System.out.println(value);
    }
}
