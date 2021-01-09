package mib.c.SpaceInvaders;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Highscore {

    public static String[][] readHighscore() {
        try {
            // Read json file.
            Path path = Path.of("highscore.json");
            String content = Files.readString(path, StandardCharsets.UTF_8);

            // Convert to json array.
            JSONObject object = new JSONObject(content);
            JSONArray highscore = object.getJSONArray("highscore");

            // Convert to 2d array.
            String[][] highscoreTable = new String[highscore.length()][2];
            for (int i = 0; i < highscore.length(); i++) {
                String score = highscore.getString(i);
                String[] splits = score.split(":");

                highscoreTable[i][0] = splits[0];
                highscoreTable[i][1] = splits[1];
            }
            return highscoreTable;
        } catch (Exception e) {
            System.out.print(e);
        }

        return null;
    }

    public static void saveHighscore(String newHighScore) {
        try {
            // Read json file.
            Path path = Path.of("highscore.json");
            String content = Files.readString(path, StandardCharsets.UTF_8);

            // Convert to json array.
            JSONObject object = new JSONObject(content);
            object.getJSONArray("highscore").put(newHighScore);

            // Write to file
            Files.write(Path.of("highscore.json"), object.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.print(e);
        }
    }

}
