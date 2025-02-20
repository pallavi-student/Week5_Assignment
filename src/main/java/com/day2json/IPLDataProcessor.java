package com.day2json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class IPL_Match {
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

public class IPLDataProcessor {

    // Read JSON
    public static List<IPL_Match> readJSON(String filename) throws Exception {
        Gson gson = new Gson();
        FileReader reader = new FileReader(filename);
        IPL_Match[] matches = gson.fromJson(reader, IPL_Match[].class);
        reader.close();
        return Arrays.asList(matches);
    }

    // Read CSV
    public static List<IPL_Match> readCSV(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        List<IPL_Match> matches = new ArrayList<>();

        br.readLine(); // Skip header
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            IPL_Match match = new IPL_Match();
            match.match_id = Integer.parseInt(values[0]);
            match.team1 = values[1];
            match.team2 = values[2];
            match.venue = values[3];
            match.winner = values[4];
            match.date = values[5];
            match.playerOfTheMatch = values[6];
            matches.add(match);
        }
        br.close();
        return matches;
    }

    // Write JSON
    public static void writeJSON(List<IPL_Match> matches, String filename) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter(filename);
        gson.toJson(matches, writer);
        writer.close();
    }

    // Write CSV
    public static void writeCSV(List<IPL_Match> matches, String filename) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.write("match_id,team1,team2,venue,winner,date,player_of_the_match\n");
        for (IPL_Match match : matches) {
            bw.write(match.match_id + "," + match.team1 + "," + match.team2 + "," + match.venue + "," +
                    match.winner + "," + match.date + "," + match.playerOfTheMatch + "\n");
        }
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        String jsonInputFile = "src/main/java/com/day2json/ipldata.json";
        String csvInputFile = "src/main/java/com/day2json/ipl.csv";
        String jsonOutputFile = "src/main/java/com/day2json/result.json";
        String csvOutputFile = "src/main/java/com/day2json/result2.json";

        // Process JSON
        List<IPL_Match> matchesFromJSON = readJSON(jsonInputFile);
        for (IPL_Match match : matchesFromJSON) match.applyCensorship();
        writeJSON(matchesFromJSON, jsonOutputFile);
        writeCSV(matchesFromJSON, csvOutputFile);

        // Process CSV
        List<IPL_Match> matchesFromCSV = readCSV(csvInputFile);
        for (IPL_Match match : matchesFromCSV) match.applyCensorship();
        writeJSON(matchesFromCSV, jsonOutputFile); // Overwrite if needed
        writeCSV(matchesFromCSV, csvOutputFile);

        System.out.println("Censored JSON and CSV files generated successfully.");
    }
}

