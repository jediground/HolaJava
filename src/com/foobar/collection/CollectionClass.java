package com.foobar.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;

// collection:
//- List 有序
//      - ArrayList
//      - LinkedList
//      - Vector
//- Set 无序
//      - HashSet
//      - LinkedHasSet
//      - TreeSet 自然排序
 
// Map:
//  - HashMap
//  - TreeMap
//  - Hashtable
//       - Properties

public class CollectionClass {

    @Test
    public void testCollection() {
        Collection coll = new ArrayList();
        coll.add(12);
        coll.add("AB");
        coll.add(new Date());
        System.out.println(coll);
        
        Collection coll1 = Arrays.asList(1, 2, 4);
        coll.addAll(coll1);
        System.out.println(coll);
    } 
}
