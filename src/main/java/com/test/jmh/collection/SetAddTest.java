package com.test.jmh.collection;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode({ Mode.AverageTime })
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class SetAddTest {
    int LOOP_COUNT = 1000;
    Set<String> set;
    String data = "awlkdjflasijdfklasdfjsddlkfj";

    @Benchmark
    public void addHashSet(Blackhole bh) {
        set = new HashSet<>();
        for(int loop=0;loop<LOOP_COUNT;loop++) {
            set.add(data+loop);
        }
        bh.consume(set);
    }

    @Benchmark
    public void addTreeSet(Blackhole bh) {
        set = new TreeSet<>();
        for(int loop=0;loop<LOOP_COUNT;loop++) {
            set.add(data+loop);
        }
        bh.consume(set);
    }

    @Benchmark
    public void addLinkedHashSet(Blackhole bh) {
        set = new LinkedHashSet<>();
        for(int loop=0;loop<LOOP_COUNT;loop++) {
            set.add(data+loop);
        }
        bh.consume(set);
    }
}

/*

    # Run complete. Total time: 00:00:31

Benchmark                    Mode  Cnt    Score   Error  Units
SetAddTest.addHashSet        avgt    5   64.050 ± 0.848  us/op
SetAddTest.addLinkedHashSet  avgt    5   70.651 ± 4.053  us/op
SetAddTest.addTreeSet        avgt    5  225.038 ± 4.030  us/op

 */