package mib.c.SpaceInvaders;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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
            String[][] highscoreTable = new String[10][2];
            for (int i = 0; i < 10; i++) {
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
            JSONArray highscore = object.getJSONArray("highscore");

            // Convert to 2d List.
            ArrayList<ArrayList <String>> highscores = new ArrayList<ArrayList<String>>();
            String[][] highscoreTable = new String[highscore.length()][2];
            for (int i = 0; i < highscore.length(); i++) {
                String score = highscore.getString(i);
                String[] splits = score.split(":");

                ArrayList<String> temp = new ArrayList<String>();
                temp.add(splits[1]);
                temp.add(splits[0]);
                highscores.add(temp);
            }

            //sort JSON  by overwritten comparefunction
            Collections.sort(highscores, new Comparator<ArrayList<String>>() {
                @Override
                public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                    int s1 = Integer.parseInt(o1.get(0));
                    int s2 = Integer.parseInt(o2.get(0));
                    int compare = (s1 > s2) ? 1 : 0;
                    if(compare == 0){
                        compare = (s1 == s2) ? 0 : -1;
                    }
                    return compare;
                }
            });
            Collections.reverse(highscores);

            // Convert to json object.
            String newContent = "{\"highscore\":[";
            for (int i=0; i < highscores.size(); i++) {

                newContent = newContent + "\"" + highscores.get(i).get(1) + ":" + highscores.get(i).get(0) + "\",";
            }
            JSONObject newObject = new JSONObject(newContent + "]}");



            // Write to file
            Files.write(Path.of("highscore.json"), newObject.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.print(e);
        }
    }

}
