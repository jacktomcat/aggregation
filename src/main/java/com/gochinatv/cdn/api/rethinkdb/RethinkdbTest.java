package com.gochinatv.cdn.api.rethinkdb;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

/**
 * https://www.rethinkdb.com/docs/install-drivers/java/
 *
 * Created by jacktomcat on 17/8/6.
 */
public class RethinkdbTest {


    public static final RethinkDB r = RethinkDB.r;


    public static void main(String[] args) {

        Connection conn = r.connection().hostname("192.168.2.160").port(28015).connect();

        r.db("test").tableCreate("tv_shows").run(conn);
        r.table("tv_shows").insert(r.hashMap("name", "Star Trek TNG")).run(conn);

    }

}
