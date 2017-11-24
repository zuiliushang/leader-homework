package lesson.leader;

public class MyItem {
	
	byte type;
	
	byte color;
	
	byte price;

	@Override
	public String toString() {
		return "MyItem [type=" + type + ", color=" + color + ", price=" + price + "]";
	}

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
		insertSort(byteStore.storeByteArray,0,byteStore.storeByteArray.length);
		for (int i = 0; i < 100; i++) {
			System.out.println(byteStore.getMyItem(i).toString() + " ");
		}
		
	}
	//插入排序
	public static void insertSort(byte[] arr,int low ,int high){
		for (int i = 3*low+5 ; i < high; i=i+3) {
			if (arr[i] < arr[i-3]) {//后一个比前一个小 那么要插入到前一个的位置
				byte tmp = arr[i];
				byte tmp1 = arr[i-1];
				byte tmp2 = arr[i-2];
				arr[i] = arr[i-3];
				int j = i-3;//前一个的位置开始循环遍历到第一个 已排序好的
				for (; j >= low && tmp < arr[j]; j=j-3) {
					arr[j+3] = arr[j];
					arr[j+2] = arr[j-1];
					arr[j+1] = arr[j-2];
				}
				arr[j+3] = tmp;
				arr[j+2] = tmp1;
				arr[j+1] = tmp2;
			}
		}
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
		return new MyItem(storeByteArray[bound], 
				storeByteArray[bound+1], 
				storeByteArray[bound+2]);
	}
}


class IntStore{
	public int[] storeByteArray = new int[3000];

	private static final int TYPE_BIT = 0;
	private static final int COLOR_BIT = 8;
	private static final int PRICE_BIT = 16;
	public void putMyItem(int index ,MyItem item) {
		int number 
			= (item.getType()<< TYPE_BIT) 
				+ (item.getColor() << COLOR_BIT) 
				+ (item.getPrice() << PRICE_BIT);
		storeByteArray[index] = number;
	}
	public MyItem getMyItem(int index) {
		int number = storeByteArray[index];
		return new MyItem((byte)(number >>> TYPE_BIT)
				, (byte)(number >>> COLOR_BIT)
				, (byte)(number >>> PRICE_BIT));
	}
	public static void main(String[] args) {
		MyItem item  = new MyItem((byte)100, (byte)21, (byte)-1);
		IntStore intStore = new IntStore();
		intStore.putMyItem(1, item);
		item = intStore.getMyItem(1);
		System.out.println(item.toString());
	}
}
