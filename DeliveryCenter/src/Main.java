//Delivery management system where deliveries are stored and tracked based on their proximity to two specified delivery centers. 
//Each delivery is represented by the Delivery class, which stores its coordinates and calculates its distance from the centers using Euclidean
//distance. The DeliveryCenter class uses two AVL trees to manage deliveries, ensuring efficient insertion, deletion, and retrieval of the 
//closest deliveries to each center. The system maintains balance in the trees, allowing for optimized performance when managing a large number
//of deliveries, and updates the closest delivery whenever changes occur.


public class Main {

	public static void main(String[] args) {
		DeliveryCenter center = new DeliveryCenter(100,20,20,10,30);
		center.add(new Delivery(10, 28, 1));
		center.add(new Delivery(10, 31, 2));
		center.add(new Delivery(40, 30, 3));
		center.add(new Delivery(19, 22, 4));
		center.add(new Delivery(20, 21, 5));
		
		
		System.out.println("*******\n Init the delivery Center:\n");
		System.out.println(center);
		System.out.println("Find the closest from the 1st center");
		
		System.out.println(center.getClosest(1));
		
		Delivery deli = center.removeClosest(1);
		System.out.println("Find the closest from the 2nd center");
		
		System.out.println(center.getClosest(2));
		
		deli= center.removeClosest(2);
		System.out.println("********\n After the changes:");
		System.out.println(center);
	}
}