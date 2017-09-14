/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.test.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;


public class MyBenchmark {


/*
    Scope (The class must be declared public)

    Thread	    Each thread running the benchmark will create its own instance of the state object.
    Group	    Each thread group running the benchmark will create its own instance of the state object.
    Benchmark	All threads running the benchmark share the same state object.
*/

/*
    Level.Trial	        The method is called once for each time for each full run of the benchmark. A full run means a full "fork" including all warmup and benchmark iterations.
    Level.Iteration	    The method is called once for each iteration of the benchmark.
    Level.Invocation	The method is called once for each call to the benchmark method.
 */
    @State(Scope.Thread)
    public static class MyState {

        @Setup(Level.Trial)
        public void doSetup() {
            sum = 0;
            System.out.println("Do Setup");
        }

        @TearDown(Level.Trial)
        public void doTearDown() {
            System.out.println("Do TearDown");
        }

        public int a= 1;
        public int b =2;
        public int sum;

    }


/*
    Mode

   Throughput	        Measures the number of operations per second, meaning the number of times per second your benchmark method could be executed.
   Average Time         Measures the average time it takes for the benchmark method to execute (a single execution).
   Sample Time          Measures how long time it takes for the benchmark method to execute, including max, min time etc.
   Single Shot Time     Measures how long time a single benchmark method execution takes to run. This is good to test how it performs under a cold start (no JVM warm up).
   All                  Measures all of the above.
*/
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void testMethod(MyState state, Blackhole bh) {
        int sum1 = state.a + state.b;
        int sum2 = state.a + state.a + state.b + state.b;

        bh.consume(sum1);
        bh.consume(sum2);
    }

}
