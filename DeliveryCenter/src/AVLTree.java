
public class AVLTree {
	
    public Node root;
    
    void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    int height(Node n) {
        if (n == null) {
        	return -1;
        }else{
        	return n.height;
        }
    }

    int getBalance(Node n) {
        if (n == null) {
        	return 0;
        }else{
        	return height(n.right) - height(n.left);
        }
    }
    
    // rotate AVLtree to the right
    Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
    
 // rotate AVLtree to the left
    Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
    
// rebalence the AVLtree 
// run time O(1)
    Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right))
                z = rotateRight(z);
            else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }
    
// recursive insert to tree each thread checks balance of tree
// run time O(logn)
    Node insert(Node node, double key,Delivery data) {
        if (node == null) {
            return new Node(key,data);
        } else if (node.key >= key) {
        		node.left = insert(node.left, key,data);
        } else if (node.key <= key) {
        		node.right = insert(node.right, key,data);
        }
        return rebalance(node);
    }
    
// recursive delete from tree and checks if the tree is balanced
// run time O(logn)
    Node delete(Node node, double key) {
        if (node == null) {
            return node;
        } else if (node.key > key) {
            node.left = delete(node.left, key);
        } else if (node.key < key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node mostLeftNode = mostLeftNode(node.right);
                node.Data = mostLeftNode.Data;
                node.key = mostLeftNode.key;
                node.right = delete(node.right, node.key);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    // returns the data in mostLeftDelivery
	public Delivery mostLeftDelivery(Node n) {
		while (n.left != null) {
			n = n.left;
		}
		return n.Data;
	}
	
// returns the most left node	
	public Node mostLeftNode(Node n) {
		while (n.left != null) {
			n = n.left;
		}
		return n;
	}
	
// returns the distance	of the most left node 
	public double mostLeftKey(Node m) {
		while (m.left != null) {
			m = m.left;
		}
		return m.key;
	}
	
	
	  public String inOrder(Node node) {
		    if (node != null) {
		    	inOrder(node.left);
		    	System.out.println(node.Data + " ");
		        inOrder(node.right);
		    }
		    return "";
		  }
}