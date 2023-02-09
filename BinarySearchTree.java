package Project2;

class BinarySearchTree {
    // the Binary Search Tree will contain TreeNodes, these TreeNodes will store left and right child
    // but will also store a pointer to a dev-created LinkedList of ArticleNodes
    // ArticleData is received which we sort into ArticleNodes and the keywords from ArticleData has stored as TreeNodes
    TreeNode root;

    public void addArticle(ArticleData article) {
      // this function will take articleData extracted from .txt, create an ArticleNode,
      // it will travel through each articleNode
      ArticleNode newArticleNode = new ArticleNode(article.id, article.title, article.author, null);
      for (String key:article.keywords) {
        // for each keyword inside the article.keyword list we will want to put a new ArticleNode 
        // inside the linkedList in those tree nodes that contain the genre (keyword) that we're looking for
        boolean inserted = insertTreeNode(key, newArticleNode);
        //System.out.printf("%s %s\n", key, inserted);
        if (!inserted) {
          // append the ArticleNode
          appendArticleNode(key, newArticleNode);
        }
      }
    }

    public void appendArticleNode(String keyword, ArticleNode newArticleNode) {
      // takes article node and stores it to resort the list by appending the article node as the first node
      // the first node is appended at the start of the list and the treeNode will point to that node as head
      // the previous first will become the second node.
      TreeNode current = root;
      while (current != null) { // keep on traveling down the tree until we find the keyword that we're looking for
        if (keyword.compareTo(current.keyword) < 0) { // moves to the left child if keyword is less than the search
          //System.out.printf("Test Left %s %s %d\n", keyword, current.keyword, newArticleNode.id);
          current = current.leftChild;
        }
        else if (keyword.compareTo(current.keyword) > 0) { // moves to right child if keyword is greater (comparing keyword)
          //System.out.printf("Test Right %s %s %d\n", keyword, current.keyword, newArticleNode.id);
          current = current.rightChild;
        }
        else { // this means the node we landed on is the keyword we are looking for, 
          //System.out.printf("Test Equal %s %s %d\n", keyword, current.keyword, newArticleNode.id);
          newArticleNode.next = current.head;
          current.head = newArticleNode;
          return;
        }
      }
    }

    public boolean insertTreeNode(String keyword, ArticleNode newArticleNode) {
      // we'll want to insert a tree node inside the BST, so the primary idea is to traverse to where
      // the node should be and then if it does exist, does nothing
      // otherwise we will be running through to where it should be and placing a new TreeNode
      if (root == null) { // base case for creating node if root is null, this can occur upon instantiation of BST
        root = new TreeNode(keyword, newArticleNode);
        return true;
      }
      else { // this occurs every time when we want to traverse the tree
        TreeNode parent = null;
        TreeNode current = root;
        while (current != null) { // we will be looking for the tree node/ figuring out where to place it
          if (keyword.compareTo(current.keyword) < 0) { // if keyword is less than, we move to left child
            //System.out.printf("Test Left %s %s %d\n", keyword, current.keyword, newArticleNode.id);
            parent = current;
            current = current.leftChild;
          }
          else if (keyword.compareTo(current.keyword) > 0) { // if keyword is greater than, we move to right child
            //System.out.printf("Test Right %s %s %d\n", keyword, current.keyword, newArticleNode.id);
            parent = current;
            current = current.rightChild;
          }
          else { // if the child is in front of us and it exists then we don't do anything and leave
            //System.out.printf("Test Equal %s %s %d\n", keyword, current.keyword, newArticleNode.id);
            return false;
          }
        }
        // upon reaching where it should be, it will decide if it wants to put the tree node
        // as left child or right child of the node (assuming that there is no node due to logic above)
        if (keyword.compareTo(parent.keyword) < 0) { // move to left child and create new node
          parent.leftChild = new TreeNode(keyword, newArticleNode);
        }
        else if (keyword.compareTo(parent.keyword) > 0) { // move to right child and create new node
          parent.rightChild = new TreeNode(keyword, newArticleNode);
        }
      }
      return true;
    }

    public void print() { // this makes it so every time we see do print(bst)
      inOrderPrint(root); // it will print the tree instead of the address to the root node
    }

    private void inOrderPrint(TreeNode node) {
      // recursive function that calls and prints the ArticleNode data in this pattern
      // left child - own node - right child
      // it moves all the way down the left tree, then moves back to middle, then right, repeats
      if (node == null) return;
      inOrderPrint(node.leftChild);
      System.out.printf("\n%s\n", node.keyword);
      ArticleNode article = node.head;
      while (article != null) { // prints all the ArticleNode data inside the tree node
        System.out.printf("\t %d | %s | %s-->\n", article.id, article.title, article.author);
        article = article.next;
      } 
      inOrderPrint(node.rightChild);
    }

    private void preOrderPrint(TreeNode root) {
      // recursive function that calls and prints the ArticleNode data in this pattern
      // own node, left child, right child
      // it prints own node, then moves to do the same as left and right
      if (root == null) return;
      System.out.printf("%s\n\t", root.keyword);
      ArticleNode article = root.head;
      while (article != null) {
        System.out.printf("%d %s %s-->\n", article.id, article.title, article.author);
        article = article.next;
      }
      inOrderPrint(root.leftChild);
      inOrderPrint(root.rightChild);
    }
}