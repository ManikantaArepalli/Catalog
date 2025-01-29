import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.math.BigInteger;
import java.util.*;
public class SecretPolynomialFinder {
    public static BigInteger convertToDecimal(String value, int base) {
        return new BigInteger(value, base);
    }
    public static BigInteger calculateSecretC(List<BigInteger> roots, int m) {
        BigInteger constantTerm = BigInteger.ONE;
        for (BigInteger root : roots) {
            constantTerm = constantTerm.multiply(root);
        }
        constantTerm = constantTerm.multiply(BigInteger.valueOf((long) Math.pow(-1, m)));
        return constantTerm.abs();
    }
    public static BigInteger processPolynomial(String jsonFilePath) throws IOException {
        String jsonData = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        JSONObject jsonObject = new JSONObject(jsonData);
        int k = jsonObject.getJSONObject("keys").getInt("k");
        int m = k - 1;
        List<BigInteger> roots = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            JSONObject entry = jsonObject.getJSONObject(String.valueOf(i));
            int base = entry.getInt("base");
            String value = entry.getString("value");
            BigInteger decimalRoot = convertToDecimal(value, base);
            roots.add(decimalRoot);
        }
        return calculateSecretC(roots, m);
    }
    public static void main(String[] args) {
        try {
            String filePath = "input.json";
            BigInteger secretC = processPolynomial(filePath);
            System.out.println("Secret C: " + secretC);
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
    }
}


OUTPUT :
x=1 , y=5  -> 5
x=3 , y=11  -> 11
x=5 , y=10  -> 10
x=7 , y=27  -> 27



c=8
