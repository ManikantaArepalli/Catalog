import org.json.JSONObject;
public class PolynomialConstantTerm {
    public static int convertToDecimal(String value, int base) {
        return Integer.parseInt(value, base);
    }
    public static int calculateConstantTerm(int[] roots, int m) {
        int constantTerm = 1;
        for (int root : roots) {
            constantTerm *= root;
        }
        constantTerm *= Math.pow(-1, m); 
        return constantTerm;
    }
    public static int processPolynomial(JSONObject jsonData) {
        int n = jsonData.getJSONObject("keys").getInt("n");
        int k = jsonData.getJSONObject("keys").getInt("k");
        int m = n - 1;
        int[] roots = new int[k];
        int index = 0;
        for (String key : jsonData.keySet()) {
            if (!key.equals("keys")) {
                JSONObject rootInfo = jsonData.getJSONObject(key);
                int base = rootInfo.getInt("base");
                String rootValue = rootInfo.getString("value");
                int decimalRoot = convertToDecimal(rootValue, base);
                roots[index++] = decimalRoot;
            }
        }
        return calculateConstantTerm(roots, m);
    }
    public static void main(String[] args) {
        String jsonInput = "{\n" +
                "    \"keys\": {\n" +
                "        \"n\": 4,\n" +
                "        \"k\": 3\n" +
                "    },\n" +
                "    \"1\": {\n" +
                "        \"base\": \"10\",\n" +
                "        \"value\": \"4\"\n" +
                "    },\n" +
                "    \"2\": {\n" +
                "        \"base\": \"2\",\n" +
                "        \"value\": \"111\"\n" +
                "    },\n" +
                "    \"3\": {\n" +
                "        \"base\": \"10\",\n" +
                "        \"value\": \"12\"\n" +
                "    },\n" +
                "    \"6\": {\n" +
                "        \"base\": \"4\",\n" +
                "        \"value\": \"213\"\n" +
                "    }\n" +
                "}";
        JSONObject jsonData = new JSONObject(jsonInput);
        int constantTerm = processPolynomial(jsonData);
        System.out.println("The constant term of the polynomial is: " + constantTerm);
    }
}


















OUTPUT :
Root 1: Base 10, Value 4 -> Decimal:4
Root 2: Base 2, Value 111 -> Decimal:7
Root 3: Base 10, Value 12 -> Decimal:12
Root 6: Base 4, Value 213 -> Decimal:39


here k=3 and m=2. for calculating constant term
    c=(-1)^m * r1 * r2 * .... rm

So,
    c=(-1)^2 * 4 * 7 * 12
    = 336
