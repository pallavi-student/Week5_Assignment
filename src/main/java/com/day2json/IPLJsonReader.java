package com.day2json;

import com.google.gson.*;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

class IPLMatch {
    int match_id;
    String team1;
    String team2;
    String venue;
    String winner;
    String date;
}

public class IPLJsonReader {
    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        FileReader reader = new FileReader("src/main/java/com/day2/ipldata.json");

        IPLMatch[] matches = gson.fromJson(reader, IPLMatch[].class);
        reader.close();

        // Printing the matches
        for (IPLMatch match : matches) {
            System.out.println(match.match_id + " | " + match.team1 + " vs " + match.team2 + " | Winner: " + match.winner);
        }
    }
}

