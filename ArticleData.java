package Project2;

import java.util.*;
class ArticleData {
    // articleData is received from driverfile which stores Id, Title, and Author
    // the keywords are where the ArticleNode (which is created using ID, title, author)
    // will be stored, ArticleData holds keyword arraylist, which contains the treenodes
    // that BST will use to create the BST (and also store the articleNodes in)
    int id;
	String title;
	String author;
	ArrayList<String> keywords;

	ArticleData(int id, String title, String author, int keywordCount) {
        // new node creation that will be given id, title, and author, and will also 
        // hold an address to arrayList type string that is size keywordCount long
		this.id=id;
		this.title=title;
		this.author=author;
		keywords=new ArrayList<String>(keywordCount);
	}

    void addKeyword(String keyword) { 
        // used by driver to put keywords from .txt into keyword arraylist
        keywords.add(keyword);
    }
}