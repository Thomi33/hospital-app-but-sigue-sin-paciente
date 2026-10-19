package utu.util;

public class GenerarHash {

     public static void main(String[] args) {

        String hash = PasswordUtil.hash("admin123");

        System.out.println("Hash generado:");
        System.out.println(hash);

        

    }

}
