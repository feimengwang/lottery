package cn.true123.lottery.model;

public class Ball implements ILottery{
	private boolean red = false;
	private boolean blue = false;
	private String num;

	/**
	 *
	 * @param num
	 * @param blue
	 * @param red
     */
	public Ball(String num, boolean blue, boolean red) {
		this.num = num;
		this.blue = blue;
		this.red = red;
	}

	public Ball() {
	}

	public boolean isBlue() {
		return blue;
	}

	public Ball setBlue(boolean blue) {
		this.blue = blue;
		if(blue){
			red = false;
		}else{
			red = true;
		}
		return this;
	}

	public boolean isRed() {
		return red;
	}

	public Ball setRed(boolean red) {
		this.red = red;
		if(red){
			blue = false;
		}else{
			blue= true;
		}
		return this;
	}

	public String getNum() {
		return num;
	}

	public Ball setNum(String num) {
		this.num = num;
		return this;
	}

	@Override
	public String toString() {
		return "Ball [num=" + num + "]";
	}
	
}
