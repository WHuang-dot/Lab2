import java.io.*;
import java.util.Scanner;

public class ChartReport {

    public static void main(String[] args) throws FileNotFoundException {

        int numberOfFiles = 2;
        for(int i=1;i<=numberOfFiles;i++){

        }
        Scanner sc = new Scanner(System.in);
        //Importing csv file
        File file = new File("D:\\regional-global-daily-latest.csv"); //reading data from this file
        Scanner reader;
        String line="";

        int maxArtistPossible = 300;

        String artists[] = new String[maxArtistPossible];
        int artistsCount[] = new int[maxArtistPossible];

        int currentIndex=0;
        //reset count to zero
        for(int i=0;i<artistsCount.length;i++){
            artistsCount[i] = 0;
        }
        int songRecordCount=0;

        try {
            reader = new Scanner(file);
            while(reader.hasNextLine()){
                songRecordCount++;
                //read line
                line = reader.nextLine();
                String columns[] = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                //Artist will be at position 2
                String tmpArtist = columns[2];

                //remove quotes from the line and determine if the artist is counted.
                tmpArtist = tmpArtist.replaceAll("\"", "");
                for(String art : tmpArtist.split(",")){
                    boolean found = false;
                    for(int i=0;i<currentIndex;i++){
                        if(art.equalsIgnoreCase(artists[i])){
                            artistsCount[i]++;
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        artists[currentIndex] = art;
                        artistsCount[currentIndex]=1;
                        currentIndex++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }


        PrintStream writer = new PrintStream(new File("D:\\SpotifyChartReport.txt"));

        //print total songs recorded
        writer.println("Total song loaded : "+(songRecordCount-2));
        //display unique artist using
        writer.println("Total unique artist count is : "+currentIndex);

        //Call the getMax method from GetHighest class to get the maximum count and the index of the maximum count from the array
        int max = GetHighest.getMax(artistsCount);
        String maxArtist = artists[GetHighest.getMaxIndex(artistsCount)];

        //display the artist with the most songs in the chart
        writer.println("Artist with the most songs in the chart is " + maxArtist + " with a total of " + max + " songs");

        //Display all the artist and the number of times they appeared on the chart
        writer.printf("%-25s%s\n","Artist","Occurence count");
        for(int i=2;i<currentIndex;i++){
            writer.printf("%-25s %-25s\n",artists[i],artistsCount[i]);
        }

        sc.close();
    }



}