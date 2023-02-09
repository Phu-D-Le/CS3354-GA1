package Project2;

class ArticleNode {
    // node for linkedlist in bst that holds 
    // book id, title, author, and the next node in linked list
    int id;
    String title;
    String author;
    ArticleNode next;

    ArticleNode(int i, String t, String a, ArticleNode r){
        // new node now holds given values, i, t, a, and r for id, title, author, and next node
        // next node holds the next ArticleNode in the linkedlist for bst
        id = i;
        title = t;
        author = a;
        next = r;
    }
}