package com.pay.mgr.web.javatest;

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
 * Created by yw on 2018/5/29.
 */
public class GenericFruitTest<T> {

    public void getName1(T t){
        System.out.println(t.toString());
    }

    public <T> void getName2(T t){
        System.out.println(t.toString());
    }


    public <E> void getName3(E e){
        System.out.println(e.toString());
    }

    /**
     * 无论何时，如果你能做到，你就该尽量使用泛型方法。也就是说，如果使用泛型方法将整个类泛型化，那么就应该使用泛型方法。
     * 另外对于一个static的方法而已，无法访问泛型类型的参数。所以如果static方法要使用泛型能力，就必须使其成为泛型方法。
     * 静态方法必须声明为泛型方法，静态方法由于普通类
     * @param e
     */
//    public static  void getName4(T e){
//
//    }
    public static <E>  void getName4(E e){

    }

    public static void main(String[] args) {

        GenericFruit.Apple apple = new GenericFruit().new Apple();
        GenericFruit.Person person = new GenericFruit().new Person();

        GenericFruitTest<GenericFruit.Fruit> fruitTest = new GenericFruitTest<>();
        fruitTest.getName1(apple);
//        fruitTest.getName1(person);

        //在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
        //由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
        fruitTest.getName2(apple);
        fruitTest.getName2(person);

        fruitTest.getName3(apple);
        fruitTest.getName3(person);

    }
}
