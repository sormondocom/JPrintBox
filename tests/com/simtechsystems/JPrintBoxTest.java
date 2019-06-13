package com.simtechsystems;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JPrintBoxTest {

    JPrintBox testPrintBox;
    @Before
    public void setUp() {
        System.out.println("Running: Initializing a JPrintBox()...");
        testPrintBox = new JPrintBox();
    }

    @Test
    public void getMaxKeySize() {
        /* Ensuring that when there are no items, getMaxKeySize() will return 0. */
        assertEquals(0,testPrintBox.getMaxKeySize());
        /*
        Ensuring when an item is inserted into the Map, the getMaxKeySize() will return a value equal to the
        inserted key of the item.
         */
        testPrintBox.addItem("testKey","testItem");
        assertEquals(7,testPrintBox.getMaxKeySize());
        /*
        Ensuring when an item key that is larger than the previously entered key is inserted that the
        getMaxKeySize() is updated accordingly.
        */
        testPrintBox.addItem("largerTestKey","testItem");
        assertEquals(13,testPrintBox.getMaxKeySize());
        /*
        Ensuring when all items are removed, getMaxKeySize() reflects 0 again.
         */
        testPrintBox.removeAllItems();
        assertEquals(0,testPrintBox.getMaxKeySize());
    }

    @Test
    public void getMaxValueSize() {
        /* Ensuring that when there are no items, getMaxValueSize() will return 0. */
        assertEquals(0,testPrintBox.getMaxValueSize());
        /*
        Ensuring when an item is inserted into the Map, the getMaxValueSize() will return a value equal to the
        inserted value of the item.
         */
        testPrintBox.addItem("testKey","testItem");
        assertEquals(8,testPrintBox.getMaxValueSize());
        /*
        Ensuring when an item value that is larger than the previously entered value is inserted that the
        getMaxValueSize() is updated accordingly.
         */
        testPrintBox.addItem("largerTestKey","largerTestItem");
        assertEquals(14,testPrintBox.getMaxValueSize());
        /*
        Ensuring when all items are removed, getMaxValueSize() reflects 0 again.
         */
        testPrintBox.removeAllItems();
        assertEquals(0,testPrintBox.getMaxKeySize());
    }

    @Test
    public void getItemValue() {
        /* Ensuring that when there is an item inserted, that getItemValue() can retrieve the value by the key used. */
        testPrintBox.addItem("testKey","testItem");
        assertEquals("testItem",testPrintBox.getItemValue("testKey"));
    }

    @Test
    public void getItemCount() {
        /* Ensuring that when there is an item inserted, that getItemCount() reflects it correctly. */
        testPrintBox.addItem("testKey","testItem");
        assertEquals(1,testPrintBox.getItemCount());
        /* Ensuring that when an item is removed, that getItemCount() returns 0. */
        testPrintBox.removeItem("testKey");
        assertEquals(0,testPrintBox.getItemCount());
    }

    @Test
    public void hasItemKey() {
        /* Ensuring that after an item is added, that it returns true. */
        testPrintBox.addItem("testKey","testItem");
        assertTrue(testPrintBox.hasItemKey("testKey"));
        /* Ensuring that when an item's key doesn't exist, it returns false. */
        assertFalse(testPrintBox.hasItemKey("testKey2"));
    }

    @Test
    public void hasItemValue() {
        /* Ensuring that after an item is added, that it returns true. */
        testPrintBox.addItem("testKey","testItem");
        assertTrue(testPrintBox.hasItemValue("testItem"));
        /*Ensuring that when an item's value doesn't exist, it returns false. */
        assertFalse(testPrintBox.hasItemValue("testValue"));
    }

    @Test
    public void getItems() {
        /* Ensuring that after items are inserted, that they can be returned. */
        testPrintBox.addItem("testKey","testItem");
        assertNotNull(testPrintBox.getItems());
    }

    @Test
    public void getTitleSize() {
        /* Ensuring that when a title is not set, that getTitleSize() returns 0. */
        assertEquals(0,testPrintBox.getTitleSize());
        /* Ensuring that when a title is set, that getTitleSize() returns the size of the title added. */
        testPrintBox.addTitle("test");
        assertEquals(4,testPrintBox.getTitleSize());
    }

    @Test
    public void getFooterSize() {
        /* Ensuring that when a footer is not set, that getFooterSize() returns 0. */
        assertEquals(0,testPrintBox.getFooterSize());
        /* Ensuring that when a footer is set, that getFooterSize() returns the size of the footer added. */
        testPrintBox.addFooter("test");
        assertEquals(4,testPrintBox.getFooterSize());
    }

    @Test
    public void addTitle() {
        /* Ensuring that when a title is added, that it is set accordingly. */
        testPrintBox.addTitle("test");
        assertEquals("test",testPrintBox.getTitle());
    }

    @Test
    public void addItem() {
        /* Ensuring that when an item is added, that it can be retrieved accordingly. */
        testPrintBox.addItem("testKey","testItem");
        assertTrue(testPrintBox.hasItemKey("testKey"));
    }

    @Test
    public void removeItem() {
        /* Ensuring that when an item is removed, that it returns false. */
        testPrintBox.addItem("testKey","testItem");
        assertTrue(testPrintBox.hasItemKey("testKey"));
        testPrintBox.removeItem("testKey");
        assertFalse(testPrintBox.hasItemKey("testKey"));
    }

    @Test
    public void addFooter() {
        /* Ensuring that when a footer is added, that it can be retrieved accordingly. */
        testPrintBox.addFooter("test footer");
        assertEquals("test footer",testPrintBox.getFooter());
    }

    @Test
    public void equals1() {
        /* Ensuring that 2 JPrintBox() objects are equals when they are, in fact equals. */
        JPrintBox testPrintBox2 = new JPrintBox();
        JPrintBox testPrintBox3 = testPrintBox2;
        assertEquals(testPrintBox2,testPrintBox3);
        /* Ensuring that when doing different object comparison, the return comes through as false.*/
        JPrintBox jPrintBox = new JPrintBox();
        Object obj = new Object();
        assertNotEquals(jPrintBox,obj);
        /* Ensuring that 2 different JPrintBox() objects are not equal when they are not equal. */
        JPrintBox testPrintBox4 = new JPrintBox();
        testPrintBox4.addTitle("title");
        testPrintBox4.addFooter("footer");
        testPrintBox4.addItem("key","value");
        JPrintBox testPrintBox5 = new JPrintBox();
        testPrintBox5.addItem("key","value");
        testPrintBox5.addTitle("title");
        testPrintBox5.addFooter("footer");
        assertEquals(testPrintBox4,testPrintBox5);
        /* Ensure that when the title changes on one JPrintBox() they are no longer equals. */
        testPrintBox5.addTitle("new title");
        assertNotEquals(testPrintBox4,testPrintBox5);
    }

    @Test
    public void getTitle() {
        /* Ensure that when a title is added, it can be retrieved.*/
        testPrintBox.addTitle("test");
        assertEquals("test",testPrintBox.getTitle());
    }

    @Test
    public void getFooter() {
        /* Ensure that when a footer is added, it can be retrieved.*/
        testPrintBox.addFooter("test footer");
        assertEquals("test footer",testPrintBox.getFooter());
    }

    @Test
    public void toString1() {
        /* Ensure that when a JPrintBox() is instantiated it returns a string value of "" */
        JPrintBox jPrintBox = new JPrintBox();
        assertEquals("",jPrintBox.toString());
        /* Ensure that when a JPrintBox() is filled in it returns something other than "" */
        jPrintBox.addFooter("footer");
        assertNotEquals("",jPrintBox.toString());
    }

    @Test
    public void getCurrentMaxWidth() {
        /* Ensure that when an empty JPrintBox() object is instantiated there is a 0 returned for getCurrentMaxWidth() */
        assertEquals(0,testPrintBox.getCurrentMaxWidth());
        /* Ensure that when a JPrintBox() object has information in it, that getCurrentMaxWidth() returns a value greater than 0. */
        testPrintBox.addTitle("test");
        assertNotEquals(0,testPrintBox.getCurrentMaxWidth());
    }

    @Test
    public void getCurrentMaxLength() {
        /* Ensure that when an empty JPrintBox() object is instantiated there is a 0 returned for getCurrentMaxLength() */
        assertEquals(0,testPrintBox.getCurrentMaxLength());
        /* Ensure that when a JPrintBox() has information in it, that getCurrentMaxLength() returns a value greater than 0. */
        testPrintBox.addTitle("test");
        assertNotEquals(0,testPrintBox.getCurrentMaxLength());
    }

    @Test
    public void removeTitle() {
        /* Ensure that when a title is removed, that it no longer returns. */
        testPrintBox.addTitle("test");
        assertEquals("test",testPrintBox.getTitle());
        testPrintBox.removeTitle();
        assertNotEquals("test",testPrintBox.getTitle());
    }

    @Test
    public void removeFooter() {
        /* Ensure that when a footer is removed, it no longer returns. */
        testPrintBox.addFooter("test footer");
        assertEquals("test footer",testPrintBox.getFooter());
        testPrintBox.removeFooter();
        assertNotEquals("test footer",testPrintBox.getFooter());
    }

    @Test
    public void getMaxItemSize() {
        /* Ensure that when items are added, that getMaxItemSize() returns the combined length of key and value. */
        testPrintBox.addItem("testKey","testValue");
        assertEquals(16,testPrintBox.getMaxItemSize());
        /* Ensure that when a larger item is added, that getMaxItemSize() returns the larger key and value size. */
        testPrintBox.addItem("testKey2","testValue2");
        assertEquals(18,testPrintBox.getMaxItemSize());
    }

    @Test
    public void resize() {
        /* Ensure that when a JPrintBox() has items that are removed, that the resize() method performs accordingly. */
        testPrintBox.addTitle("test");
        testPrintBox.addItem("key","value");
        testPrintBox.addItem("key2","value2");
        assertEquals(10,testPrintBox.getCurrentMaxWidth());
        testPrintBox.removeItem("key2");
        assertEquals(8,testPrintBox.getCurrentMaxWidth());
        /* Ensure that when all items are removed, it only returns the length of the title.*/
        testPrintBox.removeAllItems();
        assertEquals(4,testPrintBox.getCurrentMaxWidth());
        /* Ensure that when a footer is added, then removed, it only returns the length of the title.*/
        testPrintBox.addFooter("footer");
        assertEquals(6,testPrintBox.getCurrentMaxWidth());
        testPrintBox.removeFooter();
        assertEquals(4,testPrintBox.getCurrentMaxWidth());
        /*
        Ensure than when a large footer is inserted that is longer than anything else, except for one item
        it returns the length of that item.  When the item is removed, it returns the length of the footer.
         */
        testPrintBox.addFooter("this is a longer footer");
        assertEquals(23,testPrintBox.getCurrentMaxWidth());
        testPrintBox.addItem("key","test is a really long item to test the footer after removal and the resize method.");
        assertEquals(85,testPrintBox.getCurrentMaxWidth());
        testPrintBox.removeAllItems();
        assertEquals(23,testPrintBox.getCurrentMaxWidth());

    }

    @Test
    public void getInfoString() {
        /* Ensure that getInfoString() returns a value regardless of JPrintBox() being complete or not. */
        assertNotEquals("",testPrintBox.getInfoString());
    }

    @Test
    public void boxTitleToString() {
        /* Ensure that when a JPrintBox() has a title, it displays a value. */
        testPrintBox.addTitle("test");
        assertNotEquals("",testPrintBox.boxTitleToString());
    }

    @Test
    public void boxItemsToString() {
        /* Ensure that when a JPrintBox() has items, it displays a value. */
        testPrintBox.addItem("testKey","testValue");
        assertNotEquals("",testPrintBox.boxItemsToString());
    }

    @Test
    public void boxFooterToString() {
        /* Ensure that when a JPrintBox() has a footer, it displays a value. */
        testPrintBox.addFooter("test footer");
        assertNotEquals("",testPrintBox.boxFooterToString());
    }

    @Test
    public void removeAllItems() {
        /* Ensure that when a JPrintBox() has items removed, it removes all of them */
        testPrintBox.addItem("test","key");
        assertTrue(testPrintBox.hasItemKey("test"));
        testPrintBox.removeAllItems();
        assertEquals(0,testPrintBox.getItemCount());
    }

    @Test
    public void getTotalBoxWidth() {
        /* Ensure that when a JPrintBox() is empty it returns 0. */
        assertEquals(0,testPrintBox.getTotalBoxWidth());
        /* Ensure that when a JPrintBox() has anything in it, it returns greater than 0. */
        testPrintBox.addItem("test","value");
        assertNotEquals(0,testPrintBox.getTotalBoxWidth());
    }
    @After
    public void tearDown() throws Exception {
        System.out.println("Running: Emptying JPrintBox() items...");
        testPrintBox.removeAllItems();
        assertEquals(0,testPrintBox.getItemCount());
        System.out.println("Running: Setting JPrintBox() to null.");
        testPrintBox=null;
        assertNull(testPrintBox);
    }
}