package com.test.jmh.collection;

import com.test.jmh.RandomKeyUtil;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode({ Mode.AverageTime })
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class SetContains {

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

        if(keys == null || keys.length != LOOP_COUNT) {
            keys = RandomKeyUtil.generateRandomSetKeysSwap(hashSet);
        }
    }

    @Benchmark
    public void containsHashSet(Blackhole bh) {
        for(String key:keys) {
            hashSet.contains(key);
        }
        bh.consume(result);
    }

    @Benchmark
    public void containsTreeSet(Blackhole bh) {
        for(String key:keys) {
            treeSet.contains(key);
        }
        bh.consume(result);
    }

    @Benchmark
    public void containsLinkedHashSet(Blackhole bh) {
        for(String key:keys) {
            linkedHashSet.contains(key);
        }
        bh.consume(result);
    }
}

/*

# Run complete. Total time: 00:00:30

Benchmark                          Mode  Cnt    Score   Error  Units
SetContains.containsHashSet        avgt    5    6.452 ± 0.100  us/op
SetContains.containsLinkedHashSet  avgt    5    6.229 ± 0.150  us/op
SetContains.containsTreeSet        avgt    5  181.349 ± 4.618  us/op

TreeSet은 데이터를 저장하면서 정렬한다.
 */