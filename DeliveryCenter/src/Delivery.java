

public class Delivery {
	private double x;
	private double y;
	private int deliveryId;
	
	public Delivery (double x, double y, int deliveryId) {
		this.x = x;
		this.y = y;
		this. deliveryId = deliveryId;
	}	

	public int getDeliveryId () {
		return deliveryId;
	}
	
	public void setDeliveryId (int deliveryId) {
		this. deliveryId = deliveryId;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getDistanceFrom(int x, int y) {
			return Math.sqrt(Math.pow((this.x-x), 2) + Math.pow((this.y-y),2));
		}
		
	
	@Override
	public String toString() {
		return String.format("Delivery: [x= %4.2f y= %4.2f Id = %d]", x, y, deliveryId); 
	}
	
	
}