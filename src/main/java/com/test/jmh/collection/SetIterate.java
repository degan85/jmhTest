package com.test.jmh.collection;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode({ Mode.AverageTime })
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class SetIterate {

    int LOOP_COUNT = 1000;
    Set<String> hashSet;
    Set<String> treeSet;
    Set<String> linkedHashSet;

    String data = "awlkdjflasijdfklasdfjsddlkfj";
    String[] keys;

    String result = null;
    @Setup(Level.Trial)
    public void setup() {
        hashSet = new HashSet<>();
        treeSet = new TreeSet<>();
        linkedHashSet = new LinkedHashSet<>();

        for(int loop=0;loop<LOOP_COUNT;loop++) {
            String tempData = data + loop;
            hashSet.add(tempData);
            treeSet.add(tempData);
            linkedHashSet.add(tempData);
        }
    }

    @Benchmark
    public void iterateHashSet(Blackhole bh) {
        Iterator<String> iter = hashSet.iterator();
        while (iter.hasNext()) {
            result = iter.next();
        }
        bh.consume(result);
    }

    @Benchmark
    public void iterateTreeSet(Blackhole bh) {
        Iterator<String> iter = treeSet.iterator();
        while (iter.hasNext()) {
            result = iter.next();
        }
        bh.consume(result);
    }

    @Benchmark
    public void iterateLinkedHashSet(Blackhole bh) {
        Iterator<String> iter = linkedHashSet.iterator();
        while (iter.hasNext()) {
            result = iter.next();
        }
        bh.consume(result);
    }
}

/*

# Run complete. Total time: 00:00:30

Benchmark                        Mode  Cnt   Score   Error  Units
SetIterate.iterateHashSet        avgt    5   6.540 ± 1.222  us/op
SetIterate.iterateLinkedHashSet  avgt    5  10.878 ± 0.479  us/op
SetIterate.iterateTreeSet        avgt    5  12.215 ± 0.426  us/op

 */
