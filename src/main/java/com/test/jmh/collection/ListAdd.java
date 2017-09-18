package com.test.jmh.collection;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode({ Mode.AverageTime })
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ListAdd {
    int LOOP_COUNT = 1000;
    List<Integer> arrayList;
    List<Integer> vector;
    List<Integer> linkedList;

    @Benchmark
    public void addArrayList(Blackhole bh) {
        arrayList = new ArrayList<>();
        for(int loop=0;loop<LOOP_COUNT;loop++) {
            arrayList.add(loop);
        }
        bh.consume(arrayList);
    }

    @Benchmark
    public void addVector(Blackhole bh) {
        vector = new Vector<>();
        for(int loop=0;loop<LOOP_COUNT;loop++) {
            vector.add(loop);
        }
        bh.consume(vector);
    }

    @Benchmark
    public void addLinkedList(Blackhole bh) {
        linkedList = new LinkedList<>();
        for(int loop=0;loop<LOOP_COUNT;loop++) {
            linkedList.add(loop);
        }
        bh.consume(linkedList);
    }
}

/*

# Run complete. Total time: 00:00:31

Benchmark              Mode  Cnt   Score   Error  Units
ListAdd.addArrayList   avgt    5   8.274 ± 1.230  us/op
ListAdd.addLinkedList  avgt    5  10.182 ± 0.523  us/op
ListAdd.addVector      avgt    5   8.691 ± 0.856  us/op


 */