package entity;

public class Sentence {
	public String word;
	public int dataId,id;
    public Double tfidf;
    public String line;
    public String vector;
    public String tree;
    
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	
	public void setWord(String word) {
		this.word=word;
		
	}
	
	public String getWord() {
		return this.word;
	}
	
	public int getDataId() {
		return this.dataId;
	}
	public void setTfiidf(Double tfidf) {
		this.tfidf=tfidf;
	}
	public Double getTfiidf() {
		return this.tfidf;
	}
	
}
