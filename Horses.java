package exercisethree;

public class Horses {
	
	private String name;
	private boolean healthy;
	private Integer distanceTraveled;
	private String warCry;
	
	public Horses(String name, 
				  boolean healthy, 
				  Integer distanceTraveled, 
				  String warCry) {
		super();
		this.name = name;
		this.healthy = healthy;
		this.distanceTraveled = distanceTraveled;
		this.warCry = warCry;
	}
	
	public String getName() {
		return name;
	}
	
	public Boolean getHealthy() {
		return healthy;
	}
	
	public Integer getDistanceTraveled() {
		return distanceTraveled;
	}
	
	public String getWarCry() {
		return warCry;
	}
}