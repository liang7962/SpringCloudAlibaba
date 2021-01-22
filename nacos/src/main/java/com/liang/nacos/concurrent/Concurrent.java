package com.liang.nacos.concurrent;

import java.util.concurrent.*;

/*
*   定义一批
*   线程安全的集合
*
* */
public class Concurrent {
        /*
            ConcurrentHashMap：并发版 HashMap
            CopyOnWriteArrayList：并发版 ArrayList
            CopyOnWriteArraySet：并发 Set
            ConcurrentLinkedQueue：并发队列 (基于链表)
            ConcurrentLinkedDeque：并发队列 (基于双向链表)
            ConcurrentSkipListMap：基于跳表的并发 Map
            ConcurrentSkipListSet：基于跳表的并发 Set
            ArrayBlockingQueue：阻塞队列 (基于数组)
            LinkedBlockingQueue：阻塞队列 (基于链表)
            LinkedBlockingDeque：阻塞队列 (基于双向链表)
            PriorityBlockingQueue：线程安全的优先队列
            SynchronousQueue：读写成对的队列
            LinkedTransferQueue：基于链表的数据交换队列
            DelayQueue：延时队列
        */

        /*
        * jdk1.8 和jdk1.7做了一些改变
        *   1,1.7采用的分段锁存储，1.8采取cas锁，乐观锁
        *   2，防止哈希冲突，1.8存储时会设置一个阀值，当超过这个阀值，存储会自动变为红黑树
        * */
        public static ConcurrentHashMap getConcurrentHashMap(){
            return new ConcurrentHashMap();
        }

        /*
        *        并发版 ArrayList
        *   与原始的arrayList不同之处是：新增和删除时会创建一个新的数组，在新的数组里面新增和删除
        *   特点：读操作不加锁，写（增、删、改）操作加锁
        *   局限：可能读取到脏数据
        * */
        public static CopyOnWriteArrayList getCopyOnWriteArrayList(){
            return new CopyOnWriteArrayList();
        }

        /*
        *       并发 Set
        *   基于CopyOnWriteArrayList 实现,每次新增都去遍历一遍list，判断是否存在
        * */
        public static CopyOnWriteArraySet getCopyOnWriteArraySet(){
            return new CopyOnWriteArraySet();
        }

        /*
        *   并发队列 (基于链表)
        * 基于链表实现的并发队列，使用乐观锁 (CAS) 保证线程安全。因为数据结构是链表，
        * 所以理论上是没有队列大小限制的，也就是说添加数据一定能成功。
        * */
        public static ConcurrentLinkedQueue getConcurrentLinkedQueue(){
            return new ConcurrentLinkedQueue();
        }

        /*
        *       并发队列 (基于双向链表)
        *   基于双向链表实现的并发队列，可以分别对头尾进行操作，因此除了先进先出 (FIFO)，
        *   也可以先进后出（FILO），当然先进后出的话应该叫它栈了
        * */
        public static ConcurrentLinkedDeque getConcurrentLinkedDeque(){
            return new ConcurrentLinkedDeque();
        }

        /*
        *       基于跳表的并发 Map
        *   SkipList
        *   即跳表，跳表是一种空间换时间的数据结构，通过冗余数据，
        *   将链表一层一层索引，达到类似二分查找的效果
        * */
        public static ConcurrentSkipListMap getConcurrentSkipListMap(){
            return new ConcurrentSkipListMap();
        }

        /*
        *       基于跳表的并发 Set
        *   类似 HashSet 和 HashMap 的关系，ConcurrentSkipListSet 里面就是一个 ConcurrentSkipListMap
        * */
        public static ConcurrentSkipListSet getConcurrentSkipListSet(){
            return new ConcurrentSkipListSet();
        }

        /*
        *       阻塞队列 (基于数组)
        *   基于链表实现的阻塞队列，想比与不阻塞的 ConcurrentLinkedQueue，
        *   它多了一个容量限制，如果不设置默认为 int 最大值。
        * */
        public static ArrayBlockingQueue getArrayBlockingQueue(int i){
            return new ArrayBlockingQueue(i);
        }

        /*
        *   阻塞队列 (基于双向链表)
        * 类似 LinkedBlockingQueue，但提供了双向链表特有的操作。
        * */
        public static LinkedBlockingDeque getLinkedBlockingDeque(){
            return new LinkedBlockingDeque();
        }

        /*
        *       线程安全的优先队列
        *  构造时可以传入一个比较器，可以看做放进去的元素会被排序，然后读取的时候按顺序消费。
        *  某些低优先级的元素可能长期无法被消费，因为不断有更高优先级的元素进来
        * */
        public static PriorityBlockingQueue getPriorityBlockingQueue(){
            return new PriorityBlockingQueue();
        }

        /*
        *       数据同步交换的队列
        *   一个虚假的队列，因为它实际上没有真正用于存储元素的空间，
        *   每个插入操作都必须有对应的取出操作，没取出时无法继续放入。
        * */
        public static SynchronousQueue getSynchronousQueue(){
            return new SynchronousQueue();
        }

        /*
        *       基于链表的数据交换队列
        *   实现了接口 TransferQueue，通过 transfer 方法放入元素时，如果发现有线程在阻塞在取元素，
        *   会直接把这个元素给等待线程。如果没有人等着消费，那么会把这个元素放到队列尾部，
        *   并且此方法阻塞直到有人读取这个元素。和 SynchronousQueue 有点像，但比它更强大
        * */
        public static LinkedTransferQueue getLinkedTransferQueue(){
            return new LinkedTransferQueue();
        }

        /*
        *       延时队列
        *   可以使放入队列的元素在指定的延时后才被消费者取出，元素需要实现 Delayed 接口。
        * */
        public static DelayQueue getDelayQueue(){
            return new DelayQueue();
        }




}
