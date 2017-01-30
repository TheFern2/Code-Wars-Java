

import static org.junit.Assert.*;

import org.junit.Test;


//**************************************************************
// Author: Fernando Balandran
// Date: Jan 25, 2017
// 
// Program: BasicEncryptTest.java
// 
// 
// 
//**************************************************************

public class BasicEncryptTest {
    @Test
    public void testDecrypt() throws Exception {
        BasicEncrypt enc = new BasicEncrypt();
        assertEquals("text = '', rule = 1", "",
                     enc.encrypt("", 1));
        assertEquals("text = 'a', rule = 1", "b",
                     enc.encrypt("a", 1));
        assertEquals("text = 'please encrypt me', rule = 2", "rngcug\"gpet{rv\"og",
                     enc.encrypt("please encrypt me", 2));
    }
}
