package com.mysite.products;

import java.sql.Date;

public class ProductsDTO {

	private int p_code;

	private String p_name;
	
	private String p_kind;

	private String p_price;

	private String p_content;

	private String p_quantity;
	
	private Date indate;
	
	public ProductsDTO() {}

	public int getP_code() {
		return p_code;
	}

	public void setP_code(int p_code) {
		this.p_code = p_code;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_kind() {
		return p_kind;
	}

	public void setP_kind(String p_kind) {
		this.p_kind = p_kind;
	}

	public String getP_price() {
		return p_price;
	}

	public void setP_price(String p_price) {
		this.p_price = p_price;
	}

	public String getP_content() {
		return p_content;
	}

	public void setP_content(String p_content) {
		this.p_content = p_content;
	}

	public String getP_quantity() {
		return p_quantity;
	}

	public void setP_quantity(String p_quantity) {
		this.p_quantity = p_quantity;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	@Override
	public String toString() {
		return "ProductsDTO [p_code=" + p_code + ", p_name=" + p_name + ", p_kind=" + p_kind + ", p_price=" + p_price
				+ ", p_content=" + p_content + ", p_quantity=" + p_quantity + ", indate=" + indate + "]";
	}
}