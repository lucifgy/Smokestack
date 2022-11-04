//https://stackoverflow.com/questions/26830617/running-bash-commands-in-java
//https://github.com/fleipold/jproc
//jproc is not used, but seems to be a good source.

import java.io.*;
import java.util.Scanner;
//Note: This works with macOS(intelliJ). I did not test it with other OS. Should work on linux find though.
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("\nCommands:\ntest - executes all the required commands for the task.");
        System.out.println("beginS - Begins writing script.");
        System.out.println("endS - Ends the script and executes\nexit - exits.");
        System.out.println("P.S.\nLocal variables are only supported through scripts.\n");

        Scanner sc = new Scanner(System.in);
        String input = "";
        boolean exit_flag = false;

        while (!exit_flag)
        {
            System.out.printf("Enter input > ");
            input = sc.nextLine();
            //Switch might be better..
            if(input.equals("exit")) break;
            else if (input.equals("beginS")) executeCommands();
            else if (input.equals("test"))
            {
                executeBashCommand("pwd");
                executeBashCommand("echo Hello World! >> t.txt"); //no output
                executeBashCommand("cat t.txt");
                executeBashCommand("true"); //no output
                executeBashCommand("false"); //no output
                executeBashCommand("echo $PATH"); //Doesn't support loc variables yet. Needs same process.
                executeBashCommand("wc t.txt");
                executeBashCommand("rm t.txt"); //no output
            } else
                executeBashCommand(input);
        }
        //executeCommands();
    }
    public static void executeCommands() throws IOException {

        File tempScript = createTempScript();

        try {
            ProcessBuilder pb = new ProcessBuilder("bash", tempScript.toString());
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            tempScript.delete();
        }
    }
    public static File createTempScript() throws IOException {
        File tempScript = File.createTempFile("script", null);

        Writer streamWriter = new OutputStreamWriter(new FileOutputStream(tempScript));
        PrintWriter printWriter = new PrintWriter(streamWriter);

        printWriter.println("#!/bin/bash");
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your script to the lines: end with endS");
        String input = "";
        while(true)
        {
            input = sc.nextLine();
            if(input.equals("endS"))
                break;
            printWriter.println(input);
        }

        printWriter.close();

        return tempScript;
    }
    public static boolean executeBashCommand(String command) {
        boolean success = false;
        Runtime r = Runtime.getRuntime();
        // Use bash -c so we can handle things like multi commands separated by ; and
        // things like quotes, $, |, and \. My tests show that command comes as
        // one argument to bash, so we do not need to quote it to make it one thing.
        // Also, exec may object if it does not have an executable file as the first thing,
        // so having bash here makes it happy provided bash is installed and in path.
        String[] commands = {"bash", "-c", command};
        try {
            Process p = r.exec(commands);

            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";

            while ((line = b.readLine()) != null) {
                System.out.println(line);
            }

            b.close();
            success = true;
        } catch (Exception e) {
            System.err.println("Failed to execute bash with command: " + command);
            e.printStackTrace();
        }
        return success;
    }
}