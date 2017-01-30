import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;


public class CaesarCipherTest
{

	@Test
	public void test1() {
		String u = "I should have known that you would have a perfect answer for me!!!";
		List<String> v = Arrays.asList("J vltasl rlhr ", "zdfog odxr ypw", " atasl rlhr p ", "gwkzzyq zntyhv", " lvz wp!!!");
		assertEquals(v, CaesarCipher.movingShift(u, 1));
		assertEquals(u, CaesarCipher.demovingShift(CaesarCipher.movingShift(u, 1), 1)); 
	}
	
	@Test
	public void test2() {
		String u = "O CAPTAIN! my Captain! our fearful trip is done;";
		List<String> v = Arrays.asList("P FEUZHQW!", " yl Rqgltc", "i! mtr hhe", "wlbt dcuc ", "xi vhhz;");
		assertEquals(v, CaesarCipher.movingShift(u, 1));
		assertEquals(u, CaesarCipher.demovingShift(CaesarCipher.movingShift(u, 1), 1));
		
		/*
		 * The First T on CAPTAIN is showing as N on the decoded message.
		 */
	}
	
	@Test
	public void test3() {
		String u = "For you bouquets and ribbon'd wreaths--for you the shores a-crowding;";
		List<String> v = Arrays.asList("Gqu dub kyfchs", "ii sgx nfzaoo'", "g bxlicrd--tdh", " qho pec siqui", "x h-lbziqwcw;");
		assertEquals(v, CaesarCipher.movingShift(u, 1));
        assertEquals(u, CaesarCipher.demovingShift(CaesarCipher.movingShift(u, 1), 1)); 
	}
	
	@Test
	public void test4() {
		String u = "Exult, O shores, and ring, O bells! But I, with mournful tread, Walk the deck my Captain lies, Fallen cold and dead.";
		List<String> v = Arrays.asList("Fzxpy, W csaesh, sgx nfl", "f, Q fjrsa! Mgg X, obnc ", "jmtrohxp zymjn, Joaa lay", " zbaj na Gfvairx xvsh, X", "tfgak bomf esj lnko.");
		assertEquals(v, CaesarCipher.movingShift(u, 1));
        assertEquals(u, CaesarCipher.demovingShift(CaesarCipher.movingShift(u, 1), 1)); 
	}
	
	
	@Test
	public void testa() {
		String u = "uoxIirmoveNreefckgieaoiEcooqo";
		List<String> v = Arrays.asList("wrbNoy", "uxfpZe", "stvtcz", "czwlgD", "cpqts");
		assertEquals(v, CaesarCipher.movingShift(u, 2));
        assertEquals(u, CaesarCipher.demovingShift(CaesarCipher.movingShift(u, 2), 2));
        
        /*
         *  uoxIirmoveAreefckgieaoiEcooqo
         *  It expected an N instead of an A everything else looks good.
         */
	}
	
	@Test
	public void testb() {
		String u = "uaoQop jx eh osr okaKv vzagzwpxagokBKriipmc U";
		List<String> v = Arrays.asList("wdsVuw sh", " qu dii h", "evGs uzbi", "caudhoxuM", "Wewxfdu O");
		assertEquals(v, CaesarCipher.movingShift(u, 2));
        assertEquals(u, CaesarCipher.demovingShift(CaesarCipher.movingShift(u, 2), 2)); 
	}
	
	@Test
	public void testc() {
		String u = "kgpiqislyhvmffdzlyehjiIteAaaotcoapk bbMgaHlda";
		List<String> v = Arrays.asList("mjtnwpaui", "shztutqdr", "ycffGseBc", "dsyiviyu ", "noAvqYdwu");
		assertEquals(v, CaesarCipher.movingShift(u, 2));
        assertEquals(u, CaesarCipher.demovingShift(CaesarCipher.movingShift(u, 2), 2));
        
        /*
         *  kgpiqislyhvmffdzlyehjiIteAaaotcoapk bbAgaHlda
         *  It was expected an M but the result was an A
         */
	}
	
	@Test
	public void testd() {
		String u = "abcdefghjuty";
		List<String> v = Arrays.asList("bdf", "hjl", "nps", "eek", "");
		assertEquals(v, CaesarCipher.movingShift(u, 1));
        assertEquals(u, CaesarCipher.demovingShift(CaesarCipher.movingShift(u, 1), 1)); 
	}
	
	@Test
	public void teste() {
		String u = "abcdefghjuty1234";
		List<String> v = Arrays.asList("bdfh", "jlnp", "seek", "1234", "");
		assertEquals(v, CaesarCipher.movingShift(u, 1));
        assertEquals(u, CaesarCipher.demovingShift(CaesarCipher.movingShift(u, 1), 1)); 
	}

}
