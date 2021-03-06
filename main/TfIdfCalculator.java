package main;



import java.util.List;

public class TfIdfCalculator {
	
	
	
	public double tf(List<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
        	
            if (term.equalsIgnoreCase(word))
                result++;
        }
        System.out.println("tf"+result/ doc.size());
        return result / doc.size();
    }

    /**
     * @param docs list of list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     */
    public double idf(List<List<String>> docs, String term) {
        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        System.out.println("idf"+Math.log(docs.size() / n));
        return Math.log(docs.size() / n);
    }

    /**
     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
     */
    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
        return tf(doc, term) * idf(docs, term);

    }

}

