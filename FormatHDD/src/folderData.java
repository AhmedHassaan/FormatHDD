
public class folderData {
	private String name;
	private long total,used,free;
	public folderData() {
		this.name = "";
		this.total = 0;
		this.used = 0;
		this.free = 0;
	}
	public folderData(String name, long total, long used, long free) {
		this.name = name;
		this.total = total;
		this.used = used;
		this.free = free;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public long getUsed() {
		return used;
	}
	public void setUsed(long used) {
		this.used = used;
	}
	public long getFree() {
		return free;
	}
	public void setFree(long free) {
		this.free = free;
	}
	
	
	
}
