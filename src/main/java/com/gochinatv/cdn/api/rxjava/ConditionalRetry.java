package com.gochinatv.cdn.api.rxjava;

import io.reactivex.Observable;
import java.util.concurrent.atomic.AtomicInteger;

public class ConditionalRetry {

    public static void main(String[] args) {
        final AtomicInteger c = new AtomicInteger();

        Observable<String> oWithRuntimeException = Observable.create( emit ->{
            System.out.println("ExecutionRuntime: " + c.get());
            if (c.incrementAndGet() < 3) {
                emit.onError(new RuntimeException("retryable"));
            } else {
                emit.onNext("hello");
                emit.onComplete();
            }
        });

        final AtomicInteger c2 = new AtomicInteger();
        Observable<String> oWithIllegalStateException = Observable.create(emit -> {
            System.out.println("ExecutionIllegalState: " + c2.get());
            if (c2.incrementAndGet() < 3) {
                emit.onError(new RuntimeException("retryable"));
            } else {
                emit.onError(new IllegalStateException());
            }
        });

        subscribe(oWithRuntimeException);
        subscribe(oWithIllegalStateException);
    }



    public static void subscribe(Observable<String> o) {
        o = o.materialize().flatMap(n -> {
            if (n.isOnError()) {
                if (n.getError() instanceof IllegalStateException) {
                    return Observable.just(n);
                } else {
                    return Observable.error(n.getError());
                }
            } else {
                return Observable.just(n);
            }
        }).retry().dematerialize();
        o.subscribe(System.out::println);
    }


}
