package com.gochinatv.cdn.api.rxjava;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;


/**
 * https://github.com/jhusain/learnrxjava/blob/master/src/main/java/learnrxjava/examples/HelloWorld.java
 */
public class HelloWorld {


    public static void main(String[] args) {

        // Hello World
        Observable.create(subscriber -> {
            subscriber.onNext("Hello World!");
            subscriber.onNext("Hello World!");
            subscriber.onComplete();
            subscriber.onNext("111111");
            subscriber.onNext("222222");
            //subscriber.onComplete();
        }).subscribe(System.out::println);

       /* // shorten by using helper method
        Observable.just("Hello", "World!")
                .subscribe(System.out::println);

        // add onError and onComplete listeners
        Observable.just("Hello World!")
                .subscribe(System.out::println,
                        Throwable::printStackTrace,
                        () -> System.out.println("Done"));

        // expand to show full classes
        Observable.create( subscriber ->{

            subscriber.onNext("Hello World!");
            subscriber.onComplete();

        }).subscribe( );

        // add error propagation
        Observable.create(subscriber -> {
            try {
                subscriber.onNext("Hello World!");
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribe(System.out::println);

        // add concurrency (manually)
        Observable.create(subscriber -> {
            new Thread(() -> {
                try {
                    subscriber.onNext(getData());
                    subscriber.onComplete();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }).start();
        }).subscribe(System.out::println);

        // add concurrency (using a Scheduler)
        Observable.create(subscriber -> {
            try {
                subscriber.onNext(getData());
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(System.out::println);

        // add operator
        Observable.create(subscriber -> {
            try {
                subscriber.onNext(getData());
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.io())
                .map(data -> data + " --> at " + System.currentTimeMillis())
                .subscribe(System.out::println);*/

        /*// add error handling
        Observable.create(subscriber -> {
            try {
                subscriber.onNext(getData());
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.io())
                .map(data -> data + " --> at " + System.currentTimeMillis())
                .onErrorResumeNext(e -> e.onNext("aa"))
                .subscribe(System.out::println);

        // infinite
        Observable.create(subscriber -> {
            int i = 0;
            while (!subscriber.isUnsubscribed()) {
                subscriber.onNext(i++);
            }
        }).take(10).subscribe(System.out::println);*/

        /*//Hello World
        Observable.create(subscriber -> {
            throw new RuntimeException("failed!");
        }).onErrorResumeNext(throwable -> {
            return Observable.just("fallback value");
        }).subscribe(System.out::println);

        Observable.create(subscriber -> {
            throw new RuntimeException("failed!");
        }).onErrorResumeNext(Observable.just("fallback value"))
                .subscribe(System.out::println);

        Observable.create(subscriber -> {
            throw new RuntimeException("failed!");
        }).onErrorReturn(throwable -> {
            return "fallback value";
        }).subscribe(System.out::println);

        Observable.create(subscriber -> {
            throw new RuntimeException("failed!");
        }).retryWhen(attempts -> {
            return attempts.zipWith(Observable.range(1, 3), (throwable, i) -> i)
                    .flatMap(i -> {
                        System.out.println("delay retry by " + i + " second(s)");
                        return Observable.timer(i, TimeUnit.SECONDS);
                    }).concatWith(Observable.error(new RuntimeException("Exceeded 3 retries")));
        })
                .subscribe(System.out::println, t -> t.printStackTrace());*/

        /*try {
            Thread.sleep(20000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }*/
    }

    private static String getData() {
        return "Got Data!";
    }

}
