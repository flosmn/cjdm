package weka;

import weka.associations.Apriori;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.NumericToNominal;

public class wekaTest {

	public static void main(String[] args) throws Exception {
		final String FILENAME = "bank-data";
		
		/** CSV (excel format) import */
		//still not working -.-
//		String CSVPFAD = "C:/Users/Juergen/Desktop/bank-data.csv";
//		CSVLoader loader = new CSVLoader(); 
//		loader.setSource(new File(CSVPFAD));
//		Instances data = loader.getDataSet();
		
		
		/** Datei laden*/
		DataSource source = new DataSource("./javaprojectsources/data/" + FILENAME + ".arff");
		Instances data = source.getDataSet();

		/** numeric Werte zu nominal konvertieren */
		NumericToNominal numToNom = new NumericToNominal(); 
		String[] numToNomOptions = new String[3];
		numToNomOptions[0] = "-R ";
		numToNomOptions[1] = "1,";
		numToNomOptions[2] = "2";
		numToNom.setOptions(numToNomOptions);
		numToNom.setInputFormat(data);
		data = NumericToNominal.useFilter(data, numToNom); 		

		/** bilde Assoziationen*/
		Apriori apriori = new Apriori();
		
		//setze Schwelle der minimalen Treffer einer Regel
		//		zu niedrig => schreibe eine Regel die nur für ein Element aus vielen gilt
		apriori.setLowerBoundMinSupport(0.11);	//0.1 Standard
		//apriori.setUpperBoundMinSupport(0.1);	//1 Standard
		
		//setzte Schwelle für minimale Trefferwahrscheinlichkeit einer Regel
		apriori.setMinMetric(0.95);
		apriori.setNumRules(100);
		
		
		/**
		 * Methode generiert große Sets mit minimaler Unterstützung
		 * und danach "association rules" mit minimaler Zusicherung
		 */
		//return void
		apriori.buildAssociations(data); //works
		
		/**
		 * Methode generiert große Sets mit minimaler Unterstützung
		 * und danach "association rules" mit minimaler Zusicherung
		 */
		//apriori.mineCARs(data);
		// ==
		//m_car = true, kann via setCar geschehen
		//buildAssosiations
		//return rules

		System.out.println(apriori);

		
//		FastVector[] rules = apriori.mineCARs(data);
//		for (int i=0; i<rules.length; i++){
//			for (int j=0; j<rules[i].capacity(); j++){
//				System.out.println(rules[i].elementAt(j));	
//			}
//		}
		
		
		/** params missing */
//		 String[] options = new String[1];
//		 options[0] = "-U";            // unpruned tree
//		 J48 tree = new J48();         // new instance of tree
//		 tree.setOptions(options);     // set the options
//		 tree.buildClassifier(data);   // build classifier
		 
//		String[] aprioriParams = {"-t ./data/" + FILENAME + ".arff"};
//		weka.associations.Apriori.main(aprioriParams);
		 
		//System.out.println(tree);
		//
		// /**
		// * get data
		// */
		// InstanceQuery query = new InstanceQuery();
		// query.setUsername("nobody");
		// query.setPassword("");
		// query.setQuery("select * from whatsoever");
		// // if your data is sparse, then you can say so too
		// // query.setSparseData(true);
		// Instances data2 = query.retrieveInstances();
		//

		// /**
		// * delete old file
		// */
		// File datei = new File("./data/" + FILENAME + ".arff");
		// if (datei.exists()) {
		// datei.delete();
		// System.out.println("Datei gelöscht!");
		// }

		// /**
		// * save to Arff
		// */
		String dataString = "@relation golfWeatherMichigan_1988/02/10_14days"
				+ "@attribute outlook {sunny, overcast rainy}"
				+ "@attribute windy {TRUE, FALSE}"
				+ "@attribute temperature real" + "@attribute humidity real"
				+ "@attribute play {yes, no}!" + " @data"
				+ "sunny,FALSE,85,85,no" + "sunny,TRUE,80,90,no"
				+ "overcast,FALSE,83,86,yes" + "rainy,FALSE,70,96,yes"
				+ "rainy,FALSE,68,80,yes";
		// System.out.println(dataString);
		// Instances dataSet = new Instances(dataString, new FastVector(), 100);
		// ArffSaver saver = new ArffSaver();
		// // saver.setInstances(dataSet);
		//
		//
		// saver.setFile(new File("./data/" + FILENAME + ".arff"));
		// saver.setDestination(new File("./data/" + FILENAME + ".arff")); //
		// **not**
		// // necessary
		// // in 3.5.4 and
		// // later
		// saver.writeBatch();

//		/**
//		 * read data
//		 */
//		DataSource source = new DataSource("./data/" + FILENAME + ".arff");
//		Instances data = source.getDataSet();
//		// setting class attribute if the data format does not provide this
//		// information
//		// E.g., the XRFF format saves the class attribute information as well
//		if (data.classIndex() == -1) {
//			data.setClassIndex(data.numAttributes() - 1);
//		}
//		// System.out.println(data);
//
//		// FastVector my_nominal_values = new FastVector(2);
//		// my_nominal_values.addElement("0");
//		// my_nominal_values.addElement("1");
//		// Attribute position = new Attribute("class", my_nominal_values);
//
//		System.out.println(data.checkForAttributeType(0));
//		System.out.println(data.checkForAttributeType(1));
//		System.out.println(data.checkForAttributeType(139));
//		System.out.println(data.checkForAttributeType(140));
//
//		System.out.println("Number of Attributes"
//				+ source.getStructure().numAttributes());
//		System.out.println(data.attribute(0));
//		System.out.println(data.attribute(1));

		// NaiveBayes.forName("classifierName", options);

	}
}
