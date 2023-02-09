package Project2;

import java.util.ArrayList;
import java.io.*;

class Project2Testing {
    // driver file for testing, checks BST, loads datafile.txt, and runs program, then prints tree
    BufferedReader fileReader; // used to read files
    BinarySearchTree bst; // instantiate tree
    public static void main(String [] args) {
      Project2Testing Test = new Project2Testing("datafile.txt");
      //System.out.println("I will stay focused!, or maybe not hehe");
      Test.bst.print(); // this occurs after the datafile is read into the BST and the BST already sorted the data
    }

    public Project2Testing(String filename) {
      try { // try catch to check if the BST was able to read in the file
        bst = new BinarySearchTree();
        fileReader = new BufferedReader(new FileReader(filename));

        ArticleData article;
        while ((article = readNextRecord()) != null) {
          bst.addArticle(article);
        }
      }
      catch (IOException e) { // Oops!, file may have been read, but there (may) be no data inside the file
        e.printStackTrace(); // or was read incorrectly or other stuff that could be somewhat unforseen
      }
    }

    public ArticleData readNextRecord() {
      // this function will read the files from .txt into the BST
      // we achieve this by knowing the pattern the data shows up
      // then parsing the data we receive (int first, string second, string second)
      // and then store them as ArticleData node, which we will parse later into ArticleNodes
      // the numkeys stores the number of fields/genres/keywords it's reading (and storing into)
      // these keywords are found in the keywords() arraylist found in ArticleData which are available as treeNodes
      if (fileReader == null) {
        System.out.println("Error: You must open the file first.");
        return null;
      }
      else {
        ArticleData article;
        try{
          String data = fileReader.readLine();
          if (data==null) // data == null occurs when there's no more data to be had (end of file for instance)
            return null;
          // read data into articleNode after checking that there is data to be had
          int titleId = Integer.parseInt(data);
          String title = fileReader.readLine();
          String author = fileReader.readLine();
          int numKeys = Integer.parseInt(fileReader.readLine());
          article = new ArticleData(titleId, title, author, numKeys); // instantiate articleData object containing data

          String keyword;
          for (int  i=0; i<numKeys; i++) { // we will want to fill the ArticleData's keyword arraylist now with keywords
            keyword = fileReader.readLine();
            article.addKeyword(keyword);
          }
          // we can add testing for space later; for now read the blank line
          fileReader.readLine();
        }
        catch(NumberFormatException e) { 
          System.out.println("Error: Number expected!");
          return null;
        }
        catch(Exception e){
          System.out.println("Fatal Error: " + e);
          return null;
        }
        return article;
      }
    }
}