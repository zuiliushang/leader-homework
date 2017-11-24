package lesson.leader;

public class MyItem {
	
	byte type;
	
	byte color;
	
	byte price;

	public MyItem(byte type, byte color, byte price) {
		super();
		this.type = type;
		this.color = color;
		this.price = price;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getColor() {
		return color;
	}

	public void setColor(byte color) {
		this.color = color;
	}

	public byte getPrice() {
		return price;
	}

	public void setPrice(byte price) {
		this.price = price;
	}
	
	public static byte get0_128() {
		return (byte) (Math.random()*128);
	}
	
	public static MyItem getRandomItem() {
		return new MyItem(get0_128(),get0_128(),get0_128());
	}
	
	
	public static void main(String[] args) {
		ByteStore byteStore = new ByteStore();
		for (int i = 0; i < 1000; i++) {
			byteStore.putMyItem(i, getRandomItem());
		}
		System.out.println(byteStore.storeByteArray.length);
		System.out.println(1);
	}
	
}
class ByteStore{
	
	public byte[] storeByteArray = new byte[3000];

	public void putMyItem(int index ,MyItem item) {
		int bound = 3*index;
		storeByteArray[bound]=item.getType();
		storeByteArray[bound+1]=item.getColor();
		storeByteArray[bound+2]=item.getPrice();
	}
	
	public MyItem getMyItem(int index) {
		int bound = 3*index;
		return new MyItem(storeByteArray[bound], storeByteArray[bound+1], storeByteArray[bound+2]);
	}
}