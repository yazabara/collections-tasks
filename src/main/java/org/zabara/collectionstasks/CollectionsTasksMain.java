package org.zabara.collectionstasks;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.zabara.collectionstasks.graph.Graph;
import org.zabara.collectionstasks.graph.GraphImp;
import org.zabara.collectionstasks.laststand.OrderProcess;
import org.zabara.collectionstasks.parking.Parking;
import org.zabara.collectionstasks.texttask.TextStatistic;
import org.zabara.collectionstasks.texttask.TextTask;
import org.zabara.collectionstasks.texttask.version2014.StaticTextParser;
import org.zabara.collectionstasks.texttask.version2014.StaticTextParserImpl;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Yaroslav on 10.02.14.
 */
public class CollectionsTasksMain {

    private static Logger logger = Logger.getLogger(CollectionsTasksMain.class.toString());

    public static void main(String[] args) {
        testLastStand();
        testGraph();
       // testTextTask();
        testTextTask2();
        parkingTest();

        int count = 9;
        Random random = new Random(new Date().getTime());
        List<Integer>  arrayList = new ArrayList<Integer>();

        for (int i =0; i < count; i++) {
            arrayList.add(random.nextInt(100));
        }


        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int qwe = 1;

        if (arrayList.get(0) < arrayList.get(arrayList.size()-1)) {
            min = arrayList.get(0);
            max = arrayList.get(arrayList.size()-1);
        } else {
            max = arrayList.get(0);
            min = arrayList.get(arrayList.size()-1);
        }

        for (int i = 1, j = arrayList.size() - 2; i < arrayList.size()/2+1; i++, j--) {
            if (arrayList.get(i) < arrayList.get(j)) {
                if(arrayList.get(i) < min ){
                    min = arrayList.get(i);
                }
                if(arrayList.get(j) > max ){
                    max = arrayList.get(j);
                }
            } else {
                if(arrayList.get(i) > max ){
                    max = arrayList.get(i);
                }
                if(arrayList.get(j) < min ){
                    min = arrayList.get(j);
                }
            }
            qwe += 3;

        }



        Collections.sort(arrayList);
        System.out.print("MIN : " + arrayList.get(0) + ", MAX : " + arrayList.get(arrayList.size()-1) + "\n");
        System.out.print("min : " + min + ", max : " + max + "  " + qwe);
    }



    private static void parkingTest() {
        Parking parking = new Parking();
		List result = parking.showParking(100);
        System.out.println(result);
		//check
		parkingTestCheck(result);
    }

	private static void parkingTestCheck(List result) {
		boolean isGoodParking = true;
		for (int i = 0; i < result.size(); i++) {
			if (result.lastIndexOf(i) < 0 && result.lastIndexOf(i) >= result.size()) {
				isGoodParking = false;
				System.out.println("i : " + i);
				break;
			}
		}
		System.out.println("Parking is good ? - " + isGoodParking );
	}



    @Deprecated
    private static void testTextTask(){
        TextTask textTask = new TextStatistic();
        Map<String,Integer> result = null;
        try {
            result = textTask.getWordsInfo("test.txt");
            textTask.writeResults(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testTextTask2(){
        StaticTextParser staticTextParser = new StaticTextParserImpl();
        try {
            InputStreamReader stringReader = new InputStreamReader(new FileInputStream("test.txt"));
            Map wordInfo = staticTextParser.getWordsCounts(stringReader);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("output.txt"));
            staticTextParser.parseMostPopularWords(wordInfo, outputStreamWriter, 10);
            staticTextParser.parseBiggerWords(wordInfo,outputStreamWriter);
            staticTextParser.parseMinWords(wordInfo, outputStreamWriter);
            outputStreamWriter.close();
            stringReader.close();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }



    }

    private static void testLastStand() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("last-stand/app-context.xml");
        context.refresh();

        OrderProcess orderProcess = context.getBean("last-stand", OrderProcess.class);

        System.out.println(orderProcess.getLastStand().toString());
    }

    private static void testGraph(){
        Graph<String,Integer> graph = new GraphImp<String>();
        graph.addVertex("Tver");
        graph.addVertex("Moscow");
        graph.addVertex("Minsk");
        graph.addVertex("St-Peterburg");
        graph.addVertex("London");

        graph.addEdge("Tver", "Moscow" , 12);
        graph.addEdge("Tver", "Minsk" , 121);
        graph.addEdge("Tver", "St-Peterburg" , 123);

        graph.addEdge("Moscow", "Tver" , 33);
        graph.addEdge("Moscow", "Minsk" , 3);
        graph.addEdge("Moscow", "St-Peterburg" , 3);


        System.out.println(graph.getMinEdges("Tver"));
        //System.out.println(graph.toString());
    }
}
