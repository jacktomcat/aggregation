package com.gochinatv.cdn.api.influxdb;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jacktomcat on 17/12/25.
 */
public class InfluxDBTest {


    public static void main(String[] args) {

        InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.2.150:8086", "root", "root");
        String dbName = "aTimeSeries";
        influxDB.createDatabase(dbName);

        String rpName = "aRetentionPolicy";
        influxDB.createRetentionPolicy(rpName, dbName, "30d", "30m", 2, true);

        BatchPoints batchPoints = BatchPoints
                .database(dbName)
                .tag("async", "true")
                .retentionPolicy(rpName)
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();
        Point point1 = Point.measurement("cpu")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("idle", 90L)
                .addField("user", 9L)
                .addField("system", 1L)
                .build();
        Point point2 = Point.measurement("disk")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("used", 80L)
                .addField("free", 1L)
                .build();
        batchPoints.point(point1);
        batchPoints.point(point2);
        influxDB.write(batchPoints);
        Query query = new Query("SELECT idle FROM cpu", dbName);

        QueryResult result = influxDB.query(query);
        List<QueryResult.Result> results = result.getResults();

        results.forEach(rs->{
            rs.getSeries().forEach(s->{
                //s.getTags()
            });
        });


        //influxDB.dropRetentionPolicy(rpName, dbName);
        //influxDB.deleteDatabase(dbName);

    }

}
