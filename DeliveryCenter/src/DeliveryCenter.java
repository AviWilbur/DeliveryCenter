
public class DeliveryCenter {
	private int T,X1,Y1,X2,Y2;
	private double distance1,distance2;
	private double myMIN1,myMIN2;
	private Delivery closest1,closest2,temp;
	int size = 0;
	
	//1 tree for each center 
	AVLTree tree1 = new AVLTree();
	AVLTree tree2 = new AVLTree();
	
	public DeliveryCenter(int t, int x1, int y1, int x2, int y2) {
		this.T = t;
		this.X1 = x1;
		this.Y1 = y1;
		this.X2 = x2;
		this.Y2 = y2;		
	}
	
	public int getSize() {
		return size;
	}
	
// add the delivery to both tree's, update the minimum distance and the closest delivery and the size of the center
// run time O(logn) 
	void add(Delivery data) {
		if (size + 1 > T) {
			System.out.println("Sorry only " + T + " deliveries can be stored" );
		}else {
			size++;
			distance1 = data.getDistanceFrom(X1,Y1);
			distance2 = data.getDistanceFrom(X2,Y2);
			new Node(distance1,data);
			new Node(distance2,data);
			tree1.root = tree1.insert(tree1.root,distance1,data);
			tree2.root = tree2.insert(tree2.root, distance2,data);
			myMIN1 = tree1.mostLeftKey(tree1.root);
			myMIN2 = tree2.mostLeftKey(tree2.root);
			if (distance1 <= myMIN1) {
				myMIN1 = distance1;
				closest1 = data;
			}
			if (distance2 <= myMIN2) {
				myMIN2 = distance2;
				closest2 = data;
			}
		}
	}
	
	// run time O(1)
	public Delivery getClosest(int centerNum) {
		if (centerNum == 1) {
			return closest1;
		}else {
			return closest2;
		}
	}
	
// remove the closest delivery from the tree thats in input and remove that same delivery in the other tree
// update size and the closest delivery and the minimum distance
// run time O(logn)
	Delivery removeClosest(int centerNum) {
		size--;
		if (size <= 0) {
			centerNum = 0;
		}
		if (centerNum == 1) {
			temp = closest1;
			tree1.root = tree1.delete(tree1.root,myMIN1);
			tree2.root = tree2.delete(tree2.root,closest1.getDistanceFrom(X2,Y2));
			closest1 = tree1.mostLeftDelivery(tree1.root);
			closest2 = tree2.mostLeftDelivery(tree2.root);
			myMIN1 = closest1.getDistanceFrom(X1, Y1);
			myMIN2 = closest2.getDistanceFrom(X2, Y2);
			return temp;
		}
		if (centerNum == 2) {
			temp = closest2;
			tree2.root = tree2.delete(tree2.root,myMIN2);
			tree1.root = tree1.delete(tree1.root,closest2.getDistanceFrom(X1,Y1));
			closest1 = tree1.mostLeftDelivery(tree1.root);
			closest2 = tree2.mostLeftDelivery(tree2.root);
			myMIN1 = closest1.getDistanceFrom(X1, Y1);
			myMIN2 = closest2.getDistanceFrom(X2, Y2);
			return temp;	
		}else {
			temp = closest2;
			tree2.root = tree2.delete(tree2.root,myMIN2);
			tree1.root = tree1.delete(tree1.root,closest2.getDistanceFrom(X1,Y1));
			closest1 = null;
			closest2 = null;
			return temp;
		}
	}
	
	@Override
	public String toString() {
		System.out.println("Delivery locations size is: " + size);
		return tree1.inOrder(tree1.root);
	}
}