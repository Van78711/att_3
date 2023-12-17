

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        Serializer.get("libraries.json", Generator.gen(10));

    }
}