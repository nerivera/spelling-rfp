package org.hcpss.inst.nriver5714.aood.spellingrfp;

public class SubstringRange {
	private int beginIndex;
	private int endIndex;
	public SubstringRange() {
		beginIndex = -1;
		endIndex = -1;
	}
	public SubstringRange(int beginIndex, int endIndex) {
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}
	public boolean isEmpty() {
		return (getBeginIndex() == -1);
	}
	public int getBeginIndex() {
		return this.beginIndex;
	}
	public int getEndIndex() {
		return this.endIndex;
	}
	@Override
	public String toString() {
		return "[" + (isEmpty() ? "" : beginIndex + ", " + endIndex) + "]";
	}
}
