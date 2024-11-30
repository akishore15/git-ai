import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CloneAndRunRepo {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java CloneAndRunRepo <GitHub Repository URL>");
            return;
        }

        String repoUrl = args[0];
        String[] commands = {
            "git clone " + repoUrl,
            "cd " + getRepoName(repoUrl),
            "mvn clean install",
            "java -jar target/*.jar"
        };

        for (String command : commands) {
            executeCommand(command);
        }
    }

    private static void executeCommand(String command) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getRepoName(String repoUrl) {
        String[] parts = repoUrl.split("/");
        return parts[parts.length - 1].replace(".git", "");
    }
}
