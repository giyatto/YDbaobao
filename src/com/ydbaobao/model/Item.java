package com.ydbaobao.model;

public class Item {
	private int itemId;
	private Product product;
	private Order order;
	private String size;
	private int quantity;
	
	public Item() {
		
	}
	
	public Item(int itemId, Product product, Order order, String size, int quantity) {
		super();
		this.itemId = itemId;
		this.product = product;
		this.order = order;
		this.size = size;
		this.quantity = quantity;
	}

	public int getItemId() {
		return itemId;
	}

	public Product getProduct() {
		return product;
	}

	public Order getOrder() {
		return order;
	}

	public String getSize() {
		return size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemId;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemId != other.itemId)
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity != other.quantity)
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", product=" + product + ", order=" + order + ", size=" + size
				+ ", quantity=" + quantity + "]";
	}
}
