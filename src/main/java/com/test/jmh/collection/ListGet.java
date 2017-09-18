package com.test.jmh.collection;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode({ Mode.AverageTime })
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ListGet {

    int LOOP_COUNT = 1000;
    List<Integer> arrayList;
    List<Integer> vector;
    List<Integer> linkedList;

    int result = 0;


    @Setup(Level.Trial)
    public void setup() {
        arrayList = new ArrayList<>();
        vector = new Vector<>();
        linkedList = new LinkedList<>();

        for(int loop=0;loop<LOOP_COUNT;loop++) {
            arrayList.add(loop);
            vector.add(loop);
            linkedList.add(loop);
        }
    }

    @Benchmark
    public void getArrayList(Blackhole bh) {
        for(int loop=0; loop<LOOP_COUNT; loop++) {
            result = arrayList.get(loop);
        }
        bh.consume(result);
    }

    @Benchmark
    public void getVector(Blackhole bh) {
        for(int loop=0; loop<LOOP_COUNT; loop++) {
            result = vector.get(loop);
        }
        bh.consume(result);
    }

    @Benchmark
    public void getLinkedList(Blackhole bh) {
        for(int loop=0; loop<LOOP_COUNT; loop++) {
            result = linkedList.get(loop);
        }
        bh.consume(result);
    }

}


/*

# Run complete. Total time: 00:00:30

Benchmark              Mode  Cnt    Score   Error  Units
ListGet.getArrayList   avgt    5    0.910 ± 0.008  us/op
ListGet.getLinkedList  avgt    5  320.450 ± 1.426  us/op
ListGet.getVector      avgt    5   19.367 ± 0.137  us/op

getLinkedList가 매우 느리게 나온 것을 볼 수 있는데,
getLinkedList는 Queue 인터페이스를 상속받기 때문에 peek() 메서드를 사용해야 한다.

ArrayList는 여러 스레드에서 접근할 경우 문제가 발생할 수 있다. 대신 제일 빠르다.
Vector는 여러 스레드에서 접근할 경우를 방지하기 위해 get() 메서드에 synchronized가 선언되어 있다
 */