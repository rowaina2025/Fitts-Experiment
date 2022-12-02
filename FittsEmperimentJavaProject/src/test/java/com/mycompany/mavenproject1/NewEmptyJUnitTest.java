/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.mavenproject1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class NewEmptyJUnitTest {
    
    MyClass ob;
    
    public NewEmptyJUnitTest() {
       
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ob = new MyClass();
        
    }
    
    @After
    public void tearDown() {
        ob = null;
    }

    @Test
    public void test(){
        int res = ob.Sqr(3);
        assertEquals(9, res);
    }
}
