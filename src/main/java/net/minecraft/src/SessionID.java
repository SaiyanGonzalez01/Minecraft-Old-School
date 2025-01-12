package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Random;

public class SessionID {

    public static void main(String[] args) {
        String sessionID = generateSessionID();
    }

    public static String generateSessionID() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        StringBuilder randomizedUUID = new StringBuilder(uuid);
        Random rand = new Random();
        for (int i = 0; i < randomizedUUID.length(); i++) {
            int j = rand.nextInt(randomizedUUID.length());
            char temp = randomizedUUID.charAt(i);
            randomizedUUID.setCharAt(i, randomizedUUID.charAt(j));
            randomizedUUID.setCharAt(j, temp);
        }
        String[] parts = randomizedUUID.toString().split("(?<=\\G.{4})");
        for (int i = 1; i < parts.length; i += 2) {
            String temp = parts[i];
            parts[i] = parts[i - 1];
            parts[i - 1] = temp;
        }
        for (int i = 2; i < parts.length; i += 3) {
            String temp = parts[i];
            parts[i] = parts[i - 2];
            parts[i - 2] = temp;
        }
        StringBuilder finalSessionID = new StringBuilder();
        for (String part : parts) {
            finalSessionID.append(part);
        }
        finalSessionID.reverse();
        return finalSessionID.toString();
    }
}
