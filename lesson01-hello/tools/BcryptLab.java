import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// =====================================================================
//  BCRYPT LAB — experiments to SEE how BCrypt works.
//  Run with:  ./lab.sh <command> ...
//  Always put hashes in 'single quotes' (they contain $ signs).
// =====================================================================
public class BcryptLab {

    public static void main(String[] a) {
        if (a.length == 0) { help(); return; }
        switch (a[0]) {

            // 1) Make a hash (same as what Spring does when saving a user)
            case "hash" -> {
                String hash = new BCryptPasswordEncoder().encode(a[1]);
                System.out.println(hash);
                parts(hash);
            }

            // 2) Check a password against a hash (what Spring does at LOGIN)
            case "check" -> {
                boolean ok = new BCryptPasswordEncoder().matches(a[1], a[2]);
                System.out.println(ok ? "✅ MATCH — login would succeed"
                                      : "❌ NO MATCH — login would fail");
            }

            // 3) Take a hash apart into its pieces
            case "parts" -> parts(a[1]);

            // 4) Hash a password with a salt YOU choose
            //    (this is exactly what check does internally!)
            case "rehash" -> {
                String salt = a[2].substring(0, 29);   // "$2a$10$" + 22 salt chars
                String result = BCrypt.hashpw(a[1], salt);
                System.out.println("salt used : " + salt);
                System.out.println("result    : " + result);
            }

            // 5) How long does one hash take at different costs?
            case "cost" -> {
                new BCryptPasswordEncoder(4).encode(a[1]);   // warm-up run (not timed)
                for (int cost = 4; cost <= 14; cost += 2) {
                    long start = System.nanoTime();
                    new BCryptPasswordEncoder(cost).encode(a[1]);
                    long ms = (System.nanoTime() - start) / 1_000_000;
                    System.out.printf("cost %2d  (2^%-2d = %5d rounds)  →  %5d ms%n",
                            cost, cost, 1 << cost, ms);
                }
            }

            default -> help();
        }
    }

    static void parts(String h) {
        System.out.println("  version : " + h.substring(1, 3));
        System.out.println("  cost    : " + h.substring(4, 6) + "  (2^" + Integer.parseInt(h.substring(4, 6)) + " rounds)");
        System.out.println("  salt    : " + h.substring(7, 29) + "   (22 chars, random)");
        System.out.println("  result  : " + h.substring(29) + "   (31 chars)");
    }

    static void help() {
        System.out.println("""
            ./lab.sh hash   <password>
            ./lab.sh check  <password> '<hash>'
            ./lab.sh parts  '<hash>'
            ./lab.sh rehash <password> '<hash>'
            ./lab.sh cost   <password>""");
    }
}
