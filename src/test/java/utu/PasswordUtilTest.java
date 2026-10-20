package utu;

import org.junit.Test;
import utu.util.PasswordUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class PasswordUtilTest {

    @Test
    public void hashGeneraFormatoBCrypt() {
        String hash = PasswordUtil.hash("admin123");
        assertTrue(hash.startsWith("$2a$"));
        assertEquals(60, hash.length());
    }

    @Test
    public void verificarContrasenaCorrecta() {
        String hash = PasswordUtil.hash("secreto123");
        assertTrue(PasswordUtil.verificar("secreto123", hash));
    }

    @Test
    public void verificarContrasenaIncorrecta() {
        String hash = PasswordUtil.hash("secreto123");
        assertFalse(PasswordUtil.verificar("otraCosa", hash));
    }

    @Test
    public void hashesDistintosParaMismoPassword() {
        String hash1 = PasswordUtil.hash("mismoPassword");
        String hash2 = PasswordUtil.hash("mismoPassword");
        assertNotEquals(hash1, hash2);
    }
}
