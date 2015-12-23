package com.zhanjixun.domain2;

/**
 * 商家评论概要
 * 
 * @author 詹命天子
 *
 */
public class CommentsSummary {
	private Integer commentSize;
	private Integer midComment;
	private Integer goodComment;
	private Integer bedComment;
	private Shop shop;

	/**
	 * @return commentSize
	 */
	public Integer getCommentSize() {
		return commentSize;
	}

	/**
	 * @param commentSize
	 *            要设置的 commentSize
	 */
	public void setCommentSize(Integer commentSize) {
		this.commentSize = commentSize;
	}

	/**
	 * @return midComment
	 */
	public Integer getMidComment() {
		return midComment;
	}

	/**
	 * @param midComment
	 *            要设置的 midComment
	 */
	public void setMidComment(Integer midComment) {
		this.midComment = midComment;
	}

	/**
	 * @return goodComment
	 */
	public Integer getGoodComment() {
		return goodComment;
	}

	/**
	 * @param goodComment
	 *            要设置的 goodComment
	 */
	public void setGoodComment(Integer goodComment) {
		this.goodComment = goodComment;
	}

	/**
	 * @return bedComment
	 */
	public Integer getBedComment() {
		return bedComment;
	}

	/**
	 * @param bedComment
	 *            要设置的 bedComment
	 */
	public void setBedComment(Integer bedComment) {
		this.bedComment = bedComment;
	}

	/**
	 * @return shop
	 */
	public Shop getShop() {
		return shop;
	}

	/**
	 * @param shop
	 *            要设置的 shop
	 */
	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
