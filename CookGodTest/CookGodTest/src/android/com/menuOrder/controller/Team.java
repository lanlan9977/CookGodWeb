package android.com.menuOrder.controller;

public class Team {
	private int id;
	private String name;
	private double wPercent;
	private String area;
	
	public Team() {
		super();
	}

	public Team(int id, String name, double wPercent, String area) {
		super();
		this.id = id;
		this.name = name;
		this.wPercent = wPercent;
		this.area = area;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getwPercent() {
		return wPercent;
	}

	public void setwPercent(double wPercent) {
		this.wPercent = wPercent;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
}
