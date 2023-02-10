package Project2;

class TreeNode {
    // holds nodes for the bst that points to the next nodes
    // leftChild and rightChild are necessary for the bst
    // while keyword holds a genre and head holds the first linkedlist node that holds articleNode
    TreeNode leftChild;
    String keyword;
    TreeNode rightChild;
    ArticleNode head;

    public TreeNode(String key, ArticleNode rec){
        // instantiates left and right child, keyword will be checked by BST
        // head will store pointer to first ArticleNode which will change upon insertion of another ArticleNode
        leftChild = null;
        keyword = key;
        rightChild = null;
        head = rec;
    }
}