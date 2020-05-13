package day23;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @program: java.shangguigu.learn
 * @description: 软引用、弱引用等测试
 * @author: Hao Peng
 * @create: 2020-02-23 20:37
 **/
public class ReferenceDemo {

    public static void softRef_Memory_Enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        //释放回收弱引用
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }

    public static void softRef_Memory_NotEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        //释放回收弱引用
        o1 = null;
        System.gc();
        //故意产生大对象造成OOM
        try {
            byte[] bytes = new byte[20*1024*1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void weakReference(){
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
        //Map<String,SoftReference<BitMap>> imageCache = new HashMap<>();
    }

    public static void myHashMap(){
        Map<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "hashMpa";
        map.put(key,value);
        System.out.println(map);//{1=hashMpa}
        key = null;
        System.out.println(map);//{1=hashMpa}
        System.gc();
        System.out.println(map);//{1=hashMpa}
    }
    public static void myWeakHashMap(){
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "hashMpa";
        map.put(key,value);
        System.out.println(map);//{1=hashMpa}
        key = null;
        System.out.println(map);//{1=hashMpa}
        System.gc();
        System.out.println(map);//{}
    }
    public static void myPhantomReference(){
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,referenceQueue);
        System.out.println(o1);//java.lang.Object@4f3f5b24
        System.out.println(phantomReference.get());//null
        System.out.println(referenceQueue.poll());//java.lang.Object@4f3f5b24
        o1 = null;//null
        System.gc();
        System.out.println(o1);//null
        System.out.println(phantomReference.get());//null
        System.out.println(referenceQueue.poll());//java.lang.ref.WeakReference@15aeb7ab
    }


    public static void main(String[] args) {
        //softRef_Memory_Enough();
        //softRef_Memory_NotEnough();
        //weakReference();
        //myHashMap();
        //myWeakHashMap();
        myPhantomReference();
    }
}