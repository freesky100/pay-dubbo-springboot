package com.pay.mgr.web.javatest;

import javafx.scene.media.SubtitleTrack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * //          佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 * Created by yw on 2018/6/10.
 */
@RunWith(JUnit4.class)
public class TestStream {

    private static Long testParallel(Long n){
//       Long res =  Stream.iterate(1l,i->i+1).limit(n).parallel().reduce(0l,Long::sum);
       Long res = LongStream.rangeClosed(0,n).parallel().reduce(0l,Long::sum);
        System.out.println(res);
        return res;
    }
    private static Long testNoneParallel(Long n){
//       Long res =  Stream.iterate(1l,i->i+1).limit(n).reduce(0l,Long::sum);
       Long res =  LongStream.rangeClosed(0,n).reduce(0l,Long::sum);
        System.out.println(res);
        return res;
    }
    private static Long testFor(Long n){
        long sum = 0;
        for (long i = 1; i <= n; i++) {
             sum  = sum+i;
        }
        System.out.println(sum);
        return sum;
    }

    private void  test(Function<Long,Long> function,Long param){
        long max = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            Long start = System.currentTimeMillis();
            function.apply(param);
            Long end = System.currentTimeMillis()-start;
            if(end<max){
                max = end;
            }
        }
        System.out.println("最短一次耗时："+max);
    }


    @Test
    public void testTime(){
        Long n = 20_000_000L;
        test(TestStream::testParallel,n);
        test(TestStream::testNoneParallel,n);
        test(TestStream::testFor,n);
    }


}
