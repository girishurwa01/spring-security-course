import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// =====================================================================
//  LESSON 6 — TASK A: A TINY TOOL THAT HASHES A PASSWORD
//
//  This is NOT part of your web app. It's a helper you run from the
//  terminal:     ./hash.sh mypassword
//  and it prints the BCrypt hash of "mypassword".
//
//  args[0] = the first word you type after ./hash.sh
// =====================================================================
public class HashTool {

    public static void main(String[] args) {

        // The BCrypt "scrambler"
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        // TODO A: Replace null so that 'hash' holds the hashed version
        //         of args[0].
        //         Hint: the encoder has a method called  encode(...)
        String hash = encoder.encode(args[0]);

        System.out.println(hash);
    }
}
