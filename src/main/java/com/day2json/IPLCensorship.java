package com.day2json;


import com.google.gson.*;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

class IPLMatches {
    int match_id;
    String team1;
    String team2;
    String venue;
    String winner;
    String date;
    String playerOfTheMatch;

    public void applyCensorship() {
        this.team1 = censorTeamName(this.team1);
        this.team2 = censorTeamName(this.team2);
        this.winner = censorTeamName(this.winner);
        this.playerOfTheMatch = "REDACTED";
    }

    private String censorTeamName(String team) {
        String[] words = team.split(" ");
        if (words.length > 1) {
            return words[0] + " ***";
        }
        return team; // In case of single-word teams
    }
}

public class IPLCensorship {
    public static void main(String[] args) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileReader reader = new FileReader("src/main/java/com/day2json/ipldata.json");

        IPLMatches[] matches = gson.fromJson(reader, IPLMatches[].class);
        reader.close();

        // Apply censorship rules
        for (IPLMatches match : matches) {
            match.applyCensorship();
        }

        // Print censored JSON output
        System.out.println(gson.toJson(matches));
    }
}

