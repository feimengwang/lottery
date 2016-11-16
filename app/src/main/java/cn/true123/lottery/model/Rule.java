package cn.true123.lottery.model;

public class Rule implements ILottery{
	private String name;
	private String content;

	public Rule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rule(String name, String content) {
		super();
		this.name = name;
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
